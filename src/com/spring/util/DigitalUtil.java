/**    
 * 文件名：BigDecimalUtil.java    
 *    
 * 版本信息：    
 * 日期：2015年2月26日    
 * Copyright 鑫合汇 Corporation 2015     
 * 版权所有    
 *    
 */
package com.spring.util;

import java.math.BigDecimal;
import java.util.Random;

import org.apache.commons.lang.StringUtils;


/**    
 *     
 * 项目名称：xhh-pay-framework    
 * 类名称：BigDecimalUtil    
 * 类描述： 数字的工具类   
 * 创建人："zhuaijun"    
 * 创建时间：2015年2月26日 下午3:34:44    
 * 修改人："zhuaijun"    
 * 修改时间：2015年2月26日 下午3:34:44    
 * 修改备注：    
 * @version     
 *     
 */
public class DigitalUtil {
	
	/**
	 * 检验BigDecimal值得合法性
	 * 
	 * 为空或者<=0时 返回false;
	 * @param val
	 * @return
	 */
	public static boolean isEffectiveBigDecimal(BigDecimal val) {
		if (val == null || val.compareTo(BigDecimal.ZERO) <= 0)  {
			return false;
		}
		
		return true;
		
	}
	
	/**
	 * 检验Long值得合法性
	 * 
	 * 为空或者<=0时 返回false;
	 * @param val
	 * @return
	 */
	public static boolean isEffectiveLong(Long val) {
		if (val == null || val.compareTo(0L) <= 0)  {
			return false;
		}
		
		return true;
		
	}
	
	/**
	 * 检验Byte值得合法性
	 * 
	 * 为空或者<=0时 返回false;
	 * @param val
	 * @return
	 */
	public static boolean isEffectiveByte(Byte val) {
		if (val == null || val.compareTo(Byte.valueOf("0")) <= 0)  {
			return false;
		}
		
		return true;
		
	}
	
	/**
	 * 检验Short值得合法性
	 * 
	 * 为空或者<=0时 返回false;
	 * @param val
	 * @return
	 */
	public static boolean isEffectiveShort(Short val) {
		if (val == null || val.compareTo(Short.valueOf("0")) <= 0)  {
			return false;
		}
		
		return true;
		
	}
	
	/**
	 * 获取有效的BigDecimal
	 * 
	 * 为空或者<=0时 返回false;
	 * @param val
	 * @return
	 */
	public static BigDecimal getEffectiveBigDecimal(BigDecimal val) {
		if (isEffectiveBigDecimal(val)) {
			return val;
		} else {
			return BigDecimal.ZERO;
		}
		
	}
	
	/**
	 * 获取有效的Byte
	 * 
	 * 为空或者<=0时 返回false;
	 * @param val
	 * @return
	 */
	public static Byte getEffectiveByte(Byte val) {
		if (isEffectiveByte(val)) {
			return val;
		} else {
			return Byte.valueOf("0");
		}
		
	}
	
	/**
	 * 获取有效的Integer
	 * 
	 * 为空或者<=0时 返回false;
	 * @param val
	 * @return
	 */
	public static Integer getEffectiveInteger(Integer val) {
		if (isEffectiveInteger(val)) {
			return val;
		} else {
			return 0;
		}
		
	}
	
	
	/**
	 * 检验Integer值得合法性
	 * 
	 * 为空或者<=0时 返回false;
	 * @param val
	 * @return
	 */
	public static boolean isEffectiveInteger(Integer val) {
		if (val == null || val.intValue() <= 0)  {
			return false;
		}
		
		return true;
		
	}
	
	
	
    /**    
       
     * getBigDecimal(返回字符串的BigDecimal值) 
    
     * @param val void   
       
     * @throws    
       
     * @since    
       
    */
    public static BigDecimal getBigDecimal(String val) {
        if (StringUtils.isEmpty(val)) {
            return BigDecimal.ZERO;
        } else {
            return BigDecimal.valueOf(Long.valueOf(val));
        }
    }
    
    /**    
    
     * getBigDecimalDivideBai(除以100返回字符串的BigDecimal值) 
    
     * @param val void   
       
     * @throws    
       
     * @since    
       
    */
    public static BigDecimal getBigDecimalDivideBai(String val) {
        if (StringUtils.isEmpty(val)) {
            return BigDecimal.ZERO;
        } else {
            return BigDecimal.valueOf(Long.valueOf(val)).divide(new BigDecimal(100));
        }
    }
    
    /**    
    
     * getBigDecimal(返回字符串的Int值) 
    
     * @param val void   
       
     * @throws    
       
     * @since    
       
    */
    public static Integer getIntegerVal(String val) {
        if (StringUtils.isEmpty(val)) {
            return null;
        } else {
            return Integer.valueOf(val);
        }
    }
    
/**    
    
     * getByteVal(返回字符串的Byte值) 
    
     * @param val void   
       
     * @throws    
       
     * @since    
       
    */
    public static Byte getByteVal(String val) {
        if (StringUtils.isEmpty(val)) {
            return null;
        } else {
            return Byte.valueOf(val);
        }
    }
    
/**    
    
     * getByteVal(返回Decimal的字符串值) 
    
     * @param val void   
       
     * @throws    
       
     * @since    
       
    */
    public static String getStrFromBigDecimal(BigDecimal val) {
        if (val == null) {
            return null;
        } else {
            return val.toString();
        }
    }
    
/**    
    
     * getByteVal(返回Decimal的字符串值) 
    
     * @param val void   
       
     * @throws    
       
     * @since    
       
    */
    public static String getStrFromInt(Integer val) {
        if (val == null) {
            return null;
        } else {
            return val.toString();
        }
    }
    
    /**    
    
     * getBigDecimal(返回字符串的BigDecimal值) 
    
     * @param val void   
       
     * @throws    
       
     * @since    
       
    */
    public static BigDecimal getYuanToFen(String val) {
        if (StringUtils.isEmpty(val)) {
            return BigDecimal.ZERO;
        } else {
        	Double d=Double.parseDouble(val);
            return new BigDecimal(d*100);
        }
    }
    
    /**
     * java生成随机数字和字母组合
     * @param length[生成随机数的长度]
     * @return
     */
    public static  String getCharAndNumr(int length)     
	{     
	    String val = "";     
	             
	    Random random = new Random();     
	    for(int i = 0; i < length; i++)     
	    {     
	        String charOrNum = random.nextInt(2) % 2 == 0 ? "num" : "char"; // 输出字母还是数字     
	             
	         if("num".equalsIgnoreCase(charOrNum)) // 数字     
	        {     
	            val += String.valueOf(random.nextInt(10));     
	        }  
	         else if("char".equalsIgnoreCase(charOrNum)) // 字符串     
	        {     
	            int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;  //取得大写字母还是小写字母     
	            val += (char) (choice + random.nextInt(26));     
	        }   
	    }     
	             
	    return val;     
	}
    
    public static String NullToBlank(Object obj) {
    	return obj == null ? "" : obj.toString();
    }
}
