
import iged.manager.AcaoUsuario;
import iged.manager.GraphicManager;
import iged.manager.PaintUI;
import iged.manager.Quadro;
import iged.struct.Lista;
import iged.struct.Node;
import iged.struct.Struct;


public class TestGM implements AcaoUsuario {


	private Quadro quadro;
    private GraphicManager gm = new GraphicManager();
	
    int count = 0;
	
	
    public TestGM(){
		this.quadro = Quadro.getInstance();
		initEditor();
	}
        
    private void initEditor(){
    	gm.createReference("n", Struct.NODE);
       gm.createStruct(Struct.NODE);
       gm.writeReference();
       gm.lixeiro();
       
       gm.createReference("n2", Struct.NODE);
       gm.lixeiro();
       
       gm.createReference("l", Struct.LISTA);
       gm.createStruct(Struct.LISTA);
       gm.writeReference();
       
       gm.lixeiro();
    }
    
    public void Passo0(){
    	gm.readReference("l");
    	gm.readReference("n");
    	gm.writeReferenceField(Lista.INIT);
    	
    	gm.lixeiro();
    }
    public void Passo1(){
    	gm.readReference("n2");
    	gm.createStruct(Struct.NODE);
    	gm.writeReference();
    	
    	gm.lixeiro();
    }
    public void Passo2(){
    	gm.readReference("l");
    	gm.readReferenceField(Lista.INIT);
    	gm.readReference("n2");
    	gm.writeReferenceField(Node.PROX);
    	
    	gm.lixeiro();
    		
    }
    public void Passo3(){
    	gm.readReference("n");
    	gm.createStruct(Struct.NODE);
    	gm.writeReference();
    	
    	gm.lixeiro();
    }
    public void Passo4(){
    	
    	gm.readReference("n2");
    	gm.readReference("n");
    	gm.writeReferenceField(Node.PROX);

    	gm.lixeiro();
    }
    public void Passo5(){
    	gm.readReference("n");
    	gm.createStruct(Struct.NODE);
    	gm.writeReference();
    	
    	gm.lixeiro();
    	
    	
    }
    public void Passo6(){
    	
    	// Olhar que ta feio
    	gm.createReference("lista", Struct.LISTA);
    	gm.createStruct(Struct.LISTA);
    	gm.writeReference();
    	
    	gm.readReference("lista");
    	gm.readReference("n2");
    	gm.writeReferenceField(Lista.INIT);
    	
    	
    	gm.lixeiro();
    	
    }
    
    
	@Override
	 public void color(){
		switch(count){
                    case 0:
                    	Passo0();
                        break;
                    case 1:
                    	Passo1();
                        break;
                    case 2:
                    	Passo2();
                        break;
                    case 3:
                    	Passo3();
                        break;
                    case 4:
                    	Passo4();
                        break;
                    case 5:
                    	Passo5();
                        break;
                    case 6:
                    	Passo6();
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

	@Override
	public void zoom() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	public static void main(String[] args) {
		new TestGM().init();
	}
	
	
	
	
}
