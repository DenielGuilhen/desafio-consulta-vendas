package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleSallerDTO;
import com.devsuperior.dsmeta.dto.SallerMinDTO;
import com.devsuperior.dsmeta.entities.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SallerRepository extends JpaRepository <Seller, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SallerMinDTO(sel.name, SUM(s.amount)) " +
            "FROM Sale s JOIN s.seller sel " +
            "GROUP BY sel.name")
    Page<SallerMinDTO> findAmountWithSellerName(Pageable pageable);
}
