package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleSallerDTO;
import com.devsuperior.dsmeta.dto.SallerMinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public Page<SaleSallerDTO> findSalesWithSellerName
			(@RequestParam(name = "name", defaultValue = "") String name,
			 @RequestParam(name = "minDate", required = false)
			 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate minDate,
			 @RequestParam(name = "maxDate", required = false)
			 @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate maxDate,
			 Pageable pageable) {

		return service.findSalesWithSellerName(name, minDate, maxDate, pageable);
	}

	@GetMapping(value = "/summary")
	public Page<SallerMinDTO> findAmountWithSellerName(Pageable pageable) {
		return service.findAmountWithSellerName(pageable);
	}
}
