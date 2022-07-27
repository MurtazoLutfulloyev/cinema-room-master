
package uz.pdp.cinemaroom.stripePayload;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Request {

    @Expose
    private java.lang.Object id;
    @SerializedName("idempotency_key")
    private java.lang.Object idempotencyKey;

    public java.lang.Object getId() {
        return id;
    }

    public void setId(java.lang.Object id) {
        this.id = id;
    }

    public java.lang.Object getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(java.lang.Object idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

}
