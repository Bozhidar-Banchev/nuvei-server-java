/*
 * Copyright (C) 2007 - 2023 SafeCharge International Group Limited.
 */

package com.safecharge.request;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.safecharge.model.UserDetailsCashier;
import com.safecharge.request.builder.SafechargeBuilder;
import com.safecharge.util.APIConstants;
import com.safecharge.util.AddressUtils;
import com.safecharge.util.Constants;
import com.safecharge.util.ValidChecksum;
import com.safecharge.util.ValidationUtils;

/**
 * <p>
 * Request to add Credit/Debit card User Payment Option(UPO) to a User.
 * </p>
 * This goal of this request is to add a credit card UPO for a specific user according to their Temporary card token and User Token ID.
 * Once a credit card UPO is added to the user’s list of UPOs, the credit card is displayed in the payment page.
 *
 * @author <a href="mailto:bozhidarsh@safecharge.com">Bozhidar Shumanov</a>
 * @since 3/21/2017
 */
@ValidChecksum(orderMappingName = Constants.ChecksumOrderMapping.ADD_CASHIER_CC_CARD_DATA)
public class AddUPOCreditCardByTokenRequest extends SafechargeRequest {

    /**
     * A hashed value of the credit card number.
     */
    @NotNull(message = "ccToken parameter is mandatory!")
    private String ccToken;

    /**
     * The brand of the credit card, i.e. Visa, MasterCard, etc.
     */
    @NotNull(message = "brand parameter is mandatory!")
    private String brand;

    /**
     * A unique identifying code for the credit card.
     */
    @NotNull(message = "uniqueCC parameter is mandatory!")
    private String uniqueCC;

    /**
     * The credit card’s bin number.
     */
    @NotNull(message = "bin parameter is mandatory!")
    private String bin;

    /**
     * The last four digits of the credit card number.
     */
    @NotNull(message = "last4Digits parameter is mandatory!")
    private String last4Digits;

    /**
     * The last deposit use.
     */
    private String lastDepositUse;

    /**
     * The last deposit success.
     */
    private String lastDepositSuccess;

    /**
     * The last withdrawal use.
     */
    private String lastWithdrawalUse;

    /**
     * The last withdrawal success.
     */
    private String lastWithdrawalSuccess;

    /**
     * The registration date.
     */
    private String registrationDate;

    /**
     * The expiry date.
     */
    private String expiryDate;

    /**
     * One or two digit value that is the expiration month.
     */
    @NotNull(message = "ccExpMonth parameter is mandatory!")
    @Pattern(regexp = APIConstants.EXP_MONTH_REGEX, message = "Expiration month must have a valid value")
    private String ccExpMonth;

    /**
     * Two or four digit value that is the expiration year.
     * When the value is two digits, the year is assumed to be 2000 + ccExpYear; ccExpMonth and ccExpYear must be a date that is after the current date.
     * The year may not exceed 10 years in to the future.
     */
    @NotNull(message = "ccExpYear parameter is mandatory!")
    @Pattern(regexp = APIConstants.EXP_YEAR_REGEX, message = "Expiration year must 2 or 4 digits")
    private String ccExpYear;

    /**
     * The name of the credit card owner as it is written on the card.
     */
    @NotNull(message = "ccNameOnCard parameter is mandatory!")
    private String ccNameOnCard;

    /**
     * This parameter is a unique identifier for each customer generated by you.
     */
    @NotNull(message = "userTokenId parameter is mandatory!")
    private String userTokenId;

    /**
     * Billing address related to a user payment option. Since order can contain only one payment option billing address is part of the order parameters.
     */
    private UserDetailsCashier billingAddress;

    public static Builder builder() {
        return new Builder();
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

    public UserDetailsCashier getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(UserDetailsCashier billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getCcToken() {
        return ccToken;
    }

    public void setCcToken(String ccToken) {
        this.ccToken = ccToken;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUniqueCC() {
        return uniqueCC;
    }

    public void setUniqueCC(String uniqueCC) {
        this.uniqueCC = uniqueCC;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getLast4Digits() {
        return last4Digits;
    }

    public void setLast4Digits(String last4Digits) {
        this.last4Digits = last4Digits;
    }

    public String getLastDepositUse() {
        return lastDepositUse;
    }

    public void setLastDepositUse(String lastDepositUse) {
        this.lastDepositUse = lastDepositUse;
    }

    public String getLastDepositSuccess() {
        return lastDepositSuccess;
    }

    public void setLastDepositSuccess(String lastDepositSuccess) {
        this.lastDepositSuccess = lastDepositSuccess;
    }

    public String getLastWithdrawalUse() {
        return lastWithdrawalUse;
    }

    public void setLastWithdrawalUse(String lastWithdrawalUse) {
        this.lastWithdrawalUse = lastWithdrawalUse;
    }

    public String getLastWithdrawalSuccess() {
        return lastWithdrawalSuccess;
    }

    public void setLastWithdrawalSuccess(String lastWithdrawalSuccess) {
        this.lastWithdrawalSuccess = lastWithdrawalSuccess;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AddUPOCreditCard{");
        sb.append(", ccToken='")
                .append(ccToken)
                .append('\'');
        sb.append(", brand='")
                .append(brand)
                .append('\'');
        sb.append(",uniqueCC ='")
                .append(uniqueCC)
                .append('\'');
        sb.append(", bin='")
                .append(bin)
                .append('\'');
        sb.append(", last4Digits='")
                .append(last4Digits)
                .append('\'');
        sb.append(", lastDepositUse='")
                .append(lastDepositUse)
                .append('\'');
        sb.append(", lastDepositSuccess='")
                .append(lastDepositSuccess)
                .append('\'');
        sb.append(", lastWithdrawalUse='")
                .append(lastWithdrawalUse)
                .append('\'');
        sb.append(", lastWithdrawalSuccess='")
                .append(lastWithdrawalSuccess)
                .append('\'');
        sb.append(", registrationDate='")
                .append(registrationDate)
                .append('\'');
        sb.append(", expiryDate='")
                .append(expiryDate)
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

        private String ccToken;
        private String brand;
        private String uniqueCC;
        private String bin;
        private String last4Digits;
        private String lastDepositUse;
        private String lastDepositSuccess;
        private String lastWithdrawalUse;
        private String lastWithdrawalSuccess;
        private String registrationDate;
        private String expiryDate;
        private String ccExpMonth;
        private String ccExpYear;
        private String ccNameOnCard;
        private String userTokenId;
        private UserDetailsCashier billingAddress;

        public Builder addCcToken(String ccToken) {
            this.ccToken = ccToken;
            return this;
        }

        public Builder addBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder addUniqueCC(String uniqueCC) {
            this.uniqueCC = uniqueCC;
            return this;
        }

        public Builder addBin(String bin) {
            this.bin = bin;
            return this;
        }

        public Builder addLast4Digits(String last4Digits) {
            this.last4Digits = last4Digits;
            return this;
        }

        public Builder addLastDepositUse(String lastDepositUse) {
            this.lastDepositUse = lastDepositUse;
            return this;
        }

        public Builder addLastDepositSuccess(String lastDepositSuccess) {
            this.lastDepositSuccess = lastDepositSuccess;
            return this;
        }

        public Builder addLastWithdrawalUse(String lastWithdrawalUse) {
            this.lastWithdrawalUse = lastWithdrawalUse;
            return this;
        }

        public Builder addLastWithdrawalSuccess(String lastWithdrawalSuccess) {
            this.lastWithdrawalSuccess = lastWithdrawalSuccess;
            return this;
        }

        public Builder addRegistrationDate(String registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public Builder addExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
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

        public Builder addBillingAddress(UserDetailsCashier billingAddress) {
            this.billingAddress = billingAddress;
            return this;
        }


        /**
         * Adds billing address data to the request.
         *
         * @param address
         * @param city
         * @param countryCode
         * @param email
         * @param firstName
         * @param lastName
         * @param phone
         * @param state
         * @param zip
         * @param birthdate
         * @param county
         * @return this object
         */
        public Builder addBillingAddress(String address, String city, String countryCode, String email, String firstName,
                                         String lastName, String phone, String state, String zip, String birthdate,
                                         String county, String locale) {

            UserDetailsCashier billingAddress = AddressUtils.createUserDetailsCashierFromParams(address, city, countryCode, email, firstName,
                    lastName, phone, state, zip, birthdate,
                    county, locale);

            return addBillingAddress(billingAddress);
        }

        /**
         * Builds the request.
         *
         * @return {@link SafechargeRequest} object build from the params set by this builder
         * @throws ConstraintViolationException if the validation of the params fails
         */
        @Override
        public SafechargeBaseRequest build() throws ConstraintViolationException {
            AddUPOCreditCardByTokenRequest addUPOCreditCard = new AddUPOCreditCardByTokenRequest();
            addUPOCreditCard.setCcToken(ccToken);
            addUPOCreditCard.setBrand(brand);
            addUPOCreditCard.setUniqueCC(uniqueCC);
            addUPOCreditCard.setBin(bin);
            addUPOCreditCard.setLast4Digits(last4Digits);
            addUPOCreditCard.setLastDepositUse(lastDepositUse);
            addUPOCreditCard.setLastDepositSuccess(lastDepositSuccess);
            addUPOCreditCard.setLastWithdrawalUse(lastWithdrawalUse);
            addUPOCreditCard.setLastWithdrawalSuccess(lastWithdrawalSuccess);
            addUPOCreditCard.setRegistrationDate(registrationDate);
            addUPOCreditCard.setExpiryDate(expiryDate);
            addUPOCreditCard.setCcExpMonth(ccExpMonth);
            addUPOCreditCard.setCcExpYear(ccExpYear);
            addUPOCreditCard.setCcNameOnCard(ccNameOnCard);
            addUPOCreditCard.setUserTokenId(userTokenId);
            addUPOCreditCard.setBillingAddress(billingAddress);
            return ValidationUtils.validate(super.build(addUPOCreditCard));
        }
    }
}
