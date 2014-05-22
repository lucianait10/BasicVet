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

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import persistencia.Persistencia;
import persistencia.dominio.Anticonceptivo;
import persistencia.dominio.Antiparasitario;
import persistencia.dominio.Producto;
import persistencia.dominio.Vacuna;


public class MediadorModificarProducto implements ActionListener{

	private GUIProducto guiProducto;
	private GUIGestionarProducto guiGestionarProducto;
	private DefaultTableModel tablaProductos=new DefaultTableModel();
	private int fila;
	private int filaReal;
	private int idProducto;
	private Persistencia persistencia= new Persistencia() ;
	private ControlProducto controlProducto;

	public MediadorModificarProducto(DefaultTableModel tablaProd,GUIGestionarProducto guiGestionarProd){
		super();
		this.tablaProductos=tablaProd;
		this.guiGestionarProducto=guiGestionarProd;
		this.inicializar();	
	}

	public void inicializar(){
		controlProducto=new ControlProducto();
		fila = this.guiGestionarProducto.getTablaProductos().getSelectedRow();
		try{
			persistencia.iniciarTransaccion();
			if(fila>=0){
				this.guiProducto = new GUIProducto(tablaProductos);
				
				filaReal = this.guiGestionarProducto.getTableRowSorter().convertRowIndexToModel(fila);
				idProducto = Integer.parseInt((String)this.tablaProductos.getValueAt(filaReal, 0));
				Producto p=controlProducto.obtenerProducto(idProducto);
				this.guiProducto.getTextoCantidad().setText(p.getCantidad()+"");
				/*Inicializacion para modificar Vacuna*/
				if(p.getTipo().toUpperCase().compareTo("VACUNA")==0){
					Vacuna v=controlProducto.obtenerVacuna(idProducto);
					this.guiProducto.getOpcionAntic().setEnabled(false);
					this.guiProducto.getOpcionAntip().setEnabled(false);
					this.guiProducto.getOpcionOtro().setEnabled(false);
					this.guiProducto.getTextoLab().setEditable(true);
					this.guiProducto.getTextoLab().setBackground(Color.WHITE);
					this.guiProducto.getTextoSerie().setEditable(true);
					this.guiProducto.getTextoSerie().setBackground(Color.WHITE);
					this.guiProducto.getTextoOtro().setEditable(false);
					this.guiProducto.getTextoOtro().setBackground(new Color(238, 238, 238));
	
	
					this.guiProducto.getTextoNombre().setText(v.getNombre().toUpperCase());
					this.guiProducto.getTextoDescripcion().setText(v.getDescripcion().toUpperCase());
					this.guiProducto.getTextoPrecio().setText(Float.toString(v.getPrecio()));
					this.guiProducto.getTextoLab().setText(v.getLaboratorio().toUpperCase());
					this.guiProducto.getTextoSerie().setText(v.getSerie().toUpperCase());
	
				}else{/*Inicializacion para modificacion de Antiparasitario*/
					if(p.getTipo().toUpperCase().compareTo("ANTIPARASITARIO")==0){
						Antiparasitario a=controlProducto.obtenerAntiparasitario(idProducto);
						this.guiProducto.getOpcionAntic().setEnabled(false);
						this.guiProducto.getOpcionOtro().setEnabled(false);
						this.guiProducto.getOpcionVacuna().setEnabled(false);
						this.guiProducto.getTextoLab().setEditable(true);
						this.guiProducto.getTextoLab().setBackground(Color.WHITE);
						this.guiProducto.getTextoSerie().setEditable(false);
						this.guiProducto.getTextoSerie().setBackground(new Color(238, 238, 238));
						this.guiProducto.getTextoOtro().setEditable(false);
						this.guiProducto.getTextoOtro().setBackground(new Color(238, 238, 238));
	
						this.guiProducto.getTextoNombre().setText(a.getNombre().toUpperCase());
						this.guiProducto.getTextoDescripcion().setText(a.getDescripcion().toUpperCase());
						this.guiProducto.getTextoPrecio().setText(Float.toString(a.getPrecio()));
						this.guiProducto.getTextoLab().setText(a.getLaboratorio().toUpperCase());
					}else{/*Inicializacion para modificacion de Anticonceptivo*/
						if(p.getTipo().toUpperCase().compareTo("ANTICONCEPTIVO")==0){
							Anticonceptivo a=controlProducto.obtenerAnticonceptivo(idProducto);
							this.guiProducto.getOpcionAntip().setEnabled(false);
							this.guiProducto.getOpcionOtro().setEnabled(false);
							this.guiProducto.getOpcionVacuna().setEnabled(false);
							this.guiProducto.getTextoLab().setEditable(true);
							this.guiProducto.getTextoLab().setBackground(Color.WHITE);
							this.guiProducto.getTextoSerie().setEditable(false);
							this.guiProducto.getTextoSerie().setBackground(new Color(238, 238, 238));
							this.guiProducto.getTextoOtro().setEditable(false);
							this.guiProducto.getTextoOtro().setBackground(new Color(238, 238, 238));
	
							this.guiProducto.getTextoNombre().setText(a.getNombre().toUpperCase());
							this.guiProducto.getTextoDescripcion().setText(a.getDescripcion().toUpperCase());
							this.guiProducto.getTextoPrecio().setText(Float.toString(a.getPrecio()));
							this.guiProducto.getTextoLab().setText(a.getLaboratorio().toUpperCase());
	
						}else{/*Inicializacion para modificacion de otro producto*/
							this.guiProducto.getOpcionVacuna().setEnabled(false);
							this.guiProducto.getOpcionAntic().setEnabled(false);
							this.guiProducto.getOpcionAntip().setEnabled(false);
							this.guiProducto.getTextoLab().setEditable(false);
							this.guiProducto.getTextoLab().setBackground(new Color(238, 238, 238));
							this.guiProducto.getTextoSerie().setEditable(false);
							this.guiProducto.getTextoSerie().setBackground(new Color(238, 238, 238));
							this.guiProducto.getTextoOtro().setEditable(true);
							this.guiProducto.getTextoOtro().setBackground(Color.WHITE);
	
							this.guiProducto.getTextoNombre().setText(p.getNombre());
							this.guiProducto.getTextoDescripcion().setText(p.getDescripcion());
							this.guiProducto.getTextoOtro().setText(p.getTipo());
							this.guiProducto.getTextoPrecio().setText(Float.toString(p.getPrecio()));
						}
					}
				}
	
				this.guiProducto.getBotonAceptar().setEnabled(true);
				this.guiProducto.getBotonCancelar().setEnabled(true);
				this.guiProducto.setListenerButtons(this);	
	
				
			}else{JOptionPane.showMessageDialog(this.guiGestionarProducto, "SELECCIONE ALGUNA FILA");}
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}



	}

	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiProducto.show();
	}

	private void aceptarModificacion(){
		try{
			persistencia.iniciarTransaccion();
			int cant=Integer.parseInt(this.guiProducto.getTextoCantidad().getText());
			/*Modificacion de vacuna*/
			if(this.guiProducto.getOpcionVacuna().isSelected()){
				Vacuna v=controlProducto.obtenerVacuna(idProducto);
				v.setNombre(this.guiProducto.getTextoNombre().getText().toLowerCase());
				v.setDescripcion(this.guiProducto.getTextoDescripcion().getText().toLowerCase());
				v.setLaboratorio(this.guiProducto.getTextoLab().getText().toLowerCase());
				v.setSerie(this.guiProducto.getTextoSerie().getText().toLowerCase());
				v.setTipo("vacuna");
				v.setPrecio(Float.parseFloat(this.guiProducto.getTextoPrecio().getText()));
				v.setCantidad(cant);
				this.tablaProductos.setValueAt(v.getNombre().toUpperCase(), filaReal, 1);
				this.tablaProductos.setValueAt(v.getTipo().toUpperCase(), filaReal, 2);
				this.tablaProductos.setValueAt(v.getDescripcion().toUpperCase(), filaReal, 3);
				this.tablaProductos.setValueAt(v.getPrecio()+"".toUpperCase(), filaReal, 4);
				this.tablaProductos.setValueAt(v.getCantidad()+"".toUpperCase(), filaReal, 5);
	
				this.tablaProductos.fireTableDataChanged();
				this.guiGestionarProducto.getTablaProductos().repaint();
			}else{/*Modificacion Antiparasitario*/
				if(this.guiProducto.getOpcionAntip().isSelected()){
					Antiparasitario a=controlProducto.obtenerAntiparasitario(idProducto);
					a.setNombre(this.guiProducto.getTextoNombre().getText().toLowerCase());
					a.setDescripcion(this.guiProducto.getTextoDescripcion().getText().toLowerCase());
					a.setLaboratorio(this.guiProducto.getTextoLab().getText().toLowerCase());
					a.setTipo("antiparasitario");
					a.setPrecio(Float.parseFloat(this.guiProducto.getTextoPrecio().getText()));
					a.setCantidad(cant);
					this.tablaProductos.setValueAt(a.getNombre().toUpperCase(), filaReal, 1);
					this.tablaProductos.setValueAt(a.getTipo().toUpperCase(), filaReal, 2);
					this.tablaProductos.setValueAt(a.getDescripcion().toUpperCase(), filaReal, 3);
					this.tablaProductos.setValueAt(a.getPrecio()+"".toUpperCase(), filaReal, 4);
					this.tablaProductos.setValueAt(a.getCantidad()+"".toUpperCase(), filaReal, 5);
	
					this.tablaProductos.fireTableDataChanged();
					this.guiGestionarProducto.getTablaProductos().repaint();
				}else{/*Modificacion Anticonceptivo*/
					if(this.guiProducto.getOpcionAntic().isSelected()){
						Anticonceptivo a=controlProducto.obtenerAnticonceptivo(idProducto);
						a.setNombre(this.guiProducto.getTextoNombre().getText().toLowerCase());
						a.setDescripcion(this.guiProducto.getTextoDescripcion().getText().toLowerCase());
						a.setLaboratorio(this.guiProducto.getTextoLab().getText().toLowerCase());
						a.setTipo("anticonceptivo");
						a.setPrecio(Float.parseFloat(this.guiProducto.getTextoPrecio().getText()));
						a.setCantidad(cant);
						this.tablaProductos.setValueAt(a.getNombre().toUpperCase(), filaReal, 1);
						this.tablaProductos.setValueAt(a.getTipo().toUpperCase(), filaReal, 2);
						this.tablaProductos.setValueAt(a.getDescripcion().toUpperCase(), filaReal, 3);
						this.tablaProductos.setValueAt(a.getPrecio()+"".toUpperCase(), filaReal, 4);
						this.tablaProductos.setValueAt(a.getCantidad()+"".toUpperCase(), filaReal, 5);
	
						this.tablaProductos.fireTableDataChanged();
						this.guiGestionarProducto.getTablaProductos().repaint();
					}else{/*Modificacion Otro Producto*/
						Producto p=controlProducto.obtenerProducto(idProducto);
						p.setNombre(this.guiProducto.getTextoNombre().getText().toLowerCase());
						p.setDescripcion(this.guiProducto.getTextoDescripcion().getText().toLowerCase());
						p.setPrecio(Float.parseFloat(this.guiProducto.getTextoPrecio().getText()));
						p.setTipo(this.guiProducto.getTextoOtro().getText().toLowerCase());
						p.setCantidad(cant);
						this.tablaProductos.setValueAt(p.getNombre().toUpperCase(), filaReal, 1);
						this.tablaProductos.setValueAt(p.getTipo().toUpperCase(), filaReal, 2);
						this.tablaProductos.setValueAt(p.getDescripcion().toUpperCase(), filaReal, 3);
						this.tablaProductos.setValueAt(p.getPrecio()+"".toUpperCase(), filaReal, 4);
						this.tablaProductos.setValueAt(p.getCantidad()+"".toUpperCase(), filaReal, 5);
	
						this.tablaProductos.fireTableDataChanged();
						this.guiGestionarProducto.getTablaProductos().repaint();
					}
				}
			}
	
			persistencia.concretarTransaccion();
	
			this.guiProducto.setVisible(false);
			this.guiProducto.dispose();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	public void cancelarModificacion(){
		this.guiProducto.setVisible(false);
		this.guiProducto.dispose();
	}

	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(this.guiProducto.getBotonAceptar() == source){
			this.aceptarModificacion();
		}
		if(this.guiProducto.getBotonCancelar() == source){
			this.cancelarModificacion();
		}

	}

}
