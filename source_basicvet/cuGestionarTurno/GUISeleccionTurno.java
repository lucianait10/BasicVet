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

package cuGestionarTurno;

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
import java.util.Date;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.freixas.jcalendar.JCalendar;

public class GUISeleccionTurno extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JCalendar calendario;
	private JPanel panelCalendario;
	private JScrollPane panelScrollTable = null;
	private TablaTurno tabla = null;
	private DefaultTableModel modelo = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable (int row, int column){return false;}
	};
	private JButton botonAgregar = null;
	private JPanel panelCampos = null;
	private JPanel panelBoton = null;
	private JComboBox campoMotivo;
	private JButton botonModificar = null;
	private JButton botonEliminar = null;
	private JPanel panelLabel = null;
	private JLabel labelTurno = null;
	private JPanel panelReferencias = null; 
	private JTextField referenciaAzul = null;
	private JTextField referenciaAzul1 = null;
	private JLabel labelTurnoAzul = null;
	private JTextField referenciaVerde = null;
	private JTextField referenciaVerde1 = null;
	private JLabel labelTurnoVerde = null;
	private JPanel panelAzul = null;
	private JPanel panelVerde = null;
	private JButton botonReporteSemana;
	private GUIGestionarTurno gesTurno;
	private JLabel labelMotivo;
	private JPanel panelMotivo = null;
	private JButton botonAgregarMotivo = null;
	
	public GUISeleccionTurno(GUIGestionarTurno gesTurno) {
		super();
		this.gesTurno = gesTurno;
		cerrarVentana();
		initialize();
	}
	
	@SuppressWarnings({ "serial" })
	private void cerrarVentana(){
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel"); //$NON-NLS-1$
	    getRootPane().getActionMap().put("Cancel", new AbstractAction(){ //$NON-NLS-1$
	        
			public void actionPerformed(ActionEvent e){
	        	setVisible(false);
	        	dispose();
	        	gesTurno.setVisible(true);
	        }
	    });
	    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	    addWindowListener(new WindowAdapter() {
	    	
			public void windowClosing(WindowEvent evt){
	    		setVisible(false);
	    		dispose();
	    		gesTurno.setVisible(true);
	    	}
	    });
	}

	private void initialize() {
		this.setSize(800, 600);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setContentPane(getJContentPane());
		this.setTitle("SELECCION DE TURNOS");
		this.setLocationRelativeTo(null);
		this.setModal(true);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setVgap(5);
			jContentPane = new JPanel();
			jContentPane.setLayout(borderLayout);
			jContentPane.add(getPanelCalendario(), BorderLayout.EAST);
			jContentPane.add(getPanelScrollTable(), BorderLayout.CENTER);
			jContentPane.add(getPanelLabel(), BorderLayout.NORTH);
			jContentPane.add(getPanelReferencias(),BorderLayout.SOUTH);
		}
		return jContentPane;
	}
	public JCalendar getCalendario() {
		if (calendario == null) {
			calendario = new JCalendar();
		}
		return calendario;
	}

	private JPanel getPanelCalendario() {
		if (panelCalendario == null) {
			panelCalendario = new JPanel();
			panelCalendario.setLayout(new BorderLayout());
			panelCalendario.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelCalendario.add(getCalendario(), BorderLayout.NORTH);
			panelCalendario.add(getPanelCampos(), BorderLayout.CENTER);
		}
		return panelCalendario;
	}

	public JComboBox getCampoMotivo() {
		if (campoMotivo == null) {
			Object[] opciones = {};
			campoMotivo = new JComboBox(opciones);
			campoMotivo.setPreferredSize(new Dimension(200, 30));
		}
		return campoMotivo;
	}

	private JScrollPane getPanelScrollTable() {
		if (panelScrollTable == null) {
			panelScrollTable = new JScrollPane();
			panelScrollTable.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelScrollTable.setViewportView(getTabla());
		}
		return panelScrollTable;
	}
	private JScrollPane getPanelScrollTable2(){
		panelScrollTable.setViewportView(tabla);
		return panelScrollTable;
	}


	@SuppressWarnings("deprecation")
	public JTable getTabla() {
		if (tabla == null) {
			Date fecha = new Date();
			modelo.addColumn("HORA");
			modelo.addColumn(fecha.getDate()+"-"+fecha.getMonth()+"-"+fecha.getYear());
			modelo.fireTableStructureChanged();
			this.inicializarColumnaHolas(modelo);
			tabla = new TablaTurno(modelo);
			this.modificarIdentificadoresTabla();
		}
		return tabla;
	}
	
	@SuppressWarnings("deprecation")
	public void colorearTabla(Date fecha, LinkedList<Integer> l1, LinkedList<Integer> l2,LinkedList<Integer> l3,LinkedList<Integer> l4){
		modelo.setColumnIdentifiers(new String[] { "HORA", fecha.getDate()+"-"+(fecha.getMonth()+1)+"-"+(fecha.getYear()+1900)});
		tabla = new TablaTurno(modelo,l1,l2,l3,l4);
		this.modificarIdentificadoresTabla();
		getPanelScrollTable2();
	}
	
	public void setNombreColumnas(String fecha){this.tabla.getColumnModel().getColumn(0).setCellRenderer(tabla.getTableHeader().getDefaultRenderer());}
 	
	public void inicializarColumnaHolas(DefaultTableModel modelo){
		Object[] s0 = {"08:00                "};	Object[] s1 = {"08:15"};Object[] s2 = {"08:30"}; Object[] s3 = {"08:45"};
		Object[] s4 = {"09:00                "};	Object[] s5 = {"09:15"};Object[] s6 = {"09:30"}; Object[] s7 = {"09:45"};
		Object[] s8 = {"10:00                "};	Object[] s9 = {"10:15"};Object[] s10 = {"10:30"}; Object[] s11 = {"10:45"};
		Object[] s12 = {"11:00               "};	Object[] s13 = {"11:15"};Object[] s14 = {"11:30"}; Object[] s15 = {"11:45"};
		Object[] s16 = {"12:00               "};	Object[] s17 = {"12:15"};Object[] s18 = {"12:30"}; Object[] s19 = {"12:45"};
		Object[] s20 = {"13:00               "};	Object[] s21 = {"13:15"};Object[] s22 = {"13:30"}; Object[] s23 = {"13:45"};
		Object[] s24 = {"14:00               "};	Object[] s25 = {"14:15"};Object[] s26 = {"14:30"}; Object[] s27 = {"14:45"};
		Object[] s28 = {"15:00               "};	Object[] s29 = {"15:15"};Object[] s30 = {"15:30"}; Object[] s31 = {"15:45"};
		Object[] s32 = {"16:00               "};	Object[] s33 = {"16:15"};Object[] s34 = {"16:30"}; Object[] s35 = {"16:45"};
		Object[] s36 = {"17:00               "};	Object[] s37 = {"17:15"};Object[] s38 = {"17:30"}; Object[] s39 = {"17:45"};
		Object[] s40 = {"18:00               "};	Object[] s41 = {"18:15"};Object[] s42 = {"18:30"}; Object[] s43 = {"18:45"};
		Object[] s44 = {"19:00               "};	Object[] s45 = {"19:15"};Object[] s46 = {"19:30"}; Object[] s47 = {"19:45"};
		modelo.addRow(s0);modelo.addRow(s1);modelo.addRow(s2);modelo.addRow(s3);modelo.addRow(s4);modelo.addRow(s5);
		modelo.addRow(s6);modelo.addRow(s7);modelo.addRow(s8);modelo.addRow(s9);modelo.addRow(s10);modelo.addRow(s11);
		modelo.addRow(s12);modelo.addRow(s13);modelo.addRow(s14);modelo.addRow(s15);modelo.addRow(s16);modelo.addRow(s17);
		modelo.addRow(s18);modelo.addRow(s19);modelo.addRow(s20);modelo.addRow(s21);modelo.addRow(s22);modelo.addRow(s23);
		modelo.addRow(s24);modelo.addRow(s25);modelo.addRow(s26);modelo.addRow(s27);modelo.addRow(s28);modelo.addRow(s29);
		modelo.addRow(s30);modelo.addRow(s31);modelo.addRow(s32);modelo.addRow(s33);modelo.addRow(s34);modelo.addRow(s35);
		modelo.addRow(s36);modelo.addRow(s37);modelo.addRow(s38);modelo.addRow(s39);modelo.addRow(s40);modelo.addRow(s41);
		modelo.addRow(s42);modelo.addRow(s43);modelo.addRow(s44);modelo.addRow(s45);modelo.addRow(s46);modelo.addRow(s47);
	}

	public JButton getBotonAgregar() {
		if (botonAgregar == null) {
			botonAgregar = new JButton("INGRESAR");
			botonAgregar.setMnemonic('I');
			botonAgregar.registerKeyboardAction(botonAgregar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonAgregar.registerKeyboardAction(botonAgregar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonAgregar.setPreferredSize(new Dimension(130, 30));
			botonAgregar.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
		}
		return botonAgregar;
	}

	private JPanel getPanelCampos() {
		if (panelCampos == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.gridy = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridy = 2;
			gridBagConstraints1.insets = new Insets(110, 0, 0, 0);
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.insets = new Insets(0, 0, 3, 0);
			gridBagConstraints2.gridx = 0;
			panelCampos = new JPanel();
			panelCampos.setLayout(new GridBagLayout());
			panelCampos.add(getPanelBoton(), gridBagConstraints1);
			panelCampos.add(getLabelMotivo(), gridBagConstraints2);
			panelCampos.add(getPanelMotivo(), gridBagConstraints11);
		}
		return panelCampos;
	}
	
	public JLabel getLabelMotivo(){
		if(labelMotivo==null){
			labelMotivo = new JLabel();
			labelMotivo.setText("MOTIVO:");		
		}
		return labelMotivo;
	}
	public TablaTurno obtenerTabla(){
		return tabla;
	}
	public void setTabla(TablaTurno tabla) {
		this.tabla = tabla;
	}
	public void setListenerButtons(ActionListener al){
		this.botonAgregar.addActionListener(al);
		this.botonEliminar.addActionListener(al);
		this.botonModificar.addActionListener(al);
		this.botonReporteSemana.addActionListener(al);
		this.botonAgregarMotivo.addActionListener(al);
	}

	

	private JPanel getPanelBoton() {
		if (panelBoton == null) {
			panelBoton = new JPanel();
			panelBoton.setLayout(new FlowLayout());
			panelBoton.add(getBotonAgregar(), null);
			panelBoton.add(getBotonModificar(), null);
			panelBoton.add(getBotonEliminar(), null);
			panelBoton.add(getBotonReporteSemana(), null);
		}
		return panelBoton;
	}

	public JButton getBotonModificar() {
		if (botonModificar == null) {
			botonModificar = new JButton("MODIFICAR");
			botonModificar.setMnemonic('M');
			botonModificar.registerKeyboardAction(botonModificar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonModificar.registerKeyboardAction(botonModificar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonModificar.setPreferredSize(new Dimension(130, 30));
			botonModificar.setIcon(new ImageIcon(getClass().getResource("/icono/script_edit.png")));
		}
		return botonModificar;
	}

	public JButton getBotonEliminar() {
		if (botonEliminar == null) {
			botonEliminar = new JButton("ELIMINAR");
			botonEliminar.setMnemonic('E');
			botonEliminar.registerKeyboardAction(botonEliminar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonEliminar.registerKeyboardAction(botonEliminar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonEliminar.setPreferredSize(new Dimension(130, 30));
			botonEliminar.setIcon(new ImageIcon(getClass().getResource("/icono/delete.png")));
		}
		return botonEliminar;
	}
	public DefaultTableModel getTableModel(){
		return this.modelo;
	}
	
	public void modificarIdentificadoresTabla(){
		JTableHeader anHeader = tabla.getTableHeader();
		anHeader.setFont(new Font("Plain", Font.BOLD, 14));
		tabla.getColumnModel().getColumn(0).setCellRenderer(tabla.getTableHeader().getDefaultRenderer());
		tabla.setColumnSelectionAllowed(true);
	}

	private JPanel getPanelLabel() {
		if (panelLabel == null) {
			labelTurno = new JLabel();
			labelTurno.setText(" TURNOS:");
			labelTurno.setPreferredSize(new Dimension(60, 18));
			labelTurno.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
			panelLabel = new JPanel();
			panelLabel.setLayout(new BorderLayout());
			panelLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelLabel.setMinimumSize(new Dimension(55, 25));
			panelLabel.setPreferredSize(new Dimension(55, 25));
			panelLabel.add(labelTurno, BorderLayout.WEST);
		}
		return panelLabel;
	}

	private JPanel getPanelReferencias() {
		if (panelReferencias == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.insets = new Insets(0, 50, 0, 0);
			gridBagConstraints4.gridy = 0;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.insets = new Insets(0, 0, 0, 50);
			gridBagConstraints3.gridy = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.gridx = 0;
			panelReferencias = new JPanel();
			panelReferencias.setLayout(new GridBagLayout());
			panelReferencias.setSize(new Dimension(874, 38));
			panelReferencias.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelReferencias.add(getPanelAzul(), gridBagConstraints3);
			panelReferencias.add(getPanelVerde(), gridBagConstraints4);
		}
		return panelReferencias;
	}

	private JTextField getReferenciaAzul() {
		if (referenciaAzul == null) {
			referenciaAzul = new JTextField();
			referenciaAzul.setPreferredSize(new Dimension(10, 10));
			referenciaAzul.setEditable(false);
			referenciaAzul.setEnabled(false);
			referenciaAzul.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 255), 5));
			referenciaAzul.setBackground(new Color(0, 100, 255));
		}
		return referenciaAzul;
	}

	private JTextField getReferenciaAzul1() {
		if (referenciaAzul1 == null) {
			referenciaAzul1 = new JTextField();
			referenciaAzul1.setPreferredSize(new Dimension(10, 10));
			referenciaAzul1.setEditable(false);
			referenciaAzul1.setEnabled(false);
			referenciaAzul1.setBorder(BorderFactory.createLineBorder(new Color(0, 200, 255), 5));
			referenciaAzul1.setBackground(new Color(0, 200, 255));
		}
		return referenciaAzul1;
	}

	public JLabel getLabelTurnoAzul() {
		if (labelTurnoAzul == null) {
			labelTurnoAzul = new JLabel();
		}
		return labelTurnoAzul;
	}

	private JTextField getReferenciaVerde() {
		if (referenciaVerde == null) {
			referenciaVerde = new JTextField();
			referenciaVerde.setPreferredSize(new Dimension(10, 10));
			referenciaVerde.setEditable(false);
			referenciaVerde.setEnabled(false);
			referenciaVerde.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0), 5));
			referenciaVerde.setBackground(new Color(0, 255, 0));
		}
		return referenciaVerde;
	}

	private JTextField getReferenciaVerde1() {
		if (referenciaVerde1 == null) {
			referenciaVerde1 = new JTextField();
			referenciaVerde1.setPreferredSize(new Dimension(10, 10));
			referenciaVerde1.setEditable(false);
			referenciaVerde1.setEnabled(false);
			referenciaVerde1.setBorder(BorderFactory.createLineBorder(new Color(100, 255, 0), 5));
			referenciaVerde1.setBackground(new Color(100, 255, 0));
		}
		return referenciaVerde1;
	}

	private JLabel getLabelTurnoVerde() {
		if (labelTurnoVerde == null) {
			labelTurnoVerde = new JLabel();
			labelTurnoVerde.setText("OTROS TURNOS");
		}
		return labelTurnoVerde;
	}
	
	public JPanel getPanelAzul() {
		if (panelAzul == null) {
			panelAzul = new JPanel();
			panelAzul.setLayout(new FlowLayout());
			panelAzul.add(getReferenciaAzul(), null);
			panelAzul.add(getReferenciaAzul1(), null);
			panelAzul.add(getLabelTurnoAzul(), null);
		}
		return panelAzul;
	}

	private JPanel getPanelVerde() {
		if (panelVerde == null) {
			panelVerde = new JPanel();
			panelVerde.setLayout(new FlowLayout());
			panelVerde.add(getReferenciaVerde(), null);
			panelVerde.add(getReferenciaVerde1(), null);
			panelVerde.add(getLabelTurnoVerde(), null);
		}
		return panelVerde;
	}
	
	public JButton getBotonReporteSemana(){
		if(botonReporteSemana==null){
			botonReporteSemana = new JButton("VER SEMANA");
			botonReporteSemana.setMnemonic('s');
			botonReporteSemana.registerKeyboardAction(botonReporteSemana.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonReporteSemana.registerKeyboardAction(botonReporteSemana.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);

			//botonReporteSemana.setPreferredSize(new Dimension(130, 30));
			botonReporteSemana.setIcon(new ImageIcon(getClass().getResource("/icono/icono_calen.png")));
		}
		return botonReporteSemana;
	}

	/**
	 * This method initializes panelMotivo	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelMotivo() {
		if (panelMotivo == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.gridx = 0;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 1;
			gridBagConstraints5.insets = new Insets(0, 3, 0, 0);
			gridBagConstraints5.gridy = 0;
			panelMotivo = new JPanel();
			panelMotivo.setLayout(new GridBagLayout());
			panelMotivo.add(getBotonAgregarMotivo(), gridBagConstraints5);
			panelMotivo.add(getCampoMotivo(), gridBagConstraints);
		}
		return panelMotivo;
	}

	/**
	 * This method initializes botonAgregarMotivo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBotonAgregarMotivo() {
		if (botonAgregarMotivo == null) {
			botonAgregarMotivo = new JButton();
			botonAgregarMotivo.registerKeyboardAction(botonAgregarMotivo.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonAgregarMotivo.registerKeyboardAction(botonAgregarMotivo.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			botonAgregarMotivo.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
			botonAgregarMotivo.setPreferredSize(new Dimension(50, 30));
		}
		return botonAgregarMotivo;
	}
}
