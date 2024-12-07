package model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseItem {
    @JsonProperty
    public int id;

    @JsonProperty("title")
    public String title;

    @JsonProperty("description")
    public String description;

    @JsonProperty("category")
    public String category;

    @JsonProperty("price")
    public double price;

    @JsonProperty("discountPercentage")
    public double discountPercentage;

    @JsonProperty("rating")
    public float rating;

    @JsonProperty("stock")
    public int stock;

    @JsonProperty("brand")
    public String brand;

    @JsonProperty("images")
    public List<String> images; 
    
    @JsonProperty("thumbnail")
    public String thumbnail; 
}
