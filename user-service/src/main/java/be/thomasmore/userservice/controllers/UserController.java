package be.thomasmore.userservice.controllers;

import be.thomasmore.userservice.enitity.User;
import be.thomasmore.userservice.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(path = "/users")
public class UserController
{
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser (@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password){
        User n = new User();
        n.setFirstName(firstName);
        n.setLastName(lastName);
        n.setEmail(email);
        n.setPassword(password);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/id")
    public @ResponseBody User getUserById( @RequestParam int id) {
        return userRepository.findUserById(id);
    }

    @DeleteMapping("delUser/{id}")
    public @ResponseBody int delUserById(@PathVariable("id") int id){
        Long deleted = userRepository.deleteById(id);
        return deleted.intValue();
    }

}
