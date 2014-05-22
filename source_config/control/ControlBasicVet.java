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
import persistencia.dominio.BasicVet;
  
public class ControlBasicVet {
	private Persistencia persistencia;
	
	public ControlBasicVet(){
		super();
	    persistencia = new Persistencia();
	}
	
	public void guardarBasicVet(BasicVet basicVet){
		persistencia.insertarObjeto(basicVet);
	}
	
	public void eliminarBasicVet(BasicVet basicVet){
		persistencia.eliminarObjeto(basicVet);
	}
	
	public BasicVet obtenerBasicVet(String idBasicVet){
		return (BasicVet) persistencia.obtenerElemento(BasicVet.class, "id=="+idBasicVet);
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<BasicVet> obtenerBasicVets(){
		return persistencia.obtenerColeccion(BasicVet.class);
	}

	public int existeBasicVet(String dniBasicVet) {
		return persistencia.existeObjetoClase(BasicVet.class, "id=="+dniBasicVet);
	}
}
