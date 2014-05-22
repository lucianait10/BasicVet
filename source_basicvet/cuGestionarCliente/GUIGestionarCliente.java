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

package cuGestionarCliente;

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

@SuppressWarnings({ "unchecked"})
public class GUIGestionarCliente extends JDialog{
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelFiltro = null;
	private JPanel panelBotones = null;
	private JLabel labelFiltro = null;
	private JTextField campoFiltro = null;
	private JComboBox campoMotivoFiltro = null;
	private JScrollPane panelTabla = null;
	private JTable tablaClientes = null;
	private JButton botonAgregarCliente = null;
	private JButton botonModificarCliente = null;
	private JButton botonEliminarCliente = null;
	private TableRowSorter<DefaultTableModel> tFiltro;
	private JButton botonCancelar = null;
	private JButton botonSeleccionarCliente = null;
	private JButton botonReporteCliente = null;
	private JLabel labelFiltrarPor;
	

	public GUIGestionarCliente(DefaultTableModel m) {
		super();
		this.tFiltro = new TableRowSorter(m);
		this.tablaClientes = new JTable(m);
		this.tablaClientes.getTableHeader().setReorderingAllowed(false);
		this.tablaClientes.setRowSorter(tFiltro);
		initialize();
		cerrarVentana();
	}
	
	@SuppressWarnings("serial")
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
	    	
			public void windowClosing(java.awt.event.WindowEvent evt){
	    		setVisible(false);
	    		dispose();
	    	}
	    });
	}

	private void initialize() {
		this.setSize(800, 600);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setTitle("GESTIONAR CLIENTE");
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
			panelBotones.add(getBotonSeleccionarCliente(), null);
			panelBotones.add(getBotonCancelar(), null);
			panelBotones.add(getBotonAgregarCliente(), null);
			panelBotones.add(getBotonModificarCliente(), null);
			panelBotones.add(getBotonEliminarCliente(), null);
			panelBotones.add(getBotonReporteCliente(), null);
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
			panelTabla.setViewportView(getTablaClientes());
		}
		return panelTabla;
	}

	public JTable getTablaClientes() {
		if (tablaClientes == null) {
			tablaClientes = new JTable();
		}
		return tablaClientes;
	}

	public JButton getBotonAgregarCliente() {
		if (botonAgregarCliente == null) {
			botonAgregarCliente = new JButton("INGRESAR CLIENTE");
			botonAgregarCliente.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
			//botonAgregarCliente.setPreferredSize(new Dimension(162, 30));
			botonAgregarCliente.setText("INGRESAR CLIENTE");
			botonAgregarCliente.setMnemonic('I');
			botonAgregarCliente.registerKeyboardAction(botonAgregarCliente.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
					JComponent.WHEN_FOCUSED);

			botonAgregarCliente.registerKeyboardAction(botonAgregarCliente.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
					JComponent.WHEN_FOCUSED);
		}
		return botonAgregarCliente;
	}

	public JButton getBotonModificarCliente() {
		if (botonModificarCliente == null) {
			botonModificarCliente = new JButton("MODIFICAR CLIENTE");
			botonModificarCliente.setIcon(new ImageIcon(getClass().getResource("/icono/script_edit.png")));
			//botonModificarCliente.setPreferredSize(new Dimension(167, 30));
			botonModificarCliente.setMnemonic('M');
			botonModificarCliente.setText("MODIFICAR CLIENTE");
			botonModificarCliente.registerKeyboardAction(botonModificarCliente.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
					JComponent.WHEN_FOCUSED);

			botonModificarCliente.registerKeyboardAction(botonModificarCliente.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
					JComponent.WHEN_FOCUSED);
		}
		return botonModificarCliente;
	}

	public JButton getBotonEliminarCliente() {
		if (botonEliminarCliente == null) {
			botonEliminarCliente = new JButton("ELIMINAR CLIENTE");
			botonEliminarCliente.setIcon(new ImageIcon(getClass().getResource("/icono/delete.png")));
			//botonEliminarCliente.setPreferredSize(new Dimension(158, 30));
			botonEliminarCliente.setMnemonic('E');
			botonEliminarCliente.setText("ELIMINAR CLIENTE");
			botonEliminarCliente.registerKeyboardAction(botonEliminarCliente.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
					JComponent.WHEN_FOCUSED);

			botonEliminarCliente.registerKeyboardAction(botonEliminarCliente.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
					JComponent.WHEN_FOCUSED);
		}
		return botonEliminarCliente;
	}
	
	public void setListenerButtons(ActionListener al){
		this.botonAgregarCliente.addActionListener(al);
		this.botonEliminarCliente.addActionListener(al);
		this.botonModificarCliente.addActionListener(al);
		this.botonCancelar.addActionListener(al);
		this.botonSeleccionarCliente.addActionListener(al);
		this.botonReporteCliente.addActionListener(al);
	}
	
	public void setListenerMouse(MouseAdapter ma){
		this.tablaClientes.addMouseListener(ma);
	}

	public JButton getBotonCancelar() {
		if (botonCancelar == null) {
			botonCancelar = new JButton("CANCELAR");
			botonCancelar.setIcon(new ImageIcon(getClass().getResource("/icono/cancel.png")));
			botonCancelar.setMnemonic('C');
			//botonCancelar.setPreferredSize(new Dimension(116, 30));
			botonCancelar.registerKeyboardAction(botonCancelar.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
					JComponent.WHEN_FOCUSED);

			botonCancelar.registerKeyboardAction(botonCancelar.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
					JComponent.WHEN_FOCUSED);
		}
		return botonCancelar;
	}

	public JButton getBotonSeleccionarCliente() {
		if (botonSeleccionarCliente == null) {
			botonSeleccionarCliente = new JButton("ACEPTAR");
			botonSeleccionarCliente.setIcon(new ImageIcon(getClass().getResource("/icono/accept.png")));
			//botonSeleccionarCliente.setPreferredSize(new Dimension(108, 30));
			botonSeleccionarCliente.setMnemonic('A');
			botonSeleccionarCliente.setText("ACEPTAR");
			botonSeleccionarCliente.registerKeyboardAction(botonSeleccionarCliente.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
					JComponent.WHEN_FOCUSED);

			botonSeleccionarCliente.registerKeyboardAction(botonSeleccionarCliente.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
					JComponent.WHEN_FOCUSED);
		}
		return botonSeleccionarCliente;
	}
	public JButton getBotonReporteCliente() {
		if (botonReporteCliente == null) {
			botonReporteCliente = new JButton();
			botonReporteCliente.setText("REPORTE");
			//botonReporteCliente.setPreferredSize(new Dimension(108, 30));
			botonReporteCliente.setMnemonic('R');
			botonReporteCliente.setIcon(new ImageIcon(getClass().getResource("/icono/iconReport.png")));
			botonReporteCliente.registerKeyboardAction(botonReporteCliente.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
					JComponent.WHEN_FOCUSED);

			botonReporteCliente.registerKeyboardAction(botonReporteCliente.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
					JComponent.WHEN_FOCUSED);
		}
		return botonReporteCliente;
	}

	public TableRowSorter getTableRowSorter(){
		return this.tFiltro;
	}
}
