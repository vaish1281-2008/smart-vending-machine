package com.vending.smartvending.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String code;
    @Column(nullable = false)
    private String name;
    private String category;
    private String imageUrl;
    private double price;
    private int quantity;

    public Product() {}
    public Product(String code, String name, String category, double price, int quantity, String imageUrl) {
        this.code=code; this.name=name; this.category=category; this.price=price; this.quantity=quantity; this.imageUrl=imageUrl;
    }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getCode(){return code;} public void setCode(String code){this.code=code;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getCategory(){return category;} public void setCategory(String category){this.category=category;}
    public String getImageUrl(){return imageUrl;} public void setImageUrl(String imageUrl){this.imageUrl=imageUrl;}
    public double getPrice(){return price;} public void setPrice(double price){this.price=price;}
    public int getQuantity(){return quantity;} public void setQuantity(int quantity){this.quantity=quantity;}
}
