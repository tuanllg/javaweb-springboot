package com.prj321x.ver3.asm3.dao;

import com.prj321x.ver3.asm3.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
}
