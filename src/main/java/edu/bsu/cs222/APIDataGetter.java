package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.minidev.json.JSONArray;

import static edu.bsu.cs222.URLCreator.urlDesigner;

public class APIDataGetter {
    public static String main3(String input) {
        return urlMaker(input);
    }
    public static String urlMaker(String input){
        Client client = ClientBuilder.newClient();
        Response response = client.target(urlDesigner(input))
                .request(MediaType.APPLICATION_JSON)
                .get();
        // called api to give json data
        //https://api.isthereanydeal.com/v01/game/overview/?key=&region=us&country=US&plains=&shop=steam&ids=app%2F460930%2Csub%2F37125%2Cbundle%2F7078&allowed=steam%2Cgog&optional=
        String price = jsonToString(response.readEntity(String.class));
        String price2 = price.replace(".99","");
        System.out.println(price2);
        return price2;
    }
    public String historicalLowData(String input){
        Client client = ClientBuilder.newClient();
        Response response = client.target(urlDesigner(input))
                .request(MediaType.APPLICATION_JSON)
                .get();

    }

    private static String jsonToString(String json) {
        JSONArray jsonRedirects = JsonPath.read(json,"$..price_new");
        return jsonRedirects.get(0).toString();
    }
}
