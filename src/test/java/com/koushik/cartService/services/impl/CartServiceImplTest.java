package com.koushik.cartService.services.impl;

import com.koushik.cartService.daos.CartDAO;
import com.koushik.cartService.entities.Cart;
import com.koushik.cartService.exceptions.CartNotFoundException;
import com.koushik.cartService.exceptions.CustomerNameNotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
public class CartServiceImplTest {

    /**
     * Just a mock not the actual object
     */
    @Mock
    CartDAO cartDaoMock;

    @InjectMocks
    CartServiceImpl cartService;


    /**
     * Test for createCart
     */
    @Test
    public void testCreateCart(){
        // Create the data
        Cart cart  = new Cart();
        cart.setCustomerName("Koushik Goenka");

        Cart cart1  = new Cart();
        cart1.setCustomerName("Koushik Goenka");
        cart1.setCartId(1);

        // Give the functionality to mock

        Mockito.when(cartDaoMock.save(cart)).thenReturn(cart1);

        //execute
        Cart savedCart = cartService.createCart(cart);

        //testing
        Assert.assertNotNull(savedCart);
        Assert.assertEquals(1, savedCart.getCartId());
    }

    /**
     * Test for findByCartId
     */
    @Test
    public void testFindByCartId() throws CartNotFoundException {

        //Data
        Cart cart1  = new Cart();
        cart1.setCustomerName("Koushik Goenka");
        cart1.setCartId(1);

        //Mock
        Mockito.when(cartDaoMock.findById(1)).thenReturn(Optional.of(cart1));

        //Execution
        Cart cart = cartService.findByCartId(1);

        //Assertion
        Assert.assertNotNull(cart);
        Assert.assertEquals("Koushik Goenka", cart.getCustomerName());

    }

    @Test
    public void testFindByCartIdThrowsException() throws CartNotFoundException {

        //Data
        Cart cart1  = new Cart();
        cart1.setCustomerName("Koushik Goenka");
        cart1.setCartId(1);

        //Mockito
        Mockito.when(cartDaoMock.findById(1)).thenReturn(Optional.empty());

        //Execution
        try{
            Cart cart = cartService.findByCartId(1);
        } catch (Exception e){
            Assert.assertEquals(CartNotFoundException.class, e.getClass());
        }
    }

    /**
     * Test for deleteCart
     */
    @Test
    public void testDeleteCart(){

        //Data

        //Mock -- When we have to mock a method that returns nothing
        Mockito.doNothing().when(cartDaoMock).deleteById(1);

        //Execution
        boolean deleteResult = cartService.deleteCart(1);

        //Assertion
        Assert.assertTrue(deleteResult);
    }



    /**
     * Test for findByCustomerName
     */

    public void testFindByCustomerName() throws CustomerNameNotFoundException {

        Cart cart1  = new Cart();
        cart1.setCustomerName("Koushik Goenka");
        cart1.setCartId(1);

        Mockito.when(cartDaoMock.findByCustomerName("Koushik Goenka")).thenReturn(cart1);

        //Execution
        Cart cart = cartService.findByCustomerName("Koushik Goenka");

        //Assertion
        Assert.assertNotNull(cart);
        Assert.assertEquals("Koushik Goenka", cart.getCustomerName());

    }
}
