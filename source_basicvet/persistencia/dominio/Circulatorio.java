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


public class Circulatorio {
	protected int id;
	protected String ritmo;
	protected String ascultacion;
	protected String pulso;
	protected String soplo;
	protected String ecg;
	protected String otros;
	protected Vector<String> observaciones;
	protected FichaClinica ficha;
	
	public Circulatorio(String ritmo, String ascultacion, String pulso,
			String soplo, String ecg, String otros, Vector<String> observaciones, FichaClinica ficha) {
		this.ritmo = ritmo;
		this.ascultacion = ascultacion;
		this.pulso = pulso;
		this.soplo = soplo;
		this.ecg = ecg;
		this.otros = otros;
		this.observaciones = observaciones;
		this.ficha = ficha; 
	}
	
	public Circulatorio(){
		this.ritmo = null;
		this.ascultacion = null;
		this.pulso = null;
		this.soplo = null;
		this.ecg = null;
		this.otros = null;
		this.observaciones = null;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRitmo() {
		return ritmo;
	}

	public void setRitmo(String ritmo) {
		this.ritmo = ritmo;
	}

	public String getAscultacion() {
		return ascultacion;
	}

	public void setAscultacion(String ascultacion) {
		this.ascultacion = ascultacion;
	}

	public String getPulso() {
		return pulso;
	}

	public void setPulso(String pulso) {
		this.pulso = pulso;
	}

	public String getSoplo() {
		return soplo;
	}

	public void setSoplo(String soplo) {
		this.soplo = soplo;
	}

	public String getEcg() {
		return ecg;
	}

	public void setEcg(String ecg) {
		this.ecg = ecg;
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
		return "Circulatorio [ascultacion=" + ascultacion + ", ecg=" + ecg
				+ ", observaciones=" + observaciones + ", otros=" + otros
				+ ", pulso=" + pulso + ", ritmo=" + ritmo + ", soplo=" + soplo
				+ "]";
	}
}
