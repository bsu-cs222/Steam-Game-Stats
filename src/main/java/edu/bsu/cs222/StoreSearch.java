package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class StoreSearch {
    public String jsonToCurrentPrice(String json) {
        JSONArray jsonRedirectsCurrent = JsonPath.read(json,"$..price");
        String price;
        if (jsonRedirectsCurrent.toString().equals("[null]")) {
            price = "-1";
        } else {
            JSONArray jsonCurrentToPrice = JsonPath.read(jsonRedirectsCurrent, "$..price");
            price = jsonCurrentToPrice.get(0).toString();
        }
        return price;
    }

    public String jsonToLowestPrice(String json) {
        JSONArray jsonRedirectsToLowest = JsonPath.read(json, "$..lowest");
        String price;
        if (jsonRedirectsToLowest.toString().equals("[null]")) {
            price = "-1";
        } else {
            JSONArray jsonCurrentToPrice = JsonPath.read(jsonRedirectsToLowest, "$..price");
            price = jsonCurrentToPrice.get(0).toString();
        }
        return price;
    }

    public JSONArray jsonToReview(String json) {
        return JsonPath.read(json,"$..steam");
    }
}
