package com.jpmorgan.digital.model;

import com.jpmorgan.digital.domain.ProductDomain;
import com.jpmorgan.digital.domain.SaleDomain;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * A test class to verify all operations on <code>ProductModel</code>
 *
 * @author Aman Chhabra
 */

public class ProductModelTest {

    ProductModel productModel = ProductModel.getProductModelInstance();

    @Test
    public void verifyUpdateWithNoProduct(){
        productModel.updateSales(ProductModel.UpdateType.valueOf("ADD"),2,"apple");
        Assert.assertEquals(0,productModel.getProducts().length);
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifyUpdateSalesWithInvalidUpdateType(){
        productModel.updateSales(ProductModel.UpdateType.valueOf(""),2,"apple");
    }

    @Test
    public void verifyUpdateSalesWithAddOperation(){
        ProductDomain product = productModel.getProduct("apple");
        SaleDomain sale = new SaleDomain();
        sale.setProductType(product);
        sale.setValue(10);
        product.registerSale(sale);
        productModel.updateSales(ProductModel.UpdateType.valueOf("ADD"),4,"apple");
        Assert.assertEquals(14,sale.getValue());
    }

    @Test
    public void verifyUpdateSalesWithSubtractOperation(){
        ProductDomain product = productModel.getProduct("banana");
        SaleDomain sale = new SaleDomain();
        sale.setProductType(product);
        sale.setValue(10);
        product.registerSale(sale);
        productModel.updateSales(ProductModel.UpdateType.valueOf("SUBTRACT"),2,"banana");
        Assert.assertEquals(8,sale.getValue());
    }


    @Test
    public void verifyUpdateSalesWithMultiplyOperation(){
        ProductDomain product = productModel.getProduct("banana");
        SaleDomain sale = new SaleDomain();
        sale.setProductType(product);
        sale.setValue(10);
        product.registerSale(sale);
        productModel.updateSales(ProductModel.UpdateType.valueOf("MULTIPLY"),2,"banana");
        Assert.assertEquals(20,sale.getValue());
    }

    @Test
    public void verifyProductModelSingleton(){
        ProductModel productModel2 = ProductModel.getProductModelInstance();
        Assert.assertEquals(productModel,productModel2);
    }

    @Test
    public void verifyGetProductAlwaysReturnProductDomain(){
        ProductDomain product = productModel.getProduct("PRODUCT");
        Assert.assertEquals("PRODUCT", product.getName());
        Assert.assertEquals(0,product.getTotalSales());
        Assert.assertEquals(0,product.getTotalValue());
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifyGetProductWhenProductNameNull(){
        ProductDomain product = productModel.getProduct(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifyGetProductWhenNameEmpty(){
        ProductDomain product = productModel.getProduct("");
    }


}
