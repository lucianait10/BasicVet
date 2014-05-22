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

package cuGestionarCliente.report;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import cuGestionarCliente.GUIGestionarCliente;

@SuppressWarnings({ "unchecked"})
public class ReporteCliente {
	public GUIGestionarCliente framePrincipal;

	public ReporteCliente(GUIGestionarCliente gesCliente) {
		this.framePrincipal = gesCliente;
	}

	public void viewReport(Object[][] param, String[] fieldXml, String nameReport, Object[][]values) {
		try {
			HashMap parameters = new HashMap(); 
			for (int i = 0; i <param.length; i++) {
				parameters.put(param[i][0], param[i][1]);
			}
			Hashtable ht = new Hashtable();
			for (int i = 0; i < fieldXml.length; i++) {
				ht.put(fieldXml[i], new Integer(i));
			}
			DataSourceJasper data = new DataSourceJasper(values, ht);
			String fileNameReport = MakeReportCliente.PATH_OUTPUT_REPORT + nameReport + ".jasper";
			JasperPrint jasperprint= JasperFillManager.fillReport(fileNameReport,parameters,data);
			JasperViewer visor=new JasperViewer(jasperprint,false);
			visor.setTitle("Lista Clientes");
			visor.setSize(800, 600);
			visor.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){framePrincipal.setVisible(true);}            	}
			);
			visor.setVisible(true);
		} catch (JRException exc) {
			JOptionPane.showMessageDialog(null, "ERROR REPORTE \n"+exc.getMessage(), "BACKUP", JOptionPane.INFORMATION_MESSAGE);
			//exc.printStackTrace(System.out);
		}
	}

	public void viewFicha(Object[][] clientes, String nombre, String direccion, String telefono, String cuil) {
		try{
			Object[][] parametros = {{"direccionVet", direccion},{"telefonoVet", telefono},{"nombreVet",nombre},{"cuil",cuil}};
			String[] filedXml = {"dni","nombre","apellido", "telefono"};
			String nameReport = "Cliente";
			this.viewReport(parametros, filedXml, nameReport, clientes);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "ERROR REPORTE 2 \n"+e.getMessage(), "BACKUP", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
}


