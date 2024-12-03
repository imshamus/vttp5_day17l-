package sg.edu.nus.iss.day17l.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day17l.constant.Constant;

@Repository
public class MapRepo {

    @Autowired
    @Qualifier(Constant.template01)
    RedisTemplate<String, String> template;

    // Add a key-value pair to Redis hash (HSET)
    // Redis = hset c0 email fred@gmail.com & hset c0 credit 100
    // Code = template.opsForHash().put(“c0”, “email”, “fred@gmail.com”); & template.opsForHash().put(“c0”, “credit”, 100);
    public void put(String mapKey, String fieldKey, String value)
    {
        template.opsForHash().put(mapKey, fieldKey, value);
    }
    
    // Get a value from a Redis hash (HGET)
    // hget c0 email & hget c0 credit
    public Object get(String mapKey, String fieldKey) {
        return template.opsForHash().get(mapKey, fieldKey);
    }

     // Delete a field from a Redis hash (HDEL)
     // hdel c0 email
     public void delete(String mapKey, String fieldKey) {
        template.opsForHash().delete(mapKey, fieldKey);
    }
    
    // Check if a field exists in a Redis hash (HEXISTS)
    // hexists c0 email
    public Boolean hasField(String mapKey, String fieldKey) {
        return template.opsForHash().hasKey(mapKey, fieldKey);
    }

    // Get all fields in a Redis hash (HKEYS)
    // hkeys c01
    // Set<String> keys = template.opsForHash().keys(“c01”);
    public Set<Object> getFields(String mapKey) {
        return template.opsForHash().keys(mapKey);
    }

    // Get all values in a Redis hash (HVALS)
    // hvals c01
    // List<Object> values = template.opsForHash().values(“c01”)l
    public List<Object> getValues(String mapKey) {
        return template.opsForHash().values(mapKey);
    }

    // Get the size of a Redis hash (HLEN)
    // hlen c01
    // long mapSize = template.opsForHash().size(“c01”);
    public Long size(String mapKey) {
        return template.opsForHash().size(mapKey);
    }

    // Increment a numeric value in a Redis hash (HINCRBY)
    // hincrby c01 count 1
    public void increment(String mapKey, String fieldKey, long value) {
        template.opsForHash().increment(mapKey, fieldKey, value);
    }

    // Get the entire hash as a Map - Returns all key-value pairs in the hash.
    // HGETALL mapKey
    public Map<Object, Object> getAll(String mapKey) {
        return template.opsForHash().entries(mapKey);
    }

    
}
