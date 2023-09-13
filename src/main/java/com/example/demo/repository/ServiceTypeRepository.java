package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ServiceType;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Integer> {

}
