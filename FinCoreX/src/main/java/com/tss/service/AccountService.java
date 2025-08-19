package com.tss.service;

import java.util.List;

import com.tss.dao.AccountDAO;
import com.tss.model.Account;

public class AccountService {
	private final AccountDAO accountDAO = new AccountDAO();

	public List<Account> getUserAccounts(int userId) {
		return accountDAO.getAccountsByUserId(userId);
	}
}

