package mk.finki.ukim.wpaud.listeners;

import mk.finki.ukim.wpaud.model.events.ProductCreatedEvent;
import mk.finki.ukim.wpaud.service.ProductService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandler {

    private final ProductService productService;

    public ProductEventHandler(ProductService productService) {
        this.productService = productService;
    }

    @EventListener
    public void onProductCreated(ProductCreatedEvent event) {
        this.productService.refreshMaterializedView();
    }

}
