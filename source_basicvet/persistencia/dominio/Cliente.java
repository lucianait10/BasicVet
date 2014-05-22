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
  
public class Cliente {
	   
	protected String dni;
	protected String nombre;
	protected String apellido;
	protected String telefono;
	protected String cuil;
	protected boolean eliminado;

	//---------------------------------------------------------------------------------------------------------	
		public Cliente(String dni, String nombre, String apellido,String telefono,String cuil,boolean eliminado){
			this.dni = dni;
			this.nombre = nombre;
			this.apellido = apellido;
			this.telefono = telefono;
			this.cuil= cuil;
			this.eliminado = eliminado;
		}
		
		
		public String toString() {
			return "Persona [apellido=" + apellido + ", cuil=" + cuil + ", dni="
					+ dni + ", nombre=" + nombre + ", telefono=" + telefono + "]";
		}
		//---------------------------------------------------------------------------------------------------------
		public String getDni() {
			return dni;
		}
		public void setDni(String dni) {
			this.dni = dni;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getApellido() {
			return apellido;
		}
		public void setApellido(String apellido) {
			this.apellido = apellido;
		}
		public String getTelefono() {
			return telefono;
		}
		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}
		public String getCuil() {
			return cuil;
		}
		public void setCuil(String cuil) {
			this.cuil = cuil;
		}
		public boolean isEliminado() {
			return eliminado;
		}
		public void setEliminado(boolean eliminado) {
			this.eliminado = eliminado;
		}
	

}
