package com.gorajski.restdocsdemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
@WebMvcTest(MainController.class)
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getString_returnsHtmlString_whenStringEndpointIsCalled() throws Exception {
        mockMvc.perform(get("/string"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<h1>Gimme some more</h1>")))
                .andDo(document("string-endpoint"));
    }

    @Test
    void getJson_returnsJsonObject_whenJsonEndpointIsCalled() throws Exception {
        mockMvc.perform(get("/json"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"field\": \"strawberry forever\"}"))
                .andDo(document("json-endpoint",
                        responseFields(
                                fieldWithPath("field").description("You can describe what an arbitrary field is for here.")
                        )
                ))
        ;
    }
}