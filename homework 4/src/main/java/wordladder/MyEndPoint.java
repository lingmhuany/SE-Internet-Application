package wordladder;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.HashMap;
import java.util.Map;

@Endpoint(id = "try")
public class MyEndPoint {

    @ReadOperation
    public Map<String, String> test(){
        Map<String, String> result = new HashMap<>();
        result.put("date", "20190414");
        return result;
    }

}