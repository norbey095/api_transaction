package com.emazon.api_transaction.infraestructure.output.entity;

import com.emazon.api_transaction.infraestructure.util.ConstantsOutput;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    @Column(name = ConstantsOutput.BUY_DATE, nullable = false)
    private LocalDateTime buyDate;
    @Column(name = ConstantsOutput.ARTICLE_ID, nullable = false)
    private Integer articleId;
    private Integer quantity;
}
