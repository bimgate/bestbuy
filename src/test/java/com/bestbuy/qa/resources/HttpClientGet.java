package com.bestbuy.qa.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by bratislav.miletic on 07/10/2017.
 */
public class HttpClientGet {

    public int getResponseCode(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        int statusCode = http.getResponseCode();
        return statusCode;
    }

    public String httpClientGet(String apiUrl) {
        String result = null;
        try {
            URL url = new URL(apiUrl);
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            int numCharsRead;
            char[] charArray = new char[1024];
            StringBuffer sb = new StringBuffer();
            while ((numCharsRead = isr.read(charArray)) > 0) {
                sb.append(charArray, 0, numCharsRead);
            }
            result = sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
