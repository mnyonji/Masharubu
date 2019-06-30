package ke.co.marvelsoft.masharubu.web.rest;

import ke.co.marvelsoft.masharubu.service.DriverService;
import ke.co.marvelsoft.masharubu.web.rest.errors.BadRequestAlertException;
import ke.co.marvelsoft.masharubu.service.dto.DriverDTO;

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
 * REST controller for managing {@link ke.co.marvelsoft.masharubu.domain.Driver}.
 */
@RestController
@RequestMapping("/api")
public class DriverResource {

    private final Logger log = LoggerFactory.getLogger(DriverResource.class);

    private static final String ENTITY_NAME = "driver";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DriverService driverService;

    public DriverResource(DriverService driverService) {
        this.driverService = driverService;
    }

    /**
     * {@code POST  /drivers} : Create a new driver.
     *
     * @param driverDTO the driverDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new driverDTO, or with status {@code 400 (Bad Request)} if the driver has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/drivers")
    public ResponseEntity<DriverDTO> createDriver(@Valid @RequestBody DriverDTO driverDTO) throws URISyntaxException {
        log.debug("REST request to save Driver : {}", driverDTO);
        if (driverDTO.getId() != null) {
            throw new BadRequestAlertException("A new driver cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DriverDTO result = driverService.save(driverDTO);
        return ResponseEntity.created(new URI("/api/drivers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /drivers} : Updates an existing driver.
     *
     * @param driverDTO the driverDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated driverDTO,
     * or with status {@code 400 (Bad Request)} if the driverDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the driverDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/drivers")
    public ResponseEntity<DriverDTO> updateDriver(@Valid @RequestBody DriverDTO driverDTO) throws URISyntaxException {
        log.debug("REST request to update Driver : {}", driverDTO);
        if (driverDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DriverDTO result = driverService.save(driverDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, driverDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /drivers} : get all the drivers.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of drivers in body.
     */
    @GetMapping("/drivers")
    public ResponseEntity<List<DriverDTO>> getAllDrivers(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Drivers");
        Page<DriverDTO> page = driverService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /drivers/:id} : get the "id" driver.
     *
     * @param id the id of the driverDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the driverDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/drivers/{id}")
    public ResponseEntity<DriverDTO> getDriver(@PathVariable Long id) {
        log.debug("REST request to get Driver : {}", id);
        Optional<DriverDTO> driverDTO = driverService.findOne(id);
        return ResponseUtil.wrapOrNotFound(driverDTO);
    }

    /**
     * {@code DELETE  /drivers/:id} : delete the "id" driver.
     *
     * @param id the id of the driverDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/drivers/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        log.debug("REST request to delete Driver : {}", id);
        driverService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
