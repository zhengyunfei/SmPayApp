package com.zero2ipo.mobile.utils;

import java.util.List;

/**
 * 集合处理工具类
 * @author liyang
 *
 */
public class ListUtils {

	/*
     * 结合数据截取
     */
    public static <T> List<T> getSubListPage(List<T> list, int skip,
            int pageSize) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        int startIndex = skip;
        int endIndex = skip + pageSize;
        if (startIndex > endIndex || startIndex > list.size()) {
            return null;
        }
        if (endIndex > list.size()) {
            endIndex = list.size();
        }
        return list.subList(startIndex, endIndex);
    }

}
