package com.zero2ipo.weixin.utils;

import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

         public class CommonUtil {

         public static JSONObject httpsRequestToJsonObject(String requestUrl, String requestMethod, String outputStr) {
                 JSONObject jsonObject = null;
                 try {
                         StringBuffer buffer = httpsRequest(requestUrl, requestMethod, outputStr);
                        jsonObject = JSONObject.fromObject(buffer.toString());
                     } catch (Exception e) {
                     }
                 return jsonObject;
             }

                private static StringBuffer httpsRequest(String requestUrl, String requestMethod, String output)
                 throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException, MalformedURLException,
                        IOException, ProtocolException, UnsupportedEncodingException {

                URL url = new URL(requestUrl);
               HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

               connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setUseCaches(false);
                connection.setRequestMethod(requestMethod);
                 if (null != output) {
                         OutputStream outputStream = connection.getOutputStream();
                         outputStream.write(output.getBytes("UTF-8"));
                        outputStream.close();
                    }

               // 从输入流读取返回内容
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                 String str = null;
                 StringBuffer buffer = new StringBuffer();
                 while ((str = bufferedReader.readLine()) != null) {
                         buffer.append(str);
                     }

                 bufferedReader.close();
                 inputStreamReader.close();
                 inputStream.close();
                 inputStream = null;
                connection.disconnect();
                 return buffer;
             }
 }
