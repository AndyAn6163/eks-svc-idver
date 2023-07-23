package com.eks.svc.idver.service.impl;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eks.svc.idver.dto.IdverRequest;
import com.eks.svc.idver.dto.IdverResponse;
import com.eks.svc.idver.dto.base.DtoHeader;
import com.eks.svc.idver.dto.base.Request;
import com.eks.svc.idver.dto.base.Response;
import com.eks.svc.idver.enums.ResponseCodeEnum;
import com.eks.svc.idver.service.ComputeService;
import com.eks.svc.idver.service.IdverService;

import jakarta.validation.Valid;

@Service
public class IdverServiceImpl implements IdverService {
	
	private Logger logger = LoggerFactory.getLogger(IdverServiceImpl.class);
	
	@Autowired
	ComputeService computerService;
	
	//@Autowired
	//GatewayComponent gatewayComponent;

	@Override
	public Response<IdverResponse> query(@Valid Request<IdverRequest> request) {
		
		logger.info("IdVerificationServiceImpl query (身分證服務): request = {}", request.toString());
		
		Response<IdverResponse> response = new Response<>();
		IdverResponse idverResponse = new IdverResponse();
		DtoHeader dtoHeader = new DtoHeader();
		
		String svcType = request.getRequest().getSvcType();
		String id = request.getRequest().getId();
		String idCount = request.getRequest().getIdCount();
		
		if (Integer.valueOf(svcType)==1&&idCount!=null) {
			
			logger.info("IdVerificationServiceImpl query (身分證服務): SVCTYPE = 1 (隨機產生合法ID), IDCOUNT (產生數量) = {}", idCount);
			
			List<String> listRandomID = computerService.createValidRandomId(Integer.valueOf(idCount));
			idverResponse.setIdCount(idCount);
			idverResponse.setIdList(listRandomID);
			dtoHeader.setResponseCodeAndDesc(ResponseCodeEnum.SUCCESS);
		}
		
		else if (Integer.valueOf(svcType)==2&&id!=null) {
			
			logger.info("IdVerificationServiceImpl query (身分證服務): SVCTYPE = 2 (檢查ID是否合法), ID = {}", id);
			
			if(computerService.checkIdValid(id)) {
				idverResponse.setCheckResult("valid");
				dtoHeader.setResponseCodeAndDesc(ResponseCodeEnum.SUCCESS);	
			}else {
				idverResponse.setCheckResult("valid");
				dtoHeader.setResponseCodeAndDesc(ResponseCodeEnum.SUCCESS);		
			}
			
		}
		
		else if (Integer.valueOf(svcType)==3&&idCount!=null) {
			
			logger.info("IdVerificationServiceImpl query (身分證服務): SVCTYPE = 3 (驗證其他身分證服務是否正常), IDCOUNT (產生數量) = {}", idCount);
						
			for(int i=0;i<Integer.valueOf(idCount);i++) {
				
				String randomId;
				boolean valid = new Random().nextBoolean();
				
				if(valid) {
					randomId=computerService.createValidRandomId(1).get(0);
					logger.info("IdVerificationServiceImpl query (身分證服務): SVCTYPE = 3 (驗證其他身分證服務是否正常), 隨機產生合法ID = {}", randomId);
				}else {
					randomId=computerService.createInvalidRandomId(1).get(0);
					logger.info("IdVerificationServiceImpl query (身分證服務): SVCTYPE = 3 (驗證其他身分證服務是否正常), 隨機產生不合法ID = {}", randomId);
				}
								
				//request.getRequest().setId(randomId);		
				//Response apiResponse= gatewayComponent.callApi(request, new ParameterizedTypeReference<Response>(){});		
				//logger.info("IdVerificationServiceImpl call API checkid: randomId = {}, result = {}", randomId, apiResponse.getCheck());
			}
		}		
			
		else {
			dtoHeader.setResponseCodeAndDesc(ResponseCodeEnum.E001);	
		}
		
		response.setDtoHeader(dtoHeader);
		response.setResponse(idverResponse);
		return response;
	}
	
	

	

}
