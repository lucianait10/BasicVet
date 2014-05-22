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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;

public class GUIInterfazFichaClinica extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelEncabezado = null;
	private JPanel panelDatos = null;
	private JPanel panelDatosAnamnesis = null;
	private JPanel panelDatosOtros = null;
	private JTextField campoId = null;
	private JTextField campoNombre = null;
	private JTextField campoRaza = null;
	private JTextField campoEdad = null;
	private JLabel labelDiagnosticoDiferencial = null;
	private JTextField campoDiagnosticoDiferencial = null;
	private JLabel labelDiagnosticoPresuntivo = null;
	private JTextField campoDiagnosticoPresuntivo = null;
	private JLabel labelPronostico = null;
	private JTextField campoPronostico = null;
	private JLabel labelManejoClinicoTerapeutico = null;
	private JTextField campoManejoClinicoTerapeutico = null;
	private JLabel labelAnamnesis = null;
	private JTextArea campoAnamnesis = null;
	private JTextField campoNumeroFicha = null;
	private JLabel jLabel = null;
	private JTextField campoFecha = null;
	private JPanel panelDatosAnimal = null;
	private JLabel labelNumero = null;
	private JLabel labelNombre = null;
	private JLabel labelRaza = null;
	private JLabel labelEdad = null;
	private JLabel labelNumeroFicha = null;
	private JLabel labelFecha = null;
	private JTabbedPane panelExploraciones = null;
	private JPanel PanelExploracionesBoton = null;
	private JButton botonGuardar = null;
	private PanelExploracionGeneral panelExploracionGeneral = new PanelExploracionGeneral();
	private PanelCirculatorio panelCirculatorio = new PanelCirculatorio();
	private PanelDigestivo panelDigestivo = new PanelDigestivo();
	private PanelRespiratorio panelRespiratorio = new PanelRespiratorio();
	private PanelGenital panelGenital = new PanelGenital();
	private PanelLocomotor panelLocomotor = new PanelLocomotor();
	private PanelNervioso panelNervioso = new PanelNervioso();
	private PanelOjos panelOjos = new PanelOjos();
	private PanelPiel panelPiel = new PanelPiel();
	private PanelUrinario panelUrinario = new PanelUrinario();
	private int nemonic[] = {KeyEvent.VK_E,KeyEvent.VK_C,
							 KeyEvent.VK_D,KeyEvent.VK_R,
							 KeyEvent.VK_G,KeyEvent.VK_L,
							 KeyEvent.VK_N,KeyEvent.VK_O,
							 KeyEvent.VK_P,KeyEvent.VK_U};
	private Object[][] titulo = {{"Exp General",panelExploracionGeneral},
			{"Circulatorio",panelCirculatorio},
			{"Digestivo",panelDigestivo},
			{"Respiratorio",panelRespiratorio},
			{"Genital",panelGenital},
			{"Locomotor",panelLocomotor},
			{"Nervioso",panelNervioso},
			{"Ojos",panelOjos},
			{"Piel",panelPiel},
			{"Urinario",panelUrinario}};
	private JPanel panelBotones = null;
	private JButton botonCerrar = null;
	private JScrollPane panelScrollObservacion;
	private JPanel panelAnamnesisDatos = null;
	private JButton botonEstudios = null;
	private GUIGestionarFichaClinica gesFichaClinica;

	public GUIInterfazFichaClinica(GUIGestionarFichaClinica gesFichaClinica) {
		super();
		this.gesFichaClinica = gesFichaClinica;
		cerrarVentana();
		initialize();
	}

	@SuppressWarnings("serial")
	private void cerrarVentana(){
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel"); //$NON-NLS-1$
	    getRootPane().getActionMap().put("Cancel", new AbstractAction(){ //$NON-NLS-1$
	        
			public void actionPerformed(ActionEvent e){
	        	setVisible(false);
	        	dispose();
	        	gesFichaClinica.setVisible(true);
	        }
	    });
	    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	    addWindowListener(new WindowAdapter() {
	    	
			public void windowClosing(WindowEvent evt){
	    		setVisible(false);
	    		dispose();
	    		gesFichaClinica.setVisible(true);
	    	}
	    });
	}
	private void initialize(){
		this.setSize(800, 600);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("FICHA CLINICA");
		this.setLocationRelativeTo(null);
		this.setModal(true);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelEncabezado(), BorderLayout.NORTH);
			jContentPane.add(getPanelDatos(), BorderLayout.CENTER);
			jContentPane.add(getPanelExploracionesBoton(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	private JPanel getPanelEncabezado() {
		if (panelEncabezado == null) {
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 2;
			gridBagConstraints12.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("FICHA CLINICA");
			jLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
			panelEncabezado = new JPanel();
			panelEncabezado.setLayout(new GridBagLayout());
			panelEncabezado.add(jLabel, gridBagConstraints12);
		}
		return panelEncabezado;
	}

	private JPanel getPanelDatos() {
		if (panelDatos == null) {
			panelDatos = new JPanel();
			panelDatos.setLayout(new BorderLayout());
			panelDatos.add(getPanelDatosAnimal(), BorderLayout.NORTH);
			panelDatos.add(getPanelAnamnesisDatos(), BorderLayout.CENTER);
		}
		return panelDatos;
	}

	private JPanel getPanelDatosAnamnesis() {
		if (panelDatosAnamnesis == null) {
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.BOTH;
			gridBagConstraints9.gridy = 1;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.weighty = 1.0;
			gridBagConstraints9.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints9.gridx = 0;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.insets = new Insets(10, 0, 0, 0);
			gridBagConstraints8.gridy = 0;
			labelAnamnesis = new JLabel();
			labelAnamnesis.setText("Anamnesis:");
			panelDatosAnamnesis = new JPanel();
			panelDatosAnamnesis.setLayout(new GridBagLayout());
			panelDatosAnamnesis.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelDatosAnamnesis.add(labelAnamnesis, gridBagConstraints8);
			panelDatosAnamnesis.add(getPanelScrollObservacion(), gridBagConstraints9);
		}
		return panelDatosAnamnesis;
	}

	private JPanel getPanelDatosOtros() {
		if (panelDatosOtros == null) {
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints7.gridy = 3;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.insets = new Insets(0, 0, 10, 0);
			gridBagConstraints7.gridx = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.insets = new Insets(0, 0, 10, 0);
			gridBagConstraints6.gridy = 3;
			labelManejoClinicoTerapeutico = new JLabel();
			labelManejoClinicoTerapeutico.setText(" Manejo Clinico Terapeutico:");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 2;
			labelPronostico = new JLabel();
			labelPronostico.setText("Pronostico:");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 1;
			labelDiagnosticoPresuntivo = new JLabel();
			labelDiagnosticoPresuntivo.setText("Diagnostico Presuntivo:");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.insets = new Insets(10, 0, 0, 0);
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.insets = new Insets(10, 0, 0, 0);
			gridBagConstraints.gridy = 0;
			labelDiagnosticoDiferencial = new JLabel();
			labelDiagnosticoDiferencial.setText("Diagnostico Diferencial:");
			panelDatosOtros = new JPanel();
			panelDatosOtros.setLayout(new GridBagLayout());
			panelDatosOtros.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelDatosOtros.add(labelDiagnosticoDiferencial, gridBagConstraints);
			panelDatosOtros.add(getCampoDiagnosticoDiferencial(), gridBagConstraints1);
			panelDatosOtros.add(labelDiagnosticoPresuntivo, gridBagConstraints2);
			panelDatosOtros.add(getCampoDiagnosticoPresuntivo(), gridBagConstraints3);
			panelDatosOtros.add(labelPronostico, gridBagConstraints4);
			panelDatosOtros.add(getCampoPronostico(), gridBagConstraints5);
			panelDatosOtros.add(labelManejoClinicoTerapeutico, gridBagConstraints6);
			panelDatosOtros.add(getCampoManejoClinicoTerapeutico(), gridBagConstraints7);
		}
		return panelDatosOtros;
	}

	public JTextField getCampoId() {
		if (campoId == null) {
			campoId = new JTextField();
			campoId.setPreferredSize(new Dimension(100, 20));
			campoId.setBackground(new Color(238, 238, 238));
			campoId.setEditable(false);
		}
		return campoId;
	}

	public JTextField getCampoNombre() {
		if (campoNombre == null) {
			campoNombre = new JTextField();
			campoNombre.setPreferredSize(new Dimension(150, 20));
			campoNombre.setBackground(new Color(238, 238, 238));
			campoNombre.setEditable(false);
		}
		return campoNombre;
	}

	public JTextField getCampoRaza() {
		if (campoRaza == null) {
			campoRaza = new JTextField();
			campoRaza.setPreferredSize(new Dimension(100, 20));
			campoRaza.setBackground(new Color(238, 238, 238));
			campoRaza.setEditable(false);
		}
		return campoRaza;
	}

	public JTextField getCampoEdad() {
		if (campoEdad == null) {
			campoEdad = new JTextField();
			campoEdad.setPreferredSize(new Dimension(50, 20));
			campoEdad.setBackground(new Color(238, 238, 238));
			campoEdad.setEditable(false);
		}
		return campoEdad;
	}

	public JTextField getCampoDiagnosticoDiferencial() {
		if (campoDiagnosticoDiferencial == null) {
			campoDiagnosticoDiferencial = new JTextField();
			campoDiagnosticoDiferencial.setPreferredSize(new Dimension(300, 20));
		}
		return campoDiagnosticoDiferencial;
	}

	public JTextField getCampoDiagnosticoPresuntivo() {
		if (campoDiagnosticoPresuntivo == null) {
			campoDiagnosticoPresuntivo = new JTextField();
			campoDiagnosticoPresuntivo.setPreferredSize(new Dimension(300, 20));
		}
		return campoDiagnosticoPresuntivo;
	}

	public JTextField getCampoPronostico() {
		if (campoPronostico == null) {
			campoPronostico = new JTextField();
			campoPronostico.setPreferredSize(new Dimension(300, 20));
		}
		return campoPronostico;
	}

	public JTextField getCampoManejoClinicoTerapeutico() {
		if (campoManejoClinicoTerapeutico == null) {
			campoManejoClinicoTerapeutico = new JTextField();
			campoManejoClinicoTerapeutico.setPreferredSize(new Dimension(300, 20));
		}
		return campoManejoClinicoTerapeutico;
	}

	private JScrollPane getPanelScrollObservacion() {
		if (panelScrollObservacion == null) {
			panelScrollObservacion = new JScrollPane();
			panelScrollObservacion.setViewportView(getCampoAnamnesis());
		}
		return panelScrollObservacion;
	}
	
	public JTextArea getCampoAnamnesis() {
		if (campoAnamnesis == null) {
			campoAnamnesis = new JTextArea();
			campoAnamnesis.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			campoAnamnesis.setMaximumSize(new Dimension(640, 300));
			campoAnamnesis.setWrapStyleWord(true);
		}
		return campoAnamnesis;
	}

	public JTextField getCampoNumeroFicha() {
		if (campoNumeroFicha == null) {
			campoNumeroFicha = new JTextField();
			campoNumeroFicha.setPreferredSize(new Dimension(100, 20));
			campoNumeroFicha.setBackground(new Color(238, 238, 238));
			campoNumeroFicha.setForeground(Color.red);
			campoNumeroFicha.setEditable(false);
		}
		return campoNumeroFicha;
	}

	public JTextField getCampoFecha() {
		if (campoFecha == null) {
			campoFecha = new JTextField();
			campoFecha.setPreferredSize(new Dimension(100, 20));
			campoFecha.setBackground(new Color(238, 238, 238));
			campoFecha.setEditable(false);
		}
		return campoFecha;
	}

	private JPanel getPanelDatosAnimal() {
		if (panelDatosAnimal == null) {
			GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
			gridBagConstraints22.gridx = 4;
			gridBagConstraints22.gridy = 0;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 1;
			gridBagConstraints21.gridy = 0;
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints20.gridy = 0;
			gridBagConstraints20.weightx = 1.0;
			gridBagConstraints20.gridx = 5;
			GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
			gridBagConstraints19.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints19.gridy = 0;
			gridBagConstraints19.weightx = 1.0;
			gridBagConstraints19.anchor = GridBagConstraints.WEST;
			gridBagConstraints19.gridx = 2;
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			gridBagConstraints18.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints18.gridx = 7;
			gridBagConstraints18.gridy = 1;
			gridBagConstraints18.weightx = 1.0;
			gridBagConstraints18.insets = new Insets(5, 3, 1, 13);
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.insets = new Insets(7, 3, 3, 2);
			gridBagConstraints17.gridy = 1;
			gridBagConstraints17.gridx = 6;
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints16.gridx = 5;
			gridBagConstraints16.gridy = 1;
			gridBagConstraints16.weightx = 1.0;
			gridBagConstraints16.insets = new Insets(5, 3, 1, 2);
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.insets = new Insets(7, 3, 3, 2);
			gridBagConstraints15.gridy = 1;
			gridBagConstraints15.gridx = 4;
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints14.gridx = 3;
			gridBagConstraints14.gridy = 1;
			gridBagConstraints14.weightx = 1.0;
			gridBagConstraints14.insets = new Insets(5, 3, 1, 2);
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.insets = new Insets(7, 3, 3, 2);
			gridBagConstraints13.gridy = 1;
			gridBagConstraints13.anchor = GridBagConstraints.EAST;
			gridBagConstraints13.gridx = 2;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints11.gridx = 1;
			gridBagConstraints11.gridy = 1;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.insets = new Insets(5, 3, 1, 2);
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.insets = new Insets(7, 12, 3, 2);
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.gridx = 0;
			labelEdad = new JLabel();
			labelEdad.setText("Edad:");
			labelRaza = new JLabel();
			labelRaza.setText("Raza:");
			labelNombre = new JLabel();
			labelNombre.setText("Nombre:");
			labelNumero = new JLabel();
			labelNumero.setText("Id:");
			panelDatosAnimal = new JPanel();
			panelDatosAnimal.setLayout(new GridBagLayout());
			panelDatosAnimal.setSize(new Dimension(678, 76));
			panelDatosAnimal.setPreferredSize(new Dimension(678, 76));
			panelDatosAnimal.setMinimumSize(new Dimension(678, 76));
			panelDatosAnimal.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelDatosAnimal.add(labelNumero, gridBagConstraints10);
			panelDatosAnimal.add(getCampoId(), gridBagConstraints11);
			panelDatosAnimal.add(labelNombre, gridBagConstraints13);
			panelDatosAnimal.add(getCampoNombre(), gridBagConstraints14);
			panelDatosAnimal.add(labelRaza, gridBagConstraints15);
			panelDatosAnimal.add(getCampoRaza(), gridBagConstraints16);
			panelDatosAnimal.add(labelEdad, gridBagConstraints17);
			panelDatosAnimal.add(getCampoEdad(), gridBagConstraints18);
			panelDatosAnimal.add(getCampoNumeroFicha(), gridBagConstraints19);
			panelDatosAnimal.add(getCampoFecha(), gridBagConstraints20);
			panelDatosAnimal.add(getLabelNumeroFicha(), gridBagConstraints21);
			panelDatosAnimal.add(getLabelFecha(), gridBagConstraints22);
		}
		return panelDatosAnimal;
	}

	private JLabel getLabelNumeroFicha() {
		if (labelNumeroFicha == null) {
			labelNumeroFicha = new JLabel();
			labelNumeroFicha.setText("Numero De Ficha:");
		}
		return labelNumeroFicha;
	}

	private JLabel getLabelFecha() {
		if (labelFecha == null) {
			labelFecha = new JLabel();
			labelFecha.setText("Fecha:");
		}
		return labelFecha;
	}

	private JTabbedPane getPanelExploraciones() {
		if (panelExploraciones == null) { 
			panelExploraciones = new JTabbedPane();//JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT
			for(int i = 0;i<titulo.length;i++){
				panelExploraciones.addTab((String) titulo[i][0],(JPanel)titulo[i][1]);
				panelExploraciones.setMnemonicAt(i, nemonic[i]);
			}
		}
		return panelExploraciones;
	}
	
	private JPanel getPanelExploracionesBoton() {
		if (PanelExploracionesBoton == null) {
			PanelExploracionesBoton = new JPanel();
			PanelExploracionesBoton.setLayout(new BorderLayout());
			PanelExploracionesBoton.setSize(new Dimension(100, 100));
			PanelExploracionesBoton.add(getPanelExploraciones(), BorderLayout.CENTER);
			PanelExploracionesBoton.add(getPanelBotones(), BorderLayout.SOUTH);
		}
		return PanelExploracionesBoton;
	}

	public void setListenerButtons(ActionListener al){
		this.botonGuardar.addActionListener(al);
		this.botonCerrar.addActionListener(al);
		this.botonEstudios.addActionListener(al);
	}
	
	public JButton getBotonGuardar() {
		if (botonGuardar == null) {
			botonGuardar = new JButton();
			botonGuardar.setText("GUARDAR");
			botonGuardar.setMnemonic('G');
			botonGuardar.registerKeyboardAction(botonGuardar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonGuardar.registerKeyboardAction(botonGuardar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonGuardar.setPreferredSize(new Dimension(110, 30));
			botonGuardar.setIcon(new ImageIcon(getClass().getResource("/icono/accept.png")));
		}
		return botonGuardar;
	}
	
	public JButton getBotonCerrar() {
		if (botonCerrar == null) {
			botonCerrar = new JButton();
			botonCerrar.setText("CERRAR");
			botonCerrar.registerKeyboardAction(botonCerrar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonCerrar.registerKeyboardAction(botonCerrar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonCerrar.setPreferredSize(new Dimension(105, 30));
			botonCerrar.setIcon(new ImageIcon(getClass().getResource("/icono/cancel.png")));
		}
		return botonCerrar;
	}
	
	public PanelExploracionGeneral getPanelExploracionGeneral(){
		return panelExploracionGeneral;
	}
	public PanelCirculatorio getPanelCirculatorio(){
		return panelCirculatorio;
	}
	public PanelDigestivo getPanelDigestivo(){
		return panelDigestivo;
	}
	public PanelRespiratorio getPanelRespiratorio(){
		return panelRespiratorio;
	}  
	public PanelGenital getPanelGenital(){
		return panelGenital;
	}
	public PanelLocomotor getPanelLocomotor(){
		return panelLocomotor;
	}
	public PanelNervioso getPanelNervioso(){
		return panelNervioso;
	}
	public PanelOjos getPanelOjos(){
		return panelOjos;
	}
	public PanelPiel getPanelPiel(){
		return panelPiel;
	}
	public PanelUrinario getPanelUrinario(){
		return panelUrinario;
	}

	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout());
			panelBotones.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelBotones.add(getBotonEstudios(), null);
			panelBotones.add(getBotonGuardar(), null);
			panelBotones.add(getBotonCerrar(), null);
		}
		return panelBotones;
	}

	private JPanel getPanelAnamnesisDatos() {
		if (panelAnamnesisDatos == null) {
			panelAnamnesisDatos = new JPanel();
			panelAnamnesisDatos.setLayout(new BorderLayout());
			panelAnamnesisDatos.add(getPanelDatosOtros(), BorderLayout.EAST);
			panelAnamnesisDatos.add(getPanelDatosAnamnesis(), BorderLayout.CENTER);
		}
		return panelAnamnesisDatos;
	}

	public JButton getBotonEstudios() {
		if (botonEstudios == null) {
			botonEstudios = new JButton();
			botonEstudios.setText("ESTUDIOS");
			botonEstudios.setMnemonic('e');
			botonEstudios.registerKeyboardAction(botonEstudios.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonEstudios.registerKeyboardAction(botonEstudios.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonEstudios.setPreferredSize(new Dimension(130, 30));
			botonEstudios.setIcon(new ImageIcon(getClass().getResource("/icono/estudioComp.png")));
			
		}
		return botonEstudios;
	}
}
