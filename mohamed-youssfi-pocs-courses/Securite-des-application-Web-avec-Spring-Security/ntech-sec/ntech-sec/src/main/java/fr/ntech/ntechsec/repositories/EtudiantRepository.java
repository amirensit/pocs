package fr.ntech.ntechsec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.ntech.ntechsec.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
