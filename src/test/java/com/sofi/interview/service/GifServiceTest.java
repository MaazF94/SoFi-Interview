package com.sofi.interview.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.sofi.interview.data.model.SearchGiphy;
import com.sofi.interview.feign.GiphyFeignClient;
import com.sofi.interview.testBase.TestBase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class GifServiceTest extends TestBase {

    @Mock
    GiphyFeignClient giphyFeignClient;
    GifService gifService;

    static final String API_KEY = "12345";

    @BeforeEach
    public void setup() {
        setupCommonObjects();
        gifService = new GifService(giphyFeignClient);
        ReflectionTestUtils.setField(gifService, "apiKey", API_KEY);
    }

    @Test
    public void getGifsSearchResultsReturnsLessThanFiveResultsTest() {
        resetSearchResults();

        when(giphyFeignClient.getGifsSearchResult(anyString(), anyString(), anyInt())).thenReturn(searchGiphy);
        SearchGiphy actualSearchGiphy = gifService.getGifsSearchResults("Less Than 5 Results");

        assertEquals(searchGiphy, actualSearchGiphy);
        assertEquals(0, actualSearchGiphy.getGiphyData().size());
    }

    @Test
    public void getGifsSearchResultsReturnsFiveResultsTest() {
        populateSearchResults();

        when(giphyFeignClient.getGifsSearchResult(anyString(), anyString(), anyInt())).thenReturn(searchGiphy);
        SearchGiphy actualSearchGiphy = gifService.getGifsSearchResults("Five Results");

        assertEquals(searchGiphy, actualSearchGiphy);
        assertEquals(5, actualSearchGiphy.getGiphyData().size());
    }

}
