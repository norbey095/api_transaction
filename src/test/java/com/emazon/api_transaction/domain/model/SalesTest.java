package com.emazon.api_transaction.domain.model;

import com.emazon.api_transaction.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SalesTest {


    @Test
    void shouldCorrectlyCreateArticleInstanceContructor() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Sales sales = new Sales(ConstantsDomain.ID, ConstantsDomain.ARTICLE_ID
                , localDateTime, ConstantsDomain.NAME,ConstantsDomain.ARTICLE_ID);

        assertNotNull(sales);
        assertEquals(ConstantsDomain.ID, sales.getId());
        assertEquals(ConstantsDomain.ARTICLE_ID, sales.getArticleId());
        assertEquals(localDateTime, sales.getBuyDate());
        assertEquals(ConstantsDomain.NAME, sales.getEmail());
        assertEquals(ConstantsDomain.ARTICLE_ID, sales.getQuantity());
    }


    @Test
    void shouldCorrectlyCreateArticleInstanceSet() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Sales sales = new Sales();
        sales.setId(ConstantsDomain.ID);
        sales.setArticleId(ConstantsDomain.ARTICLE_ID);
        sales.setQuantity(ConstantsDomain.QUANTITY);
        sales.setBuyDate(localDateTime);
        sales.setEmail(ConstantsDomain.EMAIL);

        assertNotNull(sales);
        assertEquals(ConstantsDomain.ID, sales.getId());
        assertEquals(ConstantsDomain.ARTICLE_ID, sales.getArticleId());
        assertEquals(ConstantsDomain.QUANTITY, sales.getQuantity());
        assertEquals(localDateTime, sales.getBuyDate());
        assertEquals(ConstantsDomain.EMAIL, sales.getEmail());
    }
}


