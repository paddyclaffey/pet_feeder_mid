package com.claffey.petminder.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "PETS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

//    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private LocalDate dob;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "caretakers",
            inverseJoinColumns = @JoinColumn(name = "pet_id"),
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

}
