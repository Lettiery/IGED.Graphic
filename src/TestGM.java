
import iged.manager.AcaoUsuario;
import iged.manager.GraphicManager;
import iged.manager.PaintUI;
import iged.manager.Quadro;
import iged.manager.VarInteiroManager;
import iged.struct.Lista;
import iged.struct.Node;
import iged.struct.Struct;
import iged.struct.VarInteiro;


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

       
       gm.createReference("n2", Struct.NODE);

       
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
    	
    	gm.readReference("n2");
    	gm.writeInfo("1");
    	
    	gm.readReference("n");
    	gm.writeInfo("7");
    	
    	
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
    	
    	gm.readReference("n2");
    	gm.createStruct(Struct.NODE);
    	gm.writeReference();
    	
    	gm.readReference("n2");
    	gm.writeInfo("4");
    	
    	gm.readReference("n");
    	gm.writeInfo("9");
    	
    	
    	gm.creat_Int("l");
    	gm.readReferenceInt("l");
    	gm.setValue("2");

    	gm.creat_Int("cont");
    	gm.readReferenceInt("cont");
    	gm.setValue("7");

    	gm.creat_Int("i");
    	gm.readReferenceInt("i");
    	gm.setValue("0");
    	
    	gm.lixeiro();
    }
    public void Passo4(){
    	gm.readReference("n");
    	gm.readReference("n2");

    	gm.writeReferenceField(Node.PROX);

    	
    	
    	
    	gm.lixeiro();
    }
    public void Passo5(){
    	gm.readReference("l");
    	gm.readReferenceField(Lista.INIT);
    	gm.readReferenceField(Node.PROX);
    	gm.readReference("n");
    	gm.writeReferenceField(Node.PROX);
    	
    	gm.readReference("n2");
    	gm.readReference("l");
    	gm.readReferenceField(Lista.INIT);
    	gm.readReferenceField(Node.PROX);
    	gm.writeReference();

    	
    	gm.readReference("n");
    	gm.readReference("l");
    	gm.readReferenceField(Lista.INIT);
    	gm.writeReference();
    	gm.readReference("l");
    	gm.writeInfo("4");
    	
    	gm.lixeiro();
    	
    }
    public void Passo6(){
    	
    	gm.readReference("l");
    	gm.readReference("n2");
    	gm.readReferenceField(Node.PROX);
    	gm.readReferenceField(Node.PROX);
    	gm.writeReferenceField(Lista.INIT);
    	
    	gm.readReference("n2");
    	gm.readReferenceField(Node.PROX);
    	gm.readReference("n");
    	gm.writeReferenceField(Node.PROX);
    	
    	gm.readReference("l");
    	gm.writeInfo("1");
    	
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
