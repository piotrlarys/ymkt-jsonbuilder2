package pl.biz.nsp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Piotr Larys
 */
public class ProductInteraction {

    @JsonProperty("ItemId")
    private String itemId;
    @JsonProperty("ItemType")
    private String itemType;
    @JsonProperty("Amount")
    private String amount;
    @JsonProperty("Quantity")
    private String quantity;
    @JsonProperty("ZSIZE")
    private String zSize;
    @JsonProperty("ZCOLOR")
    private String zColor;
    @JsonProperty("ZDISCOUNTCODE")
    private String zDiscountCode;
    @JsonProperty("ZDISCOUNTREGULAR")
    private String zDiscountRegular;

    public String getItemId() {
        return itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public String getAmount() {
        return amount;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getzSize() {
        return zSize;
    }

    public String getzColor() {
        return zColor;
    }

    public String getzDiscountCode() {
        return zDiscountCode;
    }

    public String getzDiscountRegular() {
        return zDiscountRegular;
    }

    public static final class ProductInteractionBuilder {
        private String itemId;
        private String itemType;
        private String amount;
        private String quantity;
        private String zSize;
        private String zColor;
        private String zDiscountCode;
        private String zDiscountRegular;

        private ProductInteractionBuilder() {
        }

        public static ProductInteractionBuilder aProductInteraction() {
            return new ProductInteractionBuilder();
        }

        public ProductInteractionBuilder itemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public ProductInteractionBuilder itemType(String itemType) {
            this.itemType = itemType;
            return this;
        }

        public ProductInteractionBuilder amount(String amount) {
            this.amount = amount;
            return this;
        }

        public ProductInteractionBuilder quantity(String quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductInteractionBuilder zSize(String zSize) {
            this.zSize = zSize;
            return this;
        }

        public ProductInteractionBuilder zColor(String zColor) {
            this.zColor = zColor;
            return this;
        }

        public ProductInteractionBuilder zDiscountCode(String zDiscountCode) {
            this.zDiscountCode = zDiscountCode;
            return this;
        }

        public ProductInteractionBuilder zDiscountRegular(String zDiscountRegular) {
            this.zDiscountRegular = zDiscountRegular;
            return this;
        }

        public ProductInteractionBuilder but() {
            return aProductInteraction().itemId(itemId).itemType(itemType).amount(amount).quantity(quantity).zSize(zSize).zColor(zColor).zDiscountCode(zDiscountCode).zDiscountRegular(zDiscountRegular);
        }

        public ProductInteraction build() {
            ProductInteraction productInteraction = new ProductInteraction();
            productInteraction.zDiscountCode = this.zDiscountCode;
            productInteraction.quantity = this.quantity;
            productInteraction.zColor = this.zColor;
            productInteraction.itemId = this.itemId;
            productInteraction.zSize = this.zSize;
            productInteraction.amount = this.amount;
            productInteraction.zDiscountRegular = this.zDiscountRegular;
            productInteraction.itemType = this.itemType;
            return productInteraction;
        }
    }
}
