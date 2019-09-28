package com.jcainelli.redirect.util.dev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcainelli.redirect.entity.ShortUrl;
import com.jcainelli.redirect.repository.ShortUrlRepository;
import com.jcainelli.redirect.util.ExpireCalculateUtil;

@Component
public class InitializeDataBaseDesenvUtil {

    @Autowired
    private ShortUrlRepository redirectRepository;

    @Autowired
    private ExpireCalculateUtil expireCalculateUtil;


    public void instantiateDatabase() {
        ShortUrl red1 = new ShortUrl();
        red1.setId(1L);
        red1.setUrlOrigin("https://github.com/jcainelli");
        red1.setUrlShort("jfmc1982");
        red1.setExpiration(expireCalculateUtil.calculateTimeExpirationToUrl());
        redirectRepository.save(red1);
    }
}
