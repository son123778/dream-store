package com.example.dreambackend.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@SqlResultSetMapping(
        name = "HoaDonChiTietResponseMapping",
        classes = @ConstructorResult(
                targetClass = HoaDonChiTietResponse.class,
                columns = {
                        @ColumnResult(name = "id", type=Integer.class),
                        @ColumnResult(name = "idHoaDon", type=Integer.class),
                        @ColumnResult(name = "idSanPhamChiTiet", type=Integer.class),
                        @ColumnResult(name = "maHoaDon", type=String.class),
                        @ColumnResult(name = "maHoaDonChiTiet", type=String.class),
                        @ColumnResult(name = "tenSanPham", type=String.class),
                        @ColumnResult(name = "soLuong", type=Integer.class),
                        @ColumnResult(name = "donGia", type=Double.class),
                        @ColumnResult(name = "ngayTao", type= LocalDate.class),
                        @ColumnResult(name = "ngaySua", type= LocalDate.class),
                        @ColumnResult(name = "trangThai", type = Integer.class),
                        @ColumnResult(name = "totalRecords", type = Integer.class)
                }
        )
)
@Entity
public class HoaDonChiTietResponse {
    @Id
    private Integer id;
    private Integer idHoaDon;
    private Integer idSanPhamChiTiet;
    private String maHoaDon;
    private String maHoaDonChiTiet;
    private String tenSanPham;
    private Integer soLuong;
    private Double donGia;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate ngayTao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate ngaySua;
    private Integer trangThai;
    private Integer totalRecords;
}
