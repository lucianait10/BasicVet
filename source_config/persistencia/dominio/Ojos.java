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
  
import java.util.Vector;


public class Ojos {
	protected int id;
	protected String izquierdo;
	protected String derecho;
	protected Vector<String> observaciones;
	protected FichaClinica ficha;
	
	
	public Ojos(String izquierdo, String derecho, Vector<String> observaciones,FichaClinica ficha) {	
		this.izquierdo = izquierdo;
		this.derecho = derecho;
		this.observaciones = observaciones;
		this.ficha = ficha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIzquierdo() {
		return izquierdo;
	}

	public void setIzquierdo(String izquierdo) {
		this.izquierdo = izquierdo;
	}

	public String getDerecho() {
		return derecho;
	}

	public void setDerecho(String derecho) {
		this.derecho = derecho;
	}

	public Vector<String> getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(Vector<String> observaciones) {
		this.observaciones = observaciones;
	}

	public FichaClinica getFicha() {
		return ficha;
	}

	public void setFicha(FichaClinica ficha) {
		this.ficha = ficha;
	}

	
	public String toString() {
		return "Ojos [derecho=" + derecho + ", izquierdo=" + izquierdo
				+ ", observaciones=" + observaciones + "]";
	}
}
