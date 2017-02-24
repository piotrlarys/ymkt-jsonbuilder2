package pl.biz.nsp.model;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Piotr Larys
 */
public class Contact {

    @JsonProperty("IdOrigin")
    private String idOrigin;
    @JsonProperty("Id")
    private String id;
    @JsonProperty("FullName")
    private String fullName;
    @JsonProperty("Street")
    private String street;
    @JsonProperty("HouseNumber")
    private String houseNumber;
    @JsonProperty("City")
    private String city;
    @JsonProperty("CountryDescription")
    private String countryDescription;
    @JsonProperty("PostalCode")
    private String postlCode;
    @JsonProperty("EMailAddress")
    private String eMailAddress;
    @JsonProperty("Timestamp")
    private String timestamp;

    private Contact() {

    }

    private Contact(Builder builder) {
        idOrigin = builder.IdOrigin;
        id = builder.Id;
        fullName = builder.FullName;
        street = builder.Street;
        houseNumber = builder.HouseNumber;
        city = builder.City;
        countryDescription = builder.CountryDescription;
        postlCode = builder.PostlCode;
        eMailAddress = builder.EMailAddress;
        timestamp = builder.timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getIdOrigin() {
        return idOrigin;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getCity() {
        return city;
    }

    public String getCountryDescription() {
        return countryDescription;
    }

    public String getPostlCode() {
        return postlCode;
    }

    public String geteMailAddress() {
        return eMailAddress;
    }

    public static class Builder {
        private String IdOrigin;
        private String Id;
        private String FullName;
        private String Street;
        private String HouseNumber;
        private String City;
        private String CountryDescription;
        private String PostlCode;
        private String EMailAddress;
        private String timestamp;

        public Builder() {

        }

        public Builder IdOrigin(String idOrigin) {
            IdOrigin = idOrigin;
            return this;
        }

        public Builder Id(String id) {
            Id = id;
            return this;
        }

        public Builder FullName(String fullName) {
            FullName = fullName;
            return this;
        }

        public Builder Street(String street) {
            Street = street;
            return this;
        }

        public Builder HouseNumber(String houseNumber) {
            HouseNumber = houseNumber;
            return this;
        }

        public Builder City(String city) {
            City = city;
            return this;
        }

        public Builder CountryDescription(String countryDescription) {
            CountryDescription = countryDescription;
            return this;
        }

        public Builder PostlCode(String postlCode) {
            PostlCode = postlCode;
            return this;
        }

        public Builder EMailAddress(String EMailAddress) {
            this.EMailAddress = EMailAddress;
            return this;
        }

        public Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }
    }

}
