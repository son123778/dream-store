package com.example.dreambackend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vai_tro")
public class VaiTro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten", nullable = false, length = 250)
    private String ten;

    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;

    @OneToMany(mappedBy = "vaiTro")
    @JsonManagedReference // Ánh xạ ngược lại từ VaiTro đến NhanVien
    private List<NhanVien> nhanViens;
}
