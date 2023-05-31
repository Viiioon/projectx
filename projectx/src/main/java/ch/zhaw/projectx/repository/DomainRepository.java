package ch.zhaw.projectx.repository;

import ch.zhaw.projectx.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DomainRepository extends JpaRepository<Domain, Long> {

}
