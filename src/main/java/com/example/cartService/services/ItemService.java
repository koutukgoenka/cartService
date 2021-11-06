package com.koushik.cartService.services;

import com.koushik.cartService.entities.Cart;
import com.koushik.cartService.entities.Item;
import com.koushik.cartService.exceptions.CartNotFoundException;
import com.koushik.cartService.exceptions.ItemNotFoundException;

import java.util.List;

public interface ItemService {

    public Item addItemToCart(Item item, int cartId) throws CartNotFoundException;

    public List<Item> getItemsFromTheCart(int cartId) throws CartNotFoundException;

    public Cart getCartOfTheItem(int itemId) throws ItemNotFoundException;
}
