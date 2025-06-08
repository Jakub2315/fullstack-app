package com.example.pasir_lubera_jakub.service;

import com.example.pasir_lubera_jakub.dto.GroupDto;
import com.example.pasir_lubera_jakub.model.Group;
import com.example.pasir_lubera_jakub.model.Membership;
import com.example.pasir_lubera_jakub.model.User;
import com.example.pasir_lubera_jakub.repository.DebtRepository;
import com.example.pasir_lubera_jakub.repository.GroupRepository;
import com.example.pasir_lubera_jakub.repository.MembershipRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final MembershipRepository membershipRepository;
    private final MembershipService membershipService;
    private final DebtService debtService;
    private final DebtRepository debtRepository;

    public GroupService(GroupRepository groupRepository, MembershipRepository membershipRepository, MembershipService membershipService, DebtService debtService, DebtRepository debtRepository) {
        this.groupRepository = groupRepository;
        this.membershipRepository = membershipRepository;
        this.membershipService = membershipService;
        this.debtService = debtService;
        this.debtRepository = debtRepository;
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @MutationMapping
    public Group createGroup(GroupDto groupDTO) {
        User owner = membershipService.getCurrentUser(); // pobieramy aktualnie zalogowanego
        Group group = new Group();
        group.setName(groupDTO.getName());
        group.setOwner(owner);

        Group savedGroup = groupRepository.save(group);

        Membership membership = new Membership();
        membership.setUser(owner);
        membership.setGroup(savedGroup);
        membershipRepository.save(membership);

        return savedGroup;
    }

    public void deleteGroup(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grupa o ID " + id + " nie istnieje."));

        debtService.deleteAll(debtRepository.findByGroupId(id));
        membershipRepository.deleteAll(membershipRepository.findByGroupId(id));

        groupRepository.delete(group);
    }

}