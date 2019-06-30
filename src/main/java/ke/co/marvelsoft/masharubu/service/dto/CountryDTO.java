package ke.co.marvelsoft.masharubu.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link ke.co.marvelsoft.masharubu.domain.Country} entity.
 */
public class CountryDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @Size(max = 2)
    private String alphaCode2;

    @Size(max = 3)
    private String alphaCode3;

    @Size(max = 3)
    private String countryCode;

    private Integer numericCode;

    @Lob
    private byte[] flagIcon;

    private String flagIconContentType;

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

    public String getAlphaCode2() {
        return alphaCode2;
    }

    public void setAlphaCode2(String alphaCode2) {
        this.alphaCode2 = alphaCode2;
    }

    public String getAlphaCode3() {
        return alphaCode3;
    }

    public void setAlphaCode3(String alphaCode3) {
        this.alphaCode3 = alphaCode3;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(Integer numericCode) {
        this.numericCode = numericCode;
    }

    public byte[] getFlagIcon() {
        return flagIcon;
    }

    public void setFlagIcon(byte[] flagIcon) {
        this.flagIcon = flagIcon;
    }

    public String getFlagIconContentType() {
        return flagIconContentType;
    }

    public void setFlagIconContentType(String flagIconContentType) {
        this.flagIconContentType = flagIconContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CountryDTO countryDTO = (CountryDTO) o;
        if (countryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), countryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CountryDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", alphaCode2='" + getAlphaCode2() + "'" +
            ", alphaCode3='" + getAlphaCode3() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            ", numericCode=" + getNumericCode() +
            ", flagIcon='" + getFlagIcon() + "'" +
            "}";
    }
}
