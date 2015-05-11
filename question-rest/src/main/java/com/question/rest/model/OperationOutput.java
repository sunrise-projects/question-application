package com.question.rest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
//@XmlRootElement(name = "result", namespace = "com.testing.poc")
//@XmlAccessorType(XmlAccessType.NONE)
public class OperationOutput {

	@ApiModelProperty(position = 1, required = true, value = "Response code")
	private int responseCode;
	
	@ApiModelProperty(position = 2, required = true, value = "Response Description")
	private String responseDesc;
	
	@ApiModelProperty(position = 3, required = true, value = "Result")
	private int result;
	
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseDesc() {
		return responseDesc;
	}
	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "OperationOutput [responseCode=" + responseCode
				+ ", responseDesc=" + responseDesc + ", result=" + result + "]";
	}
	
	
	

}
