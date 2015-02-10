package com.hi.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.hi.common.PARAMS;
import com.lidroid.xutils.http.RequestParams;

public class HttpUtils {
	/**
	 * 检测当的网络（WLAN、3G/2G）状态
	 * @param context Context
	 * @return true 表示网络可用
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null && info.isConnected()) 
			{
				// 当前网络是连接的
				if (info.getState() == NetworkInfo.State.CONNECTED) 
				{
					// 当前所连接的网络可用
					return true;
				}
			}
		}
		return false;
	}
	// 发送Post请求，获得响应查询结果
	public static JSONArray queryStringForPost(String url,
			List<BasicNameValuePair> params) {
		// 根据url获得HttpPost对象
		HttpPost httpPost = new HttpPost(url);
		HttpClient client = new DefaultHttpClient();
		// 请求超时
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
		// 读取超时
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse httpResponse = client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(httpResponse.getEntity());
				System.out.println("请求结果:" + result);
				JSONArray jsonArray = new JSONArray(result);
				return jsonArray;

			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 发送Post请求，获得响应查询结果
	public static JSONObject queryStringForPostObject(String url,
			List<BasicNameValuePair> params) {
		// 根据url获得HttpPost对象
		HttpPost httpPost = new HttpPost(url);
		HttpClient client = new DefaultHttpClient();
		// 请求超时
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT,PARAMS.HTTP_REQUEST_TIMEOUT);
		// 读取超时
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, PARAMS.HTTP_READ_TIMEOUT);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse httpResponse = client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(httpResponse.getEntity());
				System.out.println("请求结果:" + result);
				JSONObject jsonObject = new JSONObject(result);
				return jsonObject;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, String> convertBeanToMap(Object bean)
			throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = bean.getClass().getDeclaredFields();
		HashMap<String, String> data = new HashMap<String, String>();
		for (Field field : fields) {
			field.setAccessible(true);
			data.put(field.getName(), (String) field.get(bean));
		}
		return data;
	}
	
	public static RequestParams convertBeanToParams(Object bean){
		HashMap<String, String> map;
		try {
			map = (HashMap<String, String>) convertBeanToMap(bean);
			RequestParams params=new RequestParams();
			if (map!= null) {
				Set<String> keys = map.keySet();
				for (Iterator<String> i = keys.iterator(); i.hasNext();) {
					String key = (String) i.next();
					params.addBodyParameter(key, map.get(key));
				}
			}
			return params;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static JSONObject httpPostTools(String url,Object bean) {
		HashMap<String, String> params;
		try {
			params = (HashMap<String, String>) convertBeanToMap(bean);
			List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
			if (params != null) {
				Set<String> keys = params.keySet();
				for (Iterator<String> i = keys.iterator(); i.hasNext();) {
					String key = (String) i.next();
					pairs.add(new BasicNameValuePair(key, params.get(key)));
				}
			}
			return queryStringForPostObject(url,pairs);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
