package com.example.dreambackend.services.xuatxu;


import com.example.dreambackend.entities.XuatXu;
import com.example.dreambackend.repositories.XuatXuRepository;
import com.example.dreambackend.requests.XuatXuRequest;
import com.example.dreambackend.responses.XuatXuRespone;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class XuatXuService implements IXuatXuService {
    @Autowired
    XuatXuRepository xuatXuRepository;

    @Override
    public List<XuatXuRespone> getAllXuatXu() {
        return xuatXuRepository.getAllXuatXuRespone();
    }

    @Override
    public XuatXu getXuatXu(Integer id) {
        return xuatXuRepository.findById(id).orElseThrow(()->
                new RuntimeException("Không tìm thấy id xuất xứ"));
    }

    @Override
    public XuatXu addXuatXu(XuatXuRequest xuatXuRequest) {
        XuatXu xuatXu = new XuatXu();
        BeanUtils.copyProperties(xuatXuRequest, xuatXu);
        xuatXu.setNgayTao(LocalDate.now());
        xuatXu.setNgaySua(LocalDate.now());
        return xuatXuRepository.save(xuatXu);
    }

    @Override
    public XuatXu updateXuatXu(XuatXuRequest xuatXuRequest) {
        XuatXu xuatXuUpDate = xuatXuRepository.findById(xuatXuRequest.getId()).orElseThrow(()->
                new RuntimeException("Không tìm thấy xuất xứ với id: " + xuatXuRequest.getId()));
        BeanUtils.copyProperties(xuatXuRequest, xuatXuUpDate,"id","ngayTao");
        xuatXuUpDate.setNgaySua(LocalDate.now());
        return xuatXuRepository.save(xuatXuUpDate);
    }
}
