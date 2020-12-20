package com.kanilturgut.product.catalog.service.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductCatalogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testCreateProduct() throws Exception {
        this.mockMvc
                .perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getCreateProductData().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk()
                );
    }

    @Test
    @Order(2)
    public void testGetProductDetails() throws Exception {
        String testProductId = getCreateProductData().getString("id");
        this.mockMvc
                .perform(get("/products/" + testProductId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(getCreateProductData().toString())
                );
    }

    @Test
    @Order(3)
    public void testGetProductList() throws Exception {
        this.mockMvc
                .perform(get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("[" + getCreateProductData().toString() + "]")
                );
    }

    @Test
    @Order(4)
    public void testUpdateProduct() throws Exception {
        String testProductId = getCreateProductData().getString("id");
        this.mockMvc
                .perform(put("/products/" + testProductId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getUpdateProductData().toString()))
                .andExpect(status().isOk()
                );
    }

    @Test
    @Order(5)
    public void testDeleteProduct() throws Exception {
        String testProductId = getCreateProductData().getString("id");
        this.mockMvc
                .perform(delete("/products/" + testProductId))
                .andExpect(status().isOk());
    }

    public JSONObject getCreateProductData() throws JSONException {
        JSONObject createProductData = new JSONObject();
        createProductData.put("id", "test-product-1");
        createProductData.put("title", "test product 1");
        return createProductData;
    }

    public JSONObject getUpdateProductData() throws JSONException {
        JSONObject createProductData = new JSONObject();
        createProductData.put("id", "test-product-1");
        createProductData.put("title", "test product 1 updated");
        return createProductData;
    }

}
