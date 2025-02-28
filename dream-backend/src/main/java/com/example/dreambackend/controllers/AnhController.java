package com.example.dreambackend.controllers;

import com.example.dreambackend.entities.Anh;
import com.example.dreambackend.responses.AnhRespone;
import com.example.dreambackend.services.anh.AnhService;
import com.example.dreambackend.services.sanpham.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/anh")
@CrossOrigin(origins = "http://localhost:4200")
public class AnhController {
    private static final String UPLOAD_DIR = "D:/dream-store/dream-backend/uploads/images/";

    @Autowired
    private AnhService anhService;

    @Autowired
    private SanPhamService sanPhamService;

//    @GetMapping("/image/{filename:.+}")
//    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
//        try {
//            Path filePath = Paths.get(UPLOAD_DIR).resolve(filename).normalize();
//            File file = filePath.toFile();
//
//            if (!file.exists() || !file.canRead()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            }
//
//            Resource resource = new UrlResource(file.toURI());
//
//            String contentType = Files.probeContentType(filePath);
//            if (contentType == null) {
//                contentType = "application/octet-stream";
//            }
//
//            return ResponseEntity.ok()
//                    .contentType(MediaType.parseMediaType(contentType))
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
//                    .body(resource);
//
//        } catch (MalformedURLException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @GetMapping("/hien-thi")
    public ResponseEntity<List<AnhRespone>> hienThi(@RequestParam("idSanPham") Integer idSanPham) {
        List<AnhRespone> listAnh = anhService.getAllAnh(idSanPham);
        return ResponseEntity.ok(listAnh);
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addAnhs(
            @RequestParam("anhUrl") List<MultipartFile> anhUrls,
            @RequestParam("idSanPham") Integer idSanPham) {
        List<Anh> addedAnhs = anhService.addAnhs(anhUrls, idSanPham);
        return ResponseEntity.ok(Map.of("message", "Đã tải lên " + addedAnhs.size() + " ảnh thành công"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAnh(@PathVariable("id") Integer id) {
        anhService.deleteAnh(id);
        return ResponseEntity.ok(Map.of("message", "Ảnh đã được xóa thành công"));
    }
}
