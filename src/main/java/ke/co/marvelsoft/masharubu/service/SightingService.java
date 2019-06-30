package ke.co.marvelsoft.masharubu.service;

import ke.co.marvelsoft.masharubu.service.dto.SightingDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ke.co.marvelsoft.masharubu.domain.Sighting}.
 */
public interface SightingService {

    /**
     * Save a sighting.
     *
     * @param sightingDTO the entity to save.
     * @return the persisted entity.
     */
    SightingDTO save(SightingDTO sightingDTO);

    /**
     * Get all the sightings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SightingDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sighting.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SightingDTO> findOne(Long id);

    /**
     * Delete the "id" sighting.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
