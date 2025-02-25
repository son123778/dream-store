package com.example.dreambackend.repositories;

import com.example.dreambackend.entities.VaiTro;

import com.example.dreambackend.responses.VaiTroResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaiTroRepository extends JpaRepository<VaiTro, Integer> {

}
