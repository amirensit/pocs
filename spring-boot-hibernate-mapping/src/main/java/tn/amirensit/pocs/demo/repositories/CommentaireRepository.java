package tn.amirensit.pocs.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.amirensit.pocs.demo.domain.Commentaire;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
}
