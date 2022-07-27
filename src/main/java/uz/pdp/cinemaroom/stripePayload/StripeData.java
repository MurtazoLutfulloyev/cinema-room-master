
package uz.pdp.cinemaroom.stripePayload;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class StripeData {

    @Expose
    private java.lang.Object account;
    @SerializedName("api_version")
    private String apiVersion;
    @Expose
    private Long created;
    @Expose
    private Data data;
    @Expose
    private String id;
    @Expose
    private Boolean livemode;
    @Expose
    private String object;
    @SerializedName("pending_webhooks")
    private Long pendingWebhooks;
    @Expose
    private Request request;
    @Expose
    private String type;

    public java.lang.Object getAccount() {
        return account;
    }

    public void setAccount(java.lang.Object account) {
        this.account = account;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getLivemode() {
        return livemode;
    }

    public void setLivemode(Boolean livemode) {
        this.livemode = livemode;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Long getPendingWebhooks() {
        return pendingWebhooks;
    }

    public void setPendingWebhooks(Long pendingWebhooks) {
        this.pendingWebhooks = pendingWebhooks;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
