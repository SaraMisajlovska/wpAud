package mk.finki.ukim.wpaud.repository.jpa;

import mk.finki.ukim.wpaud.model.ShoppingCart;
import mk.finki.ukim.wpaud.model.User;
import mk.finki.ukim.wpaud.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndStatus (User user, ShoppingCartStatus status);
}
