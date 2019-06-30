package ke.co.marvelsoft.masharubu.web.rest;

import ke.co.marvelsoft.masharubu.MasharubuApp;
import ke.co.marvelsoft.masharubu.domain.Park;
import ke.co.marvelsoft.masharubu.repository.ParkRepository;
import ke.co.marvelsoft.masharubu.service.ParkService;
import ke.co.marvelsoft.masharubu.service.dto.ParkDTO;
import ke.co.marvelsoft.masharubu.service.mapper.ParkMapper;
import ke.co.marvelsoft.masharubu.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static ke.co.marvelsoft.masharubu.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link ParkResource} REST controller.
 */
@SpringBootTest(classes = MasharubuApp.class)
public class ParkResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    @Autowired
    private ParkRepository parkRepository;

    @Autowired
    private ParkMapper parkMapper;

    @Autowired
    private ParkService parkService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restParkMockMvc;

    private Park park;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ParkResource parkResource = new ParkResource(parkService);
        this.restParkMockMvc = MockMvcBuilders.standaloneSetup(parkResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Park createEntity(EntityManager em) {
        Park park = new Park()
            .name(DEFAULT_NAME)
            .location(DEFAULT_LOCATION);
        return park;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Park createUpdatedEntity(EntityManager em) {
        Park park = new Park()
            .name(UPDATED_NAME)
            .location(UPDATED_LOCATION);
        return park;
    }

    @BeforeEach
    public void initTest() {
        park = createEntity(em);
    }

    @Test
    @Transactional
    public void createPark() throws Exception {
        int databaseSizeBeforeCreate = parkRepository.findAll().size();

        // Create the Park
        ParkDTO parkDTO = parkMapper.toDto(park);
        restParkMockMvc.perform(post("/api/parks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parkDTO)))
            .andExpect(status().isCreated());

        // Validate the Park in the database
        List<Park> parkList = parkRepository.findAll();
        assertThat(parkList).hasSize(databaseSizeBeforeCreate + 1);
        Park testPark = parkList.get(parkList.size() - 1);
        assertThat(testPark.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPark.getLocation()).isEqualTo(DEFAULT_LOCATION);
    }

    @Test
    @Transactional
    public void createParkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = parkRepository.findAll().size();

        // Create the Park with an existing ID
        park.setId(1L);
        ParkDTO parkDTO = parkMapper.toDto(park);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParkMockMvc.perform(post("/api/parks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Park in the database
        List<Park> parkList = parkRepository.findAll();
        assertThat(parkList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = parkRepository.findAll().size();
        // set the field null
        park.setName(null);

        // Create the Park, which fails.
        ParkDTO parkDTO = parkMapper.toDto(park);

        restParkMockMvc.perform(post("/api/parks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parkDTO)))
            .andExpect(status().isBadRequest());

        List<Park> parkList = parkRepository.findAll();
        assertThat(parkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLocationIsRequired() throws Exception {
        int databaseSizeBeforeTest = parkRepository.findAll().size();
        // set the field null
        park.setLocation(null);

        // Create the Park, which fails.
        ParkDTO parkDTO = parkMapper.toDto(park);

        restParkMockMvc.perform(post("/api/parks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parkDTO)))
            .andExpect(status().isBadRequest());

        List<Park> parkList = parkRepository.findAll();
        assertThat(parkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllParks() throws Exception {
        // Initialize the database
        parkRepository.saveAndFlush(park);

        // Get all the parkList
        restParkMockMvc.perform(get("/api/parks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(park.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION.toString())));
    }
    
    @Test
    @Transactional
    public void getPark() throws Exception {
        // Initialize the database
        parkRepository.saveAndFlush(park);

        // Get the park
        restParkMockMvc.perform(get("/api/parks/{id}", park.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(park.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPark() throws Exception {
        // Get the park
        restParkMockMvc.perform(get("/api/parks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePark() throws Exception {
        // Initialize the database
        parkRepository.saveAndFlush(park);

        int databaseSizeBeforeUpdate = parkRepository.findAll().size();

        // Update the park
        Park updatedPark = parkRepository.findById(park.getId()).get();
        // Disconnect from session so that the updates on updatedPark are not directly saved in db
        em.detach(updatedPark);
        updatedPark
            .name(UPDATED_NAME)
            .location(UPDATED_LOCATION);
        ParkDTO parkDTO = parkMapper.toDto(updatedPark);

        restParkMockMvc.perform(put("/api/parks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parkDTO)))
            .andExpect(status().isOk());

        // Validate the Park in the database
        List<Park> parkList = parkRepository.findAll();
        assertThat(parkList).hasSize(databaseSizeBeforeUpdate);
        Park testPark = parkList.get(parkList.size() - 1);
        assertThat(testPark.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPark.getLocation()).isEqualTo(UPDATED_LOCATION);
    }

    @Test
    @Transactional
    public void updateNonExistingPark() throws Exception {
        int databaseSizeBeforeUpdate = parkRepository.findAll().size();

        // Create the Park
        ParkDTO parkDTO = parkMapper.toDto(park);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParkMockMvc.perform(put("/api/parks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Park in the database
        List<Park> parkList = parkRepository.findAll();
        assertThat(parkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePark() throws Exception {
        // Initialize the database
        parkRepository.saveAndFlush(park);

        int databaseSizeBeforeDelete = parkRepository.findAll().size();

        // Delete the park
        restParkMockMvc.perform(delete("/api/parks/{id}", park.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Park> parkList = parkRepository.findAll();
        assertThat(parkList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Park.class);
        Park park1 = new Park();
        park1.setId(1L);
        Park park2 = new Park();
        park2.setId(park1.getId());
        assertThat(park1).isEqualTo(park2);
        park2.setId(2L);
        assertThat(park1).isNotEqualTo(park2);
        park1.setId(null);
        assertThat(park1).isNotEqualTo(park2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParkDTO.class);
        ParkDTO parkDTO1 = new ParkDTO();
        parkDTO1.setId(1L);
        ParkDTO parkDTO2 = new ParkDTO();
        assertThat(parkDTO1).isNotEqualTo(parkDTO2);
        parkDTO2.setId(parkDTO1.getId());
        assertThat(parkDTO1).isEqualTo(parkDTO2);
        parkDTO2.setId(2L);
        assertThat(parkDTO1).isNotEqualTo(parkDTO2);
        parkDTO1.setId(null);
        assertThat(parkDTO1).isNotEqualTo(parkDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(parkMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(parkMapper.fromId(null)).isNull();
    }
}
