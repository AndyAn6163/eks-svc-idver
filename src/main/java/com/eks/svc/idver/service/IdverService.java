package com.eks.svc.idver.service;

import com.eks.svc.idver.dto.IdverRequest;
import com.eks.svc.idver.dto.IdverResponse;
import com.eks.svc.idver.dto.base.Request;
import com.eks.svc.idver.dto.base.Response;

public interface IdverService {
	
	Response<IdverResponse> query(Request<IdverRequest> request);

}
