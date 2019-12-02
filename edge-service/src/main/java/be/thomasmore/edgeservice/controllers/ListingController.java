package be.thomasmore.edgeservice.controllers;

import be.thomasmore.edgeservice.ServiceEndpoints;
import be.thomasmore.edgeservice.models.Note;
import be.thomasmore.edgeservice.models.SpellCheckRequest;
import be.thomasmore.edgeservice.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("listings")
public class ListingController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("notes/count")
    @ApiOperation(
            value = "Count the amount of notes in the notes database",
            response = Note.class,
            responseContainer = "List"
    )
    public int getNotesCount() {
        Note[] notes = restTemplate.getForObject(ServiceEndpoints.NOTES, Note[].class);
        return notes != null ? notes.length : 0;
    }

    @GetMapping("notes/search/userid={userid}")
    @ApiOperation(
            value = "Finds notes by user id",
            response = Note.class,
            responseContainer = "List"
    )
    public List<Note> getNotesByUserId(@PathVariable("userid") int userId) {
        TypeReference<List<Note>> type = new TypeReference<List<Note>>() {};
        List<Note> notes = objectMapper.convertValue(restTemplate.getForObject(ServiceEndpoints.NOTES + "/search/userid=" + userId, Note[].class), new TypeReference<List<Note>>() {});
        // TODO: return all user notes
        return  notes;
    }

    @PostMapping("notes/create")
    @ApiOperation(
            value = "Creates a new note",
            response = Note.class
    )
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        note.setUpdatedAt(new Date());
        return restTemplate.postForEntity(ServiceEndpoints.NOTES, note,  Note.class);
    }

    @PutMapping("notes/update")
    @ApiOperation(
            value = "Update a note",
            response = Note.class
    )
    public ResponseEntity<Note> updateNote(@RequestBody Note updatedNote) {
        // TODO: validation
        restTemplate.put(ServiceEndpoints.NOTES + "/" + updatedNote.getId(), updatedNote, Note.class);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("notes/delete/{noteid}/user/{userid}")
    @ApiOperation(
            value = "Deletes a note from specified user",
            response = Note.class
    )
    public ResponseEntity<Note> deleteNote(@PathVariable String noteid, @PathVariable int userid) {
        // get the note from the db by its id.
        // if it doesn't exist, or the user is not the creator of the note, return a bad request.
        Note note = restTemplate.getForObject(ServiceEndpoints.NOTES + "/" + noteid, Note.class);
        if (note == null || note.getUserId() != userid)
            return ResponseEntity.badRequest().build();

        restTemplate.delete(ServiceEndpoints.NOTES + "/" + noteid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("users/count")
    public int getUsersCount(){
        User[] users = restTemplate.getForObject(ServiceEndpoints.USERS + "/all", User[].class);
        return users != null ? users.length : 0;
    }

    @GetMapping("users/search/userid={userid}")
    public User getUserByUserId(@PathVariable("userid") int userId){
        User user = objectMapper.convertValue(restTemplate.getForObject(ServiceEndpoints.USERS + "/id?id=" + userId, User.class), User.class);
        return user;
    }

    @GetMapping("users/getAll")
    public List<User> getALlUsers(){
        TypeReference<List<User>> type = new TypeReference<List<User>>() {};
        List<User> users = objectMapper.convertValue(restTemplate.getForObject(ServiceEndpoints.USERS + "/all", User[].class), new TypeReference<List<User>>() {});
        // TODO: return all users
        return  users;
    }

    /**
     * Check spelling of specified text
     * @param spellCheckRequest JSON Object containing the text to check
     * @return json response
     */
    @PostMapping("spelling/check")
    public ResponseEntity<String> checkSpelling(@RequestBody SpellCheckRequest spellCheckRequest) {
        return restTemplate.postForEntity(ServiceEndpoints.SPELLING + "/check", spellCheckRequest, String.class);
    }

    @PostMapping("convert/toupper")
    public ResponseEntity<String> convertToUpper(@RequestBody String request) {
        return restTemplate.postForEntity(ServiceEndpoints.CONVERT + "/to/upper", request, String.class);
    }

    @PostMapping("convert/tolower")
    public ResponseEntity<String> convertToLower(@RequestBody String request) {
        return restTemplate.postForEntity(ServiceEndpoints.CONVERT + "/to/lower", request, String.class);
    }

    @PostMapping("convert/tocapitalize")
    public ResponseEntity<String> convertToCapitalize(@RequestBody String request) {
        return restTemplate.postForEntity(ServiceEndpoints.CONVERT + "/to/capitalized", request, String.class);
    }

    @PostMapping("convert/toleet")
    public ResponseEntity<String> convertToLeet(@RequestBody String request) {
        return restTemplate.postForEntity(ServiceEndpoints.CONVERT + "/to/leet", request, String.class);
    }
}
