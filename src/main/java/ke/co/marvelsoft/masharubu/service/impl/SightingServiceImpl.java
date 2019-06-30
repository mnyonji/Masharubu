package ke.co.marvelsoft.masharubu.service.impl;

import ke.co.marvelsoft.masharubu.service.SightingService;
import ke.co.marvelsoft.masharubu.domain.Sighting;
import ke.co.marvelsoft.masharubu.repository.SightingRepository;
import ke.co.marvelsoft.masharubu.service.dto.SightingDTO;
import ke.co.marvelsoft.masharubu.service.mapper.SightingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Sighting}.
 */
@Service
@Transactional
public class SightingServiceImpl implements SightingService {

    private final Logger log = LoggerFactory.getLogger(SightingServiceImpl.class);

    private final SightingRepository sightingRepository;

    private final SightingMapper sightingMapper;

    public SightingServiceImpl(SightingRepository sightingRepository, SightingMapper sightingMapper) {
        this.sightingRepository = sightingRepository;
        this.sightingMapper = sightingMapper;
    }

    /**
     * Save a sighting.
     *
     * @param sightingDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SightingDTO save(SightingDTO sightingDTO) {
        log.debug("Request to save Sighting : {}", sightingDTO);
        Sighting sighting = sightingMapper.toEntity(sightingDTO);
        sighting = sightingRepository.save(sighting);
        return sightingMapper.toDto(sighting);
    }

    /**
     * Get all the sightings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SightingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Sightings");
        return sightingRepository.findAll(pageable)
            .map(sightingMapper::toDto);
    }


    /**
     * Get one sighting by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SightingDTO> findOne(Long id) {
        log.debug("Request to get Sighting : {}", id);
        return sightingRepository.findById(id)
            .map(sightingMapper::toDto);
    }

    /**
     * Delete the sighting by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Sighting : {}", id);
        sightingRepository.deleteById(id);
    }
}
