package com.prj321x.ver3.asm3.rest;

import com.prj321x.ver3.asm3.entity.Account;
import com.prj321x.ver3.asm3.entity.DangKyKhamBenh;
import com.prj321x.ver3.asm3.entity.Role;
import com.prj321x.ver3.asm3.error.AccountNotFoundException;
import com.prj321x.ver3.asm3.error.AppErrorResponse;
import com.prj321x.ver3.asm3.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-account")
public class AccountRestController {
    AccountService accountService;
    PasswordEncoder passwordEncoder;

    @Autowired
    public AccountRestController(AccountService accountService, PasswordEncoder passwordEncoder) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    //Thêm bệnh nhân mới
    @PostMapping("/addBenhNhan/{benhNhan}")
    public Account addBenhNhan(@RequestBody Account benhNhan) {
        benhNhan.setPassword(passwordEncoder.encode(benhNhan.getPassword()));
        Role role = accountService.findRoleById(1);
        benhNhan.addRole(role);
        Account svBenhNhan = accountService.saveAccount(benhNhan);
        System.out.println(benhNhan);
        System.out.println("done add bệnh nhân!");
        return svBenhNhan;
    }

    //thay đổi thạng thái tài khoản(khóa-hủy khóa)
    //dùng chung cho cả bác sĩ và bệnh nhân
    @PutMapping("/doiTrangThai/{idAccount}")
    public Account doiTrangThai(@PathVariable int idAccount){
        Account account = accountService.findAccountById(idAccount);
        System.out.println(account);
        if(account.isTrangThaiTaiKhoan() == false){
            account.setTrangThaiTaiKhoan(true);
        }else {
            account.setTrangThaiTaiKhoan(false);
        }
        accountService.saveAccount(account);
        return account;
    }
}
