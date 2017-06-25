package influenceOntology.twitter;

public class EntitySimilarity {

	private float En;
	private float Ecn;
	private float Ecf;
	private float Ew;
	private float Esn;
	private float Ewc;
	private float Ecc;

	EntitySimilarity(float Ecn, float En, float Esn) {
		this.setEn(En);
		this.setEcf(Ecn, En);
		this.setEsn(Esn);
		this.setEw(this.getEn(), this.getEsn());
		this.setEwc(this.getEcf(), this.getEw());
		this.setEcc(this.getEcf(), this.getEcn());
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
		return this.getEcc() * this.getEw();
	}

}
