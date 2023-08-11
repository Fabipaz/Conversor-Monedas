package com.conversor.col;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Api {

    private static final String API_KEY = "bbbcf7cb26af0b58f205c386ed94f2d5"; 
    private static final String BASE_URL = "http://api.exchangeratesapi.io/v1";

    public float getExchangeRate(String currencyCode) {
        float exchangeRate = 0; 
        

        try {
            String endpoint = "/latest";
            String accessKey = API_KEY;
            String exchangeRatesUrl = BASE_URL + endpoint + "?access_key=" + accessKey;
            String exchangeRatesJson = fetchDataFromURL(exchangeRatesUrl);
            JSONObject jsonObject = new JSONObject(exchangeRatesJson);
        

            if (jsonObject.getBoolean("success")) {
                JSONObject ratesObject = jsonObject.getJSONObject("rates");
                exchangeRate = ratesObject.getFloat(currencyCode);
               
               
                System.out.println(ratesObject);
            } else {
                System.out.println("No se pudo obtener la tasa de cambio para " + currencyCode);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los datos de conversión: " + e.getMessage());
        }

        return exchangeRate;
    }

    private String fetchDataFromURL(String urlStr) {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
