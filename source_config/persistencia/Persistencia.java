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

package persistencia;
  
import java.util.Collection;
import java.util.Iterator;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

@SuppressWarnings({ "unchecked"})
public class Persistencia {
	private static PersistenceManagerFactory persistenceManagerFactory;
	private static PersistenceManager persistenceManager;
	private static Transaction transaccion;
	  
	public Persistencia() {
		persistenceManagerFactory = Conexion.obtenerConexion();

	}
	
	public void iniciarTransaccion() {
		try {
			persistenceManager = persistenceManagerFactory.getPersistenceManager();
			transaccion = persistenceManager.currentTransaction();
			transaccion.begin();
        }
        catch ( Exception E){
            System.out.println("Error al iniciar la transaccion");
            System.out.println(E.getMessage());
            E.printStackTrace();

        }
	}
	
	public void concretarTransaccion() {
		transaccion.commit();
		//if (transaccion.isActive()){transaccion.rollback();System.out.println("Error en la base de datos");}
		if(!persistenceManager.isClosed()){
			persistenceManager.close();
		}
		

	}
	
	public void deshacerTransaccion(){
			transaccion.rollback();
			if(!persistenceManager.isClosed()){
				persistenceManager.close();
			}
	}
	
	public Object insertarObjeto(Object obj){
		return persistenceManager.makePersistent(obj);
				
	}
	
	public void eliminarObjeto(Object obj){
		persistenceManager.deletePersistent(obj);
		
	}


	public Object obtenerElemento(Class clase, String s){
		Extent<Object> e=persistenceManager.getExtent(clase,true);
		Query q=persistenceManager.newQuery(e,s);
		Collection<Object> c =(Collection<Object>)q.execute();
		Iterator<Object> iter = c.iterator();
		return iter.next();
	}
	

	public Object obtenerElementoQuery(Class clase, String s){
		Query q=persistenceManager.newQuery(clase,s);
		Collection<Object> c =(Collection<Object>)q.execute();
		Iterator<Object> iter = c.iterator();
		return iter.next();

	}
	

	public Iterator obtenerColeccionQuery(Class clase, String s){
    	Query q=persistenceManager.newQuery(clase,s);
    	Collection<Object> c =(Collection<Object>)q.execute();
    	Iterator<Object> iter = c.iterator();
    	return iter;
	}
	

	public Iterator obtenerColeccionQueryOrdered(Class clase, String s,String orden){
    	Query q=persistenceManager.newQuery(clase,s);
    	q.setOrdering(orden);
    	Collection<Object> c =(Collection<Object>)q.execute();
    	Iterator<Object> iter = c.iterator();
    	return iter;
	}
	

	public Iterator obtenerColeccion(Class clase){
		Extent e=persistenceManager.getExtent(clase,true);
    	Query q=persistenceManager.newQuery(e);
    	Collection c =(Collection)q.execute();
    	Iterator iter=c.iterator();
    	return iter;
	}
	

	public Iterator obtenerColeccionOrdered(Class clase,String orden){
		Extent e=persistenceManager.getExtent(clase,true);
    	Query q=persistenceManager.newQuery(e);
    	q.setOrdering(orden);
    	Collection c =(Collection)q.execute();
    	Iterator iter=c.iterator();
    	return iter;
	}
	

	public int existeObjetoClase(Class clase, String s){
		javax.jdo.Extent<Object> e=persistenceManager.getExtent(clase,true);
    	Query q=persistenceManager.newQuery(e,s);
    	Collection c=(Collection)q.execute();
    	Iterator iter=c.iterator();
    	if (iter.hasNext()){return 1;}
		return 0;
	}	


	public Collection<Object> obtenerObjetosFecha(Class clase,Integer anio, Integer mes,Integer dia ){
		Query query = persistenceManager.newQuery(clase);
		query.declareParameters("int anio, int mes, int dia");
		query.setFilter("fecha.getYear() == anio && fecha.getMonth() == mes && fecha.getDay() == dia");
		Collection<Object> results = (Collection<Object>)query.execute(anio,mes,dia);
		return results;
	}
}
