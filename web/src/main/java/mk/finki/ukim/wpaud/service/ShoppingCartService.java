package mk.finki.ukim.wpaud.service;

import mk.finki.ukim.wpaud.model.Product;
import mk.finki.ukim.wpaud.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Product> listAllProductsInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addProductToShoppingCart(String username, Long productId);

}
