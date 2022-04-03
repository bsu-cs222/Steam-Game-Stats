package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.minidev.json.JSONArray;

import static edu.bsu.cs222.URLCreator.urlDesigner;

public class APIDataGetter {
    public static String currentPriceData(String input){
        Client client = ClientBuilder.newClient();
        Response response = client.target(urlDesigner(input))
                .request(MediaType.APPLICATION_JSON)
                .get();
        // called api to give json data
        //https://api.isthereanydeal.com/v01/game/overview/?key=&region=us&country=US&plains=&shop=steam&ids=app%2F460930%2Csub%2F37125%2Cbundle%2F7078&allowed=steam%2Cgog&optional=
        String jsonPrice = jsonToString(response.readEntity(String.class));
        double priceAsDouble = Double.parseDouble(jsonPrice);
        String priceCeil = jsonPrice.replace(".0","");
        return priceCeil;
    }
    public static String historicalLowData(String input){
        Client client = ClientBuilder.newClient();
        Response response = client.target(urlDesigner(input))
                .request(MediaType.APPLICATION_JSON)
                .get();
        String jsonPrice = jsonToLowest(response.readEntity(String.class));
        double priceAsDouble = Double.parseDouble(jsonPrice);
        String priceCeil = String.valueOf(Math.ceil(priceAsDouble));
        priceCeil = priceCeil.replace(".0","");
        return priceCeil;
    }

    private static String jsonToString(String json) {
        JSONArray jsonRedirectsCurrent = JsonPath.read(json,"$..price");
        JSONArray jsonCurrentToPrice = JsonPath.read(jsonRedirectsCurrent,"$..price");
        return jsonCurrentToPrice.get(0).toString();
    }
    private static String jsonToLowest(String json) {
        JSONArray jsonRedirectsToLowest = JsonPath.read(json, "$..lowest");
        JSONArray jsonLowestToPrice = JsonPath.read(jsonRedirectsToLowest,"$..price");
        return jsonLowestToPrice.get(0).toString();
    }
}
