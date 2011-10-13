
import iged.geometria.Label;
import iged.geometria.Retangulo;
import iged.struct.*;

import java.awt.geom.Point2D;
import iged.manager.*;
import java.awt.Font;

public class Paint implements AcaoUsuario{
	private Quadro quadro;

	//private Vetor v = null;
	private Node n = null;
	private Node n1 = null;
	private Node n2 = null;

	private Referencia r = null;
	private Referencia r1 = null;
	private Referencia r2 = null;

	private VarInteiro vi = null;
        
        int count = 0;
        
        Label ref = null;
        Label node = null;

	public Paint(){
		this.quadro = Quadro.getInstance();
		//v = new Vetor("V", 10, new Point2D.Double(20, 20));

		n = new Node(new Point2D.Double(120, 170));
                n.setValue("10");
		n1 = new Node(new Point2D.Double(290, 170));
		n1.setValue("20");
                
		n.setNext(n1);
                n1.setNextNull();
                
		r1 = new Referencia(n, "l.inicio");

		vi = new VarInteiro(new Point2D.Double(20, 25), "l.size");
                vi.setValor("2");
                

		//this.quadro.add(v);

		this.quadro.add(n);
		this.quadro.add(n1);
		
		
		this.quadro.add(r1);
		
                this.quadro.add(vi);
                
                initEditor();
	}
        
        private void initEditor(){
            Retangulo r = new Retangulo(new Point2D.Double(20, 450), 640, 160);
            this.quadro.add(r);
            
            this.ref = new Label("No n1 = l.inicio.next;", new Point2D.Double(40, 480));
            this.quadro.add(this.ref);
            
        }
        
        private void createRef(){
            r = new Referencia(n1, "n1");
            this.quadro.add(r);
            this.quadro.atualizar();
            
            new Thread(){
                public void run(){
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){};
                    node = new Label("No n2 = new No();", new Point2D.Double(40, 505));
                    quadro.add(node);
                    quadro.atualizar();
                }
            }.start();
        }
        
        private void createNode(){
            n2 = new Node(new Point2D.Double(200, 350));
            r2 = new Referencia(n2, "n2");
            
            this.quadro.add(n2);
            this.quadro.add(r2);
            this.quadro.atualizar();
            
            new Thread(){
                public void run(){
                    try{
                        Thread.sleep(10000);
                    }catch(Exception e){};
                    node = new Label("n2.info = 15;", new Point2D.Double(40, 530));
                    quadro.add(node);
                    quadro.atualizar();
                }
            }.start();
        }
        
        private void setValue(){
            n2.setValue("15");
            this.quadro.atualizar();
            new Thread(){
                public void run(){
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){};
                    node = new Label("n2.next = n1;", new Point2D.Double(40, 555));
                    quadro.add(node);
                    quadro.atualizar();
                }
            }.start();
        }
	
        private void setNodeNext(){
            n2.setNext(n1);
            
            new Thread(){
                public void run(){
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){};
                    node = new Label("l.inicio.next = n2;", new Point2D.Double(40, 580));
                    quadro.add(node);
                    quadro.atualizar();
                }
            }.start();
        }
        
        private void setInicioNext(){
            n.setNext(n2);
            
            new Thread(){
                public void run(){
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){};
                    node = new Label("++l.size;", new Point2D.Double(40, 605));
                    quadro.add(node);
                    quadro.atualizar();
                }
            }.start();
        }
        
        private void incrSize(){
            vi.setValor("3");
            n.adjust(new Point2D.Double(120, 170));
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
                        setInicioNext();
                        break;
                    case 5:
                        incrSize();
                        break;
                }
                ++count;
	}

	public void move(){


		/*v.mover(new Point2D.Double(20, 70));*/

		n.adjust(new Point2D.Double(100, 200));
	}

	public void zoom(){
		r.setRef(null);
		this.quadro.remove(r);
		this.quadro.remove(n1);
		this.quadro.atualizar();

	}

	public Quadro getQuadro(){
		return quadro;
	}

	public void init(){
		new PaintUI("IGED V0.1", this);
	}

	public static void main(String[] args) {
		new Paint().init();
  	}
}