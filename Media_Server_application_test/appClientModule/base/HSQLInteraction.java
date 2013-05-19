package base;

import java.awt.FlowLayout;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import org.apache.commons.io.IOUtils;
import org.hsqldb.server.Server;

import com.mysql.jdbc.StringUtils;

final public class HSQLInteraction {
	Server hsqlServer;
	Connection connexion;
	Statement statement;
	public HSQLInteraction() {
		this.hsqlServer = new Server();
		this.hsqlServer.setLogWriter(null);
		this.hsqlServer.setSilent(true);
		this.hsqlServer.setDatabaseName(0, "media_server");
		this.hsqlServer.setDatabasePath(0, "file: media_server_database/media_server");
	}

	public void start() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		this.hsqlServer.start();
		this.getClass();
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		this.connexion = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/media_server", "sa",  "");
		this.statement = this.connexion.createStatement();
		this.buildDatabase();
	}
	
	public void stop() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		this.hsqlServer.stop();
	}
	
	private void buildDatabase() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		
		ResultSet mediaServerSchema = statement.executeQuery("SELECT count(*) FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'MEDIA_SERVER';");
		mediaServerSchema.next();
		if(mediaServerSchema.getInt(1) == 0) {
			statement.executeUpdate("CREATE SCHEMA MEDIA_SERVER;");
			statement.execute(IOUtils.toString(ClassLoader.getSystemResource("script/table_creation_script.sql").openStream()));
			statement.execute(IOUtils.toString(ClassLoader.getSystemResource("script/data_insertion_script.sql").openStream()));
		}
	}
	
	public Statement getStatement() {
		return this.statement;
	}
	
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
