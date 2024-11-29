package sg.edu.nus.iss.day17l.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.day17l.model.Carpark;
import sg.edu.nus.iss.day17l.restservice.CarparkRestService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/carparks")
public class CarparkRestController {

    @Autowired
    CarparkRestService carparkRestService;

    @GetMapping()
    public ResponseEntity<List<Carpark>> getCarparks() {

        List<Carpark> carparks = new ArrayList<>();

        carparks = carparkRestService.getApiCarparks();

        return ResponseEntity.ok().body(carparks);
        
    }
    
    
}
