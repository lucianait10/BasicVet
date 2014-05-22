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

public class PanelNervioso extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelDatosNervioso = null;
	private JPanel panelDatosNervioso1 = null;
	private JLabel labelParalisis = null;
	private JTextField campoParalisis = null;
	private JLabel labelConvulsiones = null;
	private JTextField campoConvulsiones = null;
	private JLabel labelAtaxia = null;
	private JTextField campoAtaxia = null;
	private JLabel labelReflejos = null;
	private JTextField campoReflejos = null;
	private JLabel labelSensibilidad = null;  
	private JTextField campoSensibilidad = null;
	private JLabel labelConducta = null;
	private JTextField campoConducta = null;
	private JPanel panelDatosNervioso2 = null;
	private JLabel labelObservacion = null;
	private JPanel panelTextoNervioso = null;
	private JLabel labelCirculatorio = null;
	private JTextArea campoObservacion = null;
	private JLabel labelSensorio = null;
	private JLabel labelOtros = null;
	private JTextField campoOtros = null;
	private JTextField campoSensorio = null;
	private JScrollPane panelScrollObservacion;

	/**
	 * This is the default constructor
	 */
	public PanelNervioso() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(640, 250);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(640, 250));
		this.setMaximumSize(new Dimension(640, 250));
		this.setMinimumSize(new Dimension(640, 250));
		this.add(getPanelTextoNervioso(), BorderLayout.NORTH);
		this.add(getPanelDatosNervioso(), BorderLayout.CENTER);
	}

	/**
	 * This method initializes panelDatosNervioso	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatosNervioso() {
		if (panelDatosNervioso == null) {
			panelDatosNervioso = new JPanel();
			panelDatosNervioso.setLayout(new BorderLayout());
			panelDatosNervioso.add(getPanelDatosNervioso1(), BorderLayout.NORTH);
			panelDatosNervioso.add(getPanelDatosNervioso2(), BorderLayout.CENTER);
		}
		return panelDatosNervioso;
	}

	/**
	 * This method initializes panelDatosNervioso1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatosNervioso1() {
		if (panelDatosNervioso1 == null) {
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints41.gridy = 2;
			gridBagConstraints41.weightx = 1.0;
			gridBagConstraints41.gridx = 3;
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints31.gridy = 3;
			gridBagConstraints31.weightx = 1.0;
			gridBagConstraints31.insets = new Insets(0, 0, 5, 0);
			gridBagConstraints31.gridx = 3;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 2;
			gridBagConstraints21.gridy = 3;
			labelOtros = new JLabel();
			labelOtros.setText("Otros:");
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.gridx = 2;
			gridBagConstraints13.gridy = 2;
			labelSensorio = new JLabel();
			labelSensorio.setText("Sensorio:");
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints11.gridy = 1;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints11.gridx = 3;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 2;
			gridBagConstraints10.insets = new Insets(0, 0, 5, 0);
			gridBagConstraints10.gridy = 1;
			labelConducta = new JLabel();
			labelConducta.setText("Conducta:");
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints9.gridy = 0;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.insets = new Insets(5, 0, 0, 0);
			gridBagConstraints9.gridx = 3;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 2;
			gridBagConstraints8.gridy = 0;
			labelSensibilidad = new JLabel();
			labelSensibilidad.setText("Sensibilidad:");
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints7.gridy = 3;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.insets = new Insets(0, 0, 5, 0);
			gridBagConstraints7.gridx = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 3;
			labelReflejos = new JLabel();
			labelReflejos.setText("Reflejos:");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 2;
			labelAtaxia = new JLabel();
			labelAtaxia.setText("Ataxia:");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 1;
			labelConvulsiones = new JLabel();
			labelConvulsiones.setText("Convulsiones:");
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
			labelParalisis = new JLabel();
			labelParalisis.setText("Paralisis:");
			panelDatosNervioso1 = new JPanel();
			panelDatosNervioso1.setLayout(new GridBagLayout());
			panelDatosNervioso1.add(labelParalisis, gridBagConstraints);
			panelDatosNervioso1.add(getCampoParalisis(), gridBagConstraints1);
			panelDatosNervioso1.add(labelConvulsiones, gridBagConstraints2);
			panelDatosNervioso1.add(getCampoConvulsiones(), gridBagConstraints3);
			panelDatosNervioso1.add(labelAtaxia, gridBagConstraints4);
			panelDatosNervioso1.add(getCampoAtaxia(), gridBagConstraints5);
			panelDatosNervioso1.add(labelReflejos, gridBagConstraints6);
			panelDatosNervioso1.add(getCampoReflejos(), gridBagConstraints7);
			panelDatosNervioso1.add(labelSensibilidad, gridBagConstraints8);
			panelDatosNervioso1.add(getCampoSensibilidad(), gridBagConstraints9);
			panelDatosNervioso1.add(labelConducta, gridBagConstraints10);
			panelDatosNervioso1.add(getCampoConducta(), gridBagConstraints11);
			panelDatosNervioso1.add(labelSensorio, gridBagConstraints13);
			panelDatosNervioso1.add(labelOtros, gridBagConstraints21);
			panelDatosNervioso1.add(getCampoOtros(), gridBagConstraints31);
			panelDatosNervioso1.add(getCampoSensorio(), gridBagConstraints41);
		}
		return panelDatosNervioso1;
	}

	/**
	 * This method initializes campoParalisis	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoParalisis() {
		if (campoParalisis == null) {
			campoParalisis = new JTextField();
			campoParalisis.setPreferredSize(new Dimension(500, 20));
			campoParalisis.setMinimumSize(new Dimension(500, 20));
		}
		return campoParalisis;
	}

	/**
	 * This method initializes campoAscultacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoConvulsiones() {
		if (campoConvulsiones == null) {
			campoConvulsiones = new JTextField();
			campoConvulsiones.setPreferredSize(new Dimension(500, 20));
			campoConvulsiones.setMinimumSize(new Dimension(500, 20));
		}
		return campoConvulsiones;
	}

	/**
	 * This method initializes campoPulso	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoAtaxia() {
		if (campoAtaxia == null) {
			campoAtaxia = new JTextField();
			campoAtaxia.setPreferredSize(new Dimension(500, 20));
			campoAtaxia.setMinimumSize(new Dimension(500, 20));
		}
		return campoAtaxia;
	}

	/**
	 * This method initializes campoReflejos	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoReflejos() {
		if (campoReflejos == null) {
			campoReflejos = new JTextField();
			campoReflejos.setPreferredSize(new Dimension(500, 20));
			campoReflejos.setMinimumSize(new Dimension(500, 20));
		}
		return campoReflejos;
	}

	/**
	 * This method initializes campoSensibilidad	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoSensibilidad() {
		if (campoSensibilidad == null) {
			campoSensibilidad = new JTextField();
			campoSensibilidad.setPreferredSize(new Dimension(500, 20));
			campoSensibilidad.setMinimumSize(new Dimension(500, 20));
		}
		return campoSensibilidad;
	}

	/**
	 * This method initializes campoConducta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoConducta() {
		if (campoConducta == null) {
			campoConducta = new JTextField();
			campoConducta.setPreferredSize(new Dimension(500, 20));
			campoConducta.setMinimumSize(new Dimension(500, 20));
		}
		return campoConducta;
	}

	/**
	 * This method initializes panelDatosNervioso2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatosNervioso2() {
		if (panelDatosNervioso2 == null) {
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
			panelDatosNervioso2 = new JPanel();
			panelDatosNervioso2.setLayout(new GridBagLayout());
			panelDatosNervioso2.add(labelObservacion, gridBagConstraints14);
			panelDatosNervioso2.add(getPanelScrollObservacion(), gridBagConstraints12);
		}
		return panelDatosNervioso2;
	}

	/**
	 * This method initializes panelTextoNervioso	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelTextoNervioso() {
		if (panelTextoNervioso == null) {
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.gridx = 0;
			gridBagConstraints20.gridy = 0;
			labelCirculatorio = new JLabel();
			labelCirculatorio.setText("Nervioso");
			labelCirculatorio.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			panelTextoNervioso = new JPanel();
			panelTextoNervioso.setLayout(new GridBagLayout());
			panelTextoNervioso.add(labelCirculatorio, gridBagConstraints20);
		}
		return panelTextoNervioso;
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
	 * This method initializes campoOtros	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoOtros() {
		if (campoOtros == null) {
			campoOtros = new JTextField();
			campoOtros.setMinimumSize(new Dimension(500, 20));
			campoOtros.setPreferredSize(new Dimension(500, 20));
		}
		return campoOtros;
	}

	/**
	 * This method initializes campoSensorio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoSensorio() {
		if (campoSensorio == null) {
			campoSensorio = new JTextField();
			campoSensorio.setMinimumSize(new Dimension(500, 20));
			campoSensorio.setPreferredSize(new Dimension(500, 20));
		}
		return campoSensorio;
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
