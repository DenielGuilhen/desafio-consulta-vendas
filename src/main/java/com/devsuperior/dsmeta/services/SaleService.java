package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.SaleSallerDTO;
import com.devsuperior.dsmeta.dto.SallerMinDTO;
import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.repositories.SallerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	@Autowired
	private SallerRepository sallerRepository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleSallerDTO> findSalesWithSellerName(String name, LocalDate minDate, LocalDate maxDate,
													   Pageable pageable) {
		if (maxDate == null){
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}

		if (minDate == null){
			minDate = maxDate.minusYears(1L);
		}

		return repository.findSalesWithSellerName(name, minDate, maxDate, pageable);
	}

	public Page<SallerMinDTO> findAmountWithSellerName(Pageable pageable){
		return sallerRepository.findAmountWithSellerName(pageable);
	}


	private void copyDtoToEntity(SaleSallerDTO dto, Sale sale, Seller seller) {
		sale.setId(dto.getId());
		sale.setDate(dto.getDate());
		sale.setAmount(dto.getAmount());
		seller.setName(dto.getName());
	}
}
