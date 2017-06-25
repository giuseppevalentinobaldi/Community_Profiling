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
	}

	public float getEn() {
		return En;
	}

	public void setEn(float en) {
		this.En = en;
	}

	public float getEcn() {
		return Ecn;
	}

	public void setEcn(float ecn) {
		this.Ecn = ecn;
	}

	public float getEcf() {
		return Ecf;
	}

	public void setEcf(float Ecn, float En) {
		this.Ecf = Ecn / En;
	}

	public float getEw() {
		return Ew;
	}

	public void setEw(float En, float Esn) {
		this.Ew = En / Esn;
	}

	public float getEsn() {
		return Esn;
	}

	public void setEsn(float esn) {
		this.Esn = esn;
	}

	public float getEwc() {
		return Ewc;
	}

	public void setEwc(float Ecf, float Ew) {
		this.Ewc = Ecf * Ew;
	}

	public float getEcc() {
		return Ecc;
	}

	public void setEcc(float Ecf, float Ecn) {
		this.Ecc = Ecf * Ecn;
	}

	public float getEcwc() {
		return Ecwc;
	}

	public void setEcwc(float ecwc) {
		this.Ecwc = ecwc;
	}
	
	public void generateEcwc() {
		this.setEcwc(this.getEcc() * this.getEw());
	}

}
