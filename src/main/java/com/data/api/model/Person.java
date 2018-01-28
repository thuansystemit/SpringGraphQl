package com.data.api.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pvthuan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {
  @Id
  @Column(name = "personId")
  private String id;
  private String firstName;
  private String lastName;
  private int age;
  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(name = "Person_Address",
    joinColumns = {@JoinColumn(name = "addressId")},
      inverseJoinColumns = {@JoinColumn(name = "personId")}
  )
  private Set<Address> addresses = new HashSet<>();
//
  public Person(String id, String firstName, String lastName, int age){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

//  public static void main(String [] args){
//    Address address = new Address("1001", "this is a street 1", "block 1", "region 1", "country 1", "zip 1");
//    Address address2 = new Address("1002", "this is a street 2", "block 2", "region 2", "country 2", "zip 2");
//    Address address3 = new Address("1003", "this is a street 3", "block 3", "region 3", "country 3", "zip 3");
//    Address address4 = new Address("1004", "this is a street 4", "block 4", "region 4", "country 4", "zip 4");
//    Address address5 = new Address("1005", "this is a street 5", "block 5", "region 5", "country 5", "zip 5");
//    Stream.of(address).collect(Collectors.toCollection(HashSet::new));
//    Stream.of(address2).collect(Collectors.toCollection(HashSet::new));
//    Stream.of(address3).collect(Collectors.toCollection(HashSet::new));
//    Stream.of(address4).collect(Collectors.toCollection(HashSet::new));
//    Stream.of(address5).collect(Collectors.toCollection(HashSet::new));
//  }
}
