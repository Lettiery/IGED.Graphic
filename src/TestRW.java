


import iged.manager.AcaoUsuario;
import iged.manager.NodesManager;
import iged.manager.PaintUI;
import iged.manager.Quadro;
import iged.manager.StructManager;
import iged.manager.VarInteiroManager;
import iged.struct.Struct;
public class TestRW implements AcaoUsuario{

	private Quadro quadro;
    
	private  NodesManager  nm = NodesManager.getIntance();
	private StructManager sm = StructManager.getIntance();
    int count = 0;
        
    

	public TestRW(){
		this.quadro = Quadro.getInstance();
		
                initEditor();
	}
        
        private void initEditor(){
        	sm.createStruct(Struct.LISTA);
        	sm.createReferenciaStruct("l", Struct.LISTA);
        	sm.setReferenciaStruct("l");
        	
        	nm.createNode();
        	nm.createReferencia("n2");
        	nm.setReferencia("n2");
        	
        	sm.readRef("l");
        	nm.readRef("n2");
        	sm.writeStructRef(StructManager.INIT);
        	sm.repintar();
            
        }
        
        private void createRef(){
        	//No n = new No();
        	nm.createNode();
        	nm.createReferencia("n");
        	nm.setReferencia("n");
        	
        	sm.repintar();
        }
        
        private void createNode(){
        	//n.info = 0;
        	nm.readRef("n");
        	nm.setValue("0");
        	
        	//n.prox = l.inicio;
        	nm.readRef("n");
        	sm.readRef("l");
        	sm.readStructField(StructManager.INIT);
        	nm.writeRef(NodesManager.NODE_PROXIMO);

        	sm.repintar();
        }
        
        private void setValue(){
        	//l.inicio = n;
        	sm.readRef("l");
        	nm.readRef("n");
        	sm.writeStructRef(StructManager.INIT);
        	
        	//l.tamanho ++;
        	tamanho++;
        	sm.readRef("l");
        	sm.setValue(String.valueOf(tamanho));
        	
        	sm.repintar();
        }
	
        private void setNodeNext(){
        	nm.removerReferencia("n2");
        	nm.removerReferencia("n");
        	sm.repintar();
        }
        
        private void removerReferencias(){
        	nm.createReferencia("aux");//Node aux;
        	
        	//aux = l.init;
        	sm.readRef("l");
        	sm.readStructField(StructManager.INIT);
        	nm.writeRef("aux");
        	sm.repintar();
        	
        }
        
        int tamanho = 1;
        private void incrSize(){
        	
        	for(int i = 1; i<=tamanho ; i++){
        		//aux.info = i;
        		nm.readRef("aux");
        		nm.setValue(String.valueOf(i));
        		
        		if(i!=tamanho ){
        			//aux = aux.prox;
        			nm.readRef("aux");
            		nm.readRefField(nm.NODE_PROXIMO);
            		nm.writeRef("aux");
        		}
        	
        	}
        	
    		sm.repintar();
        	
        }
        
        public void fim_metodo(){
        	nm.removerReferencia("aux");
        	count = -1;
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
                    case 6:
                    	fim_metodo();
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
		new TestRW().init();
	}

	@Override
	public void zoom() {
		// TODO Auto-generated method stub
		
	}

}
