package ke.co.marvelsoft.masharubu.service.dto;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link ke.co.marvelsoft.masharubu.domain.Sighting} entity.
 */
public class SightingDTO implements Serializable {

    private Long id;

    
    @Lob
    private byte[] picture;

    private String pictureContentType;
    @NotNull
    private Float latitude;

    @NotNull
    private Float longitude;

    @NotNull
    private Float altitude;

    private ZonedDateTime dateSighted;

    private String description;


    private Long driverId;

    private String driverName;

    private Long animalId;

    private String animalName;

    private Long parkId;

    private String parkName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getPictureContentType() {
        return pictureContentType;
    }

    public void setPictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getAltitude() {
        return altitude;
    }

    public void setAltitude(Float altitude) {
        this.altitude = altitude;
    }

    public ZonedDateTime getDateSighted() {
        return dateSighted;
    }

    public void setDateSighted(ZonedDateTime dateSighted) {
        this.dateSighted = dateSighted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public Long getParkId() {
        return parkId;
    }

    public void setParkId(Long parkId) {
        this.parkId = parkId;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SightingDTO sightingDTO = (SightingDTO) o;
        if (sightingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sightingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SightingDTO{" +
            "id=" + getId() +
            ", picture='" + getPicture() + "'" +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", altitude=" + getAltitude() +
            ", dateSighted='" + getDateSighted() + "'" +
            ", description='" + getDescription() + "'" +
            ", driver=" + getDriverId() +
            ", driver='" + getDriverName() + "'" +
            ", animal=" + getAnimalId() +
            ", animal='" + getAnimalName() + "'" +
            ", park=" + getParkId() +
            ", park='" + getParkName() + "'" +
            "}";
    }
}
