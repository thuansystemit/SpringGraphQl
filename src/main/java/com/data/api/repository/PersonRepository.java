package com.data.api.repository;

import com.data.api.model.Address;
import com.data.api.model.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author pvthuan
 */
@Component
public interface PersonRepository extends JpaRepository<Person, String> {
}
