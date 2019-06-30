package ke.co.marvelsoft.masharubu.web.rest;

import ke.co.marvelsoft.masharubu.service.TourOperatorService;
import ke.co.marvelsoft.masharubu.web.rest.errors.BadRequestAlertException;
import ke.co.marvelsoft.masharubu.service.dto.TourOperatorDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ke.co.marvelsoft.masharubu.domain.TourOperator}.
 */
@RestController
@RequestMapping("/api")
public class TourOperatorResource {

    private final Logger log = LoggerFactory.getLogger(TourOperatorResource.class);

    private static final String ENTITY_NAME = "tourOperator";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TourOperatorService tourOperatorService;

    public TourOperatorResource(TourOperatorService tourOperatorService) {
        this.tourOperatorService = tourOperatorService;
    }

    /**
     * {@code POST  /tour-operators} : Create a new tourOperator.
     *
     * @param tourOperatorDTO the tourOperatorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tourOperatorDTO, or with status {@code 400 (Bad Request)} if the tourOperator has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tour-operators")
    public ResponseEntity<TourOperatorDTO> createTourOperator(@Valid @RequestBody TourOperatorDTO tourOperatorDTO) throws URISyntaxException {
        log.debug("REST request to save TourOperator : {}", tourOperatorDTO);
        if (tourOperatorDTO.getId() != null) {
            throw new BadRequestAlertException("A new tourOperator cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TourOperatorDTO result = tourOperatorService.save(tourOperatorDTO);
        return ResponseEntity.created(new URI("/api/tour-operators/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tour-operators} : Updates an existing tourOperator.
     *
     * @param tourOperatorDTO the tourOperatorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tourOperatorDTO,
     * or with status {@code 400 (Bad Request)} if the tourOperatorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tourOperatorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tour-operators")
    public ResponseEntity<TourOperatorDTO> updateTourOperator(@Valid @RequestBody TourOperatorDTO tourOperatorDTO) throws URISyntaxException {
        log.debug("REST request to update TourOperator : {}", tourOperatorDTO);
        if (tourOperatorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TourOperatorDTO result = tourOperatorService.save(tourOperatorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tourOperatorDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tour-operators} : get all the tourOperators.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tourOperators in body.
     */
    @GetMapping("/tour-operators")
    public ResponseEntity<List<TourOperatorDTO>> getAllTourOperators(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of TourOperators");
        Page<TourOperatorDTO> page = tourOperatorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tour-operators/:id} : get the "id" tourOperator.
     *
     * @param id the id of the tourOperatorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tourOperatorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tour-operators/{id}")
    public ResponseEntity<TourOperatorDTO> getTourOperator(@PathVariable Long id) {
        log.debug("REST request to get TourOperator : {}", id);
        Optional<TourOperatorDTO> tourOperatorDTO = tourOperatorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tourOperatorDTO);
    }

    /**
     * {@code DELETE  /tour-operators/:id} : delete the "id" tourOperator.
     *
     * @param id the id of the tourOperatorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tour-operators/{id}")
    public ResponseEntity<Void> deleteTourOperator(@PathVariable Long id) {
        log.debug("REST request to delete TourOperator : {}", id);
        tourOperatorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
