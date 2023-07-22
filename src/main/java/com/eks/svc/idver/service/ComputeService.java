package com.eks.svc.idver.service;

import java.util.List;

public interface ComputeService {

	Integer computeVerNum(String id);
	
	boolean checkIdValid(String id);
	
	List<String> createValidRandomId(Integer idCount);
	
	List<String> createInvalidRandomId(Integer idCount);
}
