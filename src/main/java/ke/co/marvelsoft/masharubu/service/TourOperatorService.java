package ke.co.marvelsoft.masharubu.service;

import ke.co.marvelsoft.masharubu.service.dto.TourOperatorDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ke.co.marvelsoft.masharubu.domain.TourOperator}.
 */
public interface TourOperatorService {

    /**
     * Save a tourOperator.
     *
     * @param tourOperatorDTO the entity to save.
     * @return the persisted entity.
     */
    TourOperatorDTO save(TourOperatorDTO tourOperatorDTO);

    /**
     * Get all the tourOperators.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TourOperatorDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tourOperator.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TourOperatorDTO> findOne(Long id);

    /**
     * Delete the "id" tourOperator.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
