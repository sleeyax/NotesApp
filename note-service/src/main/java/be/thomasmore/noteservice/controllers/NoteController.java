package be.thomasmore.noteservice.controllers;

import be.thomasmore.noteservice.entity.Note;
import be.thomasmore.noteservice.repository.NoteRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        if (note.getNote().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Note can't be empty!");
        note.setId(ObjectId.get().toString());
        note.setUpdatedAt(new Date());
        return repository.save(note);
    }

    @GetMapping(value = "/search/userid={userid}")
    public List<Note> getNotesByUserId(@PathVariable("userid") long userid) {
        return repository.findAllByUserId(userid);
    }

    @GetMapping(value = "/{id}")
    public Note getNoteById(@PathVariable("id") String id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping(value = "/{id}")
    public Note UpdateNote(@RequestBody Note updatedNote, @PathVariable String id) {
        return repository.findById(id).map(note -> {
            // update note
            note.setUpdatedAt(new Date());
            note.setNote(updatedNote.getNote());
            return repository.save(note);
        }).orElseGet(() -> {
            // note not found in db, insert new one
            updatedNote.setId(id);
            updatedNote.setUpdatedAt(new Date());
            return repository.save(updatedNote);
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteNote(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.ok("");
    }
}
