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

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import mediador.MediadorGeneral;
import persistencia.Persistencia;
import persistencia.dominio.Cliente;
import persistencia.dominio.Producto;
import persistencia.dominio.Venta;
import cuGestionarCliente.ControlCliente;
import cuGestionarCliente.MediadorBuscarCliente;
import cuGestionarProducto.ControlProducto;
import cuGestionarProducto.MediadorBuscarProducto;

@SuppressWarnings("unused")
public class MediadorModificarVenta extends MediadorGeneral implements ActionListener{

	private GUIVenta guiModVenta;
	private GUIGestionarVenta guiGesVta;
	private int fila;
	private int filaReal;
	private DefaultTableModel modeloVta;
	private ControlVenta controlVenta;
	private ControlProducto controlProducto;
	private ControlCliente controlCliente;
	private Persistencia persistencia= new Persistencia() ;
	private int idVenta;
	private DefaultTableModel modeloCli= new DefaultTableModel();
	private int codProducto;
	private String dniCliente;

	public MediadorModificarVenta(DefaultTableModel modelo,GUIGestionarVenta guiGestionarVta){
		super();
		this.modeloVta=modelo;
		this.guiGesVta=guiGestionarVta;
		this.inicializar();	
	}

	@SuppressWarnings("deprecation")
	public void inicializar(){
		controlVenta= new ControlVenta();
		controlProducto=new ControlProducto();
		controlCliente= new ControlCliente();
		fila = this.guiGesVta.getTablaVentas().getSelectedRow();
		try{
			if(fila>=0){
				this.guiModVenta = new GUIVenta(modeloVta);
				this.guiModVenta.setTitle("MODIFICAR VENTA");
				
				filaReal = this.guiGesVta.getTableRowSorter().convertRowIndexToModel(fila);
				idVenta = Integer.parseInt((String)this.modeloVta.getValueAt(filaReal, 0));
				persistencia.iniciarTransaccion();
				Venta vta=controlVenta.obtenerVenta(idVenta);
	
				/*inicializacion de los campos a modificar*/
				String fecha=vta.getFechaVenta().getDate()+"/"+(vta.getFechaVenta().getMonth()+1)+"/"+(vta.getFechaVenta().getYear()+1900);
				this.guiModVenta.getTextoFecha().setText(fecha);
				this.guiModVenta.getTextoDni().setText(vta.getCliente().getDni().toUpperCase());
				this.guiModVenta.getTextoNombre().setText(vta.getCliente().getNombre().toUpperCase());
				this.guiModVenta.getTextoApellidoCli().setText(vta.getCliente().getApellido().toUpperCase());
				this.guiModVenta.getTextoIdProd().setText(vta.getProductoVenta().getId()+"");
				this.guiModVenta.getTextoNombreProd().setText(vta.getProductoVenta().getNombre().toUpperCase());
				this.guiModVenta.getTextoTipoProd().setText(vta.getProductoVenta().getTipo().toUpperCase());
				this.guiModVenta.getTextoPrecio().setText(vta.getPrecioUnitario()+"");
				this.guiModVenta.getTextoCant().setText(vta.getCantidadVenta()+"");
	
	
				this.guiModVenta.getBotonAceptar().setEnabled(true);
				this.guiModVenta.getBotonCancelar().setEnabled(true);
				this.guiModVenta.getBotonBuscarProd().setEnabled(true);
				this.guiModVenta.getBotonBuscarCliente().setEnabled(true);
				this.guiModVenta.setListenerButtons(this);
	
				persistencia.concretarTransaccion();
	
			}else{JOptionPane.showMessageDialog(this.guiGesVta, "SELECCIONE ALGUNA FILA");}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiModVenta.show();
	}


	private void aceptarModificacion(){
		try{
			persistencia.iniciarTransaccion();
			Venta vta=controlVenta.obtenerVenta(idVenta);
			String dniCli=this.guiModVenta.getTextoDni().getText();
			Cliente cli=controlCliente.obtenerCliente(dniCli.toLowerCase());
			float precio=0;
			if(!guiModVenta.getTextoPrecio().getText().isEmpty()){
				precio=Float.parseFloat(guiModVenta.getTextoPrecio().getText());
			}
			int cant=0;
			if(!this.guiModVenta.getTextoCant().getText().isEmpty()){
				cant=Integer.parseInt(this.guiModVenta.getTextoCant().getText());
			}
			String id=this.guiModVenta.getTextoIdProd().getText();
			Producto prod=controlProducto.obtenerProducto(Integer.parseInt(id));
			
			int cantidadValida=cant-prod.getCantidad();
			//actualiza la venta y la cantidad de productos
			if(cantidadValida<=0){//hay disponibilidad
				vta.setCliente(cli);
				vta.setPrecioUnitario(precio);
				//prod.setCantidad(cant-prod.getCantidad());
				float total=cant*precio;
				vta.setTotalVenta(total);
				//actualizacion cantidad de productos
				int oldCant=vta.getCantidadVenta();
				if(oldCant>=cant){
					int dif=oldCant-cant;
					prod.setCantidad(prod.getCantidad()+dif);
				}else{
					int dif=cant-oldCant;
					prod.setCantidad(prod.getCantidad()-dif);
				}
				vta.setCantidadVenta(cant);
				vta.setProductoVenta(prod);
				/*actualizacion de la venta en la tabla de ventas*/
				this.modeloVta.setValueAt(vta.getCliente().getNombre().toUpperCase(), fila, 2);
				this.modeloVta.setValueAt(vta.getProductoVenta().getNombre().toUpperCase(),fila,3);
				this.modeloVta.setValueAt(vta.getCantidadVenta()+"", fila, 4);
				this.modeloVta.setValueAt(vta.getPrecioUnitario()+"", fila, 5);
				this.modeloVta.setValueAt(vta.getTotalVenta()+"", fila, 6);
				this.modeloVta.fireTableDataChanged();
	
				persistencia.concretarTransaccion();
	
				this.guiModVenta.setVisible(false);
				this.guiModVenta.dispose();
				
			}else{JOptionPane.showMessageDialog(this.guiModVenta, "INSUFICIENTE STOCK DE PRODUCTO,VERIFIQUE CANTIDAD");}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}

		
	}

	private void cancelarModificacion() {
		this.guiModVenta.setVisible(false);
		this.guiModVenta.dispose();

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
			this.guiModVenta.getTextoNombreProd().setText(prod.getNombre().toUpperCase());
			this.guiModVenta.getTextoTipoProd().setText(prod.getTipo().toUpperCase());
			this.guiModVenta.getTextoIdProd().setText(prod.getId()+"");		
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}



	public void buscarCliente(){
		MediadorBuscarCliente medBuscarCliente= new MediadorBuscarCliente(this);
		medBuscarCliente.mostrar();
	}

	
	
	public void setClienteBuscado(String dniCliente){
		this.dniCliente = dniCliente;
		try{
			persistencia.iniciarTransaccion();
			Cliente cli=controlCliente.obtenerCliente(dniCliente.toLowerCase());
			this.guiModVenta.getTextoNombre().setText(cli.getNombre().toUpperCase());
			this.guiModVenta.getTextoApellidoCli().setText(cli.getApellido().toUpperCase());
			this.guiModVenta.getTextoDni().setText(cli.getDni().toUpperCase());		
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
		if(this.guiModVenta.getBotonBuscarCliente()== source){
			this.buscarCliente();

		}
		if(this.guiModVenta.getBotonBuscarProd()== source){
			this.buscarProducto();
		}
		if(this.guiModVenta.getBotonAceptar()== source){
			this.aceptarModificacion();
		}
		if(this.guiModVenta.getBotonCancelar()== source){
			this.cancelarModificacion();
		}

	}

}
