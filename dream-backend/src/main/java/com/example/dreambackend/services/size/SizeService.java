package com.example.dreambackend.services.size;

import com.example.dreambackend.entities.Size;
import com.example.dreambackend.repositories.SizeRepository;
import com.example.dreambackend.requests.SizeRequest;
import com.example.dreambackend.responses.SizeRespone;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SizeService implements ISizeService {
    @Autowired
    SizeRepository sizeRepository;
    @Override
    public List<SizeRespone> getAllSize() {
        return sizeRepository.getAllSizeRespones();
    }

    @Override
    public Size addSize(SizeRequest sizeRequest) {
        Size size = new Size();
        BeanUtils.copyProperties(sizeRequest, size);
        size.setNgayTao(LocalDate.now());
        size.setNgaySua(LocalDate.now());
        return sizeRepository.save(size);
    }

    @Override
    public Size updateSize(SizeRequest sizeRequest) {
        Size sizeUpdate = sizeRepository.findById(sizeRequest.getId()).orElseThrow(()->
                new RuntimeException("Không tìm thấy size với id: " + sizeRequest.getId()));
        BeanUtils.copyProperties(sizeRequest, sizeUpdate,"id", "ngayTao");
        sizeUpdate.setNgaySua(LocalDate.now());
        return sizeRepository.save(sizeUpdate);
    }

    @Override
    public Size getSize(Integer id) {
        return sizeRepository.findById(id).orElseThrow(()->
                new RuntimeException("Không tìm thấy id size"));
    }
}
