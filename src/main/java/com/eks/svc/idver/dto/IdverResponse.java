package com.eks.svc.idver.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdverResponse {
	
	@JsonProperty("IDLIST")
	private List<String> idList;
	
	@JsonProperty("IDCOUNT")	
	private String idCount;
	
	@JsonProperty("ID")	
	private String id;
	
	@JsonProperty("CHECKRESULT")	
	private String checkResult;
	
}
