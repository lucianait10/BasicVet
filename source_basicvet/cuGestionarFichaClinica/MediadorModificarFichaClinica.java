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
import java.util.Vector;

import javax.swing.JOptionPane;

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

public class MediadorModificarFichaClinica implements ActionListener {

	private GUIInterfazFichaClinica ingFichaClinica;
	private FichaClinica ficha;
	private ExploracionGeneral expGral;
	private Circulatorio circ;
	private Digestivo dig;
	private Respiratorio res;
	private Genital gen;
	private Locomotor loc;
	private Nervioso ner;
	private Ojos ojo;
	private Piel piel;
	private Urinario uri;
	private int idFicha;
	private int fila;
	private int idAnimal;
	private Persistencia persistencia = new Persistencia();
	private ControlFichaClinica controlFichaClinica;
	private ControlAnimal controlAnimal;
	private GUIGestionarFichaClinica gesFichaClinica;
	
	public MediadorModificarFichaClinica(int idAnimal, int fila,int idFicha, GUIGestionarFichaClinica gesFichaClinica){
		super();
		this.gesFichaClinica = gesFichaClinica;
		this.idAnimal=idAnimal; 
		this.fila = fila;
		this.idFicha = idFicha;
		this.inicializar();
	}

	@SuppressWarnings("deprecation")
	private void inicializar() {
		this.controlAnimal = new ControlAnimal();
		this.controlFichaClinica = new ControlFichaClinica();
		this.ingFichaClinica = new GUIInterfazFichaClinica(gesFichaClinica);
		this.ingFichaClinica.getBotonCerrar().setEnabled(true);
		this.ingFichaClinica.getBotonGuardar().setEnabled(true);
		try{
			persistencia.iniciarTransaccion();
		    if(fila>=0 && idFicha>=0){
		    	ficha = controlFichaClinica.obtenerFichaClinica(idFicha+"");
		    	Animal animal = controlAnimal.obtenerAnimal(idAnimal);
		       	this.ingFichaClinica.getCampoNumeroFicha().setText(""+ficha.getId());
		       	this.ingFichaClinica.getCampoNumeroFicha().setEditable(false);
		   		this.ingFichaClinica.getCampoFecha().setText(ficha.getFecha().getDate()+"/"+(ficha.getFecha().getMonth()+1)+"/"+(ficha.getFecha().getYear()+1900));
		       	this.ingFichaClinica.getCampoFecha().setEditable(false);
		       	this.ingFichaClinica.getCampoNombre().setText(animal.getNombre().toUpperCase());
		       	this.ingFichaClinica.getCampoNombre().setEditable(false);
		       	this.ingFichaClinica.getCampoId().setText(animal.getId()+"");
		       	this.ingFichaClinica.getCampoId().setEditable(false);
		       	this.ingFichaClinica.getCampoRaza().setText(animal.getRaza().toUpperCase());
		       	this.ingFichaClinica.getCampoRaza().setEditable(false);
		       	this.ingFichaClinica.getCampoEdad().setText(animal.getEdad()+"");
		       	this.ingFichaClinica.getCampoEdad().setEditable(false);
		       	this.ingFichaClinica.getCampoAnamnesis().setText(ficha.getAnamnesis().firstElement());
		       	this.ingFichaClinica.getCampoDiagnosticoDiferencial().setText(ficha.getDiagnosticoDiferencial());
		       	this.ingFichaClinica.getCampoDiagnosticoPresuntivo().setText(ficha.getDiagnosticoPresuntivo());
		       	this.ingFichaClinica.getCampoPronostico().setText(ficha.getPronostico());
		       	this.ingFichaClinica.getCampoManejoClinicoTerapeutico().setText(ficha.getManejoClinicoTerapeutico());
		       		       		//*********Exploracion General**********
		       	expGral = controlFichaClinica.obtenerExploracionGeneral(ficha.getId());
		       	this.ingFichaClinica.getPanelExploracionGeneral().getCampoTemperatura().setText(expGral.getTemperatura());
		       	this.ingFichaClinica.getPanelExploracionGeneral().getCampoFrecuenciaCardiaca().setText(expGral.getFc());
		       	this.ingFichaClinica.getPanelExploracionGeneral().getCampoFrecuenciaRespiratoria().setText(expGral.getFr());
		       	this.ingFichaClinica.getPanelExploracionGeneral().getCampoEstadoGeneral().setText(expGral.getEstadoGral());
		       	this.ingFichaClinica.getPanelExploracionGeneral().getCampoMucosaBucal().setText(expGral.getMucosaBucal());
		       	this.ingFichaClinica.getPanelExploracionGeneral().getCampoMucosaGenital().setText(expGral.getMucosaGenital());
		       	this.ingFichaClinica.getPanelExploracionGeneral().getCampoMucosaOcular().setText(expGral.getMucosaOcular());
		       	this.ingFichaClinica.getPanelExploracionGeneral().getCampoMucosaOtros().setText(expGral.getMucosaOtros());
		       	this.ingFichaClinica.getPanelExploracionGeneral().getCampoHidratacion().setText(expGral.getHidratacion());
		       	this.ingFichaClinica.getPanelExploracionGeneral().getCampoGangliosLinfaticos().setText(expGral.getGangliosLinfaticos());
		       	
		       	//**********Circulatorio***********
		       	circ = controlFichaClinica.obtenerCirculatorio(ficha.getId());
		       	this.ingFichaClinica.getPanelCirculatorio().getCampoRitmo().setText(circ.getRitmo());
		       	this.ingFichaClinica.getPanelCirculatorio().getCampoAscultacion().setText(circ.getAscultacion());
		       	this.ingFichaClinica.getPanelCirculatorio().getCampoPulso().setText(circ.getSoplo());
		       	this.ingFichaClinica.getPanelCirculatorio().getCampoSoplo().setText(circ.getSoplo());
		       	this.ingFichaClinica.getPanelCirculatorio().getCampoEcg().setText(circ.getEcg());
		       	this.ingFichaClinica.getPanelCirculatorio().getCampoOtros().setText(circ.getOtros());
		       	this.ingFichaClinica.getPanelCirculatorio().getCampoObservacion().setText(circ.getObservaciones().firstElement());
		       	
		       	//*********Digestivo****************
		       	dig = controlFichaClinica.obtenerDigestivo(ficha.getId());
		       	this.ingFichaClinica.getPanelDigestivo().getCampoCavidadOral().setText(dig.getCavidadOral());
		       	this.ingFichaClinica.getPanelDigestivo().getCampoPalpacionAbdominal().setText(dig.getPalpacionAbd());
		       	this.ingFichaClinica.getPanelDigestivo().getCampoVomitos().setText(dig.getVomitos());
		       	this.ingFichaClinica.getPanelDigestivo().getCampoDisfagia().setText(dig.getDisfagia());
		       	this.ingFichaClinica.getPanelDigestivo().getCampoMateriaFecal().setText(dig.getMateriaFecal());
		       	this.ingFichaClinica.getPanelDigestivo().getCampoObservacion().setText(dig.getObservaciones().firstElement());
		       	
		       	//***********Respiratorio************
		       	res = controlFichaClinica.obtenerRespiratorio(ficha.getId());
		       	this.ingFichaClinica.getPanelRespiratorio().getCampoRespiracion().setText(res.getRepiracion());
		       	this.ingFichaClinica.getPanelRespiratorio().getCampoTos().setText(res.getTos());
		       	this.ingFichaClinica.getPanelRespiratorio().getCampoReflejoTusigeno().setText(res.getReflejoTusigeno());
		       	this.ingFichaClinica.getPanelRespiratorio().getCampoAuscultacion().setText(res.getAuscultacion());
		       	this.ingFichaClinica.getPanelRespiratorio().getCampoObservacion().setText(res.getObservaciones().firstElement());
		       	
		       	//***********Genital*************
		       	gen = controlFichaClinica.obtenerGenital(ficha.getId());
		       	this.ingFichaClinica.getPanelGenital().getCampoHembraExp().setText(gen.getHembraExp());
		       	this.ingFichaClinica.getPanelGenital().getCampoHembraGlandulaMamarias().setText(gen.getHembraGlanMamarias());
		       	this.ingFichaClinica.getPanelGenital().getCampoHembraSecreciones().setText(gen.getHembraSecreciones());
		       	this.ingFichaClinica.getPanelGenital().getCampoMachoExp().setText(gen.getMachoExp());
		       	this.ingFichaClinica.getPanelGenital().getCampoMachoSecreciones().setText(gen.getMachoSecreciones());
		       	this.ingFichaClinica.getPanelGenital().getCampoMachoTactoRectal().setText(gen.getMachoTactoRectal());
		       	this.ingFichaClinica.getPanelGenital().getCampoObservacion().setText(gen.getObservaciones().firstElement());
		       	
		       	//************Locomotor************
		       	loc = controlFichaClinica.obtenerLocomotor(ficha.getId());
		       	this.ingFichaClinica.getPanelLocomotor().getCampoLesion().setText(loc.getLesion());
		       	this.ingFichaClinica.getPanelLocomotor().getCampoUbicacion().setText(loc.getUbicacion());
		       	this.ingFichaClinica.getPanelLocomotor().getCampoObservacion().setText(loc.getObservaciones().firstElement());
		       	
		       	//************Nervioso*************
		       	ner = controlFichaClinica.obtenerNervioso(ficha.getId());
		       	this.ingFichaClinica.getPanelNervioso().getCampoAtaxia().setText(ner.getAtaxia());
		       	this.ingFichaClinica.getPanelNervioso().getCampoConducta().setText(ner.getConducta());
		       	this.ingFichaClinica.getPanelNervioso().getCampoConvulsiones().setText(ner.getConvulsiones());
		       	this.ingFichaClinica.getPanelNervioso().getCampoParalisis().setText(ner.getParalisis());
		       	this.ingFichaClinica.getPanelNervioso().getCampoReflejos().setText(ner.getReflejos());
		       	this.ingFichaClinica.getPanelNervioso().getCampoSensibilidad().setText(ner.getSensibilidad());
		       	this.ingFichaClinica.getPanelNervioso().getCampoSensorio().setText(ner.getSensorio());
		       	this.ingFichaClinica.getPanelNervioso().getCampoOtros().setText(ner.getOtros());
		       	this.ingFichaClinica.getPanelNervioso().getCampoObservacion().setText(ner.getObservaciones().firstElement());
		       	       		//************Ojos****************
		       	ojo = controlFichaClinica.obtenerOjos(ficha.getId());
		       	this.ingFichaClinica.getPanelOjos().getCampoDerecho().setText(ojo.getDerecho());
		       	this.ingFichaClinica.getPanelOjos().getCampoIzquierdo().setText(ojo.getIzquierdo());
		       	this.ingFichaClinica.getPanelOjos().getCampoObservacion().setText(ojo.getObservaciones().firstElement());
		       	
		       	//************Piel***************
		       	piel = controlFichaClinica.obtenerPiel(ficha.getId());
		       	this.ingFichaClinica.getPanelPiel().getCampoTipoLesion().setText(piel.getTipoLesion());
		       	this.ingFichaClinica.getPanelPiel().getCampoEctoparasitos().setText(piel.getEctoparasitos());
		       	this.ingFichaClinica.getPanelPiel().getCampoForma().setText(piel.getForma());
		       	this.ingFichaClinica.getPanelPiel().getCampoUbicacion().setText(piel.getUbicacion());
		       	this.ingFichaClinica.getPanelPiel().getCampoSimetrica().setText(piel.getSimetrica());
		       	this.ingFichaClinica.getPanelPiel().getCampoMantoPiloso().setText(piel.getMantoPiloso());
		       	this.ingFichaClinica.getPanelPiel().getCampoOlor().setText(piel.getOlor());
		       	this.ingFichaClinica.getPanelPiel().getCampoOidoDerecho().setText(piel.getOidoDer());
		       	this.ingFichaClinica.getPanelPiel().getCampoOidoIzquierdo().setText(piel.getOidoIzq());
		       	this.ingFichaClinica.getPanelPiel().getCampoPrurito().setText(piel.getPrurito());
		       	this.ingFichaClinica.getPanelPiel().getCampoObservacion().setText(piel.getObservaciones().firstElement());
		       	
		       	//***********Urinario*************
		       	uri = controlFichaClinica.obtenerUrinario(ficha.getId());
		       	this.ingFichaClinica.getPanelUrinario().getCampoOrina().setText(uri.getOrina());
		       	this.ingFichaClinica.getPanelUrinario().getCampoVejiga().setText(uri.getVejiga());
		       	this.ingFichaClinica.getPanelUrinario().getCampoOtros().setText(uri.getOtros());
		       	this.ingFichaClinica.getPanelUrinario().getCampoObservacion().setText(uri.getObservaciones().firstElement());        	
		       	this.ingFichaClinica.getBotonGuardar().setEnabled(true);
		       	this.ingFichaClinica.setListenerButtons(this);
		       	persistencia.concretarTransaccion();
		       	this.ingFichaClinica.show();
		       	
		    }else{
		   		JOptionPane.showMessageDialog(this.ingFichaClinica, "SELECCIONE ALGUNA FILA");
		   		this.gesFichaClinica.setVisible(true);
		    }
		    
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	public void actionPerformed(ActionEvent e){
		if(this.ingFichaClinica.getBotonGuardar() == e.getSource()){
  			this.aceptarModificarOpcion();
		}
		if(this.ingFichaClinica.getBotonCerrar() == e.getSource()){
			this.cancelarModificacionOpcion();
		}
		if(this.ingFichaClinica.getBotonEstudios() == e.getSource()){
			long idFicha = Integer.parseInt(this.ingFichaClinica.getCampoNumeroFicha().getText());
			this.ingFichaClinica.setVisible(false);
			MediadorGestionarEstudioComplementario gec = new MediadorGestionarEstudioComplementario(idFicha,ingFichaClinica);
			gec.mostrar();
		}
	}

	private void aceptarModificarOpcion(){
		try{
			persistencia.iniciarTransaccion();
	        ficha = controlFichaClinica.obtenerFichaClinica(""+idFicha);
	        Vector<String> anamnesis = new Vector<String>();
	        anamnesis.add(this.ingFichaClinica.getCampoAnamnesis().getText());
	        ficha.setAnamnesis(anamnesis);
	        ficha.setDiagnosticoDiferencial(this.ingFichaClinica.getCampoDiagnosticoDiferencial().getText().toLowerCase());
	        ficha.setDiagnosticoPresuntivo(this.ingFichaClinica.getCampoDiagnosticoPresuntivo().getText().toLowerCase());
	        ficha.setPronostico(this.ingFichaClinica.getCampoPronostico().getText().toLowerCase());
	        ficha.setManejoClinicoTerapeutico(this.ingFichaClinica.getCampoManejoClinicoTerapeutico().getText().toLowerCase());
	        
	        expGral = controlFichaClinica.obtenerExploracionGeneral(ficha.getId());
	        expGral.setEstadoGral(this.ingFichaClinica.getPanelExploracionGeneral().getCampoEstadoGeneral().getText().toLowerCase());
	        expGral.setFc(this.ingFichaClinica.getPanelExploracionGeneral().getCampoFrecuenciaCardiaca().getText().toLowerCase());
	        expGral.setFr(this.ingFichaClinica.getPanelExploracionGeneral().getCampoFrecuenciaRespiratoria().getText().toLowerCase());
	        expGral.setTemperatura(this.ingFichaClinica.getPanelExploracionGeneral().getCampoTemperatura().getText().toLowerCase());
	        expGral.setGangliosLinfaticos(this.ingFichaClinica.getPanelExploracionGeneral().getCampoGangliosLinfaticos().getText().toLowerCase());
	        expGral.setHidratacion(this.ingFichaClinica.getPanelExploracionGeneral().getCampoHidratacion().getText().toLowerCase());
	        expGral.setMucosaBucal(this.ingFichaClinica.getPanelExploracionGeneral().getCampoMucosaBucal().getText().toLowerCase());
	        expGral.setMucosaGenital(this.ingFichaClinica.getPanelExploracionGeneral().getCampoMucosaGenital().getText().toLowerCase());
	        expGral.setMucosaOcular(this.ingFichaClinica.getPanelExploracionGeneral().getCampoMucosaOcular().getText().toLowerCase());
	        expGral.setMucosaOtros(this.ingFichaClinica.getPanelExploracionGeneral().getCampoMucosaOtros().getText().toLowerCase());
	        	//*******Circulatorio ********
	       	circ = controlFichaClinica.obtenerCirculatorio(ficha.getId());
	       	circ.setAscultacion(this.ingFichaClinica.getPanelCirculatorio().getCampoAscultacion().getText().toLowerCase());
	       	circ.setEcg(this.ingFichaClinica.getPanelCirculatorio().getCampoEcg().getText().toLowerCase());
	       	circ.setPulso(this.ingFichaClinica.getPanelCirculatorio().getCampoPulso().getText().toLowerCase());
	       	circ.setRitmo(this.ingFichaClinica.getPanelCirculatorio().getCampoRitmo().getText().toLowerCase());
	       	circ.setSoplo(this.ingFichaClinica.getPanelCirculatorio().getCampoSoplo().getText().toLowerCase());
	       	circ.setOtros(this.ingFichaClinica.getPanelCirculatorio().getCampoOtros().getText().toLowerCase());
	        Vector<String> observacion = new Vector<String>();
	        observacion.add(this.ingFichaClinica.getPanelCirculatorio().getCampoObservacion().getText());
	       	circ.setObservaciones(observacion);
	       	
	       	dig = controlFichaClinica.obtenerDigestivo(ficha.getId());
	       	dig.setCavidadOral(this.ingFichaClinica.getPanelDigestivo().getCampoCavidadOral().getText().toLowerCase());
	       	dig.setDisfagia(this.ingFichaClinica.getPanelDigestivo().getCampoDisfagia().getText().toLowerCase());
	       	dig.setMateriaFecal(this.ingFichaClinica.getPanelDigestivo().getCampoMateriaFecal().getText().toLowerCase());
	       	dig.setPalpacionAbd(this.ingFichaClinica.getPanelDigestivo().getCampoPalpacionAbdominal().getText().toLowerCase());
	       	dig.setVomitos(this.ingFichaClinica.getPanelDigestivo().getCampoVomitos().getText().toLowerCase());
	       	observacion.remove(0);
	       	observacion.add(this.ingFichaClinica.getPanelDigestivo().getCampoObservacion().getText());
	       	dig.setObservaciones(observacion);
	       	
	       	res = controlFichaClinica.obtenerRespiratorio(ficha.getId());
	        res.setAuscultacion(this.ingFichaClinica.getPanelRespiratorio().getCampoAuscultacion().getText().toLowerCase());
	       	res.setReflejoTusigeno(this.ingFichaClinica.getPanelRespiratorio().getCampoReflejoTusigeno().getText().toLowerCase());
	       	res.setRepiracion(this.ingFichaClinica.getPanelRespiratorio().getCampoRespiracion().getText().toLowerCase());
	       	res.setTos(this.ingFichaClinica.getPanelRespiratorio().getCampoTos().getText().toLowerCase());
	       	observacion.remove(0);
	       	observacion.add(this.ingFichaClinica.getPanelRespiratorio().getCampoObservacion().getText());
	       	res.setObservaciones(observacion);
	       	
	        gen = controlFichaClinica.obtenerGenital(ficha.getId());
	        gen.setHembraExp(this.ingFichaClinica.getPanelGenital().getCampoHembraExp().getText().toLowerCase());
	        gen.setHembraGlanMamarias(this.ingFichaClinica.getPanelGenital().getCampoHembraGlandulaMamarias().getText().toLowerCase());
	        gen.setHembraSecreciones(this.ingFichaClinica.getPanelGenital().getCampoHembraSecreciones().getText().toLowerCase());
	        gen.setMachoExp(this.ingFichaClinica.getPanelGenital().getCampoMachoExp().getText().toLowerCase());
	        gen.setMachoSecreciones(this.ingFichaClinica.getPanelGenital().getCampoMachoSecreciones().getText().toLowerCase());
	        gen.setMachoTactoRectal(this.ingFichaClinica.getPanelGenital().getCampoMachoTactoRectal().getText().toLowerCase());
	        observacion.remove(0);
	        observacion.add(this.ingFichaClinica.getPanelGenital().getCampoObservacion().getText());
	        gen.setObservaciones(observacion);
	        
	        loc = controlFichaClinica.obtenerLocomotor(ficha.getId());
	        loc.setLesion(this.ingFichaClinica.getPanelLocomotor().getCampoLesion().getText().toLowerCase());
	        loc.setUbicacion(this.ingFichaClinica.getPanelLocomotor().getCampoUbicacion().getText().toLowerCase());
	        observacion.remove(0);
	        observacion.add(this.ingFichaClinica.getPanelLocomotor().getCampoObservacion().getText());
	        loc.setObservaciones(observacion);
	        
	        ner = controlFichaClinica.obtenerNervioso(ficha.getId());
	        ner.setAtaxia(this.ingFichaClinica.getPanelNervioso().getCampoAtaxia().getText().toLowerCase());
	        ner.setConducta(this.ingFichaClinica.getPanelNervioso().getCampoConducta().getText().toLowerCase());
	        ner.setConvulsiones(this.ingFichaClinica.getPanelNervioso().getCampoConvulsiones().getText().toLowerCase());
	        ner.setParalisis(this.ingFichaClinica.getPanelNervioso().getCampoParalisis().getText().toLowerCase());
	        ner.setReflejos(this.ingFichaClinica.getPanelNervioso().getCampoReflejos().getText().toLowerCase());
	        ner.setSensibilidad(this.ingFichaClinica.getPanelNervioso().getCampoSensibilidad().getText().toLowerCase());
	        ner.setSensorio(this.ingFichaClinica.getPanelNervioso().getCampoSensorio().getText().toLowerCase());
	        ner.setOtros(this.ingFichaClinica.getPanelNervioso().getCampoOtros().getText().toLowerCase());
	        observacion.remove(0);
	        observacion.add(this.ingFichaClinica.getPanelNervioso().getCampoObservacion().getText());
	        ner.setObservaciones(observacion);
	        
	        ojo = controlFichaClinica.obtenerOjos(ficha.getId());
	        ojo.setDerecho(this.ingFichaClinica.getPanelOjos().getCampoDerecho().getText().toLowerCase());
	        ojo.setIzquierdo(this.ingFichaClinica.getPanelOjos().getCampoIzquierdo().getText().toLowerCase());
	        observacion.remove(0);
	        observacion.add(this.ingFichaClinica.getPanelOjos().getCampoObservacion().getText());
	        ojo.setObservaciones(observacion);
	        
	        piel = controlFichaClinica.obtenerPiel(ficha.getId());
	        piel.setEctoparasitos(this.ingFichaClinica.getPanelPiel().getCampoEctoparasitos().getText().toLowerCase());
	        piel.setForma(this.ingFichaClinica.getPanelPiel().getCampoForma().getText().toLowerCase());
	        piel.setMantoPiloso(this.ingFichaClinica.getPanelPiel().getCampoMantoPiloso().getText().toLowerCase());
	        piel.setOidoDer(this.ingFichaClinica.getPanelPiel().getCampoOidoDerecho().getText().toLowerCase());
	        piel.setOidoIzq(this.ingFichaClinica.getPanelPiel().getCampoOidoIzquierdo().getText().toLowerCase());
	        piel.setOlor(this.ingFichaClinica.getPanelPiel().getCampoOlor().getText().toLowerCase());
	        piel.setPrurito(this.ingFichaClinica.getPanelPiel().getCampoPrurito().getText().toLowerCase());
	        piel.setSimetrica(this.ingFichaClinica.getPanelPiel().getCampoSimetrica().getText().toLowerCase());
	        piel.setTipoLesion(this.ingFichaClinica.getPanelPiel().getCampoTipoLesion().getText().toLowerCase());
	        piel.setUbicacion(this.ingFichaClinica.getPanelPiel().getCampoUbicacion().getText().toLowerCase());
	        observacion.remove(0);
	        observacion.add(this.ingFichaClinica.getPanelPiel().getCampoObservacion().getText());
	        piel.setObservaciones(observacion);
	        
	        uri = controlFichaClinica.obtenerUrinario(ficha.getId());
	        uri.setOrina(this.ingFichaClinica.getPanelUrinario().getCampoOrina().getText().toLowerCase());
	        uri.setVejiga(this.ingFichaClinica.getPanelUrinario().getCampoVejiga().getText().toLowerCase());
	        uri.setOtros(this.ingFichaClinica.getPanelUrinario().getCampoOtros().getText().toLowerCase());
	        observacion.remove(0);
	        observacion.add(this.ingFichaClinica.getPanelUrinario().getCampoObservacion().getText());
	        uri.setObservaciones(observacion);		
	        persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	public void cancelarModificacionOpcion(){
        this.ingFichaClinica.setVisible(false);
		this.ingFichaClinica.dispose();
	}
}
