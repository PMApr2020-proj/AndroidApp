package mohamed.soliman.targetube.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartLineUiList {

    @SerializedName("productId")
    @Expose
    private Integer productId;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("productDesc")
    @Expose
    private String productDesc;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("imageLink")
    @Expose
    private String imageLink;
    @SerializedName("sellerId")
    @Expose
    private Integer sellerId;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}