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

package cuGestionarLogin;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

public class GUILogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField campoNombre = null;
	private JLabel labelNombre = null;
	private JLabel labelContrasena = null;
	private JButton botonIngresar = null;
	private JButton botonCancelar = null;
	private JPanel panelBoton = null;
	private JPasswordField campoContrasena = null;

	public GUILogin() {
		super();
		initialize();
		cerrarVentana();
	}
	
	@SuppressWarnings({ "serial" })
	private void cerrarVentana(){
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
	    getRootPane().getActionMap().put("Cancel", new AbstractAction(){
	        
			public void actionPerformed(ActionEvent e){
	        	setVisible(false);
	        	dispose();
	        	System.exit(0);
	        }
	    });
	    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	    addWindowListener(new WindowAdapter() {
	    	
			public void windowClosing(java.awt.event.WindowEvent evt){
	    		setVisible(false);
	    		dispose();
	    		System.exit(0);
	    	}
	    });
	}
	
	private void initialize() {
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints21.gridy = 1;
		gridBagConstraints21.weightx = 1.0;
		gridBagConstraints21.ipadx = 200;
		gridBagConstraints21.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints21.gridx = 2;
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 0;
		gridBagConstraints11.fill = GridBagConstraints.BOTH;
		gridBagConstraints11.gridwidth = 3;
		gridBagConstraints11.gridy = 2;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.insets = new Insets(0, 10, 0, 10);
		gridBagConstraints2.gridy = 1;
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints1.gridy = 0;
		gridBagConstraints1.weightx = 1.0;
		gridBagConstraints1.ipadx = 200;
		gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints1.gridx = 2;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.insets = new Insets(0, 10, 0, 10);
		gridBagConstraints.gridy = 0;
		this.setSize(336, 150);
		this.setLayout(new GridBagLayout());
		this.add(getLabelNombre(), gridBagConstraints);
		this.add(getCampoNombre(), gridBagConstraints1);
		this.add(getLabelContrasena(), gridBagConstraints2);
		this.add(getPanelBoton(), gridBagConstraints11);
		this.add(getCampoContrasena(), gridBagConstraints21);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setTitle("LOGIN");
		this.setLocationRelativeTo(null);
	}

	public JTextField getCampoNombre() {
		if (campoNombre == null) {
			campoNombre = new JTextField();
			campoNombre.addKeyListener(new KeyAdapter(){
				
				public void keyPressed(KeyEvent ke) {if (ke.getKeyCode() == KeyEvent.VK_ENTER) {botonIngresar.doClick();}}
			});
		}
		return campoNombre;
	}

	private JLabel getLabelNombre() {
		if (labelNombre == null) {
			labelNombre = new JLabel();
			labelNombre.setText("Nombre de Usuario:");
		}
		return labelNombre;
	}

	private JLabel getLabelContrasena() {
		if (labelContrasena == null) {
			labelContrasena = new JLabel();
			labelContrasena.setText("Contraseña:");
		}
		return labelContrasena;
	}

	public JButton getBotonIngresar() {
		if (botonIngresar == null) {
			botonIngresar = new JButton();
			botonIngresar.setText("Ingresar");
			botonIngresar.addKeyListener(new KeyAdapter(){
				
				public void keyPressed(KeyEvent ke) {if (ke.getKeyCode() == KeyEvent.VK_ENTER) {botonIngresar.doClick();}}
			});
		}
		return botonIngresar;
	}

	public JButton getBotonCancelar() {
		if (botonCancelar == null) {
			botonCancelar = new JButton();
			botonCancelar.setText("Cancelar");
			botonCancelar.addKeyListener(new KeyAdapter(){
				
				public void keyPressed(KeyEvent ke) {if (ke.getKeyCode() == KeyEvent.VK_ENTER) {botonCancelar.doClick();}}
			});
		}
		return botonCancelar;
	}

	private JPanel getPanelBoton() {
		if (panelBoton == null) {
			panelBoton = new JPanel();
			panelBoton.setLayout(new FlowLayout());
			panelBoton.add(getBotonIngresar(), null);
			panelBoton.add(getBotonCancelar(), null);
		}
		return panelBoton;
	}

	public JPasswordField getCampoContrasena() {
		if (campoContrasena == null) {
			campoContrasena = new JPasswordField();
			campoContrasena.addKeyListener(new KeyAdapter(){
				
				public void keyPressed(KeyEvent ke) {if (ke.getKeyCode() == KeyEvent.VK_ENTER) {botonIngresar.doClick();}}
			});
		}
		return campoContrasena;
	}
	
	public void setActionListener(ActionListener al){
		this.botonIngresar.addActionListener(al);
		this.botonCancelar.addActionListener(al);
	}
}
