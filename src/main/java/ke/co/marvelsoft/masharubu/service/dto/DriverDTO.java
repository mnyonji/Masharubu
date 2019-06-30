package ke.co.marvelsoft.masharubu.service.dto;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import ke.co.marvelsoft.masharubu.domain.enumeration.Gender;
import ke.co.marvelsoft.masharubu.domain.enumeration.Status;

/**
 * A DTO for the {@link ke.co.marvelsoft.masharubu.domain.Driver} entity.
 */
public class DriverDTO implements Serializable {

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
    private Gender gender;

    @NotNull
    private Status status;

    @NotNull
    private Integer createdBy;

    private ZonedDateTime dateCreated;

    @NotNull
    private Integer validatedBy;

    private ZonedDateTime dateValidated;


    private Long userId;

    private String userLogin;

    private Long tourOperatorId;

    private String tourOperatorName;

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public Long getTourOperatorId() {
        return tourOperatorId;
    }

    public void setTourOperatorId(Long tourOperatorId) {
        this.tourOperatorId = tourOperatorId;
    }

    public String getTourOperatorName() {
        return tourOperatorName;
    }

    public void setTourOperatorName(String tourOperatorName) {
        this.tourOperatorName = tourOperatorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DriverDTO driverDTO = (DriverDTO) o;
        if (driverDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), driverDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DriverDTO{" +
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
            ", user=" + getUserId() +
            ", user='" + getUserLogin() + "'" +
            ", tourOperator=" + getTourOperatorId() +
            ", tourOperator='" + getTourOperatorName() + "'" +
            "}";
    }
}
