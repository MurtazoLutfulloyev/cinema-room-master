
package uz.pdp.cinemaroom.stripePayload;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Address {

    @Expose
    private java.lang.Object city;
    @Expose
    private String country;
    @Expose
    private java.lang.Object line1;
    @Expose
    private java.lang.Object line2;
    @SerializedName("postal_code")
    private java.lang.Object postalCode;
    @Expose
    private java.lang.Object state;

    public java.lang.Object getCity() {
        return city;
    }

    public void setCity(java.lang.Object city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public java.lang.Object getLine1() {
        return line1;
    }

    public void setLine1(java.lang.Object line1) {
        this.line1 = line1;
    }

    public java.lang.Object getLine2() {
        return line2;
    }

    public void setLine2(java.lang.Object line2) {
        this.line2 = line2;
    }

    public java.lang.Object getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(java.lang.Object postalCode) {
        this.postalCode = postalCode;
    }

    public java.lang.Object getState() {
        return state;
    }

    public void setState(java.lang.Object state) {
        this.state = state;
    }

}
