package com.example.dreambackend.services.mausac;

import com.example.dreambackend.entities.MauSac;
import com.example.dreambackend.repositories.MauSacRepository;
import com.example.dreambackend.requests.MauSacRequest;
import com.example.dreambackend.respones.MauSacRepone;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class MauSacService implements IMauSacService{
    @Autowired
    MauSacRepository mauSacRepository;
    @Override
    public List<MauSacRepone> getAllMauSac() {
        return mauSacRepository.getAllMauSacRepone();
    }

    @Override
    public MauSac getMauSacById(Integer id) {
        return mauSacRepository.findById(id).orElseThrow(()->
                new RuntimeException("Không tìm thấy id màu sắc"));
    }

    @Override
    public MauSac addMauSac(MauSacRequest mauSacRequest) {
        MauSac mauSac = new MauSac();
        BeanUtils.copyProperties(mauSacRequest, mauSac);
        mauSac.setNgayTao(LocalDate.now());
        mauSac.setNgaySua(LocalDate.now());
        return mauSacRepository.save(mauSac);
    }

    @Override
    public MauSac updateMauSac(MauSacRequest mauSacRequest) {
        MauSac mauSacUpdate = mauSacRepository.findById(mauSacRequest.getId()).orElseThrow(()->
                new RuntimeException("Không tìm thấy màu sắc với id: "+mauSacRequest.getId()));
        // copy thuộc tính từ request sang đối tượng
        BeanUtils.copyProperties(mauSacRequest, mauSacUpdate,"id","ngayTao");
        mauSacUpdate.setNgaySua(LocalDate.now());
        return mauSacRepository.save(mauSacUpdate);
    }
}
