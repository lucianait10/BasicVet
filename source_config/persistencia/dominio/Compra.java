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

public class Compra {

	private int id;
	private float totalCompra;
	private float precioUnitario;
	private Date fecha;
	private Proveedor proveedor;
	private Producto prod;
	private int cantidadCompra;
	
	
	public Compra(){
		super();
	}
	
	public Compra(float pt,float pu,int cantCompra,Date f,Proveedor prov,Producto prod){
		this.totalCompra=pt;
		this.cantidadCompra=cantCompra;
		this.proveedor=prov;
		this.prod=prod;
		this.fecha=f;
		this.precioUnitario=pu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public float getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(float totalCompra) {
		this.totalCompra = totalCompra;
	}
	

	public int getCantidadCompra() {
		return cantidadCompra;
	}

	public void setCantidadCompra(int cantidadCompra) {
		this.cantidadCompra = cantidadCompra;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}


	public Producto getProd() {
		return prod;
	}

	public void setProd(Producto prod) {
		this.prod = prod;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	
	
	
	
	
}
