package ch.zhaw.projectx.repository;

import ch.zhaw.projectx.entity.Theorem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheoremRepository extends JpaRepository<Theorem, Long> {

}
