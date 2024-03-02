package com.prj321x.ver3.asm3.rest;

import com.prj321x.ver3.asm3.entity.Account;
import com.prj321x.ver3.asm3.entity.DangKyKhamBenh;
import com.prj321x.ver3.asm3.entity.Role;
import com.prj321x.ver3.asm3.service.AccountService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-bacsi")
public class BacSiRestController {
    AccountService accountService;
    PasswordEncoder passwordEncoder;

    public BacSiRestController(AccountService accountService, PasswordEncoder passwordEncoder) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/danhSachBenhNhan")
    public List<DangKyKhamBenh> danhSachBenhNhan(){
        String getUname = SecurityContextHolder.getContext().getAuthentication().getName();
        Account loginAccount = accountService.findAccountByName(getUname);
        System.out.println(loginAccount);
        List<DangKyKhamBenh> dangKyKhamBenhList = accountService.findBenhNhansByIdBacSi(loginAccount.getId());
        System.out.println(dangKyKhamBenhList);
        return dangKyKhamBenhList;
    }

    @PutMapping("/xacNhanKhamBenh")
    public DangKyKhamBenh xacNhanKhamBenh(@RequestBody DangKyKhamBenh dangKyKhamBenh){
        DangKyKhamBenh updateDangKyKhamBenh = accountService.findDangKyKhamBenhById(dangKyKhamBenh.getId());
        updateDangKyKhamBenh.setStatus(dangKyKhamBenh.isStatus());
        accountService.saveDangKyKhamBenh(updateDangKyKhamBenh);
        System.out.println("done xác nhận khám bênh: " + dangKyKhamBenh);
        return updateDangKyKhamBenh;
    }

    //đăng ký bác sĩ mới
    @PostMapping("/addBacSi/{bacSi}")
    public Account addBenhNhan(@RequestBody Account bacSi) {
        bacSi.setPassword(passwordEncoder.encode(bacSi.getPassword()));
        Role role = accountService.findRoleById(2);
        bacSi.addRole(role);
        Account svBacSi = accountService.saveAccount(bacSi);
        System.out.println(bacSi);
        System.out.println("done add bác sĩ!");
        return svBacSi;
    }
}
