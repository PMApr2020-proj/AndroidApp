package mohamed.soliman.targetube.model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShoppingCart {
    @SerializedName("shoppinCartToOrderDTOList")
    @Expose
    private List<ShoppinCartline> shoppinCartToOrderDTOList = null;
    @SerializedName("endUserId")
    @Expose
    private Integer endUserId;

    public List<ShoppinCartline> getShoppinCartToOrderDTOList() {
        return shoppinCartToOrderDTOList;
    }

    public void setShoppinCartToOrderDTOList(List<ShoppinCartline> shoppinCartToOrderDTOList) {
        this.shoppinCartToOrderDTOList = shoppinCartToOrderDTOList;
    }

    public Integer getEndUserId() {
        return endUserId;
    }

    public void setEndUserId(Integer endUserId) {
        this.endUserId = endUserId;
    }
}
