package edu.bsu.cs222;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static edu.bsu.cs222.URLCreator.urlSearch;

public class APIDataGetter {
    static StoreSearch storeSearch = new StoreSearch();
    public static Integer currentPriceData(String title, String store){
        Client client = ClientBuilder.newClient();
        Response response = client.target(urlSearch(title, store))
                .request(MediaType.APPLICATION_JSON)
                .get();
        // called api to give json data
        //https://api.isthereanydeal.com/v01/game/overview/?key=&region=us&country=US&plains=&shop=steam&ids=app%2F460930%2Csub%2F37125%2Cbundle%2F7078&allowed=steam%2Cgog&optional=
        String jsonPrice = storeSearch.jsonToString(response.readEntity(String.class));
        return Integer.parseInt(parseToIntegerFormat(jsonPrice));
    }

    public static Integer historicalLowData(String title, String store){
        Client client = ClientBuilder.newClient();
        Response response = client.target(urlSearch(title,store))
                .request(MediaType.APPLICATION_JSON)
                .get();
        String jsonPrice = storeSearch.jsonToLowest(response.readEntity(String.class));
        return Integer.parseInt(parseToIntegerFormat(jsonPrice));
    }
    public static String parseToIntegerFormat(String string){
        double priceAsDouble = Double.parseDouble(string);
        String priceCeiling = String.valueOf(Math.ceil(priceAsDouble));
        priceCeiling = priceCeiling.replace(".0","");
        return priceCeiling;
    }
}
