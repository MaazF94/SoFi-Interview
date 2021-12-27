package com.sofi.interview.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiphyData {

    private String gifId;
    @JsonProperty("url")
    private String url;

    @JsonProperty("gif_id")
    public String getGifId() {
        return this.gifId;
    }

    @JsonProperty("id")
    public void setGifId(String gifId) {
        this.gifId = gifId;
    }

}
