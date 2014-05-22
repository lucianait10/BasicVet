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
import java.util.Date;

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

@SuppressWarnings({"unused","deprecation"})
public class MediadorAltaVenta extends MediadorGeneral implements ActionListener {

	private GUIVenta guiAltaVenta;
	private ControlProducto controlProducto;
	private ControlVenta controlVenta;
	private ControlCliente controlCliente;
	private Persistencia persistencia= new Persistencia() ;
	private DefaultTableModel tablaVentas= new DefaultTableModel();
	private DefaultTableModel modeloCli= new DefaultTableModel();
	private Date hoy;
	private int codProducto;
	private String dniCliente;
	
	public MediadorAltaVenta(DefaultTableModel t){
		super();
		this.tablaVentas=t;
		this.inicializar();	
	}
	
	public void mostrar(){
		guiAltaVenta.show();
	}
	
	private void cancelarInsercion() {
		this.guiAltaVenta.setVisible(false);
		this.guiAltaVenta.dispose();
		
	}
	
	public void inicializar(){
		controlProducto=new ControlProducto();
		controlCliente= new ControlCliente();
		controlVenta= new ControlVenta();
		this.guiAltaVenta = new GUIVenta(tablaVentas);
		this.guiAltaVenta.setTitle("ALTA VENTA");
		hoy=new Date();
		String f="";
		f=hoy.getDate()+"/"+(hoy.getMonth()+1)+"/"+(hoy.getYear()+1900);
		this.guiAltaVenta.getTextoCant().setText(null);
		this.guiAltaVenta.getTextoFecha().setText(f);
		this.guiAltaVenta.getBotonAceptar().setEnabled(true);
		this.guiAltaVenta.getBotonCancelar().setEnabled(true);
		this.guiAltaVenta.getBotonBuscarProd().setEnabled(true);
		this.guiAltaVenta.getBotonBuscarCliente().setEnabled(true);
		this.guiAltaVenta.setListenerButtons(this);
	
	}
	
	
	public void aceptarVenta(){
		try{
			persistencia.iniciarTransaccion();
			String id=this.guiAltaVenta.getTextoIdProd().getText();
			int cant=0;
			if(!this.guiAltaVenta.getTextoCant().getText().isEmpty()){
				cant=Integer.parseInt(this.guiAltaVenta.getTextoCant().getText());
			}
			Producto prod=controlProducto.obtenerProducto(Integer.parseInt(id));
			int cantidadValida=cant-prod.getCantidad();
			//verifica que la cantidad de venta no exceda la cantidad  en sotck
			if(cantidadValida<=0){
				String f=guiAltaVenta.getTextoFecha().getText();
				float precio=0;
				if(!this.guiAltaVenta.getTextoPrecio().getText().isEmpty()){
					precio=Float.parseFloat(guiAltaVenta.getTextoPrecio().getText());
				}
				String nombreCli=this.guiAltaVenta.getTextoNombre().getText();
				String apeCli=this.guiAltaVenta.getTextoApellidoCli().getText();
				String nombreProd=this.guiAltaVenta.getTextoNombreProd().getText();		
				Cliente cli=controlCliente.obtenerCliente(this.guiAltaVenta.getTextoDni().getText().toLowerCase());
				//actualiza la cantidad de productos
				prod.setCantidad(prod.getCantidad()-cant);
				float total=cant*precio;//precio total
				Venta vta= new Venta(hoy,cli,prod,cant,precio,total);
				vta=controlVenta.guardarVenta(vta);
				//agraga la venta al modelo
				this.tablaVentas.addRow(new String[]{vta.getId()+"",f,nombreCli.toUpperCase()+" "+apeCli.toUpperCase(),nombreProd.toUpperCase(),cant+"",precio+"",total+""});
				this.guiAltaVenta.setVisible(false);
				this.guiAltaVenta.dispose();
				persistencia.concretarTransaccion();	
			}else{JOptionPane.showMessageDialog(this.guiAltaVenta, "INSUFICIENTE STOCK DE PRODUCTO,VERIFIQUE CANTIDAD");}
			
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
			
		
    
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
			this.guiAltaVenta.getTextoNombreProd().setText(prod.getNombre().toUpperCase());
			this.guiAltaVenta.getTextoTipoProd().setText(prod.getTipo().toUpperCase());
			this.guiAltaVenta.getTextoIdProd().setText(prod.getId()+"");		
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
		this.dniCliente = dniCliente.toLowerCase();
		try{
			persistencia.iniciarTransaccion();
			Cliente cli=controlCliente.obtenerCliente(dniCliente);
			this.guiAltaVenta.getTextoNombre().setText(cli.getNombre().toUpperCase());
			this.guiAltaVenta.getTextoApellidoCli().setText(cli.getApellido().toUpperCase());
			this.guiAltaVenta.getTextoDni().setText(cli.getDni().toUpperCase());		
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
		if(this.guiAltaVenta.getBotonBuscarCliente()== source){
			this.buscarCliente();
		}
		if(this.guiAltaVenta.getBotonBuscarProd()== source){
			this.buscarProducto();
		}
		if(this.guiAltaVenta.getBotonAceptar()== source){
			this.aceptarVenta();
		}
		if(this.guiAltaVenta.getBotonCancelar()== source){
			this.cancelarInsercion();
		}
	}
}

