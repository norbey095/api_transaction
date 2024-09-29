package com.emazon.api_transaction.domain.model;

import java.time.LocalDateTime;

public class Sales {

    private Integer id;
    private String  email;
    private LocalDateTime buyDate;
    private Integer articleId;
    private Integer quantity;

    public Sales() {
    }

    public Sales(Integer quantity, Integer articleId, LocalDateTime buyDate, String email, Integer id) {
        this.quantity = quantity;
        this.articleId = articleId;
        this.buyDate = buyDate;
        this.email = email;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDateTime buyDate) {
        this.buyDate = buyDate;
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
}
