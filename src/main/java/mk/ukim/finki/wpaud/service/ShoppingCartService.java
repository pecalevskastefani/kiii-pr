package mk.ukim.finki.wpaud.service;

import mk.ukim.finki.wpaud.model.Product;
import mk.ukim.finki.wpaud.model.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
    List<Product> listAllProductsInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addProductInShoppingCart(String username, Long productId);

    void deleteById(Long id);
    //Optional<Product> findById(Long id);
}
