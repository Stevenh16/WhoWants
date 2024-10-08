package model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Person extends JpaRepository<Person, Long> {
}
