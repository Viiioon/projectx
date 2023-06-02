package ch.zhaw.projectx.repository;

import ch.zhaw.projectx.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DomainRepository extends JpaRepository<Domain, Long> {

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Domain d WHERE d.name = ?1")
    boolean existsCategory(String name);
}
