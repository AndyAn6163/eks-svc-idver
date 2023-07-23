package com.eks.svc.idver.enums;

public enum ResponseCodeEnum {
	
	SUCCESS("0000","交易成功"),
	E001("E001","缺少必要欄位"),
	E002("E002","請求格式錯誤"),
	E003("E003","此API尚未開通");
	
	private String responseCode;
	private String responseDesc;
	
	private ResponseCodeEnum(String responseCode,String responseDesc) {
		this.responseCode=responseCode;
		this.responseDesc=responseDesc;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public String getResponseDesc() {
		return responseDesc;
	}

}
