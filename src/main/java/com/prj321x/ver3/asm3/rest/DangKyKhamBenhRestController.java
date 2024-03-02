package com.prj321x.ver3.asm3.rest;

import com.prj321x.ver3.asm3.entity.Account;
import com.prj321x.ver3.asm3.entity.DangKyKhamBenh;
import com.prj321x.ver3.asm3.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-dangkykhambenh")
public class DangKyKhamBenhRestController {
    AccountService accountService;

    public DangKyKhamBenhRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{theId}")
    public DangKyKhamBenh getDanhKyKhamBenh(@PathVariable int theId){
        DangKyKhamBenh dangKyKhamBenh = accountService.findDangKyKhamBenhById(theId);
        return dangKyKhamBenh;
    }

    @PostMapping("/dangKyKhamBenh")
    public DangKyKhamBenh dangKyKhamBenh(@RequestBody DangKyKhamBenh dangKyKhamBenhNew){
        Account benhNhanDangKy = accountService.findAccountById(dangKyKhamBenhNew.getBenhNhanDangky().getId());
        Account bacSiDangKy = accountService.findAccountById(dangKyKhamBenhNew.getBacSiDangKy().getId());
        dangKyKhamBenhNew.setBenhNhanDangky(benhNhanDangKy);
        dangKyKhamBenhNew.setBacSiDangKy(bacSiDangKy);
        System.out.println(dangKyKhamBenhNew);
        DangKyKhamBenh dangKyKhamBenh = accountService.saveDangKyKhamBenh(dangKyKhamBenhNew);
        System.out.println("done save dangKyKhamBenh: "+dangKyKhamBenh);
        return dangKyKhamBenh;
    }
}
