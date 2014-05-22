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

public class GUIGestionarTurno extends JDialog{
	private static final long serialVersionUID = 1L;
	private JPanel menu = null;
	private JButton ingresar;
	private JButton modificar;
	private JButton eliminar;
	private JPanel panelTurnos = null;  //  @jve:decl-index=0:
	private JLabel txtListaTurnos = null;
	private JScrollPane listaTurnosScroll = null;
	//MLucero
	public JTable listaTurnos = null;
	private JPanel jPanel = null;
	private JButton botonBuscarAnimal = null;
	private JPanel panelBuscarAnimal = null;
	private JLabel labelId = null;
	private JLabel labelNombre = null;
	private JTextField campoId = null;
	private JTextField campoNombre = null;
	public GUIGestionarTurno(DefaultTableModel m) {
		super();
		this.listaTurnos = new JTable(m);
		this.listaTurnos.getTableHeader().setReorderingAllowed(false);
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
		this.setTitle("GESTIONAR TURNO");
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setContentPane(getJPanel());
	}
	
//-------------------------------------------------------------------------------------------------------
	public JPanel getMenu() {
		if (menu == null){
			menu = new JPanel();
			menu.setLayout(new FlowLayout());
			menu.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			menu.add(getIngresar(), null);
			menu.add(getModificar(), null);
			menu.add(getEliminar(), null);
		}
		return menu;
	}
//-------------------------------------------------------------------------------------------------------
	public JButton getIngresar() {
		if (ingresar == null) {
			ingresar = new JButton();
			ingresar.setText("INGRESAR TURNO");
			ingresar.setMnemonic('I');
			ingresar.registerKeyboardAction(ingresar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			ingresar.registerKeyboardAction(ingresar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//ingresar.setPreferredSize(new Dimension(160, 30));
			ingresar.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
		}
		return ingresar;
	}
//-------------------------------------------------------------------------------------------------------
	public JButton getModificar() {
		if (modificar == null) {
			modificar = new JButton();
			modificar.setText("MODIFICAR TURNO");
			modificar.setMnemonic('m');
			modificar.registerKeyboardAction(modificar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			modificar.registerKeyboardAction(modificar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//modificar.setPreferredSize(new Dimension(160, 30));
			modificar.setIcon(new ImageIcon(getClass().getResource("/icono/script_edit.png")));
			
		}
		return modificar;
	}
	public JButton getEliminar() {
		if (eliminar == null) {
			eliminar = new JButton();
			eliminar.setText("ELIMINAR TURNO");
			eliminar.setMnemonic('E');
			eliminar.registerKeyboardAction(eliminar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			eliminar.registerKeyboardAction(eliminar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);			
			//eliminar.setPreferredSize(new Dimension(160, 30));
			eliminar.setIcon(new ImageIcon(getClass().getResource("/icono/delete.png")));
		}
		return eliminar;
	}
	
	public void setListenerButtons(ActionListener al){
		this.ingresar.addActionListener(al);
		this.modificar.addActionListener(al);
		this.eliminar.addActionListener(al);
		this.botonBuscarAnimal.addActionListener(al);
	}

	private JPanel getPanelTurnos() {
		if (panelTurnos == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.insets = new Insets(5, 0, 5, 0);
			gridBagConstraints.gridy = 0;
			txtListaTurnos = new JLabel();
			txtListaTurnos.setText("LISTA DE TURNOS:");
			panelTurnos = new JPanel();
			panelTurnos.setLayout(new BorderLayout());
			panelTurnos.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelTurnos.add(getPanelBuscarAnimal(), BorderLayout.NORTH);
			panelTurnos.add(getListaTurnosScroll(), BorderLayout.CENTER);
		}
		return panelTurnos;
	}


	private JScrollPane getListaTurnosScroll() {
		if (listaTurnosScroll == null) {
			listaTurnosScroll = new JScrollPane();
			listaTurnosScroll.setViewportView(getJTable());
		}
		return listaTurnosScroll;
	}

	public JTable getJTable() {
		if (listaTurnos == null) {
			listaTurnos = new JTable();
			listaTurnos.setRowSelectionAllowed(false);
			listaTurnos.getTableHeader().setReorderingAllowed(false);
		}
		return listaTurnos;
	}
	
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BorderLayout());
			jPanel.add(getMenu(), BorderLayout.SOUTH);
			jPanel.add(getPanelTurnos(), BorderLayout.CENTER);
		}
		return jPanel;
	}

	public JButton getBotonBuscarAnimal() {
		if (botonBuscarAnimal == null) {
			botonBuscarAnimal = new JButton("BUSCAR ANIMAL");
			botonBuscarAnimal.setMnemonic('B');
			botonBuscarAnimal.registerKeyboardAction(botonBuscarAnimal.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonBuscarAnimal.registerKeyboardAction(botonBuscarAnimal.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonBuscarAnimal.setPreferredSize(new Dimension(160, 30));
			botonBuscarAnimal.setIcon(new ImageIcon(getClass().getResource("/icono/search.jpg")));		
		}
		return botonBuscarAnimal;
	}

	private JPanel getPanelBuscarAnimal() {
		if (panelBuscarAnimal == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.insets = new Insets(10, 0, 10, 0);
			gridBagConstraints2.gridx = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 2;
			gridBagConstraints6.insets = new Insets(10, 0, 10, 0);
			gridBagConstraints6.gridy = 0;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 4;
			gridBagConstraints5.insets = new Insets(0, 10, 10, 10);
			gridBagConstraints5.gridy = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints4.gridy = 1;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.insets = new Insets(10, 0, 10, 0);
			gridBagConstraints4.gridx = 3;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 2;
			gridBagConstraints3.anchor = GridBagConstraints.EAST;
			gridBagConstraints3.gridy = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints1.gridy = 1;
			panelBuscarAnimal = new JPanel();
			panelBuscarAnimal.setLayout(new GridBagLayout());
			panelBuscarAnimal.setPreferredSize(new Dimension(700, 80));
			panelBuscarAnimal.setMinimumSize(new Dimension(302, 80));
			panelBuscarAnimal.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelBuscarAnimal.add(getLabelId(), gridBagConstraints1);
			panelBuscarAnimal.add(getLabelNombre(), gridBagConstraints3);
			panelBuscarAnimal.add(getCampoNombre(), gridBagConstraints4);
			panelBuscarAnimal.add(getBotonBuscarAnimal(), gridBagConstraints5);
			panelBuscarAnimal.add(txtListaTurnos, gridBagConstraints6);
			panelBuscarAnimal.add(getCampoId(), gridBagConstraints2);
		}
		return panelBuscarAnimal;
	}

	private JLabel getLabelId() {
		if (labelId == null) {
			labelId = new JLabel();
			labelId.setText("ID:");
		}
		return labelId;
	}

	private JLabel getLabelNombre() {
		if (labelNombre == null) {
			labelNombre = new JLabel();
			labelNombre.setText("NOMBRE:");
		}
		return labelNombre;
	}

	public JTextField getCampoId() {
		if (campoId == null) {
			campoId = new JTextField();
			campoId.setPreferredSize(new Dimension(200, 20));
			campoId.setBackground(new Color(238, 238, 238));
			campoId.setEditable(false);
		}
		return campoId;
	}

	public JTextField getCampoNombre() {
		if (campoNombre == null) {
			campoNombre = new JTextField();
			campoNombre.setPreferredSize(new Dimension(200, 20));
			campoNombre.setBackground(new Color(238, 238, 238));
			campoNombre.setEditable(false);
		}
		return campoNombre;
	}

	public void setListenerMouse(MouseAdapter ma){
		this.listaTurnos.addMouseListener(ma);
	}
}
