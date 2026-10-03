package com.vending.smartvending.controller;

import com.vending.smartvending.model.Product;
import com.vending.smartvending.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {
    private final ProductRepository repo;
    public ProductController(ProductRepository repo){this.repo=repo;}

    @GetMapping public List<Product> all(){ return repo.findAll(); }

    @PostMapping public ResponseEntity<?> add(@RequestBody Product p){
        if(p.getCode()==null || p.getCode().isBlank() || p.getName()==null || p.getName().isBlank() || p.getPrice()<=0 || p.getQuantity()<0)
            return ResponseEntity.badRequest().body("Enter valid product details.");
        if(repo.existsByCodeIgnoreCase(p.getCode())) return ResponseEntity.badRequest().body("Product code already exists.");
        return ResponseEntity.ok(repo.save(p));
    }

    @PutMapping("/{id}") public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product incoming){
        return repo.findById(id).map(p -> {p.setName(incoming.getName());p.setCategory(incoming.getCategory());p.setPrice(incoming.getPrice());p.setQuantity(incoming.getQuantity());p.setImageUrl(incoming.getImageUrl());return ResponseEntity.ok(repo.save(p));}).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") public ResponseEntity<?> delete(@PathVariable Long id){ if(!repo.existsById(id)) return ResponseEntity.notFound().build(); repo.deleteById(id); return ResponseEntity.ok().build(); }

    @PostMapping("/buy/{code}") public ResponseEntity<?> buy(@PathVariable String code, @RequestParam double amount){
        var op=repo.findByCodeIgnoreCase(code);
        if(op.isEmpty()) return ResponseEntity.status(404).body("Product not found.");
        Product p=op.get();
        if(p.getQuantity()<=0) return ResponseEntity.badRequest().body("Sorry, " + p.getName() + " is out of stock.");
        if(amount < p.getPrice()) return ResponseEntity.badRequest().body(String.format("Please add ₹%.2f more.", p.getPrice()-amount));
        p.setQuantity(p.getQuantity()-1); repo.save(p);
        double change=amount-p.getPrice();
        return ResponseEntity.ok(String.format("Purchase successful! %s dispensed. Change: ₹%.2f", p.getName(), change));
    }
}
