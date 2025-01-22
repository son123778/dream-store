//package com.example.dreambackend.entities;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.List;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "vai_tro")
//public class VaiTro {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(name = "ten", nullable = false, length = 250)
//    private String ten;
//
//    @Column(name = "trang_thai", nullable = false)
//    private int trangThai;
//
//    @OneToMany(mappedBy = "vaiTro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<NhanVien> nhanViens;
//}
