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

public class PanelRespiratorio extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelDatosRespiratorio = null;  //  @jve:decl-index=0:visual-constraint="6,147"
	private JPanel panelDatosRespiratorio1 = null;
	private JLabel labelRespiracion = null;
	private JTextField campoRespiracion = null;
	private JLabel labelAscultacion = null;  
	private JTextField campoTos = null;
	private JLabel labelAuscultacion = null;
	private JTextField campoAuscultacion = null;
	private JLabel labelReflejoTusigeno = null;
	private JTextField campoReflejoTusigeno = null;
	private JPanel panelDatosRespiratorio2 = null;
	private JLabel labelObservacion = null;
	private JPanel panelTextoRespiratorio = null;
	private JLabel labelRespiratorio = null;
	private JTextArea campoObservacion = null;
	private JScrollPane panelScrollObservacion;

	public PanelRespiratorio() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(640, 250);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(640, 250));
		this.setMaximumSize(new Dimension(640, 250));
		this.setMinimumSize(new Dimension(640, 250));
		this.add(getPanelTextoRespiratorio(), BorderLayout.NORTH);
		this.add(getPanelDatosRespiratorio(), BorderLayout.CENTER);
	}

	private JPanel getPanelDatosRespiratorio() {
		if (panelDatosRespiratorio == null) {
			panelDatosRespiratorio = new JPanel();
			panelDatosRespiratorio.setLayout(new BorderLayout());
			panelDatosRespiratorio.setSize(new Dimension(688, 246));
			panelDatosRespiratorio.add(getPanelDatosRespiratorio1(), BorderLayout.NORTH);
			panelDatosRespiratorio.add(getPanelDatosRespiratorio2(), BorderLayout.CENTER);
		}
		return panelDatosRespiratorio;
	}

	/**
	 * This method initializes panelDatosRespiratorio1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatosRespiratorio1() {
		if (panelDatosRespiratorio1 == null) {
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints7.gridy = 3;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.insets = new Insets(0, 0, 5, 0);
			gridBagConstraints7.gridx = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints6.gridy = 3;
			labelReflejoTusigeno = new JLabel();
			labelReflejoTusigeno.setText("Reflejo Tusigeno:");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints4.gridy = 2;
			labelAuscultacion = new JLabel();
			labelAuscultacion.setText("Auscultacion:");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 1;
			labelAscultacion = new JLabel();
			labelAscultacion.setText("Tos:");
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
			labelRespiracion = new JLabel();
			labelRespiracion.setText("Respiracion:");
			panelDatosRespiratorio1 = new JPanel();
			panelDatosRespiratorio1.setLayout(new GridBagLayout());
			panelDatosRespiratorio1.add(labelRespiracion, gridBagConstraints);
			panelDatosRespiratorio1.add(getCampoRespiracion(), gridBagConstraints1);
			panelDatosRespiratorio1.add(labelAscultacion, gridBagConstraints2);
			panelDatosRespiratorio1.add(getCampoTos(), gridBagConstraints3);
			panelDatosRespiratorio1.add(labelAuscultacion, gridBagConstraints4);
			panelDatosRespiratorio1.add(getCampoAuscultacion(), gridBagConstraints5);
			panelDatosRespiratorio1.add(labelReflejoTusigeno, gridBagConstraints6);
			panelDatosRespiratorio1.add(getCampoReflejoTusigeno(), gridBagConstraints7);
		}
		return panelDatosRespiratorio1;
	}

	/**
	 * This method initializes campoRespiracion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoRespiracion() {
		if (campoRespiracion == null) {
			campoRespiracion = new JTextField();
			campoRespiracion.setPreferredSize(new Dimension(500, 20));
			campoRespiracion.setMinimumSize(new Dimension(500, 20));
		}
		return campoRespiracion;
	}

	/**
	 * This method initializes campoTos	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoTos() {
		if (campoTos == null) {
			campoTos = new JTextField();
			campoTos.setPreferredSize(new Dimension(500, 20));
			campoTos.setMinimumSize(new Dimension(500, 20));
		}
		return campoTos;
	}

	/**
	 * This method initializes campoAuscultacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoAuscultacion() {
		if (campoAuscultacion == null) {
			campoAuscultacion = new JTextField();
			campoAuscultacion.setPreferredSize(new Dimension(500, 20));
			campoAuscultacion.setMinimumSize(new Dimension(500, 20));
		}
		return campoAuscultacion;
	}

	/**
	 * This method initializes campoReflejoTusigeno	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoReflejoTusigeno() {
		if (campoReflejoTusigeno == null) {
			campoReflejoTusigeno = new JTextField();
			campoReflejoTusigeno.setPreferredSize(new Dimension(500, 20));
			campoReflejoTusigeno.setMinimumSize(new Dimension(500, 20));
		}
		return campoReflejoTusigeno;
	}

	/**
	 * This method initializes panelDatosRespiratorio2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatosRespiratorio2() {
		if (panelDatosRespiratorio2 == null) {
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
			panelDatosRespiratorio2 = new JPanel();
			panelDatosRespiratorio2.setLayout(new GridBagLayout());
			panelDatosRespiratorio2.add(labelObservacion, gridBagConstraints14);
			panelDatosRespiratorio2.add(getPanelScrollObservacion(), gridBagConstraints12);
		}
		return panelDatosRespiratorio2;
	}

	/**
	 * This method initializes panelTextoRespiratorio	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelTextoRespiratorio() {
		if (panelTextoRespiratorio == null) {
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.gridx = 0;
			gridBagConstraints20.gridy = 0;
			labelRespiratorio = new JLabel();
			labelRespiratorio.setText("Respiratorio");
			labelRespiratorio.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			panelTextoRespiratorio = new JPanel();
			panelTextoRespiratorio.setLayout(new GridBagLayout());
			panelTextoRespiratorio.add(labelRespiratorio, gridBagConstraints20);
		}
		return panelTextoRespiratorio;
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
