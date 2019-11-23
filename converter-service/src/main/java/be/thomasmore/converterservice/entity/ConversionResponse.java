package be.thomasmore.converterservice.entity;

public class ConversionResponse {
    private String originalText;
    private String convertedText;

    ConversionResponse(String original, String converted) {
        this.originalText = original;
        this.convertedText = converted;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getConvertedText() {
        return convertedText;
    }

    public void setConvertedText(String convertedText) {
        this.convertedText = convertedText;
    }
}
