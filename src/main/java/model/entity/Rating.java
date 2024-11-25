package model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="stars")
    private int stars;
    @Column(name="comment")
    private String comment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="donation")
    @JsonIgnore
    private Donation donation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="person")
    private Person person;
}
