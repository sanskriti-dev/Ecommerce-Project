package com.tothenew.ecommerceapp.repositories;

import com.tothenew.ecommerceapp.entities.users.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepo extends CrudRepository<Address,Long> {
}
