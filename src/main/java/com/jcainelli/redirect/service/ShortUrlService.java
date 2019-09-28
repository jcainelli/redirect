package com.jcainelli.redirect.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jcainelli.redirect.entity.ShortUrl;
import com.jcainelli.redirect.vo.ShortUrlVO;

public interface ShortUrlService {

    ShortUrlVO save(final HttpServletRequest request, final String url);

    List<ShortUrl> findAll();

    String findShortUrl(final String shortUrl);

}
