package fr.cesi.ril17.tp1.repositories;

import fr.cesi.ril17.tp1.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
