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
  
package persistencia.dominio;  

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario{
	private String nombre;
	private String password;
	
	public Usuario(String nomUsuario,String password){
		this.nombre = nomUsuario;
		this.password = md5(password);
	}

	public String getNomUsuario() {
		return nombre;
	}

	public void setNomUsuario(String nomUsuario) {
		this.nombre = nomUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = md5(password);
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
					if (hex.length() == 1)
						sb.append('0');
					sb.append(hex);
				}
				r = sb.toString();
			}
		}catch (NoSuchAlgorithmException e){ e.printStackTrace();}
		return r;
	}
}
