package doc.management.v2;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class VanBanHanhChinh {
    @Id
    @Column(length = 36)
    private String id;

    private String trichYeu;

    private String soHieu;

    private LocalDate ngayDen;

    private LocalDate ngayBanHanh;

    private String tepDinhKem;

    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "loaiVanBanId", nullable = false)
    private LoaiVanBan loaiVanBan;

    @ManyToOne
    @JoinColumn(name = "coQuanBanHanhId", nullable = false)
    private CoQuanDonVi coQuanBanHanh;

//    @ManyToOne
//    @JoinColumn(name = "donViPhoBienId", nullable = false)
//    private CoQuanDonVi donViPhoBien;

//    @ManyToMany
//    @JoinTable(
//            name = "DonViPhoBien",
//            joinColumns = @JoinColumn(name = "vanBanHanhChinhId"),
//            inverseJoinColumns = @JoinColumn(name = "coQuanDonViId")
//    )
//    private List<CoQuanDonVi> donViPhoBiens;

    // links to join entity
    @OneToMany(mappedBy = "vanBanHanhChinh", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<DonViPhoBien> donViPhoBienLinks = new HashSet<>();

    // Add mapping to NguoiKyGiuChucVu (composite key)
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "nguoiKyId", referencedColumnName = "nguoiKyId"),
            @JoinColumn(name = "chucVuId", referencedColumnName = "chucVuId")
    })
    private NguoiKyGiuChucVu nguoiKyGiuChucVu;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private NguoiDung nguoiNhap;

    public VanBanHanhChinh() {}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrichYeu() {
        return trichYeu;
    }

    public void setTrichYeu(String trichYeu) {
        this.trichYeu = trichYeu;
    }

    public String getSoHieu() {
        return soHieu;
    }

    public void setSoHieu(String soHieu) {
        this.soHieu = soHieu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public LocalDate getNgayDen() {
        return ngayDen;
    }

    public void setNgayDen(LocalDate ngayDen) {
        this.ngayDen = ngayDen;
    }

    public LocalDate getNgayBanHanh() {
        return ngayBanHanh;
    }

    public void setNgayBanHanh(LocalDate ngayBanHanh) {
        this.ngayBanHanh = ngayBanHanh;
    }

    public String getTepDinhKem() {
        return tepDinhKem;
    }

    public void setTepDinhKem(String tepDinhKem) {
        this.tepDinhKem = tepDinhKem;
    }

    public LoaiVanBan getLoaiVanBan() {
        return loaiVanBan;
    }

    public void setLoaiVanBan(LoaiVanBan loaiVanBan) {
        this.loaiVanBan = loaiVanBan;
    }

    public CoQuanDonVi getCoQuanBanHanh() {
        return coQuanBanHanh;
    }

    public void setCoQuanBanHanh(CoQuanDonVi coQuanBanHanh) {
        this.coQuanBanHanh = coQuanBanHanh;
    }

    public Set<DonViPhoBien> getDonViPhoBienLinks() {
        return donViPhoBienLinks;
    }

    public void setDonViPhoBienLinks(Set<DonViPhoBien> donViPhoBienLinks) {
        this.donViPhoBienLinks = donViPhoBienLinks;
    }

    // convenience: get CoQuanDonVi list from links
    public List<CoQuanDonVi> getDonViPhoBiens() {
        return donViPhoBienLinks.stream()
                .map(DonViPhoBien::getCoQuanDonVi)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // helper to add a link
    public void addDonViPhoBienLink(DonViPhoBien link) {
        if (link != null) {
            donViPhoBienLinks.add(link);
            link.setVanBanHanhChinh(this);
        }
    }

    public void removeDonViPhoBienLink(DonViPhoBien link) {
        if (link != null) {
            donViPhoBienLinks.remove(link);
            link.setVanBanHanhChinh(null);
        }
    }


    public NguoiKyGiuChucVu getNguoiKyGiuChucVu() {
        return nguoiKyGiuChucVu;
    }

    public void setNguoiKyGiuChucVu(NguoiKyGiuChucVu nguoiKy) {
        this.nguoiKyGiuChucVu = nguoiKy;
    }

    public NguoiDung getNguoiNhap() {
        return nguoiNhap;
    }

    public void setNguoiNhap(NguoiDung nguoiNhap) {
        this.nguoiNhap = nguoiNhap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof VanBanHanhChinh)) return false;
        VanBanHanhChinh that = (VanBanHanhChinh) o;
        // Entities are considered equal when they have the same non-null id
        return this.id != null && this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        // Use id's hashCode when available; otherwise return a constant
        // (avoids using object identity which can change behavior when persisted)
        return (id != null) ? id.hashCode() : 0;
    }
}