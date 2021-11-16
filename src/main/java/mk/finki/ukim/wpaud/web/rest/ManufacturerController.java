package mk.finki.ukim.wpaud.web.rest;

import mk.finki.ukim.wpaud.model.Manufacturer;
import mk.finki.ukim.wpaud.service.ManufacturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public List<Manufacturer> findAll(){
        return manufacturerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> findById(@PathVariable Long id){
        return manufacturerService.findById(id)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

   @PostMapping("/add")
    public ResponseEntity<Manufacturer> save(@RequestParam String name, @RequestParam String address) {
        return manufacturerService.save(name, address)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Manufacturer> deleteById(@PathVariable Long id) {
        if (manufacturerService.deleteById(id)) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
