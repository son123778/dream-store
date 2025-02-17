package com.example.dreambackend.services.anh;

import com.example.dreambackend.entities.Anh;
import com.example.dreambackend.entities.SanPham;
import com.example.dreambackend.repositories.AnhRepository;
import com.example.dreambackend.repositories.SanPhamRepository;
import com.example.dreambackend.respones.AnhRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnhService implements IAnhService {

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    AnhRepository anhRepository;

    @Override
    public List<AnhRespone> getAllAnh() {
        return anhRepository.getAllAnhRespones();
    }

    @Override
    public Anh getAnhById(Integer id) {
        return anhRepository.findById(id).orElseThrow(()->
                new RuntimeException("Không tìm thấy id ảnh"));
    }

    @Override
    public List<Anh> addAnhs(List<MultipartFile> anhUrls, Integer trangThai, Integer idSanPham) {
        List<Anh> anhs = new ArrayList<>();
        try {
            // Kiểm tra sản phẩm tồn tại
            SanPham sanPham = sanPhamRepository.findById(idSanPham)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + idSanPham));

            for (MultipartFile anhUrl : anhUrls) {
                // Lưu file ảnh vào thư mục
                String uploadDir = "uploads/images/";
                String fileName = System.currentTimeMillis() + "_" + anhUrl.getOriginalFilename();
                Path filePath = Paths.get(uploadDir, fileName);
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, anhUrl.getBytes());

                // Tạo đối tượng Anh
                Anh anh = new Anh();
                anh.setAnhUrl("/" + uploadDir + fileName); // URL lưu file
                anh.setTrangThai(trangThai);
                anh.setSanPham(sanPham);
                anh.setNgayTao(LocalDate.now());
                anh.setNgaySua(LocalDate.now());

                // Lưu vào cơ sở dữ liệu
                anhs.add(anhRepository.save(anh));
            }

            return anhs;

        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi lưu file ảnh: " + e.getMessage());
        }
    }


    @Override
    public List<Anh> updateAnhs(List<MultipartFile> anhUrls, List<Integer> trangThais, List<Integer> ids, Integer idSanPham) {
        // Kiểm tra kích thước của các danh sách
        if (anhUrls.size() != trangThais.size() || anhUrls.size() != ids.size()) {
            throw new RuntimeException("Số lượng file, trạng thái và ID không khớp");
        }

        List<Anh> updatedAnhs = new ArrayList<>();
        try {
            // Tìm sản phẩm tương ứng
            SanPham sanPham = sanPhamRepository.findById(idSanPham)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với id: " + idSanPham));

            for (int i = 0; i < ids.size(); i++) {
                Integer id = ids.get(i);
                if (id == null) {
                    throw new RuntimeException("ID không được null tại chỉ số: " + i);
                }

                Anh anhUpdate = anhRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy ảnh với id: " + id));

                if (!anhUrls.get(i).isEmpty()) {
                    String uploadDir = "uploads/images/";
                    String fileName = System.currentTimeMillis() + "_" + anhUrls.get(i).getOriginalFilename();
                    Path filePath = Paths.get(uploadDir, fileName);
                    Files.createDirectories(filePath.getParent());
                    Files.write(filePath, anhUrls.get(i).getBytes());

                    anhUpdate.setAnhUrl("/" + uploadDir + fileName);
                }

                Integer trangThai = trangThais.get(i);
                if (trangThai == null) {
                    throw new RuntimeException("Trạng thái không được null tại chỉ số: " + i);
                }
                anhUpdate.setTrangThai(trangThai);
                anhUpdate.setNgaySua(LocalDate.now());

                // Thiết lập sản phẩm
                anhUpdate.setSanPham(sanPham); // Thiết lập đối tượng SanPham

                updatedAnhs.add(anhRepository.save(anhUpdate));
            }
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi lưu file ảnh: " + e.getMessage());
        }
        return updatedAnhs;
    }




}
