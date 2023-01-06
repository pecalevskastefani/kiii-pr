package mk.ukim.finki.wpaud.listeners;

import mk.ukim.finki.wpaud.model.events.ProductCreatedEvent;
import mk.ukim.finki.wpaud.service.ProductService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandlers {
    private final ProductService productService;

    public ProductEventHandlers(ProductService productService) {
        this.productService = productService;
    }

    @EventListener
    public void onProductCreated(ProductCreatedEvent event) {
        this.productService.refreshMaterializedView();//povikuvanje refres na materijalizaren pogled
    }
}
