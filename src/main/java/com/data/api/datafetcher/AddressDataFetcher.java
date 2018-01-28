package com.data.api.datafetcher;

import com.data.api.model.Address;
import com.data.api.model.Person;
import com.data.api.repository.AddressRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author pvthuan
 */
@Component
public class AddressDataFetcher implements DataFetcher<List<Address>>{

  private final AddressRepository repository;

  public AddressDataFetcher(final AddressRepository repository){
    this.repository = repository;
  }

  @Override
  public List<Address> get(DataFetchingEnvironment env) {
    Person person = env.getSource();
    return repository.getAddress(person.getId());
  }
}
