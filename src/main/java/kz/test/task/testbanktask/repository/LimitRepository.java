package kz.test.task.testbanktask.repository;

import kz.test.task.testbanktask.domain.model.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {
    Optional<Limit> findTopByOrderByIdDesc();
}
