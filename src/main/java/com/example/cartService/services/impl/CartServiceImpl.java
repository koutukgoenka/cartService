package com.koushik.cartService.services.impl;

import com.koushik.cartService.daos.CartDAO;
import com.koushik.cartService.entities.Cart;
import com.koushik.cartService.exceptions.CartNotFoundException;
import com.koushik.cartService.exceptions.CustomerNameNotFoundException;
import com.koushik.cartService.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDAO cartDao;

    @Override
    public Cart createCart(Cart cart) {
        return cartDao.save(cart);
    }

    @Override
    public boolean deleteCart(int cartId) {
        cartDao.deleteById(cartId);
        return true;
    }

    @Override
    public Cart findByCartId(int cartId) throws CartNotFoundException {
        return cartDao.findById(cartId).orElseThrow(() -> new CartNotFoundException());
    }

    @Override
    public Cart findByCustomerName(String customerName) throws CustomerNameNotFoundException {
        Cart cart = cartDao.findByCustomerName(customerName);

        if(cart == null){
            throw new CustomerNameNotFoundException();
        }
        return cart;
    }
}
