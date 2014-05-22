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

public class Animal { 

	protected int id;
	protected String nro;  
	protected String nombre;
	protected String raza;
	protected int edad;
	protected String especie;  
	protected Date fechaNac;
	protected String sexo;
	protected Propietario propietario;
	protected Collection<FichaClinica> fichas;
	protected Collection<Turno> turnos;
	protected boolean eliminado;
	
	public Animal(String id,String n,int e,Date fn,String r,String esp,String s){
		this.nombre= n;
		this.nro = id;
		this.raza= r;
		this.edad = e;
		this.especie=esp;
		this.fechaNac=fn;
		this.sexo=s;
		this.eliminado = false;
	}
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNro() {
		return nro;
	}
	public void setNro(String nro) {
		this.nro = nro;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void agregarFicha(FichaClinica o){
		this.fichas.add(o);		
	}
	public Collection<FichaClinica> obtenerFichas() {
		return this.fichas;
	}
	
	public void agregarTurno(Turno o){
		this.turnos.add(o);		
	}
	public Collection<Turno> obtenerTurnos() {
		return this.turnos;
	}
	
	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	
	public String toString() {
		return "Animal [edad=" + edad + ", fichas=" + fichas + ", nombre="
				+ nombre + ", nro=" + nro + ", raza=" + raza + ", turnos="
				+ turnos + "]";
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
}