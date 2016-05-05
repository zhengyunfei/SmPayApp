package com.zero2ipo.common.entity.query;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 条件解析处理类
 */
public class Condition {
	
	private static final String CONDITION_REGEX = "([\\d|\\w]*)(=|<=|>=|<|>|-)([\\d|\\w]+)?";
	
	private static final String PLACEHOLDER = " ";
	
	private static final String JOINSIGN_AND = " AND ";
	
	private static final String JOINSIGN_OR = " OR ";
	
	private int conditionCount = 0;
	
	/*
	 * 解析条件传入
	 */
	public void parseCondition(StringBuffer sb, String column, String conditionStr, int joinSign) {
		if(conditionStr != null && !"".equals(conditionStr)) 
		{
			sb.append("(");
			String[] arr = conditionStr.split(",");
			for (int i = 0; i < arr.length; i++) 
			{
				String str = full2half(arr[i]);
				
				Pattern p = Pattern.compile(CONDITION_REGEX);
				Matcher m = p.matcher(str);
				
				if (m.find()) 
				{
					String before = m.group(1);
					String sign = m.group(2);
					String end = m.group(3);
					
					if(before != null && !"".equals(end) && "-".equals(sign)) 
					{
						appendCondition(sb, column, ">=", before);
						sb.append(" AND ");
						appendCondition(sb, column, "<=", end);
					}
					else 
					{
						appendCondition(sb, column, sign, end);
					}
				}
				else 
				{
					appendCondition(sb, column, "=", str);
				}
				if(arr.length != i + 1) 
				{
					if(joinSign == 0)
					{
						sb.append(JOINSIGN_AND);
					}
					else 
					{
						sb.append(JOINSIGN_OR);
					}
				}
			}
			sb.append(")");
		}
	}
	
	/*
	 * 处理like条件
	 */
	public void parseLikeCondition(StringBuffer sb, String column, String likeCondition, int joinSign, String rangeSign) {
		if(likeCondition != null && !"".equals(likeCondition)) 
		{
			sb.append("(");
			String[] arr = likeCondition.split(",");
			for (int i = 0; i < arr.length; i++) 
			{
				String str = arr[i];
				appendCondition(sb, column, "like", "'%" +  rangeSign + str + rangeSign + "%'");
				if(arr.length != i + 1) 
				{
					sb.append(JOINSIGN_OR);
				}
			}
			sb.append(")");
		}
	}
	
	/*
	 * 根据条件数判断是否添加连接符号
	 */
	public void appendJoinSign(StringBuffer sb, String sign) {
		if(conditionCount > 0) 
		{
			sb.append(sign);
		}
	}
	
	/*
	 * 添加条件
	 */
	public void appendCondition(StringBuffer sb, String column, String sign, String value) {
		sb.append(column).append(PLACEHOLDER).append(sign).append(PLACEHOLDER).append(value);
		conditionCount++;
	}
	
	/*
	 * 将全角符号替换为半角符号
	 */
	private String full2half(String str) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("＜", "<");
		map.put("＞", ">");
		map.put("＞＝", ">=");
		map.put("＜＝", "<=");
		map.put("－", "-");
		
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String key = it.next();
			str = str.replaceAll(key, map.get(key));
		}
		return str;
	} 

}
