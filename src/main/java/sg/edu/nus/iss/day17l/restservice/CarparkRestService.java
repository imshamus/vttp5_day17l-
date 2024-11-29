package sg.edu.nus.iss.day17l.restservice;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.JsonObject;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;

import sg.edu.nus.iss.day17l.constant.Url;
import sg.edu.nus.iss.day17l.model.Carpark;
import sg.edu.nus.iss.day17l.repository.ListRepo;

@Service
public class CarparkRestService {

    @Autowired
    ListRepo carparkRepo;

    RestTemplate restTemplate = new RestTemplate();
    
    public List<Carpark> getApiCarparks()
    {
        String carparkData = restTemplate.getForObject(Url.carparkUrl, String.class);

        // System.out.println(carparkData); // make the request mapped by controller in browser to see in terminal, this is a string object



        JsonReader jReader = Json.createReader(new StringReader(carparkData));

        JsonObject jBodyObject = jReader.readObject(); // readobj reads the string and converts into json obj

        // System.out.println(jBodyObject); // make the request, this is a json object



        JsonObject jDataObject = jBodyObject.getJsonObject("result");
        JsonArray jDataArray= jDataObject.getJsonArray("records");
        
        // System.out.println(jDataArray.get(0).getClass()); // make the request, this shows index 0, this gives a jsonvalue (hover get)

        // System.out.println(jDataArray.getJsonObject(0).getClass()); // this gives jsonObj



        List<Carpark> carparks = new ArrayList<>();

        for (int i =0; i < jDataArray.size(); i++)
        {
            JsonObject jObject = jDataArray.getJsonObject(i); 

            Carpark c = new Carpark();
            c.setId(jObject.getInt("_id"));
            c.setAddress(jObject.getString("carpark"));
            c.setWdRate1(jObject.getString("weekdays_rate_1"));
            c.setWdRate2(jObject.getString("weekdays_rate_2"));
            c.setSatRate(jObject.getString("saturday_rate"));
            c.setSundayPhRate(jObject.getString("sunday_publicholiday_rate"));

            // Darryl's way
            /* Carpark c = new Carpark(jsonArray.get(i).asJsonObject().getInt("_id"), jsonArray.get(i).asJsonObject().getString("carpark"), jsonArray.get(i).asJsonObject().getString("category"), jsonArray.get(i).asJsonObject().getString("weekdays_rate_1"), jsonArray.get(i).asJsonObject().getString("weekdays_rate_2"), jsonArray.get(i).asJsonObject().getString("saturday_rate"), jsonArray.get(i).asJsonObject().getString("sunday_publicholiday_rate")); */

            carparks.add(c);
        }

        return carparks;

    }
    
}
