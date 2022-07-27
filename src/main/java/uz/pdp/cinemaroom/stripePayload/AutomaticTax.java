
package uz.pdp.cinemaroom.stripePayload;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class AutomaticTax {

    @Expose
    private Boolean enabled;
    @Expose
    private java.lang.Object status;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public java.lang.Object getStatus() {
        return status;
    }

    public void setStatus(java.lang.Object status) {
        this.status = status;
    }

}
