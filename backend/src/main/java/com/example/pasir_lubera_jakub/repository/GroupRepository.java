package com.example.pasir_lubera_jakub.repository;

import com.example.pasir_lubera_jakub.model.Group;
import com.example.pasir_lubera_jakub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByMemberships_User(User user);
}
