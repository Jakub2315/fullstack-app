package com.example.pasir_lubera_jakub.model;

import com.example.pasir_lubera_jakub.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "app_groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Membership> memberships = new ArrayList<>();

    public void addMembership(Membership membership) {
        memberships.add(membership);
        membership.setGroup(this);
    }

    public void removeMembership(Membership membership) {
        memberships.remove(membership);
        membership.setGroup(null);
    }
}