package ke.co.marvelsoft.masharubu.service;

import ke.co.marvelsoft.masharubu.service.dto.AnimalDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ke.co.marvelsoft.masharubu.domain.Animal}.
 */
public interface AnimalService {

    /**
     * Save a animal.
     *
     * @param animalDTO the entity to save.
     * @return the persisted entity.
     */
    AnimalDTO save(AnimalDTO animalDTO);

    /**
     * Get all the animals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AnimalDTO> findAll(Pageable pageable);


    /**
     * Get the "id" animal.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AnimalDTO> findOne(Long id);

    /**
     * Delete the "id" animal.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
