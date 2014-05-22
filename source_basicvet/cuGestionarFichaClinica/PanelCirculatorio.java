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

import com.jgoodies.looks.windows.WindowsScrollPaneUI;

public class PanelCirculatorio extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelDatosCirculatorio = null;  //  @jve:decl-index=0:visual-constraint="6,147"
	private JPanel panelDatosCirculatorio1 = null;
	private JLabel labelRitmo = null;
	private JTextField campoRitmo = null;  
	private JLabel labelAscultacion = null;
	private JTextField campoAscultacion = null;
	private JLabel labelPulso = null;
	private JTextField campoPulso = null;
	private JLabel labelSoplo = null;
	private JTextField campoSoplo = null;
	private JLabel labelEcg = null;
	private JTextField campoEcg = null;
	private JLabel labelOtros = null;
	private JTextField campoOtros = null;
	private JPanel panelDatosCirculatorio2 = null;
	private JLabel labelObservacion = null;
	private JPanel panelTextoCirculatorio = null;
	private JLabel labelCirculatorio = null;
	private JTextArea campoObservacion = null;
	private JScrollPane panelScrollObservacion = null;

	public PanelCirculatorio() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(640, 250);
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(640, 250));
		this.setPreferredSize(new Dimension(640, 250));
		this.setMaximumSize(new Dimension(640, 250));
		this.add(getPanelTextoCirculatorio(), BorderLayout.NORTH);
		this.add(getPanelDatosCirculatorio(), BorderLayout.CENTER);
	}

	private JPanel getPanelDatosCirculatorio() {
		if (panelDatosCirculatorio == null) {
			panelDatosCirculatorio = new JPanel();
			panelDatosCirculatorio.setLayout(new BorderLayout());
			panelDatosCirculatorio.setSize(new Dimension(688, 246));
			panelDatosCirculatorio.add(getPanelDatosCirculatorio1(), BorderLayout.NORTH);
			panelDatosCirculatorio.add(getPanelDatosCirculatorio2(), BorderLayout.CENTER);
		}
		return panelDatosCirculatorio;
	}

	/**
	 * This method initializes panelDatosCirculatorio1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatosCirculatorio1() {
		if (panelDatosCirculatorio1 == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints11.gridy = 2;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.insets = new Insets(0, 0, 5, 0);
			gridBagConstraints11.gridx = 3;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 2;
			gridBagConstraints10.insets = new Insets(0, 0, 5, 0);
			gridBagConstraints10.gridy = 2;
			labelOtros = new JLabel();
			labelOtros.setText("Otros:");
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints9.gridy = 1;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.gridx = 3;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 2;
			gridBagConstraints8.gridy = 1;
			labelEcg = new JLabel();
			labelEcg.setText("ECG:");
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints7.gridy = 0;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.insets = new Insets(5, 0, 0, 0);
			gridBagConstraints7.gridx = 3;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 2;
			gridBagConstraints6.gridy = 0;
			labelSoplo = new JLabel();
			labelSoplo.setText("Soplo:");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.insets = new Insets(0, 0, 5, 0);
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 2;
			labelPulso = new JLabel();
			labelPulso.setText("Pulso:");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 1;
			labelAscultacion = new JLabel();
			labelAscultacion.setText("  Ascultacion:");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.insets = new Insets(5, 0, 0, 0);
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.insets = new Insets(5, 0, 0, 0);
			gridBagConstraints.gridy = 0;
			labelRitmo = new JLabel();
			labelRitmo.setText("Ritmo:");
			panelDatosCirculatorio1 = new JPanel();
			panelDatosCirculatorio1.setLayout(new GridBagLayout());
			panelDatosCirculatorio1.add(labelRitmo, gridBagConstraints);
			panelDatosCirculatorio1.add(getCampoRitmo(), gridBagConstraints1);
			panelDatosCirculatorio1.add(labelAscultacion, gridBagConstraints2);
			panelDatosCirculatorio1.add(getCampoAscultacion(), gridBagConstraints3);
			panelDatosCirculatorio1.add(labelPulso, gridBagConstraints4);
			panelDatosCirculatorio1.add(getCampoPulso(), gridBagConstraints5);
			panelDatosCirculatorio1.add(labelSoplo, gridBagConstraints6);
			panelDatosCirculatorio1.add(getCampoSoplo(), gridBagConstraints7);
			panelDatosCirculatorio1.add(labelEcg, gridBagConstraints8);
			panelDatosCirculatorio1.add(getCampoEcg(), gridBagConstraints9);
			panelDatosCirculatorio1.add(labelOtros, gridBagConstraints10);
			panelDatosCirculatorio1.add(getCampoOtros(), gridBagConstraints11);
		}
		return panelDatosCirculatorio1;
	}

	/**
	 * This method initializes campoRitmo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoRitmo() {
		if (campoRitmo == null) {
			campoRitmo = new JTextField();
			campoRitmo.setPreferredSize(new Dimension(500, 20));
			campoRitmo.setMinimumSize(new Dimension(500, 20));
		}
		return campoRitmo;
	}

	/**
	 * This method initializes campoAscultacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoAscultacion() {
		if (campoAscultacion == null) {
			campoAscultacion = new JTextField();
			campoAscultacion.setPreferredSize(new Dimension(500, 20));
			campoAscultacion.setMinimumSize(new Dimension(500, 20));
		}
		return campoAscultacion;
	}

	/**
	 * This method initializes campoPulso	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoPulso() {
		if (campoPulso == null) {
			campoPulso = new JTextField();
			campoPulso.setPreferredSize(new Dimension(500, 20));
			campoPulso.setMinimumSize(new Dimension(500, 20));
		}
		return campoPulso;
	}

	/**
	 * This method initializes campoSoplo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoSoplo() {
		if (campoSoplo == null) {
			campoSoplo = new JTextField();
			campoSoplo.setPreferredSize(new Dimension(500, 20));
			campoSoplo.setMinimumSize(new Dimension(500, 20));
		}
		return campoSoplo;
	}

	/**
	 * This method initializes campoEcg	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoEcg() {
		if (campoEcg == null) {
			campoEcg = new JTextField();
			campoEcg.setPreferredSize(new Dimension(500, 20));
			campoEcg.setMinimumSize(new Dimension(500, 20));
		}
		return campoEcg;
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
	 * This method initializes panelDatosCirculatorio2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatosCirculatorio2() {
		if (panelDatosCirculatorio2 == null) {
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
			panelDatosCirculatorio2 = new JPanel();
			panelDatosCirculatorio2.setLayout(new GridBagLayout());
			panelDatosCirculatorio2.add(labelObservacion, gridBagConstraints14);
			panelDatosCirculatorio2.add(getPanelScrollObservacion(), gridBagConstraints12);
		}
		return panelDatosCirculatorio2;
	}

	/**
	 * This method initializes panelTextoCirculatorio	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelTextoCirculatorio() {
		if (panelTextoCirculatorio == null) {
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.gridx = 0;
			gridBagConstraints20.gridy = 0;
			labelCirculatorio = new JLabel();
			labelCirculatorio.setText("Circulatorio");
			labelCirculatorio.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			panelTextoCirculatorio = new JPanel();
			panelTextoCirculatorio.setLayout(new GridBagLayout());
			panelTextoCirculatorio.add(labelCirculatorio, gridBagConstraints20);
		}
		return panelTextoCirculatorio;
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
			panelScrollObservacion.setUI(new WindowsScrollPaneUI());
			panelScrollObservacion.setViewportView(getCampoObservacion());
		}
		return panelScrollObservacion;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
