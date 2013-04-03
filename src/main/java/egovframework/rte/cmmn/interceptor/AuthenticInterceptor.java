package egovframework.rte.cmmn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import egovframework.rte.cmmn.vo.Account;



public class AuthenticInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * 세션에 계정정보(Account)가 있는지 여부로 인증 여부를 체크한다.
	 * 계정정보(Account)가 없다면, 로그인 페이지로 이동한다.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {		
		// HandlerInterceptorAdapter는 preHandle, postHandle, afterCompletion을 지원하고
		// 계정이 세션에 있는지 검사하여 리다이렉트 시키는 작업은 요청 전에 해야하므로
		// preHandle 클래스를 오버라이드하여 사용한다.
		
		Account account = (Account) WebUtils.getSessionAttribute(request, "UserAccount");
		//account형의 세션을 받아서
		
		if(account!=null){//세션에 계정이 있으면 true를 반납하고 
			return true;
		}else{//계정이 없다면 로그인화면으로 리다이렉트 한다.
			ModelAndView modelAndView = new ModelAndView("redirect:/sample/login.do");			
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}

}
