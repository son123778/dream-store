package com.example.dreambackend.services.xuatxu;

import com.example.dreambackend.entities.XuatXu;
import com.example.dreambackend.repositories.XuatXuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XuatXuService implements IXuatXuService {
    @Autowired
    XuatXuRepository xuatXuRepository;
    @Override
    public List<XuatXu> getAllXuatXu() {
        return xuatXuRepository.findAll();
    }
}
