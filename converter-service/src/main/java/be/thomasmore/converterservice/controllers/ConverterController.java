package be.thomasmore.converterservice.controllers;

import org.apache.commons.lang.WordUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/convert")
public class ConverterController {
    // TODO: use POST request instead
    // TODO: respond with json

    // http://localhost:9002/convert/to/upper?text=aa
    @GetMapping(value = "/to/upper")
    public String toUpperCase(@RequestParam String text) {
        return text.toUpperCase();
    }

    @GetMapping(value = "/to/lower")
    public String toLowerCase(@RequestParam String text) {
        return text.toLowerCase();
    }

    @GetMapping(value = "/to/capitalized")
    public String toCapitalized(@RequestParam String text) {
        return WordUtils.capitalize(text);
    }

    @GetMapping(value = "/to/leet")
    public String toLeetSpeak(@RequestParam String text) {
        HashMap<String, String> leet = new HashMap<>();
        leet.put("a", "4");
        leet.put("b", "6");
        leet.put("c", "(");
        leet.put("d", "[}");
        leet.put("e", "&");
        leet.put("f", "f");
        leet.put("g", "g");
        leet.put("h", "#");
        leet.put("i", "!");
        leet.put("j", "j");
        leet.put("k", "]{");
        leet.put("l", "l");
        leet.put("m", "m");
        leet.put("n", "n");
        leet.put("o", "()");
        leet.put("p", "p");
        leet.put("q", "q");
        leet.put("r", "r");
        leet.put("s", "$");
        leet.put("t", "t");
        leet.put("u", "u");
        leet.put("v", "\\/");
        leet.put("w", "w");
        leet.put("x", "%");
        leet.put("y", "y");
        leet.put("z", "z");

        for (String key : leet.keySet()) {
            String value = leet.get(key);
            text = text.replace(key, value).replace(key.toUpperCase(), value);
        }

        return text;
    }

}
