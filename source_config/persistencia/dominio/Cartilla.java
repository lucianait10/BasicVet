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
  
package persistencia.dominio;
  

import java.util.Collection;
import java.util.Date;
  


public class Cartilla {
	
	private int id;
	private Date fechaCreacion;
	private Collection<Dosis> dosis;
	private Animal animal;
	
	
	public Cartilla(){
		super();
	}
	

	public Cartilla(Date fechaCreacion,Animal a) {
		super();
		this.fechaCreacion = fechaCreacion;
		this.animal=a;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public Animal getAnimal() {
		return animal;
	}


	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	
	public void agregarDosis(Dosis o){
		this.dosis.add(o);		
	}
	
	public Collection<Dosis> getDosis(){
		return this.dosis;
	}

}
