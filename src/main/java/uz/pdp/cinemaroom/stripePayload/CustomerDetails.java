
package uz.pdp.cinemaroom.stripePayload;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class CustomerDetails {

    @Expose
    private Address address;
    @Expose
    private String email;
    @Expose
    private String name;
    @Expose
    private java.lang.Object phone;
    @SerializedName("tax_exempt")
    private String taxExempt;
    @SerializedName("tax_ids")
    private List<java.lang.Object> taxIds;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.lang.Object getPhone() {
        return phone;
    }

    public void setPhone(java.lang.Object phone) {
        this.phone = phone;
    }

    public String getTaxExempt() {
        return taxExempt;
    }

    public void setTaxExempt(String taxExempt) {
        this.taxExempt = taxExempt;
    }

    public List<java.lang.Object> getTaxIds() {
        return taxIds;
    }

    public void setTaxIds(List<java.lang.Object> taxIds) {
        this.taxIds = taxIds;
    }

}
