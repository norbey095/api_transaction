package com.emazon.api_transaction.infraestructure.output.entity;

import com.emazon.api_transaction.infraestructure.util.ConstantsOutput;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "supply")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = ConstantsOutput.ARTICLE_ID, nullable = false)
    private Integer articleId;
    private Integer quantity;
    @Column(name = ConstantsOutput.UPDATE_DATE, nullable = false)
    private LocalDate updateDate;
    @Column(name = ConstantsOutput.USER_EMAIL, nullable = false)
    private String userEmail;
}
