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


public class PanelAnticonceptivo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollAnticonc = null;
	private JTable tablaAnticonc = null;
	
	
	public PanelAnticonceptivo(DefaultTableModel tabla) {
		super();
		this.tablaAnticonc = new JTable(tabla);
		this.tablaAnticonc.getTableHeader().setReorderingAllowed(false);
		initialize();
	}
	
	public PanelAnticonceptivo(){
		super();
		initialize();
	}

	
	private void initialize() {
		this.setSize(400, 300);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(400, 300));
		this.add(getScrollAnticonc(), BorderLayout.CENTER);
		
	}

	
	private JScrollPane getScrollAnticonc() {
		if (scrollAnticonc == null) {
			scrollAnticonc = new JScrollPane();
			scrollAnticonc.setViewportView(getTablaAnticonc());
		}
		return scrollAnticonc;
	}

	
	public JTable getTablaAnticonc() {
		if (tablaAnticonc == null) {
			tablaAnticonc = new JTable();
		}
		return tablaAnticonc;
	}

}
