package com.data.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
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
  private String id;
  private String firstName;
  private String lastName;
  private int age;
}
