package edu.bsu.cs222;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class IsThereADealCaller {
    StoreSearch storeSearch = new StoreSearch();
    URLCreator urlCreator = new URLCreator();
    Client client = ClientBuilder.newClient();
    public Integer currentPriceData(String title, String store){
        Response response = client.target(urlCreator.urlSearch(title, store)).request(MediaType.APPLICATION_JSON).get();
        String jsonPrice = storeSearch.jsonToString(response.readEntity(String.class));
        return Integer.parseInt(parseToIntegerFormat(jsonPrice));
    }

    public Integer historicalLowData(String title, String store){
        Response response = client.target(urlCreator.urlSearch(title,store)).request(MediaType.APPLICATION_JSON).get();
        String jsonPrice = storeSearch.jsonToLowest(response.readEntity(String.class));
        return Integer.parseInt(parseToIntegerFormat(jsonPrice));
    }

    public String parseToIntegerFormat(String string){
        double priceAsDouble = Double.parseDouble(string);
        String priceCeiling = String.valueOf(Math.ceil(priceAsDouble));
        priceCeiling = priceCeiling.replace(".0","");
        return priceCeiling;
    }
    public String storeReviews(String title){
        Response response = client.target(urlCreator.returnsReviews(title)).request(MediaType.TEXT_PLAIN_TYPE).get();
        return storeSearch.jsonToReview(response.readEntity(String.class));
    }
}
