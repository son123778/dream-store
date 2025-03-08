package com.example.dreambackend.repositories;

import com.example.dreambackend.entities.VaiTro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VaiTroRepository extends JpaRepository<VaiTro, Integer> {
    Optional<VaiTro> findByTen(String ten);
}
