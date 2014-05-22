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

import java.io.File;
import java.util.Iterator;

import persistencia.Persistencia;
import persistencia.dominio.EstudioComplementario;

public class ControlEstudioComplementario {
	private Persistencia persistencia;
	  
	public ControlEstudioComplementario(){
		super();
	    persistencia = new Persistencia();
	}
	
	public void guardarEstudioComplementario(EstudioComplementario estudioComplementario){
		persistencia.insertarObjeto(estudioComplementario);	
	}
	
	public EstudioComplementario obtenerEstudioComplementario(long estComp){
		return (EstudioComplementario) persistencia.obtenerElemento(EstudioComplementario.class, "id=="+estComp);		
	}
	
	public void eliminarEstudioComplementario(long estudioComplementario){
		EstudioComplementario estudioComplementarioBorrar = (EstudioComplementario) persistencia.obtenerElemento(EstudioComplementario.class, "id=="+estudioComplementario);
		File f = new File(estudioComplementarioBorrar.getPath());
		f.delete();
		persistencia.eliminarObjeto(estudioComplementarioBorrar);	
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<EstudioComplementario> obtenerEstudiosComplementarios(){
		return persistencia.obtenerColeccion(EstudioComplementario.class);
	}
}
