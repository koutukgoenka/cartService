package com.koushik.cartService.daos;

import com.koushik.cartService.entities.Cart;
import com.koushik.cartService.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This will be used to do the CRUD operations with the databases
 */

public interface ItemDAO extends JpaRepository<Item, Integer> {

    public List<Item> findItemsByCart(Cart cart);
}
