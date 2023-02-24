package org.iesvdm.examenjpaantoniogaire.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.examenjpaantoniogaire.domain.Cart_item;
import org.iesvdm.examenjpaantoniogaire.domain.Product;
import org.iesvdm.examenjpaantoniogaire.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){this.productService = productService;}

    @GetMapping(value = {"","/"}, params = {"!buscar", "!ordenar", "!page", "!size"})
    public List<Product> all() {
        log.info("Accediendo a todos los productos");
        return this.productService.all();
    }

    @GetMapping(value = {"","/"}, params = {"buscar", "ordenar", "page", "size"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam("buscar") Optional<String> buscarOptional, @RequestParam("ordenar")Optional<String> ordenarOptional,
                                                   @RequestParam(value="page", defaultValue="0") int page, @RequestParam(value = "size", defaultValue = "3")int size){

        log.info("Accediendo a todos los productos con busqueda y paginacion");

        Map<String, Object> responseAll = this.productService.all(page, size, buscarOptional.get(), ordenarOptional.get());
        return ResponseEntity.ok(responseAll);
    }

    @PostMapping({"","/"})
    public Product newCategoria(@RequestBody Product product) {
        return this.productService.save(product);
    }

    @GetMapping(value = {"{id}","{id}/"})
    public Product one(@PathVariable("id") Long id) {
        log.info("Accediendo a un productos");
        return this.productService.one(id);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return this.productService.replace(id, product);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        this.productService.delete(id);
    }
/*
    @GetMapping("/carrito/{id}")

    public List<Cart_item> all(@PathVariable("id") Long id){
        return this.productService.all(id);
    }
 */
    @GetMapping("/carrito/{id}")
    public Map<BigDecimal, List<Cart_item>> all(@PathVariable("id") Long id){
        return this.productService.all(id);
    }

    @PostMapping("/carrito/{id}")
    public Cart_item newCart_Item(@PathVariable("id") Long id, @RequestBody Cart_item cartItem){
        return this.productService.save(cartItem);
    }
}
