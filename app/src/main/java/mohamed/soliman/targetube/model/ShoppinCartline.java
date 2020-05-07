package mohamed.soliman.targetube.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShoppinCartline {
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
    @SerializedName("sellerId")
    @Expose
    private Integer sellerId;

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

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

}
