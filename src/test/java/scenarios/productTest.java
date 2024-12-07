package scenarios;

import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import apiengine.Assertion;
import apiengine.Endpoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.request.AddItem;
import model.request.UpdateItem;
import model.response.ResponseItem;
import resource.DataRequest;

public class productTest {
    RequestSpecification requestSpecification;
    private Endpoints endpoints;
    private DataRequest dataRequest;
    private String json;
    int productId;
    private Assertion assertion;
    private ResponseItem responseItem;
    private AddItem addItem;
    private UpdateItem updateItem;

    /*
     * https://dummyjson.com/docs/products
     * 1. User dapat melihat list of objects (Get all products)
     * 2. User ingin menambahkan data baru (Add a new product)
     * 3. Setelah datanya sudah berhasil di insert, user ingin update datanya
     * (Update a product)
     * 4. Diakhir user ingin delete data yang baru saja dia add (Delete a product)
     * 5. Makesure user tidak bisa lagi akses datanya, karena datanya sudah berhasil
     * terhapus (Get a single product)
     */

    @BeforeClass
    public void setUp() {
        endpoints = new Endpoints();
    }

    @Test
    public void GetAllObjects() {
        Response response = endpoints.getAllProducts();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void AddNewProduct() throws JsonMappingException, JsonProcessingException {
        dataRequest = new DataRequest();

        for (Map.Entry<String, String> entry : dataRequest.addItemCollection().entrySet()) {
            if (entry.getKey().equals("addItem")) {
                json = entry.getValue();
                break;
            }
        }

        Response response = endpoints.addNewProduct(json);

        JsonPath jsonPath = response.jsonPath();
        responseItem = jsonPath.getObject("", ResponseItem.class);

        ObjectMapper objectMapper = new ObjectMapper();
        addItem = objectMapper.readValue(json, AddItem.class);

        assertion = new Assertion();
        Assert.assertEquals(response.getStatusCode(), 201);
        assertion.assertAddItem(responseItem, addItem);

        productId = responseItem.id;  
    }

    @Test(dependsOnMethods = { "AddNewProduct" }, priority = 1)
    public void UpdateProduct() throws JsonMappingException, JsonProcessingException {
        dataRequest = new DataRequest();

        for (Map.Entry<String, String> entry : dataRequest.addItemCollection().entrySet()) {
            if (entry.getKey().equals("updateItem")) {
                json = entry.getValue();
                break;
            }
        }

        Response response = endpoints.updateProduct(1, json);

        ObjectMapper objectMapper = new ObjectMapper();
        updateItem = objectMapper.readValue(json, UpdateItem.class);

        JsonPath jsonPath = response.jsonPath();
        responseItem = jsonPath.getObject("", ResponseItem.class);

        assertion = new Assertion();
        Assert.assertEquals(response.getStatusCode(), 200);
        assertion.assertUpdateItem(responseItem, updateItem);
    }

    @Test(dependsOnMethods = { "UpdateProduct" }, priority = 2)
    public void DeleteProduct() {
        Response response = endpoints.deleteProduct(1);

        JSONObject jsonResponse = new JSONObject(response.asString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(jsonResponse.getBoolean("isDeleted"));
    }

    @Test(dependsOnMethods = { "DeleteProduct" }, priority = 3)
    public void GetASingleProduct() {
        Response response = endpoints.getASingleProduct(productId);

        JSONObject jsonResponse = new JSONObject(response.asString());

        Assert.assertEquals(response.getStatusCode(), 404);

        String expectedMessage = String.format("Product with id '%d' not found", productId);
        Assert.assertEquals(expectedMessage, jsonResponse.getString("message"));
    }
}
