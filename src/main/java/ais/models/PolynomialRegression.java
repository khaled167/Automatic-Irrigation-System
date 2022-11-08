package ais.models;

import Jama.Matrix;
import Jama.QRDecomposition;

public class PolynomialRegression implements Comparable<PolynomialRegression> {
	private final String variableName; // name of the predictor variable
	private int degree; // degree of the polynomial regression
	private Matrix beta; // the polynomial regression coefficients
	private double sse; // sum of squares due to error
	private double sst; // total sum of squares

	public PolynomialRegression(double[] x, double[] y, int degree) {
		this(x, y, degree, "n");
	}

	public static double predict(double[] x, double[] y, double val) {
		return new PolynomialRegression(x, y, 3).predict(val);
	}

	public PolynomialRegression(double[] x, double[] y, int degree, String variableName) {
		this.degree = degree;
		this.variableName = variableName;

		int n = x.length;
		QRDecomposition qr = null;
		Matrix matrixX = null;

		// in case Vandermonde matrix does not have full rank, reduce degree until it
		// does
		while (true) {

			// build Vandermonde matrix
			double[][] vandermonde = new double[n][this.degree + 1];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j <= this.degree; j++) {
					vandermonde[i][j] = Math.pow(x[i], j);
				}
			}
			matrixX = new Matrix(vandermonde);

			// find least squares solution
			qr = new QRDecomposition(matrixX);
			if (qr.isFullRank())
				break;

			// decrease degree and try again
			this.degree--;
		}

		// create matrix from vector
		Matrix matrixY = new Matrix(y, n);

		// linear regression coefficients
		beta = qr.solve(matrixY);

		// mean of y[] values
		double sum = 0.0;
		for (int i = 0; i < n; i++)
			sum += y[i];
		double mean = sum / n;

		// total variation to be accounted for
		for (int i = 0; i < n; i++) {
			double dev = y[i] - mean;
			sst += dev * dev;
		}

		// variation not accounted for
		Matrix residuals = matrixX.times(beta).minus(matrixY);
		sse = residuals.norm2() * residuals.norm2();
	}

	public double beta(int j) {
		// to make -0.0 print as 0.0
		if (Math.abs(beta.get(j, 0)) < 1E-4)
			return 0.0;
		return beta.get(j, 0);
	}

	public int degree() {
		return degree;
	}

	public double R2() {
		if (sst == 0.0)
			return 1.0; // constant function
		return 1.0 - sse / sst;
	}

	public double predict(double x) {
		// horner's method
		double y = 0.0;
		for (int j = degree; j >= 0; j--)
			y = beta(j) + (x * y);
		return y;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		int j = degree;

		// ignoring leading zero coefficients
		while (j >= 0 && Math.abs(beta(j)) < 1E-5)
			j--;

		// create remaining terms
		while (j >= 0) {
			if (j == 0)
				s.append(String.format("%.5f ", beta(j)));
			else if (j == 1)
				s.append(String.format("%.5f %s + ", beta(j), variableName));
			else
				s.append(String.format("%.5f %s^%d + ", beta(j), variableName, j));
			j--;
		}
		s = s.append("  (R^2 = " + String.format("%.5f", R2()) + ")");

		// replace "+ -2n" with "- 2n"
		return s.toString().replace("+ -", "- ");
	}

	public int compareTo(PolynomialRegression that) {
		double EPSILON = 1E-5;
		int maxDegree = Math.max(this.degree(), that.degree());
		for (int j = maxDegree; j >= 0; j--) {
			double term1 = 0.0;
			double term2 = 0.0;
			if (this.degree() >= j)
				term1 = this.beta(j);
			if (that.degree() >= j)
				term2 = that.beta(j);
			if (Math.abs(term1) < EPSILON)
				term1 = 0.0;
			if (Math.abs(term2) < EPSILON)
				term2 = 0.0;
			if (term1 < term2)
				return -1;
			else if (term1 > term2)
				return +1;
		}
		return 0;
	}

	public static void main(String args[]) {
		double[] x = { 40, 20, 10, 80, 200, 130 };
		double[] y = { 80, 40, 20, 160, 400, 260 };
		PolynomialRegression regression = new PolynomialRegression(x, y, 3);

		System.out.println(regression);
		System.out.println(regression.predict(520));

	}
}
