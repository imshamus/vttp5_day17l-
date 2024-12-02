package sg.edu.nus.iss.day17l.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.day17l.constant.Url;
import sg.edu.nus.iss.day17l.model.Carpark;

@Service
public class CarparkService {

    private static final Logger logger = LoggerFactory.getLogger(CarparkService.class);

    RestTemplate restTemplate = new RestTemplate();

    public List<Carpark> getCarparks()
    {
        logger.info("Getting info from filtered data..");
        String carparkData = restTemplate.getForObject(Url.carparkUrl2, String.class);

        // System.out.println(carparkData);

        if (carparkData == null) {
            logger.error("failed to fetch carpark data, response was null");
            return new ArrayList<>();   
           }

        logger.debug("Raw carpark data: {}", carparkData);

        // Parse the string response as a JSON Array
        JsonReader jReader = Json.createReader(new StringReader(carparkData));
        JsonArray jArray = jReader.readArray();

        // System.out.println(jArray);

        List<Carpark> carparks = new ArrayList<>();

        for (int i = 0; i < jArray.size(); i++)
        {
            JsonObject jObject = jArray.getJsonObject(i); // get single JSON obj in the JSON array

            Carpark c = new Carpark();

            c.setId(jObject.getInt("id"));
            c.setAddress(jObject.getString("address"));
            c.setWdRate1(jObject.getString("wdRate1"));
            c.setWdRate2(jObject.getString("wdRate2"));
            c.setSatRate(jObject.getString("satRate"));
            c.setSundayPhRate(jObject.getString("sundayPhRate"));

            carparks.add(c);
        }

        logger.info("Succesfully parsed {} carparks.", carparks.size());
        logger.debug("First 5 carparks: {}.", carparks.stream().limit(5).toList());

        return carparks;
    }
    
}
