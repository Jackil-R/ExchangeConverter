package com.exchange.converter;

import com.exchange.model.Country;
import com.exchange.model.Currency;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FreeCurrencyConverter implements Converter {

    private final String API_KEY="1bdb03fd93ab4b696871";
    private HttpClientBuilder builder = HttpClientBuilder.create();
    private ObjectMapper mapper = new ObjectMapper();
    private List<Currency> currencyArrayList;
    private List<Country> countryArrayList;

    public FreeCurrencyConverter(){
        countryArrayList = getListOfCountries();
        currencyArrayList = getListOfCurrencies();
    }

    @Override
    public float convert(String currencyFrom, String currencyTo){
        try (CloseableHttpClient httpclient = builder.build())
        {
            HttpGet httpGet = new HttpGet("https://free.currconv.com/api/v7/convert?q="+currencyFrom+"_"+currencyTo+"&compact=ultra&apiKey="+API_KEY);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(httpGet, responseHandler);
            JsonNode jsonNode = mapper.readTree(responseBody);
            return Float.parseFloat(String.valueOf(jsonNode.get(currencyFrom+"_"+currencyTo).toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private List<Country> getListOfCountries(){
        ArrayList<Country> countryArrayList = new ArrayList<>();
        try (CloseableHttpClient httpclient = builder.build())
        {
            HttpGet httpGet = new HttpGet("https://free.currconv.com/api/v7/countries?apiKey="+API_KEY);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(httpGet, responseHandler);
            JsonNode jsonNode = mapper.readTree(responseBody);
            jsonNode.get("results").forEach(j -> {
                Country country = null;
                try {
                    country = mapper.readValue(j.toString(), Country.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                countryArrayList.add(country);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countryArrayList;
    }

    private List<Currency> getListOfCurrencies(){
        ArrayList<Currency> currencyArrayList = new ArrayList<>();
        try (CloseableHttpClient httpclient = builder.build())
        {
            HttpGet httpGet = new HttpGet("https://free.currconv.com/api/v7/currencies?apiKey="+API_KEY);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(httpGet, responseHandler);

            JsonNode jsonNode = mapper.readTree(responseBody);
            jsonNode.get("results").forEach(j -> {
                Currency currency = null;
                try {
                    currency = mapper.readValue(j.toString(), Currency.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                currencyArrayList.add(currency);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currencyArrayList;
    }

    public List<Currency> getCurrencyArrayList() {
        return currencyArrayList;
    }

    public List<Country> getCountryArrayList() {
        return countryArrayList;
    }
}
