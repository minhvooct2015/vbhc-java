package doc.management.v2.control;

import doc.management.v2.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class TinhThanhPhoService {

    // List of all 63 province/city names (should be UPPERCASE to match input)


    public String checkCity(String cityName) {
        List<String> provinces = Arrays.asList(
                "AN GIANG", "BÀ RỊA - VŨNG TÀU", "BẮC GIANG", "BẮC KẠN", "BẠC LIÊU", "BẮC NINH", "BẾN TRE",
                "BÌNH ĐỊNH", "BÌNH DƯƠNG", "BÌNH PHƯỚC", "BÌNH THUẬN", "CÀ MAU", "CẦN THƠ", "CAO BẰNG",
                "ĐÀ NẴNG", "ĐẮK LẮK", "ĐẮK NÔNG", "ĐIỆN BIÊN", "ĐỒNG NAI", "ĐỒNG THÁP", "GIA LAI", "HÀ GIANG",
                "HÀ NAM", "HÀ NỘI", "HÀ TĨNH", "HẢI DƯƠNG", "HẢI PHÒNG", "HẬU GIANG", "HÒA BÌNH", "HƯNG YÊN",
                "KHÁNH HÒA", "KIÊN GIANG", "KON TUM", "LAI CHÂU", "LÂM ĐỒNG", "LẠNG SƠN", "LÀO CAI", "LONG AN",
                "NAM ĐỊNH", "NGHỆ AN", "NINH BÌNH", "NINH THUẬN", "PHÚ THỌ", "PHÚ YÊN", "QUẢNG BÌNH",
                "QUẢNG NAM", "QUẢNG NGÃI", "QUẢNG NINH", "QUẢNG TRỊ", "SÓC TRĂNG", "SƠN LA", "TÂY NINH",
                "THÁI BÌNH", "THÁI NGUYÊN", "THANH HÓA", "THỪA THIÊN HUẾ", "TIỀN GIANG", "TP. HỒ CHÍ MINH",
                "TRÀ VINH", "TUYÊN QUANG", "VĨNH LONG", "VĨNH PHÚC", "YÊN BÁI"
        );

        String input = "UBND TỈNH ĐỒNG NAI SỞ TÀI NGUYÊN VÀ MÔI TRƯỜNG";

        String foundProvince = provinces.stream()
                .filter(input::contains)
                .findFirst()
                .orElse(null);

        System.out.println(foundProvince); // Output: ĐỒNG NAI
        return foundProvince;
    }
    /**
     * Finds and returns the TinhThanhPho entity whose name matches (is contained in) the given input string.
     * Returns null if not found.
     */
    public static TinhThanhPho findMatchingProvince(EntityManager entityManager, String input) {
        // Get all provinces from the database
        List<TinhThanhPho> provinces  = entityManager.createQuery(
                "SELECT t FROM TinhThanhPho t", TinhThanhPho.class).getResultList();

        // Normalize input to upper case for matching
        String normalizedInput = input.toUpperCase();

        // Find the first province whose name appears in the input
        for (TinhThanhPho province : provinces) {
            if (province.getTenTinhThanhPho() != null &&
                    normalizedInput.contains(province.getTenTinhThanhPho().toUpperCase())) {
                return province;
            }
        }
        // Not found
        return null;
    }
}
