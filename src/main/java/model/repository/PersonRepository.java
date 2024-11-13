package model.repository;

import model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p JOIN FETCH p.roles WHERE p.username = :username")
    Optional<Person> findByUsername(@Param("username") String username);
    Optional<Person> findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
