package sg.edu.nus.iss.day17l.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

// import sg.edu.nus.iss.vttp5a_ssf_day16l.model.Person;
import sg.edu.nus.iss.day17l.constant.Constant;

@Repository
public class ListRepo {

    @Autowired
    @Qualifier(Constant.template01)
    RedisTemplate<String, String> template;

    // Count para
    // Positive: Removes the first count occurrences of the value.
    // Negative: Removes the last count occurrences.
    // Zero: Removes all occurrences.


    // Adding to List (Single)
    // D15 - slide 30, slide 34
    public void leftPush(String key, String value) {
        template.opsForList().leftPush(key, value);
    }
    
    public void rightPush(String key, String value) {
        template.opsForList().rightPush(key, value);
    }

    // Adding to List (Multiple)
    public void rightPushAll(String key, String... values) {
        template.opsForList().rightPushAll(key, values);
    }
    


    // Removes from List (RPOP)
    // D15 - slide 30
    public void leftPop(String key) {
        template.opsForList().leftPop(key, 1);
    }

    public void rightPop(String key) {
        template.opsForList().rightPop(key, 1);
    }

    // Retrieves and removes the last element of the list.
    // public String rightPop(String key) {
    //     return template.opsForList().rightPop(key);
    // }
    
    // Remove an Element from a List - removes the first occurance of a specific value froma  list (LREM)
    public void remove(String key, long count, String value) {
        template.opsForList().remove(key, count, value);
    }
    


    // Trim a List - Trims the list to only include elements within a specified range. (LTRIM)
    // Keeps only the elements between start and end.
    public void trim(String key, long start, long end) {
        template.opsForList().trim(key, start, end);
    }
    


    // D15 - slide 32
    public String get(String key, Integer index) {
        return template.opsForList().index(key, index).toString();
    }

    // D15 - slide 33
    public Long size(String key) {
        return template.opsForList().size(key);
    }

    // Get the whole list
    public List<String> getList(String key) {
        List<String> list = template.opsForList().range(key, 0, -1);

        return list;
    }

}
