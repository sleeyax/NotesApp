package be.thomasmore.spellcheckservice.api;


import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class GrammarBotApi {
    // read api.key from application.properties
    @Value("${api.key}")
    private String apiKey;

    // API URL
    final private String url = "http://api.grammarbot.io/v2/check";

    /**
     * Checks spelling by using the grammarbot API
     * @param text text to check
     */
    public ResponseEntity<String> checkSpelling(String text) throws Exception {
        if (this.apiKey == null)
            throw new Exception("Grammarbot API key not set!");

        RestTemplate restTemplate = new RestTemplate();

        // set request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // add form-data
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("api_key", apiKey);
        map.add("language", "en-US");
        map.add("text", text);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        // send request
        return restTemplate.postForEntity(this.url, request, String.class);
    }
}
