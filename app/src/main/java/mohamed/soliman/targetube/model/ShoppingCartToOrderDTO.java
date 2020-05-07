package mohamed.soliman.targetube.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShoppingCartToOrderDTO {

    @SerializedName("shoppingCartToOrderDTO")
    @Expose
    private ShoppingCart shoppingCartToOrderDTO;
    @SerializedName("payment")
    @Expose
    private Payment payment;

    public ShoppingCart getShoppingCartToOrderDTO() {
        return shoppingCartToOrderDTO;
    }

    public void setShoppingCartToOrderDTO(ShoppingCart shoppingCartToOrderDTO) {
        this.shoppingCartToOrderDTO = shoppingCartToOrderDTO;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

}