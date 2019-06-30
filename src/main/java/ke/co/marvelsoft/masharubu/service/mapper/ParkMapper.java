package ke.co.marvelsoft.masharubu.service.mapper;

import ke.co.marvelsoft.masharubu.domain.*;
import ke.co.marvelsoft.masharubu.service.dto.ParkDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Park} and its DTO {@link ParkDTO}.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface ParkMapper extends EntityMapper<ParkDTO, Park> {

    @Mapping(source = "country.id", target = "countryId")
    @Mapping(source = "country.name", target = "countryName")
    ParkDTO toDto(Park park);

    @Mapping(source = "countryId", target = "country")
    Park toEntity(ParkDTO parkDTO);

    default Park fromId(Long id) {
        if (id == null) {
            return null;
        }
        Park park = new Park();
        park.setId(id);
        return park;
    }
}
