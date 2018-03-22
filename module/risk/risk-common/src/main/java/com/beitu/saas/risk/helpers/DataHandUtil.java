/**   
* @Title: DateHandUtil.java 
* @author zxy   
* @date 2015年6月3日 上午11:31:59  
*/
package com.beitu.saas.risk.helpers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/** 
 * @ClassName: DateHandUtil 
 * @Description: 数据处理帮助类
 * @author zxy 
 * @date 2015年6月3日 上午11:31:59  
 */
public class DataHandUtil {
	
	
	//获取百分比
		public static String getPercent(int count,int part){
			if(count==0){
				return "0.00%";
			}else{
				double x=part*1.0; 
				double tempresult=x/count; 
				DecimalFormat df1 = new DecimalFormat("0.00%");
				return df1.format(tempresult);
			}
		}
		
		//获取百分比
		public static String getPercent(double count,double part){
			if(count==0){
				return "0.00%";
			}else{
				double x=part*1.0; 
				double tempresult=x/count; 
				DecimalFormat df1 = new DecimalFormat("0.00%");
				return df1.format(tempresult);
			}
		}
		
		//获取百分比
		public static String getPercent(float count,float part){
			if(count==0){
				return "0.00%";
			}else{
				double x=part*1.0; 
				double tempresult=x/count; 
				DecimalFormat df1 = new DecimalFormat("0.00%");
				return df1.format(tempresult);
			}
		}
		
		//计算平均数
		public static String getAverage(int count,int part){
			if(count==0){
				return "0.00";
			}else{
				double x=count*1.0; 
				double tempresult=x/part; 
				DecimalFormat df = new DecimalFormat("0.00");
				return df.format(tempresult);
			}
		}
		
		
		//获取百分比a2占a1百分比
		public static String getPercent(BigDecimal a1,BigDecimal a2){
			if(a1==null||a1==BigDecimal.ZERO||a1.compareTo(BigDecimal.ZERO)==0||a2==null||a2==BigDecimal.ZERO||a2.compareTo(BigDecimal.ZERO)==0){
				return "0.00%";
			}
			
	        BigDecimal r =a2.divide(a1, 4, BigDecimal.ROUND_HALF_EVEN).setScale(4,BigDecimal.ROUND_HALF_UP);
	        NumberFormat percent = NumberFormat.getPercentInstance();
	        percent.setMaximumFractionDigits(2);
		    return percent.format(r.doubleValue());
		}
		
		
		public static void main(String args[]){
			System.out.println(DataHandUtil.getPercent(BigDecimal.ZERO.subtract(BigDecimal.ZERO), BigDecimal.ZERO));
		}

}
