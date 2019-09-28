package com.jcainelli.redirect.service.impl;

import java.time.Instant;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jcainelli.redirect.util.UrlConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import com.jcainelli.redirect.entity.ShortUrl;
import com.jcainelli.redirect.repository.ShortUrlRepository;
import com.jcainelli.redirect.service.ShortUrlService;
import com.jcainelli.redirect.util.ExpireCalculateUtil;
import com.jcainelli.redirect.vo.ShortUrlVO;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    @Autowired
    private ShortUrlRepository repository;

    @Autowired
    private ExpireCalculateUtil expireCalculateUtil;

    @Autowired
    private UrlConvert urlConvert;

    @Override
    public ShortUrlVO save(final HttpServletRequest request, final String urlOrigin) {
        ShortUrlVO result = new ShortUrlVO();

        validateUrlOrigin(urlOrigin);

        ShortUrl persist = calculeShortUrl(urlOrigin);

        ShortUrl entity = repository.save(persist);
        if (entity != null) {
            result.setExpiresAt(entity.getExpiration());
            result.setNewUrl(extractUrl(request, entity.getUrlShort()));
        }

        return result;
    }

    private String generateShortUrl(String urlOrigin) {
        Integer numericUrl = urlConvert.generateNumericShortUrl(urlOrigin);
        return urlConvert.generateStringUrlByNumericShortUrl(numericUrl);
    }

    private String extractUrl(final HttpServletRequest request, final String urlShort) {
        return request.getRequestURL() + urlShort;
    }

    private void validateUrlOrigin(final String urlOrigin) {
        if (StringUtils.isEmpty(urlOrigin)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Url Origin: Required Field");
        }
    }

    private ShortUrl calculeShortUrl(final String urlOrigin) {
        ShortUrl result = new ShortUrl();

        ShortUrl entity = repository.findByUrlOrigin(urlOrigin);
        if (entity != null) {
            result = entity;
        }else{
            result.setUrlShort(generateShortUrl(urlOrigin));
            result.setUrlOrigin(urlOrigin);
        }
        result.setExpiration(expireCalculateUtil.calculateTimeExpirationToUrl());

        return result;
    }

    @Override
    public String findShortUrl(final String shortUrl) {

        ShortUrl redirect = repository.findByUrlShort(shortUrl);
        if (redirect == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Url not found");
        }

        if (redirect.getExpiration().getEpochSecond() < Instant.now().getEpochSecond()) {
            throw new ResponseStatusException(HttpStatus.GONE, "Url Expired");
        }

        return redirect.getUrlOrigin();
    }

    @Override
    public List<ShortUrl> findAll() {
        return repository.findAll();
    }

}
