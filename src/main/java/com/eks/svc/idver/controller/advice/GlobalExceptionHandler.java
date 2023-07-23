package com.eks.svc.idver.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.eks.svc.idver.dto.IdverResponse;
import com.eks.svc.idver.dto.base.DtoHeader;
import com.eks.svc.idver.dto.base.Response;
import com.eks.svc.idver.enums.ResponseCodeEnum;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response<IdverResponse>> HandleConstraintViolationException(){
		
		Response<IdverResponse> response = new Response<>();
		DtoHeader dtoHeader = new DtoHeader();
		dtoHeader.setResponseCodeAndDesc(ResponseCodeEnum.E002);
		response.setDtoHeader(dtoHeader);
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);	
		
	}

}
