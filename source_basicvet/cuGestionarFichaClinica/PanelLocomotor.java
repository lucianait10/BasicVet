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

public class PanelLocomotor extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelDatosLocomotor = null;
	private JPanel panelDatosLocomotor1 = null;
	private JLabel labelLesion = null;
	private JTextField campoLesion = null;
	private JLabel labelUbicacion = null;
	private JTextField campoUbicacion = null;
	private JPanel panelDatosLocomotor2 = null;
	private JLabel labelObservacion = null;
	private JPanel panelTextoLocomotor = null;
	private JLabel labelCirculatorio = null;
	private JTextArea campoObservacion = null;  
	private JScrollPane panelScrollObservacion;

	public PanelLocomotor() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(640, 250);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(640, 250));
		this.setMaximumSize(new Dimension(640, 250));
		this.setMinimumSize(new Dimension(640, 250));
		this.add(getPanelTextoLocomotor(), BorderLayout.NORTH);
		this.add(getPanelDatosLocomotor(), BorderLayout.CENTER);
	}

	private JPanel getPanelDatosLocomotor() {
		if (panelDatosLocomotor == null) {
			panelDatosLocomotor = new JPanel();
			panelDatosLocomotor.setLayout(new BorderLayout());
			panelDatosLocomotor.add(getPanelDatosLocomotor1(), BorderLayout.NORTH);
			panelDatosLocomotor.add(getPanelDatosLocomotor2(), BorderLayout.CENTER);
		}
		return panelDatosLocomotor;
	}

	private JPanel getPanelDatosLocomotor1() {
		if (panelDatosLocomotor1 == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.insets = new Insets(0, 0, 5, 0);
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 1;
			labelUbicacion = new JLabel();
			labelUbicacion.setText("Ubicacion:");
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
			labelLesion = new JLabel();
			labelLesion.setText("Lesion:");
			panelDatosLocomotor1 = new JPanel();
			panelDatosLocomotor1.setLayout(new GridBagLayout());
			panelDatosLocomotor1.add(labelLesion, gridBagConstraints);
			panelDatosLocomotor1.add(getCampoLesion(), gridBagConstraints1);
			panelDatosLocomotor1.add(labelUbicacion, gridBagConstraints2);
			panelDatosLocomotor1.add(getCampoUbicacion(), gridBagConstraints3);
		}
		return panelDatosLocomotor1;
	}

	/**
	 * This method initializes campoLesion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoLesion() {
		if (campoLesion == null) {
			campoLesion = new JTextField();
			campoLesion.setPreferredSize(new Dimension(500, 20));
			campoLesion.setMinimumSize(new Dimension(500, 20));
		}
		return campoLesion;
	}

	/**
	 * This method initializes campoUbicacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoUbicacion() {
		if (campoUbicacion == null) {
			campoUbicacion = new JTextField();
			campoUbicacion.setPreferredSize(new Dimension(500, 20));
			campoUbicacion.setMinimumSize(new Dimension(500, 20));
		}
		return campoUbicacion;
	}

	/**
	 * This method initializes panelDatosLocomotor2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatosLocomotor2() {
		if (panelDatosLocomotor2 == null) {
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
			panelDatosLocomotor2 = new JPanel();
			panelDatosLocomotor2.setLayout(new GridBagLayout());
			panelDatosLocomotor2.add(labelObservacion, gridBagConstraints14);
			panelDatosLocomotor2.add(getPanelScrollObservacion(), gridBagConstraints12);
		}
		return panelDatosLocomotor2;
	}

	/**
	 * This method initializes panelTextoLocomotor	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelTextoLocomotor() {
		if (panelTextoLocomotor == null) {
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.gridx = 0;
			gridBagConstraints20.gridy = 0;
			labelCirculatorio = new JLabel();
			labelCirculatorio.setText("Locomotor:");
			labelCirculatorio.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			panelTextoLocomotor = new JPanel();
			panelTextoLocomotor.setLayout(new GridBagLayout());
			panelTextoLocomotor.add(labelCirculatorio, gridBagConstraints20);
		}
		return panelTextoLocomotor;
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
