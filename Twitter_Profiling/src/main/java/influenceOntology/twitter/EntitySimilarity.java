package influenceOntology.twitter;

public class EntitySimilarity {

	private float En;
	private float Ecn;
	private float Ecf;
	private float Ew;
	private float Esn;
	private float Ewc;
	private float Ecc;
	private float Ecwc;

	EntitySimilarity() {
		this.Ecwc=0;
	}
	
	public void init(float Ecn, float En, float Esn){
		this.setEn(En);
		this.setEcn(Ecn);
		this.setEcf(Ecn, En);
		this.setEsn(Esn);
		this.setEw(this.getEn(), this.getEsn());
		this.setEwc(this.getEcf(), this.getEw());
		this.setEcc(this.getEcf(), this.getEcn());
		this.generateEcwc();
		System.out.println("parameters:");
		System.out.println(this.getEcf());
		System.out.println(this.getEcn());
		System.out.println(this.getEw());
		System.out.println(this.getEcc());
		System.out.println(this.getEcwc());
	}

	public float getEn() {
		return En;
	}

	public void setEn(float en) {
		En = en;
	}

	public float getEcn() {
		return Ecn;
	}

	public void setEcn(float ecn) {
		Ecn = ecn;
	}

	public float getEcf() {
		return Ecf;
	}

	public float setEcf(float Ecn, float En) {
		return Ecn / En;
	}

	public float getEw() {
		return Ew;
	}

	public float setEw(float En, float Esn) {
		return En / Esn;
	}

	public float getEsn() {
		return Esn;
	}

	public void setEsn(float esn) {
		Esn = esn;
	}

	public float getEwc() {
		return Ewc;
	}

	public float setEwc(float Ecf, float Ew) {
		return Ecf * Ew;
	}

	public float getEcc() {
		return Ecc;
	}

	public float setEcc(float Ecf, float Ecn) {
		return Ecf * Ecn;
	}

	public float getEcwc() {
		return Ecwc;
	}

	public void setEcwc(float ecwc) {
		Ecwc = ecwc;
	}
	
	public void generateEcwc() {
		this.setEcwc(this.getEcc() * this.getEw());
	}

}
