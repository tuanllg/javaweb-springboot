package com.prj321x.ver3.asm3.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "dang_ky_kham_benh")
public class DangKyKhamBenh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "trieu_chung_benh")
    private String trieuChungBenh;

    @Column(name = "thoi_gian_kham")
    private String gioKhamDangKy;
    @Column(name = "ngay_kham")
    private String ngayKhamDangKy;
    @Column(name = "status")
    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "bacsi_id")
    @JsonBackReference(value = "benhNhanDangKy")
    private Account bacSiDangKy;
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "benhnhan_id")
    @JsonBackReference(value = "bacSiDangKy")
    private Account benhNhanDangky;


    public DangKyKhamBenh() {
    }
    public DangKyKhamBenh(String trieuChungBenh, String gioKhamDangKy, String ngayKhamDangKy) {
        this.trieuChungBenh = trieuChungBenh;
        this.gioKhamDangKy = gioKhamDangKy;
        this.ngayKhamDangKy = ngayKhamDangKy;
    }

    public DangKyKhamBenh(String trieuChungBenh, String gioKhamDangKy, String ngayKhamDangKy, boolean status) {
        this.trieuChungBenh = trieuChungBenh;
        this.gioKhamDangKy = gioKhamDangKy;
        this.ngayKhamDangKy = ngayKhamDangKy;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrieuChungBenh() {
        return trieuChungBenh;
    }

    public void setTrieuChungBenh(String trieuChungBenh) {
        this.trieuChungBenh = trieuChungBenh;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Account getBenhNhanDangky() {
        return benhNhanDangky;
    }

    public void setBenhNhanDangky(Account benhNhanDangky) {
        this.benhNhanDangky = benhNhanDangky;
    }

    public Account getBacSiDangKy() {
        return bacSiDangKy;
    }

    public void setBacSiDangKy(Account bacSiDangKy) {
        this.bacSiDangKy = bacSiDangKy;
    }

    public String getGioKhamDangKy() {
        return gioKhamDangKy;
    }

    public void setGioKhamDangKy(String gioKhamDangKy) {
        this.gioKhamDangKy = gioKhamDangKy;
    }

    public String getNgayKhamDangKy() {
        return ngayKhamDangKy;
    }

    public void setNgayKhamDangKy(String ngayKhamDangKy) {
        this.ngayKhamDangKy = ngayKhamDangKy;
    }

    @Override
    public String toString() {
        return "DangKyKhamBenh{" +
                "id=" + id +
                ", trieuChungBenh='" + trieuChungBenh + '\'' +
                ", gioKhamDangKy='" + gioKhamDangKy + '\'' +
                ", ngayKhamDangKy='" + ngayKhamDangKy + '\'' +
                ", status=" + status +
                ", bacSiDangKy=" + bacSiDangKy +
                ", benhNhanDangky=" + benhNhanDangky +
                '}';
    }
}
