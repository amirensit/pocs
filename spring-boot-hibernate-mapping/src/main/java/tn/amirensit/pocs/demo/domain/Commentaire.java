package tn.amirensit.pocs.demo.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "t_commentaire_com")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class Commentaire {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "com_message", nullable = false, length = 500)
    private String message;

    @ManyToOne
    @JoinColumn(name = "ape_id", referencedColumnName = "id", nullable = false, updatable = false)
    private AvisPenalite avisPenalite;

}
