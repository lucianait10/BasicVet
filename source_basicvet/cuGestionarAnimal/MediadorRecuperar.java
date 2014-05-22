package cuGestionarAnimal;

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
import persistencia.dominio.Animal;


public class MediadorRecuperar implements ActionListener {
	
	private GUIRecuperarAnimal selAnimalEliminado;
	private DefaultTableModel modelo = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable (int row, int column){return false;}
	};  
	private String num;
	private Object source;  
	private Persistencia persistencia = new Persistencia();
	private ControlAnimal controlAnimal;
	private MediadorGeneral mediador;
	
	public MediadorRecuperar(String numero, MediadorGeneral med) {
		super();
		this.mediador=med;
		this.num=numero.toLowerCase();
		inicializar();
	}
	
	@SuppressWarnings({ "deprecation" })
	public void mostrar(){
		this.selAnimalEliminado.show();
	}
	
	private void inicializar() {
		this.controlAnimal = new ControlAnimal();
		this.modelo.setColumnIdentifiers(new String[]{"ID","NUMERO","ESTADO","NOMBRE","RAZA","ESPECIE","EDAD"});
		this.selAnimalEliminado = new GUIRecuperarAnimal(this.modelo);
		this.selAnimalEliminado.getBotonAceptar().setEnabled(true);
		this.selAnimalEliminado.getBotonCancelar().setEnabled(true);
		try{
			persistencia.iniciarTransaccion();
			Iterator<Animal> iterAnimales = controlAnimal.obtenerAnimalesNro(num);
			Animal animal;
			while(iterAnimales.hasNext()){
				animal = iterAnimales.next();
				if(animal.isEliminado()){
					this.modelo.addRow(new String[]{animal.getId()+"",animal.getNro().toUpperCase(),"ELIMINADO",animal.getNombre().toUpperCase(),animal.getRaza().toUpperCase(),animal.getEspecie().toUpperCase(),animal.getEdad()+""});
				}else{
					this.modelo.addRow(new String[]{animal.getId()+"",animal.getNro().toUpperCase(),"NO ELIMINADO",animal.getNombre().toUpperCase(),animal.getRaza().toUpperCase(),animal.getEspecie().toUpperCase(),animal.getEdad()+""});
				}
			}
			persistencia.concretarTransaccion();
			this.selAnimalEliminado.setListenerButtons(this);
			this.selAnimalEliminado.setListenerMouse(new MouseAdapter(){
				
				public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2)opcionAceptar();}
			});
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	private void opcionCancelar() {
		this.selAnimalEliminado.setVisible(false);
		this.selAnimalEliminado.dispose();
	}

	private void opcionAceptar() {
		JTable jt = this.selAnimalEliminado.getTablaAnimales();
		int fila = jt.getSelectedRow();
		try{
		if(fila >= 0){
			if(((String)this.modelo.getValueAt(fila, 2)).compareTo("ELIMINADO")==0){
				int opcion = JOptionPane.showConfirmDialog(this.selAnimalEliminado,"CONFIRMA RECUPERACION", "ANIMAL ELIMINADO", JOptionPane.YES_NO_OPTION);
	        	if(opcion == JOptionPane.YES_OPTION){
	        		persistencia.iniciarTransaccion();
	        		int idAnimalBuscado = Integer.parseInt((String) this.modelo.getValueAt(fila, 0));
	        		
	        		Animal animal = controlAnimal.obtenerAnimal(idAnimalBuscado);
	        		animal.setEliminado(false);
	        		if(animal.getPropietario().isEliminado()){
	        			animal.getPropietario().setEliminado(false);
	        		}
	        		persistencia.concretarTransaccion();
	        		mediador.setAnimalBuscado(idAnimalBuscado);
	        		this.selAnimalEliminado.setVisible(false);
	            	this.selAnimalEliminado.dispose();
	        	}
	        	
			}else{JOptionPane.showMessageDialog(selAnimalEliminado,"ES UN ANIMAL EXISTENTE NO ES NECESARIO RECUPERAR, PRESIONE CANCELAR");}
		}
		
		else{JOptionPane.showMessageDialog(selAnimalEliminado,"SELECCIONE UN ANIMAL ELIMINADO");}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	



	
	public void actionPerformed(ActionEvent e) {
		source = e.getSource();
		if(this.selAnimalEliminado.getBotonAceptar()==source){
			this.opcionAceptar();
		}
		if(this.selAnimalEliminado.getBotonCancelar()==source){
			this.opcionCancelar();
		}
		
	}
	

}
