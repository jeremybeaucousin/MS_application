package model.base;

import java.util.Hashtable;

import com.mysql.jdbc.StringUtils;

public abstract class DatabaseActionsImplemented {
	public static String insert(String tableName, Hashtable<String, String> fieldsWithValues) {
		StringBuffer request = new StringBuffer();
		if(!StringUtils.isEmptyOrWhitespaceOnly(tableName) && !fieldsWithValues.isEmpty()) {
			request.append("INSERT INTO " + tableName + " (");
			String[] fieldsTable = fieldsWithValues.keySet().toArray(new String[fieldsWithValues.size()]);
			for(int ii = 0 ;ii < fieldsTable.length; ii++) {
				if(ii == (fieldsTable.length -1)) {
					request.append(fieldsTable[ii] + ") VALUES (");
				} else {
					request.append(fieldsTable[ii] + ", ");
				}

			}
			
			for(int ii = 0 ;ii < fieldsTable.length; ii++) {
				if(ii == (fieldsTable.length -1)) {
					request.append(fieldsWithValues.get(fieldsTable[ii]) + ")");
				} else {
					request.append(fieldsWithValues.get(fieldsTable[ii]) + ", ");
				}
			}
			
			request.append(";");
		}
		
		return request.toString();
	}
}
