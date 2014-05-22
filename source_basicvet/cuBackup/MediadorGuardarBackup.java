/********************************************************
  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *********************************************************/

package cuBackup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import persistencia.Persistencia;
import persistencia.dominio.EstudioComplementario;
import clasesComunes.ControlEstudioComplementario;

public class MediadorGuardarBackup {
	private ControlEstudioComplementario controlEstComp;
	private JFileChooser fileChooser;
	private Persistencia persistencia;
	private String ubicacionMySqlFiles;
	
	public MediadorGuardarBackup(){
		this.persistencia = new Persistencia();
		controlEstComp = new ControlEstudioComplementario();
		ubicacionMySqlFiles = "C:\\Program Files\\MySQL\\MySQL Server 5.1\\bin\\";
		ubicacionMySqlFiles = replace(ubicacionMySqlFiles,"\\","\"\\\"").replaceFirst("\"", "");
		ubicacionMySqlFiles = (String) ubicacionMySqlFiles.subSequence(0, ubicacionMySqlFiles.length()-1);
	}
	
	@SuppressWarnings("static-access")
	public void opcionGuardarBackup() {
		if (fileChooser == null){
			fileChooser = new JFileChooser();
		}
		Calendar c1 = Calendar.getInstance();
		String dia = Integer.toString(c1.get(Calendar.DATE));
		int me = (c1.get(Calendar.MONTH))+1;
		String mes = Integer.toString(me);
		String anio = Integer.toString(c1.get(Calendar.YEAR));
		String nombreArchivo = "registro"+"-"+dia+"-"+mes+"-"+anio+".zip";
		File actual = new File(nombreArchivo);
		File pad = new File("c:\\sispac\\buckups");
	    fileChooser.setCurrentDirectory(pad);
	    fileChooser.setSelectedFile(actual);
	    fileChooser.setDialogTitle("Guardar Buckup de Sistema de Informacion");
	    int retVal = fileChooser.showSaveDialog(fileChooser);
		if (retVal == fileChooser.APPROVE_OPTION){
			String fileNameImg = (new java.io.File("")).getAbsolutePath()+"/src/imagenes/imagenVeterinaria.jpg";
			String fileZipName = fileChooser.getSelectedFile().getAbsolutePath();
			String fileName = (new java.io.File("")).getAbsolutePath()+"/registro"+"-"+dia+"-"+mes+"-"+anio+".sql";
			File backupFile = new File(fileName);
			try {
				FileWriter fw = new FileWriter(backupFile);
				boolean flagbackground=true;
				String osName = System.getProperty ( "os.name" );
				String command = "mysqldump -uroot -proot veterinariadb";
				String commandShell=null;
				if ( osName.equals ("Windows NT")) 
					commandShell = "cmd.exe /C " + command; 
						else if((osName.equals("Windows Vista")) || (osName.equals("Windows XP")) )
								commandShell="" + command;
								else if(osName.equals("Windows 7")){
									commandShell = command;
								}else{
							   		command="mysqldump --opt --password=root --user=root veterinariadb >"+fileName;
							   		if (flagbackground)	commandShell = "" + command +" &" ; 
							   		else commandShell = "" + command ; 
						   		}
				Process child = Runtime.getRuntime ().exec (commandShell);
				InputStreamReader irs = new InputStreamReader(child.getInputStream());
				BufferedReader br = new BufferedReader(irs);	        
				String line;
				while((line=br.readLine()) != null){
					fw.write(line + "\n");
				}
				irs.close();
				fw.close();
				br.close();

				FileInputStream in = new FileInputStream(fileName);
				FileInputStream inImg = new FileInputStream(fileNameImg);
				FileOutputStream out = new FileOutputStream(fileZipName);
				
				byte b[] = new byte[2048];
				ZipOutputStream zipOut = new ZipOutputStream(out);
				ZipEntry entry = new ZipEntry("C:\\registro"+"-"+dia+"-"+mes+"-"+anio+".sql");
				ZipEntry entryImg = new ZipEntry("C:\\imagenVeterinaria.jpg");
				zipOut.putNextEntry(entry);
				int len = 0;
				while ((len = in.read(b)) != -1) {
					zipOut.write(b, 0, len);
				}
				zipOut.putNextEntry(entryImg);
				while ((len = inImg.read(b)) != -1) {
					zipOut.write(b, 0, len);
				}
				//ciclo para guardar las imagenes est comp.
				//-----------------------------------------------------
				FileInputStream inEst = new FileInputStream(fileNameImg);
				ZipEntry entryEst;
				persistencia.iniciarTransaccion();
				Iterator<EstudioComplementario> iterEstComp = controlEstComp.obtenerEstudiosComplementarios();
				EstudioComplementario estComp;
				while(iterEstComp.hasNext()){
					estComp = iterEstComp.next();
					inEst = new FileInputStream(estComp.getPath());
					entryEst = new ZipEntry(estComp.getPath());
					zipOut.putNextEntry(entryEst);
					len = 0;
					while((len = inEst.read(b)) != -1){
						zipOut.write(b,0,len);
					}
				}
				//---------------------------------------------------
				zipOut.closeEntry();
				zipOut.close();
				in.close();
				inImg.close();
				br.close();
				backupFile.delete();
				JOptionPane.showMessageDialog(null, "EL BACKUP SE HA CREADO CON EXITO", "BACKUP", JOptionPane.INFORMATION_MESSAGE);
			}catch (IOException ex){
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR EL BACKUP \n"+ex.getMessage(), "BACKUP", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private static String replace(String str, String patron, String reemplazo) {
	    int s = 0;
	    int e = 0;
	    StringBuffer result = new StringBuffer();

	    while ((e = str.indexOf(patron, s)) >= 0) {
	        result.append(str.substring(s, e));
	        result.append(reemplazo);
	        s = e+patron.length();
	    }
	    result.append(str.substring(s));
	    return result.toString();
	}
}