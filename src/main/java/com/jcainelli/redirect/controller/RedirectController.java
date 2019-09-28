package com.jcainelli.redirect.controller;

import java.time.Instant;
import java.util.List;

import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcainelli.redirect.entity.ShortUrl;
import com.jcainelli.redirect.service.ShortUrlService;
import com.jcainelli.redirect.util.RedirectConstants;

@RestController
@RequestMapping(value = RedirectConstants.REST_END_POINT_REDIRECT)
@Api(tags = RedirectConstants.REST_END_POINT_REDIRECT)
public class RedirectController {

    @Autowired
    private ShortUrlService shortUrlService;

    @Autowired
    private ShortUrlService redirectService;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna uma lista simples das URLs cadastradas")
    public List<ShortUrl> findAll() {
        return redirectService.findAll();
    }

    @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Informa se o sistema esta operante e a hora atual do servidor")
    public String test() {
        return "Service ok. Now: " + Instant.now();
    }

}
