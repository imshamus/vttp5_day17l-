package sg.edu.nus.iss.day17l.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day17l.constant.Constant;
import sg.edu.nus.iss.day17l.model.Joke;
import sg.edu.nus.iss.day17l.repository.JokeRepo;

@Service
public class JokeService {
    
    @Autowired 
    private JokeRepo jokeRepo;

    public void addJoke(Joke joke)
    {
        // String jokeString = joke.getId() + "," + joke.getType() + "," + joke.getSetup() + "," + joke.getPunchline();

        String jokeString = joke.toString();
        jokeRepo.saveJoke(Constant.jokeKey, jokeString);
    }

    public List<Joke> getAllJokes()
    {
        List<String> jokesInRedis = jokeRepo.findAllJokes(); // load all jokes from redis, each joke as a string
        
        List<Joke> jokes = new ArrayList<>();

        for(String jokeString : jokesInRedis)
        {
            Joke joke = new Joke();

            String[] parts = jokeString.split(",");
            
            // or can create joke here and parse in the variables straight Joke joke = new joke(id, type, setup, punchline);

            joke.setId(Integer.parseInt(parts[0]));
            joke.setType(parts[1]);
            joke.setSetup(parts[2]);
            joke.setPunchline(parts[3]);

            jokes.add(joke);
        }

        return jokes;

    }

}
