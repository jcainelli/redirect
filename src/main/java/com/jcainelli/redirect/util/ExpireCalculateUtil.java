package com.jcainelli.redirect.util;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExpireCalculateUtil {

    @Value("${short.url.valid.time:30}")
    private Integer validTime;

    public Instant calculateTimeExpirationToUrl(){
        return Instant.now().plusSeconds(minuteToSecondes(validTime));
    }

    public Integer minuteToSecondes(Integer minutes){
        return minutes * 60;
    }

}
