package ke.co.marvelsoft.masharubu.service.mapper;

import ke.co.marvelsoft.masharubu.domain.*;
import ke.co.marvelsoft.masharubu.service.dto.SightingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Sighting} and its DTO {@link SightingDTO}.
 */
@Mapper(componentModel = "spring", uses = {DriverMapper.class, AnimalMapper.class, ParkMapper.class})
public interface SightingMapper extends EntityMapper<SightingDTO, Sighting> {

    @Mapping(source = "driver.id", target = "driverId")
    @Mapping(source = "driver.name", target = "driverName")
    @Mapping(source = "animal.id", target = "animalId")
    @Mapping(source = "animal.name", target = "animalName")
    @Mapping(source = "park.id", target = "parkId")
    @Mapping(source = "park.name", target = "parkName")
    SightingDTO toDto(Sighting sighting);

    @Mapping(source = "driverId", target = "driver")
    @Mapping(source = "animalId", target = "animal")
    @Mapping(source = "parkId", target = "park")
    Sighting toEntity(SightingDTO sightingDTO);

    default Sighting fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sighting sighting = new Sighting();
        sighting.setId(id);
        return sighting;
    }
}
