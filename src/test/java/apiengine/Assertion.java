package apiengine;

import org.testng.Assert;

import model.request.AddItem;
import model.request.UpdateItem;
import model.response.ResponseItem;

public class Assertion {
    public void assertAddItem(ResponseItem responseItem, AddItem addItem) {
        Assert.assertNotNull(responseItem.id);
        Assert.assertEquals(responseItem.title, addItem.title);
        Assert.assertEquals(responseItem.description, addItem.description);
        Assert.assertEquals(responseItem.category, addItem.category);
        Assert.assertEquals(responseItem.price, addItem.price);
        Assert.assertEquals(responseItem.discountPercentage, addItem.discountPercentage);
        Assert.assertEquals(responseItem.rating, addItem.rating);
        Assert.assertEquals(responseItem.stock, addItem.stock);
        Assert.assertEquals(responseItem.brand, addItem.brand);
    }

    public void assertUpdateItem(ResponseItem responseItem, UpdateItem updateItem) {
        Assert.assertNotNull(responseItem.id);
        Assert.assertEquals(responseItem.title, updateItem.title);
        Assert.assertEquals(responseItem.description, updateItem.description);
        Assert.assertEquals(responseItem.category, updateItem.category);
        Assert.assertEquals(responseItem.price, updateItem.price);
        Assert.assertEquals(responseItem.discountPercentage, updateItem.discountPercentage);
        Assert.assertEquals(responseItem.rating, updateItem.rating);
        Assert.assertEquals(responseItem.stock, updateItem.stock);
        Assert.assertEquals(responseItem.brand, updateItem.brand);
    }
}
