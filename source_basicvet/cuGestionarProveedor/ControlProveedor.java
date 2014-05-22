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

package cuGestionarProveedor;

import java.util.Iterator;

import persistencia.Persistencia;
import persistencia.dominio.Proveedor;


public class ControlProveedor {
private Persistencia persistencia;
	
	public ControlProveedor(){
		super();
	    persistencia = new Persistencia();
	}
	
	public void guardarProveedor(Proveedor proveedor){
		persistencia.insertarObjeto(proveedor);	
	}
	
	public void eliminarProveedor(Proveedor proveedor){
		persistencia.eliminarObjeto(proveedor);	
	}
	
	public Proveedor obtenerProveedor(String idProveedor){
		return (Proveedor) persistencia.obtenerElemento(Proveedor.class, "dni=='"+idProveedor+"'");
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<Proveedor> obtenerProveedores(){
		//Mlucero
		//return persistencia.obtenerColeccion(Proveedor.class);
		return persistencia.obtenerColeccionOrdered(Proveedor.class, "apellido ascending");
	}

	public int existeProveedor(String dniProveedor) {
		return persistencia.existeObjetoClase(Proveedor.class, "dni=='"+dniProveedor+"'");
	}
}
