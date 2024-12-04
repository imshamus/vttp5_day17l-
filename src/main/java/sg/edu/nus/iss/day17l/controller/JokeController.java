package sg.edu.nus.iss.day17l.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import sg.edu.nus.iss.day17l.model.Joke;
import sg.edu.nus.iss.day17l.service.JokeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;



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
    public String createJoke(@Valid @ModelAttribute("joke") Joke joke, BindingResult bindingResult, Model model) // @ModelAtttribute binds data from http request (like a form submission) to a java object. Typically used in methods that handle form submission
    {
        // Check for conversion errors early
        // Handle conversion errors by checking the BindingResult for such errors before proceeding with validation logic
        if (bindingResult.hasFieldErrors("id")) 
        {
            FieldError err = new FieldError("joke", "id", "ID must be a numeric value.");
            bindingResult.addError(err);

            return "jokecreate";
        }

        // Validation logic
        if (bindingResult.hasErrors())
        {
            return "jokecreate";
        }
        
        // Custom validation + Debug
        System.out.println("Is ID Unique? " + jokeService.isIdUnique(joke.getId()));
        
        if (!jokeService.isIdUnique(joke.getId())) {
            FieldError err = new FieldError("joke", "id", "ID already exists.");
            bindingResult.addError(err);
            return "jokecreate";
        }        

        jokeService.addJoke(joke);
        return "redirect:/jokes";
    }
    
}
