package com.example.dreambackend.services.coao;

import com.example.dreambackend.dtos.CoAoDto;
import com.example.dreambackend.entities.CoAo;
import com.example.dreambackend.requests.CoAoRequest;
import com.example.dreambackend.respones.CoAoRespone;

import java.util.List;

public interface ICoAoService {
    List<CoAoRespone> getAllCoAo();

    CoAo getCoAoById(Integer id);

    CoAo addCoAo(CoAoRequest coAoRequest);

    CoAo updateCoAo(CoAoRequest coAoRequest);
}
