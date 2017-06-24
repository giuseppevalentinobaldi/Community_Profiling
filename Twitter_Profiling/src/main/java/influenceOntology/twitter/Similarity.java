package influenceOntology.twitter;

public class Similarity {

	private float En;
	private float Ecn;
	private float Ecf;

	private float Ew;
	private float Esn;

	private float Ewc;

	private float Ecc;

	Similarity(float Ecn, float En, float Esn) {
		this.setEn(En);
		this.setEcf(Ecn, En);
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

	public float getEcwc(float Ecc, float Ew) {
		return Ecc * Ew;
	}

}
