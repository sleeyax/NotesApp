package be.thomasmore.edgeservice.controllers;

import be.thomasmore.edgeservice.ServiceEndpoints;
import be.thomasmore.edgeservice.models.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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

    @GetMapping("notes/search/userid={userid}")
    @ApiOperation(
            value = "Finds notes by user id",
            response = Note.class,
            responseContainer = "List"
    )
    public List<Note> getNotesByUserId(@PathVariable("userid") int userId) {
        TypeReference<List<Note>> type = new TypeReference<List<Note>>() {};
        List<Note> notes = objectMapper.convertValue(restTemplate.getForObject(ServiceEndpoints.NOTES + "/search/userid=" + userId, Note[].class), new TypeReference<List<Note>>() {});
        return  notes;
    }

    @PostMapping("notes/create")
    @ApiOperation(
            value = "Creates a new note",
            response = Note.class
    )
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        // TODO: find ou why this returns 'note' instead of the newly created note
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
            value = "Deletes a note by id from specified user",
            response = Note.class
    )
    @ApiResponses(value = @ApiResponse(code = 400, message = "Note not found or invalid user") )
    public ResponseEntity<Note> deleteNote(@PathVariable String noteid, @PathVariable int userid) {
        // get the note from the db by its id.
        // if it doesn't exist, or the user is not the creator of the note, return a bad request.
        Note note = restTemplate.getForObject(ServiceEndpoints.NOTES + "/" + noteid, Note.class);
        if (note == null || note.getUserId() != userid)
            return ResponseEntity.badRequest().build();

        restTemplate.delete(ServiceEndpoints.NOTES + "/" + noteid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("users/search/userid={userid}")
    @ApiOperation(
            value = "Get user by id",
            response = User.class
    )
    @ApiResponses(value = @ApiResponse(code = 404, message = "User not found") )
    public ResponseEntity getUserByUserId(@PathVariable("userid") int userId) {
        User user = restTemplate.getForObject(ServiceEndpoints.USERS + "/id?id=" + userId, User.class);
        if (user == null) return ResponseEntity.notFound().build();
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("users/{userid}")
    @ApiOperation(
            value = "Delete user by id"
    )
    public ResponseEntity deleteUserByUserId(@PathVariable("userid") int userId) {
        try {
            restTemplate.delete(ServiceEndpoints.USERS + "/" + userId);
        }catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("users/{userid}")
    @ApiOperation(
            value = "Update user by id",
            response = User.class
    )
    @ApiResponses(value = @ApiResponse(code = 404, message = "User not found") )
    public ResponseEntity updateUserByUserId(@PathVariable("userid") int userId, @RequestBody User updatedUser) {
        restTemplate.put(ServiceEndpoints.USERS + "/" + userId, updatedUser, User.class);
        return ResponseEntity.ok().build();
    }

    @PostMapping("spelling/check")
    @ApiOperation(
            value = "Check spelling of specified text",
            response = String.class
    )
    public ResponseEntity<String> checkSpelling(@RequestBody SpellCheckRequest spellCheckRequest) {
        return restTemplate.postForEntity(ServiceEndpoints.SPELLING + "/check", spellCheckRequest, String.class);
    }

    @PostMapping("convert/toupper")
    @ApiOperation(
            value = "Convert text to upper case",
            response = ConversionResponse.class
    )
    public ResponseEntity<String> convertToUpper(@RequestBody ConversionRequest request) {
        return restTemplate.postForEntity(ServiceEndpoints.CONVERT + "/to/upper", request, String.class);
    }

    @PostMapping("convert/tolower")
    @ApiOperation(
            value = "Convert text to lower case",
            response = ConversionResponse.class
    )
    public ResponseEntity<String> convertToLower(@RequestBody ConversionRequest request) {
        return restTemplate.postForEntity(ServiceEndpoints.CONVERT + "/to/lower", request, String.class);
    }

    @PostMapping("convert/tocapitalize")
    @ApiOperation(
            value = "Capitalize text",
            response = ConversionResponse.class
    )
    public ResponseEntity<String> convertToCapitalize(@RequestBody ConversionRequest request) {
        return restTemplate.postForEntity(ServiceEndpoints.CONVERT + "/to/capitalized", request, String.class);
    }

    @PostMapping("convert/toleet")
    @ApiOperation(
            value = "Convert text to leet speak",
            response = ConversionResponse.class
    )
    public ResponseEntity<String> convertToLeet(@RequestBody ConversionRequest request) {
        return restTemplate.postForEntity(ServiceEndpoints.CONVERT + "/to/leet", request, String.class);
    }
}
