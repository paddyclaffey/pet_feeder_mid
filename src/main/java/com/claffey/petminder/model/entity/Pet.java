package com.claffey.petminder.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false, columnDefinition = "pet_type")
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String breed;

    @Column(nullable = false)
    private String colour;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private String size;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "caretakers",
            inverseJoinColumns = @JoinColumn(name = "pet_id"),
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

}
