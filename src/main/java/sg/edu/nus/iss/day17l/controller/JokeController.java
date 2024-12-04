package sg.edu.nus.iss.day17l.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/jokes")
public class JokeController {

    private static final Logger logger = LoggerFactory.getLogger(JokeController.class);

    @Autowired
    private JokeService jokeService;

    @GetMapping("")
    public String showJokes(Model model) 
    {
        // Fetch all jokes from Service
        List<Joke> jokes = jokeService.getAllJokes();
        logger.info("Fetch {} jokes from the database.", jokes.size());

        // Add jokes to the model for display on the HTML page
        model.addAttribute("jokes", jokes);
        
        return "jokelist";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        logger.debug("Displaying create joke form.");

        // Initialise a new Joke object for binding to the form
        model.addAttribute("joke", new Joke());
        return "jokecreate";
    }

    @PostMapping("/create")
    public String createJoke(@Valid @ModelAttribute("joke") Joke joke, BindingResult bindingResult, Model model) // @ModelAtttribute binds data from http request (like a form submission) to a java object. Typically used in methods that handle form submission
    {
        logger.debug("Processing joke creation: {}", joke);
        
        // Check for conversion errors early
        // Handle conversion errors by checking the BindingResult for such errors before proceeding with validation logic
        if (bindingResult.hasFieldErrors("id")) 
        {
            FieldError err = new FieldError("joke", "id", "ID must be a numeric value.");
            bindingResult.addError(err);

            return "jokecreate";
        }

        // Validation logic (Check for validation erros)
        if (bindingResult.hasErrors())
        {
            logger.warn("Validation errors found in the submitted joke: {}", bindingResult.getAllErrors());
            return "jokecreate";
        }
        
        // Custom validation + Debug
        System.out.println("Is ID Unique? " + jokeService.isIdUnique(joke.getId()));
        
        if (!jokeService.isIdUnique(joke.getId())) 
        {
            FieldError err = new FieldError("joke", "id", "ID already exists.");
            bindingResult.addError(err);
            logger.warn("Duplicate joke ID detected: {}", joke.getId());
            
            return "jokecreate";
        }        

        jokeService.addJoke(joke);
        logger.info("Successfully added joke with ID: {}", joke.getId());
        
        return "redirect:/jokes";
    }

    // Test logic with postman
    // url: http://localhost:8888/jokes/delete
    // Method: POST
    // Body: id=1 (x-www-form-urlencoded)
    @PostMapping("/delete")
    // public String deleteJoke(@RequestParam(value = "id") Integer id, Model model)
    public String deleteJoke(@RequestParam(value = "id") Integer id, RedirectAttributes redirectAttributes)  
    {
        logger.debug("Processing joke deletion for ID: {}", id);
        
        // Check if ID is missing (null)
        if (id == null)
        {
            logger.warn("No ID provided for deletion.");
            // model.addAttribute("error", "No ID provided. Please select a joke to delete.");

            // use redirectAttributes instead as model attribute is not persisting across the redirect. This happens because the redirect:/jokes clears the current model, which includes attributes like success.

            // using RedirectAttributes allows attributes to persist across a redirect.
            redirectAttributes.addFlashAttribute("error", "No ID provided. Please select a joke to delete.");

            return "redirect/jokes";
        }


        // Fetch all jokes
        List<Joke> jokes = jokeService.getAllJokes();
        

        // Find the joke to delete by matching its ID

        // Joke jokeToDelete = jokes.stream().filter(j -> j.getId().equals(id)).findFirst().get(); //.get(): Extracts the value. However if empty, throws NoSuchElementException.
        // Use case: you confident got value present after filter.

        Joke jokeToDelete = jokes.stream().filter(j -> j.getId().equals(id)).findFirst().orElse(null); // orElse(null): if value present, it is retrieved. if value x present, it defaults to null. 
        // Use Case: When possibility that no match exists. You want to provide default value when no match to handle such cases gracefully.
        

        // Handle case when joke is not found
        if (jokeToDelete == null)
        {
            logger.warn("No joke found with the provided ID: {}");
            // model.addAttribute("error", "No joke found with the provided ID.");
            redirectAttributes.addFlashAttribute("error", "No joke found with the provided ID.");

            // for "error"'s value' to show up, must add a section in jokelist.html to display
            // e.g. <div class="alert alert-danger" th:if="${error}"><p th:text="${error}"></p></div>
            // <div> doesnt show if "error" attribute DNE

            return "redirect/jokes";
        }
        

        // Delete the joke
        jokeService.deleteJoke(jokeToDelete);
        logger.info("Successfully deleted joke with ID: {}", id);
        // model.addAttribute("success", "Joke with ID " + id + " was successfully deleted.");
        redirectAttributes.addFlashAttribute("success", "Joke with ID " + id + " was successfully deleted.");


        //redirect back to joke list
        return "redirect:/jokes";
    }
    
    
}
