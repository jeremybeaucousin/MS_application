import javax.swing.JFileChooser;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import base.Connexion;

public class Main{
	private static int cpt = 0;
	private static ArrayList<String> VideoExtensionList = new ArrayList<String>() {{
		add("AAF"); 
		add("3GP");
		add("GIF");
		add("ASF");
		add("AVCHD");
		add("AVI");
		add("CAM");
		add("DAT");
		add("DSH");
		add("FLV");
		add("M1V");
		add("MPEG-1");
		add("M2V");
		add("MPEG-2");
		add("FLA");
		add("FLR");
		add("SOL");
		add("M4V");
		add("MKV");
		add("WRAP");
		add("MNG");
		add("MOV");
		add("QTCH");
		add("MPEG");
		add("MPEG-4");
		add("MP4");
		add("MXF");
		add("ROQ");
		add("NSV");
		add("OGG");
		add("RM");
		add("SVI");
		add("SMI");
		add("SWF");
	}}; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFileChooser chooser = new JFileChooser();
		
		chooser.setDialogTitle("Sélectionner votre dossier de vidéo");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			chooser.setCurrentDirectory(chooser.getSelectedFile());
			FolderDeepness(chooser.getCurrentDirectory());
			System.out.println(cpt);
		}
		Connexion cn=new Connexion();
		cn.connect();
		
		ResultSet rs=cn.select("SELECT * FROM nationality");
		try {
			while(rs.next())
			{
				String leType=rs.getString("nationality_wording");
				System.out.println(leType);
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cn.close();
		
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

	private static void FolderDeepness(File folderChosen) {
		File[] fileList = folderChosen.listFiles();
		for (int ii = 0; ii < fileList.length; ii++) {
			if(fileList[ii].isDirectory()) {
				if (!fileList[ii].isHidden()) {
					FolderDeepness(fileList[ii]);
				}
			} else {
				int lastPointPosition = fileList[ii].getName().lastIndexOf(".");
				if (lastPointPosition != -1) {
					if(VideoExtensionList.contains(fileList[ii].getName().substring(lastPointPosition + 1).toUpperCase())) {
						System.out.println(fileList[ii].getPath());
						cpt ++;
					}
				}
			}
		}
	}
}