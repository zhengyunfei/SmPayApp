package com.zero2ipo.pay.util;

/**
 * Created by Administrator on 2016/4/15.
 */
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * The Class MapToXMLString.
 *
 * @author sword.cai (c) Totyustar 2008.
 */
public class MapToXMLString {

    /**
     * Converter Map<Object, Object> instance to xml string. Note: currently,
     * we aren't consider more about some collection types, such as array,list,
     *
     * @param dataMap  the data map
     *
     * @return the string
     */
    public static String converter(Map<Object, Object> dataMap)
    {
        synchronized (MapToXMLString.class)
        {
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("<xmlroot>");
            Set<Object> objSet = dataMap.keySet();
            for (Object key : objSet)
            {
                if (key == null)
                {
                    continue;
                }
                strBuilder.append("\n");
                strBuilder.append("<").append(key.toString()).append(">\n");
                Object value = dataMap.get(key);
                strBuilder.append(coverter(value));
                strBuilder.append("</").append(key.toString()).append(">\n");
            }
            strBuilder.append("</xmlroot>");
            return strBuilder.toString();
        }
    }
    public static String mapToXml(Map<String,String> treeMap){
        StringBuilder sb = new StringBuilder();
        for (String key : treeMap.keySet()) {
            sb.append(key).append("=").append(treeMap.get(key)).append("&");
        }
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>\n");
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
           // if ("body".equals(entry.getKey()) || "sign".equals(entry.getKey())) {
             //   xml.append("<" + entry.getKey() + "><![CDATA[").append(entry.getValue()).append("]]></" + entry.getKey() + ">\n");
            //} else {
                xml.append("<" + entry.getKey() + ">").append(entry.getValue()).append("</" + entry.getKey() + ">\n");
           // }
        }
        xml.append("</xml>");
        return xml.toString();

    }
    public static String coverter(Object[] objects) {
        StringBuilder strBuilder = new StringBuilder();
        for(Object obj:objects) {
            strBuilder.append("<item className=").append(obj.getClass().getName()).append(">\n");
            strBuilder.append(coverter(obj));
            strBuilder.append("</item>\n");
        }
        return strBuilder.toString();
    }

    public static String coverter(Collection<?> objects)
    {
        StringBuilder strBuilder = new StringBuilder();
        for(Object obj:objects) {
            strBuilder.append("<item className=").append(obj.getClass().getName()).append(">\n");
            strBuilder.append(coverter(obj));
            strBuilder.append("</item>\n");
        }
        return strBuilder.toString();
    }

    /**
     * Coverter.
     *
     * @param object the object
     * @return the string
     */
    public static String coverter(Object object)
    {
        if (object instanceof Object[])
        {
            return coverter((Object[]) object);
        }
        if (object instanceof Collection)
        {
            return coverter((Collection<?>) object);
        }
        StringBuilder strBuilder = new StringBuilder();
        if (isObject(object))
        {
            Class<? extends Object> clz = object.getClass();
            Field[] fields = clz.getDeclaredFields();

            for (Field field : fields)
            {
                field.setAccessible(true);
                if (field == null)
                {
                    continue;
                }
                String fieldName = field.getName();
                Object value = null;
                try
                {
                    value = field.get(object);
                }
                catch (IllegalArgumentException e)
                {
                    continue;
                }
                catch (IllegalAccessException e)
                {
                    continue;
                }
                strBuilder.append("<").append(fieldName)
                        .append(" className=\"").append(
                        value.getClass().getName()).append("\">\n");
                if (isObject(value))
                {
                    strBuilder.append(coverter(value));
                }
                else if (value == null)
                {
                    strBuilder.append("null\n");
                }
                else
                {
                    strBuilder.append(value.toString() + "\n");
                }
                strBuilder.append("</").append(fieldName).append(">\n");
            }
        }
        else if (object == null)
        {
            strBuilder.append("null\n");
        }
        else
        {
            strBuilder.append(object.toString() + "\n");
        }
        return strBuilder.toString();
    }

    /**
     * Checks if is object.
     *
     * @param obj the obj
     *
     * @return true, if is object
     */
    private static boolean isObject(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (obj instanceof String)
        {
            return false;
        }
        if (obj instanceof Integer)
        {
            return false;
        }
        if (obj instanceof Double)
        {
            return false;
        }
        if (obj instanceof Float)
        {
            return false;
        }
        if (obj instanceof Byte)
        {
            return false;
        }
        if (obj instanceof Long)
        {
            return false;
        }
        if (obj instanceof Character)
        {
            return false;
        }
        if (obj instanceof Short)
        {
            return false;
        }
        if (obj instanceof Boolean)
        {
            return false;
        }
        return true;
    }

}
