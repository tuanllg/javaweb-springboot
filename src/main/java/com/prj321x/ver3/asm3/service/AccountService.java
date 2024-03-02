package com.prj321x.ver3.asm3.service;

import com.prj321x.ver3.asm3.entity.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends UserDetailsService {
    //account
    Account findAccountByEmail(String accountEmail);
    Account findAccountByName(String nameAccount);
    List<Account> findAccountsByName(String nameAccount);
    Account saveAccount(Account svAccount);
    Account findAccountById(int theId);
    List<DangKyKhamBenh> findDangKyKhamBenhOfBenhNhanById(int theId);
    Role findRoleById(int theId);
    //tìm kiếm danh sách bệnh nhân của bác sĩ
    List<DangKyKhamBenh> findBenhNhansByIdBacSi(int theId);

    //dangKyKhamBenh
    DangKyKhamBenh findDangKyKhamBenhById(int theId);
    DangKyKhamBenh saveDangKyKhamBenh(DangKyKhamBenh scDangKyKhamBenh);


    //thêm các tìm kiếm ck, phòng khám
    ChuyenKhoa findChuyenKhoaById(int theId);
    List<ChuyenKhoa> findChuyenKhoaByName(String nameChuyenKhoa);
    PhongKham findPhongKhamById(int theId);
    List<PhongKham> findPhongKhamByName(String namePhongKham);
}
