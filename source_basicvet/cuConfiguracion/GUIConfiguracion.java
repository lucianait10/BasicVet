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

package cuConfiguracion;
 
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class GUIConfiguracion extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private PanelTurno panelTurno = new PanelTurno();
	private PanelAnimal panelAnimal = new PanelAnimal();
	private PanelBasicVet panelBasicVet = new PanelBasicVet();
	private PanelEstudio panelEstudio = new PanelEstudio();
	private Object[][] titulo = {{"<html><body><table width='100'>Turno</table></body></html>",panelTurno},
								{"<html><body><table width='100'>Animal</table></body></html>",panelAnimal},
								{"<html><body><table width='100'>BasicVet</table></body></html>",panelBasicVet},
								{"<html><body><table width='100'>Estudio</table></body></html>",panelEstudio}};
	private JTabbedPane configuraciones;
	
	public GUIConfiguracion() {
		super();
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

	private void initialize() {
		this.setSize(800, 600);
		this.setContentPane(getJContentPane());
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setTitle("CONFIGURACION");
		this.setLocationRelativeTo(null);
		this.setModal(true);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getConfiguraciones(), BorderLayout.CENTER);
		}
		return jContentPane;
	}
	
	private JTabbedPane getConfiguraciones(){
		if (configuraciones == null) {
			configuraciones = new JTabbedPane(SwingConstants.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);
			for(int i = 0;i<titulo.length;i++){
				configuraciones.addTab((String) titulo[i][0],(JPanel)titulo[i][1]);
			}
		}
		return configuraciones;
	}
	
	public PanelTurno getPanelTurno(){
		return panelTurno;
	}
	public PanelAnimal getPanelAnimal(){
		return panelAnimal;
	}
	public PanelBasicVet getPanelBasicVet(){
		return panelBasicVet;
	}
	public PanelEstudio getPanelEstudio(){
		return panelEstudio;
	}
}