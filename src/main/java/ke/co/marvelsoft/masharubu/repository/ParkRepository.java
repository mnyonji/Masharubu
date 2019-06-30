package ke.co.marvelsoft.masharubu.repository;

import ke.co.marvelsoft.masharubu.domain.Park;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Park entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParkRepository extends JpaRepository<Park, Long> {

}
