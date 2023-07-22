package com.eks.svc.idver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class IdverRqesut {
	
	@JsonProperty("FUNCTION")
	@Pattern(regexp="^(1|2)$",message="資料檢核有誤，身分證服務使用方式只有1隨機產生合法身分證、2驗證身分證是否合法")  
	@Valid
	private String function;
	
	@JsonProperty("ID")
	@Pattern(regexp = "^[A-Z]{1}[1-2]{1}[\\d]{8}$", message = "資料檢核有誤, ID 欄位應為中華民國身分證合法規則")
	@Valid
	private String id;
	
	@JsonProperty("IDCOUNT")
	@Pattern(regexp = "^[\\d]+$", message = "資料檢核有誤, IDCOUNT 欄位不能為負數")
	@Valid
	private String idCount;

}
