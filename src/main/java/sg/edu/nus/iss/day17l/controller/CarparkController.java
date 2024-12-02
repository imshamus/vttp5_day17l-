package sg.edu.nus.iss.day17l.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

        return "carparklist"; 
    }

    // @GetMapping("/create")
    // public String showCreatePage(Model model) {
    //     // Create an empty Carpark object
    //     // This will serve as the "backing object" for the form fields in the HTML view
    //     Carpark carpark = new Carpark();

    //     // Adds this empty carpark object to the model
    //     // This makes the "carpark" object available to the Thymeleaf template (carparkform.html)
    //     // Thymeleaf will bind this object to the form fields, allowing user input to be mapped to the Carpark object's properties 
    //     model.addAttribute("carpark", carpark);

    //     // model.addAttribute("carpark", new Carpark());

    //     // Return the name of the Thymeleaf template to render (carparkform.html)
    //     return "carparkform";
    // }
    

    // @PostMapping("/create")
    // public String createCarparkRecord(@ModelAttribute("carpark") Carpark carpark, BindingResult bindingResult, Model model) {
    //     carparkService.addCarpark(Util)
        
    //     return entity;
    // }
    
    
    
    
}
