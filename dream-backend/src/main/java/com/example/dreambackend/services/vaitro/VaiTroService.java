package com.example.dreambackend.services.vaitro;

import com.example.dreambackend.entities.VaiTro;
import com.example.dreambackend.repositories.VaiTroRepository;
import com.example.dreambackend.requests.VaiTroRequest;
import com.example.dreambackend.responses.VaiTroResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaiTroService implements IVaiTroService {

    @Autowired
    private VaiTroRepository vaiTroRepository;

    @Override
    public List<VaiTroResponse> getAllVaiTro() {
        return vaiTroRepository.getAllVaiTroResponses();
    }

    @Override
    public VaiTro addVaiTro(VaiTroRequest vaiTroRequest) {
        VaiTro vaiTro = new VaiTro();
        BeanUtils.copyProperties(vaiTroRequest, vaiTro);
        return vaiTroRepository.save(vaiTro);
    }

    @Override
    public VaiTro updateVaiTro(VaiTroRequest vaiTroRequest) {
        VaiTro vaiTro = vaiTroRepository.findById(vaiTroRequest.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy vai trò với id: " + vaiTroRequest.getId()));

        BeanUtils.copyProperties(vaiTroRequest, vaiTro, "id");
        return vaiTroRepository.save(vaiTro);
    }
}
