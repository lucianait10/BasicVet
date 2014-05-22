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
import java.util.Vector;
  
public class FichaClinica {	
	protected long id;
	protected Vector<String> anamnesis;
	protected String diagnosticoDiferencial;
	protected String diagnosticoPresuntivo;
	protected String pronostico;
	protected String manejoClinicoTerapeutico;
	protected Date fecha;
	protected Collection<EstudioComplementario> estudios;
	
	public FichaClinica( Date fecha, Vector<String> anamnesis2, String diagDif, String diagoPres,
			String pronostico, String manejoCliTer) {
		this.fecha = fecha;
		this.anamnesis = anamnesis2;
		this.diagnosticoDiferencial = diagDif;
		this.diagnosticoPresuntivo = diagoPres;
		this.pronostico = pronostico;
		this.manejoClinicoTerapeutico = manejoCliTer;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Vector<String> getAnamnesis() {
		return anamnesis;
	}
	public void setAnamnesis(Vector<String> anamnesis) {
		this.anamnesis = anamnesis;
	}
	public String getDiagnosticoDiferencial() {
		return diagnosticoDiferencial;
	}
	public void setDiagnosticoDiferencial(String diagnosticoDiferencial) {
		this.diagnosticoDiferencial = diagnosticoDiferencial;
	}
	public String getDiagnosticoPresuntivo() {
		return diagnosticoPresuntivo;
	}
	public void setDiagnosticoPresuntivo(String diagnosticoPresuntivo) {
		this.diagnosticoPresuntivo = diagnosticoPresuntivo;
	}
	public String getPronostico() {
		return pronostico;
	}
	public void setPronostico(String pronostico) {
		this.pronostico = pronostico;
	}
	public String getManejoClinicoTerapeutico() {
		return manejoClinicoTerapeutico;
	}
	public void setManejoClinicoTerapeutico(String manejoClinicoTerapeutico) {
		this.manejoClinicoTerapeutico = manejoClinicoTerapeutico;
	}
	public void agregarEstudioComplementario(EstudioComplementario estComp){
		this.estudios.add(estComp);
	}
	public Collection<EstudioComplementario> obtenerEstudiosComplementarios(){
		return this.estudios;
	}
	
	public String toString() {
		return "FichaClinica [Anamnesis=" + anamnesis + ", animal=" //+ //animal
				+ ", diagnosticoDiferencial=" + diagnosticoDiferencial
				+ ", diagnosticoPresuntivo=" + diagnosticoPresuntivo
				+ ", fecha=" + fecha + ", manejoClinicoTerapeutico="
				+ manejoClinicoTerapeutico + ", pronostico=" + pronostico + "]";
	}
}
