package be.thomasmore.noteservice.repository;

import be.thomasmore.noteservice.entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "notes", path = "notes")
public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findAllByUserId(@Param("userid") long userid);
}
