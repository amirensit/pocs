package tn.amirensit.pocs.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.amirensit.pocs.demo.domain.AvisPenalite;

import java.util.List;

@Repository
public interface AvisPenaliteRepository extends JpaRepository<AvisPenalite, Long> {

    @Query(value = "select distinct ape" +
            " from AvisPenalite ape" +
            " join fetch ape.commentaires")
    List<AvisPenalite> findAllIncludingAssociations();
}
