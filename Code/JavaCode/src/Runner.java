public class Runner {
    public static void main(String[] args) {
        // System.out.println(VStats.computeGammaFunction(0.5));
        // System.out.println(VStats.computeChiSquarePDF(0, 3));
        // System.out.println(VStats.computeChiSquareCDFRiemannSum(0.7, 5, 6));
        // System.out.println(VStats.computeChiSquareCDFRiemannSum(0.01, 4.7, 5));

        // System.out.println(VStats.computeGammaFunction(2.2345));

        // System.out.println(VStats.computeChiSquareCDF(2.02, 5.778, 7));

        // System.out.println(VStats.StirlingTest2(2));

        // System.out.println(VStats.computeChiSquareCDF(6, 100, 3));

        // System.out.println(VStats.computeChiSquarePDF(0.00204, 1));

        // double[] observed = { 4, 7, 8, 5, 6 };
        // double[] expected = { 6, 6, 6, 6, 6 };

        // System.out.println(VStats.computeChiSquareGOFTest(observed, expected, 0.05));

        // double[][] obs = { { 30, 10 }, { 15, 25 }, { 15, 5 } };
        // System.out.println(VStats.computeChiSquareHomogeneityTest(obs, 0.05));

        // System.out.println(VStats.computeChiSquareCDF(0.00204, 100, 1));

        // System.out.println(VStats.TEST(4));

        // System.out.println(VStats.computeGammaFunctionNemesApprox(0.5));

        // System.out.println(VStats.computeTPDF(0.2007, 8));

        // System.out.println(VStats.computeTCDF(-100, 0, 27));

        // System.out.println(VStats.computeOneMeanTTestHaLessThanValue(50, 50.3, 2.03,
        // 100, 0.05));

        // System.out.println(VStats.computeOneMeanTTestHaGreaterThanValue(55, 56, 2.03,
        // 100, 0.05));

        // System.out.println(VStats.computeOneMeanTTestHaNotEqualToValue(55, 54.7,
        // 1.07, 100, 0.05));

        // double[] indVar = { 4, 5, 7, 8, 55 };
        // double[] depVar = { 7, 8, 9, 11, 12 };

        // System.out.println(VStats.computeLinRegTTestUnequalSlope(indVar, depVar,
        // 0.05));

        // System.out.println(VStats.computeSeb(indVar, depVar));

        // System.out.println(VStats.computeChiSquareCDF(1.02, 3, 5));

        double[] observed = { 2, 4, 6 };
        double[] expected = { 4, 4, 4 };

        System.out.println(VStats.computeChiSquareGOFTest(observed, expected, 0.05));

    }
}