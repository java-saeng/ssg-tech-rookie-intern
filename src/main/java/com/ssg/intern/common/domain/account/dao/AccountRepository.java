package com.ssg.intern.common.domain.account.dao;

import com.ssg.intern.common.domain.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
