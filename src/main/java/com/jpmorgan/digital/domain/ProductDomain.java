package com.jpmorgan.digital.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Domain class for Product
 */
public class ProductDomain {

    /**
     * Constructor to initialize the object with product name
     * @param name
     */
    public ProductDomain(String name) {
        setName(name);
        setSales(new ArrayList<SaleDomain>());
    }

    /**
     * Name of the product
     */
    private String name;

    /**
     * List of all the sales of the product
     */
    private List<SaleDomain> sales;

    /**
     * Check {@link com.jpmorgan.digital.domain.ProductDomain#name} for more information
     */
    public String getName() {
        return name;
    }

    /**
     * Check {@link com.jpmorgan.digital.domain.ProductDomain#name} for more information
     */
    public void setName(String name) {
        if(name == null || name.isEmpty()){
            throw  new IllegalArgumentException("Name can not be empty");
        }
        this.name = name;
    }

    /**
     * Check {@link com.jpmorgan.digital.domain.ProductDomain#sales} for more information
     */
    public List<SaleDomain> getSales() {
        if(sales == null) {
            sales = new ArrayList<SaleDomain>();
        }
        return sales;
    }

    /**
     * Check {@link com.jpmorgan.digital.domain.ProductDomain#sales} for more information
     */
    public void setSales(List<SaleDomain> sales) {
        this.sales = sales;
    }

    /**
     * Function to register a sale with the product
     *
     * @param sale Sale of product
     * @since 1.0
     */
    public void registerSale(SaleDomain sale){
        getSales().add(sale);
    }

    /**
     * Function to get total value of all the sales for this product
     *
     * @return int Total value of all sales of the product in pence
     * @since 1.0
     */
    public int getTotalValue(){
        int totalValue = 0;
        for(SaleDomain sale:sales) {
            totalValue += sale.getValue()*sale.getNoOfOccurences();
        }
        return  totalValue;
    }

    /**
     * Function to get total number of sales for this product
     *
     * @return int Total number of sales for this product
     * @since 1.0
     */
    public int getTotalSales(){
        int totalSales = 0;
        for(SaleDomain sale:sales) {
            totalSales += sale.getNoOfOccurences();
        }
        return  totalSales;
    }
}
