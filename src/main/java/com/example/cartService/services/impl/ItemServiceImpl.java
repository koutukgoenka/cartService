package com.koushik.cartService.services.impl;

import com.koushik.cartService.daos.ItemDAO;
import com.koushik.cartService.entities.Cart;
import com.koushik.cartService.entities.Item;
import com.koushik.cartService.exceptions.CartNotFoundException;
import com.koushik.cartService.exceptions.ItemNotFoundException;
import com.koushik.cartService.services.CartService;
import com.koushik.cartService.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {



    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private CartService cartService;

    @Override
    public Item addItemToCart(Item item, int cartId) throws CartNotFoundException {

        //need to fetch the cart based on the cartId
        Cart cart = cartService.findByCartId(cartId);
        item.setCart(cart);
        return itemDAO.save(item);
    }

    @Override
    public List<Item> getItemsFromTheCart(int cartId) throws CartNotFoundException {
        Cart cart = cartService.findByCartId(cartId);
        //Lazy initialization --> cart.getItems() -- empty

        List<Item> items = itemDAO.findItemsByCart(cart);

        return items;
    }

    @Override
    public Cart getCartOfTheItem(int itemId) throws ItemNotFoundException {

        Item item = itemDAO.findById(itemId).orElseThrow(() -> new ItemNotFoundException());

        /**
         * item -> cart : many to one : eager initialization
         */

        Cart cart = item.getCart();

        return cart;
    }
}
