
public class Trainer {
	double[] inputs;
	int answer;
	
	public Trainer(double x, double y, int a) {
		inputs = new double[3];
		inputs[0] = x;
		inputs[1] = y;
		inputs[2] = 1;
		answer = a;
	}
	
	public String getPoint() {
		return "(" + inputs[0] + ", " + inputs[1] + ") " + answer + "\n";
	}
	
	public double[] getInputs() {
		return inputs;
	}
	
	public int getAnswer() {
		return answer;
	}
}
