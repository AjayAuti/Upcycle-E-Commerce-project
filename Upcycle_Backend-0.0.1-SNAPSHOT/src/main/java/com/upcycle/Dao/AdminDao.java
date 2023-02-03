package com.upcycle.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upcycle.Entity.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, String> {

}
