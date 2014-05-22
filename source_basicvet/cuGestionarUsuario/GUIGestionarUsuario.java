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

package cuGestionarUsuario;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

@SuppressWarnings({ "unchecked"})
public class GUIGestionarUsuario extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelTablaUsuarios = null;
	private JPanel panelBotones = null;
	private JButton botonIngresarUsuario = null;
	private JButton botonModificarUsuario = null;
	private JButton botonEliminarUsuario = null;
	private JButton botonCancelar = null;
	private JButton botonAceptarUsuario = null;
	private JScrollPane scrollTabla = null;
	private JTable tablaUsuarios = null;
	private JPanel panelFiltro = null;
	private JTextField campoFiltro;
	private TableRowSorter<DefaultTableModel> tFiltro;
	private JLabel labelFiltro = null;

	public GUIGestionarUsuario(DefaultTableModel m){
		super();
		this.tFiltro = new TableRowSorter(m);
		this.tablaUsuarios = new JTable(m);
		this.tablaUsuarios.setRowSelectionAllowed(false);
		this.tablaUsuarios.getTableHeader().setReorderingAllowed(false);
		this.tablaUsuarios.setRowSorter(tFiltro);
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
		this.setSize(600, 300);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setContentPane(getJContentPane());
		this.setTitle("GESTIONAR USUARIO");
		this.setLocationRelativeTo(null);
		this.setModal(true);
	}

	private JPanel getJContentPane(){
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelBotones(), BorderLayout.SOUTH);
			jContentPane.add(getPanelTablaUsuarios(),BorderLayout.CENTER);
			jContentPane.add(getPanelFiltro(),BorderLayout.NORTH);
		}
		return jContentPane;
	}

	private JPanel getPanelTablaUsuarios(){
		if (panelTablaUsuarios == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.weightx = 1.0;
			panelTablaUsuarios = new JPanel();
			panelTablaUsuarios.setLayout(new GridBagLayout());
			panelTablaUsuarios.setSize(new Dimension(562, 136));
			panelTablaUsuarios.add(getScrollTabla(), gridBagConstraints);
		}
		return panelTablaUsuarios;
	}

	private JPanel getPanelBotones(){
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout());
			panelBotones.add(getBotonIngresarUsuario(), null);
			panelBotones.add(getBotonModificarUsuario(), null);
			panelBotones.add(getBotonEliminarUsuario(), null);
			panelBotones.add(getBotonCancelar(), null);
			panelBotones.add(getBotonAceptarUsuario(), null);
		}
		return panelBotones;
	}

	public JButton getBotonIngresarUsuario() {
		if (botonIngresarUsuario == null) {
			botonIngresarUsuario = new JButton();
			botonIngresarUsuario.setText("INGRESAR USUARIO");
			botonIngresarUsuario.setMnemonic('I');
			botonIngresarUsuario.registerKeyboardAction(botonIngresarUsuario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonIngresarUsuario.registerKeyboardAction(botonIngresarUsuario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonIngresarUsuario.setPreferredSize(new Dimension(180, 30));
			botonIngresarUsuario.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
		}
		return botonIngresarUsuario;
	}

	public JButton getBotonModificarUsuario() {
		if (botonModificarUsuario == null) {
			botonModificarUsuario = new JButton();
			botonModificarUsuario.setText("MODIFICAR USUARIO");
			botonModificarUsuario.setMnemonic('M');
			botonModificarUsuario.registerKeyboardAction(botonModificarUsuario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonModificarUsuario.registerKeyboardAction(botonModificarUsuario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonModificarUsuario.setPreferredSize(new Dimension(180, 30));
			botonModificarUsuario.setIcon(new ImageIcon(getClass().getResource("/icono/script_edit.png")));
		}
		return botonModificarUsuario;
	}

	public JButton getBotonEliminarUsuario() {
		if (botonEliminarUsuario == null) {
			botonEliminarUsuario = new JButton();
			botonEliminarUsuario.setText("ELIMINAR USUARIO");
			botonEliminarUsuario.setMnemonic('E');
			botonEliminarUsuario.registerKeyboardAction(botonEliminarUsuario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonEliminarUsuario.registerKeyboardAction(botonEliminarUsuario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonEliminarUsuario.setPreferredSize(new Dimension(180, 30));
			botonEliminarUsuario.setIcon(new ImageIcon(getClass().getResource("/icono/delete.png")));
		}
		return botonEliminarUsuario;
	}

	public JButton getBotonCancelar() {
		if (botonCancelar == null) {
			botonCancelar = new JButton();
			botonCancelar.setText("CANCELAR");
			botonCancelar.setIcon(new ImageIcon(getClass().getResource("/icono/cancel.png")));
		}
		return botonCancelar;
	}

	public JButton getBotonAceptarUsuario() {
		if (botonAceptarUsuario == null) {
			botonAceptarUsuario = new JButton();
			botonAceptarUsuario.setText("ACEPTAR");
			botonAceptarUsuario.setMnemonic('A');
			botonAceptarUsuario.registerKeyboardAction(botonAceptarUsuario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonAceptarUsuario.registerKeyboardAction(botonAceptarUsuario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonAceptarUsuario.setPreferredSize(new Dimension(130, 30));
			botonAceptarUsuario.setIcon(new ImageIcon(getClass().getResource("/icono/accept.png")));
		}
		return botonAceptarUsuario;
	}

	private JScrollPane getScrollTabla() {
		if (scrollTabla == null) {
			scrollTabla = new JScrollPane();
			scrollTabla.setViewportView(getTablaUsuarios());
		}
		return scrollTabla;
	}

	private JTable getTablaUsuarios() {
		if (tablaUsuarios == null) {
			tablaUsuarios = new JTable();
		}
		return tablaUsuarios;
	}
	
	public void setListenerButtons(ActionListener al){
		this.botonAceptarUsuario.addActionListener(al);
		this.botonEliminarUsuario.addActionListener(al);
		this.botonIngresarUsuario.addActionListener(al);
		this.botonModificarUsuario.addActionListener(al);
		this.botonCancelar.addActionListener(al);
	}
	
	public void setListenerMouse(MouseAdapter ma){
		this.tablaUsuarios.addMouseListener(ma);
	}

	private JPanel getPanelFiltro() {
		if (panelFiltro == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.gridy = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.insets = new Insets(0, 0, 0, 200);
			gridBagConstraints1.gridx = 1;
			panelFiltro = new JPanel();
			panelFiltro.setLayout(new GridBagLayout());
			panelFiltro.setSize(new Dimension(754, 41));
			panelFiltro.add(getCampoFiltro(), gridBagConstraints1);
			panelFiltro.add(getLabelFiltro(), gridBagConstraints3);
		}
		return panelFiltro;
	}
	
	private JTextField getCampoFiltro() {
		if (campoFiltro == null) {
			campoFiltro = new JTextField();
			campoFiltro.setPreferredSize(new Dimension(150, 20));
			campoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
				
				public void keyReleased(final java.awt.event.KeyEvent e){tFiltro.setRowFilter(RowFilter.regexFilter(campoFiltro.getText().toUpperCase(), 0));}
	            });
		}
		return campoFiltro;
	}
	
	private JLabel getLabelFiltro() {
		if (labelFiltro == null) {
			labelFiltro = new JLabel();
			labelFiltro.setText("FILTRO:");
		}
		return labelFiltro;
	}
	
	public TableRowSorter getTableRowSorter(){
		return this.tFiltro;
	}
	
	public JTable getTabla(){
		return this.tablaUsuarios;
	}
} 
