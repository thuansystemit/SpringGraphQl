package com.data.api.loader;

import com.data.api.model.Person;
import com.data.api.repository.PersonRepository;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * @author pvthuan
 */
@Component
public class PersonLoader {

  private final PersonRepository repository;

  public PersonLoader(final PersonRepository repository){
    this.repository = repository;
  }

  @PostConstruct
  public void personLoader(){
    repository.save(new Person("1", "Hieu", "Ngo Minh",  30));
    repository.save(new Person("2", "Ha", "Huynh Xuan",  20));
    repository.save(new Person("3", "Ngoc", "Bui Bich",  25));
    repository.save(new Person("4", "Le", "Ly Kieu",  24));
    repository.save(new Person("5", "Tu", "Huynh Ngoc",  25));
  }
}
