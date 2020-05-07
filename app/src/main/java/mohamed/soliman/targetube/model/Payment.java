package mohamed.soliman.targetube.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payment {

    @SerializedName("nameOnCard")
    @Expose
    private String nameOnCard;
    @SerializedName("paymentType")
    @Expose
    private Integer paymentType;
    @SerializedName("cardNo")
    @Expose
    private String cardNo;
    @SerializedName("expiryDate")
    @Expose
    private String expiryDate;
    @SerializedName("cvv")
    @Expose
    private String cvv;

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

}