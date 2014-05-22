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

public class PanelGenital extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelDatosGenital = null;  //  @jve:decl-index=0:visual-constraint="6,147"
	private JPanel panelDatosGenital1 = null;
	private JLabel labelMachoExp = null;
	private JTextField campoMachoExp = null;
	private JLabel labelMachoSecreciones = null;
	private JTextField campoMachoSecreciones = null;  
	private JLabel labelMachoTactoRectal = null;
	private JTextField campoMachoTactoRectal = null;
	private JLabel labelHembraExp = null;
	private JTextField campoHembraExp = null;
	private JLabel labelHembraSecreciones = null;
	private JTextField campoHembraSecreciones = null;
	private JLabel labelHembraGlandulaMamarias = null;
	private JTextField campoHembraGlandulaMamarias = null;
	private JPanel panelDatosGenital2 = null;
	private JLabel labelObservacion = null;
	private JPanel panelTextoGenital = null;
	private JLabel labelGenital = null;
	private JTextArea campoObservacion = null;
	private JScrollPane panelScrollObservacion;

	public PanelGenital() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(640, 250);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(640, 250));
		this.setMaximumSize(new Dimension(640, 250));
		this.setMinimumSize(new Dimension(640, 250));
		this.add(getPanelTextoGenital(), BorderLayout.NORTH);
		this.add(getPanelDatosGenital(), BorderLayout.CENTER);
	}

	private JPanel getPanelDatosGenital() {
		if (panelDatosGenital == null) {
			panelDatosGenital = new JPanel();
			panelDatosGenital.setLayout(new BorderLayout());
			panelDatosGenital.setSize(new Dimension(688, 246));
			panelDatosGenital.add(getPanelDatosGenital1(), BorderLayout.NORTH);
			panelDatosGenital.add(getPanelDatosGenital2(), BorderLayout.CENTER);
		}
		return panelDatosGenital;
	}

	/**
	 * This method initializes panelDatosGenital1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatosGenital1() {
		if (panelDatosGenital1 == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints11.gridy = 2;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.insets = new Insets(0, 0, 5, 0);
			gridBagConstraints11.gridx = 3;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 2;
			gridBagConstraints10.insets = new Insets(0, 10, 5, 0);
			gridBagConstraints10.gridy = 2;
			labelHembraGlandulaMamarias = new JLabel();
			labelHembraGlandulaMamarias.setText("GlandulaMamarias:");
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints9.gridy = 1;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.gridx = 3;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 2;
			gridBagConstraints8.gridy = 1;
			labelHembraSecreciones = new JLabel();
			labelHembraSecreciones.setText("Secreciones:");
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints7.gridy = 0;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.insets = new Insets(5, 0, 0, 0);
			gridBagConstraints7.gridx = 3;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 2;
			gridBagConstraints6.gridy = 0;
			labelHembraExp = new JLabel();
			labelHembraExp.setText("Exp:");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.insets = new Insets(0, 0, 5, 0);
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 2;
			labelMachoTactoRectal = new JLabel();
			labelMachoTactoRectal.setText("TactoRectal:");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 1;
			labelMachoSecreciones = new JLabel();
			labelMachoSecreciones.setText("Secreciones:");
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
			labelMachoExp = new JLabel();
			labelMachoExp.setText("Exp:");
			panelDatosGenital1 = new JPanel();
			panelDatosGenital1.setLayout(new GridBagLayout());
			panelDatosGenital1.add(labelMachoExp, gridBagConstraints);
			panelDatosGenital1.add(getCampoMachoExp(), gridBagConstraints1);
			panelDatosGenital1.add(labelMachoSecreciones, gridBagConstraints2);
			panelDatosGenital1.add(getCampoMachoSecreciones(), gridBagConstraints3);
			panelDatosGenital1.add(labelMachoTactoRectal, gridBagConstraints4);
			panelDatosGenital1.add(getCampoMachoTactoRectal(), gridBagConstraints5);
			panelDatosGenital1.add(labelHembraExp, gridBagConstraints6);
			panelDatosGenital1.add(getCampoHembraExp(), gridBagConstraints7);
			panelDatosGenital1.add(labelHembraSecreciones, gridBagConstraints8);
			panelDatosGenital1.add(getCampoHembraSecreciones(), gridBagConstraints9);
			panelDatosGenital1.add(labelHembraGlandulaMamarias, gridBagConstraints10);
			panelDatosGenital1.add(getCampoHembraGlandulaMamarias(), gridBagConstraints11);
		}
		return panelDatosGenital1;
	}

	/**
	 * This method initializes campoMachoExp	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoMachoExp() {
		if (campoMachoExp == null) {
			campoMachoExp = new JTextField();
			campoMachoExp.setPreferredSize(new Dimension(500, 20));
			campoMachoExp.setMinimumSize(new Dimension(500, 20));
		}
		return campoMachoExp;
	}

	/**
	 * This method initializes campoMachoSecreciones	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoMachoSecreciones() {
		if (campoMachoSecreciones == null) {
			campoMachoSecreciones = new JTextField();
			campoMachoSecreciones.setPreferredSize(new Dimension(500, 20));
			campoMachoSecreciones.setMinimumSize(new Dimension(500, 20));
		}
		return campoMachoSecreciones;
	}

	/**
	 * This method initializes campoMachoTactoRectal	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoMachoTactoRectal() {
		if (campoMachoTactoRectal == null) {
			campoMachoTactoRectal = new JTextField();
			campoMachoTactoRectal.setPreferredSize(new Dimension(500, 20));
			campoMachoTactoRectal.setMinimumSize(new Dimension(500, 20));
		}
		return campoMachoTactoRectal;
	}

	/**
	 * This method initializes campoHembraExp	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoHembraExp() {
		if (campoHembraExp == null) {
			campoHembraExp = new JTextField();
			campoHembraExp.setPreferredSize(new Dimension(500, 20));
			campoHembraExp.setMinimumSize(new Dimension(500, 20));
		}
		return campoHembraExp;
	}

	/**
	 * This method initializes campoHembraSecreciones	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoHembraSecreciones() {
		if (campoHembraSecreciones == null) {
			campoHembraSecreciones = new JTextField();
			campoHembraSecreciones.setPreferredSize(new Dimension(500, 20));
			campoHembraSecreciones.setMinimumSize(new Dimension(500, 20));
		}
		return campoHembraSecreciones;
	}

	/**
	 * This method initializes campoHembraGlandulaMamarias	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoHembraGlandulaMamarias() {
		if (campoHembraGlandulaMamarias == null) {
			campoHembraGlandulaMamarias = new JTextField();
			campoHembraGlandulaMamarias.setPreferredSize(new Dimension(500, 20));
			campoHembraGlandulaMamarias.setMinimumSize(new Dimension(500, 20));
		}
		return campoHembraGlandulaMamarias;
	}

	/**
	 * This method initializes panelDatosGenital2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatosGenital2() {
		if (panelDatosGenital2 == null) {
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
			panelDatosGenital2 = new JPanel();
			panelDatosGenital2.setLayout(new GridBagLayout());
			panelDatosGenital2.add(labelObservacion, gridBagConstraints14);
			panelDatosGenital2.add(getPanelScrollObservacion(), gridBagConstraints12);
		}
		return panelDatosGenital2;
	}

	/**
	 * This method initializes panelTextoGenital	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelTextoGenital() {
		if (panelTextoGenital == null) {
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.gridx = 0;
			gridBagConstraints20.gridy = 0;
			labelGenital = new JLabel();
			labelGenital.setText("Genital");
			labelGenital.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			panelTextoGenital = new JPanel();
			panelTextoGenital.setLayout(new GridBagLayout());
			panelTextoGenital.add(labelGenital, gridBagConstraints20);
		}
		return panelTextoGenital;
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
