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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelExploracionGeneral extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelDatosExpGeneral = null;
	private JPanel panelDatosGenerales2 = null;
	private JLabel labelEstadoGeneral = null;
	private JTextField campoEstadoGeneral = null;
	private JLabel labelMucosaOcular = null;  
	private JTextField campoMucosaOcular = null;
	private JLabel labelMucosaGenital = null;
	private JTextField campoMucosaGenital = null;
	private JLabel labelMucosaBucal = null;
	private JTextField campoMucosaBucal = null;
	private JLabel labelMucosaOtros = null;
	private JTextField campoMucosaOtros = null;
	private JLabel labelHidratacion = null;
	private JTextField campoHidratacion = null;
	private JLabel labelGangliosLinfaticos = null;
	private JTextField campoGangliosLinfaticos = null;
	private JPanel panelDatosGenerales1 = null;
	private JLabel labelTempertura = null;
	private JTextField campoTemperatura = null;
	private JLabel labelFrecuenciaCardiaca = null;
	private JTextField campoFrecuenciaCardiaca = null;
	private JLabel labelFrecuenciaRespiratoria = null;
	private JTextField campoFrecuenciaRespiratoria = null;
	private JPanel panelTextoExploracion = null;
	private JLabel labelExploracionGeneral = null;

	public PanelExploracionGeneral() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(685, 250);
		this.setLayout(new BorderLayout());
		this.setMaximumSize(new Dimension(685, 250));
		this.setPreferredSize(new Dimension(685, 250));
		this.setMinimumSize(new Dimension(685, 250));
		this.add(getPanelTextoExploracion(), BorderLayout.NORTH);
		this.add(getPanelDatosExpGeneral(), BorderLayout.CENTER);
	}

	private JPanel getPanelDatosExpGeneral() {
		if (panelDatosExpGeneral == null) {
			panelDatosExpGeneral = new JPanel();
			panelDatosExpGeneral.setLayout(new BorderLayout());
			panelDatosExpGeneral.add(getPanelDatosGenerales1(), BorderLayout.NORTH);
			panelDatosExpGeneral.add(getPanelDatosGenerales2(), BorderLayout.CENTER);
		}
		return panelDatosExpGeneral;
	}

	private JPanel getPanelDatosGenerales2() {
		if (panelDatosGenerales2 == null) {
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints13.gridy = 6;
			gridBagConstraints13.weightx = 1.0;
			gridBagConstraints13.gridx = 1;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 0;
			gridBagConstraints12.insets = new Insets(0, 5, 0, 5);
			gridBagConstraints12.gridy = 6;
			labelGangliosLinfaticos = new JLabel();
			labelGangliosLinfaticos.setText("Ganglios Linfaticos:");
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints11.gridy = 5;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.gridx = 1;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.gridy = 5;
			labelHidratacion = new JLabel();
			labelHidratacion.setText("Hidratacion:");
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints9.gridy = 4;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.gridx = 1;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.gridy = 4;
			labelMucosaOtros = new JLabel();
			labelMucosaOtros.setText("Mucosa Otros:");
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints7.gridy = 3;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.gridx = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 3;
			labelMucosaBucal = new JLabel();
			labelMucosaBucal.setText("Mucosa Bucal:");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 2;
			labelMucosaGenital = new JLabel();
			labelMucosaGenital.setText("Mucosa Genital:");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 1;
			labelMucosaOcular = new JLabel();
			labelMucosaOcular.setText("Mucosa Ocular:");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			labelEstadoGeneral = new JLabel();
			labelEstadoGeneral.setText("Estado General:");
			panelDatosGenerales2 = new JPanel();
			panelDatosGenerales2.setLayout(new GridBagLayout());
			panelDatosGenerales2.add(labelEstadoGeneral, gridBagConstraints);
			panelDatosGenerales2.add(getCampoEstadoGeneral(), gridBagConstraints1);
			panelDatosGenerales2.add(labelMucosaOcular, gridBagConstraints2);
			panelDatosGenerales2.add(getCampoMucosaOcular(), gridBagConstraints3);
			panelDatosGenerales2.add(labelMucosaGenital, gridBagConstraints4);
			panelDatosGenerales2.add(getCampoMucosaGenital(), gridBagConstraints5);
			panelDatosGenerales2.add(labelMucosaBucal, gridBagConstraints6);
			panelDatosGenerales2.add(getCampoMucosaBucal(), gridBagConstraints7);
			panelDatosGenerales2.add(labelMucosaOtros, gridBagConstraints8);
			panelDatosGenerales2.add(getCampoMucosaOtros(), gridBagConstraints9);
			panelDatosGenerales2.add(labelHidratacion, gridBagConstraints10);
			panelDatosGenerales2.add(getCampoHidratacion(), gridBagConstraints11);
			panelDatosGenerales2.add(labelGangliosLinfaticos, gridBagConstraints12);
			panelDatosGenerales2.add(getCampoGangliosLinfaticos(), gridBagConstraints13);
		}
		return panelDatosGenerales2;
	}

	public JTextField getCampoEstadoGeneral() {
		if (campoEstadoGeneral == null) {
			campoEstadoGeneral = new JTextField();
			campoEstadoGeneral.setPreferredSize(new Dimension(500, 20));
			campoEstadoGeneral.setMinimumSize(new Dimension(500, 20));
		}
		return campoEstadoGeneral;
	}

	/**
	 * This method initializes campoMucosaOcular	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoMucosaOcular() {
		if (campoMucosaOcular == null) {
			campoMucosaOcular = new JTextField();
			campoMucosaOcular.setPreferredSize(new Dimension(500, 20));
			campoMucosaOcular.setMinimumSize(new Dimension(500, 20));
		}
		return campoMucosaOcular;
	}

	/**
	 * This method initializes campoMucosaGenital	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoMucosaGenital() {
		if (campoMucosaGenital == null) {
			campoMucosaGenital = new JTextField();
			campoMucosaGenital.setPreferredSize(new Dimension(500, 20));
			campoMucosaGenital.setMinimumSize(new Dimension(500, 20));
		}
		return campoMucosaGenital;
	}

	/**
	 * This method initializes campoMucosaBucal	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoMucosaBucal() {
		if (campoMucosaBucal == null) {
			campoMucosaBucal = new JTextField();
			campoMucosaBucal.setPreferredSize(new Dimension(500, 20));
			campoMucosaBucal.setMinimumSize(new Dimension(500, 20));
		}
		return campoMucosaBucal;
	}

	/**
	 * This method initializes campoMucosaOtros	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoMucosaOtros() {
		if (campoMucosaOtros == null) {
			campoMucosaOtros = new JTextField();
			campoMucosaOtros.setPreferredSize(new Dimension(500, 20));
			campoMucosaOtros.setMinimumSize(new Dimension(500, 20));
		}
		return campoMucosaOtros;
	}

	/**
	 * This method initializes campoHidratacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoHidratacion() {
		if (campoHidratacion == null) {
			campoHidratacion = new JTextField();
			campoHidratacion.setPreferredSize(new Dimension(500, 20));
			campoHidratacion.setMinimumSize(new Dimension(500, 20));
		}
		return campoHidratacion;
	}

	/**
	 * This method initializes campoGangliosLinfaticos	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoGangliosLinfaticos() {
		if (campoGangliosLinfaticos == null) {
			campoGangliosLinfaticos = new JTextField();
			campoGangliosLinfaticos.setPreferredSize(new Dimension(500, 20));
			campoGangliosLinfaticos.setMinimumSize(new Dimension(500, 20));
		}
		return campoGangliosLinfaticos;
	}

	/**
	 * This method initializes panelDatosGenerales1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatosGenerales1() {
		if (panelDatosGenerales1 == null) {
			GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
			gridBagConstraints19.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints19.gridy = 0;
			gridBagConstraints19.weightx = 1.0;
			gridBagConstraints19.insets = new Insets(10, 0, 5, 0);
			gridBagConstraints19.gridx = 5;
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			gridBagConstraints18.gridx = 4;
			gridBagConstraints18.insets = new Insets(10, 0, 5, 0);
			gridBagConstraints18.gridy = 0;
			labelFrecuenciaRespiratoria = new JLabel();
			labelFrecuenciaRespiratoria.setText("Frecuencia Respiratoria:");
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints17.gridy = 0;
			gridBagConstraints17.weightx = 1.0;
			gridBagConstraints17.insets = new Insets(10, 0, 5, 0);
			gridBagConstraints17.gridx = 3;
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.gridx = 2;
			gridBagConstraints16.insets = new Insets(10, 0, 5, 0);
			gridBagConstraints16.gridy = 0;
			labelFrecuenciaCardiaca = new JLabel();
			labelFrecuenciaCardiaca.setText("Frecuencia Cardiaca:");
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints15.gridy = 0;
			gridBagConstraints15.weightx = 1.0;
			gridBagConstraints15.insets = new Insets(10, 0, 5, 0);
			gridBagConstraints15.gridx = 1;
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.gridx = 0;
			gridBagConstraints14.insets = new Insets(10, 5, 5, 0);
			gridBagConstraints14.gridy = 0;
			labelTempertura = new JLabel();
			labelTempertura.setText("Temperatura:");
			panelDatosGenerales1 = new JPanel();
			panelDatosGenerales1.setLayout(new GridBagLayout());
			panelDatosGenerales1.add(labelTempertura, gridBagConstraints14);
			panelDatosGenerales1.add(getCampoTemperatura(), gridBagConstraints15);
			panelDatosGenerales1.add(labelFrecuenciaCardiaca, gridBagConstraints16);
			panelDatosGenerales1.add(getCampoFrecuenciaCardiaca(), gridBagConstraints17);
			panelDatosGenerales1.add(labelFrecuenciaRespiratoria, gridBagConstraints18);
			panelDatosGenerales1.add(getCampoFrecuenciaRespiratoria(), gridBagConstraints19);
		}
		return panelDatosGenerales1;
	}

	/**
	 * This method initializes campoTemperatura	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoTemperatura() {
		if (campoTemperatura == null) {
			campoTemperatura = new JTextField();
			campoTemperatura.setPreferredSize(new Dimension(100, 20));
		}
		return campoTemperatura;
	}

	/**
	 * This method initializes campoFrecuenciaCardiaca	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoFrecuenciaCardiaca() {
		if (campoFrecuenciaCardiaca == null) {
			campoFrecuenciaCardiaca = new JTextField();
			campoFrecuenciaCardiaca.setPreferredSize(new Dimension(100, 20));
		}
		return campoFrecuenciaCardiaca;
	}

	/**
	 * This method initializes campoFrecuenciaRespiratoria	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoFrecuenciaRespiratoria() {
		if (campoFrecuenciaRespiratoria == null) {
			campoFrecuenciaRespiratoria = new JTextField();
			campoFrecuenciaRespiratoria.setPreferredSize(new Dimension(100, 20));
		}
		return campoFrecuenciaRespiratoria;
	}

	/**
	 * This method initializes panelTextoExploracion	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelTextoExploracion() {
		if (panelTextoExploracion == null) {
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.gridx = 0;
			gridBagConstraints20.gridy = 0;
			labelExploracionGeneral = new JLabel();
			labelExploracionGeneral.setText("Exploracion General");
			labelExploracionGeneral.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			panelTextoExploracion = new JPanel();
			panelTextoExploracion.setLayout(new GridBagLayout());
			panelTextoExploracion.add(labelExploracionGeneral, gridBagConstraints20);
		}
		return panelTextoExploracion;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
