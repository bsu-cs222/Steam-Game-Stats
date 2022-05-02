package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class StoreSearchTest {
    private final StoreSearch storeSearch = new StoreSearch();
    private final InputStream overviewStream = StoreSearchTest.class.getClassLoader().getResourceAsStream("overviewTest.json");
    private final String priceOverviewJson;

    {
        assert overviewStream != null;
        priceOverviewJson = new BufferedReader(new InputStreamReader(overviewStream)).lines().parallel().collect(Collectors.joining("\n"));
    }

    @Test
    public void testCurrentPrice() {
        String expected = "19.99";
        // Input Stream is read, then it's buffered into a string so the methods in StoreSearch can parse into prices
        String actual = storeSearch.jsonToCurrentPrice(priceOverviewJson);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testLowestPrice() {
        String expected = "4.99";
        String actual = storeSearch.jsonToLowestPrice(priceOverviewJson);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testReviewData(){
        InputStream reviewStream = StoreSearchTest.class.getClassLoader().getResourceAsStream("reviewDataTest.json");
        assert reviewStream != null;
        String dataReviewJson = new BufferedReader(new InputStreamReader(reviewStream)).lines().parallel().collect(Collectors.joining("\n"));
        String expected = "[{\"perc_positive\":80,\"total\":159318,\"text\":\"Very Positive\\nVery Positive\",\"timestamp\":1634592789}]";
        String actual = storeSearch.jsonToReview(dataReviewJson).toString();
        Assertions.assertEquals(expected,actual);
    }
}
