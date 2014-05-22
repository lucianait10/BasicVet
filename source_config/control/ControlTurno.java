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

import java.util.Collection;
import java.util.Date;

import persistencia.Persistencia;
import persistencia.dominio.Turno;

public class ControlTurno {
	private Persistencia persistencia;
	
	public ControlTurno(){
		super();
	    persistencia = new Persistencia();
	}
	
	public Turno guardarTurno(Turno turno){
		return (Turno) persistencia.insertarObjeto(turno);	
	}
	
	public void eliminarTurno(Turno turno){
		persistencia.eliminarObjeto(turno);	
	}
	
	public Turno obtenerTurno(int idTurno){
		return (Turno) persistencia.obtenerElemento(Turno.class, "id=="+idTurno);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public Collection obtenerTurnosFecha(Date fecha){
		return persistencia.obtenerObjetosFecha(Turno.class,fecha.getYear()+1900,fecha.getMonth(),fecha.getDate());
	}
}
