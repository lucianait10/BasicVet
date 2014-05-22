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

package cuGestionarCompra;

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
import persistencia.dominio.Compra;
import clasesComunes.ControlBasicVet;
import cuGestionarCompra.report.ReporteCompra;


public class MediadorGestionarCompra extends MediadorGeneral implements ActionListener {

	private GUIGestionarCompra guiGestionarCompra;
	private Persistencia persistencia= new Persistencia() ;
	private ControlCompra controlCompra;
	private DefaultTableModel modelo = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable (int row, int column){return false;}
	};
	private ControlBasicVet controlBasicVet;
	
	
	public MediadorGestionarCompra(){
		super();
		this.inicializar();	
	}
	
	@SuppressWarnings({ "deprecation" })
	public void inicializar(){
		controlCompra=new ControlCompra();
		this.controlBasicVet = new ControlBasicVet();
		this.modelo.setColumnIdentifiers(new String[]{"ID","FECHA","PROVEEDOR","PRODUCTO","CANTIDAD","P. UNITARIO", "TOTAL"});
		this.guiGestionarCompra = new GUIGestionarCompra(this.modelo);
		this.guiGestionarCompra.getBotonModificar().setEnabled(false);
		this.guiGestionarCompra.getBotonEliminar().setEnabled(false);
		this.guiGestionarCompra.getBotonReporte().setEnabled(false);
		try{
			persistencia.iniciarTransaccion();
			Iterator<Compra> iterCompra = controlCompra.obtenerCompras();
			Compra compra;
			while(iterCompra.hasNext()){
				compra = iterCompra.next();
				String fecha=compra.getFecha().getDate()+"/"+(compra.getFecha().getMonth()+1)+"/"+(compra.getFecha().getYear()+1900);
				this.modelo.addRow(new String[]{compra.getId()+"",fecha,compra.getProveedor().getNombre().toUpperCase(),compra.getProd().getNombre().toUpperCase(),Integer.toString(compra.getCantidadCompra()),Float.toString(compra.getPrecioUnitario()),Float.toString(compra.getTotalCompra())});
			}
			if(this.modelo.getRowCount()>0){
				this.guiGestionarCompra.getBotonModificar().setEnabled(true);
				this.guiGestionarCompra.getBotonEliminar().setEnabled(true);
				this.guiGestionarCompra.getBotonReporte().setEnabled(true);
			}
			persistencia.concretarTransaccion();
			this.guiGestionarCompra.setListenerButtons(this);
			this.guiGestionarCompra.setListenerMouse(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2)modificarCompra();}
			});
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiGestionarCompra.show();
	}
	
	private void agregarCompra() {
		MediadorAltaCompra medAltaCompra = new MediadorAltaCompra(this.modelo);
		medAltaCompra.mostrar();
		if(this.modelo.getRowCount()>0){
			this.guiGestionarCompra.getBotonModificar().setEnabled(true);
			this.guiGestionarCompra.getBotonEliminar().setEnabled(true);
			this.guiGestionarCompra.getBotonReporte().setEnabled(true);
		}
		this.modelo.fireTableDataChanged();
	}
	
		
	
	private void modificarCompra(){
			MediadorModificarCompra medModificar = new MediadorModificarCompra(modelo,guiGestionarCompra);
			medModificar.mostrar();
	}
	
	private void eliminarCompra(){
		JTable jt = this.guiGestionarCompra.getTablaCompra();
		int fila = jt.getSelectedRow();
		int idCompra=-1;
		try{
			if(fila>=0){
				int filaReal = this.guiGestionarCompra.getTableRowSorter().convertRowIndexToModel(fila);
				idCompra = Integer.parseInt((String) this.guiGestionarCompra.getTablaCompra().getValueAt(filaReal,0));
				int opcion = JOptionPane.showConfirmDialog(this.guiGestionarCompra, "DESEA ELIMINAR LA COMPRA "+idCompra, "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);
				if(opcion == JOptionPane.YES_OPTION){
					persistencia.iniciarTransaccion();
					Compra c=controlCompra.obtenerCompra(idCompra);
					controlCompra.eliminarCompra(c);
					persistencia.concretarTransaccion();
					this.modelo.removeRow(filaReal);
					this.modelo.fireTableDataChanged();	
				}
					
					
	        		if(this.modelo.getRowCount()==0){
	        			this.guiGestionarCompra.getBotonModificar().setEnabled(false);
	        			this.guiGestionarCompra.getBotonEliminar().setEnabled(false);
	        			this.guiGestionarCompra.getBotonReporte().setEnabled(false);
	        		}
		
			}else{JOptionPane.showMessageDialog(this.guiGestionarCompra, "SELECCIONE ALGUNA FILA");}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource(); 
		if(this.guiGestionarCompra.getBotonAgregar()== source){
			this.agregarCompra();
		}
		if(this.guiGestionarCompra.getBotonEliminar()==source){
			this.eliminarCompra();
		}
		if(this.guiGestionarCompra.getBotonModificar()==source){
			this.modificarCompra();
		}
		if(this.guiGestionarCompra.getBotonReporte()==source){
			this.opcionReporte();
		}
	}

	private void opcionReporte() {
		if(this.guiGestionarCompra.getTableRowSorter().getViewRowCount()>0){
			int numColumnas = this.modelo.getColumnCount();
			int numFilas = this.guiGestionarCompra.getTableRowSorter().getViewRowCount();
			Object[][] datos = new Object[numFilas][numColumnas];
			for(int i=0;i<numFilas;i++){
				for(int j=0;j<numColumnas;j++){
					int filaVista = this.guiGestionarCompra.getTableRowSorter().convertRowIndexToModel(i);
					datos[i][j] = this.modelo.getValueAt(filaVista, j);
					System.out.println(datos[i][j]);
				}
			}
			guiGestionarCompra.setVisible(false);
			ReporteCompra viewReport = new ReporteCompra(guiGestionarCompra);
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
		}else{JOptionPane.showMessageDialog(guiGestionarCompra, "LA LISTA ESTA VACIA");}
	}

}

