package iged.struct;

import java.awt.geom.Point2D;

import iged.geometria.Label;
import iged.manager.Quadro;
import iged.manager.StructManager;

public class Lista extends Elemento implements Struct {

	private int bond = 100;
	private boolean repintado = false;
	private String referencia;
	private String tamanho = "0";

	private Label l;
	private Referencia ref;
	private VarInteiro tam = null;

	private Node ini = null;
	private Quadro quadro = Quadro.getInstance();
	private int yBase = 0;
	private int espaco = 85;

	private Null n;

	public void desenhar(int yBase) {
		this.yBase = yBase;

		if (this.ini == null) {
			// desenhar lista vazia "referencia solta"
			if (n == null) {
				this.ref = new Referencia(new Point2D.Double(60, yBase + espaco
						+ 10), referencia + ".in�cio", true);

				n = new Null(new Point2D.Double(
						120 + (7 * referencia.length()), yBase + espaco + 5),
						true);// apontando para null
				quadro.add(n);
				quadro.add(ref);
				quadro.atualizar();
			} else {
				quadro.remove(ref);
				quadro.remove(n);
				this.ref = new Referencia(new Point2D.Double(60, yBase + espaco
						+ 10), referencia + ".in�cio", true);// apontando para
																// null
				n = new Null(new Point2D.Double(
						120 + (7 * referencia.length()), yBase + espaco + 5),
						true);
				quadro.add(n);
				quadro.add(ref);
				quadro.atualizar();
			}
		} else {

			if (n != null) {
				quadro.remove(n);
				n = null;
			}
			if (ref != null) {
				quadro.remove(ref);
				ini.remove(ref);

				ref = new Referencia(ini, referencia + ".in�cio",
						new Point2D.Double(60, yBase + espaco + 10));
				ref.setFixa(true);

				quadro.add(ref);
				quadro.atualizar();
			} else {

				ref = new Referencia(ini, referencia + ".in�cio",
						new Point2D.Double(60, yBase + espaco + 10));
				ref.setFixa(true);

				quadro.add(ref);
				quadro.atualizar();
			}
		}
		if (tam != null) {
			quadro.remove(tam);
			quadro.add(tam);
		} else {
			tam = new VarInteiro(new Point2D.Double(30, yBase + 100),referencia + ".tamanho");
			tam.setValor(tamanho);
			quadro.add(tam);
		}
		quadro.atualizar();
	}

	public void setyBase(int yBase) {
		this.yBase = yBase;
	}

	public void setBond(int bond) {
		this.bond = bond;
	}

	@Override
	public int getBond() {
		return this.bond;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
		this.desenhar(yBase);
	}

	public Node getInit() {
		return ini;
	}

	public void setInit(Struct ini) {
		if(this.ini != null){
			this.ini.remove(this.ref);
		}
		if(n != null){
			quadro.remove(n);
		}
		this.ini = ((Node) ini);
		this.desenhar(yBase);

	}

	public void adjust() {

		this.ini.adjust(this.getPInit());

	}

	public boolean isVista() {
		return this.repintado;
	}

	public void serVista(boolean vista) {
		this.repintado = vista;
	}

	public void setSize(String value) {
		this.tamanho = value;
		if (tam != null) {
			quadro.remove(tam);
			tam = new VarInteiro(new Point2D.Double(30, yBase + 100),referencia + ".tamanho");
			tam.setValor(tamanho);
			quadro.add(tam);
		} else {
			tam = new VarInteiro(new Point2D.Double(30, yBase + 100),referencia + ".tamanho");
			tam.setValor(tamanho);
			quadro.add(tam);
		}
		quadro.atualizar();
	}

	public Point2D getPInit() {
		return new Point2D.Double(VarInteiro.LARGURA + 120, yBase + (bond + 30));
	}

	public void repintar() {
		
// redesenhando
		
		if(!this.repintado){
			this.desenhar(yBase);
			Node n1 = this.ini;

				n1 = this.ini;
				
				while (n1 != null) {
					if(!n1.isRepintado()){
						n1.repintar();
					}else{
						break;
					}
					
					n1 = n1.getProx();
					if (n1 != null && n1.equals(this.ini)) {
						break;
					}
				}
				
				if(this.ini !=null){
					this.adjust();
				}
			
			this.repintado = true;
		}
		
	}

	@Override
	public Struct readField(int field) {
		switch (field) {
		case Lista.INIT:
			return this.ini;

		}
		return null;
	}

	@Override
	public void writeField(Struct s, int field) {
		switch (field) {
		case Lista.INIT:
			this.setInit(((Node) s));

		}

	}

	@Override
	public String readInfo() {
		return this.tamanho;
	}

	@Override
	public void writeInfo(String value) {
		this.tamanho = value;
		if (tam != null) {
			quadro.remove(tam);
			tam = new VarInteiro(new Point2D.Double(30, yBase + 100),
					referencia + ".tamanho");
			tam.setValor(tamanho);
			quadro.add(tam);
		} else {
			tam = new VarInteiro(new Point2D.Double(30, yBase + 100),
					referencia + ".tamanho");
			tam.setValor(tamanho);
			quadro.add(tam);
		}
		quadro.atualizar();
	}

	public static final int INIT = 1;

	@Override
	public boolean isRepintado() {
		return this.repintado;
	}

	@Override
	public void startRepaint() {
		this.repintado = false;
		
		Node n1 = this.ini;
		while (n1 != null) {
			n1.startRepaint();
			n1 = n1.getProx();
			if (n1 != null && n1.equals(this.ini)) {
				break;
			}
		}

	}

	@Override
	public String toString() {
		return "Lista " + referencia;
	}

}
