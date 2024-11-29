package sg.edu.nus.iss.day17l.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.day17l.model.Carpark;
import sg.edu.nus.iss.day17l.service.CarparkService;


@Controller
@RequestMapping("/carparks")
public class CarparkController {

    @Autowired 
    CarparkService carparkService;

    @GetMapping("")
    public  String showCarparks(Model model) 
    {
        List<Carpark> carparks = carparkService.getCarparks();
        model.addAttribute("carparks", carparks);
        
        return "carparklist"; // returns the carparklist.html
    }
    
    
    
}
