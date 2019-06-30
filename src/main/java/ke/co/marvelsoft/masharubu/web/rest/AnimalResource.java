package ke.co.marvelsoft.masharubu.web.rest;

import ke.co.marvelsoft.masharubu.service.AnimalService;
import ke.co.marvelsoft.masharubu.web.rest.errors.BadRequestAlertException;
import ke.co.marvelsoft.masharubu.service.dto.AnimalDTO;

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
 * REST controller for managing {@link ke.co.marvelsoft.masharubu.domain.Animal}.
 */
@RestController
@RequestMapping("/api")
public class AnimalResource {

    private final Logger log = LoggerFactory.getLogger(AnimalResource.class);

    private static final String ENTITY_NAME = "animal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnimalService animalService;

    public AnimalResource(AnimalService animalService) {
        this.animalService = animalService;
    }

    /**
     * {@code POST  /animals} : Create a new animal.
     *
     * @param animalDTO the animalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new animalDTO, or with status {@code 400 (Bad Request)} if the animal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/animals")
    public ResponseEntity<AnimalDTO> createAnimal(@Valid @RequestBody AnimalDTO animalDTO) throws URISyntaxException {
        log.debug("REST request to save Animal : {}", animalDTO);
        if (animalDTO.getId() != null) {
            throw new BadRequestAlertException("A new animal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnimalDTO result = animalService.save(animalDTO);
        return ResponseEntity.created(new URI("/api/animals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /animals} : Updates an existing animal.
     *
     * @param animalDTO the animalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated animalDTO,
     * or with status {@code 400 (Bad Request)} if the animalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the animalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/animals")
    public ResponseEntity<AnimalDTO> updateAnimal(@Valid @RequestBody AnimalDTO animalDTO) throws URISyntaxException {
        log.debug("REST request to update Animal : {}", animalDTO);
        if (animalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AnimalDTO result = animalService.save(animalDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, animalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /animals} : get all the animals.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of animals in body.
     */
    @GetMapping("/animals")
    public ResponseEntity<List<AnimalDTO>> getAllAnimals(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Animals");
        Page<AnimalDTO> page = animalService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /animals/:id} : get the "id" animal.
     *
     * @param id the id of the animalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the animalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/animals/{id}")
    public ResponseEntity<AnimalDTO> getAnimal(@PathVariable Long id) {
        log.debug("REST request to get Animal : {}", id);
        Optional<AnimalDTO> animalDTO = animalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(animalDTO);
    }

    /**
     * {@code DELETE  /animals/:id} : delete the "id" animal.
     *
     * @param id the id of the animalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/animals/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        log.debug("REST request to delete Animal : {}", id);
        animalService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
