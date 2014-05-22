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

package cuGestionarCartilla.report;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Hashtable;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import cuGestionarCartilla.GUIGestionarCartilla;


public class ReporteCartilla {
	public GUIGestionarCartilla framePrincipal;
	
	public ReporteCartilla(GUIGestionarCartilla gesCartilla) {
		this.framePrincipal=gesCartilla;
	}
	@SuppressWarnings({ "unchecked"})
	public void viewReport(Object[][] param, String[] fieldXml, String nameReport, Object[][]values) {
		try {
			HashMap parametros = new HashMap(); 
			for (int i = 0; i <param.length; i++) {
				parametros.put(param[i][0], param[i][1]);
			}
			Hashtable ht = new Hashtable();
			for (int i = 0; i < fieldXml.length; i++){
				ht.put(fieldXml[i], new Integer(i));
			}
			DataSourceJasper data = new DataSourceJasper(values, ht);
			String fileNameReport = MakeReportCartilla.PATH_OUTPUT_REPORT + nameReport + ".jasper";
			JasperPrint jasperprint= JasperFillManager.fillReport(fileNameReport,parametros,data);
			JasperViewer visor=new JasperViewer(jasperprint,false);
            visor.setTitle("Cartilla");
            visor.setSize(800,600);
            visor.setLocationRelativeTo(null);
            visor.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){framePrincipal.setVisible(true);}            	}
            						);
            visor.setVisible(true);
		}catch(JRException exc){
			exc.printStackTrace(System.out);
		}
	}
	public void viewFicha(Object[][] dosis,String tipo, String nombre, String direccion, String telefono, String cuil){
		try{
			Object[][] parametros = {{"tipo",tipo},{"direccionVet", direccion},{"telefonoVet", telefono},{"nombreVet",nombre},{"cuil",cuil}};
			String[] filedXml = {"idV","fechaV","fechaProxV","nombreV","viaV"};
			String nameReport = "Cartilla";
			this.viewReport(parametros, filedXml, nameReport, dosis);
		}catch(Exception e){e.printStackTrace();}
	}
}
