package mohamed.soliman.targetube.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartLineUi {
    @SerializedName("cartLineUiList")
    @Expose
    private List<CartLineUiList> cartLineUiList = null;
    @SerializedName("user_id")
    @Expose
    private Integer userId;

    public List<CartLineUiList> getCartLineUiList() {
        return cartLineUiList;
    }

    public void setCartLineUiList(List<CartLineUiList> cartLineUiList) {
        this.cartLineUiList = cartLineUiList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
