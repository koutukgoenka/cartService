package com.koushik.cartService.daos;

import com.koushik.cartService.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This will help us do the crud operation with the databases
 */

public interface CartDAO extends JpaRepository<Cart, Integer> {

    public Cart findByCustomerName(String name);
}
