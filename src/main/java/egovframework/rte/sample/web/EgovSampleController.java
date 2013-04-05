/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.rte.sample.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.sample.service.EgovSampleService;
import egovframework.rte.sample.service.SampleDefaultVO;
import egovframework.rte.sample.service.SampleVO;

/**  
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
 * @Modification Information  
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 * 
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 * 
 *  Copyright (C) by MOPAS All right reserved.
 */
/* @SessionAttributes은 상태유지를 위해 사용하는 어노테이션으로
 * Controller가 생성하는 모델 정보중 @SessionAttributes에 지정한 것과 동일한
 * 것이 있다면 이를 Session에 저장. 
 * 
 * @ModelAttribute는 지정된 파라미터가 있을 때 이 파라미터에 전달해줄
 * 오브젝트를 그 세션에서 가져온다.
 * @ModelAttribute (page scope) 입력폼에서 필요로하는 데이터를 전달하기위한 
 * referenceDate() 에 어노테이션 버젼
 * HTTP Request에 포함된 파라미터를 Model 객체로 바인딩함
 * @ModelAttribute의 'name'으로 정의한 Model객체를 다음 View에서 사용 가능
 * 데이터를 화면에 똑같이 살리고싶을때 사용
 * Spring 커스텀 태그와 같이 사용
 * 
 * 원래 @ModelAttribute는 해당 타입의 새 오브젝트를 생성한 후 요청 파라미터
 * 값을 프로퍼티에 바인딩하지만 @SessionAttributes와 @ModelAttribute의 모델
 * 이름이 동일하면 먼저 Session에 같은 이름의 오브젝트가 존재하는지 확인한다.
 * 만약 존재하면 모델 오브젝트를 새로 만들지 않고 Session에 있는 오브젝트를
 * 가져와 @ModelAttribute 파라미터로 전달할 오브젝트로 사용한다. 
 * 
 * @SessionAttributes는 하나 이상의 모델을 Session에 저장하도록 지정할 수 있다. 
 * @SessionAttributes 의 설정은 클래스의 모든 메소드에 적용된다. Controller 
 * 메소드에 의해 생성되는 모든 종류의 모델 오브젝트는 @SessionAttributes에 
 * 저장될 이름을 갖고 있는지 확인된다. 따라서 Model 파라미터를 이용해 저장한 
 * 모델이든, 단일 모델 오브젝트의 리턴을 통해 만들어지는 모델이든, @ModelAttribute 
 * 로 정의된 모델이든 상관없이 모두 @SessionAttributes의 적용 후보가 된다. 
 * 단, @SessionAttributes의 기본 구현인 HTTP Session을 이용한 Session 저장소는 
 * 모델 이름을 Session에 저장할 애트리뷰트 이름으로 사용한다는 점을 주의하자. 
 * 따라서 @SessionAttributes에 사용하는 모델 이름에 충돌이 발생하지 않도록 주의해야 한다.
*/
@Controller
@SessionAttributes(types=SampleVO.class)
public class EgovSampleController {
	
	//@Resource로 의존하는 빈 객체 전달.사용할 service클래스를 @Resource을 이용하여 받아온다.
	/** EgovSampleService */
    @Resource(name = "sampleService")
    private EgovSampleService sampleService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** Validator */
    @Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
    /**
	 * 글 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SampleDefaultVO
	 * @param model
	 * @return "/sample/egovSampleList"
	 * @exception Exception
	 * 최초 게시판 리스트조회, 검색어 조회시 사용
	 */
    @RequestMapping(value="/sample/egovSampleList.do")
    public String selectSampleList(@ModelAttribute("searchVO") SampleDefaultVO searchVO, 
    		ModelMap model)
            throws Exception {
    	
    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));//프로퍼티값(context-properties.xml)을 받아 SET
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();//페이지계산을 위한 객체 생성
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());//페이지정보 set
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());//페이지당 게시되는 게시물수set
		paginationInfo.setPageSize(searchVO.getPageSize());//페이지리스트에 게시되는 페이지수set
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());//페이지리스트의 첫번호
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());//페이지리스트의 마지막번호
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());//페이지당 게시되는 게시물수
		
        List sampleList = sampleService.selectSampleList(searchVO);//글목록을 list형식으로 가져옴
        //resultList라는 이름의 어트리뷰트에 sampleList를 어트리뷰트값으로 add
        model.addAttribute("resultList", sampleList);
        
        int totCnt = sampleService.selectSampleListTotCnt(searchVO);//글 총수
		paginationInfo.setTotalRecordCount(totCnt);//글의 총수 set
        model.addAttribute("paginationInfo", paginationInfo);//jsp(view)에서 paginationInfo로 접근할수있다.
        
        return "/sample/egovSampleList";
    } 

    /**
	 * 글을 조회한다.
	 * @param sampleVO - 조회할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("sampleVO") - 조회한 정보
	 * @exception Exception
	 */
    @RequestMapping("/sample/selectSample.do")
    public @ModelAttribute("sampleVO")
    SampleVO selectSample(
            SampleVO sampleVO,
            @ModelAttribute("searchVO") SampleDefaultVO searchVO) throws Exception {
        return sampleService.selectSample(sampleVO);
    }
		
    /**
	 * 글 등록 화면을 조회한다.
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/sample/egovSampleRegister"
	 * @exception Exception
	 */
    @RequestMapping("/sample/addSampleView.do")
    public String addSampleView(
            @ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("sampleVO", new SampleVO());
        return "/sample/egovSampleRegister";
    }
    
    /**
	 * 글을 등록한다.
	 * @param sampleVO - 등록할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/sample/egovSampleList.do"
	 * @exception Exception
	 */
    @RequestMapping("/sample/addSample.do")
    public String addSample(
    		@ModelAttribute("searchVO") SampleDefaultVO searchVO,
       	 	SampleVO sampleVO,
            BindingResult bindingResult, Model model, SessionStatus status) 
    throws Exception {
    	
    	// Server-Side Validation
    	beanValidator.validate(sampleVO, bindingResult);
    	
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("sampleVO", sampleVO);
			return "/sample/egovSampleRegister";
    	}
    	
        sampleService.insertSample(sampleVO);
        status.setComplete();
        return "forward:/sample/egovSampleList.do";
    }
    
    /**
	 * 글 수정화면을 조회한다.
	 * @param id - 수정할 글 id
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/sample/egovSampleRegister"
	 * @exception Exception
	 * egovSampleList.jsp의 fn_egov_select(id)가 호출
	 */
    @RequestMapping("/sample/updateSampleView.do")
    public String updateSampleView(
            @RequestParam("selectedId") String id ,//파라미터로 전달된 글번호id
            //serachVO라는 이름으로 목록 조회조건이 모델정보에 담아진다.
            @ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model)
            throws Exception {
        SampleVO sampleVO = new SampleVO();
        sampleVO.setId(id);
        // 변수명은 CoC 에 따라 sampleVO
        model.addAttribute(selectSample(sampleVO, searchVO));
        return "/sample/egovSampleRegister";
    }

    /**
	 * 글을 수정한다.
	 * @param sampleVO - 수정할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/sample/egovSampleList.do"
	 * @exception Exception
	 */
    @RequestMapping("/sample/updateSample.do")
    public String updateSample(
            @ModelAttribute("searchVO") SampleDefaultVO searchVO, 
            SampleVO sampleVO, 
            BindingResult bindingResult, Model model, SessionStatus status)
            throws Exception {

    	beanValidator.validate(sampleVO, bindingResult);
    	
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("sampleVO", sampleVO);
			return "/sample/egovSampleRegister";
    	}
    	
        sampleService.updateSample(sampleVO);
        status.setComplete();
        return "forward:/sample/egovSampleList.do";
    }
    
    /**
	 * 글을 삭제한다.
	 * @param sampleVO - 삭제할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/sample/egovSampleList.do"
	 * @exception Exception
	 */
    @RequestMapping("/sample/deleteSample.do")
    public String deleteSample(
            SampleVO sampleVO,
            @ModelAttribute("searchVO") SampleDefaultVO searchVO, SessionStatus status)
            throws Exception {
        sampleService.deleteSample(sampleVO);
        status.setComplete();
        return "forward:/sample/egovSampleList.do";
    }

}
