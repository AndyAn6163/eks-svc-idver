package com.eks.svc.idver.dto.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
	
	@JsonProperty("RESPONSEHEADER")
	private DtoHeader dtoHeader;
	
	@JsonProperty("RESPONSE")
	private T response;

}
