package fr.cesi.ril17.tp1.services;

import fr.cesi.ril17.tp1.domain.Product;
import fr.cesi.ril17.tp1.domain.Seller;
import fr.cesi.ril17.tp1.repositories.ProductRepository;
import fr.cesi.ril17.tp1.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class DataLoader {

    private SellerRepository sellerRepo;
    private ProductRepository productRepo;

    @Autowired
    public DataLoader(SellerRepository sellerRepo, ProductRepository productRepo) {
        this.sellerRepo = sellerRepo;
        this.productRepo = productRepo;
    }

    @PostConstruct
    public void load() {
        Seller seller1 = new Seller(null, "Loïck Virot", "06130", "Grasse", null);
        sellerRepo.save(seller1);

        Seller seller2 = new Seller(null, "Jane Doe", "75001", "Paris", null);
        sellerRepo.save(seller2);

        Product product1 = new Product(
                null,
                "Télé 4K UHD",
                "Superbe télé 4K Ultra HD pas cher et de bonne facture",
                "https://imgur.com/loremipsum",
                495.90,
                new Date(),
                seller1
        );
        productRepo.save(product1);

        Product product2 = new Product(
                null,
                "iPhone 12XS Plus",
                "Après le 11, le 12.",
                "https://imgur.com/appleiphone12",
                1099.90,
                new Date(),
                seller1
        );
        productRepo.save(product2);

        Product product3 = new Product(
                null,
                "Ordinateur Alienware X2000",
                "Idéal pour le gaming, cet ordinateur vous permet de jouer a tous vos jeux en 4K",
                "https://imgur.com/alienwarex2000",
                890.50,
                new Date(),
                seller2
        );
        productRepo.save(product3);
    }

}
