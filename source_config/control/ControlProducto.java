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
import persistencia.dominio.Anticonceptivo;
import persistencia.dominio.Antiparasitario;
import persistencia.dominio.Producto;
import persistencia.dominio.Vacuna;

public class ControlProducto {
	private Persistencia persistencia;

	public ControlProducto(){
		super();
		persistencia = new Persistencia();
	}

	public Anticonceptivo guardarAnticonceptivo(Anticonceptivo anticonceptivo){
		return (Anticonceptivo)persistencia.insertarObjeto(anticonceptivo);	
	}

	public void eliminarAnticonceptivo(Anticonceptivo anticonceptivo){
		persistencia.eliminarObjeto(anticonceptivo);	
	}

	public Anticonceptivo obtenerAnticonceptivo(int idAnticonceptivo){
		return (Anticonceptivo) persistencia.obtenerElemento(Anticonceptivo.class, "id=="+idAnticonceptivo);
	}

	@SuppressWarnings("unchecked")
	public Iterator<Anticonceptivo> obtenerAnticonceptivos(){
		return persistencia.obtenerColeccion(Anticonceptivo.class);
	}

	public int existeAnticonceptivo(int idAnticonceptivo) {
		return persistencia.existeObjetoClase(Anticonceptivo.class, "id=="+idAnticonceptivo);
	}


	/********************************************************************************************/


	public Producto guardarProducto(Producto producto){
		return (Producto)persistencia.insertarObjeto(producto);	
	}

	public void eliminarProducto(Producto producto){
		persistencia.eliminarObjeto(producto);	
	}

	public Producto obtenerProducto(int idProducto){
		return (Producto) persistencia.obtenerElemento(Producto.class, "id=="+idProducto);
	}

	@SuppressWarnings("unchecked")
	public Iterator<Producto> obtenerProductos(){
		//Mlucero
		//return persistencia.obtenerColeccion(Producto.class);
		return persistencia.obtenerColeccionOrdered(Producto.class, "nombre ascending");
	}

	public int existeProducto(int idProducto) {
		return persistencia.existeObjetoClase(Producto.class, "id=="+idProducto);
	}

	/********************************************************************************************/

	public Antiparasitario guardarAntiparasitario(Antiparasitario antiparasitario){
		return (Antiparasitario)persistencia.insertarObjeto(antiparasitario);	
	}

	public void eliminarAntiparasitario(Antiparasitario antiparasitario){
		persistencia.eliminarObjeto(antiparasitario);	
	}

	public Antiparasitario obtenerAntiparasitario(int idAntiparasitario){
		return (Antiparasitario) persistencia.obtenerElemento(Antiparasitario.class, "id=="+idAntiparasitario);
	}

	@SuppressWarnings("unchecked")
	public Iterator<Antiparasitario> obtenerAntiparasitarios(){
		return persistencia.obtenerColeccion(Antiparasitario.class);
	}

	public int existeAntiparasitario(int idAntiparasitario) {
		return persistencia.existeObjetoClase(Antiparasitario.class, "id=="+idAntiparasitario);
	}

	/********************************************************************************************/

	public Vacuna guardarVacuna(Vacuna vacuna){
		return (Vacuna)persistencia.insertarObjeto(vacuna);	
	}

	public void eliminarVacuna(Vacuna vacuna){
		persistencia.eliminarObjeto(vacuna);	
	}

	public Vacuna obtenerVacuna(int idVacuna){
		return (Vacuna) persistencia.obtenerElemento(Vacuna.class, "id=="+idVacuna);
	}

	@SuppressWarnings("unchecked")
	public Iterator<Vacuna> obtenerVacunas(){
		return persistencia.obtenerColeccion(Vacuna.class);
	}

	public int existeVacuna(int idVacuna) {
		return persistencia.existeObjetoClase(Vacuna.class, "id=="+idVacuna);
	}
}
