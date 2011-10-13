package iged.struct;

import iged.geometria.Label;
import java.awt.geom.Point2D;
import java.util.*;

public class Vetor extends Elemento implements Struct{
	private static int bond = 100;
	private Label nome = null;
	private List<Celula> celulas = null;

	public Vetor(String nome, int size, Point2D p){
		this.pb = p;
		this.nome = new Label(nome, new Point2D.Double(pb.getX() - 5, pb.getY() - 5));
		this.textos.add(this.nome);

		this.celulas = new ArrayList<Celula>(size);
		for(int i=0; i<size; ++i){
			Point2D pc = new Point2D.Double(pb.getX() + i*(Celula.LARGURA + 2), pb.getY());
			Celula c = new Celula(pc, i);
			this.celulas.add(c);
			this.elementos.add(c);
		}
	}

	public void ler(int pos){
		if(pos >= celulas.size())
			return;
		this.celulas.get(pos).ler();
	}

	public void escrever(int pos, String valor){
		if(pos >= celulas.size())
			return;
		this.celulas.get(pos).escrever(valor);
	}

	@Override
	public int getBond() {
		return Vetor.bond;
	}

	@Override
	public Point2D getPInit() {
		return this.pb;
	}


	@Override
	public void repintar() {
		
	}

	@Override
	public Struct readField(int field) {
		return null;
	}

	@Override
	public void writeField(Struct s, int field) {
		
	}

	@Override
	public String readInfo() {
		return null;
	}

	@Override
	public void writeInfo(String value) {
	}

	@Override
	public boolean isRepintado() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void startRepaint() {
		// TODO Auto-generated method stub
		
	}
}