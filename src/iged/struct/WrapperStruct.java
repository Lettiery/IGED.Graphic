package iged.struct;

import iged.geometria.Label;
import iged.manager.Quadro;

import java.awt.geom.Point2D;

public class WrapperStruct {

	private Struct s;
	private Referencia ref;
	private String referencia;
	private int type = -1;
	
	public WrapperStruct(Struct s, int type){
		this.s = s;
		this.type = type;
		//Quadro.getInstance();
		// add no WrapperStruct no quadro
		
	}
	
	public WrapperStruct(String reference ,Struct s, int type){
		this.s = s;
		this.type = type;
		this.setReferencia(reference);
	}
	
	public void setReferenciaVazia(String reference ,Point2D pn){
		//lembrar de repintar quando o esta referencia deixar de ser vazia

		switch (type) {
		/*case Struct.LISTA:
			this.ref = new Referencia( pn,reference);
			Quadro.getInstance().add(ref);
			break;
			*/
		case Struct.NODE:
			this.ref  = new Referencia( pn,reference);
			Quadro.getInstance().add(ref);
			break;
			
		default:
			break;
		}
		this.referencia = reference;
	}
	
	public void removerReferencia(){
		
		if(ref != null || ref.getNode()!= null ){
			switch (type) {
			/*case Struct.LISTA:
				Node l = ((Lista)s).getInit();
				l.remove(ref);
				break;
				*/
			case Struct.NODE:
				Quadro.getInstance().remove(this.ref);
				Node n = ((Node)s);
				n.remove(ref);
				break;
				
			default:
				break;
			}
		}
	}
	
	public void setReferencia(String reference){
		
		switch (type) {
		
		/*case Struct.LISTA:
			Node l = ((Lista)s).getInit();
			ref = new Referencia( l ,reference);
			break;
			*/
		case Struct.NODE:
			Node n = ((Node)s);
			ref = new Referencia( n ,reference);
			Quadro.getInstance().add(ref);
			break;
			
		}
		
		
		this.referencia = reference;
	}
	
	public int getType() {
		return type;
	}

	public Struct getStruct() {
		return s;
	}

	public void setStruct(Struct s) {
		// deve atualizar o ref , vazer ele apontar para a nova struct e remover da antiga
		//acho que ta fazendo isso
		switch (type) {
		case Struct.LISTA:
			((Lista)s).setReferencia(this.referencia);
			break;

		case Struct.NODE:
			if(this.s != null){
				Node n = ((Node)this.s);
				n.remove(ref);
			}
			ref = new Referencia(  ((Node)s) ,this.referencia);
			Quadro.getInstance().add(ref);
			break;
			
		default:
			break;
		}

		this.s = s;
	}
	
	public void startRepaint(){
		if(s != null){
			s.startRepaint();
		}
	}
	
	public String getReferenci(){
		return this.referencia;
	}
	
	public void repintar(){
			if(ref !=null && this.type != Struct.LISTA ){
				Quadro.getInstance().add(ref);
			}
			if(s != null){
				this.s.repintar();
			}
	}
	
	public String toString(){
				return this.referencia;//+" | "+ s.toString();
	}
}
