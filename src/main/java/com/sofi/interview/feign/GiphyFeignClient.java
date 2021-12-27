package com.sofi.interview.feign;

import com.sofi.interview.data.model.SearchGiphy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "giphy", url = "${giphy.service.url}")
public interface GiphyFeignClient {
    
    @GetMapping(value = "${giphy.service.gifs.search}")
    SearchGiphy getGifsSearchResult(@RequestParam("api_key") String apiKey, @RequestParam("q") String query, @RequestParam("limit") int limit);
}
