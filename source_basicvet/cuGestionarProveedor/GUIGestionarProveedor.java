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

package cuGestionarProveedor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
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

@SuppressWarnings({ "unchecked","serial" })
public class GUIGestionarProveedor extends JDialog{
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelFiltro = null;
	private JPanel panelBotones = null;
	private JLabel labelFiltro = null;
	private JTextField campoFiltro = null;
	private JComboBox campoMotivoFiltro = null;
	private JScrollPane panelTabla = null;
	private JTable tablaProveedores = null;
	private JButton botonAgregarProveedor = null;
	private JButton botonModificarProveedor = null;
	private JButton botonEliminarProveedor = null;
	private TableRowSorter<DefaultTableModel> tFiltro;
	private JButton botonCancelar = null;
	private JButton botonSeleccionarProveedor = null;
	private JButton botonReporteProveedor = null;
	private JLabel labelFiltrarPor;
	
	public GUIGestionarProveedor(DefaultTableModel m) {
		super();
		this.tFiltro = new TableRowSorter(m);
		this.tablaProveedores = new JTable(m);
		this.tablaProveedores.getTableHeader().setReorderingAllowed(false);
		this.tablaProveedores.setRowSorter(tFiltro);
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

	private void initialize(){
		this.setSize(800, 600);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setTitle("GESTIONAR PROVEEDOR");
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
			panelBotones.add(getBotonSeleccionarProveedor(), null);
			panelBotones.add(getBotonCancelar(), null);
			panelBotones.add(getBotonAgregarProveedor(), null);
			panelBotones.add(getBotonModificarProveedor(), null);
			panelBotones.add(getBotonEliminarProveedor(), null);
			panelBotones.add(getBotonReporteProveedor(), null);
		}
		return panelBotones;
	}

	private JTextField getCampoFiltro() {
		if (campoFiltro == null) {
			campoFiltro = new JTextField();
			campoFiltro.setPreferredSize(new Dimension(150, 20));
			campoFiltro.addKeyListener(new KeyAdapter() {
				
				public void keyReleased(final KeyEvent e) {
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
			panelTabla.setViewportView(getTablaProveedores());
		}
		return panelTabla;
	}

	public JTable getTablaProveedores() {
		if (tablaProveedores == null) {
			tablaProveedores = new JTable();
		}
		return tablaProveedores;
	}

	public JButton getBotonAgregarProveedor() {
		if (botonAgregarProveedor == null) {
			botonAgregarProveedor = new JButton("INGRESAR PROVEEDOR");
			botonAgregarProveedor.setMnemonic('I');
			botonAgregarProveedor.registerKeyboardAction(botonAgregarProveedor.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonAgregarProveedor.registerKeyboardAction(botonAgregarProveedor.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonAgregarProveedor.setPreferredSize(new Dimension(190, 30));
			botonAgregarProveedor.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
			//botonAgregarProveedor.setText("INGRESAR PROVEEDOR");
		}
		return botonAgregarProveedor;
	}

	public JButton getBotonModificarProveedor() {
		if (botonModificarProveedor == null) {
			botonModificarProveedor = new JButton("MODIFICAR PROVEEDOR");
			botonModificarProveedor.setMnemonic('M');
			botonModificarProveedor.registerKeyboardAction(botonModificarProveedor.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);
			botonModificarProveedor.registerKeyboardAction(botonModificarProveedor.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonModificarProveedor.setPreferredSize(new Dimension(190, 30));
			botonModificarProveedor.setIcon(new ImageIcon(getClass().getResource("/icono/script_edit.png")));
			//botonModificarProveedor.setText("MODIFICAR PROVEEDOR");
		}
		return botonModificarProveedor;
	}

	public JButton getBotonEliminarProveedor() {
		if (botonEliminarProveedor == null) {
			botonEliminarProveedor = new JButton("ELIMINAR PROVEEDOR");
			botonEliminarProveedor.setMnemonic('E');
			botonEliminarProveedor.registerKeyboardAction(botonEliminarProveedor.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonEliminarProveedor.registerKeyboardAction(botonEliminarProveedor.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonEliminarProveedor.setPreferredSize(new Dimension(185, 30));
			botonEliminarProveedor.setIcon(new ImageIcon(getClass().getResource("/icono/delete.png")));
			//botonEliminarProveedor.setText("ELIMINAR PROVEEDOR");
		}
		return botonEliminarProveedor;
	}
	
	public void setListenerButtons(ActionListener al){
		this.botonAgregarProveedor.addActionListener(al);
		this.botonEliminarProveedor.addActionListener(al);
		this.botonModificarProveedor.addActionListener(al);
		this.botonCancelar.addActionListener(al);
		this.botonSeleccionarProveedor.addActionListener(al);
		this.botonReporteProveedor.addActionListener(al);
	}

	public void setListenerMouse(MouseAdapter ma){
		this.tablaProveedores.addMouseListener(ma);
	}
	
	public JButton getBotonCancelar() {
		if (botonCancelar == null) {
			botonCancelar = new JButton("CANCELAR");
			botonCancelar.setIcon(new ImageIcon(getClass().getResource("/icono/cancel.png")));
		}
		return botonCancelar;
	}

	public JButton getBotonSeleccionarProveedor() {
		if (botonSeleccionarProveedor == null) {
			botonSeleccionarProveedor = new JButton("ACEPTAR");
			botonSeleccionarProveedor.setMnemonic('A');
			botonSeleccionarProveedor.registerKeyboardAction(botonSeleccionarProveedor.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);
			botonSeleccionarProveedor.registerKeyboardAction(botonSeleccionarProveedor.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonSeleccionarProveedor.setPreferredSize(new Dimension(150, 30));
			botonSeleccionarProveedor.setIcon(new ImageIcon(getClass().getResource("/icono/accept.png")));
		}
		return botonSeleccionarProveedor;
	}
	public JButton getBotonReporteProveedor() {
		if (botonReporteProveedor == null) {
			botonReporteProveedor = new JButton();
			botonReporteProveedor.setText("REPORTE");
			botonReporteProveedor.setMnemonic('R');
			botonReporteProveedor.registerKeyboardAction(botonReporteProveedor.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonReporteProveedor.registerKeyboardAction(botonReporteProveedor.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonReporteProveedor.setPreferredSize(new Dimension(120, 30));
			botonReporteProveedor.setIcon(new ImageIcon(getClass().getResource("/icono/iconReport.png")));
		}
		return botonReporteProveedor;
	}
	
	public TableRowSorter getTableRowSorter(){
		return this.tFiltro;
	}
} 
