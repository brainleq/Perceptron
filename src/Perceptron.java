
public class Perceptron {
	
	double[] weights;
	double eta = 0.1;
	
	/**
	 * initializes random weights
	 * @param n the number of inputs to perceptron
	 */
	public Perceptron(int n) {
		weights = new double[n];
		for(int i = 0; i < n; i++) {
			weights[i] = randomValue(-1, 1);
		}
	}
	
	/**
	 * calculates weighted sum of inputs
	 * @param inputs array with x coord, y coord, and bias
	 * @return the activated weighted sum
	 */
	public int feedforward(double[] inputs) {
		double sum = 0;
		for(int i = 0; i < weights.length; i++) {
			sum += weights[i] * inputs[i];
		}
		return activate(sum);
	}
	
	/**
	 * binary step activation function
	 * @param sum the weighted sum of inputs
	 * @return 1 if sum > 0 and -1 otherwise
	 */
	public int activate(double sum) {
		if(sum > 0)
			return 1;
		else
			return -1;
	}
	
	/**
	 * adjusts the weights of the perceptron
	 * @param inputs array with x coord, y coord, and bias
	 * @param desired
	 */
	public void train(double[] inputs, int desired) {
		int guess = feedforward(inputs);
		double error = desired - guess;
		for(int i = 0; i < weights.length; i++) {
			weights[i] += eta * error * inputs[i];
		}
	}
	
	/**
	 * line function that defines which points are 1 or -1
	 * @param x x coordinate of point
	 * @return f(x)
	 */
	public static double lineFunction(double x) {
		return 2*x + 1;
	}
	
	public static double randomValue(double lower_bound, double upper_bound){
        return Math.random()*(upper_bound-lower_bound) + lower_bound;
    }
	
	public static void main(String[] args) {
		int setSize = 20000;
		Perceptron ptron = new Perceptron(3);
		Trainer[] training = new Trainer[setSize];
		
		// create the training points
		for(int i = 0; i < training.length; i++) {
			double x = randomValue(-100, 100);
			double y = randomValue(-100, 100);
			int answer = 1;
			if(y < lineFunction(x))
				answer = -1;
			training[i] = new Trainer(x, y, answer);
		}
		
		int errorCount = 0;
		
		for(int i = 0; i < setSize-1000; i++) {
			int guess = ptron.feedforward(training[i].getInputs());
			if(guess == training[i].getAnswer()) {
				System.out.println("CORRECT");
			}
			else {
				errorCount++;
				System.out.println("============= " + errorCount);
			}
			ptron.train(training[i].getInputs(), training[i].getAnswer());
		}
		System.out.println("TEST SET TOTAL ERRORS = " + errorCount + "\n");
		
		System.out.println("FINAL TEST OF 1000 POINTS");
		int correctCount = 0;
		for(int i = setSize-1000; i < setSize; i++) {
			int guess = ptron.feedforward(training[i].getInputs());
			if(guess == training[i].getAnswer()) {
				correctCount++;
			}
		}
		System.out.println("RESULTS: " + correctCount + "/1000");
		
	}
	
}
