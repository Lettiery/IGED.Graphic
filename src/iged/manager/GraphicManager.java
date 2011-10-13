package iged.manager;

import iged.geometria.Label;
import iged.struct.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class GraphicManager {

	private Map<String, WrapperStruct> structs = new HashMap<String, WrapperStruct>();
	public Stack<WrapperStruct> pilha = new Stack<WrapperStruct>();
	

	private int referenciasVazias = 0;
	private int nodesSoltos = 0;
	private int contStructs = 0;
	private int yBaseTrabalho = 170;
	private int espacoEntreNodes = 150;

	private int yBase = 200;

	public void createStruct(int type) {
		switch (type) {
		case Struct.LISTA:
			Lista l = new Lista();
			l.setyBase(yBase);
			
			int y = yBase;
			for (WrapperStruct w : structs.values()) {
				if (w.getType() != Struct.NODE && w.getType() != Struct.VAZIA) {
					if(w.getStruct() != null){
						y += w.getStruct().getBond() + 35;
					}
				}
			}
			l.setyBase(y);
			
			pilha.push(new WrapperStruct(l, Struct.LISTA));
			break;
		case Struct.NODE:
			Node n = new Node(new Point2D.Double(getXNodeSoltos(), yBaseTrabalho));
			// new Node(new Point2D.Double(getXNodeSoltos(), yBaseTrabalho));
			pilha.push(new WrapperStruct(n, Struct.NODE));
			Quadro.getInstance().add(n);
			break;
		case Struct.VETOR:
			//criar contrutor novo contrutor de vetor assim como o de list;
			break;

		default:
			break;
		}
	}

	public void createReference(String reference, int type) {
		//feito acho
		switch (type) {
		case Struct.LISTA:
			/*if (pilha.isEmpty()) {
				Lista l = ((Lista) reg.getStruct());

				int y = yBase;
				for (WrapperStruct w : structs.values()) {
					if (w.getType() != Struct.NODE && w.getType() != Struct.VAZIA) {
						y += w.getStruct().getBond() + 35;
					}
				}
				l.setReferencia(reference);
				l.desenhar(y);
				structs.put(reference, reg);
				contStructs++;
				reg = null;

			}else {
			*
			*///cria referencia vazia
				WrapperStruct w = new WrapperStruct(null, Struct.LISTA);
				w.setReferenciaVazia(reference, new Point2D.Double(getXReferenciaSolta(), yBaseTrabalho + 60));
				pilha.push(w);
				this.structs.put(reference, w);
				referenciasVazias++;
			
			break;

		case Struct.NODE:
			/*if (pilha.isEmpty()) {
				WrapperStruct w1 = pilha.pop();
				w1.setReferencia(reference);
				structs.put(reference, w1);
				nodesSoltos++;
			} else {
				*///cria referencia vazia
				
				WrapperStruct w2 = new WrapperStruct(null, Struct.NODE);
				w2.setReferenciaVazia(reference, new Point2D.Double(getXReferenciaSolta(), yBaseTrabalho + 60));
				this.structs.put(reference, w2);
				pilha.push(w2);
				referenciasVazias++;
				
			
			break;

		default:
			break;
		}
	}

	public void readReference(String reference) {
		pilha.push(structs.get(reference));
		//empilha o WrapperStruct que reference aponta no map structs
	}

	public void writeReference() {
		
		WrapperStruct w2 = pilha.pop();
		WrapperStruct w1 = pilha.pop();
		w1.setStruct(w2.getStruct());

	}

	public void readReferenceField(int field) {
		WrapperStruct w = new WrapperStruct(pilha.pop().getStruct().readField(field), Struct.VAZIA);
		pilha.push(w);
		//retira o primeiro WrapperStruct da pilha e empilha o campo field como WrapperStruct apenas com esse struct
	}
	
	//*************olhar nos testes
	public void writeReferenceField(int field) {
		WrapperStruct w2 = pilha.pop();
		WrapperStruct w1 = pilha.pop();
		w1.getStruct().writeField(w2.getStruct(), field);
		// retira o WrapperStruct da pilha e set ele no field do segundo no da pilha
	}

	public String readInfo() {
		return pilha.pop().getStruct().readInfo();
		//apenas ler o info
	}

	public void writeInfo(String value) {
		pilha.pop().getStruct().writeInfo(value);
		//apenas escreve um lavor
	}

	public void removeReference(String reference) {
		WrapperStruct w = structs.remove(reference);
		w.removerReferencia();
	}

	private int getXNodeSoltos() {
		int x = 0;
		if (nodesSoltos == 0) {
			x = 100;
		} else {
			x = (nodesSoltos * espacoEntreNodes) + 100;
		}
		return x;
	}

	private int getXReferenciaSolta() {
		int x = 0;
		if (referenciasVazias == 0) {
			x = 30;
		} else {
			x = ((referenciasVazias + 1) * 50) + 30;
		}
		return x;
	}

	private int getXReferenciaSolta(int index) {
		int x = 0;
		if (index == 0) {
			x = 100;
		} else {
			x = (index * espacoEntreNodes) + 100;
		}
		return x;
	}

	public void crearStack(){
		pilha.clear();
	}
	
	public void lixeiro(){
		Quadro.getInstance().limpar();
		for(WrapperStruct w: this.structs.values()){
			w.startRepaint();
		}
		
		List<WrapperStruct> nodes = new ArrayList<WrapperStruct>();
		
		for(WrapperStruct w: this.structs.values()){
			if(w.getType() != Struct.NODE){
				w.repintar();
			}else{
				nodes.add(w);
			}
		}
		
		this.nodesSoltos = 0;
		
		for(WrapperStruct no : nodes){
				no.repintar();
		}
		
		Quadro.getInstance().atualizar();
		crearStack();
		//vi.repintar();
	}
	
}
