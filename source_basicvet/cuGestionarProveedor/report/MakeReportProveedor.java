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

package cuGestionarProveedor.report;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;

public class MakeReportProveedor {
	
	public static String BASE = (new java.io.File("")).getAbsolutePath(); 
	public static String PATH_SOURCE_REPORT = BASE + "/src/cuGestionarProveedor/report/xml/";
	public static String PATH_OUTPUT_REPORT = BASE + "/bin/cuGestionarProveedor/report/xml/";

	public static void make(String nameReport) {
		try {
			System.setProperty("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");
			String fileXML = PATH_SOURCE_REPORT + nameReport + ".xml";
			String fileJASPER = PATH_OUTPUT_REPORT + nameReport + ".jasper";
			String fileJRPRINT = PATH_OUTPUT_REPORT + nameReport + ".jrprint";
			JasperCompileManager.compileReportToFile(fileXML, fileJASPER);
			JasperFillManager.fillReportToFile(fileJASPER, fileJRPRINT, null,new JREmptyDataSource());			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
 }
	
	public static void main(String[] args) {
		String nameReport = "Proveedor";
		MakeReportProveedor.make(nameReport);
	}
}
