


import iged.geometria.Label;
import iged.geometria.Retangulo;
import iged.manager.AcaoUsuario;
import iged.manager.NodesManager;
import iged.manager.PaintUI;
import iged.manager.Quadro;
import iged.manager.StructManager;
import iged.manager.VarInteiroManager;
import iged.struct.Node;
import iged.struct.Null;
import iged.struct.Referencia;
import iged.struct.Struct;
import iged.struct.VarInteiro;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Teste2 implements AcaoUsuario{

	private Quadro quadro;
    
	private  NodesManager  nm = NodesManager.getIntance();
	private VarInteiroManager im = VarInteiroManager.getInstance();
	private StructManager sm = StructManager.getIntance();
        int count = 0;
        
    

	public Teste2(){
		this.quadro = Quadro.getInstance();
		
                
                initEditor();
	}
        
        private void initEditor(){
            
            im.createVarInteiro("i");
            
        }
        
        private void createRef(){
        	im.createVarInteiro("m");
        	im.createVarInteiro("h");
        
        	
        	sm.createStruct(Struct.LISTA);
        	sm.createReferenciaStruct("l", Struct.LISTA);
        	sm.setReferenciaStruct("l");
        	

        	
        }
        
        private void createNode(){
        	im.remover("i");
        	im.setValor("h", "3");
        	im.setValor("m", "6");
        	
        	nm.createNode();
        	nm.createReferencia("no1");
        	nm.setReferencia("no1");
        	
        	nm.createNode();
        	nm.createReferencia("no2");
        	nm.setReferencia("no2");
        	
        	nm.createNode();
        	nm.createReferencia("no3");
        	nm.setReferencia("no3");
        	
        	
        }
        
        private void setValue(){
        	
        	im.ler("m");

            
        }
	
        private void setNodeNext(){
        	nm.readRef("no1");
        	nm.readRef("no2");
        	nm.writeRef(NodesManager.NODE_PROXIMO);
        	   
        	
        	nm.readRef("no1");
        	nm.readRefField(NodesManager.NODE_PROXIMO);
        	nm.readRef("no3");
        	nm.writeRef(NodesManager.NODE_PROXIMO);
        	
        }
        
        private void removerReferencias(){
        	nm.removerReferencia("no1");
        	nm.removerReferencia("no2");
        	nm.removerReferencia("no3");
        	
        	
        	
        	
        }
        
        private void incrSize(){
        		sm.repintar();
        		
        }
        
        public void color(){
		switch(count){
                    case 0:
                        createRef();
                        break;
                    case 1:
                        createNode();
                        break;
                    case 2:
                        setValue();
                        break;
                    case 3:
                        setNodeNext();
                        break;
                    case 4:
                    	removerReferencias();
                        break;
                    case 5:
                        incrSize();
                        break;
                }
                ++count;
	}

	public void move(){
		/*v.mover(new Point2D.Double(20, 70));*/
	}


	public Quadro getQuadro(){
		return quadro;
	}

	public void init(){
		new PaintUI("IGED V0.1", this);
	}
	
	
	
	public static void main(String[] args) {
		new Teste2().init();
	}

	@Override
	public void zoom() {
		// TODO Auto-generated method stub
		
	}

}
