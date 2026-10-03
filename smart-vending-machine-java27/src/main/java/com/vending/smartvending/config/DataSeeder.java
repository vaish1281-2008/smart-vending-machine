package com.vending.smartvending.config;

import com.vending.smartvending.model.Product;
import com.vending.smartvending.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class DataSeeder {
    @Bean CommandLineRunner seed(ProductRepository repo){
        return args -> {
            if(repo.count()>0) return;
            String[][] data={
                {"A1","Classic Burger","Meals","80","12","https://images.unsplash.com/photo-1568901346375-23c9450c58cd?auto=format&fit=crop&w=700&q=85"},
                {"A2","Margherita Pizza","Meals","120","8","https://images.unsplash.com/photo-1574071318508-1cdbab80d002?auto=format&fit=crop&w=700&q=85"},
                {"A3","French Fries","Snacks","50","18","https://images.unsplash.com/photo-1573080496219-bb080dd4f877?auto=format&fit=crop&w=700&q=85"},
                {"A4","Grilled Sandwich","Snacks","70","10","https://images.unsplash.com/photo-1528735602780-2552fd46c7af?auto=format&fit=crop&w=700&q=85"},
                {"B1","Creamy Pasta","Meals","110","7","https://images.unsplash.com/photo-1473093295043-cdd812d0e601?auto=format&fit=crop&w=700&q=85"},
                {"B2","Chocolate Donut","Desserts","45","14","https://images.unsplash.com/photo-1551024601-bec78aea704b?auto=format&fit=crop&w=700&q=85"},
                {"B3","Vanilla Ice Cream","Desserts","60","9","https://images.unsplash.com/photo-1501443762994-82bd5dace89a?auto=format&fit=crop&w=700&q=85"},
                {"B4","Chocolate Bar","Snacks","40","20","https://images.unsplash.com/photo-1549007994-cb92caebd54b?auto=format&fit=crop&w=700&q=85"},
                {"C1","Fresh Salad","Healthy","90","6","https://images.unsplash.com/photo-1512621776951-a57141f2eefd?auto=format&fit=crop&w=700&q=85"},
                {"C2","Cappuccino","Drinks","65","15","https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?auto=format&fit=crop&w=700&q=85"},
                {"C3","Fresh Orange Juice","Drinks","75","11","https://images.unsplash.com/photo-1600271886742-f049cd451bba?auto=format&fit=crop&w=700&q=85"},
                {"C4","Cheese Popcorn","Snacks","55","13","https://images.unsplash.com/photo-1585647347384-2593bc35786b?auto=format&fit=crop&w=700&q=85"}
            };
            repo.saveAll(List.of(data).stream().map(x->new Product(x[0],x[1],x[2],Double.parseDouble(x[3]),Integer.parseInt(x[4]),x[5])).toList());
        };
    }
}
