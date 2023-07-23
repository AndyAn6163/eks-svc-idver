package com.eks.svc.idver.controller.advice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.eks.svc.idver.dto.IdverResponse;
import com.eks.svc.idver.dto.base.DtoHeader;
import com.eks.svc.idver.dto.base.Response;
import com.eks.svc.idver.enums.ResponseCodeEnum;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// https://blog.csdn.net/weixin_44021888/article/details/122237646
	// Springboot捕获全局异常：MethodArgumentNotValidException
	
	// https://juejin.cn/post/7121539527151190053
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Response<IdverResponse>> HandleConstraintViolationException(MethodArgumentNotValidException e){
				
		List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
		String responseDesc = allErrors.stream().map(error-> error.getDefaultMessage()).collect(Collectors.joining(";"));
		
		Response<IdverResponse> response = new Response<>();
		DtoHeader dtoHeader = new DtoHeader();
		dtoHeader.setResponseCode(ResponseCodeEnum.E002.getResponseCode());
		dtoHeader.setResponseDesc(responseDesc);
		response.setDtoHeader(dtoHeader);
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);	
		
	}

}
