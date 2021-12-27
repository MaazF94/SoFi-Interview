package com.sofi.interview.controller;

import com.sofi.interview.data.model.SearchGiphy;
import com.sofi.interview.service.GifService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GifController {

    GifService gifService;

    public GifController(GifService gifService) {
        this.gifService = gifService;
    }

    @GetMapping("/search/{searchTerm}")
    public ResponseEntity<SearchGiphy> search(@PathVariable String searchTerm) {
        SearchGiphy results = gifService.getGifsSearchResults(searchTerm);
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

}