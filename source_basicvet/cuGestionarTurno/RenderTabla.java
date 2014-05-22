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

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderTabla extends DefaultTableCellRenderer{
	private int colorTurnoSiguiente;
	private static final long serialVersionUID = 1L;
//----------------------------------------------------------------------------------------------------
	public RenderTabla(int valorTurno){
		super();
		this.colorTurnoSiguiente = valorTurno;
	}
//----------------------------------------------------------------------------------------------------
	
	public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus, int row, int column){
		JComponent celda =(JComponent) super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
		Border border;
		if(this.colorTurnoSiguiente==1){
			border = BorderFactory.createMatteBorder(2, 2, 1, 1, new Color(100,220,0));
			celda.setBackground(new Color(100,220,0));
	    	celda.setBorder(border);
		}else if(this.colorTurnoSiguiente==2){
				border = BorderFactory.createMatteBorder(2, 2, 1, 1, new Color(0,255,0));
				celda.setBackground(new Color(0,255,0));
				celda.setBorder(border);
			}else{if(this.colorTurnoSiguiente==3){
					border = BorderFactory.createMatteBorder(2, 2, 1, 1, new Color(0,200,255));
					celda.setBackground(new Color(0,200,255));
					celda.setBorder(border);
				}else{if(this.colorTurnoSiguiente==4){
						border = BorderFactory.createMatteBorder(2, 2, 1, 1, new Color(0,100,255));
						celda.setBackground(new Color(0,100,255));
						celda.setForeground(Color.yellow);
						celda.setBorder(border);
					}
				}
			}
    	if(isSelected){
    		celda.setBackground(Color.RED);
    		border = BorderFactory.createMatteBorder(2, 2, 1, 1, Color.RED);
        	celda.setBorder(border);
    	}
    	return celda;
    }
}

