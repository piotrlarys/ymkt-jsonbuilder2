package pl.biz.nsp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Piotr Larys
 */
public class ProductCategory {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Id")
    private String id;

    @JsonProperty("HierarchyId")
    private String hierarchyId;

    private ProductCategory() {}

    private ProductCategory(Builder builder) {
        name = builder.name;
        id = builder.id;
        hierarchyId = builder.hierarchyId;
    }

    public String getId() {
        return id;
    }

    public String getHierarchyId() {
        return hierarchyId;
    }

    public String getName() {
        return name;
    }

    public static class Builder {

        private String id;
        private String hierarchyId;
        private String name;

        public Builder(){}

        public Builder Id(String id) {
            this.id = id;
            return this;
        }

        public Builder HierarchyId(String hierarchyId) {
            this.hierarchyId = hierarchyId;
            return this;
        }

        public Builder Name(String name) {
            this.name = name;
            return this;
        }

        public ProductCategory builder() {
            return new ProductCategory(this);
        }
    }

}
