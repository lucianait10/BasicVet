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

package cuGestionarPropietario.report;

import java.util.Hashtable;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
  
@SuppressWarnings({ "unchecked"})
public class DataSourceJasper implements JRDataSource {  

	private Object[][]data = null;
	private Hashtable tabla = null; // entrada: nombre campo - valor: indice en
	private int index = -1;

	public DataSourceJasper(Object[][]newData, Hashtable newTabla) {
		data = newData;
		tabla = newTabla;
	}
	
	
	public boolean next() throws JRException {
		index++;
		return (index < data.length);
	}

	
	public Object getFieldValue(JRField field) throws JRException {
		String fieldName = field.getName();
		return data[index][((Integer) tabla.get(fieldName)).intValue()];
	}

}
