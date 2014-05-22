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


public class Digestivo {	
	protected int id;
	protected String cavidadOral;
	protected String palpacionAbd;
	protected String vomitos;
	protected String disfagia;
	protected String materiaFecal;
	protected Vector<String> observaciones;
	protected FichaClinica ficha;
	
	
	public Digestivo(String cavidadOral, String palpacionAbd, String vomitos,
			String disfagia, String materiaFecal, Vector<String> observaciones,FichaClinica ficha) {
		this.cavidadOral = cavidadOral;
		this.palpacionAbd = palpacionAbd;
		this.vomitos = vomitos;
		this.disfagia = disfagia;
		this.materiaFecal = materiaFecal;
		this.observaciones = observaciones;
		this.ficha = ficha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCavidadOral() {
		return cavidadOral;
	}

	public void setCavidadOral(String cavidadOral) {
		this.cavidadOral = cavidadOral;
	}

	public String getPalpacionAbd() {
		return palpacionAbd;
	}

	public void setPalpacionAbd(String palpacionAbd) {
		this.palpacionAbd = palpacionAbd;
	}

	public String getVomitos() {
		return vomitos;
	}

	public void setVomitos(String vomitos) {
		this.vomitos = vomitos;
	}

	public String getDisfagia() {
		return disfagia;
	}

	public void setDisfagia(String disfagia) {
		this.disfagia = disfagia;
	}

	public String getMateriaFecal() {
		return materiaFecal;
	}

	public void setMateriaFecal(String materiaFecal) {
		this.materiaFecal = materiaFecal;
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
		return "Digestivo [cavidadOral=" + cavidadOral + ", disfagia="
				+ disfagia + ", materiaFecal=" + materiaFecal
				+ ", observaciones=" + observaciones + ", palpacionAbd="
				+ palpacionAbd + ", vomitos=" + vomitos + "]";
	}
}
