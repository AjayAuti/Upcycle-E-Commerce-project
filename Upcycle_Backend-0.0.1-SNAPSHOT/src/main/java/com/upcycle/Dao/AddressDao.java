package com.upcycle.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upcycle.Entity.Address;


@Repository
public interface AddressDao extends JpaRepository<Address, Integer> {

}
