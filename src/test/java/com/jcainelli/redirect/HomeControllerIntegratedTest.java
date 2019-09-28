package com.jcainelli.redirect;

import com.jcainelli.redirect.entity.ShortUrl;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RedirectApplication.class)
@AutoConfigureMockMvc
@AutoConfigureCache
public class HomeControllerIntegratedTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShortUrl repo;

    @Test
    public void whenConsultListUrl_thenResultList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/redirect"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(Matchers.greaterThanOrEqualTo(1))))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"));
    }

}
