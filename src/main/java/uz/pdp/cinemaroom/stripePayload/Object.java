
package uz.pdp.cinemaroom.stripePayload;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Object {

    @SerializedName("after_expiration")
    private java.lang.Object afterExpiration;
    @SerializedName("allow_promotion_codes")
    private java.lang.Object allowPromotionCodes;
    @SerializedName("amount_subtotal")
    private Long amountSubtotal;
    @SerializedName("amount_total")
    private Long amountTotal;
    @SerializedName("automatic_tax")
    private AutomaticTax automaticTax;
    @SerializedName("billing_address_collection")
    private java.lang.Object billingAddressCollection;
    @SerializedName("cancel_url")
    private String cancelUrl;
    @SerializedName("client_reference_id")
    private String clientReferenceId;
    @Expose
    private java.lang.Object consent;
    @SerializedName("consent_collection")
    private java.lang.Object consentCollection;
    @Expose
    private String currency;
    @Expose
    private String customer;
    @SerializedName("customer_creation")
    private String customerCreation;
    @SerializedName("customer_details")
    private CustomerDetails customerDetails;
    @SerializedName("customer_email")
    private java.lang.Object customerEmail;
    @SerializedName("expires_at")
    private Long expiresAt;
    @Expose
    private String id;
    @Expose
    private Boolean livemode;
    @Expose
    private java.lang.Object locale;
    @Expose
    private Metadata metadata;
    @Expose
    private String mode;
    @Expose
    private String object;
    @SerializedName("payment_intent")
    private String paymentIntent;
    @SerializedName("payment_link")
    private java.lang.Object paymentLink;
    @SerializedName("payment_method_options")
    private PaymentMethodOptions paymentMethodOptions;
    @SerializedName("payment_method_types")
    private List<String> paymentMethodTypes;
    @SerializedName("payment_status")
    private String paymentStatus;
    @SerializedName("phone_number_collection")
    private PhoneNumberCollection phoneNumberCollection;
    @SerializedName("recovered_from")
    private java.lang.Object recoveredFrom;
    @SerializedName("setup_intent")
    private java.lang.Object setupIntent;
    @Expose
    private java.lang.Object shipping;
    @SerializedName("shipping_address_collection")
    private java.lang.Object shippingAddressCollection;
    @SerializedName("shipping_options")
    private List<java.lang.Object> shippingOptions;
    @SerializedName("shipping_rate")
    private java.lang.Object shippingRate;
    @Expose
    private String status;
    @SerializedName("submit_type")
    private java.lang.Object submitType;
    @Expose
    private java.lang.Object subscription;
    @SerializedName("success_url")
    private String successUrl;
    @SerializedName("total_details")
    private TotalDetails totalDetails;
    @Expose
    private java.lang.Object url;

    public java.lang.Object getAfterExpiration() {
        return afterExpiration;
    }

    public void setAfterExpiration(java.lang.Object afterExpiration) {
        this.afterExpiration = afterExpiration;
    }

    public java.lang.Object getAllowPromotionCodes() {
        return allowPromotionCodes;
    }

    public void setAllowPromotionCodes(java.lang.Object allowPromotionCodes) {
        this.allowPromotionCodes = allowPromotionCodes;
    }

    public Long getAmountSubtotal() {
        return amountSubtotal;
    }

    public void setAmountSubtotal(Long amountSubtotal) {
        this.amountSubtotal = amountSubtotal;
    }

    public Long getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(Long amountTotal) {
        this.amountTotal = amountTotal;
    }

    public AutomaticTax getAutomaticTax() {
        return automaticTax;
    }

    public void setAutomaticTax(AutomaticTax automaticTax) {
        this.automaticTax = automaticTax;
    }

    public java.lang.Object getBillingAddressCollection() {
        return billingAddressCollection;
    }

    public void setBillingAddressCollection(java.lang.Object billingAddressCollection) {
        this.billingAddressCollection = billingAddressCollection;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public String getClientReferenceId() {
        return clientReferenceId;
    }

    public void setClientReferenceId(String clientReferenceId) {
        this.clientReferenceId = clientReferenceId;
    }

    public java.lang.Object getConsent() {
        return consent;
    }

    public void setConsent(java.lang.Object consent) {
        this.consent = consent;
    }

    public java.lang.Object getConsentCollection() {
        return consentCollection;
    }

    public void setConsentCollection(java.lang.Object consentCollection) {
        this.consentCollection = consentCollection;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomerCreation() {
        return customerCreation;
    }

    public void setCustomerCreation(String customerCreation) {
        this.customerCreation = customerCreation;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    public java.lang.Object getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(java.lang.Object customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
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

    public java.lang.Object getLocale() {
        return locale;
    }

    public void setLocale(java.lang.Object locale) {
        this.locale = locale;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getPaymentIntent() {
        return paymentIntent;
    }

    public void setPaymentIntent(String paymentIntent) {
        this.paymentIntent = paymentIntent;
    }

    public java.lang.Object getPaymentLink() {
        return paymentLink;
    }

    public void setPaymentLink(java.lang.Object paymentLink) {
        this.paymentLink = paymentLink;
    }

    public PaymentMethodOptions getPaymentMethodOptions() {
        return paymentMethodOptions;
    }

    public void setPaymentMethodOptions(PaymentMethodOptions paymentMethodOptions) {
        this.paymentMethodOptions = paymentMethodOptions;
    }

    public List<String> getPaymentMethodTypes() {
        return paymentMethodTypes;
    }

    public void setPaymentMethodTypes(List<String> paymentMethodTypes) {
        this.paymentMethodTypes = paymentMethodTypes;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PhoneNumberCollection getPhoneNumberCollection() {
        return phoneNumberCollection;
    }

    public void setPhoneNumberCollection(PhoneNumberCollection phoneNumberCollection) {
        this.phoneNumberCollection = phoneNumberCollection;
    }

    public java.lang.Object getRecoveredFrom() {
        return recoveredFrom;
    }

    public void setRecoveredFrom(java.lang.Object recoveredFrom) {
        this.recoveredFrom = recoveredFrom;
    }

    public java.lang.Object getSetupIntent() {
        return setupIntent;
    }

    public void setSetupIntent(java.lang.Object setupIntent) {
        this.setupIntent = setupIntent;
    }

    public java.lang.Object getShipping() {
        return shipping;
    }

    public void setShipping(java.lang.Object shipping) {
        this.shipping = shipping;
    }

    public java.lang.Object getShippingAddressCollection() {
        return shippingAddressCollection;
    }

    public void setShippingAddressCollection(java.lang.Object shippingAddressCollection) {
        this.shippingAddressCollection = shippingAddressCollection;
    }

    public List<java.lang.Object> getShippingOptions() {
        return shippingOptions;
    }

    public void setShippingOptions(List<java.lang.Object> shippingOptions) {
        this.shippingOptions = shippingOptions;
    }

    public java.lang.Object getShippingRate() {
        return shippingRate;
    }

    public void setShippingRate(java.lang.Object shippingRate) {
        this.shippingRate = shippingRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.lang.Object getSubmitType() {
        return submitType;
    }

    public void setSubmitType(java.lang.Object submitType) {
        this.submitType = submitType;
    }

    public java.lang.Object getSubscription() {
        return subscription;
    }

    public void setSubscription(java.lang.Object subscription) {
        this.subscription = subscription;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public TotalDetails getTotalDetails() {
        return totalDetails;
    }

    public void setTotalDetails(TotalDetails totalDetails) {
        this.totalDetails = totalDetails;
    }

    public java.lang.Object getUrl() {
        return url;
    }

    public void setUrl(java.lang.Object url) {
        this.url = url;
    }

}
