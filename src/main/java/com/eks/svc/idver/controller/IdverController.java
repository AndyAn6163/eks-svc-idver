package com.eks.svc.idver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eks.svc.idver.dto.IdverRequest;
import com.eks.svc.idver.dto.IdverResponse;
import com.eks.svc.idver.dto.base.Request;
import com.eks.svc.idver.dto.base.Response;
import com.eks.svc.idver.service.IdverService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
public class IdverController {
	
	@Autowired
	IdverService idverService;
		
	@PostMapping("/query")
	public ResponseEntity<Response<IdverResponse>> query(@RequestBody @Valid Request<IdverRequest> request){
		
		Response<IdverResponse> response = idverService.query(request);
			
		return new ResponseEntity<>(response, HttpStatus.OK);
			
	}

}
