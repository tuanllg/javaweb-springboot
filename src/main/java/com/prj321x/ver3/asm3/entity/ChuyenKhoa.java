package com.prj321x.ver3.asm3.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chuyenkhoa")
public class ChuyenKhoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chuyenKhoa", cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonManagedReference(value = "chuyenKhoa")
    private List<Account> cacBacSiCuaChuyenKhoa;

    public ChuyenKhoa() {
    }

    public ChuyenKhoa(String name) {
        this.name = name;
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

    public void addBacSi(Account bacSiUser) {
        if (cacBacSiCuaChuyenKhoa == null) cacBacSiCuaChuyenKhoa = new ArrayList<>();

        cacBacSiCuaChuyenKhoa.add(bacSiUser);
        bacSiUser.setChuyenKhoa(this);
    }

    @Override
    public String toString() {
        return "ChuyenKhoa{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
