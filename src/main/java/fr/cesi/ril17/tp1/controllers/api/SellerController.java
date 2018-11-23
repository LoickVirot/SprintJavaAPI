package fr.cesi.ril17.tp1.controllers.api;

import fr.cesi.ril17.tp1.domain.Seller;
import fr.cesi.ril17.tp1.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping(value = "/seller", produces = MediaType.APPLICATION_JSON_VALUE)
public class SellerController {

    @Autowired
    private SellerRepository sellerRepository;

    @GetMapping("/")
    public ResponseEntity<List<Seller>> list() {
        List<Seller> sellerList = (List<Seller>) sellerRepository.findAll();
        return new ResponseEntity<>(sellerList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> show(@PathVariable Long id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        if (seller.isPresent())
            return new ResponseEntity<>(seller.get(), HttpStatus.OK);
        return new ResponseEntity<>((Seller) null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Seller> create(@RequestBody Seller seller) {
        sellerRepository.save(seller);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        if (!seller.isPresent()) {
            return new ResponseEntity<>("No seller found with id " + id, HttpStatus.NOT_FOUND);
        }
        sellerRepository.delete(seller.get());
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Seller> update(@RequestBody Seller seller, @PathVariable Long id) {
        Optional<Seller> sellerFromBase = sellerRepository.findById(id);
        if (!sellerFromBase.isPresent())
            return new ResponseEntity<>((Seller) null, HttpStatus.NOT_FOUND);

        if (seller.getName() != null)
            sellerFromBase.get().setName(seller.getName());

        if (seller.getCity() != null)
            sellerFromBase.get().setCity(seller.getCity());

        if (seller.getZipCode() != null)
            sellerFromBase.get().setZipCode(seller.getZipCode());

        sellerRepository.save(sellerFromBase.get());
        return new ResponseEntity<>(sellerFromBase.get(), HttpStatus.OK);
    }
}
