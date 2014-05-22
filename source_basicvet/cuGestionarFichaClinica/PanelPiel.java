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

public class PanelPiel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelDatosPiel = null;
	private JPanel panelDatosPiel1 = null;
	private JLabel labelTipoLesion = null;
	private JTextField campoTipoLesion = null;
	private JLabel labelForma = null;
	private JTextField campoForma = null;
	private JLabel labelUbicacion = null;
	private JTextField campoUbicacion = null;
	private JLabel labelSimetrica = null;
	private JTextField campoSimetrica = null;
	private JLabel labelOlor = null;
	private JTextField campoOlor = null;
	private JLabel labelPrurito = null;
	private JTextField campoPrurito = null;
	private JPanel panelDatosPiel2 = null;
	private JLabel labelObservacion = null;  
	private JPanel panelTextoPiel = null;
	private JLabel labelCirculatorio = null;
	private JTextArea campoObservacion = null;
	private JLabel labelMantoPiloso = null;
	private JLabel labelEctoparasitos = null;
	private JTextField campoEctoparasitos = null;
	private JTextField campoMantoPiloso = null;
	private JLabel labelOidoIzquierdo = null;
	private JLabel labelOidoDerecho = null;
	private JTextField campoOidoIzquierdo = null;
	private JTextField campoOidoDerecho = null;
	private JScrollPane panelScrollObservacion;

	/**
	 * This is the default constructor
	 */
	public PanelPiel() {
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
		this.add(getPanelTextoPiel(), BorderLayout.NORTH);
		this.add(getPanelDatosPiel(), BorderLayout.CENTER);
	}

	/**
	 * This method initializes panelDatosPiel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatosPiel() {
		if (panelDatosPiel == null) {
			panelDatosPiel = new JPanel();
			panelDatosPiel.setLayout(new BorderLayout());
			panelDatosPiel.add(getPanelDatosPiel1(), BorderLayout.NORTH);
			panelDatosPiel.add(getPanelDatosPiel2(), BorderLayout.CENTER);
		}
		return panelDatosPiel;
	}

	/**
	 * This method initializes panelDatosPiel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatosPiel1() {
		if (panelDatosPiel1 == null) {
			GridBagConstraints gridBagConstraints81 = new GridBagConstraints();
			gridBagConstraints81.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints81.gridy = 4;
			gridBagConstraints81.weightx = 1.0;
			gridBagConstraints81.insets = new Insets(0, 0, 5, 5);
			gridBagConstraints81.gridx = 3;
			GridBagConstraints gridBagConstraints71 = new GridBagConstraints();
			gridBagConstraints71.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints71.gridy = 3;
			gridBagConstraints71.weightx = 1.0;
			gridBagConstraints71.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints71.gridx = 3;
			GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
			gridBagConstraints61.gridx = 2;
			gridBagConstraints61.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints61.gridy = 4;
			labelOidoDerecho = new JLabel();
			labelOidoDerecho.setText("Oido Derecho:");
			GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
			gridBagConstraints51.gridx = 2;
			gridBagConstraints51.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints51.gridy = 3;
			labelOidoIzquierdo = new JLabel();
			labelOidoIzquierdo.setText("Oido Izquierdo:");
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints41.gridy = 1;
			gridBagConstraints41.weightx = 1.0;
			gridBagConstraints41.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints41.gridx = 3;
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints31.gridy = 2;
			gridBagConstraints31.weightx = 1.0;
			gridBagConstraints31.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints31.gridx = 3;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 2;
			gridBagConstraints21.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints21.gridy = 2;
			labelEctoparasitos = new JLabel();
			labelEctoparasitos.setText("Ectoparasitos:");
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.gridx = 2;
			gridBagConstraints13.gridy = 1;
			labelMantoPiloso = new JLabel();
			labelMantoPiloso.setText("Manto Piloso:");
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints11.gridy = 0;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.insets = new Insets(5, 0, 0, 5);
			gridBagConstraints11.gridx = 3;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 2;
			gridBagConstraints10.insets = new Insets(0, 0, 5, 0);
			gridBagConstraints10.gridy = 0;
			labelPrurito = new JLabel();
			labelPrurito.setText("Prurito:");
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints9.gridy = 4;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.insets = new Insets(0, 0, 5, 0);
			gridBagConstraints9.gridx = 1;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.gridy = 4;
			labelOlor = new JLabel();
			labelOlor.setText("Olor:");
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints7.gridy = 3;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.gridx = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 3;
			labelSimetrica = new JLabel();
			labelSimetrica.setText("Simetrica:");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 2;
			labelUbicacion = new JLabel();
			labelUbicacion.setText("Ubicacion:");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 1;
			labelForma = new JLabel();
			labelForma.setText("Forma:");
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
			labelTipoLesion = new JLabel();
			labelTipoLesion.setText("Tipo Lesion:");
			panelDatosPiel1 = new JPanel();
			panelDatosPiel1.setLayout(new GridBagLayout());
			panelDatosPiel1.add(labelTipoLesion, gridBagConstraints);
			panelDatosPiel1.add(getCampoTipoLesion(), gridBagConstraints1);
			panelDatosPiel1.add(labelForma, gridBagConstraints2);
			panelDatosPiel1.add(getCampoForma(), gridBagConstraints3);
			panelDatosPiel1.add(labelUbicacion, gridBagConstraints4);
			panelDatosPiel1.add(getCampoUbicacion(), gridBagConstraints5);
			panelDatosPiel1.add(labelSimetrica, gridBagConstraints6);
			panelDatosPiel1.add(getCampoSimetrica(), gridBagConstraints7);
			panelDatosPiel1.add(labelOlor, gridBagConstraints8);
			panelDatosPiel1.add(getCampoOlor(), gridBagConstraints9);
			panelDatosPiel1.add(labelPrurito, gridBagConstraints10);
			panelDatosPiel1.add(getCampoPrurito(), gridBagConstraints11);
			panelDatosPiel1.add(labelMantoPiloso, gridBagConstraints13);
			panelDatosPiel1.add(labelEctoparasitos, gridBagConstraints21);
			panelDatosPiel1.add(getCampoEctoparasitos(), gridBagConstraints31);
			panelDatosPiel1.add(getCampoMantoPiloso(), gridBagConstraints41);
			panelDatosPiel1.add(labelOidoIzquierdo, gridBagConstraints51);
			panelDatosPiel1.add(labelOidoDerecho, gridBagConstraints61);
			panelDatosPiel1.add(getCampoOidoIzquierdo(), gridBagConstraints71);
			panelDatosPiel1.add(getCampoOidoDerecho(), gridBagConstraints81);
		}
		return panelDatosPiel1;
	}

	/**
	 * This method initializes campoTipoLesion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoTipoLesion() {
		if (campoTipoLesion == null) {
			campoTipoLesion = new JTextField();
			campoTipoLesion.setPreferredSize(new Dimension(500, 20));
			campoTipoLesion.setMinimumSize(new Dimension(500, 20));
		}
		return campoTipoLesion;
	}

	/**
	 * This method initializes campoForma	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoForma() {
		if (campoForma == null) {
			campoForma = new JTextField();
			campoForma.setPreferredSize(new Dimension(500, 20));
			campoForma.setMinimumSize(new Dimension(500, 20));
		}
		return campoForma;
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
	 * This method initializes campoSimetrica	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoSimetrica() {
		if (campoSimetrica == null) {
			campoSimetrica = new JTextField();
			campoSimetrica.setPreferredSize(new Dimension(500, 20));
			campoSimetrica.setMinimumSize(new Dimension(500, 20));
		}
		return campoSimetrica;
	}

	/**
	 * This method initializes campoOlor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoOlor() {
		if (campoOlor == null) {
			campoOlor = new JTextField();
			campoOlor.setPreferredSize(new Dimension(500, 20));
			campoOlor.setMinimumSize(new Dimension(500, 20));
		}
		return campoOlor;
	}

	/**
	 * This method initializes campoPrurito	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoPrurito() {
		if (campoPrurito == null) {
			campoPrurito = new JTextField();
			campoPrurito.setPreferredSize(new Dimension(500, 20));
			campoPrurito.setMinimumSize(new Dimension(500, 20));
		}
		return campoPrurito;
	}

	/**
	 * This method initializes panelDatosPiel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatosPiel2() {
		if (panelDatosPiel2 == null) {
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
			panelDatosPiel2 = new JPanel();
			panelDatosPiel2.setLayout(new GridBagLayout());
			panelDatosPiel2.add(labelObservacion, gridBagConstraints14);
			panelDatosPiel2.add(getPanelScrollObservacion(), gridBagConstraints12);
		}
		return panelDatosPiel2;
	}

	/**
	 * This method initializes panelTextoPiel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelTextoPiel() {
		if (panelTextoPiel == null) {
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.gridx = 0;
			gridBagConstraints20.gridy = 0;
			labelCirculatorio = new JLabel();
			labelCirculatorio.setText("Piel");
			labelCirculatorio.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			panelTextoPiel = new JPanel();
			panelTextoPiel.setLayout(new GridBagLayout());
			panelTextoPiel.add(labelCirculatorio, gridBagConstraints20);
		}
		return panelTextoPiel;
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
	 * This method initializes campoEctoparasitos	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoEctoparasitos() {
		if (campoEctoparasitos == null) {
			campoEctoparasitos = new JTextField();
			campoEctoparasitos.setMinimumSize(new Dimension(500, 20));
			campoEctoparasitos.setPreferredSize(new Dimension(500, 20));
		}
		return campoEctoparasitos;
	}

	/**
	 * This method initializes campoMantoPiloso	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoMantoPiloso() {
		if (campoMantoPiloso == null) {
			campoMantoPiloso = new JTextField();
			campoMantoPiloso.setMinimumSize(new Dimension(500, 20));
			campoMantoPiloso.setPreferredSize(new Dimension(500, 20));
		}
		return campoMantoPiloso;
	}

	/**
	 * This method initializes campoOidoIzquierdo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoOidoIzquierdo() {
		if (campoOidoIzquierdo == null) {
			campoOidoIzquierdo = new JTextField();
			campoOidoIzquierdo.setMinimumSize(new Dimension(500, 20));
			campoOidoIzquierdo.setPreferredSize(new Dimension(500, 20));
		}
		return campoOidoIzquierdo;
	}

	/**
	 * This method initializes campoOidoDerecho	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoOidoDerecho() {
		if (campoOidoDerecho == null) {
			campoOidoDerecho = new JTextField();
			campoOidoDerecho.setMinimumSize(new Dimension(500, 20));
			campoOidoDerecho.setPreferredSize(new Dimension(500, 20));
		}
		return campoOidoDerecho;
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
