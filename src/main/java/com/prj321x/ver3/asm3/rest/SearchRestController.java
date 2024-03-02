package com.prj321x.ver3.asm3.rest;

import com.prj321x.ver3.asm3.entity.Account;
import com.prj321x.ver3.asm3.entity.ChuyenKhoa;
import com.prj321x.ver3.asm3.entity.PhongKham;
import com.prj321x.ver3.asm3.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchRestController {
    AccountService accountService;

    public SearchRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/timKiemBacSi/{tenBs}")
    public List<Account> timKiemBacSi(@PathVariable String tenBs){
        List<Account> bacSis = accountService.findAccountsByName(tenBs);
        return bacSis;
    }

    @GetMapping("/timKiemPhongKham/{tenPhongKham}")
    public List<PhongKham> timKiemPhongKham(@PathVariable String tenPhongKham){
        List<PhongKham> phongKhams = accountService.findPhongKhamByName(tenPhongKham);
        return phongKhams;
    }
    @GetMapping("/timKiemChuyenKhoa/{tenChuyenKhoa}")
    public List<ChuyenKhoa> timKiemChuyenKhoa(@PathVariable String tenChuyenKhoa){
        List<ChuyenKhoa> chuyenKhoas = accountService.findChuyenKhoaByName(tenChuyenKhoa);
        return chuyenKhoas;
    }
}
