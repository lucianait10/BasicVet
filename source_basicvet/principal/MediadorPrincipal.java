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

package principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

import persistencia.Persistencia;
import persistencia.dominio.BasicVet;
import persistencia.dominio.Cartilla;
import persistencia.dominio.Dosis;
import persistencia.dominio.Turno;
import clasesComunes.ControlBasicVet;
import cuAcercaDe.MediadorAcercaDe;
import cuBackup.MediadorCargarBackup;
import cuBackup.MediadorGuardarBackup;
import cuConfiguracion.MediadorConfiguracion;
import cuGestionarAnimal.MediadorGestionarAnimal;
import cuGestionarCartilla.MediadorGestionarCartilla;
import cuGestionarCliente.MediadorGestionarCliente;
import cuGestionarCompra.MediadorGestionarCompra;
import cuGestionarFichaClinica.MediadorGestionarFichaClinica;
import cuGestionarProducto.MediadorGestionarProducto;
import cuGestionarPropietario.MediadorGestionarPropietario;
import cuGestionarProveedor.MediadorGestionarProveedor;
import cuGestionarTurno.MediadorGestionarTurno;
import cuGestionarUsuario.MediadorGestionarUsuario;
import cuGestionarVenta.MediadorGestionarVenta;
import cuGestionarVeterinario.MediadorGestionarVeterinario;
  
public class MediadorPrincipal implements ActionListener{
	private GUIPrincipal guiPrincipal;
	private MediadorGestionarFichaClinica medGestionarFichaClinica;
	private MediadorGestionarTurno medGestionarTurno;
	private MediadorGestionarCartilla medGestionarCartilla;
	private MediadorGestionarPropietario medGestionarPropietario;
	private MediadorGestionarAnimal medGestionarAnimal;
	private MediadorGestionarVeterinario medGestionarVeterinario;
	private MediadorGestionarProducto medGestionarProducto;
	private MediadorGestionarCompra medGestionarCompra;
	private MediadorGestionarVenta medGestionarVenta;
	private MediadorGestionarCliente medGestionarCliente;
	private DefaultTableModel modeloAlarma = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable (int row, int column){return false;}
	};
	private Persistencia persistencia;
	private Date fecha;
	@SuppressWarnings("unused")
	private MediadorConfiguracion medConfiguracion;
	private MediadorGestionarUsuario medGestionarUsuario;
	private MediadorGestionarProveedor medGestionarProveedor;
	private MediadorAcercaDe mediadorAcercaDe;
	private BasicVet basicVet;
	private ControlBasicVet controlBasic;
	public static String BASE = (new File("")).getAbsolutePath();
	private String PATH_IMAGE = BASE + "/src/imagenes/";
	
	public MediadorPrincipal(){
		super();
		inicializar();
	}

	@SuppressWarnings({ "deprecation", "unchecked"})
	private void inicializar(){
		this.persistencia = new Persistencia();
		controlBasic = new ControlBasicVet();
		this.modeloAlarma.setColumnIdentifiers(new String[]{"ID","ANIMAL","MOTIVO","HORARIO","TELEFONO PROPIETARIO"});
		try{
			persistencia.iniciarTransaccion();
			fecha = new Date();
			int existe = controlBasic.existeBasicVet("1");		
			if(existe==0){
				basicVet = new BasicVet();
				basicVet.setPath(PATH_IMAGE+"imagenVeterinaria.jpg");
				controlBasic.guardarBasicVet(basicVet);
				
			}	
			Collection turnos = persistencia.obtenerObjetosFecha(Turno.class,fecha.getYear()+1900,fecha.getMonth(),fecha.getDate());
			Iterator<Turno> iterTurnos = turnos.iterator();
			while(iterTurnos.hasNext()){
				Turno turno = iterTurnos.next();
				String tel = turno.getAnimal().getPropietario().getTelefono();
				this.modeloAlarma.addRow(new String[]{turno.getId()+"",turno.getAnimal().getNombre().toUpperCase(),turno.getMotivo(),turno.getHora(),tel+""});
			}
			Iterator<Cartilla> iterCartillas=persistencia.obtenerColeccion(Cartilla.class);
			while(iterCartillas.hasNext()){
				Cartilla c=iterCartillas.next();
				Collection dosis=c.getDosis();
				Iterator<Dosis> iterDosis=dosis.iterator();
				while(iterDosis.hasNext()){
					Dosis d=iterDosis.next();
					if(d.getFechaProxDosis().getDate()==fecha.getDate()){
						String tel = c.getAnimal().getPropietario().getTelefono();
						this.modeloAlarma.addRow(new String[]{d.getId()+"",c.getAnimal().getNombre().toUpperCase(),"DOSIS: "+d.getMedicamento().getTipo().toUpperCase(),tel+""});
					}
				}
			}
			persistencia.concretarTransaccion();
			
			this.guiPrincipal = new GUIPrincipal(this.modeloAlarma);
			//persistencia.abrirTransaccion();
			BasicVet basicVet = controlBasic.obtenerBasicVet("1");
			if(basicVet.getNombre()==null){
				this.guiPrincipal.setTitle("BASIC-VET");
			}else{
				this.guiPrincipal.setTitle("BASIC-VET"+" - "+basicVet.getNombre().toUpperCase());
			}
			
			this.guiPrincipal.getBotonGestionarCartilla().setEnabled(true);
			this.guiPrincipal.setListenerButtons(this);
			this.guiPrincipal.getTablaAlarmas();
			this.guiPrincipal.show();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	
	public void actionPerformed(ActionEvent e) {
		if(this.guiPrincipal.getBotonGestionarAnimal()==e.getSource()){
			this.opcionGestionarAnimal();
		}
		if(this.guiPrincipal.getMenuItemGestionarAnimal()==e.getSource()){
			this.opcionGestionarAnimal();
		}
		//--------------------------------------------------------
		if(this.guiPrincipal.getBotonGestionarPropietario()==e.getSource()){
			this.opcionGestionarPropietario();
		}

		if(this.guiPrincipal.getMenuItemGestionarPropietario()==e.getSource()){
			this.opcionGestionarPropietario();
		}
		//--------------------------------------------------------
		if (this.guiPrincipal.getBotonGestionarFichaClinica()==e.getSource()) {
			this.opcionGestionarFichaClinica();
		}		
		if(this.guiPrincipal.getMenuItemGestionarFichaClinica()==e.getSource()){
			this.opcionGestionarFichaClinica();
		}
		//----------------------------------------------------------
		if(this.guiPrincipal.getBotonGestionarCartilla()==e.getSource()){
			this.opcionGestionarCartilla();
		}
		
		if(this.guiPrincipal.getMenuItemGestionarCartilla()==e.getSource()){
			this.opcionGestionarCartilla();
		}
		//----------------------------------------------------------
		if(this.guiPrincipal.getBotonGestionarTurno()==e.getSource()){
			this.opcionGestionarTurno();
		}
		
		if(this.guiPrincipal.getMenuItemGestionarTurno()==e.getSource()){
			this.opcionGestionarTurno();
		}
		//---------------------------------------------------------
		if(this.guiPrincipal.getBotonConfiguracion()==e.getSource()){
			this.opcionConfigurar();
		}
		
		if(this.guiPrincipal.getMenuItemConfiguracion()==e.getSource()){
			this.opcionConfigurar();
		}
		//----------------------------------------------------------
		if(this.guiPrincipal.getBotonGestionarProducto()==e.getSource()){
			this.opcionGestionarProducto();
		}
		
		if(this.guiPrincipal.getMenuItemGestionarProducto()==e.getSource()){
			this.opcionGestionarProducto();
		}
		//-----------------------------------------------------------
		if(this.guiPrincipal.getBotonGestionarCompra()==e.getSource()){
			this.opcionGestionarCompra();
		}
		if(this.guiPrincipal.getMenuItemGestionarCompra()==e.getSource()){
			this.opcionGestionarCompra();
		}
		//------------------------------------------------------------
		if(this.guiPrincipal.getBotonGestionarVenta()==e.getSource()){
			this.opcionGestionarVenta();
		}
		if(this.guiPrincipal.getMenuItemGestionarVenta()==e.getSource()){
			this.opcionGestionarVenta();
		}
		//------------------------------------------------------------
		if(this.guiPrincipal.getBotonGestionarVeterinario()==e.getSource()){
			this.opcionGestionarVeterinario();
		}
		if(this.guiPrincipal.getMenuItemGestionarVeterinario()==e.getSource()){
			this.opcionGestionarVeterinario();
		}
		//-------------------------------------------------------------
		if(this.guiPrincipal.getBotonGestionarProveedor()==e.getSource()){
			this.opcionGestionarProveedor();	
		}
		if(this.guiPrincipal.getMenuItemGestionarProveedor()==e.getSource()){
			this.opcionGestionarProveedor();
		}
		if(this.guiPrincipal.getBotonGestionarCliente()==e.getSource()){
			this.opcionGestionarCliente();
		}
		if(this.guiPrincipal.getMenuItemGestionarCliente()==e.getSource()){
			this.opcionGestionarCliente();
		}
		if(this.guiPrincipal.getMenuItemGestionarUsuario()==e.getSource()){
			this.opcionGestionarUsuario();
		}
		if(this.guiPrincipal.getMenuItemCargarBackup()==e.getSource()){
			MediadorCargarBackup medCargarBackup = new MediadorCargarBackup();
			medCargarBackup.opcionCargarBackup();
		}
		if(this.guiPrincipal.getMenuItemGuardarBackup()==e.getSource()){
			MediadorGuardarBackup medGuardarBackup = new MediadorGuardarBackup();
			medGuardarBackup.opcionGuardarBackup();
		}
		if(this.guiPrincipal.getMenuItemAcerca()==e.getSource()){
			this.opcionAcercaDe();
		}
	}
	

	private void opcionGestionarProveedor(){
		this.medGestionarProveedor = new MediadorGestionarProveedor();
		this.medGestionarProveedor.mostrar();
	}

	private void opcionGestionarUsuario() {
		this.medGestionarUsuario = new MediadorGestionarUsuario();
		this.medGestionarUsuario.mostrar();
	}

	private void opcionGestionarVeterinario() {
		this.medGestionarVeterinario = new MediadorGestionarVeterinario();
		this.medGestionarVeterinario.mostrar();
	}

	private void opcionConfigurar() {
		this.medConfiguracion = new MediadorConfiguracion();
	}

	private void opcionGestionarAnimal() {
		this.medGestionarAnimal = new MediadorGestionarAnimal();
		this.medGestionarAnimal.mostrar();
	}

	private void opcionGestionarPropietario(){
		this.medGestionarPropietario = new MediadorGestionarPropietario();
		this.medGestionarPropietario.mostrar();
	}

	private void opcionGestionarTurno() {
		this.medGestionarTurno = new MediadorGestionarTurno();
		this.medGestionarTurno.mostrar();	
	}

	private void opcionGestionarCartilla() {
		this.medGestionarCartilla=new MediadorGestionarCartilla();
		this.medGestionarCartilla.mostrar();
	}

	private void opcionGestionarFichaClinica() {
		this.medGestionarFichaClinica = new MediadorGestionarFichaClinica();
		this.medGestionarFichaClinica.mostrar();
	}
	
	private void opcionGestionarProducto(){
		this.medGestionarProducto= new MediadorGestionarProducto();
		this.medGestionarProducto.mostrar();
	}
	
	private void opcionGestionarCompra(){
		this.medGestionarCompra=new MediadorGestionarCompra();
		this.medGestionarCompra.mostrar();
	}
	
	private void opcionGestionarVenta(){
		this.medGestionarVenta=new MediadorGestionarVenta();
		this.medGestionarVenta.mostrar();
	}
	
	private void opcionGestionarCliente(){
		this.medGestionarCliente=new MediadorGestionarCliente();
		this.medGestionarCliente.mostrar();
	}
	
	private void opcionAcercaDe(){
		this.mediadorAcercaDe = new MediadorAcercaDe();
		this.mediadorAcercaDe.mostrar();
	}

}
