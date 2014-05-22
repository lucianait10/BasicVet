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
import persistencia.dominio.Veterinario;

public class ControlVeterinario {
private Persistencia persistencia;
	
	public ControlVeterinario(){
		super();
	    persistencia = new Persistencia();
	}
	
	public void guardarVeterinario(Veterinario veterinario){
		persistencia.insertarObjeto(veterinario);	
	}
	
	public void eliminarVeterinario(Veterinario veterinario){
		persistencia.eliminarObjeto(veterinario);	
	}
	
	public Veterinario obtenerVeterinario(String idVeterinario){
		return (Veterinario) persistencia.obtenerElemento(Veterinario.class, "dni=='"+idVeterinario+"'");
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<Veterinario> obtenerVeterinarios(){
		//return persistencia.obtenerColeccion(Veterinario.class);
		return persistencia.obtenerColeccionOrdered(Veterinario.class, "apellido ascending");
	}

	public int existeVeterinario(String dniVeterinario) {
		return persistencia.existeObjetoClase(Veterinario.class, "dni=='"+dniVeterinario+"'");
	}
}
