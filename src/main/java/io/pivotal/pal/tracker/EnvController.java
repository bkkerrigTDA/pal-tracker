package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

     String port;
     String memoryLimit;
     String cfInstanceIndex;
     String cfInstanceAddress;

    public EnvController(
            @Value("${cf.port:NOT SET}") String s,
            @Value("${cf.memory.limit:NOT SET}") String s1,
            @Value("${cf.instance.index:NOT SET}") String s2,
            @Value("${cf.instance.addr:NOT SET}") String s3)
    {
        this.port = s;
        this.memoryLimit = s1;
        this.cfInstanceIndex = s2;
        this.cfInstanceAddress = s3;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map<String, String> env = new HashMap<>();
        env.put("PORT", port);
        env.put("MEMORY_LIMIT", memoryLimit);
        env.put("CF_INSTANCE_INDEX", cfInstanceIndex);
        env.put("CF_INSTANCE_ADDR", cfInstanceAddress);
        return env;
    }
}
