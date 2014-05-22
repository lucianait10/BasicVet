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
  

public class ExploracionGeneral {
	protected int id;
	protected String temperatura;
	protected String fc;
	protected String fr;
	protected String estadoGral;
	protected String mucosaOcular;
	protected String mucosaGenital;
	protected String mucosaBucal;
	protected String mucosaOtros;
	protected String hidratacion;
	protected String gangliosLinfaticos;
	protected FichaClinica ficha;
	

	public ExploracionGeneral(String temperatura, String fc, String fr, String estadoGral,
			String mucosaOcular, String mucosaGenital, String mucosaBucal,
			String mucosaOtros, String hidratacion, String gangliosLinfaticos,FichaClinica ficha) {
		this.temperatura = temperatura;
		this.fc = fc;
		this.fr = fr;
		this.estadoGral = estadoGral;
		this.mucosaOcular = mucosaOcular;
		this.mucosaGenital = mucosaGenital;
		this.mucosaBucal = mucosaBucal;
		this.mucosaOtros = mucosaOtros;
		this.hidratacion = hidratacion;
		this.gangliosLinfaticos = gangliosLinfaticos;
		this.ficha = ficha; 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public String getFc() {
		return fc;
	}

	public void setFc(String fc) {
		this.fc = fc;
	}

	public String getFr() {
		return fr;
	}

	public void setFr(String fr) {
		this.fr = fr;
	}

	public String getEstadoGral() {
		return estadoGral;
	}

	public void setEstadoGral(String estadoGral) {
		this.estadoGral = estadoGral;
	}

	public String getMucosaOcular() {
		return mucosaOcular;
	}

	public void setMucosaOcular(String mucosaOcular) {
		this.mucosaOcular = mucosaOcular;
	}

	public String getMucosaGenital() {
		return mucosaGenital;
	}

	public void setMucosaGenital(String mucosaGenital) {
		this.mucosaGenital = mucosaGenital;
	}

	public String getMucosaBucal() {
		return mucosaBucal;
	}

	public void setMucosaBucal(String mucosaBucal) {
		this.mucosaBucal = mucosaBucal;
	}

	public String getMucosaOtros() {
		return mucosaOtros;
	}

	public void setMucosaOtros(String mucosaOtros) {
		this.mucosaOtros = mucosaOtros;
	}

	public String getHidratacion() {
		return hidratacion;
	}

	public void setHidratacion(String hidratacion) {
		this.hidratacion = hidratacion;
	}

	public String getGangliosLinfaticos() {
		return gangliosLinfaticos;
	}

	public void setGangliosLinfaticos(String gangliosLinfaticos) {
		this.gangliosLinfaticos = gangliosLinfaticos;
	}

	public FichaClinica getFicha() {
		return ficha;
	}

	public void setFicha(FichaClinica ficha) {
		this.ficha = ficha;
	}

	
	public String toString() {
		return "ExpGral [estadoGral=" + estadoGral + ", fc=" + fc + ", fr="
				+ fr + ", gangliosLinfaticos=" + gangliosLinfaticos
				+ ", hidratacion=" + hidratacion + ", mucosaBucal="
				+ mucosaBucal + ", mucosaGenital=" + mucosaGenital
				+ ", mucosaOcular=" + mucosaOcular + ", mucosaOtros="
				+ mucosaOtros + ", temperatura=" + temperatura + "]";
	}
}
