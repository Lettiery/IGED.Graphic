package iged.struct;

import java.awt.geom.Point2D;

public interface Struct {

	public Point2D getPInit();

	public int getBond();
	
	public void repintar();
	
	public boolean isRepintado();
	
	public void startRepaint();

	public Struct readField(int field);

	public void writeField(Struct s, int field);

	public String readInfo();

	public void writeInfo(String value);

	public static final int VAZIA = -1	;
	public static final int LISTA = 0;
	public static final int NODE = 1;
	public static final int VETOR = 2;
}
