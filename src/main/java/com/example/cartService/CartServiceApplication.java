package com.koushik.cartService;

import com.koushik.cartService.daos.CartDAO;
import com.koushik.cartService.daos.ItemDAO;
import com.koushik.cartService.entities.Cart;
import com.koushik.cartService.entities.Item;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;

@SpringBootApplication
public class CartServiceApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(CartServiceApplication.class, args);

		System.out.println("Hello Spring");
	}

}
