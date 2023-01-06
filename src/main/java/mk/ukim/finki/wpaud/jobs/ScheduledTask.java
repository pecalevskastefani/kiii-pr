package mk.ukim.finki.wpaud.jobs;

import mk.ukim.finki.wpaud.service.ProductService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    private final ProductService productService;

    public ScheduledTask(ProductService productService) {
        this.productService = productService;
    }

    @Scheduled(fixedDelay = 5000) //na 5s ke imame izvrsuanje na metodot
    //cron="0012**? = sekoj den na pladne vo 12 ke se izvrsi
    public void refreshMaterializedView() {
        //this.productService.refreshMaterializedView();
    }
}
