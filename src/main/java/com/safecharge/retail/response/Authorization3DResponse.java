package com.safecharge.retail.response;

/**
 * Copyright (C) 2007-2017 SafeCharge International Group Limited.
 *
 * @author <a mailto:nikolad@safecharge.com>Nikola Dichev</a>
 * @since 2/15/2017
 */
public class Authorization3DResponse extends PaymentsResponse {

    private String paRequest;

    private String acsUrl;

    /**
     * @return the 3D secure request data for the card issuer/bank.
     */
    public String getPaRequest() {
        return paRequest;
    }

    /**
     * Sets the 3D secure request data for the card issuer/bank.
     *
     * @param paRequest
     */
    public void setPaRequest(String paRequest) {
        this.paRequest = paRequest;
    }

    /**
     * @return URL/endpoint used to redirect the customer to the card issuer/bank 3D secure verification page.
     */
    public String getAcsUrl() {
        return acsUrl;
    }

    /**
     * Sets the URL/endpoint used to redirect the customer to the card issuer/bank 3D secure verification page.
     *
     * @param acsUrl
     */
    public void setAcsUrl(String acsUrl) {
        this.acsUrl = acsUrl;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("Authorization3DResponse{");
        sb.append("paRequest='")
          .append(paRequest)
          .append('\'');
        sb.append(", acsUrl='")
          .append(acsUrl)
          .append('\'');
        sb.append(", ")
          .append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
