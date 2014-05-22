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

package clasesComunes;

import java.util.Iterator;

import persistencia.Persistencia;
import persistencia.dominio.Raza;


public class ControlRaza {
	private Persistencia persistencia;
	  
	public ControlRaza(){
		super();
	    persistencia = new Persistencia();
	}
	
	public void guardarRaza(Raza raza){
		persistencia.insertarObjeto(raza);	
	}
	
	public void eliminarRaza(String raza){
		Raza razaBorrar = (Raza) persistencia.obtenerElemento(Raza.class, "nombre=='"+raza+"'");
		persistencia.eliminarObjeto(razaBorrar);	
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<Raza> obtenerRaza(){
		//MLucero
		//return persistencia.obtenerColeccion(Raza.class);
		return persistencia.obtenerColeccionOrdered(Raza.class, "nombre ascending");
	}
	
	public int existeRaza(String raza) {
		return persistencia.existeObjetoClase(Raza.class, "nombre=='"+raza+"'");
	}
}
