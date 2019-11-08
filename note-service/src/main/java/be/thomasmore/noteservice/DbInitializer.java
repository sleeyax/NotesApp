package be.thomasmore.noteservice;

import be.thomasmore.noteservice.entity.Note;
import be.thomasmore.noteservice.repository.NoteRepository;

public class DbInitializer {
    public static void createExampleNotes(NoteRepository repository) {
        repository.save(new Note(1, "hello world"));
    }
}
