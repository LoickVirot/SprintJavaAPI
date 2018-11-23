package fr.cesi.ril17.tp1.controllers.api;

import fr.cesi.ril17.tp1.domain.Product;
import fr.cesi.ril17.tp1.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public ResponseEntity<List<Product>> list() {
        List<Product> productList = (List<Product>) productRepository.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> show(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent())
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        return new ResponseEntity<>((Product) null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Product> create(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<Product> product= productRepository.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>("No product found with id " + id, HttpStatus.NOT_FOUND);
        }
        productRepository.delete(product.get());
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable Long id) {
        Optional<Product> productFromBase = productRepository.findById(id);
        if (!productFromBase.isPresent())
            return new ResponseEntity<>((Product) null, HttpStatus.NOT_FOUND);

        if (product.getName() != null)
            productFromBase.get().setName(product.getName());

        if (product.getDescription() != null)
            productFromBase.get().setDescription(product.getDescription());

        if (product.getImageUrl() != null)
            productFromBase.get().setImageUrl(product.getImageUrl());

        if (product.getPrice() != null)
            productFromBase.get().setPrice(product.getPrice());

        productRepository.save(productFromBase.get());

        return new ResponseEntity<>(productFromBase.get(), HttpStatus.OK);
    }
}
