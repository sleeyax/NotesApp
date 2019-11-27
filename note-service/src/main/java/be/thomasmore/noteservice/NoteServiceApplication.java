package be.thomasmore.noteservice;

import be.thomasmore.noteservice.entity.Note;
import be.thomasmore.noteservice.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NoteServiceApplication implements CommandLineRunner {

	@Autowired
	private NoteRepository repository;

	public static void main(String[] args) { SpringApplication.run(NoteServiceApplication.class, args); }

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();
		DbInitializer.createExampleNotes(repository);
	}
}
