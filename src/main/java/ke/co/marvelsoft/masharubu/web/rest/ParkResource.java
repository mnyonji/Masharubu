package ke.co.marvelsoft.masharubu.web.rest;

import ke.co.marvelsoft.masharubu.service.ParkService;
import ke.co.marvelsoft.masharubu.web.rest.errors.BadRequestAlertException;
import ke.co.marvelsoft.masharubu.service.dto.ParkDTO;

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
 * REST controller for managing {@link ke.co.marvelsoft.masharubu.domain.Park}.
 */
@RestController
@RequestMapping("/api")
public class ParkResource {

    private final Logger log = LoggerFactory.getLogger(ParkResource.class);

    private static final String ENTITY_NAME = "park";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParkService parkService;

    public ParkResource(ParkService parkService) {
        this.parkService = parkService;
    }

    /**
     * {@code POST  /parks} : Create a new park.
     *
     * @param parkDTO the parkDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new parkDTO, or with status {@code 400 (Bad Request)} if the park has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/parks")
    public ResponseEntity<ParkDTO> createPark(@Valid @RequestBody ParkDTO parkDTO) throws URISyntaxException {
        log.debug("REST request to save Park : {}", parkDTO);
        if (parkDTO.getId() != null) {
            throw new BadRequestAlertException("A new park cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParkDTO result = parkService.save(parkDTO);
        return ResponseEntity.created(new URI("/api/parks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /parks} : Updates an existing park.
     *
     * @param parkDTO the parkDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated parkDTO,
     * or with status {@code 400 (Bad Request)} if the parkDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the parkDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/parks")
    public ResponseEntity<ParkDTO> updatePark(@Valid @RequestBody ParkDTO parkDTO) throws URISyntaxException {
        log.debug("REST request to update Park : {}", parkDTO);
        if (parkDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ParkDTO result = parkService.save(parkDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, parkDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /parks} : get all the parks.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of parks in body.
     */
    @GetMapping("/parks")
    public ResponseEntity<List<ParkDTO>> getAllParks(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Parks");
        Page<ParkDTO> page = parkService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /parks/:id} : get the "id" park.
     *
     * @param id the id of the parkDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the parkDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/parks/{id}")
    public ResponseEntity<ParkDTO> getPark(@PathVariable Long id) {
        log.debug("REST request to get Park : {}", id);
        Optional<ParkDTO> parkDTO = parkService.findOne(id);
        return ResponseUtil.wrapOrNotFound(parkDTO);
    }

    /**
     * {@code DELETE  /parks/:id} : delete the "id" park.
     *
     * @param id the id of the parkDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/parks/{id}")
    public ResponseEntity<Void> deletePark(@PathVariable Long id) {
        log.debug("REST request to delete Park : {}", id);
        parkService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
