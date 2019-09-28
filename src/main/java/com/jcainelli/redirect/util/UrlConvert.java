package com.jcainelli.redirect.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UrlConvert {

    public static final int MIN_SHORT_URL_LENGTH = 5;
    public static final int MAX_SHORT_URL_LENGTH = 36;

    private String list = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public Integer generateNumericShortUrl(String url) {
        Integer result = 0;

        for (int i = 0; i < url.length(); i++){
            Integer index = list.indexOf(url.charAt(i));

            if (index >= 0){
                result+= index;
            }
        }
        return result;
    }

    public String generateStringUrlByNumericShortUrl(Integer numericShortUrl) {
        char map[] = list.toCharArray();
        Integer count = 0;

        StringBuffer url = new StringBuffer();

        while (numericShortUrl > 0) {
            url.append(map[numericShortUrl % 62]);
            numericShortUrl = numericShortUrl / 62;

            if (++count > MAX_SHORT_URL_LENGTH){
                break;
            }
        }

        if (url.length() < MIN_SHORT_URL_LENGTH){
            Random ramdom = new Random();
            while(url.length() <= MIN_SHORT_URL_LENGTH){
                url.append(map[ramdom.nextInt(62)]);
            }
        }

        return url.reverse().toString();
    }

}
