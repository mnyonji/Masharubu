package ke.co.marvelsoft.masharubu.web.rest;

import ke.co.marvelsoft.masharubu.MasharubuApp;
import ke.co.marvelsoft.masharubu.domain.Sighting;
import ke.co.marvelsoft.masharubu.repository.SightingRepository;
import ke.co.marvelsoft.masharubu.service.SightingService;
import ke.co.marvelsoft.masharubu.service.dto.SightingDTO;
import ke.co.marvelsoft.masharubu.service.mapper.SightingMapper;
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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static ke.co.marvelsoft.masharubu.web.rest.TestUtil.sameInstant;
import static ke.co.marvelsoft.masharubu.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link SightingResource} REST controller.
 */
@SpringBootTest(classes = MasharubuApp.class)
public class SightingResourceIT {

    private static final byte[] DEFAULT_PICTURE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PICTURE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PICTURE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PICTURE_CONTENT_TYPE = "image/png";

    private static final Float DEFAULT_LATITUDE = 1F;
    private static final Float UPDATED_LATITUDE = 2F;

    private static final Float DEFAULT_LONGITUDE = 1F;
    private static final Float UPDATED_LONGITUDE = 2F;

    private static final Float DEFAULT_ALTITUDE = 1F;
    private static final Float UPDATED_ALTITUDE = 2F;

    private static final ZonedDateTime DEFAULT_DATE_SIGHTED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_SIGHTED = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private SightingRepository sightingRepository;

    @Autowired
    private SightingMapper sightingMapper;

    @Autowired
    private SightingService sightingService;

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

    private MockMvc restSightingMockMvc;

    private Sighting sighting;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SightingResource sightingResource = new SightingResource(sightingService);
        this.restSightingMockMvc = MockMvcBuilders.standaloneSetup(sightingResource)
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
    public static Sighting createEntity(EntityManager em) {
        Sighting sighting = new Sighting()
            .picture(DEFAULT_PICTURE)
            .pictureContentType(DEFAULT_PICTURE_CONTENT_TYPE)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE)
            .altitude(DEFAULT_ALTITUDE)
            .dateSighted(DEFAULT_DATE_SIGHTED)
            .description(DEFAULT_DESCRIPTION);
        return sighting;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sighting createUpdatedEntity(EntityManager em) {
        Sighting sighting = new Sighting()
            .picture(UPDATED_PICTURE)
            .pictureContentType(UPDATED_PICTURE_CONTENT_TYPE)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .altitude(UPDATED_ALTITUDE)
            .dateSighted(UPDATED_DATE_SIGHTED)
            .description(UPDATED_DESCRIPTION);
        return sighting;
    }

    @BeforeEach
    public void initTest() {
        sighting = createEntity(em);
    }

    @Test
    @Transactional
    public void createSighting() throws Exception {
        int databaseSizeBeforeCreate = sightingRepository.findAll().size();

        // Create the Sighting
        SightingDTO sightingDTO = sightingMapper.toDto(sighting);
        restSightingMockMvc.perform(post("/api/sightings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sightingDTO)))
            .andExpect(status().isCreated());

        // Validate the Sighting in the database
        List<Sighting> sightingList = sightingRepository.findAll();
        assertThat(sightingList).hasSize(databaseSizeBeforeCreate + 1);
        Sighting testSighting = sightingList.get(sightingList.size() - 1);
        assertThat(testSighting.getPicture()).isEqualTo(DEFAULT_PICTURE);
        assertThat(testSighting.getPictureContentType()).isEqualTo(DEFAULT_PICTURE_CONTENT_TYPE);
        assertThat(testSighting.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testSighting.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testSighting.getAltitude()).isEqualTo(DEFAULT_ALTITUDE);
        assertThat(testSighting.getDateSighted()).isEqualTo(DEFAULT_DATE_SIGHTED);
        assertThat(testSighting.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createSightingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sightingRepository.findAll().size();

        // Create the Sighting with an existing ID
        sighting.setId(1L);
        SightingDTO sightingDTO = sightingMapper.toDto(sighting);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSightingMockMvc.perform(post("/api/sightings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sightingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sighting in the database
        List<Sighting> sightingList = sightingRepository.findAll();
        assertThat(sightingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLatitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sightingRepository.findAll().size();
        // set the field null
        sighting.setLatitude(null);

        // Create the Sighting, which fails.
        SightingDTO sightingDTO = sightingMapper.toDto(sighting);

        restSightingMockMvc.perform(post("/api/sightings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sightingDTO)))
            .andExpect(status().isBadRequest());

        List<Sighting> sightingList = sightingRepository.findAll();
        assertThat(sightingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLongitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sightingRepository.findAll().size();
        // set the field null
        sighting.setLongitude(null);

        // Create the Sighting, which fails.
        SightingDTO sightingDTO = sightingMapper.toDto(sighting);

        restSightingMockMvc.perform(post("/api/sightings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sightingDTO)))
            .andExpect(status().isBadRequest());

        List<Sighting> sightingList = sightingRepository.findAll();
        assertThat(sightingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAltitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sightingRepository.findAll().size();
        // set the field null
        sighting.setAltitude(null);

        // Create the Sighting, which fails.
        SightingDTO sightingDTO = sightingMapper.toDto(sighting);

        restSightingMockMvc.perform(post("/api/sightings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sightingDTO)))
            .andExpect(status().isBadRequest());

        List<Sighting> sightingList = sightingRepository.findAll();
        assertThat(sightingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSightings() throws Exception {
        // Initialize the database
        sightingRepository.saveAndFlush(sighting);

        // Get all the sightingList
        restSightingMockMvc.perform(get("/api/sightings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sighting.getId().intValue())))
            .andExpect(jsonPath("$.[*].pictureContentType").value(hasItem(DEFAULT_PICTURE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(Base64Utils.encodeToString(DEFAULT_PICTURE))))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].altitude").value(hasItem(DEFAULT_ALTITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].dateSighted").value(hasItem(sameInstant(DEFAULT_DATE_SIGHTED))))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getSighting() throws Exception {
        // Initialize the database
        sightingRepository.saveAndFlush(sighting);

        // Get the sighting
        restSightingMockMvc.perform(get("/api/sightings/{id}", sighting.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sighting.getId().intValue()))
            .andExpect(jsonPath("$.pictureContentType").value(DEFAULT_PICTURE_CONTENT_TYPE))
            .andExpect(jsonPath("$.picture").value(Base64Utils.encodeToString(DEFAULT_PICTURE)))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.altitude").value(DEFAULT_ALTITUDE.doubleValue()))
            .andExpect(jsonPath("$.dateSighted").value(sameInstant(DEFAULT_DATE_SIGHTED)))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSighting() throws Exception {
        // Get the sighting
        restSightingMockMvc.perform(get("/api/sightings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSighting() throws Exception {
        // Initialize the database
        sightingRepository.saveAndFlush(sighting);

        int databaseSizeBeforeUpdate = sightingRepository.findAll().size();

        // Update the sighting
        Sighting updatedSighting = sightingRepository.findById(sighting.getId()).get();
        // Disconnect from session so that the updates on updatedSighting are not directly saved in db
        em.detach(updatedSighting);
        updatedSighting
            .picture(UPDATED_PICTURE)
            .pictureContentType(UPDATED_PICTURE_CONTENT_TYPE)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .altitude(UPDATED_ALTITUDE)
            .dateSighted(UPDATED_DATE_SIGHTED)
            .description(UPDATED_DESCRIPTION);
        SightingDTO sightingDTO = sightingMapper.toDto(updatedSighting);

        restSightingMockMvc.perform(put("/api/sightings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sightingDTO)))
            .andExpect(status().isOk());

        // Validate the Sighting in the database
        List<Sighting> sightingList = sightingRepository.findAll();
        assertThat(sightingList).hasSize(databaseSizeBeforeUpdate);
        Sighting testSighting = sightingList.get(sightingList.size() - 1);
        assertThat(testSighting.getPicture()).isEqualTo(UPDATED_PICTURE);
        assertThat(testSighting.getPictureContentType()).isEqualTo(UPDATED_PICTURE_CONTENT_TYPE);
        assertThat(testSighting.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testSighting.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testSighting.getAltitude()).isEqualTo(UPDATED_ALTITUDE);
        assertThat(testSighting.getDateSighted()).isEqualTo(UPDATED_DATE_SIGHTED);
        assertThat(testSighting.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingSighting() throws Exception {
        int databaseSizeBeforeUpdate = sightingRepository.findAll().size();

        // Create the Sighting
        SightingDTO sightingDTO = sightingMapper.toDto(sighting);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSightingMockMvc.perform(put("/api/sightings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sightingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sighting in the database
        List<Sighting> sightingList = sightingRepository.findAll();
        assertThat(sightingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSighting() throws Exception {
        // Initialize the database
        sightingRepository.saveAndFlush(sighting);

        int databaseSizeBeforeDelete = sightingRepository.findAll().size();

        // Delete the sighting
        restSightingMockMvc.perform(delete("/api/sightings/{id}", sighting.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Sighting> sightingList = sightingRepository.findAll();
        assertThat(sightingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sighting.class);
        Sighting sighting1 = new Sighting();
        sighting1.setId(1L);
        Sighting sighting2 = new Sighting();
        sighting2.setId(sighting1.getId());
        assertThat(sighting1).isEqualTo(sighting2);
        sighting2.setId(2L);
        assertThat(sighting1).isNotEqualTo(sighting2);
        sighting1.setId(null);
        assertThat(sighting1).isNotEqualTo(sighting2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SightingDTO.class);
        SightingDTO sightingDTO1 = new SightingDTO();
        sightingDTO1.setId(1L);
        SightingDTO sightingDTO2 = new SightingDTO();
        assertThat(sightingDTO1).isNotEqualTo(sightingDTO2);
        sightingDTO2.setId(sightingDTO1.getId());
        assertThat(sightingDTO1).isEqualTo(sightingDTO2);
        sightingDTO2.setId(2L);
        assertThat(sightingDTO1).isNotEqualTo(sightingDTO2);
        sightingDTO1.setId(null);
        assertThat(sightingDTO1).isNotEqualTo(sightingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sightingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sightingMapper.fromId(null)).isNull();
    }
}
