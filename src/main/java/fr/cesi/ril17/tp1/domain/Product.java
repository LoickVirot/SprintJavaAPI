package fr.cesi.ril17.tp1.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    /*
    o Id (Long) – généré automatiquement
    o Name (String)
    o Description (String) – Enregistré en tant que champ texte
    o ImageUrl (String)
    o Price (Decimal)
    o CreatedOn (Date)
    o Seller (Seller)
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imageUrl;

    @Column(columnDefinition = "DECIMAL")
    private Double price;

    private Date createdOn;

    @ManyToOne
    @JsonBackReference
    private Seller seller;


}
