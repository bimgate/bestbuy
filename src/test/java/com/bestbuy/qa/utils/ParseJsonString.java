package com.bestbuy.qa.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by bratislav.miletic on 08/10/2017.
 */
public class ParseJsonString {

    public Map<String,String> parse(JSONObject json, String data, String key, String value) throws ParseException {

        Map hm = new HashMap();
        JSONParser parser = new JSONParser();
        JSONArray dataProductElements = (JSONArray) json.get(data);

        for (Object dataElements : dataProductElements) {
            String elementString = dataElements.toString();
            JSONObject elementsJsonObject = (JSONObject) parser.parse(elementString);

            String keyId = elementsJsonObject.get(key).toString();
            String valuePrice = elementsJsonObject.get(value).toString();
            hm.put(keyId, valuePrice);
        }
        return hm;
    }

    public ArrayList<Double> parseAndCollect(JSONObject json, String data, String key) throws ParseException {
        ArrayList<Double> arl = new ArrayList();
        JSONParser parser = new JSONParser();
        JSONArray dataProductElements = (JSONArray) json.get(data);

        for (Object dataElements : dataProductElements) {
            String elementString = dataElements.toString();

            JSONObject elementsJsonObject = (JSONObject) parser.parse(elementString);
            Object valuePrice = elementsJsonObject.get(key);
            double i = (Double) valuePrice;
            arl.add(i);
        }
        return arl;
    }

    public List toArr(JSONObject json, String data) {
        ArrayList arr = new ArrayList();
        JSONArray dataArr = (JSONArray) json.get(data);
        if (data != null) {
            for (int i=0;i<dataArr.size();i++){
                arr.add(dataArr.get(i));
            }
        }
        return arr;
    }

    public static String REGEX_FIND_WORD="(?i).*?\\b%s\\b.*?";

    public static boolean containsWord(String text, String word) {
        String regex=String.format(REGEX_FIND_WORD, Pattern.quote(word));
        return text.matches(regex);
    }
}
