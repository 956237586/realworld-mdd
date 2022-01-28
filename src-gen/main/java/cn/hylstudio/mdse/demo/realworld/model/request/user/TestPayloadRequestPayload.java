package cn.hylstudio.mdse.demo.realworld.model.request.user;

import cn.hylstudio.mdse.demo.realworld.model.request.RequestPayload;
import cn.hylstudio.mdse.demo.realworld.util.ValueUtils;

public class TestPayloadRequestPayload extends RequestPayload {
	private Integer inttest;

	private Long longtest;

	private Boolean booleanTest;

	public TestPayloadRequestPayload(){
	}

	public void checkTestPayloadRequestPayload(TestPayloadRequestPayload testPayload) {
		ValueUtils.nonNull(testPayload.getInttest(), "empty testPayload.inttest");
		ValueUtils.checkNumberRange(testPayload.getInttest(), 0, 10, "testPayload.inttest range error");
		
		ValueUtils.nonNull(testPayload.getLongtest(), "empty testPayload.longtest");
		ValueUtils.checkNumberRange(testPayload.getLongtest(), 10L, 20L, "testPayload.longtest range error");
		
		ValueUtils.checkTrue(testPayload.getBooleanTest(), "testPayload.booleanTest value error");
		ValueUtils.checkFalse(testPayload.getBooleanTest(), "testPayload.booleanTest value error");
	}

	public Integer getInttest() {
	    return inttest;
	}

	public void setInttest(Integer inttest) {
	    this.inttest = inttest;
	}

	public Long getLongtest() {
	    return longtest;
	}

	public void setLongtest(Long longtest) {
	    this.longtest = longtest;
	}

	public Boolean getBooleanTest() {
	    return booleanTest;
	}

	public void setBooleanTest(Boolean booleanTest) {
	    this.booleanTest = booleanTest;
	}

}
