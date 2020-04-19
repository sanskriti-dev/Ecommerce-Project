package com.tothenew.ecommerceapp.repositories;

import com.tothenew.ecommerceapp.entities.users.Address;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface AddressRepo extends CrudRepository<Address,Long> {

    @Transactional
    @Modifying
    @Query(value = "delete from address where id=:id",nativeQuery = true)
    void deleteById(@Param("id") Long id);
}
