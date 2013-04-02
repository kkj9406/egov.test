package egovframework.rte.cmmn.service;

import egovframework.rte.cmmn.vo.Account;

public interface LoginService {
	
	public Account authenticate(String regUser, String password);	
}
