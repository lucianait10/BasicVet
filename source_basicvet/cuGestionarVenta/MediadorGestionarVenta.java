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

package cuGestionarVenta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import mediador.MediadorGeneral;
import persistencia.Persistencia;
import persistencia.dominio.BasicVet;
import persistencia.dominio.Venta;
import clasesComunes.ControlBasicVet;
import cuGestionarVenta.report.ReporteVenta;

@SuppressWarnings("deprecation")
public class MediadorGestionarVenta extends MediadorGeneral implements ActionListener {

	private GUIGestionarVenta guiGestionarVenta;
	private Persistencia persistencia= new Persistencia() ;
	private ControlVenta controlVenta;
	private DefaultTableModel modelo = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable (int row, int column){return false;}
	};
	private ControlBasicVet controlBasicVet;
	
	
	public MediadorGestionarVenta(){
		super();
		this.inicializar();	
	}
	
	public void inicializar(){
		this.controlBasicVet = new ControlBasicVet();
		this.controlVenta= new ControlVenta();
		this.modelo.setColumnIdentifiers(new String[]{"ID","FECHA","CLIENTE","PRODUCTO","CANTIDAD","P. UNITARIO","TOTAL"});
		this.guiGestionarVenta = new GUIGestionarVenta(this.modelo);
		this.guiGestionarVenta.getBotonModificar().setEnabled(false);
		this.guiGestionarVenta.getBotonEliminar().setEnabled(false);
		this.guiGestionarVenta.getBotonReporte().setEnabled(false);
		try{
			persistencia.iniciarTransaccion();
			Iterator<Venta> iterVenta =controlVenta.obtenerVentas();
			Venta venta;
			while(iterVenta.hasNext()){
				venta = iterVenta.next();
				String fecha=venta.getFechaVenta().getDate()+"/"+(venta.getFechaVenta().getMonth()+1)+"/"+(venta.getFechaVenta().getYear()+1900);
				this.modelo.addRow(new String[]{venta.getId()+"",fecha,venta.getCliente().getNombre().toUpperCase(),venta.getProductoVenta().getNombre().toUpperCase(),Integer.toString(venta.getCantidadVenta()),Float.toString(venta.getPrecioUnitario()),Float.toString(venta.getTotalVenta())});
			}
			if(this.modelo.getRowCount()>0){
				this.guiGestionarVenta.getBotonModificar().setEnabled(true);
				this.guiGestionarVenta.getBotonEliminar().setEnabled(true);
				this.guiGestionarVenta.getBotonReporte().setEnabled(true);
			}
			persistencia.concretarTransaccion();
			this.guiGestionarVenta.setListenerButtons(this);
			this.guiGestionarVenta.setListenerMouse(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2)modificarVenta();}
			});
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	public void mostrar(){
		this.guiGestionarVenta.show();
	}
	
	private void agregarVenta() {
		MediadorAltaVenta medAltaVenta = new MediadorAltaVenta(this.modelo);
		medAltaVenta.mostrar();
		if(this.modelo.getRowCount()>0){
			this.guiGestionarVenta.getBotonModificar().setEnabled(true);
			this.guiGestionarVenta.getBotonEliminar().setEnabled(true);
			this.guiGestionarVenta.getBotonReporte().setEnabled(true);
		}
		this.modelo.fireTableDataChanged();
	}
	
		
	
	private void modificarVenta(){
			MediadorModificarVenta medModificar = new MediadorModificarVenta(modelo,guiGestionarVenta);
			medModificar.mostrar();
	}
	
	private void eliminarVenta(){
		JTable jt = this.guiGestionarVenta.getTablaVentas();
		int fila = jt.getSelectedRow();
		int idVenta=-1;
		try{
			if(fila>=0){
				int filaReal = this.guiGestionarVenta.getTableRowSorter().convertRowIndexToModel(fila);
				idVenta = Integer.parseInt((String) this.modelo.getValueAt(filaReal, 0));
				int opcion = JOptionPane.showConfirmDialog(this.guiGestionarVenta, "DESEA ELIMINAR LA VENTA "+idVenta, "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);
				if(opcion == JOptionPane.YES_OPTION){
					persistencia.iniciarTransaccion();
					Venta vta=controlVenta.obtenerVenta(idVenta);
					controlVenta.eliminarVenta(vta);
					
					this.modelo.removeRow(filaReal);
					this.modelo.fireTableDataChanged();
					persistencia.concretarTransaccion();
				}
					
				
	        	if(this.modelo.getRowCount()==0){
	        		this.guiGestionarVenta.getBotonModificar().setEnabled(false);
	        		this.guiGestionarVenta.getBotonEliminar().setEnabled(false);
	        		this.guiGestionarVenta.getBotonReporte().setEnabled(false);
	        	}
		
			}else{JOptionPane.showMessageDialog(this.guiGestionarVenta, "SELECCIONE ALGUNA FILA");}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource(); 
		if(this.guiGestionarVenta.getBotonAgregar()== source){
			this.agregarVenta();
		}
		if(this.guiGestionarVenta.getBotonEliminar()==source){
			this.eliminarVenta();
		}
		if(this.guiGestionarVenta.getBotonModificar()==source){
			this.modificarVenta();
		}
		if(this.guiGestionarVenta.getBotonReporte()==source){
			this.opcionReporte();
		}
	}

	private void opcionReporte() {
		if(this.guiGestionarVenta.getTableRowSorter().getViewRowCount()>0){
			int numColumnas = this.modelo.getColumnCount();
			int numFilas = this.guiGestionarVenta.getTableRowSorter().getViewRowCount();
			Object[][] datos = new Object[numFilas][numColumnas];
			for(int i=0;i<numFilas;i++){
				for(int j=0;j<numColumnas;j++){
					int filaVista = this.guiGestionarVenta.getTableRowSorter().convertRowIndexToModel(i);
					datos[i][j] = this.modelo.getValueAt(filaVista, j);
				}
			}
			guiGestionarVenta.setVisible(false);
			ReporteVenta viewReport = new ReporteVenta(guiGestionarVenta);
			try{
				persistencia.iniciarTransaccion();
				BasicVet bv = controlBasicVet.obtenerBasicVet("1");
				viewReport.viewFicha(datos,bv.getNombre(),bv.getDireccion(),bv.getTelefono()+"",bv.getCuil());
				persistencia.concretarTransaccion();
			}
			catch(Exception e){
				System.out.println("Error "+this.getClass().toString());
				e.printStackTrace();
				persistencia.deshacerTransaccion();
			}
		}else{JOptionPane.showMessageDialog(guiGestionarVenta, "LA LISTA ESTA VACIA");}
	}

}

