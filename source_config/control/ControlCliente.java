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
import persistencia.dominio.Cliente;


public class ControlCliente {
private Persistencia persistencia;
	
	public ControlCliente(){
		super();
	    persistencia = new Persistencia();
	}
	
	public void guardarCliente(Cliente cliente){
		persistencia.insertarObjeto(cliente);	
	}
	
	public void eliminarCliente(Cliente cliente){
		persistencia.eliminarObjeto(cliente);	
	}
	
	public Cliente obtenerCliente(String dniCliente){
		return (Cliente) persistencia.obtenerElemento(Cliente.class, "dni=='"+dniCliente+"'");
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<Cliente> obtenerClientes(){
		//MLucero
		//return persistencia.obtenerColeccion(Cliente.class);
		return persistencia.obtenerColeccionOrdered(Cliente.class, "apellido ascending");
	}

	public int existeCliente(String dniCliente) {
		return persistencia.existeObjetoClase(Cliente.class, "dni=='"+dniCliente+"'");
	}
}
