package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleSallerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

//    @Query(nativeQuery = true, value = "SELECT sales.id, sales.date, sales.amount, seller.name "
//            + "FROM sales "
//            + "INNER JOIN seller ON sales.seller_id = seller.id;")
//    Page<SaleSellerProjection> searchWithProjection(String id, String date, String amount, String name,
//                                                    Pageable pageable);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSallerDTO(s.id, s.date, s.amount, sel.name) " +
            "FROM Sale s JOIN s.seller sel " +
            "WHERE UPPER(sel.name) LIKE UPPER(CONCAT('%', :name, '%')) " +
            "AND s.date BETWEEN :minDate AND :maxDate")
    Page<SaleSallerDTO> findSalesWithSellerName(
            String name, LocalDate minDate, LocalDate maxDate,
            Pageable pageable);
}