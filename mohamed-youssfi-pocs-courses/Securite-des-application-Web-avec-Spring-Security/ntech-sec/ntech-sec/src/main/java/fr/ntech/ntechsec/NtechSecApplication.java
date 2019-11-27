package fr.ntech.ntechsec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.text.ParseException;

@SpringBootApplication
public class NtechSecApplication {

	public static void main(String[] args) throws ParseException {
		ApplicationContext ctx =  SpringApplication.run(NtechSecApplication.class, args);
		/*EtudiantRepository etudiantRepository = ctx.getBean(EtudiantRepository.class);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		etudiantRepository.save(new Etudiant("safouane", "safouane", df.parse("1983-11-12")));
		etudiantRepository.save(new Etudiant("rifaat", "rifaat", df.parse("1983-11-12")));
		etudiantRepository.save(new Etudiant("sara", "sara", df.parse("1983-11-12")));
		etudiantRepository.save(new Etudiant("hamza", "hamza", df.parse("1983-11-12")));*/

	}

}
