package com.example.dreambackend.services.thongke;

import com.example.dreambackend.repositories.HoaDonChiTietRepository;
import com.example.dreambackend.repositories.HoaDonRepository;
import com.example.dreambackend.respones.ThongKeResponse;
import com.example.dreambackend.respones.ThongKeThangResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThongKeService {

    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    public ThongKeResponse thongKeTongQuan(String type) {
        LocalDate startDate = null;
        LocalDate endDate = null;

        switch (type) {
            case "hom-nay":
                startDate = LocalDate.now();
                endDate = LocalDate.now();
                break;
            case "thang-nay":
                YearMonth currentMonth = YearMonth.now();
                startDate = currentMonth.atDay(1);
                endDate = currentMonth.atEndOfMonth();
                break;
            case "nam-nay":
                int currentYear = LocalDate.now().getYear();
                startDate = LocalDate.of(currentYear, 1, 1);
                endDate = LocalDate.of(currentYear, 12, 31);
                break;
            case "tat-ca":
                // Không thiết lập startDate và endDate
                break;
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }

        return hoaDonRepository.getTongQuan(startDate, endDate);
    }

    public List<ThongKeThangResponse> thongKeTungThang() {
        return hoaDonRepository.getDoanhThuTungThang();
    }
    public List<ThongKeThangResponse> thongKeTungNam() {
        List<Object[]> results = hoaDonRepository.getDoanhThuTungNam();
        return results.stream()
                .map(obj -> new ThongKeThangResponse((Integer) obj[0], (Double) obj[1]))
                .collect(Collectors.toList());
    }

}
