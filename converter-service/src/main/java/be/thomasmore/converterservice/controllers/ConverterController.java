package be.thomasmore.converterservice.controllers;

import be.thomasmore.converterservice.entity.ConversionRequest;
import be.thomasmore.converterservice.entity.ConversionResponse;
import org.apache.commons.lang.WordUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/convert")
public class ConverterController {
    @PostMapping(value = "/to/upper")
    public ConversionResponse toUpperCase(@RequestBody ConversionRequest request) {
        return new ConversionResponse(request.getText(), request.getText().toUpperCase());
    }

    @PostMapping(value = "/to/lower")
    public ConversionResponse toLowerCase(@RequestBody ConversionRequest request) {
        return new ConversionResponse(request.getText(), request.getText().toLowerCase());
    }

    @PostMapping(value = "/to/capitalized")
    public ConversionResponse toCapitalized(@RequestBody ConversionRequest request) {
        return new ConversionResponse(request.getText(), WordUtils.capitalize(request.getText()));
    }

    @PostMapping(value = "/to/leet")
    public ConversionResponse toLeetSpeak(@RequestBody ConversionRequest request) {
        String text = request.getText();
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

        return new ConversionResponse(request.getText(), text);
    }

}
