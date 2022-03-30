package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.minidev.json.JSONArray;

public class URLCreator {
    public static void main(String[] args) {
        System.out.println(urlMaker("falloutiv"));
    }
    public static String urlMaker(String input){
        Client client = ClientBuilder.newClient();
        Response response = client.target(urlDesigner(input))
                .request(MediaType.TEXT_PLAIN_TYPE)
                .get();
        // called api to give json data

        //insert below for method to call current price


        //https://api.isthereanydeal.com/v01/game/overview/?key=&region=us&country=US&plains=&shop=steam&ids=app%2F460930%2Csub%2F37125%2Cbundle%2F7078&allowed=steam%2Cgog&optional=
        return jsonToString(response.readEntity(String.class));
    }

    private static String jsonToString(String json) {
        JSONArray jsonRedirects = JsonPath.read(json,"$..price_new");
        return jsonRedirects.get(0).toString();
    }

    public static String urlDesigner(String input){
        return String.format("https://api.isthereanydeal.com/v01/game/prices/?key=420d3d4cd304e25e8b0ac4e1a58dfa406283946d&plains=%s&region=us&country=US&shops=steam",input);
    }
}
