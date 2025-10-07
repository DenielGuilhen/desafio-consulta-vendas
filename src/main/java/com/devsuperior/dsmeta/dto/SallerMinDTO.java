package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

public class SallerMinDTO {

    private String name;
    private double amount;

    public SallerMinDTO(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public SallerMinDTO(Sale sale, Seller seller){
        name = seller.getName();
        amount = sale.getAmount();
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }
}
