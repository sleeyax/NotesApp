package be.thomasmore.edgeservice.controllers;

import be.thomasmore.edgeservice.ServiceEndpoints;
import be.thomasmore.edgeservice.models.Note;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
