package sg.edu.nus.iss.day17l.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import sg.edu.nus.iss.day17l.model.Joke;
import sg.edu.nus.iss.day17l.service.JokeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/jokes")
public class JokeController {

    @Autowired
    private JokeService jokeService;

    @GetMapping("")
    public String showJokes(Model model) 
    {
        List<Joke> jokes = jokeService.getAllJokes();
        model.addAttribute("jokes", jokes);
        
        return "jokelist";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("joke", new Joke());
        return "jokecreate";
    }

    @PostMapping("/create")
    public String createJoke(@ModelAttribute Joke joke) {
        jokeService.addJoke(joke);
        return "redirect:/jokes";
    }
    
    
    
}
