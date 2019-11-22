package be.thomasmore.edgeservice.controllers;

import be.thomasmore.edgeservice.ServiceEndpoints;
import be.thomasmore.edgeservice.models.Note;
import be.thomasmore.edgeservice.models.SpellCheckRequest;
import be.thomasmore.edgeservice.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("listings")
public class ListingController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("notes/count")
    public int getNotesCount() {
        Note[] notes = restTemplate.getForObject(ServiceEndpoints.NOTES, Note[].class);
        return notes != null ? notes.length : 0;
    }

    @GetMapping("notes/search/userid={userid}")
    public List<Note> getNotesByUserId(@PathVariable("userid") int userId) {
        TypeReference<List<Note>> type = new TypeReference<List<Note>>() {};
        List<Note> notes = objectMapper.convertValue(restTemplate.getForObject(ServiceEndpoints.NOTES + "search/userid=" + userId, Note[].class), new TypeReference<List<Note>>() {});
        // TODO: return all user notes
        return  notes;
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

    @PostMapping("users/add/firstname={firstname}&lastname={lastname}&email={email}&password={password}")
    public ResponseEntity<String> addUser(@PathVariable("firstname") String firstName, @PathVariable("lastname") String lastName,
                                         @PathVariable("email") String email, @PathVariable("password") String password){
        ResponseEntity<String> result = restTemplate.postForEntity(ServiceEndpoints.USERS + "/add?firstName=" + firstName + "&lastName=" + lastName
        + "&email=" + email + "&password=" + password, User.class, String.class);
        return result;
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
}
