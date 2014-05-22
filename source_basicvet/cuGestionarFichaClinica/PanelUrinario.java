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

public class PanelUrinario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelDaOrinaUrinario = null;
	private JPanel panelDaOrinaUrinario1 = null;
	private JLabel labelVejiga = null;
	private JTextField campoVejiga = null;
	private JLabel labelAscultacion = null;
	private JTextField campoOrina = null;
	private JLabel labelOtros = null;  
	private JTextField campoOtros = null;
	private JPanel panelDaOrinaUrinario2 = null;
	private JLabel labelObservacion = null;
	private JPanel panelTextoUrinario = null;
	private JLabel labelUrinario = null;
	private JTextArea campoObservacion = null;
	private JScrollPane panelScrollObservacion;

	/**
	 * This is the default constructor
	 */
	public PanelUrinario() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(640, 250);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(640, 250));
		this.setMaximumSize(new Dimension(640, 250));
		this.setMinimumSize(new Dimension(640, 250));
		this.add(getPanelTextoUrinario(), BorderLayout.NORTH);
		this.add(getPanelDaOrinaUrinario(), BorderLayout.CENTER);
	}

	private JPanel getPanelDaOrinaUrinario() {
		if (panelDaOrinaUrinario == null) {
			panelDaOrinaUrinario = new JPanel();
			panelDaOrinaUrinario.setLayout(new BorderLayout());
			panelDaOrinaUrinario.setSize(new Dimension(688, 246));
			panelDaOrinaUrinario.add(getPanelDaOrinaUrinario1(), BorderLayout.NORTH);
			panelDaOrinaUrinario.add(getPanelDaOrinaUrinario2(), BorderLayout.CENTER);
		}
		return panelDaOrinaUrinario;
	}

	private JPanel getPanelDaOrinaUrinario1() {
		if (panelDaOrinaUrinario1 == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.insets = new Insets(0, 0, 5, 0);
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints4.gridy = 2;
			labelOtros = new JLabel();
			labelOtros.setText("Otros:");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints2.gridy = 1;
			labelAscultacion = new JLabel();
			labelAscultacion.setText("Orina:");
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
			labelVejiga = new JLabel();
			labelVejiga.setText("Vejiga:");
			panelDaOrinaUrinario1 = new JPanel();
			panelDaOrinaUrinario1.setLayout(new GridBagLayout());
			panelDaOrinaUrinario1.add(labelVejiga, gridBagConstraints);
			panelDaOrinaUrinario1.add(getCampoVejiga(), gridBagConstraints1);
			panelDaOrinaUrinario1.add(labelAscultacion, gridBagConstraints2);
			panelDaOrinaUrinario1.add(getCampoOrina(), gridBagConstraints3);
			panelDaOrinaUrinario1.add(labelOtros, gridBagConstraints4);
			panelDaOrinaUrinario1.add(getCampoOtros(), gridBagConstraints5);
		}
		return panelDaOrinaUrinario1;
	}

	/**
	 * This method initializes campoVejiga	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoVejiga() {
		if (campoVejiga == null) {
			campoVejiga = new JTextField();
			campoVejiga.setPreferredSize(new Dimension(500, 20));
			campoVejiga.setMinimumSize(new Dimension(500, 20));
		}
		return campoVejiga;
	}

	/**
	 * This method initializes campoOrina	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoOrina() {
		if (campoOrina == null) {
			campoOrina = new JTextField();
			campoOrina.setPreferredSize(new Dimension(500, 20));
			campoOrina.setMinimumSize(new Dimension(500, 20));
		}
		return campoOrina;
	}

	/**
	 * This method initializes campoOtros	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoOtros() {
		if (campoOtros == null) {
			campoOtros = new JTextField();
			campoOtros.setPreferredSize(new Dimension(500, 20));
			campoOtros.setMinimumSize(new Dimension(500, 20));
		}
		return campoOtros;
	}

	/**
	 * This method initializes panelDaOrinaUrinario2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDaOrinaUrinario2() {
		if (panelDaOrinaUrinario2 == null) {
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.fill = GridBagConstraints.BOTH;
			gridBagConstraints12.gridy = 1;
			gridBagConstraints12.weightx = 1.0;
			gridBagConstraints12.weighty = 1.0;
			gridBagConstraints12.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints12.gridx = 0;
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.gridx = 0;
			gridBagConstraints14.insets = new Insets(5, 0, 5, 0);
			gridBagConstraints14.gridy = 0;
			labelObservacion = new JLabel();
			labelObservacion.setText("Observaciones:");
			panelDaOrinaUrinario2 = new JPanel();
			panelDaOrinaUrinario2.setLayout(new GridBagLayout());
			panelDaOrinaUrinario2.add(labelObservacion, gridBagConstraints14);
			panelDaOrinaUrinario2.add(getPanelScrollObservacion(), gridBagConstraints12);
		}
		return panelDaOrinaUrinario2;
	}

	/**
	 * This method initializes panelTextoUrinario	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelTextoUrinario() {
		if (panelTextoUrinario == null) {
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.gridx = 0;
			gridBagConstraints20.gridy = 0;
			labelUrinario = new JLabel();
			labelUrinario.setText("Urinario");
			labelUrinario.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			panelTextoUrinario = new JPanel();
			panelTextoUrinario.setLayout(new GridBagLayout());
			panelTextoUrinario.add(labelUrinario, gridBagConstraints20);
		}
		return panelTextoUrinario;
	}

	/**
	 * This method initializes campoObservacion	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getCampoObservacion() {
		if (campoObservacion == null) {
			campoObservacion = new JTextArea();
			campoObservacion.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		}
		return campoObservacion;
	}
	
	/**
	 * This method initializes panelScrollObservacion	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getPanelScrollObservacion() {
		if (panelScrollObservacion == null) {
			panelScrollObservacion = new JScrollPane();
			panelScrollObservacion.setViewportView(getCampoObservacion());
		}
		return panelScrollObservacion;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
