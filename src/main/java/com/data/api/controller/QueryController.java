package com.data.api.controller;

import com.data.api.datafetcher.AddressDataFetcher;
import com.data.api.datafetcher.AllPeopleDataFetcher;
import com.data.api.datafetcher.PersonDataFetcher;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.File;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;
/**
 * @author pvthuan
 */
@RestController
@Slf4j
public class QueryController {
  @Value("classpath:person.graphqls")
  private Resource schemaResource;

  private GraphQL graphQL;

  private final AllPeopleDataFetcher allPeopleDataFetcher;

  private final PersonDataFetcher personDataFetcher;

  private final AddressDataFetcher addressDataFetcher;

  public QueryController(final AllPeopleDataFetcher allPeopleDataFetcher,
      PersonDataFetcher personDataFetcher,
      AddressDataFetcher addressDataFetcher
      ){
    this.allPeopleDataFetcher = allPeopleDataFetcher;
    this.personDataFetcher = personDataFetcher;
    this.addressDataFetcher = addressDataFetcher;
  }

  @PostConstruct
  public void loadSchema() throws IOException{
    File schemaFile = schemaResource.getFile();
    TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
    RuntimeWiring wiring = buildRuntimeWiring();
    GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
    graphQL = GraphQL.newGraphQL(schema).build();
  }

  public RuntimeWiring buildRuntimeWiring(){
    return newRuntimeWiring()
        .type("Query", typeWiring->typeWiring
            .dataFetcher("allPeople", allPeopleDataFetcher)
            .dataFetcher("person", personDataFetcher))
        .type("Person", typeWiring->typeWiring.dataFetcher("addresses", addressDataFetcher))
        .build();
  }
  @RequestMapping(value = "/query", method = RequestMethod.POST)
  public ResponseEntity query(@RequestBody String query){
//    return ResponseEntity.ok("Hello API draft");
    ExecutionResult result = graphQL.execute(query);
    log.info(String.valueOf(result.getErrors()));
    return ResponseEntity.ok(result.getData());
  }
}
