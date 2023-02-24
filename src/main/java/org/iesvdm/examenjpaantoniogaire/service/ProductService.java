package org.iesvdm.examenjpaantoniogaire.service;

import org.iesvdm.examenjpaantoniogaire.domain.Cart_item;
import org.iesvdm.examenjpaantoniogaire.domain.Product;
import org.iesvdm.examenjpaantoniogaire.exception.ProductNotFoundException;
import org.iesvdm.examenjpaantoniogaire.repository.Cart_itemRepository;
import org.iesvdm.examenjpaantoniogaire.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final Cart_itemRepository cartItemRepository;

    public ProductService(ProductRepository productRepository, Cart_itemRepository cartItemRepository){
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;}

    public List<Product> all(){return this.productRepository.findAll();}
    public Product one(long id){return this.productRepository.findById(id).get();}

    public Product save(Product product){return this.productRepository.save(product);}

    public Product replace(Long id, Product product){
        return this.productRepository.findById(id).map( p -> (id.equals(product.getIdProduct())  ?
                        this.productRepository.save(product) : null))
                        .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public void delete(Long id) {
        this.productRepository.findById(id).map(p -> {this.productRepository.delete(p);
                    return p;})
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Map<String, Object> all(int page, int size, String buscar, String ordenar){

        Pageable pages = PageRequest.of(page, size, Sort.by("idProduct").ascending());;

        Page<Product> productPage = this.productRepository.findAllByNameContainsIgnoreCaseOrderByIdProductAsc(pages, buscar);

        if(ordenar.toLowerCase().equals("desc")){
            productPage = this.productRepository.findAllByNameContainsIgnoreCaseOrderByIdProductDesc(pages, buscar);
        }

        Map<String, Object> response = new HashMap<>();

        response.put("products", productPage.getContent());
        response.put("currentPage", productPage.getNumber());
        response.put("totalItems", productPage.getTotalElements());
        response.put("totalPages", productPage.getTotalPages());

        return response;
    }

    //public List<Cart_item> all(Long id){return this.productRepository.queryCarritobyIdUser(id);}
    public Map<BigDecimal, List<Cart_item>> all(Long id){
        //Object[] response = new Array[];
        Map<BigDecimal, List<Cart_item>> response = new HashMap<>();
        response.put(this.productRepository.queryTotalCarritobyIdUser(id), this.productRepository.queryCarritobyIdUser(id));
        return response;
    }

    public Cart_item save(Cart_item cartItem){return this.cartItemRepository.save(cartItem);}
}
