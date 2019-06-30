package ke.co.marvelsoft.masharubu.service.impl;

import ke.co.marvelsoft.masharubu.service.TourOperatorService;
import ke.co.marvelsoft.masharubu.domain.TourOperator;
import ke.co.marvelsoft.masharubu.repository.TourOperatorRepository;
import ke.co.marvelsoft.masharubu.service.dto.TourOperatorDTO;
import ke.co.marvelsoft.masharubu.service.mapper.TourOperatorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TourOperator}.
 */
@Service
@Transactional
public class TourOperatorServiceImpl implements TourOperatorService {

    private final Logger log = LoggerFactory.getLogger(TourOperatorServiceImpl.class);

    private final TourOperatorRepository tourOperatorRepository;

    private final TourOperatorMapper tourOperatorMapper;

    public TourOperatorServiceImpl(TourOperatorRepository tourOperatorRepository, TourOperatorMapper tourOperatorMapper) {
        this.tourOperatorRepository = tourOperatorRepository;
        this.tourOperatorMapper = tourOperatorMapper;
    }

    /**
     * Save a tourOperator.
     *
     * @param tourOperatorDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TourOperatorDTO save(TourOperatorDTO tourOperatorDTO) {
        log.debug("Request to save TourOperator : {}", tourOperatorDTO);
        TourOperator tourOperator = tourOperatorMapper.toEntity(tourOperatorDTO);
        tourOperator = tourOperatorRepository.save(tourOperator);
        return tourOperatorMapper.toDto(tourOperator);
    }

    /**
     * Get all the tourOperators.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TourOperatorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TourOperators");
        return tourOperatorRepository.findAll(pageable)
            .map(tourOperatorMapper::toDto);
    }


    /**
     * Get one tourOperator by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TourOperatorDTO> findOne(Long id) {
        log.debug("Request to get TourOperator : {}", id);
        return tourOperatorRepository.findById(id)
            .map(tourOperatorMapper::toDto);
    }

    /**
     * Delete the tourOperator by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TourOperator : {}", id);
        tourOperatorRepository.deleteById(id);
    }
}
