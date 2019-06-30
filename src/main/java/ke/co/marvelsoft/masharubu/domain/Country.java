package ke.co.marvelsoft.masharubu.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Country.
 */
@Entity
@Table(name = "country")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Size(max = 2)
    @Column(name = "alpha_code_2", length = 2)
    private String alphaCode2;

    @Size(max = 3)
    @Column(name = "alpha_code_3", length = 3)
    private String alphaCode3;

    @Size(max = 3)
    @Column(name = "country_code", length = 3)
    private String countryCode;

    @Column(name = "numeric_code")
    private Integer numericCode;

    @Lob
    @Column(name = "flag_icon")
    private byte[] flagIcon;

    @Column(name = "flag_icon_content_type")
    private String flagIconContentType;

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

    public Country name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlphaCode2() {
        return alphaCode2;
    }

    public Country alphaCode2(String alphaCode2) {
        this.alphaCode2 = alphaCode2;
        return this;
    }

    public void setAlphaCode2(String alphaCode2) {
        this.alphaCode2 = alphaCode2;
    }

    public String getAlphaCode3() {
        return alphaCode3;
    }

    public Country alphaCode3(String alphaCode3) {
        this.alphaCode3 = alphaCode3;
        return this;
    }

    public void setAlphaCode3(String alphaCode3) {
        this.alphaCode3 = alphaCode3;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Country countryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getNumericCode() {
        return numericCode;
    }

    public Country numericCode(Integer numericCode) {
        this.numericCode = numericCode;
        return this;
    }

    public void setNumericCode(Integer numericCode) {
        this.numericCode = numericCode;
    }

    public byte[] getFlagIcon() {
        return flagIcon;
    }

    public Country flagIcon(byte[] flagIcon) {
        this.flagIcon = flagIcon;
        return this;
    }

    public void setFlagIcon(byte[] flagIcon) {
        this.flagIcon = flagIcon;
    }

    public String getFlagIconContentType() {
        return flagIconContentType;
    }

    public Country flagIconContentType(String flagIconContentType) {
        this.flagIconContentType = flagIconContentType;
        return this;
    }

    public void setFlagIconContentType(String flagIconContentType) {
        this.flagIconContentType = flagIconContentType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Country)) {
            return false;
        }
        return id != null && id.equals(((Country) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Country{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", alphaCode2='" + getAlphaCode2() + "'" +
            ", alphaCode3='" + getAlphaCode3() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            ", numericCode=" + getNumericCode() +
            ", flagIcon='" + getFlagIcon() + "'" +
            ", flagIconContentType='" + getFlagIconContentType() + "'" +
            "}";
    }
}
