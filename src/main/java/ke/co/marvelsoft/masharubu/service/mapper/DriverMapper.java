package ke.co.marvelsoft.masharubu.service.mapper;

import ke.co.marvelsoft.masharubu.domain.*;
import ke.co.marvelsoft.masharubu.service.dto.DriverDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Driver} and its DTO {@link DriverDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, TourOperatorMapper.class})
public interface DriverMapper extends EntityMapper<DriverDTO, Driver> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "tourOperator.id", target = "tourOperatorId")
    @Mapping(source = "tourOperator.name", target = "tourOperatorName")
    DriverDTO toDto(Driver driver);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "tourOperatorId", target = "tourOperator")
    Driver toEntity(DriverDTO driverDTO);

    default Driver fromId(Long id) {
        if (id == null) {
            return null;
        }
        Driver driver = new Driver();
        driver.setId(id);
        return driver;
    }
}
