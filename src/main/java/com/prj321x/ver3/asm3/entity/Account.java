package com.prj321x.ver3.asm3.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String name;

    @Column(name = "male")
    private String male;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "dia_chi")
    private String dia_chi;

    @Column(name = "avatar_path")
    private String avatar_path;
    @Column(name = "status")
    private boolean trangThaiTaiKhoan;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "phongkham_id")
    @JsonBackReference(value = "phongKham")
    private PhongKham phongKham;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "chuyenkhoa_id")
    @JsonBackReference(value = "chuyenKhoa")
    private ChuyenKhoa chuyenKhoa;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "accounts_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnoreProperties("accounts")
    private Collection<Role> roles;

    //list các bác sĩ đã đăng ký khám bênh dành cho bênh nhân
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bacSiDangKy", cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonManagedReference(value = "benhNhanDangKy")
    private List<DangKyKhamBenh> cacBenhNhanDangky;

    //list các bênh nhân đăng ký khám bệnh đanh cho bác sĩ
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "benhNhanDangky", cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonManagedReference(value = "bacSiDangKy")
    private List<DangKyKhamBenh> cacBacSiDaDangKy;

    @Column(name = "password")
    private String password;



    public Account() {
    }

    public ChuyenKhoa getChuyenKhoa() {
        return chuyenKhoa;
    }

    public void setChuyenKhoa(ChuyenKhoa chuyenKhoa) {
        this.chuyenKhoa = chuyenKhoa;
    }

    public PhongKham getPhongKham() {
        return phongKham;
    }

    public void setPhongKham(PhongKham phongKham) {
        this.phongKham = phongKham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public String getAvatar_path() {
        return avatar_path;
    }

    public void setAvatar_path(String avatar_path) {
        this.avatar_path = avatar_path;
    }

    public boolean isTrangThaiTaiKhoan() {
        return trangThaiTaiKhoan;
    }

    public void setTrangThaiTaiKhoan(boolean trangThaiTaiKhoan) {
        this.trangThaiTaiKhoan = trangThaiTaiKhoan;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        if (roles == null) roles = new ArrayList<>();
        roles.add(role);

    }

    public List<DangKyKhamBenh> getCacBacSiDaDangKy() {
        return cacBacSiDaDangKy;
    }

    public void setCacBacSiDaDangKy(List<DangKyKhamBenh> cacBacSiDaDangKy) {
        this.cacBacSiDaDangKy = cacBacSiDaDangKy;
    }

//    thêm bác sĩ vào danh sách đăng ký
    public void addBacSi(DangKyKhamBenh dangKyKhamBenhMoi) {
        if (cacBacSiDaDangKy == null) cacBacSiDaDangKy = new ArrayList<>();
        dangKyKhamBenhMoi.setBenhNhanDangky(this);
        cacBacSiDaDangKy.add(dangKyKhamBenhMoi);
    }


    public List<DangKyKhamBenh> getCacBenhNhanDangky() {
        return cacBenhNhanDangky;
    }

    public void setCacBenhNhanDangky(List<DangKyKhamBenh> cacBenhNhanDangky) {
        this.cacBenhNhanDangky = cacBenhNhanDangky;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", male='" + male + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dia_chi='" + dia_chi + '\'' +
                ", avatar_path='" + avatar_path + '\'' +
                ", trangThaiTaiKhoan=" + trangThaiTaiKhoan +
                ", phongKham=" + phongKham +
                ", chuyenKhoa=" + chuyenKhoa +
                ", roles=" + roles +
                ", password='" + password + '\'' +
                '}';
    }
}
