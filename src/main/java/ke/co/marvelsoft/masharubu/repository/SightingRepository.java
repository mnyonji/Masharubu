package ke.co.marvelsoft.masharubu.repository;

import ke.co.marvelsoft.masharubu.domain.Sighting;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Sighting entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SightingRepository extends JpaRepository<Sighting, Long> {

}
