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


public class Venta {

	private int id;
	private Cliente cliente;
	private Producto prodVenta;
	private Date fechaVenta;
	private int cantidadVenta;
	private float totalVenta;
	private float precioUnitario;
	
	public Venta() {
		super();
	}
	
	public Venta(Date f,Cliente c,Producto p,int cant,float total,float pu) {
		this.fechaVenta=f;
		this.cliente=c;
		this.prodVenta=p;
		this.cantidadVenta=cant;
		this.totalVenta=total;
		this.precioUnitario=pu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Producto getProductoVenta() {
		return prodVenta;
	}

	public void setProductoVenta(Producto prod) {
		this.prodVenta = prod;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fecha) {
		this.fechaVenta = fecha;
	}

	public int getCantidadVenta() {
		return cantidadVenta;
	}

	public void setCantidadVenta(int cantidadVenta) {
		this.cantidadVenta = cantidadVenta;
	}

	public float getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(float totalVenta) {
		this.totalVenta = totalVenta;
	}

	public Producto getProdVenta() {
		return prodVenta;
	}

	public void setProdVenta(Producto prodVenta) {
		this.prodVenta = prodVenta;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	
	
	

}
