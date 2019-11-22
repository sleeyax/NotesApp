package be.thomasmore.spellcheckservice.entity;

public class SpellCheckResponse {
    private String[] misspelledWords;

    public String[] getMisspelledWords() {
        return misspelledWords;
    }

    public void setMisspelledWords(String[] misspelledWords) {
        this.misspelledWords = misspelledWords;
    }
}
