package com.jpmorgan.digital.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * A test class to verify all operations on <code>ProductDomain</code>
 *
 * @author Aman Chhabra
 */
public class ProductDomainTest {

    ProductDomain multipleSaleProduct = new ProductDomain("newFruit");
    ProductDomain noSaleProduct = new ProductDomain("anotherFruit");

    @Before
    public void init(){
        SaleDomain sale1 = new SaleDomain();
        sale1.setNoOfOccurences(1);
        sale1.setValue(20);
        sale1.setProductType(multipleSaleProduct);
        multipleSaleProduct.registerSale(sale1);
        SaleDomain sale2 = new SaleDomain();
        sale2.setNoOfOccurences(2);
        sale2.setValue(10);
        sale2.setProductType(multipleSaleProduct);
        multipleSaleProduct.registerSale(sale2);
    }
    @Test
    public void verifyGetSalesWhenNoSaleSet(){
        List<SaleDomain> sales = noSaleProduct.getSales();
        Assert.assertEquals(0,sales.size());
    }

    @Test
    public void verifyGetTotalSale(){
        int totalSale = multipleSaleProduct.getTotalSales();
        Assert.assertEquals(3,totalSale);
    }

    @Test
    public void verifyGetTotalValue(){
        int totalValue = multipleSaleProduct.getTotalValue();
        Assert.assertEquals(40,totalValue);
    }

    @Test
    public void verifyGetSales(){
        List<SaleDomain> sales = noSaleProduct.getSales();
        Assert.assertEquals(0,sales.size());
    }

    @Test
    public void verifyGetTotalSalesWhenNoSaleSet(){
        int totalSale = noSaleProduct.getTotalSales();
        Assert.assertEquals(0,totalSale);
    }

    @Test
    public void verifyGetTotalValueWhenNoSaleSet(){
        int totalValue = noSaleProduct.getTotalValue();
        Assert.assertEquals(0,totalValue);
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifySetNameWithNull(){
        noSaleProduct.setName(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifySetNameWithEmpty(){
        noSaleProduct.setName("");
    }

    @Test
    public void verifySetName() {
        noSaleProduct.setName("someFruit");
        Assert.assertEquals("someFruit",noSaleProduct.getName());
    }


}
