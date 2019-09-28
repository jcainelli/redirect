package com.jcainelli.redirect.controller;

import com.jcainelli.redirect.service.ShortUrlService;
import com.jcainelli.redirect.vo.ShortUrlVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(value = "/")
@Api(tags = "/")
public class HomeController {

    @Autowired
    private ShortUrlService shortUrlService;

    @GetMapping(path = "/{shortUrl}", produces = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna uma URL redirecionando para uma URL de acordo com a URL encurtada")
    private RedirectView findShortUrl(@PathVariable("shortUrl") String shortUrl) {
        return new RedirectView(shortUrlService.findShortUrl(shortUrl));
    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Salva uma URL  e retorna uma URL encurtada")
    private ShortUrlVO save(HttpServletRequest request, @Valid @RequestBody final String urlOrigin) {
        return shortUrlService.save(request, urlOrigin);
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Documentação da API")
    private RedirectView findShortUrl() {
        return new RedirectView("doc/swagger-ui.html");
    }

}
