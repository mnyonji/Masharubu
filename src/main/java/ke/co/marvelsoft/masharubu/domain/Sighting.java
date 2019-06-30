package ke.co.marvelsoft.masharubu.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A Sighting.
 */
@Entity
@Table(name = "sighting")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Sighting implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "picture", nullable = false)
    private byte[] picture;

    @Column(name = "picture_content_type", nullable = false)
    private String pictureContentType;

    @NotNull
    @Column(name = "latitude", nullable = false)
    private Float latitude;

    @NotNull
    @Column(name = "longitude", nullable = false)
    private Float longitude;

    @NotNull
    @Column(name = "altitude", nullable = false)
    private Float altitude;

    @Column(name = "date_sighted")
    private ZonedDateTime dateSighted;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonIgnoreProperties("sightings")
    private Driver driver;

    @ManyToOne
    @JsonIgnoreProperties("sightings")
    private Animal animal;

    @ManyToOne
    @JsonIgnoreProperties("sightings")
    private Park park;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPicture() {
        return picture;
    }

    public Sighting picture(byte[] picture) {
        this.picture = picture;
        return this;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getPictureContentType() {
        return pictureContentType;
    }

    public Sighting pictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
        return this;
    }

    public void setPictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Sighting latitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public Sighting longitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getAltitude() {
        return altitude;
    }

    public Sighting altitude(Float altitude) {
        this.altitude = altitude;
        return this;
    }

    public void setAltitude(Float altitude) {
        this.altitude = altitude;
    }

    public ZonedDateTime getDateSighted() {
        return dateSighted;
    }

    public Sighting dateSighted(ZonedDateTime dateSighted) {
        this.dateSighted = dateSighted;
        return this;
    }

    public void setDateSighted(ZonedDateTime dateSighted) {
        this.dateSighted = dateSighted;
    }

    public String getDescription() {
        return description;
    }

    public Sighting description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Driver getDriver() {
        return driver;
    }

    public Sighting driver(Driver driver) {
        this.driver = driver;
        return this;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Sighting animal(Animal animal) {
        this.animal = animal;
        return this;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Park getPark() {
        return park;
    }

    public Sighting park(Park park) {
        this.park = park;
        return this;
    }

    public void setPark(Park park) {
        this.park = park;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sighting)) {
            return false;
        }
        return id != null && id.equals(((Sighting) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Sighting{" +
            "id=" + getId() +
            ", picture='" + getPicture() + "'" +
            ", pictureContentType='" + getPictureContentType() + "'" +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", altitude=" + getAltitude() +
            ", dateSighted='" + getDateSighted() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
