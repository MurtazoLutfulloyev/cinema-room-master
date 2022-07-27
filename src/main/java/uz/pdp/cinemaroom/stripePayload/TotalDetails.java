
package uz.pdp.cinemaroom.stripePayload;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class TotalDetails {

    @SerializedName("amount_discount")
    private Long amountDiscount;
    @SerializedName("amount_shipping")
    private Long amountShipping;
    @SerializedName("amount_tax")
    private Long amountTax;

    public Long getAmountDiscount() {
        return amountDiscount;
    }

    public void setAmountDiscount(Long amountDiscount) {
        this.amountDiscount = amountDiscount;
    }

    public Long getAmountShipping() {
        return amountShipping;
    }

    public void setAmountShipping(Long amountShipping) {
        this.amountShipping = amountShipping;
    }

    public Long getAmountTax() {
        return amountTax;
    }

    public void setAmountTax(Long amountTax) {
        this.amountTax = amountTax;
    }

}
