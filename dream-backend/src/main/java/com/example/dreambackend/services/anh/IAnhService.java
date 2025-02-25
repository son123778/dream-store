package com.example.dreambackend.services.anh;

import com.example.dreambackend.entities.Anh;
import com.example.dreambackend.responses.AnhRespone;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAnhService {
    List<AnhRespone> getAllAnh();

    Anh getAnhById(Integer id);

    List<Anh> addAnhs(List<MultipartFile> anhUrls, Integer trangThai, Integer idSanPham);

    List<Anh> updateAnhs(List<MultipartFile> anhUrls, List<Integer> trangThais, List<Integer> ids, Integer idSanPham);
}
