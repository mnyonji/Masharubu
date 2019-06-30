package ke.co.marvelsoft.masharubu.repository;

import ke.co.marvelsoft.masharubu.domain.TourOperator;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TourOperator entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TourOperatorRepository extends JpaRepository<TourOperator, Long> {

}
