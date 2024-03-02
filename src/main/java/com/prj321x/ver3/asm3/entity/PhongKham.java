package com.prj321x.ver3.asm3.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "phongkham")
public class PhongKham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "phongKham", cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonManagedReference(value = "phongKham")
    private List<Account> cacBacSiCuaPhongKham;

    public PhongKham() {
    }

    public PhongKham(String name, String diaChi, String email, String phoneNumber) {
        this.name = name;
        this.diaChi = diaChi;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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

    public void addBacSi(Account bacSiUser) {
        if (cacBacSiCuaPhongKham == null) cacBacSiCuaPhongKham = new ArrayList<>();

        cacBacSiCuaPhongKham.add(bacSiUser);
        bacSiUser.setPhongKham(this);
    }

    @Override
    public String toString() {
        return "PhongKham{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
