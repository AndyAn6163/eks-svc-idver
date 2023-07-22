package com.eks.svc.idver.enums;

public enum CountryCodeEnum {
	
	A(10,"臺北市"),
	B(11,"臺中市"),	
	C(12,"基隆市"),
	D(13,"臺南市"),
	E(14,"高雄市"),
	F(15,"新北市"),
	G(16,"宜蘭縣"),
	H(17,"桃園市"),
	I(34,"嘉義市"),
	J(18,"新竹縣"),
	K(19,"苗栗縣"),	
	M(21,"南投縣"),
	N(22,"彰化縣"),
	O(35,"新竹市"),
	P(23,"雲林縣"),
	Q(24,"嘉義縣"),
	T(27,"屏東縣"),
	U(28,"花蓮縣"),
	V(29,"臺東縣"),
	W(32,"金門縣"),
	X(30,"澎湖縣"),
	Z(33,"連江縣"),
	L(20,"臺中縣"),
	R(25,"臺南縣"),
	S(26,"高雄縣"),
	Y(31,"陽明山管理局");	
			
	private Integer countryNumber;

	private String countryName;
	
	private CountryCodeEnum(Integer countryNumber, String countryName) {
		this.countryNumber = countryNumber;
		this.countryName = countryName;
	}

	public Integer getCountryNumber() {
		return countryNumber;
	}

	public String getCountryName() {
		return countryName;
	}
	
	public Integer getCountryNumberFromCharacter(String Character) {
		for(CountryCodeEnum countryEnum : CountryCodeEnum.values()) {
			if(countryEnum.toString().equals(Character)) {
				return countryEnum.getCountryNumber();
			}
		}
		return null;
	}
}
