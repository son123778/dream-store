package com.example.dreambackend.services;

import com.example.dreambackend.entities.ThuongHieu;
import com.example.dreambackend.repositories.ThuongHieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ThuongHieuService implements IThuongHieuService{
    @Autowired
    ThuongHieuRepository thuongHieuRepository;
    @Override
    public List<ThuongHieu> getAllThuongHieu() {
        return thuongHieuRepository.findAll();
    }
}
