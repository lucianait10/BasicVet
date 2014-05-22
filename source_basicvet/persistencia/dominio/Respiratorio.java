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


public class Respiratorio {
	protected int id;
	protected String respiracion;
	protected String tos;
	protected String auscultacion;
	protected String reflejoTusigeno;
	protected Vector<String> observaciones;
	protected FichaClinica ficha;
	
	public Respiratorio(String repiracion, String tos, String auscultacion,
			String reflejoTusigeno, Vector<String> observaciones,FichaClinica ficha) {
		this.respiracion = repiracion;
		this.tos = tos;
		this.auscultacion = auscultacion;
		this.reflejoTusigeno = reflejoTusigeno;
		this.observaciones = observaciones;
		this.ficha = ficha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRepiracion() {
		return respiracion;
	}

	public void setRepiracion(String repiracion) {
		this.respiracion = repiracion;
	}

	public String getTos() {
		return tos;
	}

	public void setTos(String tos) {
		this.tos = tos;
	}

	public String getAuscultacion() {
		return auscultacion;
	}

	public void setAuscultacion(String auscultacion) {
		this.auscultacion = auscultacion;
	}

	public String getReflejoTusigeno() {
		return reflejoTusigeno;
	}

	public void setReflejoTusigeno(String reflejoTusigeno) {
		this.reflejoTusigeno = reflejoTusigeno;
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
		return "Respiratorio [auscultacion=" + auscultacion
				+ ", reflejoTusigeno=" + reflejoTusigeno + ", repiracion="
				+ respiracion + ", tos=" + tos + "]";
	}
}
