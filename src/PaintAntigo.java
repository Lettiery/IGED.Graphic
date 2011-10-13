import java.awt.Color;

import iged.geometria.*;
import iged.struct.*;

import java.awt.geom.Point2D;
import java.awt.Point;
import iged.manager.*;

public class PaintAntigo implements AcaoUsuario{
	private Quadro quadro;

	private Vetor v = null;
	private Node n = null;
	private Node n1 = null;
	private Node n2 = null;

	private Referencia r = null;
	private Referencia r1 = null;
	private Referencia r2 = null;

	private VarInteiro vi = null;

	public PaintAntigo(){
		this.quadro = Quadro.getInstance();
		v = new Vetor("V", 10, new Point2D.Double(20, 20));

		n = new Node(new Point2D.Double(100, 150));
		n1 = new Node(new Point2D.Double(300, 150));
		n2 = new Node(new Point2D.Double(450, 350));

		r = new Referencia(n, "r");
		r1 = new Referencia(n, "r1");

		r2 = new Referencia(n, "r2");

		vi = new VarInteiro(new Point2D.Double(550, 150), "miim");

		this.quadro.add(v);
		this.quadro.add(n);
		this.quadro.add(n1);
		this.quadro.add(n2);
		this.quadro.add(r);
		this.quadro.add(r1);
		this.quadro.add(r2);
		this.quadro.add(vi);
	}

	public void color(){
		//n2.setNext(n1);

		v.ler(3);

		for(int i=0; i<10; ++i)
			v.escrever(i, "1");

		n.setValue("4");
		n1.setValue("3");

		n.setNext(n1);
		n1.setNext(n2);

		r1.setRef(n1);

		vi.setValor("10");

		n2.setNextNull();
	}

	public void move(){


		v.mover(new Point2D.Double(20, 70));

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
		new PaintUI("PaintOO V1.0", this);
	}

	public static void main(String[] args) {
		(new Paint()).init();
  	}
}