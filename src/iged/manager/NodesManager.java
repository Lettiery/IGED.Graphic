
package iged.manager;

import iged.struct.Node;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class NodesManager {
    
	private Quadro quadro = Quadro.getInstance();
	
	private Map<String, Node> nos = new HashMap<String , Node>();
	private Map<String, Node> structs = new HashMap<String , Node>();
	private List<Node> soltos = new ArrayList<Node>(); 
	Stack<Node> pilha = new Stack<Node>();
	 
	private Node reg = null;
	private Apontadores ap = new Apontadores();
	private int yBaseTrabalho = 170;
	private int espacoEntreNodes = 150;
	private static NodesManager nm = null;
	
	private NodesManager(){}
	
	public static NodesManager getIntance(){
		if(nm == null){
			nm = new NodesManager();
		}
		return nm;
	}
	

	
    public void createNode(){
    	this.reg = new Node( new Point2D.Double(getXNodeSoltos(),yBaseTrabalho));
    	soltos.add(this.reg);
    	quadro.add(this.reg);
    	quadro.atualizar();
    	//cria novo no e guarda no registrador
    }
    
    public void createReferencia(String referencia ){				
    	ap.addReferencia(referencia, new Point2D.Double(getXReferenciaSolta(),yBaseTrabalho+60));
    	
    	//cria referencia vazia
    }
    
    public void removerReferencia(String referencia){
    	this.ap.removeReferencia(referencia);
    	Node n = nos.remove(referencia);
    	
    	this.soltos.remove(n);
    }
    
    public void setReferencia(String referencia){

    	nos.put(referencia, this.reg);
    	ap.atribuirReferencia(referencia, this.reg);
    	//atribui "String referencia" ao "Node recem criado"
    }
    
    
    
    public void readRef(String referencia){
    	pilha.push(nos.get(referencia));
    }
    
    public void readRefField(int field){
    	Node n = pilha.pop();
    	switch(field){
		case NodesManager.NODE_PROXIMO:
			pilha.push(n.getProx());
			break;
		
    	}
    }
    
    public void writeRef(){
    	//aqui
    	Node n2 = pilha.pop();
    	Node n1 = pilha.pop();
    	n1 = n2;
    }
    
    public void writeRef(int field){
    	Node n1 = pilha.pop();
    	switch(field){
    		case NodesManager.NODE_PROXIMO:
    			pilha.pop().setNext(n1);
    			break;
    		
    	}
    	
    }
    
    public void writeRef(String referencia){
    	//atribie referencia a o n� no topo da pilha . exp: No n = l.inicio; ou  No n = n2.pox;
    	Node n = pilha.pop();
    	ap.atribuirReferencia(referencia, n);
    	this.nos.put(referencia, n);
    }
    
    public void setNextNull(String referencia){
    	nos.get(referencia).setNextNull();
    	ap.setNextNull(nos.get(referencia));
    	quadro.atualizar();
    }
    
    public void setValue(String value){
    	pilha.pop().setValue(value);
    	quadro.atualizar();
    }
    
    public int getXNodeSoltos(){
    	int x = 0;
    	if(soltos.size() == 0){
    		x = 100;
    	}else{
    		x = (soltos.size()*espacoEntreNodes)+100;
    	}
    	return x;
    }
    
    public int getXReferenciaSolta(){
    	int x = 0;
    	if(ap.sizeReferenciasSoltas() == 0){
    		x = 30;
    	}else{
    		x = ((ap.sizeReferenciasSoltas()+1)*50)+30;
    	}
    	return x;
    }
    
    public int getXReferenciaSolta(int index){
    	int x = 0;
    	if(index == 0){
    		x = 100;
    	}else{
    		x = (index*espacoEntreNodes)+100;
    	}
    	return x;
    }
    
    
    public void adjust(String referencia){
    	this.nos.get(referencia).adjust(this.nos.get(referencia).getPointPB());
    	
    }
    
    Node getNode(String referencia){
    	return this.nos.get(referencia);
    }
    
    void setNodeInit(String struct ,String referenciaNode, Point2D pInit){
    	this.structs.put(struct, nos.get(referenciaNode));
    	nos.get(referenciaNode).adjust(pInit);
    	Node n  = nos.get(referenciaNode);
    	while(n != null){
    		if( soltos.contains(n) ){
    			soltos.remove(n);
    		}
    		n = n.getProx();
    	}
    	
    	for(Node n2 : soltos){
    		n2.mover(new Point2D.Double( getXReferenciaSolta(soltos.indexOf(n2)) ,yBaseTrabalho) );
    	}
    }
    
    void repintar(){
    	//preparar estruturas para redesenhar
    	
    	for(Node n : this.soltos){
    			n.repintado = true;
    	}
    	for(Node n : this.nos.values()){
    			n.repintado = true;
    	}
    	
    	//redesenhando
    	for(Node n : this.soltos){
    		if(n.repintado){
    			this.quadro.add(n);
    			n.repintado = false;
    		}
    	}
    	for(Node n : this.nos.values()){
    		if(n.repintado){
    			this.quadro.add(n);
    			n.repintado = false;
    		}
    	}
    	this.ap.repintar();
    }
    
    public static final int WORK_SPACE = 170; 
    public static final int NODE_PROXIMO = 1;
    
}
