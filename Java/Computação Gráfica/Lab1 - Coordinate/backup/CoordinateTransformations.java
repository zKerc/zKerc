public class CoordinateTransformations {

    // Caso I: NDC [0,1] x [0,1]
    public static void inpToNdc_CaseI(double inp_x, double inp_y, double inp_min, double inp_max, double[] ndc) {
        ndc[0] = (inp_x - inp_min) / (inp_max - inp_min);
        ndc[1] = (inp_y - inp_min) / (inp_max - inp_min);
    }

    // Caso II: NDC [-1,1] x [-1,1]
    public static void inpToNdc_CaseII(double inp_x, double inp_y, double inp_min, double inp_max, double[] ndc) {
        ndc[0] = 2 * (inp_x - inp_min) / (inp_max - inp_min) - 1;
        ndc[1] = 2 * (inp_y - inp_min) / (inp_max - inp_min) - 1;
    }

    public static void ndcToUser(double ndc_x, double ndc_y, double user_min, double user_max, double[] user) {
        user[0] = (ndc_x + 1) / 2 * (user_max - user_min) + user_min;
        user[1] = (ndc_y + 1) / 2 * (user_max - user_min) + user_min;
    }

    public static void userToNdc(double user_x, double user_y, double user_min, double user_max, double[] ndc) {
        ndc[0] = 2 * (user_x - user_min) / (user_max - user_min) - 1;
        ndc[1] = 2 * (user_y - user_min) / (user_max - user_min) - 1;
    }

    public static void ndcToDc(double ndc_x, double ndc_y, int dc_max_x, int dc_max_y, int[] dc) {
        dc[0] = (int) ((ndc_x + 1) / 2 * dc_max_x);
        dc[1] = (int) ((ndc_y + 1) / 2 * dc_max_y);
    }
}
