package ke.co.marvelsoft.masharubu.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import ke.co.marvelsoft.masharubu.domain.enumeration.Gender;

import ke.co.marvelsoft.masharubu.domain.enumeration.Status;

/**
 * A Driver.
 */
@Entity
@Table(name = "driver")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Driver implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotNull
    @Size(max = 20)
    @Column(name = "phone_nbr", length = 20, nullable = false)
    private String phoneNbr;

    @NotNull
    @Size(max = 100)
    @Column(name = "email_addr", length = 100, nullable = false)
    private String emailAddr;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    @Column(name = "date_created")
    private ZonedDateTime dateCreated;

    @NotNull
    @Column(name = "validated_by", nullable = false)
    private Integer validatedBy;

    @Column(name = "date_validated")
    private ZonedDateTime dateValidated;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("drivers")
    private TourOperator tourOperator;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Driver name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNbr() {
        return phoneNbr;
    }

    public Driver phoneNbr(String phoneNbr) {
        this.phoneNbr = phoneNbr;
        return this;
    }

    public void setPhoneNbr(String phoneNbr) {
        this.phoneNbr = phoneNbr;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public Driver emailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
        return this;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public Gender getGender() {
        return gender;
    }

    public Driver gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Status getStatus() {
        return status;
    }

    public Driver status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public Driver createdBy(Integer createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public Driver dateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getValidatedBy() {
        return validatedBy;
    }

    public Driver validatedBy(Integer validatedBy) {
        this.validatedBy = validatedBy;
        return this;
    }

    public void setValidatedBy(Integer validatedBy) {
        this.validatedBy = validatedBy;
    }

    public ZonedDateTime getDateValidated() {
        return dateValidated;
    }

    public Driver dateValidated(ZonedDateTime dateValidated) {
        this.dateValidated = dateValidated;
        return this;
    }

    public void setDateValidated(ZonedDateTime dateValidated) {
        this.dateValidated = dateValidated;
    }

    public User getUser() {
        return user;
    }

    public Driver user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TourOperator getTourOperator() {
        return tourOperator;
    }

    public Driver tourOperator(TourOperator tourOperator) {
        this.tourOperator = tourOperator;
        return this;
    }

    public void setTourOperator(TourOperator tourOperator) {
        this.tourOperator = tourOperator;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Driver)) {
            return false;
        }
        return id != null && id.equals(((Driver) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Driver{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", phoneNbr='" + getPhoneNbr() + "'" +
            ", emailAddr='" + getEmailAddr() + "'" +
            ", gender='" + getGender() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy=" + getCreatedBy() +
            ", dateCreated='" + getDateCreated() + "'" +
            ", validatedBy=" + getValidatedBy() +
            ", dateValidated='" + getDateValidated() + "'" +
            "}";
    }
}
