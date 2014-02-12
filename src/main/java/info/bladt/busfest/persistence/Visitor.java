package info.bladt.busfest.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@Table(name = "visitors")
@Entity
public class Visitor extends AbstractEntity {
    // TODO Club-IG

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "street")
    private String street;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "email_address")
    private String emailAddress;

    public Visitor() {}

    public Visitor(String firstName, String lastName, String street, String zipCode, String city, String country, Date dateOfBirth, String telephoneNumber, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
