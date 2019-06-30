package ke.co.marvelsoft.masharubu.web.rest;

import ke.co.marvelsoft.masharubu.service.SightingService;
import ke.co.marvelsoft.masharubu.web.rest.errors.BadRequestAlertException;
import ke.co.marvelsoft.masharubu.service.dto.SightingDTO;

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
 * REST controller for managing {@link ke.co.marvelsoft.masharubu.domain.Sighting}.
 */
@RestController
@RequestMapping("/api")
public class SightingResource {

    private final Logger log = LoggerFactory.getLogger(SightingResource.class);

    private static final String ENTITY_NAME = "sighting";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SightingService sightingService;

    public SightingResource(SightingService sightingService) {
        this.sightingService = sightingService;
    }

    /**
     * {@code POST  /sightings} : Create a new sighting.
     *
     * @param sightingDTO the sightingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sightingDTO, or with status {@code 400 (Bad Request)} if the sighting has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sightings")
    public ResponseEntity<SightingDTO> createSighting(@Valid @RequestBody SightingDTO sightingDTO) throws URISyntaxException {
        log.debug("REST request to save Sighting : {}", sightingDTO);
        if (sightingDTO.getId() != null) {
            throw new BadRequestAlertException("A new sighting cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SightingDTO result = sightingService.save(sightingDTO);
        return ResponseEntity.created(new URI("/api/sightings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sightings} : Updates an existing sighting.
     *
     * @param sightingDTO the sightingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sightingDTO,
     * or with status {@code 400 (Bad Request)} if the sightingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sightingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sightings")
    public ResponseEntity<SightingDTO> updateSighting(@Valid @RequestBody SightingDTO sightingDTO) throws URISyntaxException {
        log.debug("REST request to update Sighting : {}", sightingDTO);
        if (sightingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SightingDTO result = sightingService.save(sightingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sightingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sightings} : get all the sightings.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sightings in body.
     */
    @GetMapping("/sightings")
    public ResponseEntity<List<SightingDTO>> getAllSightings(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Sightings");
        Page<SightingDTO> page = sightingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sightings/:id} : get the "id" sighting.
     *
     * @param id the id of the sightingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sightingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sightings/{id}")
    public ResponseEntity<SightingDTO> getSighting(@PathVariable Long id) {
        log.debug("REST request to get Sighting : {}", id);
        Optional<SightingDTO> sightingDTO = sightingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sightingDTO);
    }

    /**
     * {@code DELETE  /sightings/:id} : delete the "id" sighting.
     *
     * @param id the id of the sightingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sightings/{id}")
    public ResponseEntity<Void> deleteSighting(@PathVariable Long id) {
        log.debug("REST request to delete Sighting : {}", id);
        sightingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
