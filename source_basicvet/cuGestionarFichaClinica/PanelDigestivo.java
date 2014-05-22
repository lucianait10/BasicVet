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

public class PanelDigestivo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelDatosCirculatorio = null;
	private JPanel panelDatosDigestivo1 = null;
	private JLabel labelCavidadOral = null;
	private JTextField campoCavidadOral = null;  
	private JLabel labelPalpacionAbdominal = null;
	private JTextField campoPalpacionAbdominal = null;
	private JLabel labelVomitos = null;
	private JTextField campoVomitos = null;
	private JLabel labelDisfagia = null;
	private JTextField campoDisfagia = null;
	private JLabel labelMateriaFecal = null;
	private JTextField campoMateriaFecal = null;
	private JPanel panelDatosDigestivo2 = null;
	private JLabel labelObservacion = null;
	private JPanel panelTextoCirculatorio = null;
	private JLabel labelCirculatorio = null;
	private JTextArea campoObservacion = null;
	private JScrollPane panelScrollObservacion;

	public PanelDigestivo() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(640, 250);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(640, 250));
		this.setMaximumSize(new Dimension(640, 250));
		this.setMinimumSize(new Dimension(640, 250));
		this.add(getPanelTextoCirculatorio(), BorderLayout.NORTH);
		this.add(getPanelDatosCirculatorio(), BorderLayout.CENTER);
	}

	private JPanel getPanelDatosCirculatorio() {
		if (panelDatosCirculatorio == null) {
			panelDatosCirculatorio = new JPanel();
			panelDatosCirculatorio.setLayout(new BorderLayout());
			panelDatosCirculatorio.setSize(new Dimension(688, 246));
			panelDatosCirculatorio.add(getPanelDatosDigestivo1(), BorderLayout.NORTH);
			panelDatosCirculatorio.add(getPanelDatosDigestivo2(), BorderLayout.CENTER);
		}
		return panelDatosCirculatorio;
	}

	/**
	 * This method initializes panelDatosDigestivo1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatosDigestivo1() {
		if (panelDatosDigestivo1 == null) {
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints9.gridy = 4;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.insets = new Insets(0, 0, 5, 0);
			gridBagConstraints9.gridx = 1;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.insets = new Insets(0, 0, 5, 0);
			gridBagConstraints8.gridy = 4;
			labelMateriaFecal = new JLabel();
			labelMateriaFecal.setText("Materia Fecal:");
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints7.gridy = 3;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.gridx = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 3;
			labelDisfagia = new JLabel();
			labelDisfagia.setText("Disfagia:");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 2;
			labelVomitos = new JLabel();
			labelVomitos.setText("Vomitos:");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints2.gridy = 1;
			labelPalpacionAbdominal = new JLabel();
			labelPalpacionAbdominal.setText("Palpacion Abdominal:");
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
			labelCavidadOral = new JLabel();
			labelCavidadOral.setText("Cavidad Oral:");
			panelDatosDigestivo1 = new JPanel();
			panelDatosDigestivo1.setLayout(new GridBagLayout());
			panelDatosDigestivo1.add(labelCavidadOral, gridBagConstraints);
			panelDatosDigestivo1.add(getCampoCavidadOral(), gridBagConstraints1);
			panelDatosDigestivo1.add(labelPalpacionAbdominal, gridBagConstraints2);
			panelDatosDigestivo1.add(getCampoPalpacionAbdominal(), gridBagConstraints3);
			panelDatosDigestivo1.add(labelVomitos, gridBagConstraints4);
			panelDatosDigestivo1.add(getCampoVomitos(), gridBagConstraints5);
			panelDatosDigestivo1.add(labelDisfagia, gridBagConstraints6);
			panelDatosDigestivo1.add(getCampoDisfagia(), gridBagConstraints7);
			panelDatosDigestivo1.add(labelMateriaFecal, gridBagConstraints8);
			panelDatosDigestivo1.add(getCampoMateriaFecal(), gridBagConstraints9);
		}
		return panelDatosDigestivo1;
	}

	/**
	 * This method initializes campoCavidadOral	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoCavidadOral() {
		if (campoCavidadOral == null) {
			campoCavidadOral = new JTextField();
			campoCavidadOral.setPreferredSize(new Dimension(500, 20));
			campoCavidadOral.setMinimumSize(new Dimension(500, 20));
		}
		return campoCavidadOral;
	}

	/**
	 * This method initializes campoPalpacionAbdominal	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoPalpacionAbdominal() {
		if (campoPalpacionAbdominal == null) {
			campoPalpacionAbdominal = new JTextField();
			campoPalpacionAbdominal.setPreferredSize(new Dimension(500, 20));
			campoPalpacionAbdominal.setMinimumSize(new Dimension(500, 20));
		}
		return campoPalpacionAbdominal;
	}

	/**
	 * This method initializes campoVomitos	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoVomitos() {
		if (campoVomitos == null) {
			campoVomitos = new JTextField();
			campoVomitos.setPreferredSize(new Dimension(500, 20));
			campoVomitos.setMinimumSize(new Dimension(500, 20));
		}
		return campoVomitos;
	}

	/**
	 * This method initializes campoDisfagia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoDisfagia() {
		if (campoDisfagia == null) {
			campoDisfagia = new JTextField();
			campoDisfagia.setPreferredSize(new Dimension(500, 20));
			campoDisfagia.setMinimumSize(new Dimension(500, 20));
		}
		return campoDisfagia;
	}

	/**
	 * This method initializes campoMateriaFecal	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoMateriaFecal() {
		if (campoMateriaFecal == null) {
			campoMateriaFecal = new JTextField();
			campoMateriaFecal.setPreferredSize(new Dimension(500, 20));
			campoMateriaFecal.setMinimumSize(new Dimension(500, 20));
		}
		return campoMateriaFecal;
	}

	/**
	 * This method initializes panelDatosDigestivo2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatosDigestivo2() {
		if (panelDatosDigestivo2 == null) {
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.fill = GridBagConstraints.BOTH;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.weightx = 1.0;
			gridBagConstraints10.weighty = 1.0;
			gridBagConstraints10.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints10.gridx = 0;
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.gridx = 0;
			gridBagConstraints14.insets = new Insets(5, 0, 5, 0);
			gridBagConstraints14.gridy = 0;
			labelObservacion = new JLabel();
			labelObservacion.setText("Observaciones:");
			panelDatosDigestivo2 = new JPanel();
			panelDatosDigestivo2.setLayout(new GridBagLayout());
			panelDatosDigestivo2.add(labelObservacion, gridBagConstraints14);
			panelDatosDigestivo2.add(getPanelScrollObservacion(), gridBagConstraints10);
		}
		return panelDatosDigestivo2;
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
			labelCirculatorio.setText("Digestivo");
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
			panelScrollObservacion.setViewportView(getCampoObservacion());
		}
		return panelScrollObservacion;
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
