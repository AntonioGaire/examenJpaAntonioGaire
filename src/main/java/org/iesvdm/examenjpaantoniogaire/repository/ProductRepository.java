package org.iesvdm.examenjpaantoniogaire.repository;

import org.iesvdm.examenjpaantoniogaire.domain.Cart_item;
import org.iesvdm.examenjpaantoniogaire.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Page<Product> findAllByNameContainsIgnoreCaseOrderByIdProductAsc(Pageable page, String busqueda);
    public Page<Product> findAllByNameContainsIgnoreCaseOrderByIdProductDesc(Pageable page, String busqueda);

    /*
    @Query(value = "select ci from Cart_item ci join ci.user u where u.idUser= :id")
    public List<Cart_item> queryCarritobyIdUser(@Param("id") long id);
     */

    @Query(value = "select ci from Cart_item ci join ci.user u where u.idUser= :id")
    public List<Cart_item> queryCarritobyIdUser(@Param("id") long id);

    @Query(value = "select  sum(ci.product.price * ci.quantity) from Cart_item ci join ci.user u where u.idUser= :id")
    public BigDecimal queryTotalCarritobyIdUser(@Param("id") long id);

}
