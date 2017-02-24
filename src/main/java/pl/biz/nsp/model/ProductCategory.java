package pl.biz.nsp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Piotr Larys
 */
public class ProductCategory {

    @JsonProperty("Name")
    private String name;

    private ProductCategory() {}

    private ProductCategory(Builder builder) {
        name = builder.name;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private String name;

        public Builder(){}

        public Builder Name(String name) {
            this.name = name;
            return this;
        }

        public ProductCategory builder() {
            return new ProductCategory(this);
        }
    }

}
