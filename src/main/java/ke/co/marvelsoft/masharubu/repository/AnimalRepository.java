package ke.co.marvelsoft.masharubu.repository;

import ke.co.marvelsoft.masharubu.domain.Animal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Animal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
