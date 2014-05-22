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

package cuGestionarCartilla;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import mediador.MediadorGeneral;
import persistencia.Persistencia;
import persistencia.dominio.Animal;
import persistencia.dominio.Anticonceptivo;
import persistencia.dominio.Antiparasitario;
import persistencia.dominio.BasicVet;
import persistencia.dominio.Cartilla;
import persistencia.dominio.Dosis;
import persistencia.dominio.Vacuna;
import clasesComunes.ControlBasicVet;
import cuGestionarAnimal.ControlAnimal;
import cuGestionarAnimal.MediadorBuscarAnimal;
import cuGestionarCartilla.report.ReporteCartilla;

@SuppressWarnings({ "unchecked"})
public class MediadorGestionarCartilla extends MediadorGeneral implements ActionListener{

	private GUIGestionarCartilla guiGestionarCartilla;
	private Persistencia persistencia;
	private ControlCartilla controlCartilla;
	private ControlAnimal controlAnimal;
	private DefaultTableModel tablaVac=new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable (int row, int column){return false;}
	};
	private DefaultTableModel tablaAntip=new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable (int row, int column){return false;}
	};
	private DefaultTableModel tablaAntic=new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable (int row, int column){return false;}
	};
	private int idCartilla;
	private ControlBasicVet controlBasicVet;


	public MediadorGestionarCartilla(){
		super();
		inicializar();
	}

	@SuppressWarnings("deprecation")
	public void mostrar() {
		this.guiGestionarCartilla.show();
	}

	private void inicializar() {
		this.persistencia= new Persistencia();
		this.controlBasicVet = new ControlBasicVet();
		this.controlCartilla=new ControlCartilla();
		this.controlAnimal=new ControlAnimal();
		this.guiGestionarCartilla= new GUIGestionarCartilla();
		this.guiGestionarCartilla.getBotonVer().setEnabled(true);
		this.guiGestionarCartilla.getBotonCrearCartilla().setEnabled(true);
		this.guiGestionarCartilla.getBotonEliminarCartilla().setEnabled(true);
		this.guiGestionarCartilla.getBotonBuscar().setEnabled(true);
		this.guiGestionarCartilla.getBotonAgregarDosis().setEnabled(false);
		this.guiGestionarCartilla.getBotonEliminarDosis().setEnabled(false);
		this.guiGestionarCartilla.getBotonModificarDosis().setEnabled(false);
		this.guiGestionarCartilla.getBotonReporte().setEnabled(false);
		this.guiGestionarCartilla.getBotonCrearCartilla().setEnabled(false);
		this.guiGestionarCartilla.getBotonEliminarCartilla().setEnabled(false);
		this.guiGestionarCartilla.getBotonVer().setEnabled(false);
		this.guiGestionarCartilla.setListenerButtons(this);
		inicPnlVac();
		inicPnlAntip();
		inicPnlAntic();
	}

	@SuppressWarnings("deprecation")
	public void verCartilla(){
		try{
			if(this.guiGestionarCartilla.getTextoIdAnimal()!=null &&  this.guiGestionarCartilla.getTextoNombreAnimal()!=null){
				String id=this.guiGestionarCartilla.getTextoIdAnimal().getText();
				persistencia.iniciarTransaccion();
				if(controlCartilla.existeCartillaAnimal(Integer.parseInt(id))==1){
					Cartilla c=controlCartilla.obtenerCartillaAnimal(Integer.parseInt(id));
					idCartilla=c.getId();
					this.guiGestionarCartilla.getTextoIdCartilla().setText(Integer.toString(idCartilla).toUpperCase());
					String fecha=c.getFechaCreacion().getDate()+"/"+(c.getFechaCreacion().getMonth()+1)+"/"+(c.getFechaCreacion().getYear()+1900);
					this.guiGestionarCartilla.getTextoFecha().setText(fecha);
					persistencia.concretarTransaccion();
					inicPnlVac();
					inicPnlAntip();
					inicPnlAntic();
					this.guiGestionarCartilla.getBotonAgregarDosis().setEnabled(true);
					this.guiGestionarCartilla.getBotonEliminarDosis().setEnabled(true);
					this.guiGestionarCartilla.getBotonModificarDosis().setEnabled(true);
					this.guiGestionarCartilla.getBotonReporte().setEnabled(true);
					this.guiGestionarCartilla.getBotonEliminarCartilla().setEnabled(true);
					
				}else{JOptionPane.showMessageDialog(guiGestionarCartilla,"EL ANIMAL NO POSEE CARTILLA");
					  this.guiGestionarCartilla.getBotonAgregarDosis().setEnabled(false);
					  this.guiGestionarCartilla.getBotonEliminarDosis().setEnabled(false);
					  this.guiGestionarCartilla.getBotonModificarDosis().setEnabled(false);
					  this.guiGestionarCartilla.getBotonReporte().setEnabled(false);}
			}else{JOptionPane.showMessageDialog(guiGestionarCartilla,"NO HAY ANIMAL SELECCIONADO");
				  this.guiGestionarCartilla.getBotonAgregarDosis().setEnabled(false);
				  this.guiGestionarCartilla.getBotonEliminarDosis().setEnabled(false);
				  this.guiGestionarCartilla.getBotonModificarDosis().setEnabled(false);
				  this.guiGestionarCartilla.getBotonReporte().setEnabled(false);}
			}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
		
	}

	@SuppressWarnings({ "deprecation" })
	public void inicPnlVac(){
		try{
			persistencia.iniciarTransaccion();
			tablaVac.setRowCount(0);
			tablaVac.setColumnIdentifiers(new String[]{"ID","FECHA","PROX DOSIS","NOMBRE","VIA","TIPO","LABORATORIO","SERIE","CANTIDAD"});
			if(controlCartilla.existeCartilla(idCartilla)==1){
				Cartilla c=controlCartilla.obtenerCartilla(idCartilla);
				if(c.getDosis()!=null){
					Iterator<Dosis> iter=c.getDosis().iterator();
					while (iter.hasNext()){
						Dosis d = (Dosis)iter.next();     
						if(d.getMedicamento().getTipo().toLowerCase().compareTo("vacuna")==0){
							String id = (Integer.toString(d.getId()));
							String fecha = d.getFecha().getDate()+"/"+(d.getFecha().getMonth()+1)+"/"+(d.getFecha().getYear()+1900);
							String fechaProxDosis = d.getFechaProxDosis().getDate()+"/"+(d.getFechaProxDosis().getMonth()+1)+"/"+(d.getFechaProxDosis().getYear()+1900);
							String cant=Float.toString(d.getCant());
							String via = d.getVia().toUpperCase();
							Vacuna v=(Vacuna)d.getMedicamento();
							String nombre = v.getNombre().toUpperCase();
							String tipo = v.getTipo().toUpperCase();
							String lab = v.getLaboratorio().toUpperCase();
							String serie =v.getSerie().toUpperCase();
							tablaVac.addRow(new String[] {id,fecha,fechaProxDosis,nombre,via,tipo,lab,serie,cant});
						}
					}
				}
				
				
				tablaVac.fireTableDataChanged();
			}
			PanelVacuna pv= new PanelVacuna(tablaVac);
			guiGestionarCartilla.getPanelPestanias().setComponentAt(0,pv);
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
		
	}

	public void agregarVacuna(){
		try{
		//persistencia.iniciarTransaccion();
			if(controlCartilla.existeCartilla(idCartilla)==1){
				//persistencia.concretarTransaccion();
				MediadorAltaDosisVacuna medIngresar = new MediadorAltaDosisVacuna(idCartilla, tablaVac);
				medIngresar.mostrar();
				this.tablaVac.fireTableDataChanged();
			}else{JOptionPane.showMessageDialog(this.guiGestionarCartilla,"CARTILLA INEXISTENTE");}
		
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			//persistencia.deshacerTransaccion();
		}

	}


	public void modificarVacuna(){
			int fila = this.guiGestionarCartilla.getVacunas().getTablaVacuna().getSelectedRow();
			if(fila>=0){
				MediadorModificarDosisVacuna medModificar = new MediadorModificarDosisVacuna(tablaVac,guiGestionarCartilla);
				medModificar.mostrar();
				this.tablaVac.fireTableDataChanged();
			}
			else{JOptionPane.showMessageDialog(this.guiGestionarCartilla, "SELECCIONE ALGUNA FILA");}
	}

	public void eliminarVacuna(){
		try{
			JTable jt = this.guiGestionarCartilla.getVacunas().getTablaVacuna();
			int fila = jt.getSelectedRow();
			int idDosisVac=-1;
			if(fila>=0){
				int filaReal = this.guiGestionarCartilla.getVacunas().getTablaVacuna().convertRowIndexToModel(fila);
				idDosisVac = Integer.parseInt((String) this.guiGestionarCartilla.getVacunas().getTablaVacuna().getValueAt(filaReal,0));
				int opcion = JOptionPane.showConfirmDialog(this.guiGestionarCartilla, "DESEA ELIMINAR LA VACUNA "+idDosisVac, "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);
				if(opcion == JOptionPane.YES_OPTION){
					persistencia.iniciarTransaccion();
					Dosis v=controlCartilla.obtenerDosis(idDosisVac);
					controlCartilla.eliminarDosis(v);
					this.tablaVac.removeRow(fila);
					this.tablaVac.fireTableDataChanged();
					persistencia.concretarTransaccion();
				}
			}else{JOptionPane.showMessageDialog(this.guiGestionarCartilla, "SELECCIONE ALGUNA FILA");}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	@SuppressWarnings({ "deprecation"})
	public void inicPnlAntip(){
		try{
			persistencia.iniciarTransaccion();
			tablaAntip.setRowCount(0);
			tablaAntip.setColumnIdentifiers(new String[]{"ID","FECHA","PROX DOSIS","NOMBRE","VIA","TIPO","CANTIDAD"});
			if(controlCartilla.existeCartilla(idCartilla)==1){				
				Cartilla c=controlCartilla.obtenerCartilla(idCartilla);
				if(c.getDosis()!=null){
					Iterator iter=c.getDosis().iterator();
					while (iter.hasNext()){
						Dosis d = (Dosis)iter.next();
						if(d.getMedicamento().getTipo().toLowerCase().compareTo("antiparasitario")==0){
							String id = (Integer.toString(d.getId()));
							String fecha = d.getFecha().getDate()+"/"+(d.getFecha().getMonth()+1)+"/"+(d.getFecha().getYear()+1900);
							String fechaProxDosis = d.getFechaProxDosis().getDate()+"/"+(d.getFechaProxDosis().getMonth()+1)+"/"+(d.getFechaProxDosis().getYear()+1900);
							String cant = Float.toString(d.getCant());
							String via = d.getVia().toUpperCase();
							Antiparasitario a=(Antiparasitario)d.getMedicamento();
							String nombre = a.getNombre().toUpperCase();
							String tipo= a.getTipo().toUpperCase();
							tablaAntip.addRow(new String[]{id,fecha,fechaProxDosis,nombre,via,tipo,cant});
						}
					}
				}
				
				tablaAntip.fireTableDataChanged();
			}
			
			PanelAntiparasitario pa= new PanelAntiparasitario(tablaAntip);
			guiGestionarCartilla.getPanelPestanias().setComponentAt(1,pa);
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	public void agregarAntiparasitario(){
		try{
			//persistencia.iniciarTransaccion();
			if(controlCartilla.existeCartilla(idCartilla)==1){
				//persistencia.concretarTransaccion();
				MediadorAltaDosisAntiparasitario medIngresar = new MediadorAltaDosisAntiparasitario(idCartilla,tablaAntip);
				medIngresar.mostrar();
				this.tablaAntip.fireTableDataChanged();
			}else{JOptionPane.showMessageDialog(this.guiGestionarCartilla,"CARTILLA INEXISTENTE");}
			
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			//persistencia.deshacerTransaccion();
		}
	}


	public void modificarAntiparasitario(){
		int fila = this.guiGestionarCartilla.getAntiparasitarios().getTablaAntiparas().getSelectedRow();
		if(fila>=0){		
			MediadorModificarDosisAntiparasitario medModificar = new MediadorModificarDosisAntiparasitario(tablaAntip,guiGestionarCartilla);
			medModificar.mostrar();
			this.tablaAntip.fireTableDataChanged();
		}
		else{JOptionPane.showMessageDialog(this.guiGestionarCartilla, "SELECCIONE ALGUNA FILA");}
	}

	
	public void eliminarAntiparasitario(){
		try{
		int fila = this.guiGestionarCartilla.getAntiparasitarios().getTablaAntiparas().getSelectedRow();
		int idDosisAntip=-1;
		if(fila>=0){
			int filaReal = this.guiGestionarCartilla.getAntiparasitarios().getTablaAntiparas().convertRowIndexToModel(fila);
			idDosisAntip = Integer.parseInt((String) this.guiGestionarCartilla.getAntiparasitarios().getTablaAntiparas().getValueAt(filaReal,0));
			int opcion = JOptionPane.showConfirmDialog(this.guiGestionarCartilla, "DESEA ELIMINAR EL ANTIPARASITARIO "+idDosisAntip, "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);
			if(opcion == JOptionPane.YES_OPTION){
				persistencia.iniciarTransaccion();
				Dosis a=controlCartilla.obtenerDosis(idDosisAntip);
				controlCartilla.eliminarDosis(a);

				this.tablaAntip.removeRow(fila);
				this.tablaAntip.fireTableDataChanged();
				
				persistencia.concretarTransaccion();
			}
		}else{JOptionPane.showMessageDialog(this.guiGestionarCartilla, "SELECCIONE ALGUNA FILA");}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	@SuppressWarnings({ "deprecation" })
	public void inicPnlAntic(){
		try{
			persistencia.iniciarTransaccion();
			tablaAntic.setRowCount(0);
			tablaAntic.setColumnIdentifiers(new String[]{"ID","FECHA","PROX DOSIS","NOMBRE","VIA","TIPO","LABORATORIO","CANTIDAD"});
			if(controlCartilla.existeCartilla(idCartilla)==1){
				Cartilla c=controlCartilla.obtenerCartilla(idCartilla);
				if(c.getDosis()!=null){
					Iterator iter=c.getDosis().iterator();
					while (iter.hasNext()){
						Dosis d = (Dosis)iter.next();
						if(d.getMedicamento().getTipo().toLowerCase().compareTo("anticonceptivo")==0){
							String id = (Integer.toString(d.getId()));
							String fecha = d.getFecha().getDate()+"/"+(d.getFecha().getMonth()+1)+"/"+(d.getFecha().getYear()+1900);
							String fechaProxDosis = d.getFechaProxDosis().getDate()+"/"+(d.getFechaProxDosis().getMonth()+1)+"/"+(d.getFechaProxDosis().getYear()+1900);
							String via = d.getVia().toUpperCase();
							String cant = Float.toString(d.getCant());
							Anticonceptivo a=(Anticonceptivo)d.getMedicamento();
							String nombre = a.getNombre().toUpperCase();
							String tipo = a.getTipo().toUpperCase();
							String lab = a.getLaboratorio().toUpperCase();
							tablaAntic.addRow(new String[]{id,fecha,fechaProxDosis,nombre,via,tipo,lab,cant});  
						}
					}
				}
				
				tablaAntic.fireTableDataChanged();
			}
			
			PanelAnticonceptivo pa= new PanelAnticonceptivo(tablaAntic);
			guiGestionarCartilla.getPanelPestanias().setComponentAt(2,pa);
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	public void agregarAnticonceptivo(){
		try{
			if(controlCartilla.existeCartilla(idCartilla)==1){
				MediadorAltaDosisAnticonceptivo medIngresar = new MediadorAltaDosisAnticonceptivo(idCartilla,tablaAntic);
				medIngresar.mostrar();
				this.tablaAntic.fireTableDataChanged();
			}else{JOptionPane.showMessageDialog(this.guiGestionarCartilla,"CARTILLA INEXISTENTE");}
			
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			//persistencia.deshacerTransaccion();
		}
	}

	
	public void modificarAnticonceptivo(){
		int fila = this.guiGestionarCartilla.getAnticonceptivos().getTablaAnticonc().getSelectedRow();
		if(fila>=0){		
			MediadorModificarDosisAnticonceptivo medModificar = new MediadorModificarDosisAnticonceptivo(tablaAntic,guiGestionarCartilla);
			medModificar.mostrar();
			this.tablaAntic.fireTableDataChanged();
		}
		else{JOptionPane.showMessageDialog(this.guiGestionarCartilla, "SELECCIONE ALGUNA FILA");}
	}

	public void eliminarAnticonceptivo(){
		try{
			int fila = this.guiGestionarCartilla.getAnticonceptivos().getTablaAnticonc().getSelectedRow();
			int idDosisAntic=-1;
			if(fila>=0){
				int filaReal = this.guiGestionarCartilla.getAnticonceptivos().getTablaAnticonc().convertRowIndexToModel(fila);
				idDosisAntic = Integer.parseInt((String) this.guiGestionarCartilla.getAnticonceptivos().getTablaAnticonc().getValueAt(filaReal,0));
				int opcion = JOptionPane.showConfirmDialog(this.guiGestionarCartilla, "DESEA ELIMINAR EL ANTICONCEPTIVO "+idDosisAntic, "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);
				if(opcion == JOptionPane.YES_OPTION){
					persistencia.iniciarTransaccion();
					Dosis a=controlCartilla.obtenerDosis(idDosisAntic);
					controlCartilla.eliminarDosis(a);
					
					this.tablaAntic.removeRow(fila);
					this.tablaAntic.fireTableDataChanged();
					
					persistencia.concretarTransaccion();
				}
			}else{JOptionPane.showMessageDialog(this.guiGestionarCartilla, "SELECCIONE ALGUNA FILA");}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	
	
	@SuppressWarnings("deprecation")
	public void crearCartilla(){
		try{
			if(this.guiGestionarCartilla.getTextoIdAnimal()!=null &&  this.guiGestionarCartilla.getTextoNombreAnimal()!=null){
				String id=this.guiGestionarCartilla.getTextoIdAnimal().getText();
				persistencia.iniciarTransaccion();
				if((controlCartilla.existeCartillaAnimal(Integer.parseInt(id))==0)){				
					Cartilla c=new Cartilla();
					controlAnimal= new ControlAnimal();
					Animal a=controlAnimal.obtenerAnimal(Integer.parseInt(id));
					Date fechaCreacion=new Date();
					c.setAnimal(a);
					c.setFechaCreacion(fechaCreacion);
					controlCartilla.guardarCartilla(c);
					persistencia.concretarTransaccion();
					this.guiGestionarCartilla.getBotonAgregarDosis().setEnabled(true);
					this.guiGestionarCartilla.getBotonEliminarDosis().setEnabled(true);
					this.guiGestionarCartilla.getBotonModificarDosis().setEnabled(true);
					this.guiGestionarCartilla.getBotonReporte().setEnabled(true);
					this.guiGestionarCartilla.getBotonEliminarCartilla().setEnabled(true);
					this.guiGestionarCartilla.getBotonVer().setEnabled(true);
					this.guiGestionarCartilla.getTextoIdCartilla().setText(c.getId()+"");
					String fecha=c.getFechaCreacion().getDate()+"/"+(c.getFechaCreacion().getMonth()+1)+"/"+(c.getFechaCreacion().getYear()+1900);
					this.guiGestionarCartilla.getTextoFecha().setText(fecha);
					JOptionPane.showMessageDialog(guiGestionarCartilla,"CARTILLA CREADA");
					
					//this.verCartilla();
					
				}else{
					JOptionPane.showMessageDialog(guiGestionarCartilla,"EL ANIMAL YA POSEE CARTILLA");
				}
				//persistencia.concretarTransaccion();
				
			}else{
				JOptionPane.showMessageDialog(guiGestionarCartilla,"NO HAY ANIMAL SELECCIONADO");
			}
			
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	public void eliminarCartilla(){
		try{
			persistencia.iniciarTransaccion();
			int id=Integer.parseInt(this.guiGestionarCartilla.getTextoIdCartilla().getText());
			if(controlCartilla.existeCartilla(id)==1){
				Cartilla c = controlCartilla.obtenerCartilla(id);
				int opcion = JOptionPane.showConfirmDialog(this.guiGestionarCartilla, "DESEA ELIMINAR LA CARTILLA "+id, "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);
				if(opcion == JOptionPane.YES_OPTION){
					c.getDosis().clear();
					controlCartilla.eliminarCartilla(c);					
					this.guiGestionarCartilla.getTextoIdCartilla().setText(null);
					this.guiGestionarCartilla.getTextoFecha().setText(null);
					
					tablaVac.setRowCount(0);
					tablaAntip.setRowCount(0);
					tablaAntic.setRowCount(0);
					this.guiGestionarCartilla.getBotonAgregarDosis().setEnabled(false);
					this.guiGestionarCartilla.getBotonModificarDosis().setEnabled(false);
					this.guiGestionarCartilla.getBotonEliminarDosis().setEnabled(false);
					this.guiGestionarCartilla.getBotonReporte().setEnabled(false);
					this.guiGestionarCartilla.getBotonCrearCartilla().setEnabled(true);
					this.guiGestionarCartilla.getBotonEliminarCartilla().setEnabled(false);
					this.guiGestionarCartilla.getBotonVer().setEnabled(true);
				}
			}else{
				JOptionPane.showMessageDialog(guiGestionarCartilla,"NO HAY CARTILLA SELECCIONADA");
			}
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}


	
	
	public void setAnimalBuscado(int id){
		try{
			persistencia.iniciarTransaccion();
			Animal a=controlAnimal.obtenerAnimal(id);
			this.guiGestionarCartilla.getTextoIdAnimal().setText(a.getId()+"");
			this.guiGestionarCartilla.getTextoNombreAnimal().setText(a.getNombre().toUpperCase());
			/**Inicializa la interfaz para gestionar la cartilla del animal*/
			this.guiGestionarCartilla.getTextoIdCartilla().setText(null);
			this.guiGestionarCartilla.getTextoFecha().setText(null);
			this.guiGestionarCartilla.getBotonCrearCartilla().setEnabled(true);
			this.guiGestionarCartilla.getBotonVer().setEnabled(true);
			this.guiGestionarCartilla.getBotonEliminarCartilla().setEnabled(false);
			this.guiGestionarCartilla.getBotonReporte().setEnabled(false);
			this.guiGestionarCartilla.getBotonAgregarDosis().setEnabled(false);
			this.guiGestionarCartilla.getBotonModificarDosis().setEnabled(false);
			this.guiGestionarCartilla.getBotonEliminarDosis().setEnabled(false);
			idCartilla=-1;
			persistencia.concretarTransaccion();
			this.inicPnlVac();
			this.inicPnlAntip();
			this.inicPnlAntic();
			
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
		
	}


	public void buscarAnimal(){
		MediadorBuscarAnimal medBuscarAnimal= new MediadorBuscarAnimal(this);
		medBuscarAnimal.mostrar();
	}

	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(this.guiGestionarCartilla.getBotonVer() == source){
			this.verCartilla();
		}
		if(this.guiGestionarCartilla.getBotonCrearCartilla() == source){
			this.crearCartilla();
		}
		if(this.guiGestionarCartilla.getBotonEliminarCartilla() == source){
			this.eliminarCartilla();
		}
		if(this.guiGestionarCartilla.getBotonBuscar() == source){
			this.buscarAnimal();
		}
		if(this.guiGestionarCartilla.getBotonAgregarDosis() == source && this.guiGestionarCartilla.getPanelPestanias().getSelectedIndex()==0){
			this.agregarVacuna();//ver que los agregar no corroboran que haya una cartilla seleccionada
		}
		if(this.guiGestionarCartilla.getBotonAgregarDosis() == source && this.guiGestionarCartilla.getPanelPestanias().getSelectedIndex()==1){
			this.agregarAntiparasitario();
		}
		if(this.guiGestionarCartilla.getBotonAgregarDosis() == source && this.guiGestionarCartilla.getPanelPestanias().getSelectedIndex()==2){
			this.agregarAnticonceptivo();
		}
		if(this.guiGestionarCartilla.getBotonEliminarDosis() == source && this.guiGestionarCartilla.getPanelPestanias().getSelectedIndex()==0){
			this.eliminarVacuna();
		}
		if(this.guiGestionarCartilla.getBotonEliminarDosis() == source && this.guiGestionarCartilla.getPanelPestanias().getSelectedIndex()==1){
			this.eliminarAntiparasitario();
		}
		if(this.guiGestionarCartilla.getBotonEliminarDosis() == source && this.guiGestionarCartilla.getPanelPestanias().getSelectedIndex()==2){
			this.eliminarAnticonceptivo();
		}
		if(this.guiGestionarCartilla.getBotonModificarDosis() == source && this.guiGestionarCartilla.getPanelPestanias().getSelectedIndex()==0){
			this.modificarVacuna();
		}
		if(this.guiGestionarCartilla.getBotonModificarDosis() == source && this.guiGestionarCartilla.getPanelPestanias().getSelectedIndex()==1){
			this.modificarAntiparasitario();
		}
		if(this.guiGestionarCartilla.getBotonModificarDosis() == source && this.guiGestionarCartilla.getPanelPestanias().getSelectedIndex()==2){
			this.modificarAnticonceptivo();
		}
		if(this.guiGestionarCartilla.getBotonReporte()== source && this.guiGestionarCartilla.getPanelPestanias().getSelectedIndex()==0){
			this.opcionReporte(tablaVac,"VACUNAS");
		}
		if(this.guiGestionarCartilla.getBotonReporte()== source && this.guiGestionarCartilla.getPanelPestanias().getSelectedIndex()==1){
			this.opcionReporte(tablaAntip,"ANTIPARASITARIOS");
		}
		if(this.guiGestionarCartilla.getBotonReporte()== source && this.guiGestionarCartilla.getPanelPestanias().getSelectedIndex()==2){
			this.opcionReporte(tablaAntic,"ANTICONCEPTIVOS");
		}
	}

	private void opcionReporte(DefaultTableModel tabla, String tipoStr) {
		int numFila = tabla.getRowCount();
		Object[][] datos = new Object[numFila][5];
		for(int i=0;i<numFila;i++){
			datos[i][0] = tabla.getValueAt(i, 0);
			datos[i][1] = tabla.getValueAt(i, 1);
			datos[i][2] = tabla.getValueAt(i, 2);
			datos[i][3] = tabla.getValueAt(i, 3);
			datos[i][4] = tabla.getValueAt(i, 4);
		}
		guiGestionarCartilla.setVisible(false);
		ReporteCartilla viewReport = new ReporteCartilla(guiGestionarCartilla);
		try{
			persistencia.iniciarTransaccion();
			BasicVet bv = controlBasicVet.obtenerBasicVet("1");
			viewReport.viewFicha(datos,tipoStr,bv.getNombre(),bv.getDireccion(),bv.getTelefono()+"",bv.getCuil());
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
		
	}

}
