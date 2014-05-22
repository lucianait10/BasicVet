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

package cuGestionarFichaClinica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import persistencia.Persistencia;
import persistencia.dominio.Animal;
import persistencia.dominio.Circulatorio;
import persistencia.dominio.Digestivo;
import persistencia.dominio.ExploracionGeneral;
import persistencia.dominio.FichaClinica;
import persistencia.dominio.Genital;
import persistencia.dominio.Locomotor;
import persistencia.dominio.Nervioso;
import persistencia.dominio.Ojos;
import persistencia.dominio.Piel;
import persistencia.dominio.Respiratorio;
import persistencia.dominio.Urinario;
import cuGestionarAnimal.ControlAnimal;
import cuGestionarEstudioComplementario.MediadorGestionarEstudioComplementario;

public class MediadorIngresarFichaClinica implements ActionListener{
	private GUIInterfazFichaClinica ingFichaClinica;
	private Date fecha = new Date();
	private int numeroBuscar;
	private DefaultTableModel tablaInsertar;
	private Persistencia persistencia = new Persistencia();
	private MediadorGestionarFichaClinica medGes;
	private ControlFichaClinica controlFichaCLinica;
	private ControlAnimal controlAnimal;
	private GUIGestionarFichaClinica gesFichaClinica;
	
	public MediadorIngresarFichaClinica(int numero, DefaultTableModel m, MediadorGestionarFichaClinica medGes, GUIGestionarFichaClinica gesFichaClinica){
		super();
		this.gesFichaClinica = gesFichaClinica;
		this.medGes = medGes;
		this.numeroBuscar = numero;
		this.tablaInsertar = m;
		this.inicializar();
	}

	@SuppressWarnings("deprecation")
	private void inicializar(){
		try{
			persistencia.iniciarTransaccion();
			this.controlAnimal = new ControlAnimal();
			this.controlFichaCLinica = new ControlFichaClinica();
			this.ingFichaClinica = new GUIInterfazFichaClinica(this.gesFichaClinica);
	        this.ingFichaClinica.getCampoFecha().setText(fecha.getDate()+"/"+(fecha.getMonth()+1)+"/"+(fecha.getYear()+1900));;
	    	this.ingFichaClinica.getBotonGuardar().setEnabled(true);
	    	this.ingFichaClinica.getBotonEstudios().setEnabled(false);
	    	this.ingFichaClinica.setListenerButtons(this);
	        Animal animal = controlAnimal.obtenerAnimal(this.numeroBuscar);
	        this.ingFichaClinica.getCampoId().setText(animal.getId()+"");
	        this.ingFichaClinica.getCampoNombre().setText(animal.getNombre().toUpperCase());
	        this.ingFichaClinica.getCampoRaza().setText(animal.getRaza().toUpperCase());
	        this.ingFichaClinica.getCampoEdad().setText(animal.getEdad()+"");
	        persistencia.concretarTransaccion();
	        this.ingFichaClinica.show();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	
	public void actionPerformed(ActionEvent e){
		if(this.ingFichaClinica.getBotonGuardar() == e.getSource()){
  			this.aceptarInsercion();
		}
		if(this.ingFichaClinica.getBotonCerrar() == e.getSource()){
			this.opcionCancelar();
		}
		if(this.ingFichaClinica.getBotonEstudios()==e.getSource()){
			long idFicha = Integer.parseInt(this.ingFichaClinica.getCampoNumeroFicha().getText());
			this.ingFichaClinica.setVisible(false);
			MediadorGestionarEstudioComplementario gec = new MediadorGestionarEstudioComplementario(idFicha,ingFichaClinica);
			gec.mostrar();
		}
	}
	@SuppressWarnings("deprecation")
	public void aceptarInsercion() {
		try{
			persistencia.iniciarTransaccion();
	        
	        //Ficha Clinica
	    	Animal animal = controlAnimal.obtenerAnimal(this.numeroBuscar);
	        Vector<String> anamnesis = new Vector<String>();
	        anamnesis.add(this.ingFichaClinica.getCampoAnamnesis().getText());
	        String diagnosticoDiferencial = this.ingFichaClinica.getCampoDiagnosticoDiferencial().getText();
	        String diagnosticoPresuntivo = this.ingFichaClinica.getCampoDiagnosticoPresuntivo().getText();
	        String pronostico = this.ingFichaClinica.getCampoPronostico().getText();
	        String manejoClinicoTerapeutico= this.ingFichaClinica.getCampoManejoClinicoTerapeutico().getText(); 
	        FichaClinica ficha = new FichaClinica(fecha,anamnesis,diagnosticoDiferencial,diagnosticoPresuntivo,pronostico,manejoClinicoTerapeutico);
	        animal.agregarFicha(ficha);
	        
	        //********Exploracion General*******
	        String temp = this.ingFichaClinica.getPanelExploracionGeneral().getCampoTemperatura().getText();
	        String frCar = this.ingFichaClinica.getPanelExploracionGeneral().getCampoFrecuenciaCardiaca().getText();
	        String frRes = this.ingFichaClinica.getPanelExploracionGeneral().getCampoFrecuenciaRespiratoria().getText();
	        String estGral = this.ingFichaClinica.getPanelExploracionGeneral().getCampoEstadoGeneral().getText();
	        String muOcular = this.ingFichaClinica.getPanelExploracionGeneral().getCampoMucosaOcular().getText();
			String muGenital = this.ingFichaClinica.getPanelExploracionGeneral().getCampoMucosaGenital().getText();
			String muBucal = this.ingFichaClinica.getPanelExploracionGeneral().getCampoMucosaBucal().getText();
			String muOtros = this.ingFichaClinica.getPanelExploracionGeneral().getCampoMucosaOtros().getText();
			String hidratacion = this.ingFichaClinica.getPanelExploracionGeneral().getCampoHidratacion().getText();
			String gaLinfaticos = this.ingFichaClinica.getPanelExploracionGeneral().getCampoGangliosLinfaticos().getText();
			ExploracionGeneral expGral = new ExploracionGeneral(temp,frCar,frRes,estGral,muOcular,muGenital,muBucal,muOtros,hidratacion,gaLinfaticos,ficha);
			controlFichaCLinica.guardarExpGeneral(expGral);
			
			//********Circulatorio******
			String ritmo = this.ingFichaClinica.getPanelCirculatorio().getCampoRitmo().getText();
			String ascultacion = this.ingFichaClinica.getPanelCirculatorio().getCampoAscultacion().getText();
			String pulso = this.ingFichaClinica.getPanelCirculatorio().getCampoPulso().getText();
			String soplo =this.ingFichaClinica.getPanelCirculatorio().getCampoSoplo().getText();
			String ecg = this.ingFichaClinica.getPanelCirculatorio().getCampoEcg().getText();
			String otros = this.ingFichaClinica.getPanelCirculatorio().getCampoOtros().getText();
			Vector<String> observacion = new Vector<String>();
			observacion.add(this.ingFichaClinica.getPanelCirculatorio().getCampoObservacion().getText());
			Circulatorio circ = new Circulatorio(ritmo,ascultacion,pulso,soplo,ecg,otros,observacion,ficha);
			controlFichaCLinica.guardarCirculatorio(circ);
			
			//*******Digestivo*******
			String cavidadOral = this.ingFichaClinica.getPanelDigestivo().getCampoCavidadOral().getText();
			String palpacionAbd = this.ingFichaClinica.getPanelDigestivo().getCampoPalpacionAbdominal().getText();
			String vomitos = this.ingFichaClinica.getPanelDigestivo().getCampoVomitos().getText();
			String disfagia = this.ingFichaClinica.getPanelDigestivo().getCampoDisfagia().getText();
			String materiaFecal = this.ingFichaClinica.getPanelDigestivo().getCampoMateriaFecal().getText();
			observacion.clear();
			observacion.add(this.ingFichaClinica.getPanelDigestivo().getCampoObservacion().getText());
			Digestivo dig = new Digestivo(cavidadOral,palpacionAbd,vomitos,disfagia,materiaFecal,observacion,ficha);
			controlFichaCLinica.guardarDigestivo(dig);
			
			//******Genital********
			String machoExp = this.ingFichaClinica.getPanelGenital().getCampoMachoExp().getText();
			String machoSecreciones = this.ingFichaClinica.getPanelGenital().getCampoMachoSecreciones().getText();
			String machoTactoRectal = this.ingFichaClinica.getPanelGenital().getCampoMachoTactoRectal().getText();
			String hembraExp = this.ingFichaClinica.getPanelGenital().getCampoHembraExp().getText();
			String hembraSecreciones = this.ingFichaClinica.getPanelGenital().getCampoHembraSecreciones().getText();
			String hembraGlanMamarias = this.ingFichaClinica.getPanelGenital().getCampoHembraGlandulaMamarias().getText();
			observacion.clear();
			observacion.add(this.ingFichaClinica.getPanelGenital().getCampoObservacion().getText());
			Genital genital = new Genital(machoExp,machoSecreciones,machoTactoRectal,hembraExp,hembraSecreciones,hembraGlanMamarias,observacion,ficha);
			controlFichaCLinica.guardarGenital(genital);
	        
	        //*******Locomotor*******
	        String lesion = this.ingFichaClinica.getPanelLocomotor().getCampoLesion().getText();
	        String ubicacion = this.ingFichaClinica.getPanelLocomotor().getCampoUbicacion().getText();
	        observacion.clear();
	        observacion.add(this.ingFichaClinica.getPanelLocomotor().getCampoObservacion().getText());
	        Locomotor locomotor = new Locomotor(lesion,ubicacion,observacion,ficha);
	        controlFichaCLinica.guardarLocomotor(locomotor);
	        
	        //********Nervioso********
	        String paralisis = this.ingFichaClinica.getPanelNervioso().getCampoParalisis().getText();
	        String convulsiones = this.ingFichaClinica.getPanelNervioso().getCampoConvulsiones().getText();
	        String ataxia = this.ingFichaClinica.getPanelNervioso().getCampoAtaxia().getText();
	        String reflejos = this.ingFichaClinica.getPanelNervioso().getCampoReflejos().getText();
	        String sensibilidad = this.ingFichaClinica.getPanelNervioso().getCampoSensibilidad().getText();
	        String conducta = this.ingFichaClinica.getPanelNervioso().getCampoConducta().getText();
	        String sensorio = this.ingFichaClinica.getPanelNervioso().getCampoSensorio().getText();
	        otros = this.ingFichaClinica.getPanelNervioso().getCampoOtros().getText();
	        observacion.clear();
	        observacion.add(this.ingFichaClinica.getPanelNervioso().getCampoObservacion().getText());
	        Nervioso nervioso = new Nervioso(paralisis,convulsiones,ataxia,reflejos,sensibilidad,conducta,sensorio,otros,observacion,ficha);
	        controlFichaCLinica.guardarNervioso(nervioso);
	        
	        //*********Ojos*********
	        String izquierdo = this.ingFichaClinica.getPanelOjos().getCampoIzquierdo().getText();
	        String derecho = this.ingFichaClinica.getPanelOjos().getCampoDerecho().getText();
	        observacion.clear();
	        observacion.add(this.ingFichaClinica.getPanelOjos().getCampoObservacion().getText());
	        Ojos ojos = new Ojos(izquierdo,derecho,observacion,ficha);
	        controlFichaCLinica.guardarOjos(ojos);
	        
	        //*********Piel**********
	        String tipoLesion = this.ingFichaClinica.getPanelPiel().getCampoTipoLesion().getText();
	        String forma = this.ingFichaClinica.getPanelPiel().getCampoForma().getText();
	        ubicacion = this.ingFichaClinica.getPanelPiel().getCampoUbicacion().getText();
	        String simetrica = this.ingFichaClinica.getPanelPiel().getCampoSimetrica().getText();
	        String olor = this.ingFichaClinica.getPanelPiel().getCampoOlor().getText();
	        String prurito = this.ingFichaClinica.getPanelPiel().getCampoPrurito().getText();
	        String mantoPiloso = this.ingFichaClinica.getPanelPiel().getCampoMantoPiloso().getText();
	        String ectoparasitos = this.ingFichaClinica.getPanelPiel().getCampoEctoparasitos().getText();
	        String oidoIzq = this.ingFichaClinica.getPanelPiel().getCampoOidoIzquierdo().getText();
	        String oidoDer = this.ingFichaClinica.getPanelPiel().getCampoOidoDerecho().getText();
	        observacion.remove(0);
	        observacion.add(this.ingFichaClinica.getPanelPiel().getCampoObservacion().getText());
	        Piel piel = new Piel(tipoLesion,forma,ubicacion,simetrica,olor,prurito,mantoPiloso,ectoparasitos,oidoIzq,oidoDer,observacion,ficha);
	        controlFichaCLinica.guardarPiel(piel);
	        	
	        //*********Respiratorio********
	        String respiracion = this.ingFichaClinica.getPanelRespiratorio().getCampoRespiracion().getText();
	        String tos = this.ingFichaClinica.getPanelRespiratorio().getCampoTos().getText();
	        String auscultacion = this.ingFichaClinica.getPanelRespiratorio().getCampoAuscultacion().getText();
	        String refTusigeno = this.ingFichaClinica.getPanelRespiratorio().getCampoReflejoTusigeno().getText();
	        observacion.remove(0);
	        observacion.add(this.ingFichaClinica.getPanelRespiratorio().getCampoObservacion().getText());
	        Respiratorio resp = new Respiratorio(respiracion,tos,auscultacion,refTusigeno,observacion,ficha);
	        controlFichaCLinica.guardarRespiratorio(resp);
	        
	        //*********Urinario**********
	        String vejiga = this.ingFichaClinica.getPanelUrinario().getCampoVejiga().getText();
	        String orina  = this.ingFichaClinica.getPanelUrinario().getCampoOrina().getText();
	        otros = this.ingFichaClinica.getPanelUrinario().getCampoOtros().getText();
	        observacion.remove(0);
	        observacion.add(this.ingFichaClinica.getPanelUrinario().getCampoObservacion().getText());
	        Urinario urinario = new Urinario(vejiga,orina,otros,observacion,ficha);
	        controlFichaCLinica.guardarUrinario(urinario);
	        Object[] s = {ficha.getId()+"",animal.getNombre().toUpperCase(),fecha.getDate()+"/"+(fecha.getMonth()+1)+"/"+(fecha.getYear()+1900)};
	        this.tablaInsertar.addRow(s);
	        this.tablaInsertar.fireTableDataChanged();
	        persistencia.concretarTransaccion();
	        this.ingFichaClinica.getCampoNumeroFicha().setText(ficha.getId()+"");
	        this.medGes.setearBotonEliminar(true);
	        this.medGes.setearBotonReporte(true);
	        this.medGes.setearBotonModificar(true);
	        this.ingFichaClinica.getBotonGuardar().setEnabled(false);
	        this.ingFichaClinica.getBotonEstudios().setEnabled(true);
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	public void opcionCancelar(){
        this.ingFichaClinica.setVisible(false);
		this.ingFichaClinica.dispose();
		this.gesFichaClinica.setVisible(true);
	}
}
