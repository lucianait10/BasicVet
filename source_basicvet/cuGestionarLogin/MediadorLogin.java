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

package cuGestionarLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

import persistencia.Persistencia;
import persistencia.dominio.Usuario;
import principal.MediadorPrincipal;
import cuGestionarUsuario.ControlUsuario;

public class MediadorLogin implements ActionListener{
	private Persistencia persistencia;
	private ControlUsuario controlUsuario;
	private GUILogin guiLogin;
	
	@SuppressWarnings("deprecation")
	public MediadorLogin(){
		this.persistencia = new Persistencia();
		this.controlUsuario = new ControlUsuario();
		this.guiLogin = new GUILogin();
		this.guiLogin.setActionListener(this);
		this.guiLogin.show();
	}
	
	
	public void actionPerformed(ActionEvent e){
		if(this.guiLogin.getBotonIngresar()==e.getSource()){
			this.opcionIngresar();
		}
		if(this.guiLogin.getBotonCancelar()==e.getSource()){
			this.opcionCancelar();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void opcionIngresar(){
		String nomUsuario = this.guiLogin.getCampoNombre().getText();
		String passwordEscrita = this.guiLogin.getCampoContrasena().getText();
		try{
			persistencia.iniciarTransaccion();
			int existeUsuario = controlUsuario.existeUsuario(nomUsuario);
			if(existeUsuario==1){
				Usuario usuario = controlUsuario.obtenerUsuario(nomUsuario);
				String passwordUsuario = usuario.getPassword();
				if(md5(passwordEscrita).compareTo(passwordUsuario)==0){
					this.guiLogin.setVisible(false);
					this.guiLogin.dispose();
					persistencia.concretarTransaccion();
		    		@SuppressWarnings("unused")
					MediadorPrincipal med = new MediadorPrincipal();
				}else JOptionPane.showMessageDialog(this.guiLogin, "Nombre de usuario o password invalido");
			}else JOptionPane.showMessageDialog(this.guiLogin, "Nombre de usuario o password invalido");
			
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	private void opcionCancelar(){
		this.guiLogin.setVisible(false);
		this.guiLogin.dispose();
	}

	public String md5(String s){
		String r = null;
		try {
			if(s!=null){
				MessageDigest algorithm =MessageDigest.getInstance("MD5");
				algorithm.reset();
				algorithm.update(s.getBytes());
				byte bytes[] = algorithm.digest();
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < bytes.length; i++) {
					String hex = Integer.toHexString(0xff & bytes[i]);
					if (hex.length() == 1)sb.append('0');
					sb.append(hex);
				}
				r = sb.toString();
			}
		}catch (NoSuchAlgorithmException e){e.printStackTrace();}
		return r;
	}
}
