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

package cuGestionarCartilla;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelAntiparasitario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollAntiparas = null;
	private JTable tablaAntiparas = null;


	public PanelAntiparasitario(DefaultTableModel tabla) {
		super();
		this.tablaAntiparas = new JTable(tabla);
		this.tablaAntiparas.getTableHeader().setReorderingAllowed(false);
		initialize();
	}
	
	public PanelAntiparasitario(){
		super();
		initialize();
	}



	private void initialize() {
		this.setSize(400, 300);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(400, 300));
		this.add(getScrollAntiparas(), BorderLayout.CENTER);
		
	}



	private JScrollPane getScrollAntiparas() {
		if (scrollAntiparas == null) {
			scrollAntiparas = new JScrollPane();
			scrollAntiparas.setViewportView(getTablaAntiparas());
		}
		return scrollAntiparas;
	}



	public JTable getTablaAntiparas() {
		if (tablaAntiparas == null) {
			tablaAntiparas = new JTable();
		}
		return tablaAntiparas;
	}

} 
