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
import persistencia.dominio.Venta;

public class ControlVenta {
	private Persistencia persistencia;
	
	public ControlVenta(){
		super();
	    persistencia = new Persistencia();
	}
	
	public Venta guardarVenta(Venta venta){
		return (Venta)persistencia.insertarObjeto(venta);	
	}
	
	public void eliminarVenta(Venta venta){
		persistencia.eliminarObjeto(venta);	
	}
	
	public Venta obtenerVenta(int idVenta){
		return (Venta) persistencia.obtenerElemento(Venta.class, "id=="+idVenta);
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<Venta> obtenerVentas(){
		return persistencia.obtenerColeccion(Venta.class);
	}
}
