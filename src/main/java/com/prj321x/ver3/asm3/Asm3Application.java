package com.prj321x.ver3.asm3;

import com.prj321x.ver3.asm3.entity.Account;
import com.prj321x.ver3.asm3.entity.ChuyenKhoa;
import com.prj321x.ver3.asm3.entity.DangKyKhamBenh;
import com.prj321x.ver3.asm3.entity.PhongKham;
import com.prj321x.ver3.asm3.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Asm3Application {

    public static void main(String[] args) {
        SpringApplication.run(Asm3Application.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(AccountService accountService) {
//        return runner -> {
//            //set phong khám và chuyên khoa cho bác sĩ.
//            //addPhongKhamVaChuyenKhoaEx(accountService);
//            //addDangKyKhamBenh(accountService);
//        };
//    }

    private void addPhongKhamVaChuyenKhoaEx(AccountService accountService) {
        Account bacSi = accountService.findAccountById(3);
        ChuyenKhoa chuyenKhoa = accountService.findChuyenKhoaById(1);
        PhongKham phongKham = accountService.findPhongKhamById(1);
        bacSi.setChuyenKhoa(chuyenKhoa);
        bacSi.setPhongKham(phongKham);
        accountService.saveAccount(bacSi);
    }

    private void addDangKyKhamBenh(AccountService accountService) {
        DangKyKhamBenh dangKyKhamBenhMoi = new DangKyKhamBenh("sốt", "sáng", "22/01/2024",true);
        Account bn1 = accountService.findAccountById(1);
        Account bs1 = accountService.findAccountById(3);
        List<DangKyKhamBenh> dangKyKhamBenhList = accountService.findDangKyKhamBenhOfBenhNhanById(1);
        if (dangKyKhamBenhList == null) dangKyKhamBenhList = new ArrayList<>();
        dangKyKhamBenhMoi.setBacSiDangKy(bs1);
        dangKyKhamBenhMoi.setBenhNhanDangky(bn1);
        dangKyKhamBenhList.add(dangKyKhamBenhMoi);

        bn1.setCacBenhNhanDangky(dangKyKhamBenhList);
        accountService.saveAccount(bn1);

//        dangKyKhamBenhMoi.setBenhNhanDangky(bn1);
//        appService.saveDangKyKhamBenh(dangKyKhamBenhMoi);
    }
}
