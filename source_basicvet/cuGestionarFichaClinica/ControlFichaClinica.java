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

package cuGestionarFichaClinica;

import persistencia.Persistencia;
import persistencia.dominio.Circulatorio;
import persistencia.dominio.Digestivo;
import persistencia.dominio.ExploracionGeneral;
import persistencia.dominio.FichaClinica;
import persistencia.dominio.Genital;
import persistencia.dominio.Locomotor;
import persistencia.dominio.Nervioso;
import persistencia.dominio.Ojos;
import persistencia.dominio.Piel;
import persistencia.dominio.Respiratorio;
import persistencia.dominio.Urinario;

public class ControlFichaClinica {
	private Persistencia persistencia;
	
	public ControlFichaClinica(){
		super();
	    persistencia = new Persistencia();
	}
	
	public void guardarFichaClinica(FichaClinica fichaClinica){
		persistencia.insertarObjeto(fichaClinica);	
	}
	
	public void eliminarFichaClinica(FichaClinica fichaClinica){
		persistencia.eliminarObjeto(fichaClinica);	
	}
	
	public FichaClinica obtenerFichaClinica(String idFichaClinica){
		return (FichaClinica) persistencia.obtenerElemento(FichaClinica.class, "id=="+idFichaClinica);
	}
	
	public void guardarCirculatorio(Circulatorio circulatorio){
		persistencia.insertarObjeto(circulatorio);	
	}
	
	public void guardarDigestivo(Digestivo digestivo){
		persistencia.insertarObjeto(digestivo);	
	}
	
	public void guardarRespiratorio(Respiratorio respiratorio){
		persistencia.insertarObjeto(respiratorio);	
	}
	
	public void guardarGenital(Genital genital){
		persistencia.insertarObjeto(genital);	
	}
	
	public void guardarOjos(Ojos ojos){
		persistencia.insertarObjeto(ojos);	
	}
	
	public void guardarPiel(Piel piel){
		persistencia.insertarObjeto(piel);	
	}
	
	public void guardarNervioso(Nervioso nervioso){
		persistencia.insertarObjeto(nervioso);	
	}
	
	public void guardarLocomotor(Locomotor locomotor){
		persistencia.insertarObjeto(locomotor);	
	}
	
	public void guardarExpGeneral(ExploracionGeneral expGeneral){
		persistencia.insertarObjeto(expGeneral);	
	}
	
	public void guardarUrinario(Urinario urinario){
		persistencia.insertarObjeto(urinario);
	}
	
	public Circulatorio obtenerCirculatorio(long idFicha){
		return (Circulatorio)persistencia.obtenerElemento(Circulatorio.class,"ficha.id=="+idFicha);
	}
	public Digestivo obtenerDigestivo(long idFicha){
		return (Digestivo)persistencia.obtenerElemento(Digestivo.class,"ficha.id=="+idFicha);
	}
	public Respiratorio obtenerRespiratorio(long idFicha){
		return (Respiratorio)persistencia.obtenerElemento(Respiratorio.class,"ficha.id=="+idFicha);
	}
	public Genital obtenerGenital(long idFicha){
		return (Genital)persistencia.obtenerElemento(Genital.class,"ficha.id=="+idFicha);
	}
	public Ojos obtenerOjos(long idFicha){
		return (Ojos)persistencia.obtenerElemento(Ojos.class,"ficha.id=="+idFicha);
	}
	public Piel obtenerPiel(long idFicha){
		return (Piel)persistencia.obtenerElemento(Piel.class,"ficha.id=="+idFicha);
	}
	public ExploracionGeneral obtenerExploracionGeneral(long idFicha){
		return (ExploracionGeneral)persistencia.obtenerElemento(ExploracionGeneral.class,"ficha.id=="+idFicha);
	}
	public Urinario obtenerUrinario(long idFicha){
		return (Urinario)persistencia.obtenerElemento(Urinario.class,"ficha.id=="+idFicha);
	}
	public Locomotor obtenerLocomotor(long idFicha){
		return (Locomotor)persistencia.obtenerElemento(Locomotor.class,"ficha.id=="+idFicha);
	}
	public Nervioso obtenerNervioso(long idFicha){
		return (Nervioso)persistencia.obtenerElemento(Nervioso.class,"ficha.id=="+idFicha);
	}
}
