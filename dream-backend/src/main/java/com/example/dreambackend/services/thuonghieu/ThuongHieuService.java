package com.example.dreambackend.services.thuonghieu;


import com.example.dreambackend.dtos.ThuongHieuDto;

import com.example.dreambackend.entities.ThuongHieu;
import com.example.dreambackend.repositories.ThuongHieuRepository;
import com.example.dreambackend.requests.ThuongHieuRequest;
import com.example.dreambackend.respones.ThuongHieuRespone;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class ThuongHieuService implements IThuongHieuService{
    @Autowired
    ThuongHieuRepository thuongHieuRepository;

    @Override
    public List<ThuongHieuRespone> getAllThuongHieu() {
        return thuongHieuRepository.getAllThuongHieuRespones();
    }

    @Override
    public ThuongHieu getThuongHieu(Integer id) {
        return thuongHieuRepository.findById(id).orElseThrow(()->
                new RuntimeException("Không tìm thấy id thương hiệu"));
    }

    @Override
    public ThuongHieu addThuongHieu(ThuongHieuRequest thuongHieuRequest) {
        ThuongHieu thuongHieu = new ThuongHieu();
        BeanUtils.copyProperties(thuongHieuRequest, thuongHieu);
        thuongHieu.setNgayTao(LocalDate.now());
        thuongHieu.setNgaySua(LocalDate.now());
        return thuongHieuRepository.save(thuongHieu);
    }

    @Override
    public ThuongHieu updateThuongHieu(ThuongHieuRequest thuongHieuRequest) {
        ThuongHieu thuongHieuUpdate = thuongHieuRepository.findById(thuongHieuRequest.getId()).orElseThrow(()->
                new RuntimeException("Không tìm thấy sản phẩm với id: "+thuongHieuRequest.getId()));
        BeanUtils.copyProperties(thuongHieuRequest, thuongHieuUpdate,"id","ngayTao");
        thuongHieuUpdate.setNgaySua(LocalDate.now());
        return thuongHieuRepository.save(thuongHieuUpdate);
    }
}
