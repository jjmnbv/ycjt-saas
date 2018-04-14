package com.beitu.saas.risk.helpers;

public class CalculateInterestUtil {

	public static float turnOver(int cash,int week,int percent,int userType){
		if(cash == 400){
			return 400 * 0.08f * percent / 100;
		}else if(cash == 600){
			return 600 * 0.08f * percent / 100;
		}else if(cash == 1000){
			if(userType == 1){
				return 1000 * 0.14f * percent / 100;
			}else{
				return 1000 * 0.04f * week * percent / 100;
			}
		}else if(cash == 2000 && week == 4){
			return 2000 * 0.14f * percent / 100;
		}else if(cash == 2000 && week == 8){
			return 2000 * 0.24f * percent / 100;
		}else{
			return 0;
		}
	}
	
	public static float repaymentAmount(int cash,int week,int percent,int userType){
		if(cash == 400){
			return 400 * 0.08f * percent / 100 + cash;
		}else if(cash == 600){
			return 600 * 0.08f * percent / 100 + cash;
		}else if(cash == 1000){
			if(userType == 1){
				return 1000 * 0.14f * percent / 100 + cash;
			}else{
				return 1000 * 0.04f*week * percent / 100 + cash;
			}
		}else if(cash == 2000 && week == 4){
			return 2000 * 0.14f * percent / 100 + cash;
		}else if(cash == 2000 && week == 8){
			return (2000 * 0.24f * percent / 100 + cash)/(week/4);
		}else{
			return 0;
		}
	}
	
	public static float getTurnOverServiceFeeRatio(int cash,int week,int percent,int userType){
		if(cash == 400){
			return 0.08f * percent / 100;
		}else if(cash == 600){
			return 0.08f * percent / 100;
		}else if(cash == 1000 * percent / 100){
			if(userType == 1){
				return 0.14f;
			}else{
				return 0.04f * week;
			}
		}else if(cash == 2000 && week == 4){
			return 0.14f * percent / 100;
		}else if(cash == 2000 && week == 8){
			return 0.24f * percent / 100;
		}else{
			return 0;
		}
	}
}
