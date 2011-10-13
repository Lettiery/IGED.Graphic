


import iged.manager.AcaoUsuario;
import iged.manager.NodesManager;
import iged.manager.PaintUI;
import iged.manager.Quadro;
import iged.manager.StructManager;
import iged.manager.VarInteiroManager;
import iged.struct.Struct;
public class Test implements AcaoUsuario{

	private Quadro quadro;
    
	private  NodesManager  nm = NodesManager.getIntance();
	private VarInteiroManager im = VarInteiroManager.getInstance();
	private StructManager sm = StructManager.getIntance();
        int count = 0;
        
    

	public Test(){
		this.quadro = Quadro.getInstance();
		
                
                initEditor();
	}
        
        private void initEditor(){
            
            im.createVarInteiro("i");
            
        }
        
        private void createRef(){
        	im.createVarInteiro("m");
        	im.createVarInteiro("h");
        	im.createVarInteiro("j");
        	im.createVarInteiro("l");
        	
        	sm.createStruct(Struct.LISTA);
        	sm.createReferenciaStruct("l", Struct.LISTA);
        	sm.setReferenciaStruct("l");
        	
        	sm.createStruct(Struct.LISTA);
        	sm.createReferenciaStruct("list", Struct.LISTA);
        	sm.setReferenciaStruct("list");

        	
        }
        
        private void createNode(){
        	im.remover("i");
        	im.setValor("h", "3");
        	im.setValor("m", "6");
        	nm.createNode();
        	nm.createReferencia("n1");
        	nm.setReferencia("n1");
        	
        	nm.createNode();
        	nm.createReferencia("n2");
        	nm.setReferencia("n2");
        	
        	nm.createNode();
        	nm.createReferencia("n3");
        	nm.setReferencia("n3");
        	
        	
        }
        
        private void setValue(){
        	
        	im.remover("j");
        	im.ler("m");
        	//nm.setValue("n1", "2");
        	//nm.setValue("n2", "13");
        	//nm.setValue("n3", "0");

            
        }
	
        private void setNodeNext(){
        	
        	nm.readRef("n1");
        	nm.readRef("n2");
        	nm.writeRef(NodesManager.NODE_PROXIMO);
        	//nm.setNext("n1", "n2");
        	nm.readRef("n2");
        	nm.readRef("n3");
        	nm.writeRef(NodesManager.NODE_PROXIMO);
        	//nm.setNext("n2", "n3");
        	
        	nm.setNextNull("n3");
        	
        	nm.createNode();
        	nm.createReferencia("no2");
        	nm.setReferencia("no2");
        	
        	nm.createNode();
        	nm.createReferencia("no3");
        	nm.setReferencia("no3");
        }
        
        private void removerReferencias(){
        	nm.removerReferencia("n2");
        	nm.removerReferencia("n3");
        	
        	sm.setInitNode("l", "n1");
        	sm.setSize("l", "3");
        	nm.removerReferencia("n1");
        	
        	
        	nm.readRef("no2");
        	nm.readRef("no3");
        	nm.writeRef(NodesManager.NODE_PROXIMO);
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
		new Test().init();
	}

	@Override
	public void zoom() {
		// TODO Auto-generated method stub
		
	}

}
