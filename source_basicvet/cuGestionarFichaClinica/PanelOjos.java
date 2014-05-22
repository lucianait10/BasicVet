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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class PanelOjos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelDatosOjos = null;
	private JPanel panelDatosOjos1 = null;
	private JLabel labelIzquierdo = null;
	private JTextField campoIzquierdo = null;
	private JLabel labelDerecho = null;
	private JTextField campoDerecho = null;
	private JPanel panelDatosOjos2 = null;
	private JLabel labelObservacion = null;
	private JPanel panelTextoOjos = null;
	private JLabel labelCirculatorio = null;
	private JTextArea campoObservacion = null;
	private JScrollPane panelScrollObservacion;

	public PanelOjos() {
		super();  
		initialize();
	}

	private void initialize() {
		this.setSize(640, 250);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(640, 250));
		this.setMaximumSize(new Dimension(640, 250));
		this.setMinimumSize(new Dimension(640, 250));
		this.add(getPanelTextoOjos(), BorderLayout.NORTH);
		this.add(getPanelDatosOjos(), BorderLayout.CENTER);
	}

	private JPanel getPanelDatosOjos() {
		if (panelDatosOjos == null) {
			panelDatosOjos = new JPanel();
			panelDatosOjos.setLayout(new BorderLayout());
			panelDatosOjos.add(getPanelDatosOjos1(), BorderLayout.NORTH);
			panelDatosOjos.add(getPanelDatosOjos2(), BorderLayout.CENTER);
		}
		return panelDatosOjos;
	}

	private JPanel getPanelDatosOjos1() {
		if (panelDatosOjos1 == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.insets = new Insets(0, 0, 5, 0);
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.insets = new Insets(0, 10, 5, 0);
			gridBagConstraints2.gridy = 1;
			labelDerecho = new JLabel();
			labelDerecho.setText("Derecho:");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.insets = new Insets(5, 0, 0, 0);
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.insets = new Insets(5, 10, 0, 0);
			gridBagConstraints.gridy = 0;
			labelIzquierdo = new JLabel();
			labelIzquierdo.setText("Izquierdo:");
			panelDatosOjos1 = new JPanel();
			panelDatosOjos1.setLayout(new GridBagLayout());
			panelDatosOjos1.add(labelIzquierdo, gridBagConstraints);
			panelDatosOjos1.add(getCampoIzquierdo(), gridBagConstraints1);
			panelDatosOjos1.add(labelDerecho, gridBagConstraints2);
			panelDatosOjos1.add(getCampoDerecho(), gridBagConstraints3);
		}
		return panelDatosOjos1;
	}

	public JTextField getCampoIzquierdo() {
		if (campoIzquierdo == null) {
			campoIzquierdo = new JTextField();
			campoIzquierdo.setPreferredSize(new Dimension(500, 20));
			campoIzquierdo.setMinimumSize(new Dimension(500, 20));
		}
		return campoIzquierdo;
	}

	public JTextField getCampoDerecho() {
		if (campoDerecho == null) {
			campoDerecho = new JTextField();
			campoDerecho.setPreferredSize(new Dimension(500, 20));
			campoDerecho.setMinimumSize(new Dimension(500, 20));
		}
		return campoDerecho;
	}

	private JPanel getPanelDatosOjos2() {
		if (panelDatosOjos2 == null) {
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.fill = GridBagConstraints.BOTH;
			gridBagConstraints12.gridy = 1;
			gridBagConstraints12.weightx = 1.0;
			gridBagConstraints12.weighty = 1.0;
			gridBagConstraints12.insets = new Insets(0, 10, 5, 10);
			gridBagConstraints12.gridx = 0;
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.gridx = 0;
			gridBagConstraints14.insets = new Insets(5, 0, 5, 0);
			gridBagConstraints14.gridy = 0;
			labelObservacion = new JLabel();
			labelObservacion.setText("Observaciones:");
			panelDatosOjos2 = new JPanel();
			panelDatosOjos2.setLayout(new GridBagLayout());
			panelDatosOjos2.add(labelObservacion, gridBagConstraints14);
			panelDatosOjos2.add(getPanelScrollObservacion(), gridBagConstraints12);
		}
		return panelDatosOjos2;
	}

	private JPanel getPanelTextoOjos() {
		if (panelTextoOjos == null) {
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.gridx = 0;
			gridBagConstraints20.gridy = 0;
			labelCirculatorio = new JLabel();
			labelCirculatorio.setText("Ojos");
			labelCirculatorio.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			panelTextoOjos = new JPanel();
			panelTextoOjos.setLayout(new GridBagLayout());
			panelTextoOjos.add(labelCirculatorio, gridBagConstraints20);
		}
		return panelTextoOjos;
	}

	public JTextArea getCampoObservacion() {
		if (campoObservacion == null) {
			campoObservacion = new JTextArea();
			campoObservacion.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		}
		return campoObservacion;
	}

	private JScrollPane getPanelScrollObservacion() {
		if (panelScrollObservacion == null) {
			panelScrollObservacion = new JScrollPane();
			panelScrollObservacion.setViewportView(getCampoObservacion());
		}
		return panelScrollObservacion;
	}
}
