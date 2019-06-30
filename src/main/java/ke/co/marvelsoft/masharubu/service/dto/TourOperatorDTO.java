package ke.co.marvelsoft.masharubu.service.dto;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import ke.co.marvelsoft.masharubu.domain.enumeration.Status;

/**
 * A DTO for the {@link ke.co.marvelsoft.masharubu.domain.TourOperator} entity.
 */
public class TourOperatorDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 20)
    private String phoneNbr;

    @NotNull
    @Size(max = 100)
    private String emailAddr;

    @NotNull
    private Status status;

    @NotNull
    private Integer createdBy;

    private ZonedDateTime dateCreated;

    @NotNull
    private Integer validatedBy;

    private ZonedDateTime dateValidated;

    @NotNull
    private String physicalAddress;


    private Long userId;

    private String userLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNbr() {
        return phoneNbr;
    }

    public void setPhoneNbr(String phoneNbr) {
        this.phoneNbr = phoneNbr;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getValidatedBy() {
        return validatedBy;
    }

    public void setValidatedBy(Integer validatedBy) {
        this.validatedBy = validatedBy;
    }

    public ZonedDateTime getDateValidated() {
        return dateValidated;
    }

    public void setDateValidated(ZonedDateTime dateValidated) {
        this.dateValidated = dateValidated;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TourOperatorDTO tourOperatorDTO = (TourOperatorDTO) o;
        if (tourOperatorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tourOperatorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TourOperatorDTO{" +
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
            ", user=" + getUserId() +
            ", user='" + getUserLogin() + "'" +
            "}";
    }
}
