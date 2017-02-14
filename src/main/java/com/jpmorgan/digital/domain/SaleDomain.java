package com.jpmorgan.digital.domain;

/**
 * Domain class for sale
 */
public class SaleDomain {

    /**
     * Product corresponding to this sale
     */
    private ProductDomain productType;

    /**
     * Value of the sale in pence
     */
    private int value;

    /**
     * No of occurences of Sales
     */
    private int noOfOccurences;

    /**
     * Check {@link com.jpmorgan.digital.domain.SaleDomain#productType} for more information
     */
    public ProductDomain getProductType() {
        return productType;
    }

    /**
     * Check {@link com.jpmorgan.digital.domain.SaleDomain#productType} for more information
     */
    public void setProductType(ProductDomain productType) {
        if(productType == null){
            throw  new IllegalArgumentException("Product Type can not be null");
        }
        this.productType = productType;
    }

    /**
     * Check {@link com.jpmorgan.digital.domain.SaleDomain#value} for more information
     */
    public int getValue() {
        return value;
    }

    /**
     * Check {@link com.jpmorgan.digital.domain.SaleDomain#value} for more information
     */
    public void setValue(int value) {
        if(value < 0){
            throw  new IllegalArgumentException("Value of sale can not be negative");
        }
        this.value = value;
    }

    /**
     * Check {@link com.jpmorgan.digital.domain.SaleDomain#noOfOccurences} for more information
     */
    public int getNoOfOccurences() {
        return noOfOccurences;
    }

    /**
     * Check {@link com.jpmorgan.digital.domain.SaleDomain#noOfOccurences} for more information
     */
    public void setNoOfOccurences(int noOfOccurences) {
        if(noOfOccurences <= 0){
            throw  new IllegalArgumentException("No of Sale occurence can not be zero or less than zero");
        }
        this.noOfOccurences = noOfOccurences;
    }
}
