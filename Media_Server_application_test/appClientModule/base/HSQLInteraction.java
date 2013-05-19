package base;

import java.awt.FlowLayout;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import org.apache.commons.io.IOUtils;
import org.hsqldb.server.Server;

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
		this.getClass().forName("org.hsqldb.jdbcDriver").newInstance();
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
			statement.executeUpdate("CREATE SCHEMA MEDIA_SERVER");
			statement.execute(IOUtils.toString(ClassLoader.getSystemResource("script/script_sql.sql").openStream()));
		}
	}
	
	public Statement getStatement() {
		return this.statement;
	}

}
