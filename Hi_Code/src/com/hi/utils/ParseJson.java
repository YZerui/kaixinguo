package com.hi.utils;

import java.io.IOException;
import java.util.ArrayList;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hi.common.PARAMS;
import com.hi.dao.model.T_MsgCache;
import com.hi.module.locale.model.RecvLocalUserItemBean;
import com.hi.module.locale.model.RecvPhotoUrlBean;
import com.hi.module.msg.model.RecvMsgCacheSeqBean;
import com.hi.module.register_login.model.ReqThirdLoginBean;
import com.hi.module.store.model.RecvMyPrivilegeBean;
import com.hi.module.store.model.RecvPrivilegeBean;
import com.hi.module.store.model.RecvShopBean;
import com.hi.module.store.model.RecvStoreDetailBean;

/**
 * @author MM_Zerui 该工具类实现对JSON数据的解析
 */
public class ParseJson {
	/**
	 * @param array
	 * @return 解析请求的正确性
	 */
	public static boolean parseJsonState(Object object) {
		try {
			JSONObject jsonObject = new JSONObject();
			if (object instanceof String) {
				String jsonStr = (String) object;
				jsonObject = new JSONObject(jsonStr);
			} else if (object instanceof JSONObject) {
				jsonObject = (JSONObject) object;
			} else {
				return false;
			}
			// JSONObject object=(JSONObject) array.get(1);
			String state = jsonObject.getString("success");
			if (state != null && state.equals("true")) {
				return true;
			}
			return false;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @param object
	 * @return Object
	 */
	public static Object parseResultData(Object object) {
		if (object != null) {
			try {
				JSONObject jsonObject = new JSONObject();
				if (object instanceof String) {
					String jsonStr = (String) object;
					jsonObject = new JSONObject(jsonStr);
				} else if (object instanceof JSONObject) {
					jsonObject = (JSONObject) object;
				} else {
					return null;
				}
				return jsonObject.get("data");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}

	/**
	 * @param object
	 * @return 解析JsonObject
	 */
	public static <T> T parseResultJson(Object object, Class<T> beanClass) {
		if (object != null) {
			try {
				JSONObject jsonObject = new JSONObject();
				if (object instanceof String) {
					String jsonStr = (String) object;
					jsonObject = new JSONObject(jsonStr);
				} else if (object instanceof JSONObject) {
					jsonObject = (JSONObject) object;
				} else {
					return null;
				}
				// String arrayStr=object.get("data").toString();
				JSONObject jsonData = jsonObject.getJSONObject("data");
				ObjectMapper objectMapper = new ObjectMapper();
				if (jsonData != null) {
					try {
						// objectMapper.configure(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
						// true);
						T bean = (T) objectMapper.readValue(
								jsonData.toString(), beanClass);
						return bean;
					} catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @param object
	 * @return 解析JsonObject
	 */
	public static <T> T parseJsonToBean(Object object, Class<T> beanClass) {
		if (object != null) {
			try {
				JSONObject jsonData = new JSONObject();
				if (object instanceof String) {
					String jsonStr = (String) object;
					jsonData = new JSONObject(jsonStr);
				} else if (object instanceof JSONObject) {
					jsonData = (JSONObject) object;
				} else {
					return null;
				}
				ObjectMapper objectMapper = new ObjectMapper();
				if (jsonData != null) {
					try {
						// objectMapper.configure(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
						// true);
						T bean = (T) objectMapper.readValue(
								jsonData.toString(), beanClass);
						return bean;
					} catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @param object
	 * @return 解析JsonArray的信息
	 */
	public static <T> ArrayList<T> parseLocalJsonArray(Object object,
			Class<T> beanCalss) {
		if (object != null) {
			try {
				JSONObject jsonObject = new JSONObject();
				if (object instanceof String) {
					String jsonStr = (String) object;
					jsonObject = new JSONObject(jsonStr);
				} else if (object instanceof JSONObject) {
					jsonObject = (JSONObject) object;
				} else {
					return null;
				}
				// String arrayStr=object.get("data").toString();
				JSONArray jsonArray = jsonObject.getJSONArray("data");

				if (jsonArray != null) {
					ObjectMapper objectMapper = new ObjectMapper();
					try {
						ArrayList<T> listBean = new ArrayList<T>();
						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject json = (JSONObject) jsonArray.get(i);
							T bean = (T) objectMapper.readValue(
									json.toString(), beanCalss);
							// listBean.getLocalUserItemBean().add(bean);
							listBean.add(bean);
						}
						return listBean;
					} catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("json解析错误");
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("json解析类型不匹配");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("输入输出操作错误");
					}

				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;

	}

	/**
	 * @param jsonStr
	 * @return 解析QQ授权信息（获得用户唯一标识）
	 */
	public static ReqThirdLoginBean parseQQAuthorJson(String jsonStr) {
		try {
			JSONObject jsonObject = new JSONObject(jsonStr);
			ReqThirdLoginBean bean = new ReqThirdLoginBean();
			if (jsonStr != null) {
				bean.setOpenid(jsonObject.getString("openid"));
				// bean.setUserThirdId(jsonObject.getString("openid"));
				return bean;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @param bean
	 * @param jsonStr
	 * @return 解析QQ用户信息
	 */
	public static ReqThirdLoginBean parseQQUserJson(ReqThirdLoginBean bean,
			String jsonStr) {
		try {
			JSONObject jsonObject = new JSONObject(jsonStr);
			if (jsonObject != null) {
				if (jsonObject.getString("gender").equals("男")) {
					bean.setGender(PARAMS.MAN);
				} else {
					bean.setGender(PARAMS.WOMEN);
				}
				bean.setFigureurl(jsonObject.getString("figureurl_2"));
				bean.setType(PARAMS.THIRD_LOGIN_QQ);
				bean.setNickname(jsonObject.getString("nickname"));
				return bean;
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param object
	 * @return 解析现场用户的信息
	 */
	public static ArrayList<RecvLocalUserItemBean> parseLocalJson(
			JSONObject object) {
		if (object != null) {
			try {
				// String arrayStr=object.get("data").toString();
				JSONArray jsonArray = object.getJSONArray("data");
				if (jsonArray != null) {
					ArrayList<RecvLocalUserItemBean> list = new ArrayList<RecvLocalUserItemBean>();
					for (int i = 0; i < jsonArray.length(); i++) {
						RecvLocalUserItemBean bean = new RecvLocalUserItemBean();
						JSONObject jsonObject = (JSONObject) jsonArray.get(i);
						bean.setIconUrl(jsonObject.getString("head"));
						bean.setMid(jsonObject.getString("mid"));
						bean.setNickName(jsonObject.getString("nickName"));
						list.add(bean);
					}
					return list;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public static ArrayList<RecvPhotoUrlBean> getPhotoList(String url[]) {
		String photosUrl[] = url;
		if (photosUrl == null) {
			return null;
		}
		int length = photosUrl.length;
		ArrayList<RecvPhotoUrlBean> beanList = new ArrayList<RecvPhotoUrlBean>();
		for (int i = 0; i < (length / 8); i++) {
			RecvPhotoUrlBean bean = new RecvPhotoUrlBean();
			bean.setUrl(photosUrl[4 + (i * 8)]);
			bean.setLocation(photosUrl[2 + (i * 8)]);
			bean.setTime(photosUrl[6 + (i * 8)]);
			beanList.add(bean);
		}
		return beanList;
	}

	// /**
	// * @param object
	// * @return 解析消息缓存信息(对话）
	// */
	// public static ArrayList<T_MsgCache> parseMsgCacheJson(Object object) {
	// if (object != null) {
	// try {
	// JSONObject jsonObject = new JSONObject();
	// if (object instanceof String) {
	// String jsonStr = (String) object;
	// jsonObject = new JSONObject(jsonStr);
	// } else if (object instanceof JSONObject) {
	// jsonObject = (JSONObject) object;
	// } else {
	// return null;
	// }
	// // String arrayStr=object.get("data").toString();
	// JSONArray jsonArray = jsonObject.getJSONArray("data");
	// if (jsonArray != null) {
	// ArrayList<T_MsgCache> list = new ArrayList<T_MsgCache>();
	// for (int i = 0; i < jsonArray.length(); i++) {
	//
	// JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
	// String nickName = jsonObject2.getString("nickname");
	// // 对对话内容进行解析
	// String sms = jsonObject2.getString("sms");
	// JSONArray array = new JSONArray(sms);
	// for (int k = 0; k < array.length(); k++) {
	// T_MsgCache bean = new T_MsgCache();
	// JSONObject jsonObject3 = (JSONObject) array.get(k);
	// bean.setHead(jsonObject3.getString("head"));
	// bean.setMsg(jsonObject3.getString("msg"));
	// bean.setNickname(nickName);
	// bean.setRecordTime(jsonObject3.getString("time"));
	// bean.setUid(jsonObject3.getString("sendid"));
	// list.add(bean);
	// }
	// }
	// return list;
	// }
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// return null;
	// }

	/**
	 * 解析店家优惠劵
	 * 
	 * @param object
	 * @return
	 */
	public static ArrayList<RecvPrivilegeBean> parseStorePrivilegeJson(
			Object object) {
		if (object != null) {
			try {
				JSONObject jsonObject = new JSONObject();
				if (object instanceof String) {
					String jsonStr = (String) object;
					jsonObject = new JSONObject(jsonStr);
				} else if (object instanceof JSONObject) {
					jsonObject = (JSONObject) object;
				} else {
					return null;
				}
				JSONArray jsonArray = jsonObject.getJSONArray("data");
				if (jsonArray != null) {
					ArrayList<RecvPrivilegeBean> list = new ArrayList<RecvPrivilegeBean>();
					for (int i = 0; i < jsonArray.length(); i++) {

						JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
						RecvPrivilegeBean bean = new RecvPrivilegeBean();
						bean.setName(jsonObject2.getString("Name"));
						bean.setForwardEffe(jsonObject2
								.getString("ForwardEffe"));
						bean.setPhone(jsonObject2.getString("Phone"));
						bean.setLogo(jsonObject2.getString("Logo"));
						bean.setAddress(jsonObject2.getString("Address"));
						bean.setId(jsonObject2.getString("ID"));
						bean.setAloneeffe(jsonObject2.getString("AloneEffe"));
						bean.setImage(jsonObject2.getString("image"));
						bean.setContent(jsonObject2.getString("Content"));
						bean.setTotal(jsonObject2.getString("Total"));
						bean.setTitle(jsonObject2.getString("Title"));
						bean.setCoordinates(jsonObject2
								.getString("Coordinates"));
						list.add(bean);
					}
					return list;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 解析本人拥有优惠劵列表
	 * 
	 * @param object
	 * @return
	 */
	public static ArrayList<RecvMyPrivilegeBean> parseMyPrivilegeJson(
			Object object) {
		if (object != null) {
			try {
				// String arrayStr=object.get("data").toString();
				JSONObject jsonObject = new JSONObject();
				if (object instanceof String) {
					String jsonStr = (String) object;
					jsonObject = new JSONObject(jsonStr);
				} else if (object instanceof JSONObject) {
					jsonObject = (JSONObject) object;
				} else {
					return null;
				}
				JSONArray jsonArray = jsonObject.getJSONArray("data");
				if (jsonArray != null) {
					ArrayList<RecvMyPrivilegeBean> list = new ArrayList<RecvMyPrivilegeBean>();
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
						RecvMyPrivilegeBean bean = new RecvMyPrivilegeBean();
						bean.setCID(jsonObject2.getString("cid"));
						bean.setID(jsonObject2.getString("id"));
						bean.setMID(jsonObject2.getString("mid"));
						bean.setUseEffe(jsonObject2.getString("useEffe"));
						bean.setUseTime(jsonObject2.getString("useTime"));
						list.add(bean);
					}
					return list;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	// /**
	// * @param object
	// * @return 解析消息缓存顺序对话
	// */
	// public static ArrayList<T_MsgGroupCache> parseMsgCacheSeqJson(
	// Object object) {
	// if (object != null) {
	// try {
	// JSONObject jsonObject = new JSONObject();
	// if (object instanceof String) {
	// String jsonStr = (String) object;
	// jsonObject = new JSONObject(jsonStr);
	// } else if (object instanceof JSONObject) {
	// jsonObject = (JSONObject) object;
	// } else {
	// return null;
	// }
	// JSONArray jsonArray = jsonObject.getJSONArray("data");
	// if (jsonArray != null) {
	// ArrayList<T_MsgGroupCache> list = new ArrayList<T_MsgGroupCache>();
	// for (int i = 0; i < jsonArray.length(); i++) {
	// T_MsgGroupCache bean = new T_MsgGroupCache();
	// JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
	// bean.setHead(jsonObject2.getString("head"));
	// bean.setNickname(jsonObject2.getString("nickname"));
	// bean.setRecordtime(jsonObject2.getString("time"));
	// bean.setUid(jsonObject2.getString("uid"));
	// list.add(bean);
	// }
	// return list;
	// }
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// return null;
	// }

	/**
	 * 解析店家详细地址
	 * 
	 * @param object
	 * @return
	 */
	public static RecvStoreDetailBean parseStoreDetailJson(String objectStr) {
		JSONObject object;
		try {
			object = new JSONObject(objectStr);
			if (object != null) {

				try {
					JSONObject jsonObject = object.getJSONObject("data");
					if (jsonObject != null) {
						RecvStoreDetailBean bean = new RecvStoreDetailBean();
						bean.setName(jsonObject.getString("name"));
						bean.setPhone(jsonObject.getString("phone"));
						bean.setAddress(jsonObject.getString("address"));
						bean.setPhotos(parseStoreDetailImgWall(jsonObject
								.getJSONArray("photos")));

						return bean;
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	/**
	 * 解析店家照片墙
	 * 
	 * @param array
	 * @return
	 */
	public static ArrayList<String> parseStoreDetailImgWall(JSONArray array) {
		if (array != null) {
			ArrayList<String> imgWalls = new ArrayList<String>();
			for (int i = 0; i < array.length(); i++) {
				try {
					JSONObject jsonObject = (JSONObject) array.get(i);
					String img = jsonObject.getString("url");
					imgWalls.add(img);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// return null;
				} catch (Exception e) {
					// TODO: handle exception

				}
			}
			return imgWalls;
		}
		return null;
	}

}
