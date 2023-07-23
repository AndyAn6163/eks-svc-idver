package com.eks.svc.idver.dto.base;

import com.eks.svc.idver.enums.ResponseCodeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true, value="responseCodeAndDesc")
public class DtoHeader {
	
	@JsonProperty("EVENTID")
	@NotBlank(message="資料檢核有誤, EVENTID 不得為空")
	private String eventId;
					
	@JsonProperty("SVCID")
	@NotBlank(message="資料檢核有誤, SVCID 不得為空")
	private String svcId;
		
	@JsonProperty("RESPONSECODE")
	private String responseCode;
		
	@JsonProperty("RESPONSEDESC")
	private String responseDesc;
	
	public void setResponseCodeAndDesc(ResponseCodeEnum responseCodeEnum) {
		this.setResponseCode(responseCodeEnum.getResponseCode());
		this.setResponseDesc(responseCodeEnum.getResponseDesc());
	}
		
}
