package com.sofi.interview.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.sofi.interview.data.model.SearchGiphy;
import com.sofi.interview.service.GifService;
import com.sofi.interview.testBase.TestBase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class GifControllerTest extends TestBase {

    @Mock
    private GifService gifService;
    private GifController gifController;

    @BeforeEach
    public void setup() {
        setupCommonObjects();
        gifController = new GifController(gifService);
    }

    @Test
    public void searchReturnsLessThanFiveResultsTest() {
        resetSearchResults();

        when(gifService.getGifsSearchResults(anyString())).thenReturn(searchGiphy);
        ResponseEntity<SearchGiphy> searchResults = gifController.search("Less than 5 results");

        assertEquals(searchGiphy, searchResults.getBody());
        assertEquals(0, searchResults.getBody().getGiphyData().size());
        assertEquals(HttpStatus.OK, searchResults.getStatusCode());
    }

    @Test
    public void searchReturnsFiveResultsTest() {
        populateSearchResults();

        when(gifService.getGifsSearchResults(anyString())).thenReturn(searchGiphy);
        ResponseEntity<SearchGiphy> searchResults = gifController.search("5 results");

        assertEquals(searchGiphy, searchResults.getBody());
        assertEquals(5, searchResults.getBody().getGiphyData().size());
        assertEquals(HttpStatus.OK, searchResults.getStatusCode());
    }
}
