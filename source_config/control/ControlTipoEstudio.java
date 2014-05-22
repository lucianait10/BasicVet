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
import persistencia.dominio.TipoEstudio;

public class ControlTipoEstudio{
	private Persistencia persistencia;
	  
	public ControlTipoEstudio(){
		super();
	    persistencia = new Persistencia();
	}
	
	public void guardarTipoEstudio(TipoEstudio tipoEstudio){
		persistencia.insertarObjeto(tipoEstudio);	
	}
	
	public void eliminarTipoEstudio(String tipoEstudio){
		TipoEstudio tipoEstudioBorrar = (TipoEstudio) persistencia.obtenerElemento(TipoEstudio.class, "tipo=='"+tipoEstudio+"'");
		persistencia.eliminarObjeto(tipoEstudioBorrar);	
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<TipoEstudio> obtenerTipoEstudios(){
		return persistencia.obtenerColeccion(TipoEstudio.class);
	}

	@SuppressWarnings("unchecked")
	public Iterator<TipoEstudio> obtenerEstudiosComplementarios() {
		return persistencia.obtenerColeccion(TipoEstudio.class);
	}
	
	public int existeTipoEstudio(String tipoEstudio) {
		return persistencia.existeObjetoClase(TipoEstudio.class, "tipo=='"+tipoEstudio+"'");
	}
}
