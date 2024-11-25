package model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @JsonIgnore
    @OneToMany(mappedBy = "donor",fetch = FetchType.LAZY)
    private List<Donation> donations;
    @OneToMany(mappedBy = "beneficiary",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Donation> benefits;
    @OneToMany(mappedBy = "person",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Rating> ratings;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
