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
import java.util.Vector;

public class EstudioComplementario{
	protected long id;
	protected Date fecha;
	protected Vector<String> informe;
	protected String path;
	protected String tipo;
	
	public EstudioComplementario(Date fecha ,Vector<String> informe, String path, String tipo) {
		super();
		this.fecha = fecha;
		this.informe = informe;
		this.path = path;
		this.tipo = tipo;
	}
	
	public long getId(){
		return id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Vector<String> getInforme() {
		return informe;
	}

	public void setInforme(Vector<String> informe) {
		this.informe = informe;
	}

	public String getPath(){
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}