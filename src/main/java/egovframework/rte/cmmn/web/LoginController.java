package egovframework.rte.cmmn.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.rte.cmmn.service.LoginService;
import egovframework.rte.cmmn.service.SampleService;
import egovframework.rte.cmmn.vo.Account;


@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    //Autowired 어노테이션으로 멤버 필드에 객체 생성
    @Autowired
    private SampleService sampleService;

    /**
     * 사용자로 부터 아이디, 패스워드를 입력받아 인증 성공이면 세션 객체에 계정정보를 담고 
     * 게시판 페이지로 포워딩한다. 인증에 실패하면 로그인 페이지로 다시 리턴한다.
     */
    @RequestMapping("/sample/loginProcess.do")//dispatcherservlet에 의해 loginprocess.do요청을 받음
    public String login(HttpServletRequest request, @RequestParam("regUser") String regUser,
            @RequestParam("password") String password) {
    	//계정정보인 regUser와 password를 파라미터로 받는다.

        // LoginService의 authenticate메소드를 이용하여 
    	// 로긴여부 체크 Account 객체를 리턴 받는다.
    	Account account = (Account) loginService.authenticate(regUser, password);

        /*
         * 가져온 account 객체가 null 아닌 경우
         * request.getSession() 메소드를 통해 Session을 구해 UserAccount 이름으로 Session 에
         * Attribute로 저장한다. 그리고 "redirect:/loginSuccess.do" 값을 반환한다. null 인경우
         * "login" 반환한다.
         */    	
        if (account != null) {//계정이 null이 아니면
        	//세션에 계정정보를 set하고 loginSuccess.do로 리다이렉트
            request.getSession().setAttribute("UserAccount", account);
            return "redirect:/sample/loginSuccess.do";
        } else {//계정이 null이면 다시 login화면으로 이동
            return "/sample/login";
        }
    	
    } 

    // loginSuccess.do 로 호출된 처리
    // (@RequestMapping(value="/loginSuccess.do", method=RequestMethod.GET) 를 위한
    // 메소드(loginSuccess) 를 void 타입의 public 메소드로 작성
    @RequestMapping(value = "/sample/loginSuccess.do", method = RequestMethod.GET)
    public void loginSuccess() {
    }
    

    @RequestMapping("/sample/exception.do")
    public String invokeException(@RequestParam("regUser") String regUser) throws Exception {
        
        sampleService.invokeMethodAException();
        
        return null;
    }
    
}
