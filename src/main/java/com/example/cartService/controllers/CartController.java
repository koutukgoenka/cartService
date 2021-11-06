package com.koushik.cartService.controllers;

import com.koushik.cartService.dtos.CartDTO;
import com.koushik.cartService.dtos.ItemDTO;
import com.koushik.cartService.entities.Cart;
import com.koushik.cartService.entities.Item;
import com.koushik.cartService.exceptions.CartNotFoundException;
import com.koushik.cartService.exceptions.ItemNotFoundException;
import com.koushik.cartService.services.CartService;
import com.koushik.cartService.services.ItemService;
import com.koushik.cartService.utils.DTOEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

/**
 * 127.0.0.1:8080/cartService/v1/carts
 */
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity helloStudents(){
        return new ResponseEntity("hello Students", HttpStatus.OK);
    }

    /**
     * create an endpoint to create the cart
     * POST 127.0.0.1:8080/cartService/v1/carts
     * Body Request -- JSON
     */

    @PostMapping
    public ResponseEntity createCart(@RequestBody CartDTO cartDTO){

        // Create and save Cart in the system
        Cart cart = cartService.createCart(DTOEntityMapper.convertCartDTOToCartEntity(cartDTO));

        //Convert the Cart entity back to the CartDTO
        CartDTO cartResponse = DTOEntityMapper.convertCartEntityToCartDTO(cart);

        //Return the response
        return new ResponseEntity(cartResponse, HttpStatus.CREATED);
    }

    /**
     * Search a cart based on the cartId
     *
     * GET 127.0.0.1/cartService/v1/carts/{cart_id}
     */
    @GetMapping("/{cart_id}")
    public ResponseEntity getCart(@PathVariable("cart_id") int cartId) throws CartNotFoundException {
        Cart cart = cartService.findByCartId(cartId);

        CartDTO cartResponse = DTOEntityMapper.convertCartEntityToCartDTO(cart);

        return new ResponseEntity(cartResponse, HttpStatus.OK);
    }

    /**
     * Add an item in the cart
     */

    @PostMapping("/{cart_id}/items")
    public ResponseEntity addItemToCart(@RequestBody ItemDTO itemDTO, @PathVariable("cart_id") int cartId) throws CartNotFoundException, ItemNotFoundException {

        // I need to create item inside the cart so need itemService
        Item item = itemService.addItemToCart(DTOEntityMapper.convertItemDTOToItemEntity(itemDTO), cartId);

        ItemDTO itemResponse = DTOEntityMapper.convertItemEntityToItemDTO(item);
        Cart cart = itemService.getCartOfTheItem(itemResponse.getItemId());
        CartDTO cartDTO = DTOEntityMapper.convertCartEntityToCartDTO(cart);

        return new ResponseEntity(cartDTO, HttpStatus.CREATED);
    }




}
