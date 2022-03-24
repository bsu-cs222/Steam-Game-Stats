package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.minidev.json.JSONArray;
import net.minidev.json.writer.JsonReader;
import org.netbeans.html.json.impl.JSON;

import java.net.MalformedURLException;
import java.net.URL;

public class URLCreator {
    public String urlMaker(String input){
        Client client = ClientBuilder.newClient();
        Response response = client.target(urlDesigner(input))
                .request(MediaType.TEXT_PLAIN_TYPE)
                .get();
        // called api to give json data

        //insert below for method to call current price
        jsonToString(response.readEntity(JSON.class));


        return "";
    }

    private String jsonToString(JSON json) {
        JSONArray jsonRedirects = JsonPath.read(json,"$..Price");
        String body = jsonRedirects.get(0).toString();
        return body;
    }

    public String urlDesigner(String input){
        return String.format("https://api.isthereanydeal.com/v01/game/prices/?key=420d3d4cd304e25e8b0ac4e1a58dfa406283946d&plains=%s&region=us&country=US&shops=steam",input);
    }
}
