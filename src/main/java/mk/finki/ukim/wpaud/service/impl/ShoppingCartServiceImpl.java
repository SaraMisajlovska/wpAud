package mk.finki.ukim.wpaud.service.impl;

import mk.finki.ukim.wpaud.model.Product;
import mk.finki.ukim.wpaud.model.ShoppingCart;
import mk.finki.ukim.wpaud.model.User;
import mk.finki.ukim.wpaud.model.enumerations.ShoppingCartStatus;
import mk.finki.ukim.wpaud.model.exceptions.ProductAlreadyInShoppingCartException;
import mk.finki.ukim.wpaud.model.exceptions.ProductNotFoundException;
import mk.finki.ukim.wpaud.model.exceptions.ShoppingCartNotFoundException;
import mk.finki.ukim.wpaud.model.exceptions.UserNotFoundException;
import mk.finki.ukim.wpaud.repository.impl.InMemoryShoppingCartRepository;
import mk.finki.ukim.wpaud.repository.impl.InMemoryUserRepository;
import mk.finki.ukim.wpaud.repository.jpa.ShoppingCartRepository;
import mk.finki.ukim.wpaud.repository.jpa.UserRepository;
import mk.finki.ukim.wpaud.service.ProductService;
import mk.finki.ukim.wpaud.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository,
                                   ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if (!shoppingCartRepository.findById(cartId).isPresent()) {
            throw new ShoppingCartNotFoundException(cartId);
        }
        return shoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return shoppingCartRepository
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart shoppingCart = new ShoppingCart(user);
                    return shoppingCartRepository.save(shoppingCart);
                });

    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {

        ShoppingCart shoppingCart = getActiveShoppingCart(username);
        Product product = productService.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        if (shoppingCart.getProducts()
                .stream()
                .filter(i -> i.getId().equals(productId))
                .collect(Collectors.toList())
                .size() > 0)
            throw new ProductAlreadyInShoppingCartException(productId, username);
        shoppingCart.getProducts().add(product);
        return shoppingCartRepository.save(shoppingCart);
    }

}
