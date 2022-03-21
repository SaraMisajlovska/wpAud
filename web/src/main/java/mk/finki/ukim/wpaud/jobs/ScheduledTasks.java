package mk.finki.ukim.wpaud.jobs;

import mk.finki.ukim.wpaud.service.ProductService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final ProductService productService;

    public ScheduledTasks(ProductService productService) {
        this.productService = productService;
    }


    //look up  cron exp
    @Scheduled(fixedDelay = 5000)
    public void refreshMaterializedView(){
       // this.productService.refreshMaterializedView();
    }

}
