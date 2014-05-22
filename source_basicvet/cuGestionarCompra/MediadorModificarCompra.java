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
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import mediador.MediadorGeneral;
import persistencia.Persistencia;
import persistencia.dominio.Compra;
import persistencia.dominio.Producto;
import persistencia.dominio.Proveedor;
import cuGestionarProducto.ControlProducto;
import cuGestionarProducto.MediadorBuscarProducto;
import cuGestionarProveedor.ControlProveedor;
import cuGestionarProveedor.MediadorIngresarProveedor;

public class MediadorModificarCompra extends MediadorGeneral implements ActionListener{

	private GUICompra guiModCompra;
	private GUIGestionarCompra guiGesCompra;
	private Persistencia persistencia= new Persistencia() ;
	private ControlCompra controlCompra;
	private ControlProducto controlProducto;
	private ControlProveedor controlProveedor;
	private int idCompra;
	private DefaultTableModel modeloProv= new DefaultTableModel();
	private DefaultTableModel modeloCompra;
	@SuppressWarnings("unused")
	private int codProducto;
	private int fila;
	private int filaReal;
	
	public MediadorModificarCompra(DefaultTableModel modelo,GUIGestionarCompra guiGestionarCompra){
		super();
		this.guiGesCompra=guiGestionarCompra;
		this.modeloCompra=modelo;
		this.inicializar();	
	}
	
	@SuppressWarnings("deprecation")
	public void inicializar(){
		controlCompra= new ControlCompra();
		controlProducto= new ControlProducto();
		controlProveedor=new ControlProveedor();
		try{
			fila = this.guiGesCompra.getTablaCompra().getSelectedRow();
			if(fila>=0){
				this.guiModCompra = new GUICompra(modeloCompra);
				this.guiModCompra.setTitle("MODIFICAR COMPRA");
				filaReal = this.guiGesCompra.getTableRowSorter().convertRowIndexToModel(fila);
				idCompra = Integer.parseInt((String)this.modeloCompra.getValueAt(filaReal, 0));
				/*inicializa el combo proveedores*/
				this.cargarProveedores();
				
				persistencia.iniciarTransaccion();
				
				/*inicializacion de los campos a modificar*/
				Compra c=controlCompra.obtenerCompra(idCompra);
				String fecha=c.getFecha().getDate()+"/"+(c.getFecha().getMonth()+1)+"/"+(c.getFecha().getYear()+1900);
				this.guiModCompra.getTextoFecha().setText(fecha);
				this.guiModCompra.getComboProveedor().setSelectedItem(c.getProveedor().getDni()+"-"+c.getProveedor().getNombre().toUpperCase());
				this.guiModCompra.getTextoIdProd().setText(c.getProd().getId()+"");
				this.guiModCompra.getTextoNombreProd().setText(c.getProd().getNombre().toUpperCase());
				this.guiModCompra.getTextoPrecio().setText(c.getPrecioUnitario()+"");
				this.guiModCompra.getTextoCantProd().setText(c.getCantidadCompra()+"");
				
				
				persistencia.concretarTransaccion();
				
				this.guiModCompra.getBotonAceptar().setEnabled(true);
				this.guiModCompra.getBotonCancelar().setEnabled(true);
				this.guiModCompra.getBotonBuscar().setEnabled(true);
				this.guiModCompra.getBotonAgregarProv().setEnabled(true);
				this.guiModCompra.setListenerButtons(this);
				
			}else{JOptionPane.showMessageDialog(this.guiGesCompra, "SELECCIONE ALGUNA FILA");}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiModCompra.show();
	}
	
	
	private void aceptarModificacion(){
		try{
			persistencia.iniciarTransaccion();
			Compra c=controlCompra.obtenerCompra(idCompra);
			String nombreProv=this.guiModCompra.getComboProveedor().getSelectedItem().toString().toUpperCase();
			Proveedor prov=controlProveedor.obtenerProveedor(nombreProv.split("-")[0]);
			c.setProveedor(prov);
			float precio=0;
			if(!guiModCompra.getTextoPrecio().getText().isEmpty()){
				precio=Float.parseFloat(guiModCompra.getTextoPrecio().getText());
			}
			int cant=0;
			if(!this.guiModCompra.getTextoCantProd().getText().isEmpty()){
				cant=Integer.parseInt(this.guiModCompra.getTextoCantProd().getText());
			}
			float total=precio*cant;
			c.setPrecioUnitario(precio);
			c.setTotalCompra(total);
			int oldCant=c.getCantidadCompra();
			c.setCantidadCompra(cant);
			String id=this.guiModCompra.getTextoIdProd().getText();
			Producto prod=controlProducto.obtenerProducto(Integer.parseInt(id));
			//actualizacion cantidad de productos
			if(oldCant>=cant){
				int dif=oldCant-cant;
				prod.setCantidad(prod.getCantidad()-dif);
			}else{
				int dif=cant-oldCant;
				prod.setCantidad(prod.getCantidad()+dif);
			}
			c.setProd(prod);
			
			/*actualizacion de la compra en la tabla de compras*/
			this.modeloCompra.setValueAt(c.getProveedor().getNombre().toUpperCase(), fila, 2);
			this.modeloCompra.setValueAt(c.getProd().getNombre().toUpperCase(),fila,3);
			this.modeloCompra.setValueAt(c.getCantidadCompra()+"", fila, 4);
			this.modeloCompra.setValueAt(c.getPrecioUnitario()+"", fila, 5);
			this.modeloCompra.setValueAt(c.getTotalCompra()+"", fila, 6);
			this.modeloCompra.fireTableDataChanged();
			
			persistencia.concretarTransaccion();
			
			this.guiModCompra.setVisible(false);
			this.guiModCompra.dispose();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	private void cancelarModificacion() {
		this.guiModCompra.setVisible(false);
		this.guiModCompra.dispose();
		
	}
	
	public void buscarProducto(){
		MediadorBuscarProducto medBuscarProd= new MediadorBuscarProducto(this);
		medBuscarProd.mostrar();
	}

	
	
	public void setProductoBuscado(int codProducto){
		this.codProducto = codProducto;
		try{
			persistencia.iniciarTransaccion();
			Producto prod=controlProducto.obtenerProducto(codProducto);
			this.guiModCompra.getTextoNombreProd().setText(prod.getNombre().toUpperCase());
			this.guiModCompra.getTextoIdProd().setText(prod.getId()+"");		
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	

	private void cargarProveedores(){
		try{
			persistencia.iniciarTransaccion();
			Iterator<Proveedor> iterProveedor = controlProveedor.obtenerProveedores();
			while(iterProveedor.hasNext()){
				Proveedor prov = iterProveedor.next();
				this.guiModCompra.getComboProveedor().addItem(prov.getDni()+"-"+prov.getNombre().toUpperCase());
			}
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	public void agregarProveedor(){
		MediadorIngresarProveedor medIngresarProv= new MediadorIngresarProveedor(this.modeloProv);
		medIngresarProv.mostrar();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(this.guiModCompra.getBotonAgregarProv()== source){
			this.agregarProveedor();
			//this.cargarProveedores();
		}
		if(this.guiModCompra.getBotonBuscar()== source){
			this.buscarProducto();
		}
		if(this.guiModCompra.getBotonAceptar()== source){
			this.aceptarModificacion();
		}
		if(this.guiModCompra.getBotonCancelar()== source){
			this.cancelarModificacion();
		}
		
	}

}
