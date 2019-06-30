package ke.co.marvelsoft.masharubu.service;

import ke.co.marvelsoft.masharubu.service.dto.ParkDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ke.co.marvelsoft.masharubu.domain.Park}.
 */
public interface ParkService {

    /**
     * Save a park.
     *
     * @param parkDTO the entity to save.
     * @return the persisted entity.
     */
    ParkDTO save(ParkDTO parkDTO);

    /**
     * Get all the parks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ParkDTO> findAll(Pageable pageable);


    /**
     * Get the "id" park.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ParkDTO> findOne(Long id);

    /**
     * Delete the "id" park.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
