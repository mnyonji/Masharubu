package ke.co.marvelsoft.masharubu.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import ke.co.marvelsoft.masharubu.domain.enumeration.Status;

/**
 * A TourOperator.
 */
@Entity
@Table(name = "tour_operator")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TourOperator implements Serializable {

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

    @NotNull
    @Column(name = "physical_address", nullable = false)
    private String physicalAddress;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

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

    public TourOperator name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNbr() {
        return phoneNbr;
    }

    public TourOperator phoneNbr(String phoneNbr) {
        this.phoneNbr = phoneNbr;
        return this;
    }

    public void setPhoneNbr(String phoneNbr) {
        this.phoneNbr = phoneNbr;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public TourOperator emailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
        return this;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public Status getStatus() {
        return status;
    }

    public TourOperator status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public TourOperator createdBy(Integer createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public TourOperator dateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getValidatedBy() {
        return validatedBy;
    }

    public TourOperator validatedBy(Integer validatedBy) {
        this.validatedBy = validatedBy;
        return this;
    }

    public void setValidatedBy(Integer validatedBy) {
        this.validatedBy = validatedBy;
    }

    public ZonedDateTime getDateValidated() {
        return dateValidated;
    }

    public TourOperator dateValidated(ZonedDateTime dateValidated) {
        this.dateValidated = dateValidated;
        return this;
    }

    public void setDateValidated(ZonedDateTime dateValidated) {
        this.dateValidated = dateValidated;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public TourOperator physicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
        return this;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public User getUser() {
        return user;
    }

    public TourOperator user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TourOperator)) {
            return false;
        }
        return id != null && id.equals(((TourOperator) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TourOperator{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", phoneNbr='" + getPhoneNbr() + "'" +
            ", emailAddr='" + getEmailAddr() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy=" + getCreatedBy() +
            ", dateCreated='" + getDateCreated() + "'" +
            ", validatedBy=" + getValidatedBy() +
            ", dateValidated='" + getDateValidated() + "'" +
            ", physicalAddress='" + getPhysicalAddress() + "'" +
            "}";
    }
}
