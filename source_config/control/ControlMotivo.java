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
import persistencia.dominio.Motivo;
  
public class ControlMotivo {
	private Persistencia persistencia;
		  
	public ControlMotivo(){
		super();
	    persistencia = new Persistencia();
	}
	
	public void guardarMotivo(Motivo Motivo){
		persistencia.insertarObjeto(Motivo);	
	}
	
	public void eliminarMotivo(String motivo){
		Motivo motivoBorrar = (Motivo) persistencia.obtenerElemento(Motivo.class, "nombre=='"+motivo+"'");
		persistencia.eliminarObjeto(motivoBorrar);	
	}
		
	@SuppressWarnings("unchecked")
	public Iterator<Motivo> obtenerMotivos(){
		return persistencia.obtenerColeccion(Motivo.class);
	}
	
	public int existeMotivo(String motivo) {
		return persistencia.existeObjetoClase(Motivo.class, "nombre=='"+motivo+"'");
	}
}