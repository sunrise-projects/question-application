package com.testing.poc.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class OperationInput {


    @ApiModelProperty(position = 1, required = true, value = "First number")
    private int firstNumber;
    
    @ApiModelProperty(position = 2, required = true, value = "Second number")
    private int secondNumber;
    
    public int getFirstNumber() {
		return firstNumber;
	}

	public void setFirstNumber(int firstNumber) {
		this.firstNumber = firstNumber;
	}

	public int getSecondNumber() {
		return secondNumber;
	}

	public void setSecondNumber(int secondNumber) {
		this.secondNumber = secondNumber;
	}

}
