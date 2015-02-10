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
	 * ��⵱�����磨WLAN��3G/2G��״̬
	 * @param context Context
	 * @return true ��ʾ�������
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null && info.isConnected()) 
			{
				// ��ǰ���������ӵ�
				if (info.getState() == NetworkInfo.State.CONNECTED) 
				{
					// ��ǰ�����ӵ��������
					return true;
				}
			}
		}
		return false;
	}
	// ����Post���󣬻����Ӧ��ѯ���
	public static JSONArray queryStringForPost(String url,
			List<BasicNameValuePair> params) {
		// ����url���HttpPost����
		HttpPost httpPost = new HttpPost(url);
		HttpClient client = new DefaultHttpClient();
		// ����ʱ
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
		// ��ȡ��ʱ
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse httpResponse = client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(httpResponse.getEntity());
				System.out.println("������:" + result);
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

	// ����Post���󣬻����Ӧ��ѯ���
	public static JSONObject queryStringForPostObject(String url,
			List<BasicNameValuePair> params) {
		// ����url���HttpPost����
		HttpPost httpPost = new HttpPost(url);
		HttpClient client = new DefaultHttpClient();
		// ����ʱ
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT,PARAMS.HTTP_REQUEST_TIMEOUT);
		// ��ȡ��ʱ
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, PARAMS.HTTP_READ_TIMEOUT);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse httpResponse = client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(httpResponse.getEntity());
				System.out.println("������:" + result);
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
