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


public class Piel {
	protected int id;
	protected String tipoLesion;
	protected String forma;
	protected String ubicacion;
	protected String simetrica;
	protected String olor;
	protected String prurito;
	protected String mantoPiloso;
	protected String ectoparasitos;
	protected String oidoIzq;
	protected String oidoDer;
	protected Vector<String> observaciones;
	protected FichaClinica ficha;
	
	public Piel(String tipoLesion, String forma, String ubicacion,
			String simetrica, String olor, String prurito, String mantoPiloso,
			String ectoparasitos, String oidoIzq, String oidoDer,
			Vector<String> observaciones,FichaClinica ficha) {
		this.tipoLesion = tipoLesion;
		this.forma = forma;
		this.ubicacion = ubicacion;
		this.simetrica = simetrica;
		this.olor = olor;
		this.prurito = prurito;
		this.mantoPiloso = mantoPiloso;
		this.ectoparasitos = ectoparasitos;
		this.oidoIzq = oidoIzq;
		this.oidoDer = oidoDer;
		this.observaciones = observaciones;
		this.ficha = ficha;
	}	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipoLesion() {
		return tipoLesion;
	}

	public void setTipoLesion(String tipoLesion) {
		this.tipoLesion = tipoLesion;
	}

	public String getForma() {
		return forma;
	}

	public void setForma(String forma) {
		this.forma = forma;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getSimetrica() {
		return simetrica;
	}

	public void setSimetrica(String simetrica) {
		this.simetrica = simetrica;
	}

	public String getOlor() {
		return olor;
	}

	public void setOlor(String olor) {
		this.olor = olor;
	}

	public String getPrurito() {
		return prurito;
	}

	public void setPrurito(String prurito) {
		this.prurito = prurito;
	}

	public String getMantoPiloso() {
		return mantoPiloso;
	}

	public void setMantoPiloso(String mantoPiloso) {
		this.mantoPiloso = mantoPiloso;
	}

	public String getEctoparasitos() {
		return ectoparasitos;
	}

	public void setEctoparasitos(String ectoparasitos) {
		this.ectoparasitos = ectoparasitos;
	}

	public String getOidoIzq() {
		return oidoIzq;
	}

	public void setOidoIzq(String oidoIzq) {
		this.oidoIzq = oidoIzq;
	}

	public String getOidoDer() {
		return oidoDer;
	}

	public void setOidoDer(String oidoDer) {
		this.oidoDer = oidoDer;
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
		return "Piel [Olor=" + olor + ", ectoparasitos=" + ectoparasitos
				+ ", forma=" + forma + ", id=" + id + ", mantoPiloso="
				+ mantoPiloso + ", observaciones=" + observaciones
				+ ", oidoDer=" + oidoDer + ", oidoIzq=" + oidoIzq
				+ ", prurito=" + prurito + ", simetrica=" + simetrica
				+ ", tipoLesion=" + tipoLesion + ", ubicacion=" + ubicacion
				+ "]";
	}
}