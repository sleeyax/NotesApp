package be.thomasmore.noteservice.controllers;

import be.thomasmore.noteservice.entity.Note;
import be.thomasmore.noteservice.repository.NoteRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    private NoteRepository repository;

    @GetMapping(value = "/")
    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    @PostMapping(value = "/")
    public Note postNote(@RequestBody Note note) {
        note.setId(ObjectId.get().toString());
        note.setCreatedAt(new Date());
        repository.save(note);
        return note;
    }

    @GetMapping(value = "/search/userid={userid}")
    public List<Note> getNotesByUserId(@PathVariable("userid") int userid) {
        return repository.findAllByUserId(userid);
    }

    @GetMapping(value = "/{id}")
    public Note getNoteById(@PathVariable("id") String id) {
        return repository.findById(id).orElse(null);
    }
}
