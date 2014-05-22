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

package cuGestionarTurno;

import java.util.LinkedList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;



public class TablaTurno extends JTable {
	private static final long serialVersionUID = 1L;
	private RenderTabla miRender1 = new RenderTabla(1);
	private RenderTabla miRender2 = new RenderTabla(2);
	private RenderTabla miRender3 = new RenderTabla(3);
	private RenderTabla miRender4 = new RenderTabla(4);
	private LinkedList<Integer> filasRender1;
	private LinkedList<Integer> filasRender2;
	private LinkedList<Integer> filasRender3;
	private LinkedList<Integer> filasRender4;
	
	TablaTurno(DefaultTableModel modelo,LinkedList<Integer> l1, LinkedList<Integer> l2,LinkedList<Integer> l3,LinkedList<Integer> l4){
		super(modelo);
		this.filasRender1 = l1;
		this.filasRender2 = l2;
		this.filasRender3 = l3;
		this.filasRender4 = l4;
	    getTableHeader().setReorderingAllowed(false);
	    setCellSelectionEnabled(true);
	    setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	}
	
	TablaTurno(DefaultTableModel modelo){
		super(modelo);
		this.filasRender1 = new LinkedList<Integer>();
		this.filasRender2 = new LinkedList<Integer>();
		this.filasRender3 = new LinkedList<Integer>();
		this.filasRender4 = new LinkedList<Integer>();
	}
		  
	
	public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
		if (columnIndex == 0)	
			super.changeSelection(rowIndex, columnIndex + 1, toggle,extend);
		else
			super.changeSelection(rowIndex, columnIndex, toggle,extend);
	}
	
	public TableCellRenderer getCellRenderer(int row, int column) {
		for(int i=0;i<this.filasRender1.size();i++){
			if (column == 1 && row==this.filasRender1.get(i)) {
				return miRender1;
			}
		}
		for(int i=0;i<this.filasRender2.size();i++){
			if (column == 1 && row==this.filasRender2.get(i)) {
				return miRender2;
			}
		}
		for(int i=0;i<this.filasRender3.size();i++){
			if (column == 1 && row==this.filasRender3.get(i)) {
				return miRender3;
			}
		}
		for(int i=0;i<this.filasRender4.size();i++){
			if (column == 1 && row==this.filasRender4.get(i)) {
				return miRender4;
			}
		}
		return super.getCellRenderer(row, column);
	}
}
