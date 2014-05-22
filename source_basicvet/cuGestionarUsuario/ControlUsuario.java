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

import java.util.Iterator;

import persistencia.Persistencia;
import persistencia.dominio.Usuario;

public class ControlUsuario {
	private Persistencia persistencia;
	
	public ControlUsuario(){
		super();
	    persistencia = new Persistencia();
	}
	
	public void guardarUsuario(Usuario usuario){
		persistencia.insertarObjeto(usuario);	
	}
	
	public void eliminarUsuario(Usuario usuario){
		persistencia.eliminarObjeto(usuario);	
	}
	
	public Usuario obtenerUsuario(String nomUsuario){
		return (Usuario) persistencia.obtenerElemento(Usuario.class, "nombre=='"+nomUsuario+"'");
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<Usuario> obtenerUsuarios(){
		//Mlucero
		//return persistencia.obtenerColeccion(Usuario.class);
		return persistencia.obtenerColeccionOrdered(Usuario.class, "nombre ascending");
	}

	public int existeUsuario(String nomUsuario) {
		return persistencia.existeObjetoClase(Usuario.class, "nombre=='"+nomUsuario+"'");
	}
}
