package com.bestbuy.qa.helpers;

import java.util.*;

import static com.bestbuy.qa.utils.ParseJsonString.containsWord;

/**
 * Created by bratislav.miletic on 07/10/2017.
 */
public class Tests {



    public Boolean additionalElement(ArrayList arr, String name, String price){
        boolean addElement = false;

        for (int i = 0; i< arr.size(); i++ ) {
            HashMap<String, String> hm = (HashMap) arr.get(i);
            for (String elementHm : hm.keySet()) {
                if (!name.equals(elementHm)){
                    if (!price.equals(elementHm)){
                        addElement = true;
                        break;
                    }

                }
            }
        }
        return addElement;
    }

    public Boolean oneType(ArrayList arr, String type, String name){
        boolean typeOne = false;
        ArrayList arrTypes = new ArrayList();

        for (int i = 0; i< arr.size(); i++ ) {
            HashMap<String, String> hm = (HashMap) arr.get(i);
            for (Map.Entry<String, String> elementHm : hm.entrySet()) {
                if ((elementHm.getKey().equals(type)) && (elementHm.getValue().equals(name))){
                    arrTypes.add(elementHm.getValue());
                    }
            }
        }
            if (arrTypes.size() == arr.size()) {
                typeOne = true;
            }
        return typeOne;
    }

    public Boolean priceLessThanOne(ArrayList arr, String price, String priceValueOne){
        Double priceValueOneDoub = new Double(priceValueOne);
        boolean lessThanOne = false;
        ArrayList<Double> arrPricesValues = new ArrayList();

        for (int i = 0; i< arr.size(); i++ ) {
            HashMap<String, Double> hm = (HashMap) arr.get(i);
            for (Map.Entry<String, Double> elementHm : hm.entrySet()) {
                if ((elementHm.getKey().equals(price))) {
                  Double priceValue = elementHm.getValue();
                  arrPricesValues.add(priceValue);                  
                }
            }
        }
        if (Collections.max(arrPricesValues) <= priceValueOneDoub){
             lessThanOne = true;
        }
        return lessThanOne;
    }

    public Boolean textInNameAndLessThanPrice(ArrayList arr, String text, String name, String priceText, String priceValueLessThan){
        Double priceValueOneDoub = new Double(priceValueLessThan);
        boolean textInNameAndLessThanPrice = false;
        ArrayList arrNames = new ArrayList();
        ArrayList arrPrices = new ArrayList();

        for (int i = 0; i< arr.size(); i++ ) {
            HashMap<String, String> hm = (HashMap) arr.get(i);
            for (Map.Entry<String, String> elementHm : hm.entrySet()) {
                if ((elementHm.getKey().equals(name))) {
                    String nameValue = elementHm.getValue();
                        if (containsWord(nameValue, text) == true){
                            arrNames.add(nameValue);
                        }
                    }
                if ((elementHm.getKey().equals(priceText))) {
                    Object value = elementHm.getValue();
                   double priceValue = new Double(value.toString());
                        if (priceValue < priceValueOneDoub){
                            arrPrices.add(priceValue);
                        }
                }
                }
            }
            if (arrNames.size() == arrPrices.size() && arrNames.size() == arr.size()){
                textInNameAndLessThanPrice = true;
            }

        return textInNameAndLessThanPrice;
    }

    public Boolean twoPrices(ArrayList arr, String priceText, String priceValueFirst, String priceValueSecond) {
        Double priceValueFirstDoub = new Double(priceValueFirst);
        Double priceValueSecondDoub = new Double(priceValueSecond);
        ArrayList arrPriceVal = new ArrayList();
        boolean twoPrices = false;

        for (int i = 0; i < arr.size(); i++) {
            HashMap<String, Double> hm = (HashMap) arr.get(i);
            for (Map.Entry<String, Double> elementHm : hm.entrySet()) {
                if ((elementHm.getKey().equals(priceText))) {
                    Double priceValue = elementHm.getValue();
                    if (priceValue.equals(priceValueFirstDoub) || priceValue.equals(priceValueSecondDoub)) {

                        arrPriceVal.add(priceValue);
                    }
                }
            }
        }
        if (arrPriceVal.size() == arr.size()){
            twoPrices = true;
        }
        return twoPrices;
    }

    public Boolean shippingPrice(ArrayList arr, String shipping, String shippingPrice){
        Double shippingPriceDoub = new Double(shippingPrice);
        ArrayList arrShippingPricesVal = new ArrayList();
        boolean shippingPrices = false;

        for (int i = 0; i < arr.size(); i++) {
            HashMap<String, Double> hm = (HashMap) arr.get(i);
            for (Map.Entry<String, Double> elementHm : hm.entrySet()) {
                if ((elementHm.getKey().equals(shipping))) {
                    Double shoppingPriceValue = elementHm.getValue();
                    if (shoppingPriceValue > shippingPriceDoub) {

                        arrShippingPricesVal.add(shoppingPriceValue);
                    }
                }
            }
        }
        if (arrShippingPricesVal.size() == arr.size()){
            shippingPrices = true;
        }
        return shippingPrices;
    }

    public Boolean noTwoTypes(ArrayList arr, String type,String hardGood,String software){
        boolean noTwoTypes = true;

        for (int i = 0; i < arr.size(); i++) {
            HashMap<String, String> hm = (HashMap) arr.get(i);
            for (Map.Entry<String, String> elementHm : hm.entrySet()) {
                if ((elementHm.getKey().equals(type))) {
                    String typeValue = elementHm.getValue();
                    if (typeValue.equals(hardGood) || typeValue.equals(software)) {
                        noTwoTypes = false;
                    }
                }
            }
        }
        return noTwoTypes;
    }

    public Boolean oneCategory(ArrayList arr, String category, String name, String type){
        boolean oneCategory = false;
        ArrayList categoryNameArr = new ArrayList();

        for (int i = 0; i < arr.size(); i++) {
            HashMap<String, ArrayList> hm = (HashMap) arr.get(i);
            for (Map.Entry<String, ArrayList> elementHm : hm.entrySet()) {
                if ((elementHm.getKey().equals(category))) {
                    ArrayList arrCategoryValues = elementHm.getValue();
                    for (int j = 0; j< arrCategoryValues.size(); j++ ) {
                        HashMap<String, String> hmInArr = (HashMap) arrCategoryValues.get(j);
                        String categoryName = hmInArr.get(name);
                            if (categoryName.equals(type)){
                                categoryNameArr.add(categoryName);
                            }
                    }
                }
            }
            }
        if (categoryNameArr.size() == arr.size()){
            oneCategory = true;
        }
            return oneCategory;
    }

}
