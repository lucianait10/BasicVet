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

 
public class Turno implements Comparable<Turno>{
	protected int id;
	protected Date fecha;
	protected String hora;
	protected String motivo;
	protected int idAnimal;
	protected Animal animal;
	protected Collection<Horario> horarios;

	public Turno(Date fe, String h, String motivo,int animal){
		this.fecha = fe;
		this.hora = h;
		this.motivo = motivo;  
		this.idAnimal=animal;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	public void agregarHorario(Horario h){
		this.horarios.add(h);
	}
	
	public Collection<Horario> obtenerHorarios(){
		return this.horarios;
	}
	
	public int getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(int animal) {
		this.idAnimal = animal;
	}
		
	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public int compareTo(Turno o){
		Integer turnoClass = this.obtenerHorarios().iterator().next().getFila();
		Integer turnoParam = o.obtenerHorarios().iterator().next().getFila();
		return (turnoClass).compareTo(turnoParam);
	}

	
	public String toString() {
		return this.quitarNumeros(this.animal.getNombre()).toUpperCase();
		
	}
	
	//-----------------------------------------------------------------------------------------------
	public String quitarNumeros(String numero){
		int i =0;
		boolean salir = false;
		while(i<numero.length() && !salir){
			if(!(numero.charAt(i)=='0' || numero.charAt(i)=='1' || numero.charAt(i)=='2' || numero.charAt(i)=='3' || numero.charAt(i)=='4' || numero.charAt(i)=='5' || numero.charAt(i)=='6' || numero.charAt(i)=='7' || numero.charAt(i)=='8' || numero.charAt(i)=='9')){
				salir = true;
				i--;
			}
			i++;
		}	
		return numero.substring(i);
	}

}