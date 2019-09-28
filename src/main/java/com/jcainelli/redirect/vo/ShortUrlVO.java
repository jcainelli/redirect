package com.jcainelli.redirect.vo;

import lombok.Data;

import java.time.Instant;

@Data
public class ShortUrlVO {

    private String newUrl;

    private Instant expiresAt;

}
