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

package cuGestionarProducto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import persistencia.Persistencia;
import persistencia.dominio.BasicVet;
import persistencia.dominio.Producto;
import clasesComunes.ControlBasicVet;
import cuGestionarProducto.report.ReporteProducto;

@SuppressWarnings({ "unchecked"})
public class MediadorGestionarProducto implements ActionListener {

	private GUIGestionarProducto guiGestionarProducto;
	private ControlProducto controlProducto;
	private Persistencia persistencia= new Persistencia() ;
	private DefaultTableModel modelo = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable (int row, int column){return false;}
	};
	private ControlBasicVet controlBasicVet;


	public MediadorGestionarProducto(){
		super();
		this.inicializar();	
	}


	public void inicializar(){
		this.controlBasicVet = new ControlBasicVet();
		this.controlProducto= new ControlProducto();
		this.modelo.setColumnIdentifiers(new String[]{"ID","NOMBRE","TIPO","DESCRIPCION","PRECIO","CANTIDAD"});
		this.guiGestionarProducto = new GUIGestionarProducto(this.modelo);
		this.guiGestionarProducto.getBotonModificar().setEnabled(false);
		this.guiGestionarProducto.getBotonEliminar().setEnabled(false);
		this.guiGestionarProducto.getBotonReporteProducto().setEnabled(false);
		try{
			persistencia.iniciarTransaccion();
			Iterator iter=controlProducto.obtenerProductos();
			while(iter.hasNext()){
				Producto p=(Producto)iter.next();
				this.modelo.addRow(new String[]{p.getId()+"",p.getNombre().toUpperCase(),p.getTipo().toUpperCase(),p.getDescripcion().toUpperCase()+"",Float.toString(p.getPrecio()),Integer.toString(p.getCantidad())});
			}
			if(this.modelo.getRowCount()>0){
				this.guiGestionarProducto.getBotonModificar().setEnabled(true);
				this.guiGestionarProducto.getBotonEliminar().setEnabled(true);
				this.guiGestionarProducto.getBotonReporteProducto().setEnabled(true);
			}
			persistencia.concretarTransaccion();
			this.guiGestionarProducto.setListenerButtons(this);
			this.guiGestionarProducto.setListenerMouse(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2)modificarProducto();}
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
		this.guiGestionarProducto.show();
	}

	private void agregarProducto() {
		MediadorAltaProducto medAltaProducto = new MediadorAltaProducto(this.modelo);
		medAltaProducto.mostrar();
		if(this.modelo.getRowCount()>0){
			this.guiGestionarProducto.getBotonModificar().setEnabled(true);
			this.guiGestionarProducto.getBotonEliminar().setEnabled(true);
			this.guiGestionarProducto.getBotonReporteProducto().setEnabled(true);
		}
		this.modelo.fireTableDataChanged();
	}

	private void modificarProducto(){
		MediadorModificarProducto medModificar = new MediadorModificarProducto(modelo,guiGestionarProducto);
		medModificar.mostrar();
	}

	private void eliminarProducto(){
		JTable jt = this.guiGestionarProducto.getTablaProductos();
		int fila = jt.getSelectedRow();
		int idProducto=-1;
		try{
			persistencia.iniciarTransaccion();
			if(fila>=0){
				int filaReal = this.guiGestionarProducto.getTableRowSorter().convertRowIndexToModel(fila);
				idProducto = Integer.parseInt((String) this.modelo.getValueAt(filaReal, 0));
				
				Producto p=controlProducto.obtenerProducto(idProducto);
				if(!(p.getTipo().toUpperCase().compareTo("VACUNA")==0)&& !(p.getTipo().toUpperCase().compareTo("ANTICONCEPTIVO")==0) && !(p.getTipo().toUpperCase().compareTo("ANTIPARASITARIO")==0)){
					int opcion = JOptionPane.showConfirmDialog(this.guiGestionarProducto, "DESEA ELIMINAR EL PRODUCTO "+idProducto, "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);
					if(opcion == JOptionPane.YES_OPTION){
						controlProducto.eliminarProducto(p);					
						
						this.modelo.removeRow(filaReal);
						this.modelo.fireTableDataChanged();
						if(this.modelo.getRowCount()==0){
							this.guiGestionarProducto.getBotonModificar().setEnabled(false);
							this.guiGestionarProducto.getBotonEliminar().setEnabled(false);
							this.guiGestionarProducto.getBotonReporteProducto().setEnabled(false);
						}
					}
				}else{JOptionPane.showMessageDialog(this.guiGestionarProducto, "LOS MEDICAMENTOS SON REFERENCIADOS EN LA CARTILLA Y NO PUEDEN SER ELIMINADOS");}
			}else{JOptionPane.showMessageDialog(this.guiGestionarProducto, "SELECCIONE ALGUNA FILA");}
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource(); 
		if(this.guiGestionarProducto.getBotonAgregar()== source){
			this.agregarProducto();
		}
		if(this.guiGestionarProducto.getBotonEliminar()==source){
			this.eliminarProducto();
		}
		if(this.guiGestionarProducto.getBotonModificar()==source){
			this.modificarProducto();
		}
		if(this.guiGestionarProducto.getBotonReporteProducto()==source){
			this.opcionReporte();
		}
	}

	private void opcionReporte() {
		if(this.guiGestionarProducto.getTableRowSorter().getViewRowCount()>0){
			int numColumnas = this.modelo.getColumnCount();
			int numFilas = this.guiGestionarProducto.getTableRowSorter().getViewRowCount();
			Object[][] datos = new Object[numFilas][numColumnas];
			for(int i=0;i<numFilas;i++){
				for(int j=0;j<numColumnas;j++){
					int filaVista = this.guiGestionarProducto.getTableRowSorter().convertRowIndexToModel(i);
					datos[i][j] = this.modelo.getValueAt(filaVista, j);
				}
			}
			guiGestionarProducto.setVisible(false);
			ReporteProducto viewReport = new ReporteProducto(guiGestionarProducto);
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
		}else{JOptionPane.showMessageDialog(guiGestionarProducto, "LA LISTA ESTA VACIA");}
	}

}
