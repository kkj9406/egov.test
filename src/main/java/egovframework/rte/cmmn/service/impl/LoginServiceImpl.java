package egovframework.rte.cmmn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.cmmn.service.LoginService;
import egovframework.rte.cmmn.vo.Account;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("loginService")
public class LoginServiceImpl extends AbstractServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    public Account authenticate(String regUser, String password) {
        // TODO [Step 4-1-02] 구현되어져 있는 LoginDao의 authenticate 메소드를 이용하여 검증하여 
        // 결과(Account)객체를 리턴한다.
//    	return null;
    	return loginDao.authenticate(regUser, password);

    }

}
 