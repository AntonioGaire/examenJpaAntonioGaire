package org.iesvdm.examenjpaantoniogaire.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "product")

@Entity
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private long idProduct;

    @Column(name = "name")
    private String name;

    @Column(name= "description")
    private String description;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name= "sku")
    private String sku;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private long quantity;

    @ManyToOne()
    @JoinColumn(name = "id_category")
    private Category category;

}
