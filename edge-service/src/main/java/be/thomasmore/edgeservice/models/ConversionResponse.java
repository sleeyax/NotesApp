package be.thomasmore.edgeservice.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Response of a text conversion")
public class ConversionResponse {
    @ApiModelProperty(notes = "Original text that was submitted")
    private String originalText;

    @ApiModelProperty(notes = "Converted text")
    private String convertedText;

    public String getOriginalText() {
        return originalText;
    }

    public String getConvertedText() {
        return convertedText;
    }
}
