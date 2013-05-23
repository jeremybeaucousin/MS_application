package util;

import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

public class JSONObject extends org.json.JSONObject{
	
	// Constructors //

	public JSONObject() {
		super();
	}

	public JSONObject(org.json.JSONObject jo, String[] names) {
		super(jo, names);
	}

	public JSONObject(JSONTokener x) throws JSONException {
		super(x);
	}

	public JSONObject(Map map) {
		super(map);
	}

	public JSONObject(Object object, String[] names) {
		super(object, names);
	}

	public JSONObject(Object bean) {
		super(bean);
	}

	public JSONObject(String baseName, Locale locale) throws JSONException {
		super(baseName, locale);
	}

	public JSONObject(String source) throws JSONException {
		super(source);
	}
	
	//getters and setters //
	
	// tests Méthode //
	
	private static Boolean isJSONObject(Object jsonObject) {
		return jsonObject instanceof org.json.JSONObject;
	}
	
	private static Boolean isJSONArray(Object jsonArray) {
		return jsonArray instanceof JSONArray;
	}
	
	// Usefulls Méthods //
	
	public static String[] jsonArrayToArray(Object json) throws JSONException {
		String[] array = null;
		if(isJSONArray(json)) {
			JSONArray jsonArray = (JSONArray) json;
			array = new String[jsonArray.length()];
			for(int ii = 0; ii < jsonArray.length(); ii++) {
				array[ii] = jsonArray.getString(ii);
			}
		}
		return array;
	}
	
	
	public Hashtable<String, Object> jsonToHachtable() throws JSONException {
		Hashtable<String, Object> ObjectMap = new Hashtable<String, Object>();
		if(this != null) {
			JSONArray keyset = this.names();
			for(int ii = 0; ii < keyset.length(); ii++) {
				if(keyset.get(ii) instanceof String) {
					String key = keyset.getString(ii);
					Object value = this.get(key);
					if(value == JSONObject.NULL) {
						ObjectMap.put(key, ConstantString.EMPTY);
					} else if(isJSONObject(value)) {
						ObjectMap.put(key, new util.JSONObject(this.getJSONObject(key), jsonArrayToArray(this.getJSONObject(key).names())).jsonToHachtable());
					} else if(value instanceof String) {
						ObjectMap.put(key, this.getString(key));
					} else if(value instanceof Integer) {
						ObjectMap.put(key, this.getInt(key));
					} else if(value instanceof Double) {
						ObjectMap.put(key, this.getDouble(key));
					} else if(value instanceof Boolean) {
						ObjectMap.put(key, this.getBoolean(key));
					} else if(isJSONArray(value)) {
						JSONArray jsonArray = this.getJSONArray(key);
						Object[] objectTable = new Object[jsonArray.length()];
						for(int jj = 0; jj < jsonArray.length(); jj++) {
							objectTable[jj] = new JSONObject(jsonArray.getJSONObject(jj), jsonArrayToArray(jsonArray.getJSONObject(jj).names())).jsonToHachtable();
						}
						ObjectMap.put(key, objectTable);
					}
				}
			}
		}
		return ObjectMap;
	}
	
	public static void jsonBrowser(Object json) throws JSONException {
		if(isJSONArray(json)) {
			JSONArray jsonArray = (JSONArray) json;
			for(int ii = 0; ii < jsonArray.length(); ii++) {
				Object object = jsonArray.get(ii);
				if(isJSONObject(object)) {
					jsonBrowser(object);
				} else {
					System.out.println(object);
				}
			}
		} else if (isJSONObject(json)) {
			org.json.JSONObject jsonObject = (org.json.JSONObject) json;
			JSONArray jsonObjectKeys = jsonObject.names();
			for(int ii = 0; ii < jsonObjectKeys.length(); ii++) {
				Object object = jsonObject.get(jsonObjectKeys.getString(ii));
				if(isJSONObject(object) || isJSONArray(object)) {
					jsonBrowser(object);
				} else {
					System.out.println(object);
				}
			}
		} else {
			System.out.println(json);
		}
	}
	
}