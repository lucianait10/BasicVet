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

package cuGestionarCompra;

import java.util.Iterator;

import persistencia.Persistencia;
import persistencia.dominio.Compra;

public class ControlCompra {
	private Persistencia persistencia;
	
	public ControlCompra(){
		super();
	    persistencia = new Persistencia();
	}
	
	public Compra guardarCompra(Compra compra){
		return (Compra)persistencia.insertarObjeto(compra);	
	}
	
	public void eliminarCompra(Compra compra){
		persistencia.eliminarObjeto(compra);	
	}
	
	public Compra obtenerCompra(int idCompra){
		return (Compra) persistencia.obtenerElemento(Compra.class, "id=="+idCompra);
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<Compra> obtenerCompras(){
		return persistencia.obtenerColeccion(Compra.class);
	}
}
