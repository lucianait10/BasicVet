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

package cuGestionarVeterinario;

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

@SuppressWarnings({"unchecked"})
public class GUIGestionarVeterinario extends JDialog{
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelFiltro = null;
	private JPanel panelBotones = null;
	private JLabel labelFiltro = null;
	private JTextField campoFiltro = null;
	private JComboBox campoMotivoFiltro = null;
	private JScrollPane panelTabla = null;
	private JTable tablaVeterinarios = null;
	private JButton botonAgregarVeterinario = null;
	private JButton botonModificarVeterinario = null;
	private JButton botonEliminarVeterinario = null;
	private TableRowSorter<DefaultTableModel> tFiltro;
	private JButton botonCancelar = null;
	private JButton botonSeleccionarVeterinario = null;
	private JButton botonReporteVeterinario = null;
	private JLabel labelFiltrarPor;
	
	public GUIGestionarVeterinario(DefaultTableModel m) {
		super();
		this.tFiltro = new TableRowSorter(m);
		this.tablaVeterinarios = new JTable(m);
		this.tablaVeterinarios.getTableHeader().setReorderingAllowed(false);
		this.tablaVeterinarios.setRowSorter(tFiltro);
		cerrarVentana();
		initialize();
	}

	@SuppressWarnings({ "serial" })
	private void cerrarVentana(){
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
	    getRootPane().getActionMap().put("Cancel", new AbstractAction(){
	        
			public void actionPerformed(ActionEvent e){
	        	setVisible(false);
	        	dispose();
	        }
	    });
	    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	    addWindowListener(new WindowAdapter() {
	    	
			public void windowClosing(WindowEvent evt){
	    		setVisible(false);
	    		dispose();
	    	}
	    });
	}
	
	private void initialize() {
		this.setSize(800, 600);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setTitle("GESTIONAR VETERINARIO");
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(null);
		this.setModal(true);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelFiltro(),BorderLayout.NORTH);
			jContentPane.add(getPanelTabla(),BorderLayout.CENTER);
			jContentPane.add(getPanelBotones(),BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	private JPanel getPanelFiltro() {
		if (panelFiltro == null) {
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
			gridBagConstraints1.insets = new Insets(0, 0, 0, 280);
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints.gridy = 0;
			labelFiltro = new JLabel();
			labelFiltro.setText("FILTRO:");
			panelFiltro = new JPanel();
			panelFiltro.setLayout(new GridBagLayout());
			panelFiltro.setSize(new Dimension(685, 42));
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
			panelBotones.add(getBotonSeleccionarVeterinario(), null);
			panelBotones.add(getBotonCancelar(), null);
			panelBotones.add(getBotonAgregarVeterinario(), null);
			panelBotones.add(getBotonModificarVeterinario(), null);
			panelBotones.add(getBotonEliminarVeterinario(), null);
			panelBotones.add(getBotonReporteVeterinario(), null);
		}
		return panelBotones;
	}

	private JTextField getCampoFiltro() {
		if (campoFiltro == null) {
			campoFiltro = new JTextField();
			campoFiltro.setPreferredSize(new Dimension(150, 20));
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
			Object[] opciones = {"DNI","NOMBRE","APELLIDO","TELEFONO"};
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
			panelTabla.setViewportView(getTablaVeterinarios());
		}
		return panelTabla;
	}

	public JTable getTablaVeterinarios() {
		if (tablaVeterinarios == null) {
			tablaVeterinarios = new JTable();
		}
		return tablaVeterinarios;
	}

	public JButton getBotonAgregarVeterinario() {
		if (botonAgregarVeterinario == null) {
			botonAgregarVeterinario = new JButton("INGRESAR VETERINARIO");
			botonAgregarVeterinario.setMnemonic('I');
			botonAgregarVeterinario.registerKeyboardAction(botonAgregarVeterinario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonAgregarVeterinario.registerKeyboardAction(botonAgregarVeterinario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonAgregarVeterinario.setPreferredSize(new Dimension(190, 30));
			botonAgregarVeterinario.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
			//botonAgregarVeterinario.setText("INGRESAR VETERINARIO");
		}
		return botonAgregarVeterinario;
	}

	public JButton getBotonModificarVeterinario() {
		if (botonModificarVeterinario == null) {
			botonModificarVeterinario = new JButton("MODIFICAR VETERINARIO");
			botonModificarVeterinario.setMnemonic('M');
			botonModificarVeterinario.registerKeyboardAction(botonModificarVeterinario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonModificarVeterinario.registerKeyboardAction(botonModificarVeterinario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonModificarVeterinario.setPreferredSize(new Dimension(195, 30));
			botonModificarVeterinario.setIcon(new ImageIcon(getClass().getResource("/icono/script_edit.png")));
			//botonModificarVeterinario.setText("MODIFICAR VETERINARIO");
		}
		return botonModificarVeterinario;
	}

	public JButton getBotonEliminarVeterinario() {
		if (botonEliminarVeterinario == null) {
			botonEliminarVeterinario = new JButton("ELIMINAR VETERINARIO");
			botonEliminarVeterinario.setMnemonic('E');
			botonEliminarVeterinario.registerKeyboardAction(botonEliminarVeterinario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonEliminarVeterinario.registerKeyboardAction(botonEliminarVeterinario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonEliminarVeterinario.setPreferredSize(new Dimension(190, 30));
			botonEliminarVeterinario.setIcon(new ImageIcon(getClass().getResource("/icono/delete.png")));
			//botonEliminarVeterinario.setText("ELIMINAR VETERINARIO");
		}
		return botonEliminarVeterinario;
	}
	
	public void setListenerButtons(ActionListener al){
		this.botonAgregarVeterinario.addActionListener(al);
		this.botonEliminarVeterinario.addActionListener(al);
		this.botonModificarVeterinario.addActionListener(al);
		this.botonCancelar.addActionListener(al);
		this.botonSeleccionarVeterinario.addActionListener(al);
		this.botonReporteVeterinario.addActionListener(al);
	}
	
	public void setListenerMouse(MouseAdapter ma){
		this.tablaVeterinarios.addMouseListener(ma);
	}

	public JButton getBotonCancelar() {
		if (botonCancelar == null) {
			botonCancelar = new JButton("CANCELAR");
			botonCancelar.setIcon(new ImageIcon(getClass().getResource("/icono/cancel.png")));
			//botonCancelar.setPreferredSize(new Dimension(116, 30));
		}
		return botonCancelar;
	}

	public JButton getBotonSeleccionarVeterinario() {
		if (botonSeleccionarVeterinario == null) {
			botonSeleccionarVeterinario = new JButton("ACEPTAR");
			botonSeleccionarVeterinario.setMnemonic('A');
			botonSeleccionarVeterinario.registerKeyboardAction(botonSeleccionarVeterinario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonSeleccionarVeterinario.registerKeyboardAction(botonSeleccionarVeterinario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonSeleccionarVeterinario.setPreferredSize(new Dimension(190, 30));
			botonSeleccionarVeterinario.setIcon(new ImageIcon(getClass().getResource("/icono/accept.png")));
			//botonSeleccionarVeterinario.setText("ACEPTAR");
		}
		return botonSeleccionarVeterinario;
	}
	public JButton getBotonReporteVeterinario() {
		if (botonReporteVeterinario == null) {
			botonReporteVeterinario = new JButton();
			botonReporteVeterinario.setText("REPORTE");
			botonReporteVeterinario.setMnemonic('R');
			botonReporteVeterinario.registerKeyboardAction(botonReporteVeterinario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonReporteVeterinario.registerKeyboardAction(botonReporteVeterinario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonReporteVeterinario.setPreferredSize(new Dimension(120, 30));
			botonReporteVeterinario.setIcon(new ImageIcon(getClass().getResource("/icono/iconReport.png")));
		}
		return botonReporteVeterinario;
	}
	
	public TableRowSorter getTableRowSorter(){
		return this.tFiltro;
	}
} 
