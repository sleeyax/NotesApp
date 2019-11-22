package be.thomasmore.spellcheckservice.controllers;

import be.thomasmore.spellcheckservice.api.GrammarBotApi;
import be.thomasmore.spellcheckservice.entity.SpellCheckRequest;
import be.thomasmore.spellcheckservice.entity.SpellCheckResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/spelling")
public class SpellCheckController {
    @Autowired
    private GrammarBotApi api;

    @PostMapping(value = "/check")
    public ResponseEntity<String> check(@RequestBody SpellCheckRequest request) throws Exception {
        return this.api.checkSpelling(request.getText());
    }
}
