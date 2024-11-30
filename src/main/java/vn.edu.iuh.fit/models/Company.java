package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @Column(name="CompanyID")
    private long comp_id;
    @Column(name="About",columnDefinition = "varchar(2000)")
    private String about;
    @Column(name="Email",columnDefinition = "varchar(255)")
    private String email;
    @Column(name="CompanyName",columnDefinition = "varchar(255)")
    private String comp_name;
    @Column(name="Phone",columnDefinition = "varchar(255)")
    private String phone;
    @Column(name="WebUrl",columnDefinition = "varchar(255)")
    private String web_url;
    @ManyToOne
    @JoinColumn(name = "AddressID")
    private Address address;

    public Company() {
    }

    public Company(long comp_id) {
        this.comp_id = comp_id;
    }

    public Company(long comp_id, String about, String email, String comp_name, String phone, String web_url, Address address) {
        this.comp_id = comp_id;
        this.about = about;
        this.email = email;
        this.comp_name = comp_name;
        this.phone = phone;
        this.web_url = web_url;
        this.address = address;
    }

    public long getComp_id() {
        return comp_id;
    }

    public void setComp_id(long comp_id) {
        this.comp_id = comp_id;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComp_name() {
        return comp_name;
    }

    public void setComp_name(String comp_name) {
        this.comp_name = comp_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Company{" +
                "comp_id=" + comp_id +
                ", about='" + about + '\'' +
                ", email='" + email + '\'' +
                ", comp_name='" + comp_name + '\'' +
                ", phone='" + phone + '\'' +
                ", web_url='" + web_url + '\'' +
                ", address=" + address +
                '}';
    }
}