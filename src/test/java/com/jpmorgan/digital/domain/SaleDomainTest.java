package com.jpmorgan.digital.domain;

import com.jpmorgan.digital.model.ProductModel;
import org.junit.Assert;
import org.junit.Test;

/**
 * A test class to verify all operations on <code>SaleDomain</code>
 *
 * @author Aman Chhabra
 */

public class SaleDomainTest {

    SaleDomain saleDomain = new SaleDomain();

    @Test
    public void verifySetProductType(){
        ProductDomain product = new ProductDomain("newFruit");
        saleDomain.setProductType(product);
        Assert.assertEquals(product,saleDomain.getProductType());
    }

    @Test (expected = IllegalArgumentException.class)
      public void verifySetProductTypeWithNull(){
        saleDomain.setProductType(null);
    }

    @Test
    public void verifySetValue(){
        saleDomain.setValue(10);
        Assert.assertEquals(10, saleDomain.getValue());
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifySetValueWithNegative(){
        saleDomain.setValue(-10);
    }

    @Test
    public void verifySetNoOfOccurences(){
        saleDomain.setNoOfOccurences(10);
        Assert.assertEquals(10,saleDomain.getNoOfOccurences());
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifySetNoOfOccurencesWithZero(){
        saleDomain.setNoOfOccurences(0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifySetNoOfOccurencesWithNegative(){
        saleDomain.setNoOfOccurences(-10);
    }
}
