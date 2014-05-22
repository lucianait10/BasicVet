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

import java.util.Date;
    

public class Dosis{	

	private int id;
	private String via;
	private Date fecha;
	private Date fechaProxDosis;
	private float cant=0;
	private Medicamento medicamento;

	public Dosis(){}
	
	public Dosis(Date f,Date fpd,String v,float c,Medicamento med){
		this.fecha=f;
		this.fechaProxDosis=fpd;
		this.via=v;
		this.cant=c;
		this.medicamento=med;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaProxDosis() {
		return fechaProxDosis;
	}

	public void setFechaProxDosis(Date fechaProxDosis) {
		this.fechaProxDosis = fechaProxDosis;
	}

	public float getCant() {
		return cant;
	}

	public void setCant(float cant) {
		this.cant = cant;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	
	public String toString() {
		return "Dosis [cant=" + cant + ", fecha=" + fecha + ", fechaProxDosis="
				+ fechaProxDosis + ", id=" + id + ", medicamento="
				+ medicamento + ", via=" + via + "]";
	}
}