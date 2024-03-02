package com.prj321x.ver3.asm3.service;

import com.prj321x.ver3.asm3.dao.*;
import com.prj321x.ver3.asm3.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;
    ChuyenKhoaRepository chuyenKhoaRepository;
    PhongKhamRepository phongKhamRepository;
    DangKyKhamBenhRepository dangKyKhamBenhRepository;
    RoleRepository roleRepository;
    EntityManager entityManager;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, ChuyenKhoaRepository chuyenKhoaRepository, PhongKhamRepository phongKhamRepository, DangKyKhamBenhRepository dangKyKhamBenhRepository, RoleRepository roleRepository, EntityManager entityManager) {
        this.accountRepository = accountRepository;
        this.chuyenKhoaRepository = chuyenKhoaRepository;
        this.phongKhamRepository = phongKhamRepository;
        this.dangKyKhamBenhRepository = dangKyKhamBenhRepository;
        this.roleRepository = roleRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Account findAccountByEmail(String accountEmail) {
        //tìm kiếm bằng query với name user trong db
        TypedQuery query = entityManager.createQuery("from Account where email=:email and trangThaiTaiKhoan = true", Account.class);
        query.setParameter("email", accountEmail);
        Account theAccount = null;
        try {
            theAccount = (Account) query.getSingleResult();
        } catch (Exception e) {
            theAccount = null;
        }
        return theAccount;
    }

    @Override
    public Account findAccountByName(String nameAccount) {
        TypedQuery query = entityManager.createQuery("from Account where email=:name", Account.class);
        query.setParameter("name", nameAccount);
        Account theAccount = null;
        try {
            theAccount = (Account) query.getSingleResult();
        } catch (Exception e) {
            theAccount = null;
        }
        return theAccount;
    }
    @Override
    public List<Account> findAccountsByName(String nameAccount) {
        TypedQuery<Account> query = entityManager.createQuery("from Account a where a.name like :data", Account.class);
        query.setParameter("data", "%" + nameAccount + "%");
        return query.getResultList();
    }

    @Override
    public Account saveAccount(Account svAccount) {
        return accountRepository.save(svAccount);
    }

    @Override
    public Account findAccountById(int theId) {
        Optional<Account> result = accountRepository.findById(theId);
        Account account = null;
        if (result.isPresent()) {
            account = result.get();
        } else {
            throw new RuntimeException("không tìm thấy account id" + theId);
        }
        return account;
    }

    @Override
    public List<DangKyKhamBenh> findDangKyKhamBenhOfBenhNhanById(int theId) {
        TypedQuery<DangKyKhamBenh> query = entityManager.createQuery("from DangKyKhamBenh where benhNhanDangky.id = :data", DangKyKhamBenh.class);
        query.setParameter("data", theId);
        List<DangKyKhamBenh> dangKyKhamBenhList = query.getResultList();
        return dangKyKhamBenhList;
    }

    @Override
    public Role findRoleById(int theId) {
        Optional<Role> result = roleRepository.findById(theId);
        Role role = null;
        if (result.isPresent()) {
            role = result.get();
        } else {
            throw new RuntimeException("không tìm thấy role với id: " + theId);
        }
        return role;
    }

    @Override
    public List<DangKyKhamBenh> findBenhNhansByIdBacSi(int theId) {
        TypedQuery<DangKyKhamBenh> query = entityManager.createQuery("from DangKyKhamBenh d " +
                "where bacSiDangKy.id = :data", DangKyKhamBenh.class);
        query.setParameter("data",theId);
        return query.getResultList();
    }


    @Override
    public DangKyKhamBenh findDangKyKhamBenhById(int theId) {
        TypedQuery<DangKyKhamBenh> query = entityManager.createQuery("from DangKyKhamBenh d where d.id = :data", DangKyKhamBenh.class);
        query.setParameter("data", theId);
        return query.getSingleResult();
    }

    @Override
    public DangKyKhamBenh saveDangKyKhamBenh(DangKyKhamBenh scDangKyKhamBenh) {
        return dangKyKhamBenhRepository.save(scDangKyKhamBenh);
    }

    @Override
    public ChuyenKhoa findChuyenKhoaById(int theId) {
        Optional<ChuyenKhoa> result = chuyenKhoaRepository.findById(theId);
        ChuyenKhoa chuyenKhoa = null;
        if (result.isPresent()) {
            chuyenKhoa = result.get();
        } else {
            throw new RuntimeException("không tìm thấy account id" + theId);
        }
        return chuyenKhoa;
    }

    @Override
    public List<ChuyenKhoa> findChuyenKhoaByName(String nameChuyenKhoa) {
        TypedQuery<ChuyenKhoa> query = entityManager.createQuery("from ChuyenKhoa c where c.name like :data", ChuyenKhoa.class);
        query.setParameter("data", "%" + nameChuyenKhoa + "%");
        return query.getResultList();
    }

    @Override
    public PhongKham findPhongKhamById(int theId) {
        Optional<PhongKham> result = phongKhamRepository.findById(theId);
        PhongKham phongKham = null;
        if (result.isPresent()) {
            phongKham = result.get();
        } else {
            throw new RuntimeException("không tìm thấy account id" + theId);
        }
        return phongKham;
    }

    @Override
    public List<PhongKham> findPhongKhamByName(String namePhongKham) {
        TypedQuery<PhongKham> query = entityManager.createQuery("from PhongKham p where p.name like :data", PhongKham.class);
        query.setParameter("data", "%" + namePhongKham + "%");
        return query.getResultList();
    }

    @Override
    public UserDetails loadUserByUsername(String accountEmail) throws UsernameNotFoundException {
        Account getAccount = findAccountByEmail(accountEmail);
        if (getAccount == null) {
            System.out.println("Không thể xac thực tài khoản với email: " + accountEmail);
            throw new UsernameNotFoundException("Tên tài khoản hoặc mật khẩu không hợp lệ");

        }
        System.out.println("tải thành công tài khoản với email: " + accountEmail);
        return new org.springframework.security.core.userdetails.User(getAccount.getEmail(),
                getAccount.getPassword(),
                mapRolesToAuthorities(getAccount.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<Role> getRoles = new ArrayList<>();
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
