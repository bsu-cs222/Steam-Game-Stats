package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
public class SteamSearch {
    public String jsonToString(String json) {
        JSONArray jsonRedirectsCurrent = JsonPath.read(json,"$..price");
        JSONArray jsonCurrentToPrice = JsonPath.read(jsonRedirectsCurrent,"$..price");
        return jsonCurrentToPrice.get(0).toString();
    }
    public String jsonToLowest(String json) {
        JSONArray jsonRedirectsToLowest = JsonPath.read(json, "$..lowest");
        JSONArray jsonLowestToPrice = JsonPath.read(jsonRedirectsToLowest,"$..price");
        return jsonLowestToPrice.get(0).toString();
    }
}
