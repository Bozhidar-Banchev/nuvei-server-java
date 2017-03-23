package com.safecharge.retail.request;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.safecharge.retail.model.UserDetails;
import com.safecharge.retail.request.builder.SafechargeBuilder;
import com.safecharge.retail.util.APIConstants;
import com.safecharge.retail.util.Constants;
import com.safecharge.retail.util.ValidChecksum;
import com.safecharge.retail.util.ValidationUtil;

/**
 * Copyright (C) 2007-2017 SafeCharge International Group Limited.
 *
 * @author <a mailto:nikolad@safecharge.com>Nikola Dichev</a>
 * @since 3/21/2017
 */
@ValidChecksum(orderMappingName = Constants.ChecksumOrderMapping.ADD_CASHIER_CC_CARD) public class AddUPOCreditCardRequest extends SafechargeRequest {

    /**
     * A valid credit card number.
     */
    @NotNull(message = "ccCardNumber parameter is mandatory!") private String ccCardNumber;

    /**
     * One or two digit value that is the expiration month.
     */
    @NotNull(message = "ccExpMonth parameter is mandatory!") @Pattern(regexp = APIConstants.EXP_MONTH_REGEX,
                                                                      message = "Expiration month must have a valid value") private String ccExpMonth;

    /**
     * Two or four digit value that is the expiration year. When the value is two digits, the year is assumed to be 2000 + ccExpYear; ccExpMonth and ccExpYear must be a date that is after the current date. The year may not exceed 10 years in to the future.
     */
    @NotNull(message = "ccExpYear parameter is mandatory!") @Pattern(regexp = APIConstants.EXP_YEAR_REGEX,
                                                                     message = "Expiration year must 2 or 4 digits") private String ccExpYear;

    /**
     * The name of the credit card owner as it is written on the card.
     */
    @NotNull(message = "ccNameOnCard parameter is mandatory!") private String ccNameOnCard;

    /**
     * This parameter is a unique identifier for each customer generated by you.
     */
    @NotNull(message = "userTokenId parameter is mandatory!") private String userTokenId;

    /**
     * Billing address related to a user payment option. Since order can contain only one payment option billing address is part of the order parameters.
     */
    private UserDetails billingAddress;

    public static Builder builder() {
        return new Builder();
    }

    public String getCcCardNumber() {
        return ccCardNumber;
    }

    public void setCcCardNumber(String ccCardNumber) {
        this.ccCardNumber = ccCardNumber;
    }

    public String getCcExpMonth() {
        return ccExpMonth;
    }

    public void setCcExpMonth(String ccExpMonth) {
        this.ccExpMonth = ccExpMonth;
    }

    public String getCcExpYear() {
        return ccExpYear;
    }

    public void setCcExpYear(String ccExpYear) {
        this.ccExpYear = ccExpYear;
    }

    public String getCcNameOnCard() {
        return ccNameOnCard;
    }

    public void setCcNameOnCard(String ccNameOnCard) {
        this.ccNameOnCard = ccNameOnCard;
    }

    public String getUserTokenId() {
        return userTokenId;
    }

    public void setUserTokenId(String userTokenId) {
        this.userTokenId = userTokenId;
    }

    public UserDetails getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(UserDetails billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("AddUPOCreditCard{");
        sb.append("ccCardNumber='")
          .append(ccCardNumber)
          .append('\'');
        sb.append(", ccExpMonth='")
          .append(ccExpMonth)
          .append('\'');
        sb.append(", ccExpYear='")
          .append(ccExpYear)
          .append('\'');
        sb.append(", ccNameOnCard='")
          .append(ccNameOnCard)
          .append('\'');
        sb.append(", billingAddress=")
          .append(billingAddress);
        sb.append(", userTokenId='")
          .append(userTokenId)
          .append('\'');
        sb.append(", ")
          .append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    public static class Builder extends SafechargeBuilder<Builder> {

        private String ccCardNumber;
        private String ccExpMonth;
        private String ccExpYear;
        private String ccNameOnCard;
        private String userTokenId;
        private UserDetails billingAddress;

        public Builder addCcCardNumber(String ccCardNumber) {
            this.ccCardNumber = ccCardNumber;
            return this;
        }

        public Builder addCcExpMonth(String ccExpMonth) {
            this.ccExpMonth = ccExpMonth;
            return this;
        }

        public Builder addCCExpYear(String ccExpYear) {
            this.ccExpYear = ccExpYear;
            return this;
        }

        public Builder addCcNameOnCard(String ccNameOnCard) {
            this.ccNameOnCard = ccNameOnCard;
            return this;
        }

        public Builder addUserTokenId(String userTokenId) {
            this.userTokenId = userTokenId;
            return this;
        }

        public Builder addBillingAddress(UserDetails billingAddress) {
            this.billingAddress = billingAddress;
            return this;
        }

        public Builder addBillingAddress(String firstName, String lastName, String address, String phone, String zip, String city, String countryCode,
                String state, String email, String locale, String birthdate) {
            UserDetails billingAddress = new UserDetails();
            billingAddress.setFirstName(firstName);
            billingAddress.setLastName(lastName);
            billingAddress.setAddress(address);
            billingAddress.setPhone(phone);
            billingAddress.setZip(zip);
            billingAddress.setCity(city);
            billingAddress.setCountryCode(countryCode);
            billingAddress.setState(state);
            billingAddress.setEmail(email);
            billingAddress.setLocale(locale);
            billingAddress.setBirthdate(birthdate);
            return addBillingAddress(billingAddress);
        }

        @Override public SafechargeRequest build() throws ConstraintViolationException {
            AddUPOCreditCardRequest addUPOCreditCard = new AddUPOCreditCardRequest();
            addUPOCreditCard.setCcCardNumber(ccCardNumber);
            addUPOCreditCard.setCcExpMonth(ccExpMonth);
            addUPOCreditCard.setCcExpYear(ccExpYear);
            addUPOCreditCard.setCcNameOnCard(ccNameOnCard);
            addUPOCreditCard.setUserTokenId(userTokenId);
            addUPOCreditCard.setBillingAddress(billingAddress);
            return ValidationUtil.validate(super.build(addUPOCreditCard));
        }
    }
}
