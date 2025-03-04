package com.example.dreambackend.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SqlResultSetMapping(
        name = "GioHangChiTietResponseMapping",
        classes = @ConstructorResult(
                targetClass = GioHangChiTietResponse.class,
                columns = {
                        @ColumnResult(name = "id",type = Integer.class),
                        @ColumnResult(name = "soLuong",type = Integer.class),
                        @ColumnResult(name = "donGia",type = Double.class),
                        @ColumnResult(name = "anhUrl",type = String.class),
                        @ColumnResult(name = "tenSanPham",type = String.class),
                        @ColumnResult(name = "giaTriGiam",type = Double.class),
                        @ColumnResult(name = "hinhThucGiam",type = Boolean.class),
                        @ColumnResult(name = "ngayTao",type = LocalDate.class),
                        @ColumnResult(name = "ngaySua",type = LocalDate.class),
                        @ColumnResult(name = "trangThai",type = Integer.class),
                        @ColumnResult(name = "mau",type = String.class),
                        @ColumnResult(name = "size",type = String.class),
                        @ColumnResult(name = "totalRecords",type = Integer.class),
                }
        )
)
@Entity
public class GioHangChiTietResponse {
    @Id
    private Integer id;
    private Integer soLuong;
    private Double donGia;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate ngayTao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate ngaySua;
    private Integer trangThai;
    private Integer idKhachHang;
    private Integer idSanPhamChiTiet;
    private String anhUrl;
    private String tenSanPham;
    private Boolean hinhThucGiam;
    private Double giaTriGiam;
    private String mau;
    private String size;
    private Integer totalRecords;
}
