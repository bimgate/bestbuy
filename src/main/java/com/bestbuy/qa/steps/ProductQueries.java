package com.bestbuy.qa.steps;

import com.bestbuy.qa.helpers.Convert;
import com.bestbuy.qa.helpers.Tests;
import com.bestbuy.qa.helpers.Sorted;
import com.bestbuy.qa.resources.HttpClientGet;
import com.bestbuy.qa.utils.ParseJsonString;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.ArrayList;

/**
 * Created by bratislav.miletic on 07/10/2017.
 */
public class ProductQueries {
    HttpClientGet client = new HttpClientGet();
    Convert convert = new Convert();
    JSONObject apiResponse;
    Tests tests = new Tests();
    ParseJsonString parse = new ParseJsonString();
    Sorted sorted = new Sorted();


    @Given("^server API is up and running on \"([^\"]*)\" with response code (\\d+)$")
    public void serverAPIIsUpAndRunningOnWithResponseCode(String apiUrl, int responseCode) throws Throwable {
        Assert.assertEquals(client.getResponseCode(apiUrl), responseCode, "different response code");
    }

    @When("^client send get request \"([^\"]*)\"$")
    public void clientSendGetRequest(String apiUrl) throws Throwable {
        apiResponse = convert.toJsonObject(client.httpClientGet(apiUrl));
        Assert.assertNotNull(apiResponse, "server response is null");
    }

    @Then("^server API should response with JSON payload:$")
    public void serverAPIShouldResponseWithJSONPayload(String expectedPayload) throws Throwable {
        Assert.assertEquals(convert.toJsonObject(expectedPayload), apiResponse, "consumed payload is different than expected");
    }

    @Then("^server API should response with JSON which contains list of all products$")
    public void serverAPIShouldResponseWithJSONWhichContainsListOfAllProducts() throws Throwable {
        Assert.assertNotNull(apiResponse, "server response is null");
    }

    @And("^every product should has id \"([^\"]*)\", model \"([^\"]*)\":$")
    public void everyProductShouldHasIdModel(String id, String model) throws Throwable {
      Assert.assertEquals(model, parse.parse(apiResponse,"data","id","model").get(id));
    }

    @Then("^server API should response with (\\d+) product JSON$")
    public void serverAPIShouldResponseWithProductJSON(int one) throws Throwable {
        JSONArray data = (JSONArray) apiResponse.get("data");
        Assert.assertEquals(data.size(),one, "more than one or non product is in response");
    }

    @And("^with \"([^\"]*)\" field value \"([^\"]*)\"$")
    public void withFieldValue(String limit, String one) throws Throwable {
        String limitValue = apiResponse.get(limit).toString();
        Assert.assertEquals(limitValue, one);
    }

    @Then("^server API response JSON should has \"([^\"]*)\" value \"([^\"]*)\"$")
    public void serverAPIResponseJSONShouldHasValue(String skip, String value) throws Throwable {
        String skipValue = apiResponse.get(skip).toString();
        Assert.assertEquals(skipValue, value);
    }

    @Then("^server API response JSON should has products \"([^\"]*)\" ordered from highest to lowest$")
    public void serverAPIResponseJSONShouldHasProductsOrderedFromHighestToLowest(String price) throws Throwable {
        Assert.assertEquals(sorted.isSortedDescending(parse.parseAndCollect(apiResponse, "data", price)),true);
    }

    @Then("^server API response JSON should has products \"([^\"]*)\" ordered from lowest to highest$")
    public void serverAPIResponseJSONShouldHasProductsOrderedFromLowestToHighest(String price) throws Throwable {
        Assert.assertEquals(sorted.isSortedAscending(parse.parseAndCollect(apiResponse, "data", price)),true);
    }

    @Then("^server API response JSON should contain only \"([^\"]*)\" and \"([^\"]*)\" for all products$")
    public void serverAPIResponseJSONShouldContainOnlyAndForAllProducts(String name, String price) throws Throwable {
        ArrayList onlyNamesAndPrices = (ArrayList) parse.toArr(apiResponse,"data");
        boolean result = tests.additionalElement(onlyNamesAndPrices, name, price);
        Assert.assertEquals(result,false);
    }

    @Then("^server API response JSON should contain only products which \"([^\"]*)\" is \"([^\"]*)\"$")
    public void serverAPIResponseJSONShouldContainOnlyProductsWhichIs(String type, String hardGood) throws Throwable {
        ArrayList elementsList = (ArrayList) parse.toArr(apiResponse, "data");
        boolean result = tests.oneType(elementsList, type, hardGood);
        Assert.assertEquals(result,true);
    }

    @Then("^server API response JSON should contain only products with \"([^\"]*)\" lest than or equal \"([^\"]*)\"$")
    public void serverAPIResponseJSONShouldContainOnlyProductsWithLestThanOrEqual(String price, String priceValue) throws Throwable {
        ArrayList elementsList = (ArrayList) parse.toArr(apiResponse, "data");
        boolean result = tests.priceLessThanOne(elementsList,price, priceValue);
        Assert.assertEquals(result,true);
    }

    @Then("^server API response JSON should contain only products with text \"([^\"]*)\" in \"([^\"]*)\" and \"([^\"]*)\" less than \"([^\"]*)\"$")
    public void serverAPIResponseJSONShouldContainOnlyProductsWithTextInAndLessThan(String text, String name, String priceText, String price) throws Throwable {
        ArrayList elementsList = (ArrayList) parse.toArr(apiResponse, "data");
        boolean result = tests.textInNameAndLessThanPrice(elementsList,text,name, priceText, price);
        Assert.assertEquals(result,true);
    }

    @Then("^server API response JSON should contain only products with \"([^\"]*)\" \"([^\"]*)\" or \"([^\"]*)\"$")
    public void serverAPIResponseJSONShouldContainOnlyProductsWithOr(String priceText, String priceValueFirst, String priceValueSecond) throws Throwable {
        ArrayList elementsList = (ArrayList) parse.toArr(apiResponse, "data");
        boolean result = tests.twoPrices(elementsList,priceText,priceValueFirst, priceValueSecond);
        Assert.assertEquals(result,true);
    }

    @Then("^server API response JSON should contain only products with \"([^\"]*)\" more than \"([^\"]*)\"$")
    public void serverAPIResponseJSONShouldContainOnlyProductsWithMoreThan(String shipping, String shippingPrice) throws Throwable {
        ArrayList elementsList = (ArrayList) parse.toArr(apiResponse, "data");
        boolean result = tests.shippingPrice(elementsList,shipping,shippingPrice);
        Assert.assertEquals(result,true);
    }

    @Then("^server API response JSON should contain only products which \"([^\"]*)\" is not \"([^\"]*)\" or \"([^\"]*)\"$")
    public void serverAPIResponseJSONShouldContainOnlyProductsWhichIsNotOr(String type, String hardGood, String software) throws Throwable {
        ArrayList elementsList = (ArrayList) parse.toArr(apiResponse, "data");
        boolean result = tests.noTwoTypes(elementsList,type,hardGood,software);
        Assert.assertEquals(result,true);

    }

    @Then("^server API response JSON should contain only products which are in \"([^\"]*)\", \"([^\"]*)\" \"([^\"]*)\"$")
    public void serverAPIResponseJSONShouldContainOnlyProductsWhichAreIn(String category, String name, String type) throws Throwable {
        ArrayList elementsList = (ArrayList) parse.toArr(apiResponse, "data");
        boolean result =  tests.oneCategory(elementsList, category, name, type);
        Assert.assertEquals(result,true);
    }


}