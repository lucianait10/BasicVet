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
import java.awt.Color;
//import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;

public class GUIUsuario extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelUsuario = null;
	private JTextField campoNombre = null;
	private JLabel labelNombre = null;
	private JPasswordField campoContrasena = null;
	private JLabel labelContrasena = null;
	private JPasswordField campoNuevaContrasena = null;
	private JLabel labelNuevaContrasena = null;
	private JLabel labelUsuarios = null;
	private JPanel panelBotonUsuario = null;
	private JButton botonIngresar = null;
	private JButton botonModificar = null;
	public GUIUsuario(){
		super();
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
		this.setSize(464, 200);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setContentPane(getJContentPane());
		this.setTitle("USUARIO");
		this.setLocationRelativeTo(null);
		this.setModal(true);
	}
	
	private JPanel getJContentPane() {
		if (jContentPane == null){
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelBotonUsuario(), BorderLayout.SOUTH);
			jContentPane.add(getPanelUsuario(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	private JPanel getPanelUsuario() {
		if (panelUsuario == null) {
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.insets = new Insets(0, 0, 3, 0);
			gridBagConstraints9.gridy = 0;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.insets = new Insets(15, 20, 0, 0);
			gridBagConstraints7.gridy = 3;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.gridy = 3;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.insets = new Insets(15, 0, 0, 0);
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.insets = new Insets(0, 20, 0, 0);
			gridBagConstraints4.gridwidth = 1;
			gridBagConstraints4.ipadx = 0;
			gridBagConstraints4.ipady = 0;
			gridBagConstraints4.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints4.gridy = 2;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 2;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.insets = new Insets(2, 0, 0, 0);
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.insets = new Insets(0, 20, 0, 0);
			gridBagConstraints1.gridy = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.ipadx = 2;
			gridBagConstraints.anchor = GridBagConstraints.CENTER;
			gridBagConstraints.gridx = 1;
			panelUsuario = new JPanel();
			panelUsuario.setLayout(new GridBagLayout());
			panelUsuario.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelUsuario.add(getCampoNombre(), gridBagConstraints);
			panelUsuario.add(getLabelNombre(), gridBagConstraints1);
			panelUsuario.add(getCampoContrasena(), gridBagConstraints3);
			panelUsuario.add(getLabelContrasena(), gridBagConstraints4);
			panelUsuario.add(getCampoNuevaContrasena(), gridBagConstraints5);
			panelUsuario.add(getLabelNuevaContrasena(), gridBagConstraints7);
			panelUsuario.add(getLabelUsuarios(), gridBagConstraints9);
		}
		return panelUsuario;
	}

	public JTextField getCampoNombre() {
		if (campoNombre == null) {
			campoNombre = new JTextField();
			campoNombre.setEditable(true);
			campoNombre.setBackground(Color.white);
		}
		return campoNombre;
	}

	private JLabel getLabelNombre() {
		if (labelNombre == null) {
			labelNombre = new JLabel();
			labelNombre.setText("Nombre:");
		}
		return labelNombre;
	}

	public JPasswordField getCampoContrasena() {
		if (campoContrasena == null) {
			campoContrasena = new JPasswordField();
		}
		return campoContrasena;
	}

	private JLabel getLabelContrasena() {
		if (labelContrasena == null) {
			labelContrasena = new JLabel();
			labelContrasena.setText("Contraseña:");
		}
		return labelContrasena;
	}

	public JPasswordField getCampoNuevaContrasena() {
		if (campoNuevaContrasena == null) {
			campoNuevaContrasena = new JPasswordField();
		}
		return campoNuevaContrasena;
	}

	public JLabel getLabelNuevaContrasena() {
		if (labelNuevaContrasena == null) {
			labelNuevaContrasena = new JLabel();
			labelNuevaContrasena.setText("Nueva Contraseña: ");
		}
		return labelNuevaContrasena;
	}

	private JLabel getLabelUsuarios() {
		if (labelUsuarios == null) {
			labelUsuarios = new JLabel();
			labelUsuarios.setText("DATOS USUARIO:");
		}
		return labelUsuarios;
	}

	private JPanel getPanelBotonUsuario() {
		if (panelBotonUsuario == null) {
			panelBotonUsuario = new JPanel();
			panelBotonUsuario.setLayout(new FlowLayout());
			panelBotonUsuario.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelBotonUsuario.add(getBotonIngresar(), null);
			panelBotonUsuario.add(getBotonModificar(), null);
		}
		return panelBotonUsuario;
	}

	public JButton getBotonIngresar() {
		if (botonIngresar == null) {
			botonIngresar = new JButton();
			botonIngresar.setText("INGRESAR");
			botonIngresar.setMnemonic('I');
			botonIngresar.registerKeyboardAction(botonIngresar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonIngresar.registerKeyboardAction(botonIngresar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonIngresar.setPreferredSize(new Dimension(130, 30));
			botonIngresar.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
		}
		return botonIngresar;
	}

	public JButton getBotonModificar() {
		if (botonModificar == null) {
			botonModificar = new JButton();
			botonModificar.setText("MODIFICAR");
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

	public void setListenerButtons(ActionListener al){
		this.botonIngresar.addActionListener(al);
		this.botonModificar.addActionListener(al);
	}
}  
