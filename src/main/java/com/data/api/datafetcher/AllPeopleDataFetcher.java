package com.data.api.datafetcher;

import com.data.api.model.Person;
import com.data.api.repository.PersonRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author pvthuan
 */
@Component
public class AllPeopleDataFetcher implements DataFetcher<List<Person>> {

  private final PersonRepository repository;

  public AllPeopleDataFetcher(PersonRepository repository){
    this.repository = repository;
  }

  @Override
  public List<Person> get(DataFetchingEnvironment env) {
    return repository.findAll();
  }
}
