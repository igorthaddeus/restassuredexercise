package apiengine;

import com.restassuredapi.constants.Constants;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Endpoints {
    public static RequestSpecification requestSpecification;

    public Endpoints() {
        RestAssured.baseURI = Constants.BASE_URL + "/products";
        requestSpecification = RestAssured.given();
    }

    public Response getAllProducts() {
        Response response = requestSpecification.get();

        return response;
    }

    public Response addNewProduct(String body) {
        Response response = requestSpecification
                .body(body)
                .contentType("application/json")
                .post("/add");

        return response;
    }

    public Response updateProduct(int productId, String body) {
        Response response = requestSpecification
                .body(body)
                .contentType("application/json")
                .put("/" + productId);

        return response;
    }

    public Response deleteProduct(int productId) {
        Response response = requestSpecification
                .delete("/" + productId);

        return response;
    }

    public Response getASingleProduct(int productId) {
        Response response = requestSpecification
                .get("/" + productId);

        return response;

    }

    public Response searchProducts(String title) {
        Response response = requestSpecification
                .queryParam("q", title)
                .get("/search");

        return response;
    }
}
