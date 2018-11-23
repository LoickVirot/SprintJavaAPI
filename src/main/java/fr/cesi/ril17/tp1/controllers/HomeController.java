package fr.cesi.ril17.tp1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Hello world!", HttpStatus.OK);
    }

}
