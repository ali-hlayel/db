package de.db.domain.repository;

import de.db.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long>, JpaSpecificationExecutor<Station> {

    Optional<Station> findByShortcodeIgnoreCase(String hortcode);
}
