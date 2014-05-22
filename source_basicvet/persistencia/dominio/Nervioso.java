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


public class Nervioso {
	protected int id;
	protected String paralisis;
	protected String convulsiones;
	protected String ataxia;
	protected String reflejos;
	protected String sensibilidad;
	protected String conducta;
	protected String sensorio;
	protected String otros;
	protected Vector<String> observaciones;
	protected FichaClinica ficha;
			
	public Nervioso(String paralisis, String convulsiones, String ataxia,
			String reflejos, String sensibilidad, String conducta,
			String sensorio, String otros, Vector<String> observaciones,FichaClinica ficha) {
		this.paralisis = paralisis;
		this.convulsiones = convulsiones;
		this.ataxia = ataxia;
		this.reflejos = reflejos;
		this.sensibilidad = sensibilidad;
		this.conducta = conducta;
		this.sensorio = sensorio;
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

	public String getParalisis() {
		return paralisis;
	}

	public void setParalisis(String paralisis) {
		this.paralisis = paralisis;
	}

	public String getConvulsiones() {
		return convulsiones;
	}

	public void setConvulsiones(String convulsiones) {
		this.convulsiones = convulsiones;
	}

	public String getAtaxia() {
		return ataxia;
	}

	public void setAtaxia(String ataxia) {
		this.ataxia = ataxia;
	}

	public String getReflejos() {
		return reflejos;
	}

	public void setReflejos(String reflejos) {
		this.reflejos = reflejos;
	}

	public String getSensibilidad() {
		return sensibilidad;
	}

	public void setSensibilidad(String sensibilidad) {
		this.sensibilidad = sensibilidad;
	}

	public String getConducta() {
		return conducta;
	}

	public void setConducta(String conducta) {
		this.conducta = conducta;
	}

	public String getSensorio() {
		return sensorio;
	}

	public void setSensorio(String sensorio) {
		this.sensorio = sensorio;
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
		return "Nervioso [ataxia=" + ataxia + ", conducta=" + conducta
				+ ", convulsiones=" + convulsiones + ", observaciones="
				+ observaciones + ", otros=" + otros + ", paralisis="
				+ paralisis + ", reflejos=" + reflejos + ", sensibilidad="
				+ sensibilidad + ", sensorio=" + sensorio + "]";
	}
}
