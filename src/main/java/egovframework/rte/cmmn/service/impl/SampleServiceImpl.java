package egovframework.rte.cmmn.service.impl;


import org.springframework.stereotype.Service;



import egovframework.rte.cmmn.service.SampleService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service
public class SampleServiceImpl extends AbstractServiceImpl implements SampleService {

    public void invokeMethodAException() throws Exception {
        try{
           int i = 1/0;
        }catch(ArithmeticException ae){
            throw processException("msg.exception.case1", ae);
        }

    }

    public void invokeMethodBException() throws Exception {


    }

}
