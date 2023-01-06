package mk.ukim.finki.wpaud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import mk.ukim.finki.wpaud.model.enumerations.ShoppingCartStatus;

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
    @JsonIgnore
    private User user;
    @ManyToMany //deka e lista
    private List<Product> products;
    @Enumerated(EnumType.STRING) //vrednostite da se zacuvaat kako string
    private ShoppingCartStatus status;

    public ShoppingCart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.products = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }

    public ShoppingCart() {

    }
}
