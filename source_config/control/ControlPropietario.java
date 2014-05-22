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

package control;

import java.util.Iterator;

import persistencia.Persistencia;
import persistencia.dominio.Propietario;

public class ControlPropietario {
	private Persistencia persistencia;
	
	public ControlPropietario(){
		super();
	    persistencia = new Persistencia();
	}
	
	public void guardarPropietario(Propietario propietario){
		persistencia.insertarObjeto(propietario);	
	}
	
	public void eliminarPropietario(Propietario propietario){
		persistencia.eliminarObjeto(propietario);	
	}
	
	public Propietario obtenerPropietario(String idPropietario){
		return (Propietario) persistencia.obtenerElemento(Propietario.class, "dni=='"+idPropietario+"'");
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<Propietario> obtenerPropietarios(){
		//MLucero
		//return persistencia.obtenerColeccion(Propietario.class);
		return persistencia.obtenerColeccionOrdered(Propietario.class, "apellido ascending");
	}

	public int existePropietario(String dniPropietario) {
		return persistencia.existeObjetoClase(Propietario.class, "dni=='"+dniPropietario+"'");
	}
}
