package com.koushik.cartService.services;

import com.koushik.cartService.entities.Cart;
import com.koushik.cartService.exceptions.CartNotFoundException;
import com.koushik.cartService.exceptions.CustomerNameNotFoundException;

/**
 * We will define all the methods, that we want CartService to support
 */

public interface CartService {

    /**
     * These methods will add a new cart, and persist in the DB
     */
    public Cart createCart(Cart cart);

    public boolean deleteCart(int cartId);

    public Cart findByCartId(int cartId) throws CartNotFoundException;

    public Cart findByCustomerName(String userName) throws CustomerNameNotFoundException;

}
