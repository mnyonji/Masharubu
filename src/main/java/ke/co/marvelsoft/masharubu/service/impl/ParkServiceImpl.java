package ke.co.marvelsoft.masharubu.service.impl;

import ke.co.marvelsoft.masharubu.service.ParkService;
import ke.co.marvelsoft.masharubu.domain.Park;
import ke.co.marvelsoft.masharubu.repository.ParkRepository;
import ke.co.marvelsoft.masharubu.service.dto.ParkDTO;
import ke.co.marvelsoft.masharubu.service.mapper.ParkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Park}.
 */
@Service
@Transactional
public class ParkServiceImpl implements ParkService {

    private final Logger log = LoggerFactory.getLogger(ParkServiceImpl.class);

    private final ParkRepository parkRepository;

    private final ParkMapper parkMapper;

    public ParkServiceImpl(ParkRepository parkRepository, ParkMapper parkMapper) {
        this.parkRepository = parkRepository;
        this.parkMapper = parkMapper;
    }

    /**
     * Save a park.
     *
     * @param parkDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ParkDTO save(ParkDTO parkDTO) {
        log.debug("Request to save Park : {}", parkDTO);
        Park park = parkMapper.toEntity(parkDTO);
        park = parkRepository.save(park);
        return parkMapper.toDto(park);
    }

    /**
     * Get all the parks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ParkDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Parks");
        return parkRepository.findAll(pageable)
            .map(parkMapper::toDto);
    }


    /**
     * Get one park by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ParkDTO> findOne(Long id) {
        log.debug("Request to get Park : {}", id);
        return parkRepository.findById(id)
            .map(parkMapper::toDto);
    }

    /**
     * Delete the park by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Park : {}", id);
        parkRepository.deleteById(id);
    }
}
