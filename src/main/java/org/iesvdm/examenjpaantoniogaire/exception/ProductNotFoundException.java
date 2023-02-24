package org.iesvdm.examenjpaantoniogaire.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id) {
        super("Not found Product with id: " + id);
    }
}
