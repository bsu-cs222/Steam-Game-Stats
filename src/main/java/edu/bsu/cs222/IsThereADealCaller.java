package edu.bsu.cs222;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.minidev.json.JSONArray;

public class IsThereADealCaller {
    private final StoreSearch storeSearch = new StoreSearch();
    private final URLCreator urlCreator = new URLCreator();
    private final Client client = ClientBuilder.newClient();
    private String percentageReview;
    private String totalReview;
    private String textReview;

    public Integer getCurrentPriceData(String title, String store) {
        try {
            Response response = client.target(urlCreator.urlSearch(title, store)).request(MediaType.APPLICATION_JSON).get();
            String jsonPrice = storeSearch.jsonToCurrentPrice(response.readEntity(String.class));
            return Integer.parseInt(parseToString(jsonPrice));
        } catch (Exception exception) {
            //throws -2 because -1 tells the UI the game doesn't exist
            return -2;
        }
    }

    public Integer getHistoricalLowData(String title, String store) {
        try {
            Response response = client.target(urlCreator.urlSearch(title, store)).request(MediaType.APPLICATION_JSON).get();
            String jsonPrice = storeSearch.jsonToLowestPrice(response.readEntity(String.class));
            return Integer.parseInt(parseToString(jsonPrice));
        } catch (Exception exception) {
            //throws -2 because -1 tells the UI the game doesn't exist
            return -2;
        }
    }

    public String parseToString(String string) {
        double priceAsDouble = Double.parseDouble(string);
        String priceCeiling = String.valueOf(Math.ceil(priceAsDouble));
        priceCeiling = priceCeiling.replace(".0", "");
        return priceCeiling;
    }

    //Method Below Stores the Review from Search title
    public void depositReviews(String title) {
        try {
            Response response = client.target(urlCreator.returnsReviewURL(title)).request(MediaType.TEXT_PLAIN_TYPE).get();
            JSONArray updatesStrings = storeSearch.jsonToReview(response.readEntity(String.class));
            String renameStuff = updatesStrings.get(0).toString();
            String[] part = renameStuff.split(",", 0);
            totalReview = part[1].replace("total=", "");
            percentageReview = part[0].replace("{perc_positive=", "");
            textReview = part[2].replace("text=", "");
        } catch (Exception ignored) {
        }
    }

    public String getPercentageReview() {
        return percentageReview;
    }

    public String getTotalReview() {
        return totalReview;
    }

    public String getTextReview() {
        return textReview;
    }
}
