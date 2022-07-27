
package uz.pdp.cinemaroom.stripePayload;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Data {

    @Expose
    private uz.pdp.cinemaroom.stripePayload.Object object;
    @SerializedName("previous_attributes")
    private java.lang.Object previousAttributes;

    public uz.pdp.cinemaroom.stripePayload.Object getObject() {
        return object;
    }

    public void setObject(uz.pdp.cinemaroom.stripePayload.Object object) {
        this.object = object;
    }

    public java.lang.Object getPreviousAttributes() {
        return previousAttributes;
    }

    public void setPreviousAttributes(java.lang.Object previousAttributes) {
        this.previousAttributes = previousAttributes;
    }

}
