package sg.edu.nus.iss.day17l.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.day17l.constant.Url;
import sg.edu.nus.iss.day17l.model.Carpark;
import sg.edu.nus.iss.day17l.repository.ListRepo;

@Service
public class CarparkService {

    RestTemplate restTemplate = new RestTemplate();

    public List<Carpark> getCarparks()
    {
        String carparkData = restTemplate.getForObject(Url.carparkUrl2, String.class);

        // System.out.println(carparkData);

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

        return carparks;
    }
    
}
