package com.emazon.api_transaction.domain.model;

import com.emazon.api_transaction.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ArticleUpdateTest {


    @Test
    void shouldCorrectlyCreateArticleInstanceContructor() {
        ArticleUpdate article = new ArticleUpdate(ConstantsDomain.ID, ConstantsDomain.ARTICLE_ID
                , ConstantsDomain.QUANTITY
                , LocalDate.now(), ConstantsDomain.EMAIL);

        assertNotNull(article);
        assertEquals(ConstantsDomain.ID, article.getId());
        assertEquals(ConstantsDomain.ARTICLE_ID, article.getArticleId());
        assertEquals(ConstantsDomain.QUANTITY, article.getQuantity());
        assertEquals(LocalDate.now(), article.getUpdateDate());
        assertEquals(ConstantsDomain.EMAIL, article.getUserEmail());
    }


    @Test
    void shouldCorrectlyCreateArticleInstanceSet() {
        ArticleUpdate article = new ArticleUpdate();
        article.setId(ConstantsDomain.ID);
        article.setArticleId(ConstantsDomain.ARTICLE_ID);
        article.setQuantity(ConstantsDomain.QUANTITY);
        article.setUpdateDate(LocalDate.now());
        article.setUserEmail(ConstantsDomain.EMAIL);

        assertNotNull(article);
        assertEquals(ConstantsDomain.ID, article.getId());
        assertEquals(ConstantsDomain.ARTICLE_ID, article.getArticleId());
        assertEquals(ConstantsDomain.QUANTITY, article.getQuantity());
        assertEquals(LocalDate.now(), article.getUpdateDate());
        assertEquals(ConstantsDomain.EMAIL, article.getUserEmail());
    }
}


