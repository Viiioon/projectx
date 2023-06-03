package ch.zhaw.projectx.repository;

import ch.zhaw.projectx.entity.Belief;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BeliefRepository extends JpaRepository<Belief,Long> {

    //
    @Query("SELECT CASE WHEN COUNT(b) = 19734 THEN true ELSE false END FROM Belief b")
    boolean areBeliefsAlreadyInserted();

}