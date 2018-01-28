package com.data.api.repository;

import com.data.api.model.Address;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author pvthuan
 */
@Component
public class AddressRepository{
  @Value("classpath:StaysAt.csv")
  private Resource resourceStaysAt;

  @Value("classpath:Addresses.csv")
  private Resource addressResources;

  public List<Address> getAddress(String personId){
    List<String> addressIds = null;
    List<Address> addresses = null;
    try{
      addressIds = getAddressIds(personId);
      addresses = getAddresses(addressIds);
    }catch (Exception ex){
      ex.printStackTrace();
    }
    return addresses;
  }

  private List<String> getAddressIds(String personId) throws IOException{
    List<String> addressId = new ArrayList<>();
    File file = resourceStaysAt.getFile();
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()){
      String line = scanner.nextLine();
      String[] columns = line.split(",");
      if(columns[0].equals(personId)){
        addressId.add(columns[1]);
      }
    }

    return addressId;
  }

  public List<Address> getAddresses(List<String> addressIds) throws IOException{
    List<Address> addreses = new ArrayList<>();
    Map<String,Address> map = getAddress();
    for(String addressId: addressIds){
      addreses.add(map.get(addressId));
    }
    return addreses;
  }

  public Map<String, Address> getAddress() throws IOException{
    Map<String, Address> addresses = new HashMap<>();
    File file = addressResources.getFile();
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()){
      String line = scanner.nextLine();
      String[] columns = line.split(",");
      Address address = new Address(columns[0], columns[1], columns[2],
          columns[3], columns[4], columns[5]);
      addresses.put(columns[0], address);
    }
    return addresses;
  }
}
