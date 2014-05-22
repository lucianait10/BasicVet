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

package cuGestionarUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import persistencia.Persistencia;
import persistencia.dominio.Usuario;


public class MediadorIngresarUsuario implements ActionListener{
	private GUIUsuario guiUsuario;
	private ControlUsuario controlUsuario;
	private Persistencia persistencia;
	private DefaultTableModel modeloUsuario;
	private GUIGestionarUsuario guiGestionarUsuario;
	
	public MediadorIngresarUsuario(DefaultTableModel modeloUsuario, GUIGestionarUsuario guiGestionarUsuario){
		super();
		this.guiGestionarUsuario = guiGestionarUsuario;
		this.modeloUsuario = modeloUsuario;
		inicializar();
	}
	
	public void mostrar(){
		this.guiUsuario.setVisible(true);
	}

	private void inicializar(){
		this.guiUsuario = new GUIUsuario();
		this.controlUsuario = new ControlUsuario();
		this.persistencia = new Persistencia();
		this.guiUsuario.getBotonModificar().setVisible(false);
		this.guiUsuario.getLabelNuevaContrasena().setText("Repetir Contrseña: ");
		this.guiUsuario.setListenerButtons(this);
	}

	
	public void actionPerformed(ActionEvent e) {
		if(this.guiUsuario.getBotonIngresar()==e.getSource()){
			this.opcionIngresar();
		}
	}

	private void opcionCancelar(){
		this.guiUsuario.setVisible(false);
		this.guiUsuario.dispose();
	}

	@SuppressWarnings("deprecation")
	private void opcionIngresar(){
		try{
			persistencia.iniciarTransaccion();
			String nombre = this.guiUsuario.getCampoNombre().getText().toLowerCase();
			String pass = this.guiUsuario.getCampoContrasena().getText();
			String nuevaPass = this.guiUsuario.getCampoNuevaContrasena().getText();
			int exiteUsuario = controlUsuario.existeUsuario(nombre);
			if(exiteUsuario != 1){
				if(pass.compareTo(nuevaPass)==0){
					Usuario user = new Usuario(nombre,pass);
					controlUsuario.guardarUsuario(user);
					modeloUsuario.addRow(new String[]{user.getNomUsuario().toUpperCase()});
					if(modeloUsuario.getRowCount()>0){
						this.guiGestionarUsuario.getBotonModificarUsuario().setEnabled(true);
						this.guiGestionarUsuario.getBotonEliminarUsuario().setEnabled(true);
					}
					persistencia.concretarTransaccion();
					this.opcionCancelar();
				}else{JOptionPane.showMessageDialog(guiUsuario, "CONTRASEÑA INVALIDA");}
			}else{JOptionPane.showMessageDialog(guiUsuario,"EL USUARIO YA EXISTE");}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
}
