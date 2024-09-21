package com.emazon.api_transaction.domain.model;

import java.time.LocalDate;

public class ArticleUpdate {
    private Integer id;
    private Integer articleId;
    private Integer quantity;
    private LocalDate updateDate;
    private String userEmail;

    public ArticleUpdate() {
    }

    public ArticleUpdate(Integer id, Integer articleId, Integer quantity, LocalDate updateDate, String userEmail) {
        this.id = id;
        this.articleId = articleId;
        this.quantity = quantity;
        this.updateDate = updateDate;
        this.userEmail = userEmail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
