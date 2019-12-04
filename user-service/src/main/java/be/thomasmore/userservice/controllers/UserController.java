package be.thomasmore.userservice.controllers;

import be.thomasmore.userservice.enitity.User;
import be.thomasmore.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/users")
public class UserController
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping(path = "/add")
    ResponseEntity addNewUser (@RequestBody User user) {
        // check if user already exists
        if (userRepository.findUserByEmail(user.getEmail()) != null)
            return ResponseEntity.badRequest().body("Email already registered to another user!");

        // save user in db
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);

        // hide pw from response
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/id")
    public @ResponseBody User getUserById( @RequestParam int id) {
        return userRepository.findUserById(id);
    }

    @DeleteMapping(path = "/delUser")
    public void delUserById(@RequestParam int id){
        userRepository.deleteById(id);
    }

    @PutMapping(path = "/putUser")
    public void putUser(@RequestParam User user){
        userRepository.putUser(user);
    }

}
