package ke.co.marvelsoft.masharubu.service.impl;

import ke.co.marvelsoft.masharubu.service.AnimalService;
import ke.co.marvelsoft.masharubu.domain.Animal;
import ke.co.marvelsoft.masharubu.repository.AnimalRepository;
import ke.co.marvelsoft.masharubu.service.dto.AnimalDTO;
import ke.co.marvelsoft.masharubu.service.mapper.AnimalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Animal}.
 */
@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {

    private final Logger log = LoggerFactory.getLogger(AnimalServiceImpl.class);

    private final AnimalRepository animalRepository;

    private final AnimalMapper animalMapper;

    public AnimalServiceImpl(AnimalRepository animalRepository, AnimalMapper animalMapper) {
        this.animalRepository = animalRepository;
        this.animalMapper = animalMapper;
    }

    /**
     * Save a animal.
     *
     * @param animalDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AnimalDTO save(AnimalDTO animalDTO) {
        log.debug("Request to save Animal : {}", animalDTO);
        Animal animal = animalMapper.toEntity(animalDTO);
        animal = animalRepository.save(animal);
        return animalMapper.toDto(animal);
    }

    /**
     * Get all the animals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AnimalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Animals");
        return animalRepository.findAll(pageable)
            .map(animalMapper::toDto);
    }


    /**
     * Get one animal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AnimalDTO> findOne(Long id) {
        log.debug("Request to get Animal : {}", id);
        return animalRepository.findById(id)
            .map(animalMapper::toDto);
    }

    /**
     * Delete the animal by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Animal : {}", id);
        animalRepository.deleteById(id);
    }
}
