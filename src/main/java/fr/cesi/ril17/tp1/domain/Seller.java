package fr.cesi.ril17.tp1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import java.util.List;

@Entity(name="seller")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // String au lien d'un Integer pour prendre en compte les codes commençant par 0 (L'Ain, 01 par exemple)
    @Length(min = 5, max = 5)
    private String zipCode;

    private String city;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.REMOVE)
    private List<Product> products;
}
