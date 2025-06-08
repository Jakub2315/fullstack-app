package com.example.pasir_lubera_jakub.repository;

import com.example.pasir_lubera_jakub.model.Group;
import com.example.pasir_lubera_jakub.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    List<Membership> findByGroupId(Long groupId);

    Collection<Object> findByGroup(Group group);
}
