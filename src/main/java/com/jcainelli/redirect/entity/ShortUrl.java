package com.jcainelli.redirect.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "REDIRECT")
@Data
public class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url_origin", nullable = false, length = 500)
    private String urlOrigin;

    @Column(name = "url_short", nullable = false, length = 100)
    private String urlShort;

    @Column(name = "expiration_time", nullable = false)
    private Instant expiration;


}
