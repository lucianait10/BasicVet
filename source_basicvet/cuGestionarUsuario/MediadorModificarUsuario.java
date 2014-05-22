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

import persistencia.Persistencia;
import persistencia.dominio.Usuario;

public class MediadorModificarUsuario implements ActionListener{
	private GUIUsuario guiUsuario;
	private Persistencia persistencia;
	private ControlUsuario controlUsuario;
	private String nomUser;
	
	public MediadorModificarUsuario(String nomUser){
		super();
		this.nomUser = nomUser;
		inicializar();
	}
	
	@SuppressWarnings("deprecation")
	public void mostrar() {
		this.guiUsuario.show();
	}
	
	private void inicializar(){
		this.persistencia = new Persistencia();
		this.guiUsuario = new GUIUsuario();
		this.guiUsuario.getBotonIngresar().setVisible(false);
		this.guiUsuario.getCampoNombre().setEditable(false);
		this.controlUsuario = new ControlUsuario();
		try{
			persistencia.iniciarTransaccion();
			Usuario user = controlUsuario.obtenerUsuario(this.nomUser.toLowerCase());
			this.guiUsuario.getCampoNombre().setText(user.getNomUsuario());
			this.guiUsuario.getCampoContrasena().setText("");
			this.guiUsuario.getCampoNuevaContrasena().setText("");
			persistencia.concretarTransaccion();
			this.guiUsuario.setListenerButtons(this);
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	
	public void actionPerformed(ActionEvent e){
		if(this.guiUsuario.getBotonModificar()==e.getSource()){
			this.opcionModificar();
		}
	}

	@SuppressWarnings("deprecation")
	private void opcionModificar(){
		try{
			persistencia.iniciarTransaccion();
			String password = this.guiUsuario.getCampoContrasena().getText();
			String newPassword = this.guiUsuario.getCampoNuevaContrasena().getText();
			if(password.compareTo(newPassword)==0){
				Usuario user = controlUsuario.obtenerUsuario(this.nomUser.toLowerCase());
				user.setPassword(this.guiUsuario.getCampoContrasena().getText());
				persistencia.concretarTransaccion();
				this.opcionCancelar();
			}else{JOptionPane.showMessageDialog(guiUsuario, "LAS CONTRASEÑAS SON DISTINTAS");}
			
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	private void opcionCancelar() {
		this.guiUsuario.setVisible(false);
		this.guiUsuario.dispose();
	}
}
