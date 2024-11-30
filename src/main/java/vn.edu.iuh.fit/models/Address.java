package vn.edu.iuh.fit.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name="AddressID")
    private long adr_id;
    @Column(name="Street",columnDefinition = "varchar(150)")
    private String street;
    @Column(name="City",columnDefinition = "varchar(50)")
    private String city;
    @Column(name="Country",columnDefinition = "smallint(6)")
    private int country;
    @Column(name="Number",columnDefinition = "varchar(20)")
    private String number;
    @Column(name="ZipCode",columnDefinition = "varchar(7)")
    private String zipcode;

    public Address() {
    }

    public Address(long adr_id, String street, String city, int country, String number, String zipcode) {
        this.adr_id = adr_id;
        this.street = street;
        this.city = city;
        this.country = country;
        this.number = number;
        this.zipcode = zipcode;
    }

    public long getAdr_id() {
        return adr_id;
    }

    public void setAdr_id(long adr_id) {
        this.adr_id = adr_id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "adr_id=" + adr_id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country=" + country +
                ", number='" + number + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}