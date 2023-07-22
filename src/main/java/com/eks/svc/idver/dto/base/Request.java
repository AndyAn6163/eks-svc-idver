package com.eks.svc.idver.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Request<T> {

	@JsonProperty("REQUESTHEADER")
	@NotNull(message="資料檢核有誤, REQUESTHEADER 不得為空")
	@Valid
	private DtoHeader dtoHeader;
	
	@JsonProperty("REQUEST")
	@NotNull(message="資料檢核有誤, REQUEST 不得為空")
	@Valid
	private T request;
	
}
