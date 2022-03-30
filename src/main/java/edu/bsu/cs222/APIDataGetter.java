package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.writer.JsonReader;
import org.netbeans.html.json.impl.JSON;

import static edu.bsu.cs222.URLCreator.urlDesigner;

public class APIDataGetter {
    public void main(String[] args) {
        System.out.println(urlMaker("falloutiv"));
    }
    public static String urlMaker(String input){
        Client client = ClientBuilder.newClient();
        Response response = client.target(urlDesigner(input))
                .request(MediaType.APPLICATION_JSON)
                .get();
        // called api to give json data
        //https://api.isthereanydeal.com/v01/game/overview/?key=&region=us&country=US&plains=&shop=steam&ids=app%2F460930%2Csub%2F37125%2Cbundle%2F7078&allowed=steam%2Cgog&optional=
        return jsonToString(response.readEntity(String.class));
    }

    private static String jsonToString(String json) {
        JSONArray jsonRedirects = JsonPath.read(json,"$..price_new");
        return jsonRedirects.get(0).toString();
    }
}
