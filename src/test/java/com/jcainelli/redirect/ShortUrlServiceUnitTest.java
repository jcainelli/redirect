package com.jcainelli.redirect;

import com.jcainelli.redirect.entity.ShortUrl;
import com.jcainelli.redirect.repository.ShortUrlRepository;
import com.jcainelli.redirect.service.impl.ShortUrlServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;

@RunWith(MockitoJUnitRunner.class)
public class ShortUrlServiceUnitTest {

    @Mock
    private ShortUrlServiceImpl service;

    @Mock
    private ShortUrlRepository repository;

    @Before
    public void init(){
        service = new ShortUrlServiceImpl(repository);
    }

    @Test
    public void whenFindUrlShort_thenResult(){
        Mockito.when(repository.findByUrlShort(Mockito.anyString())).thenReturn(createNewShortUrl());

        String actual = service.findShortUrl("jfmc1982");

        Assert.assertEquals("https://github.com/jcainelli", actual);
    }

    private ShortUrl createNewShortUrl() {
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setExpiration(Instant.now().plusSeconds(60));
        shortUrl.setUrlOrigin("https://github.com/jcainelli");
        return shortUrl;
    }

}
