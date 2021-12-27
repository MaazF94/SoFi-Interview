package com.sofi.interview.service;

import java.util.ArrayList;
import com.sofi.interview.data.model.SearchGiphy;
import com.sofi.interview.feign.GiphyFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GifService {

    GiphyFeignClient giphyFeignClient;

    @Value("${api_key}")
    private String apiKey;

    @Autowired
    public GifService(GiphyFeignClient giphyFeignClient) {
        this.giphyFeignClient = giphyFeignClient;
    }

    public SearchGiphy getGifsSearchResults(String searchTerm) {
        SearchGiphy giphyResults = callSearchApi(searchTerm);
        if (giphyResults.getGiphyData().size() < 5) {
            giphyResults.setGiphyData(new ArrayList<>());
            return giphyResults;
        }

        return giphyResults;
    }

    private SearchGiphy callSearchApi(String searchTerm) {
        return giphyFeignClient.getGifsSearchResult(apiKey, searchTerm, 5);
    }

}
