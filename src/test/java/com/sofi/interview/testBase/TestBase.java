package com.sofi.interview.testBase;

import java.util.ArrayList;
import java.util.List;

import com.sofi.interview.data.model.GiphyData;
import com.sofi.interview.data.model.SearchGiphy;

public class TestBase {

    GiphyData giphyData;
    List<GiphyData> giphyDataLst;
    public SearchGiphy searchGiphy;

    static final String GIF_ID = "123";
    static final String GIF_URL = "www.giphy.com/gifs/theresult";

    public void setupCommonObjects() {
        giphyDataLst = new ArrayList<>();
        searchGiphy = new SearchGiphy();
        giphyData = new GiphyData(GIF_ID, GIF_URL);
    }

    public void populateSearchResults() {
        for (int i = 0; i < 5; i++) {
            giphyDataLst.add(giphyData);
        }
        searchGiphy.setGiphyData(giphyDataLst);
    }

    public void resetSearchResults() {
        searchGiphy.setGiphyData(new ArrayList<>());
    }

}
