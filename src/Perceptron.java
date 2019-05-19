
public class Perceptron {
	
	double[] weights;
	double eta = 0.01;
	
	public Perceptron(int n) {
		weights = new double[n];
		for(int i = 0; i < n; i++) {
			weights[i] = randomValue(-1, 1);
		}
	}
	
	public int feedforward(double[] inputs) {
		double sum = 0;
		for(int i = 0; i < weights.length; i++) {
			sum += weights[i] * inputs[i];
		}
		return activate(sum);
	}
	
	public int activate(double sum) {
		if(sum > 0)
			return 1;
		else
			return -1;
	}
	
	public void train(double[] inputs, int desired) {
		int guess = feedforward(inputs);
		double error = desired - guess;
		for(int i = 0; i < weights.length; i++) {
			weights[i] += eta * error * inputs[i];
		}
	}
	
	public static double randomValue(double lower_bound, double upper_bound){
        return Math.random()*(upper_bound-lower_bound) + lower_bound;
    }
	
	public static double lineFunction(double x) {
		return 2*x + 1;
	}
	
	public static void main(String[] args) {
		Perceptron per = new Perceptron(3);
		Trainer[] training = new Trainer[2000];
		
		for(int i = 0; i < training.length; i++) {
			double x = randomValue(-200, 200);
			double y = randomValue(-200, 200);
			int answer = 1;
			if(y < lineFunction(x))
				answer = -1;
			training[i] = new Trainer(x, y, answer);
		}
		
		
	}
	
}
