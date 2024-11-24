package model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="date")
    private LocalDateTime date;
    @Column(name="img")
    private String img;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor")
    private Person donor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiary")
    private Person beneficiary;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thing")
    private Thing thing;
    @OneToMany(mappedBy = "donation", fetch = FetchType.LAZY)
    private List<Rating> ratings;
}
