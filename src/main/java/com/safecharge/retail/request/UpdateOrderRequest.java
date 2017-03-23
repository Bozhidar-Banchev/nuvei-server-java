package com.safecharge.retail.request;

import javax.validation.constraints.Size;

import com.safecharge.retail.request.builder.SafechargeOrderBuilder;
import com.safecharge.retail.util.Constants;
import com.safecharge.retail.util.ValidChecksum;
import com.safecharge.retail.util.ValidationUtil;

/**
 * Copyright (C) 2007-2017 SafeCharge International Group Limited.
 *
 * @author <a mailto:nikolad@safecharge.com>Nikola Dichev</a>
 * @since 2/17/2017
 */
@ValidChecksum(orderMappingName = Constants.ChecksumOrderMapping.API_GENERIC_CHECKSUM_MAPPING)
public class UpdateOrderRequest extends SafechargeOrderDetailsRequest implements SafechargeOrderRequest {

    @Size(max = 45,
          message = "orderId size must be up to 45 characters long!") private String orderId;

    public static Builder builder() {
        return new Builder();
    }

    public String getOrderId() {
        return orderId;
    }

    @Override public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("UpdateOrderRequest{");
        sb.append("orderId='")
          .append(orderId)
          .append('\'');
        sb.append(", ")
          .append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    public static class Builder extends SafechargeOrderBuilder<Builder> {

        private String orderId;

        public Builder addOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        @Override public SafechargeRequest build() {
            UpdateOrderRequest updateOrderRequest = new UpdateOrderRequest();
            updateOrderRequest.setOrderId(orderId);
            return ValidationUtil.validate(super.build(updateOrderRequest));
        }
    }
}
