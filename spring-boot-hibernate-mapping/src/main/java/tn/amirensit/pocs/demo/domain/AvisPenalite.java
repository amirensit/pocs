package tn.amirensit.pocs.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_avis_penalite_ape")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class AvisPenalite {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "avisPenalite", fetch = FetchType.LAZY)
    private List<Commentaire> commentaires;
}
