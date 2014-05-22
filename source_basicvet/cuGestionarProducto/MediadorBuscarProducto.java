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

import mediador.MediadorGeneral;
import persistencia.Persistencia;
import persistencia.dominio.Producto;

@SuppressWarnings({ "unchecked"})
public class MediadorBuscarProducto implements ActionListener{

	private GUIBuscarProducto guiBuscarProducto;
	private DefaultTableModel tabla = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable (int row, int column){return false;}
	};
	private Persistencia persistencia= new Persistencia(); 
	private ControlProducto controlProducto;
	private static int idProductoBuscado;
	MediadorGeneral mediador;

	public MediadorBuscarProducto(MediadorGeneral m){
		super();
		this.mediador=m;
		this.inicializar();
	}

	@SuppressWarnings("deprecation")
	public void mostrar() {
		this.guiBuscarProducto.show();
	}


	private void inicializar() {
		controlProducto= new ControlProducto();
		this.tabla.setColumnIdentifiers(new String[]{"ID","NOMBRE","TIPO","DESCRIPCION","PRECIO","CANTIDAD"});
		try{
			persistencia.iniciarTransaccion();
			Iterator iter=controlProducto.obtenerProductos();
			while(iter.hasNext()){
				Producto p=(Producto)iter.next();
				this.tabla.addRow(new String[]{p.getId()+"",p.getNombre().toUpperCase(),p.getTipo().toUpperCase(),p.getDescripcion().toUpperCase()+"",Float.toString(p.getPrecio()),Integer.toString(p.getCantidad())});
			}
			this.guiBuscarProducto = new GUIBuscarProducto(tabla);
			this.guiBuscarProducto.getBotonAceptar().setEnabled(true);
			this.guiBuscarProducto.getBotonCancelar().setEnabled(true);
			this.guiBuscarProducto.getBotonAgregar().setEnabled(true);
			this.guiBuscarProducto.setListenerButtons(this);
			this.guiBuscarProducto.setListenerMouse(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2)aceptarBusqueda();}
			});
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
		if(this.guiBuscarProducto.getBotonAceptar() == source){
			this.aceptarBusqueda();
		}
		if(this.guiBuscarProducto.getBotonCancelar() == source){
			this.cancelarBusqueda();
		}
		if(this.guiBuscarProducto.getBotonAgregar() == source){
			this.agregarProducto();
		}
	}

	private void agregarProducto() {
		MediadorAltaProducto medAltaProd= new MediadorAltaProducto(this.tabla);
		medAltaProd.mostrar();

	}

	private void cancelarBusqueda() {
		this.guiBuscarProducto.setVisible(false);
		this.guiBuscarProducto.dispose();

	}

	private void aceptarBusqueda() {
		JTable jt = this.guiBuscarProducto.getTablaBusqueda();
		int fila = jt.getSelectedRow();
		if(fila >= 0){
			int filaReal = this.guiBuscarProducto.getTableRowSorter().convertRowIndexToModel(fila);
			idProductoBuscado =Integer.parseInt((String)this.tabla.getValueAt(filaReal,0));
			mediador.setProductoBuscado(idProductoBuscado);
			this.guiBuscarProducto.setVisible(false);
			this.guiBuscarProducto.dispose();
		}
		else{
			JOptionPane.showMessageDialog(guiBuscarProducto,"SELECCIONE ALGUNA FILA");
		}

	}


}
