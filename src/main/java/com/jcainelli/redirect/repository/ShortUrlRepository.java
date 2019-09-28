package com.jcainelli.redirect.repository;

import com.jcainelli.redirect.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    ShortUrl findByUrlShort(String shortUrl);

    ShortUrl findByUrlOrigin(String url);
}
