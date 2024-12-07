package resource;

import java.util.HashMap;
import java.util.Map;

public class DataRequest {
    public Map<String, String> addItemCollection() {
        Map<String, String> dataCollection = new HashMap<>();

        dataCollection.put("addItem",
                """
                        {
                            "title": "Susu Ultra",
                            "description": "Fresh cow milk",
                            "category": "Dairy",
                            "price": 9.99,
                            "discountPercentage": 7.17,
                            "rating": 4.94,
                            "stock": 500,
                            "brand": "FreshFarm",
                            "images": [
                                "https://cdn.dummyjson.com/products/images/mens-watches/Rolex%20Cellini%20Date%20Black%20Dial/1.png",
                                "https://cdn.dummyjson.com/products/images/mens-watches/Rolex%20Cellini%20Date%20Black%20Dial/2.png"
                            ],
                            "thumbnail": "https://cdn.dummyjson.com/products/images/mens-watches/Rolex%20Cellini%20Date%20Black%20Dial/thumbnail.png"
                        }
                                                """);

        dataCollection.put("updateItem",
                """
                        {
                            "title": "Susu Ultra",
                            "description": "Fresh cow milk",
                            "category": "Dairy",
                            "price": 9.99,
                            "discountPercentage": 7.17,
                            "rating": 4.94,
                            "stock": 500,
                            "brand": "FreshFarm"
                        }
                        """);

        return dataCollection;
    }
}
