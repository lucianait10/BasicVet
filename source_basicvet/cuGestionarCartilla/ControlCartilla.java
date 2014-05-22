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

package cuGestionarCartilla;

import java.util.Iterator;

import persistencia.Persistencia;
import persistencia.dominio.Cartilla;
import persistencia.dominio.Dosis;

public class ControlCartilla {
	private Persistencia persistencia;

	public ControlCartilla(){
		super();
		persistencia = new Persistencia();
	}

	public Cartilla guardarCartilla(Cartilla cartilla){
		return (Cartilla)persistencia.insertarObjeto(cartilla);	
	}

	public void eliminarCartilla(Cartilla cartilla){
		persistencia.eliminarObjeto(cartilla);	
	}

	public Cartilla obtenerCartilla(int idCartilla){
		return (Cartilla) persistencia.obtenerElemento(Cartilla.class, "id=="+idCartilla);
	}

	@SuppressWarnings("unchecked")
	public Iterator<Cartilla> obtenerCartillas(){
		return persistencia.obtenerColeccion(Cartilla.class);
	}

	public int existeCartilla(int idCartilla) {
		return persistencia.existeObjetoClase(Cartilla.class, "id=="+idCartilla);
	}
	
	public int existeCartillaAnimal(int idAnimal) {
		return persistencia.existeObjetoClase(Cartilla.class,"animal.id=="+idAnimal);
	}
	
	public Cartilla obtenerCartillaAnimal(int idAnimal){
		return (Cartilla)persistencia.obtenerElemento(Cartilla.class,"animal.id=="+idAnimal);
	}
	
	/********************************************************************************************/

	public Dosis guardarDosis(Dosis dosis){
		return (Dosis)persistencia.insertarObjeto(dosis);	
	}

	public void eliminarDosis(Dosis dosis){
		persistencia.eliminarObjeto(dosis);	
	}

	public Dosis obtenerDosis(int idDosis){
		return (Dosis) persistencia.obtenerElemento(Dosis.class, "id=="+idDosis);
	}

	@SuppressWarnings("unchecked")
	public Iterator<Dosis> obtenerDosis(){
		return persistencia.obtenerColeccion(Dosis.class);
	}

	public int existeDosis(int idDosis) {
		return persistencia.existeObjetoClase(Dosis.class, "id=="+idDosis);
	}
}
