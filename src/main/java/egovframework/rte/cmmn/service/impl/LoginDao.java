package egovframework.rte.cmmn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import egovframework.rte.cmmn.vo.Account;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

//@Repository("loginDao")로 dao클래스를 찾아갈 id값 명시.ibatis에서 이id로 접근가능
@Repository("loginDao")
public class LoginDao  extends EgovAbstractDAO {
	
	public Account authenticate(String regUser, String password) throws DataAccessException{
		Account account = new Account();
		account.setRegUser(regUser);
		account.setPassword(password);
		return (Account)selectByPk("Member.authenticate", account);
	}
}