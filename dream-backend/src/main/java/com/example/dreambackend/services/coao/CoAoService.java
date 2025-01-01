package com.example.dreambackend.services.coao;

import com.example.dreambackend.entities.CoAo;
import com.example.dreambackend.repositories.CoAoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoAoService implements ICoAoService {
    @Autowired
    CoAoRepository coAoRepository;

    @Override
    public List<CoAo> getAllCoAo() {
        return coAoRepository.findAll();
    }
}
