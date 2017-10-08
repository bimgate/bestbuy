package com.bestbuy.qa.helpers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by bratislav.miletic on 07/10/2017.
 */
public class Convert {
    public JSONObject toJsonObject(String response) throws ParseException {
        JSONObject responsJsonObject = (JSONObject) new JSONParser().parse(response);

    return responsJsonObject;
    }
}
