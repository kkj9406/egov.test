package egovframework.rte.cmmn.service.impl;

import org.springframework.stereotype.Service;

import egovframework.rte.cmmn.service.TestService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

//@Service("examService")
public class ExamServiceImpl extends AbstractServiceImpl implements TestService {

	public void doService() {
		System.out.println("ExamServiceImpl : doService() is called");
	}

}
