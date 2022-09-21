package tn.amirensit.pocs.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tn.amirensit.pocs.demo.domain.AvisPenalite;
import tn.amirensit.pocs.demo.domain.Commentaire;
import tn.amirensit.pocs.demo.repositories.AvisPenaliteRepository;
import tn.amirensit.pocs.demo.repositories.CommentaireRepository;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private CommentaireRepository commentaireRepository;

	@Autowired
	private AvisPenaliteRepository avisPenaliteRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		 AvisPenalite avisPenalite = AvisPenalite.builder().build();
		 avisPenalite = avisPenaliteRepository.save(avisPenalite);
		Commentaire commentaire = Commentaire.builder()
				.message("hello")
				.avisPenalite(avisPenalite)
				.build();
		commentaireRepository.save(commentaire);
		avisPenalite = avisPenaliteRepository.findAllIncludingAssociations().get(0);
		System.out.println("commentaires dans l'avis: " + avisPenalite.getCommentaires().get(0).getMessage());
	}
}
