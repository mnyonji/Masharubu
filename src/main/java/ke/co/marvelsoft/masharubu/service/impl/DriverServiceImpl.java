package ke.co.marvelsoft.masharubu.service.impl;

import ke.co.marvelsoft.masharubu.service.DriverService;
import ke.co.marvelsoft.masharubu.domain.Driver;
import ke.co.marvelsoft.masharubu.repository.DriverRepository;
import ke.co.marvelsoft.masharubu.service.dto.DriverDTO;
import ke.co.marvelsoft.masharubu.service.mapper.DriverMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Driver}.
 */
@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private final Logger log = LoggerFactory.getLogger(DriverServiceImpl.class);

    private final DriverRepository driverRepository;

    private final DriverMapper driverMapper;

    public DriverServiceImpl(DriverRepository driverRepository, DriverMapper driverMapper) {
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
    }

    /**
     * Save a driver.
     *
     * @param driverDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DriverDTO save(DriverDTO driverDTO) {
        log.debug("Request to save Driver : {}", driverDTO);
        Driver driver = driverMapper.toEntity(driverDTO);
        driver = driverRepository.save(driver);
        return driverMapper.toDto(driver);
    }

    /**
     * Get all the drivers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DriverDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Drivers");
        return driverRepository.findAll(pageable)
            .map(driverMapper::toDto);
    }


    /**
     * Get one driver by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DriverDTO> findOne(Long id) {
        log.debug("Request to get Driver : {}", id);
        return driverRepository.findById(id)
            .map(driverMapper::toDto);
    }

    /**
     * Delete the driver by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Driver : {}", id);
        driverRepository.deleteById(id);
    }
}
