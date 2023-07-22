package com.eks.svc.idver.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eks.svc.idver.enums.CountryCodeEnum;
import com.eks.svc.idver.service.ComputeService;

@Service
public class ComputeServiceImpl implements ComputeService {
	
	private Logger logger = LoggerFactory.getLogger(ComputeServiceImpl.class);

	@Override
	public Integer computeVerNum(String id) {
		
		logger.info("ComputeServiceImpl computeVerNum (計算檢查碼): ID = {}", id);
		
		// 國民身分證統一編號檢查方法
		// 1. 第一位英文字母對應數值 * 9 + 十位數之值，然後總和再除以 10，所得之餘數
				
		int computeEngChar = Arrays
				.stream(CountryCodeEnum.values())
				.filter(countryCodeEnum -> countryCodeEnum.toString().equals(id.substring(0, 1)))
				.mapToInt(countryCodeEnum -> {
					
					int countryNumber = countryCodeEnum.getCountryNumber().intValue();
					int unitsDigit = countryNumber % 10;					
					int tensDigit = countryNumber / 10 % 10;
					logger.info("ComputeServiceImpl computeVerNum (計算檢查碼): ID = {}, countryNumber (英文對應碼) = {}, unitsDigit (個位數) = {}, tensDigit (十位數) = {}",id ,countryNumber ,unitsDigit ,tensDigit);
					
					return (unitsDigit * 9 + tensDigit) % 10;})
				.findFirst()
				.orElse(0);
				
				logger.info("ComputeServiceImpl computeVerNum (計算檢查碼): ID = {}, computeEngChar (英文對應碼計算結果) = {}",id ,computeEngChar);
				
		// 國民身分證統一編號檢查方法
		// 2. 第一位英文字母計算後所得的餘數及其他9位數字所形成的十位數字陣列
		// 3. 將這十位數字依序乘以 1 8 7 6 5 4 3 2 1
		// 4. 加總起來後除以10，再取其餘數
		// 5. 再取此餘數之補數，得到檢查碼
		// 6. 因為餘數0之補數為10，因此再除以10以處理餘數0之問題，餘數0之檢查碼應為0
				
		// a. 餘數 	0  1 2 3 4 5 6 7 8 9 
		// b. 補數	10 9 8 7 6 5 4 3 2 1  
		// c. 檢查碼 0  9 8 7 6 5 4 3 2 1 
		// d. 做法 (10-餘數)%10   
				
		AtomicInteger counter = new AtomicInteger(9);
		int verNum = (10 - id
				.substring(1, 9)
				.chars()
				.mapToObj(Character::getNumericValue)
				.mapToInt(num -> {	
					
					int multiplyResult = num.intValue() * (counter.decrementAndGet());
					logger.info("ComputeServiceImpl computeVerNum (計算檢查碼): ID = {}, num (數字碼) = {}, multiplyResult (數字碼計算結果) = {}",id ,num ,multiplyResult);
					return multiplyResult;})
				
				.reduce(computeEngChar, Integer::sum) % 10)
				%10;
				
		logger.info("ComputeServiceImpl computeVerNum (計算檢查碼): ID = {}, verNum (檢查碼) = {}", id, verNum);
		
		return verNum;
	}

	@Override
	public boolean checkIdValid(String id) {
		
		logger.info("ComputeServiceImpl checkIdValid (檢查ID是否合法): ID = {}", id);
		
		if(computeVerNum(id) == Integer.parseInt(id.substring(id.length()-1))) {
			return true;
		}
		
		return false;
	}

	@Override
	public List<String> createValidRandomId(Integer idCount) {
		
		logger.info("ComputeServiceImpl createValidRandomId (隨機產生合法ID): idCount (產生數量) = {}", idCount);
		
		List<String> listRandomID = new ArrayList<>();
		
		// 國民身分證統一編號製造方法
		// 1. 英文數字 + 姓別碼 + 7碼隨機 + 1碼檢查碼
		
		for (int i = 0; i < idCount; i++) {
					
			StringBuilder randomID = new StringBuilder();
			
			int randomASC2 = (int) (Math.random() * (90 - 65 + 1)) + 65;
			char randomEngChar = (char) randomASC2;
			
			int randomSex = (int) (Math.random() * (2 - 1 + 1)) + 1;
					
			randomID.append(randomEngChar).append(randomSex);
			
			new Random().ints(7, 0, 10).forEach(randomNum -> randomID.append(randomNum));
							
			int verNum = computeVerNum(randomID.toString()+"1");
			
			randomID.append(verNum);
			
			logger.info("ComputeServiceImpl createValidRandomId (隨機產生合法ID): randomId (ID) = {}", randomID);
		
			listRandomID.add(randomID.toString());			
		}
		
		return listRandomID;
	}

	@Override
	public List<String> createInvalidRandomId(Integer idCount) {
		
		logger.info("ComputeServiceImpl createInvalidRandomID (隨機產生不合法ID): idCount (產生數量) = {}", idCount);
		
		List<String> listRandomID = new ArrayList<>();
		
		for (int i = 0; i < idCount; i++) {
			StringBuilder randomID = new StringBuilder();
			new Random().ints(1, 65, 91).forEach(randomEngChar -> randomID.append((char)randomEngChar));
			new Random().ints(1, 1, 3).forEach(randomSex -> randomID.append(randomSex));
			new Random().ints(8, 0, 10).forEach(randomNum -> randomID.append(randomNum));
			
			logger.info("ComputeServiceImpl createInvalidRandomID (隨機產生不合法ID): randomID (ID) = {}", randomID.toString());
			
			listRandomID.add(randomID.toString());
		}
		return listRandomID;
	}

}
