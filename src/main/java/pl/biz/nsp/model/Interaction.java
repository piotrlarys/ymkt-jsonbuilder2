package pl.biz.nsp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.jmx.snmp.Timestamp;

import java.util.List;

/**
 * @author Piotr Larys
 */
public class Interaction {

    @JsonProperty("Key")
    private String key;
    @JsonProperty("SourceObjectId")
    private String sourceObjectId;
    @JsonProperty("IdOrigin")
    private String idOrigin;
    @JsonProperty("Timestamp")
    private String timestamp;
    @JsonProperty("InteractionType")
    private String interactionType;
    @JsonProperty("CommunicationMedium")
    private String communicationMedium;
    @JsonProperty("ContactId")
    private String contactId;
    @JsonProperty("Amount")
    private String amount;
    @JsonProperty("Currency")
    private String currency;
    @JsonProperty("Products")
    private List<ProductInteraction> productInteractionList;
    @JsonProperty("Interests")
    @JsonIgnore
    private List<InterestInteraction> interestInteractionList;

    private Interaction() {

    }

    private Interaction(Builder builder) {
        if (builder == null)
            return;

        key = builder.key;
        sourceObjectId = builder.sourceObjectId;
        idOrigin = builder.idOrigin;
        timestamp = builder.timestamp;
        interactionType = builder.interactionType;
        communicationMedium = builder.communicationMedium;
        contactId = builder.contactId;
        amount = builder.amount;
        currency = builder.currency;
        productInteractionList = builder.productInteractionList;
        interestInteractionList = builder.interestInteractionList;
    }

    public String getKey() {
        return key;
    }

    public String getSourceObjectId() {
        return sourceObjectId;
    }

    public String getIdOrigin() {
        return idOrigin;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getInteractionType() {
        return interactionType;
    }

    public String getCommunicationMedium() {
        return communicationMedium;
    }

    public String getContactId() {
        return contactId;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public List<ProductInteraction> getProductInteractionList() {
        return productInteractionList;
    }

    public List<InterestInteraction> getInterestInteractionList() {
        return interestInteractionList;
    }

    public static class Builder {

        private String key;

        private String sourceObjectId;

        private String idOrigin;

        private String timestamp;

        private String interactionType;

        private String communicationMedium;

        private String contactId;

        private String amount;

        private String currency;

        private List<ProductInteraction> productInteractionList;

        private List<InterestInteraction> interestInteractionList;

        public Builder() {
        }

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder sourceObjectId(String sourceObjectId) {
            this.sourceObjectId = sourceObjectId;
            return this;
        }

        public Builder idOrigin(String idOrigin) {
            this.idOrigin = idOrigin;
            return this;
        }

        public Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder interactionType(String interactionType) {
            this.interactionType = interactionType;
            return this;
        }

        public Builder communicationMedium(String communicationMedium) {
            this.communicationMedium = communicationMedium;
            return this;
        }

        public Builder contactId(String contactId) {
            this.contactId = contactId;
            return this;
        }

        public Builder amount(String amount) {
            this.amount = amount;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder productInteractionList(List<ProductInteraction> productInteractionList) {
            this.productInteractionList = productInteractionList;
            return this;
        }

        public Builder interestInteractionList(List<InterestInteraction> interestInteractionList) {
            this.interestInteractionList = interestInteractionList;
            return this;
        }

        public Interaction build() {
            return new Interaction(this);
        }
    }


}