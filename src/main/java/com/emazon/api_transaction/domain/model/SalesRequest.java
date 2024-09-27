package com.emazon.api_transaction.domain.model;

import java.time.LocalDateTime;

public class SalesRequest {

    private Integer id;
    private String  email;
    private LocalDateTime buyDate;
    private Integer idArticle;
    private Integer quantity;

    public SalesRequest() {
    }

    public SalesRequest(Integer id, String email, LocalDateTime buyDate, Integer idArticle, Integer quantity) {
        this.id = id;
        this.email = email;
        this.buyDate = buyDate;
        this.idArticle = idArticle;
        this.quantity = quantity;
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

    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
