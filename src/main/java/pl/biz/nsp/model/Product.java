package pl.biz.nsp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Piotr Larys
 */
public class Product {

    @JsonProperty("Id")
    private String id;
    @JsonProperty("IdOrigin")
    private String idOrigin;
    @JsonProperty("Language")
    private String language;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("ZBRAND")
    private String zBrand;
    @JsonProperty("ZUPPER")
    private String zUpper;
    @JsonProperty("ZPREMIUM")
    private String zPremium;
    @JsonProperty("ZSERIES")
    private String zSeries;
    @JsonProperty("ZSEAZON")
    private String zSeazon;
    //@JsonProperty("ProductCategories")
    @JsonIgnore
    private List<ProductCategory> productCategoryList;
    
    private Product(){}

    private Product(Builder builder) {
        id = builder.id;
        idOrigin = builder.idOrigin;
        language = builder.language;
        name = builder.name;
        zBrand = builder.zBrand;
        zUpper = builder.zUpper;
        zPremium = builder.zPremium;
        zSeries = builder.zSeries;
        zSeazon = builder.zSeazon;
        productCategoryList = builder.productCategoryList;
    }

    public String getId() {
        return id;
    }

    public String getIdOrigin() {
        return idOrigin;
    }

    public String getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }

    public String getzUpper() {
        return zUpper;
    }

    public String getzPremium() {
        return zPremium;
    }

    public String getzSeries() {
        return zSeries;
    }

    public String getzSeazon() {
        return zSeazon;
    }

    public String getzBrand() {
        return zBrand;
    }

    public static class Builder {
        private String id;
        private String idOrigin;
        private String language;
        private String name;
        private String zBrand;
        private String zUpper;
        private String zPremium;
        private String zSeries;
        private String zSeazon;
        private List<ProductCategory> productCategoryList;

        public Builder() {}

        public Builder Id(String id) {
            this.id = id;
            return this;
        }

        public Builder IdOrigin(String idOrigin) {
            this.idOrigin = idOrigin;
            return this;
        }

        public Builder Language(String language) {
            this.language = language;
            return this;
        }

        public Builder Name(String name) {
            this.name = name;
            return this;
        }

        public Builder zBrand(String zBrand) {
            this.zBrand = zBrand;
            return this;
        }

        public Builder zUpper(String zUpper) {
            this.zUpper = zUpper;
            return this;
        }

        public Builder zPremium(String zPremium) {
            this.zPremium = zPremium;
            return this;
        }

        public Builder zSeries(String zSeries) {
            this.zSeries = zSeries;
            return this;
        }

        public Builder zSeazon(String zSeazon) {
            this.zSeazon = zSeazon;
            return this;
        }

        public Builder ProductCategoryList(List<ProductCategory> productCategoryList) {
            this.productCategoryList = productCategoryList;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
    
}
