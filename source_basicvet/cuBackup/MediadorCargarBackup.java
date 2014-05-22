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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class MediadorCargarBackup {
	public static String BASE = (new File("")).getAbsolutePath();
	private String PATH_OUTPUT = BASE + "/src/imagenes/";
	private JFileChooser fileChooserOpen;
	private String ubicacionMySqlFiles;
	
	public MediadorCargarBackup(){
		ubicacionMySqlFiles = "C:\\Program Files\\MySQL\\MySQL Server 5.1\\bin\\";
		ubicacionMySqlFiles = replace(ubicacionMySqlFiles,"\\","\"\\\"").replaceFirst("\"", "");
		ubicacionMySqlFiles = (String) ubicacionMySqlFiles.subSequence(0, ubicacionMySqlFiles.length()-1);
	}
	
	public void opcionCargarBackup() {
		borrarDirLocal();
		if (fileChooserOpen == null){
			fileChooserOpen = new JFileChooser();
		}
		File pad = new File("c:\\sispac\\buckups");
	    fileChooserOpen.setCurrentDirectory(pad);
	    fileChooserOpen.setDialogTitle("RECUPERAR Buckup de Sistema de Informacion");
		//Valor que retorna al elegir una opcion en el file chooser}
		int retVal = fileChooserOpen.showOpenDialog(fileChooserOpen);
		if (retVal == JFileChooser.APPROVE_OPTION){
			//El path absoluto del archivo elegido
			boolean flagbackground=true;
			String fileZipName = fileChooserOpen.getSelectedFile().getAbsolutePath();
			try{
				final int BUFFER = 2048;
				String fileName=null;
				String fileImgName=null;
				try{
			        BufferedOutputStream dest = null;
			        FileInputStream fis = new FileInputStream(fileZipName);
			        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
			        ZipEntry entry;
			        int i= 0;
			        while((entry = zis.getNextEntry())!= null){
			            int count;			            
			            if(i==0)fileName = entry.getName();
			            else fileImgName = entry.getName();
			            byte data[] = new byte[BUFFER];
			            FileOutputStream fos = new FileOutputStream(entry.getName());
			            dest = new BufferedOutputStream(fos, BUFFER);
			            while ((count = zis.read(data, 0, BUFFER))!= -1){
			            	dest.write(data, 0, count);
			            }
			            dest.flush();
			            dest.close();
			            i++;
			        }
			        zis.close();
			    }catch(Exception e){e.printStackTrace();}
				FileInputStream inImg = new FileInputStream(fileImgName);
				FileOutputStream outImg = new FileOutputStream(PATH_OUTPUT+"imagenVeterinaria.jpg");
				
				int c;
				while( (c = inImg.read() ) != -1)
					outImg.write(c);
	 
				inImg.close();
				outImg.close();

				String command = "cmd.exe /C " +"mysql.exe --password=root --user=root veterinariadb < "+fileName;
				String commandShell=null; 
				//Recuperamos el sistema operativo 
				String osName = System.getProperty ( "os.name" );
				//Cargamos la cadena del interprete de comandos segun el sistema operativo y el comando a ejecutar 
				if( osName.equals ("Windows NT") ) 
					commandShell = "cmd.exe /C " + command;
							else if((osName.equals("Windows Vista")) || (osName.equals("Windows XP")))
									commandShell="" + command;
							   	 else if(osName.equals("Windows 7")){
							   		 commandShell=command;
							   	 }else{
								   		command="mysql --password=root --user=root veterinariadb<"+fileName;
										// En UNIX y LUNUX podemos lanzar el proceso en background sufijandolo con & 
								   		if (flagbackground)	commandShell = "" + command +" &" ; 
								   		else commandShell = "" + command ; 
							   	}
				try{
					System.out.println(commandShell);
					Runtime.getRuntime().exec (commandShell);
					JOptionPane.showMessageDialog(null, "EL BACKUP SE HA CARGADO CON EXITO \n DEBE REINICIAR LA APLICACION PARA QUE LOS CAMBIOS TENGAN EFECTOS", "BACKUP", JOptionPane.INFORMATION_MESSAGE);
				}catch (IOException eproc){
					//System.out.println ("Error to execute the command : "+eproc);
					JOptionPane.showMessageDialog(null, "ERROR AL CARGAR EL BACKUP \n"+eproc.getMessage(), "BACKUP", JOptionPane.INFORMATION_MESSAGE);
				}
			}catch (Exception ioe){ioe.printStackTrace();}
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
	
	public void borrarDirLocal() {
		File dir = new File((new File("")).getAbsolutePath()+"/src/imagenes/imgEstudios");
        File[] f = dir.listFiles();
        for(int i = 0; i < f.length; i++) {
            if(!f[i].delete()) {
                System.out.println("Error al borrar " + f[i].getName());
            }
        }
}
}