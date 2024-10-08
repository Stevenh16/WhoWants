package model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Thing extends JpaRepository<Thing, Long> {
}
