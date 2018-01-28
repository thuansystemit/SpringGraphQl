package com.data.api.datafetcher;

import com.data.api.model.Person;
import com.data.api.repository.PersonRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * @author pvthuan
 */
@Component
public class PersonDataFetcher implements DataFetcher<Person>{

  private final PersonRepository repository;

  public PersonDataFetcher(final PersonRepository repository){
    this.repository = repository;
  }
  @Override
  public Person get(DataFetchingEnvironment env) {
    Map arguments = env.getArguments();
    return repository.getOne((String)arguments.get("id"));
  }
}
