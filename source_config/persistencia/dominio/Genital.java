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
  

public class Genital {
	protected int id;
	protected String machoExp;
	protected String machoSecreciones;
	protected String machoTactoRectal;
	protected String hembraExp;
	protected String hembraSecreciones;
	protected String hembraGlanMamarias;
	protected Vector<String> observaciones;
	protected FichaClinica ficha;
	
	
	public Genital(String machoExp, String machoSecreciones,
			String machoTactoRectal, String hembraExp,
			String hembraSecreciones, String hembraGlanMamarias,
			Vector<String> observaciones,FichaClinica ficha) {
		this.machoExp = machoExp;
		this.machoSecreciones = machoSecreciones;
		this.machoTactoRectal = machoTactoRectal;
		this.hembraExp = hembraExp;
		this.hembraSecreciones = hembraSecreciones;
		this.hembraGlanMamarias = hembraGlanMamarias;
		this.observaciones = observaciones;
		this.ficha = ficha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMachoExp() {
		return machoExp;
	}

	public void setMachoExp(String machoExp) {
		this.machoExp = machoExp;
	}

	public String getMachoSecreciones() {
		return machoSecreciones;
	}

	public void setMachoSecreciones(String machoSecreciones) {
		this.machoSecreciones = machoSecreciones;
	}

	public String getMachoTactoRectal() {
		return machoTactoRectal;
	}

	public void setMachoTactoRectal(String machoTactoRectal) {
		this.machoTactoRectal = machoTactoRectal;
	}

	public String getHembraExp() {
		return hembraExp;
	}

	public void setHembraExp(String hembraExp) {
		this.hembraExp = hembraExp;
	}

	public String getHembraSecreciones() {
		return hembraSecreciones;
	}

	public void setHembraSecreciones(String hembraSecreciones) {
		this.hembraSecreciones = hembraSecreciones;
	}

	public String getHembraGlanMamarias() {
		return hembraGlanMamarias;
	}

	public void setHembraGlanMamarias(String hembraGlanMamarias) {
		this.hembraGlanMamarias = hembraGlanMamarias;
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
		return "Genital [hembraExp=" + hembraExp + ", hembraGlanMamarias="
				+ hembraGlanMamarias + ", hembraSecreciones="
				+ hembraSecreciones + ", machoExp=" + machoExp
				+ ", machoSecreciones=" + machoSecreciones
				+ ", machoTactoRectal=" + machoTactoRectal + ", observaciones="
				+ observaciones + "]";
	}
}
