package com.example.dreambackend.services.vaitro;

import com.example.dreambackend.entities.VaiTro;
import com.example.dreambackend.requests.VaiTroRequest;
import com.example.dreambackend.respones.VaiTroResponse;

import java.util.List;

public interface IVaiTroService {
    List<VaiTroResponse> getAllVaiTro();

    VaiTro addVaiTro(VaiTroRequest vaiTroRequest);

    VaiTro updateVaiTro(VaiTroRequest vaiTroRequest);
}
