package com.example.dreambackend.services.coao;

import com.example.dreambackend.entities.CoAo;
import com.example.dreambackend.repositories.CoAoRepository;
import com.example.dreambackend.requests.CoAoRequest;
import com.example.dreambackend.responses.CoAoRespone;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CoAoService implements ICoAoService {
    @Autowired
    CoAoRepository coAoRepository;

    @Override
    public List<CoAoRespone> getAllCoAo() {
        return coAoRepository.getAllCoAoRespones();
    }

    @Override
    public CoAo getCoAoById(Integer id) {
        return coAoRepository.findById(id).orElseThrow(()->
                new RuntimeException("Không tìm thấy id cổ áo"));
    }

    @Override
    public CoAo addCoAo(CoAoRequest coAoRequest) {
        CoAo coAo = new CoAo();
        BeanUtils.copyProperties(coAoRequest, coAo);
        coAo.setNgayTao(LocalDate.now());
        coAo.setNgaySua(LocalDate.now());
        return coAoRepository.save(coAo);
    }

    @Override
    public CoAo updateCoAo(CoAoRequest coAoRequest) {
        CoAo coAoUpdate = coAoRepository.findById(coAoRequest.getId()).orElseThrow(()->
                new RuntimeException("Không tìm thấy cổ áo với id: "+coAoRequest.getId()));
        // sao chép thuộc tính từ request sang đối tượng cần nhập trừ id với ngày tạo
        BeanUtils.copyProperties(coAoRequest, coAoUpdate,"id","ngayTao");
        coAoUpdate.setNgaySua(LocalDate.now());
        return coAoRepository.save(coAoUpdate);
    }
}
