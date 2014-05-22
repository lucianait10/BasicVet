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


public class Urinario {
	protected int id;
	protected String vejiga;
	protected String orina;
	protected String otros;
	protected Vector<String> observaciones;
	protected FichaClinica ficha;
	
	
	public Urinario(String vejiga, String orina, String otros,
			Vector<String> observaciones,FichaClinica ficha) {
		this.vejiga = vejiga;
		this.orina = orina;
		this.otros = otros;
		this.observaciones = observaciones;
		this.ficha = ficha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVejiga() {
		return vejiga;
	}

	public void setVejiga(String vejiga) {
		this.vejiga = vejiga;
	}

	public String getOrina() {
		return orina;
	}

	public void setOrina(String orina) {
		this.orina = orina;
	}

	public String getOtros() {
		return otros;
	}

	public void setOtros(String otros) {
		this.otros = otros;
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
		return "Urinario [observaciones=" + observaciones + ", orina=" + orina
				+ ", otros=" + otros + ", vejiga=" + vejiga + "]";
	}
}
