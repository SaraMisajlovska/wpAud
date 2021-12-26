package mk.finki.ukim.wpaud.model;

import lombok.Data;
import mk.finki.ukim.wpaud.model.enumerations.ShoppingCartStatus;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;

    @ManyToMany
    //@Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    private List<Product> products;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    public ShoppingCart() {
        //this.id = (long) (Math.random() * 1000);
    }

    public ShoppingCart(User user) {
        //this.id = (long) (Math.random() * 1000);
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.products = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }

}
