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
import persistencia.dominio.Animal;
import persistencia.dominio.Turno;

@SuppressWarnings({ "unchecked"})
public class ControlAnimal {
	private Persistencia persistencia;
	
	public ControlAnimal(){
		super();
	    persistencia = new Persistencia();
	}
	
	public Animal guardarAnimal(Animal animal){
		return (Animal)persistencia.insertarObjeto(animal);	
	}
	
	public void eliminarAnimal(Animal animal){
		persistencia.eliminarObjeto(animal);	
	}
	
	public Animal obtenerAnimal(int idAnimal){
		return (Animal) persistencia.obtenerElemento(Animal.class, "id=="+idAnimal);
	}
	
	public Iterator<Turno> obtenerTurnosDeAnimal(int idAnimal){
		// MLucero
		//return persistencia.obtenerColeccionQuery(Turno.class, "animal.id=="+idAnimal);
		return persistencia.obtenerColeccionQueryOrdered(Turno.class, "animal.id=="+idAnimal,"fecha ascending");
	}
	

	public Iterator<Animal> obtenerAnimalesNro(String nroAnimal){
		return persistencia.obtenerColeccionQuery(Animal.class, "nro=='"+nroAnimal+"'");
	}
	
	public Iterator<Animal> obtenerAnimales(){
		return persistencia.obtenerColeccion(Animal.class);
	}
	
	public int existeAnimal(String nroAnimal) {
		return persistencia.existeObjetoClase(Animal.class, "nro=='"+nroAnimal+"'");
	}
}
