package com.eks.svc.idver.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eks.svc.idver.dto.IdverRequest;
import com.eks.svc.idver.dto.IdverResponse;
import com.eks.svc.idver.dto.base.Request;
import com.eks.svc.idver.dto.base.Response;
import com.eks.svc.idver.service.ComputeService;
import com.eks.svc.idver.service.IdverService;

@Service
public class IdverServiceImpl implements IdverService {
	
	private Logger logger = LoggerFactory.getLogger(IdverServiceImpl.class);
	
	@Autowired
	ComputeService computerService;
	
	//@Autowired
	//GatewayComponent gatewayComponent;

	@Override
	public Response<IdverResponse> query(Request<IdverRequest> request) {
		
		
		
		
		
		
		
		
		// TODO Auto-generated method stub
		return null;
	}
	
	

	

}
