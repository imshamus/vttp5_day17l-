package sg.edu.nus.iss.day17l.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day17l.constant.Constant;

@Repository
public class JokeRepo 
{
    // Injects the RedisTemplate bean defined in RedisConfig
    @Autowired
    @Qualifier(Constant.template01) // Specifies the exact RedisTemplate to use
    private RedisTemplate<String, String> template;

    // Retrieve all jokes from Redis
    public List<String> findAllJokes()
    {
        return template.opsForList().range(Constant.jokeKey, 0, -1);
    }

    // Saves a joke to redis
    public void saveJoke(String key, String joke)
    {
        template.opsForList().rightPush(key, joke);
    }

    // Delete a joke from redis


    
}
