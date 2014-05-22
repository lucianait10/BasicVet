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

package cuGestionarEstudioComplementario;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import cuGestionarFichaClinica.GUIInterfazFichaClinica;

@SuppressWarnings("serial")
public class GUIGestionarEstudioComplementario extends JDialog{
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelFiltro = null;
	private JPanel panelBotones = null;
	private JLabel labelFiltro = null;
	private JTextField campoFiltro = null;
	private JComboBox campoMotivoFiltro = null;  
	private JScrollPane panelTabla = null;
	private JTable tablaEstudios = null;
	private JButton botonAgregarEstComp = null;
	private JButton botonModificarEstComp = null;
	private JButton botonEliminarEstComp = null;
	private TableRowSorter<DefaultTableModel> tFiltro;
	private GUIInterfazFichaClinica ingFichaClinica;
	private JLabel labelFiltrarPor;
	
	@SuppressWarnings({ "unchecked"})
	public GUIGestionarEstudioComplementario(DefaultTableModel m, GUIInterfazFichaClinica ingFichaClinica) {
		super();
		this.ingFichaClinica = ingFichaClinica;
		this.tFiltro = new TableRowSorter(m);
		this.tablaEstudios = new JTable(m);
		this.tablaEstudios.getTableHeader().setReorderingAllowed(false);
		this.tablaEstudios.setRowSorter(tFiltro);
		cerrarVentana();
		initialize();
	}
	
	private void cerrarVentana(){
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel"); //$NON-NLS-1$
	    getRootPane().getActionMap().put("Cancel", new AbstractAction(){ //$NON-NLS-1$
	        
			public void actionPerformed(ActionEvent e){
	        	setVisible(false);
	        	dispose();
	        	ingFichaClinica.setVisible(true);
	        }
	    });
	    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	    addWindowListener(new WindowAdapter() {
	    	
			public void windowClosing(WindowEvent evt){
	    		setVisible(false);
	    		dispose();
	    		ingFichaClinica.setVisible(true);
	    	}
	    });
	}

	private void initialize() {
		this.setSize(680, 500);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setTitle("GESTIONAR ESTUDIOS COMPLEMENTARIOS");
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(null);
		this.setModal(true);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setHgap(0);
			jContentPane = new JPanel();
			jContentPane.setLayout(borderLayout);
			jContentPane.add(getPanelFiltro(),BorderLayout.NORTH);
			jContentPane.add(getPanelTabla(),BorderLayout.CENTER);
			jContentPane.add(getPanelBotones(),BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	private JPanel getPanelFiltro() {
		if (panelFiltro == null) {
			/*FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(java.awt.FlowLayout.LEFT);
			labelFiltro = new JLabel();
			labelFiltro.setText("FILTRO:");
			panelFiltro = new JPanel();
			panelFiltro.setLayout(flowLayout);
			panelFiltro.setSize(new Dimension(685, 42));
			panelFiltro.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelFiltro.add(labelFiltro, null);
			panelFiltro.add(getCampoFiltro(), null);
			panelFiltro.add(getCampoMotivoFiltro(), null);*/
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints6.gridy = 0;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.gridx = 3;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 2;
			gridBagConstraints3.gridy = 0;
			labelFiltrarPor = new JLabel();
			labelFiltrarPor.setText("FILTRAR POR:");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.insets = new Insets(0, 0, 0, 180);
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints.gridy = 0;
			labelFiltro = new JLabel();
			labelFiltro.setText("FILTRO:");
			panelFiltro = new JPanel();
			panelFiltro.setLayout(new GridBagLayout());
			panelFiltro.setSize(new Dimension(680, 42));
			panelFiltro.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelFiltro.setPreferredSize(new Dimension(487, 40));
			panelFiltro.add(labelFiltro, gridBagConstraints);
			panelFiltro.add(getCampoFiltro(), gridBagConstraints1);
			panelFiltro.add(labelFiltrarPor, gridBagConstraints3);
			panelFiltro.add(getCampoMotivoFiltro(), gridBagConstraints6);

		}
		return panelFiltro;
	}

	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout());
			panelBotones.setSize(new Dimension(678, 40));
			panelBotones.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelBotones.add(getBotonAgregarEstComp(), null);
			panelBotones.add(getBotonModificarEstComp(), null);
			panelBotones.add(getBotonEliminarEstComp(), null);
		}
		return panelBotones;
	}

	private JTextField getCampoFiltro() {
		if (campoFiltro == null) {
			campoFiltro = new JTextField();
			campoFiltro.setPreferredSize(new Dimension(180, 20));
			campoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
				
				public void keyReleased(final java.awt.event.KeyEvent e) {
	                switch(campoMotivoFiltro.getSelectedIndex()){
	                	case 1:tFiltro.setRowFilter(RowFilter.regexFilter(campoFiltro.getText().toUpperCase(), 1));
	                	break;
	                	
	                	case 2:tFiltro.setRowFilter(RowFilter.regexFilter(campoFiltro.getText().toUpperCase(), 2));
	                	break;
	                	
	                	case 3:tFiltro.setRowFilter(RowFilter.regexFilter(campoFiltro.getText().toUpperCase(), 3));
	                	break;
	                	
	                	default :tFiltro.setRowFilter(RowFilter.regexFilter(campoFiltro.getText(), 0));
	                }
	            }
	        });
		}
		return campoFiltro;
	}


	private JComboBox getCampoMotivoFiltro() {
		if (campoMotivoFiltro == null) {
			Object[] opciones = {"ID","TIPO","FECHA"};
			campoMotivoFiltro = new JComboBox(opciones);
			campoMotivoFiltro.setPreferredSize(new Dimension(150, 25));
		}
		return campoMotivoFiltro;
	}

	private JScrollPane getPanelTabla() {
		if (panelTabla == null) {
			panelTabla = new JScrollPane();
			panelTabla.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelTabla.setSize(new Dimension(125, 88));
			panelTabla.setViewportView(getTablaEstudios());
		}
		return panelTabla;
		
	}

	public JTable getTablaEstudios() {
		if (tablaEstudios == null) {
			tablaEstudios = new JTable();
		}
		return tablaEstudios;
	}

	public JButton getBotonAgregarEstComp() {
		if (botonAgregarEstComp == null) {
			botonAgregarEstComp = new JButton("INGRESAR ESTUDIO");
			botonAgregarEstComp.setMnemonic('I');
			botonAgregarEstComp.registerKeyboardAction(botonAgregarEstComp.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonAgregarEstComp.registerKeyboardAction(botonAgregarEstComp.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonAgregarEstComp.setPreferredSize(new Dimension(180, 30));
			botonAgregarEstComp.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
		}
		return botonAgregarEstComp;
	}

	public JButton getBotonModificarEstComp() {
		if (botonModificarEstComp == null) {
			botonModificarEstComp = new JButton("MODIFICAR ESTUDIO");
			botonModificarEstComp.setMnemonic('M');
			botonModificarEstComp.registerKeyboardAction(botonModificarEstComp.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonModificarEstComp.registerKeyboardAction(botonModificarEstComp.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonModificarEstComp.setPreferredSize(new Dimension(200, 30));
			botonModificarEstComp.setIcon(new ImageIcon(getClass().getResource("/icono/script_edit.png")));
		}
		return botonModificarEstComp;
	}

	public JButton getBotonEliminarEstComp() {
		if (botonEliminarEstComp == null) {
			botonEliminarEstComp = new JButton("ELIMINAR ESTUDIO");
			botonEliminarEstComp.setMnemonic('E');
			botonEliminarEstComp.registerKeyboardAction(botonEliminarEstComp.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonEliminarEstComp.registerKeyboardAction(botonEliminarEstComp.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonEliminarEstComp.setPreferredSize(new Dimension(180, 30));
			botonEliminarEstComp.setIcon(new ImageIcon(getClass().getResource("/icono/delete.png")));
		}
		return botonEliminarEstComp;
	}
	
	public void setListenerButtons(ActionListener al){
		this.botonAgregarEstComp.addActionListener(al);
		this.botonEliminarEstComp.addActionListener(al);
		this.botonModificarEstComp.addActionListener(al);
	}	

	@SuppressWarnings({"unchecked" })
	public TableRowSorter getTableRowSorter(){
		return this.tFiltro;
	}

	public void setListenerMouse(MouseAdapter ma) {
		this.tablaEstudios.addMouseListener(ma);
	}
}
