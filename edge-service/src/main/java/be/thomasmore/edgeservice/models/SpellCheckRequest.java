package be.thomasmore.edgeservice.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Request a spelling check")
public class SpellCheckRequest {
    @ApiModelProperty(notes = "Text to submit")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
