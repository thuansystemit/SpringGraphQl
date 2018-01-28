package com.data.api.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

public class Address {
  @Id
  @Column(name = "addressId")
  private String id;
  private String street;
  private String block;
  private String region;
  private String country;
  private String zip;
  @ManyToMany(mappedBy = "addresses")
  private Set<Person> persons = new HashSet<>();

  public Address(String id, String street, String block,
      String region, String country, String zip){
    this.id = id;
    this.street = street;
    this.block = block;
    this.region = region;
    this.country = country;
    this.zip = zip;
  }
}
