import java.util.Arrays;

/**
 * Classe che verifica la legge dei grandi numeri
 * 
 * @author Chiurla Andrea 4AII
 * @version started: 07/11/23
 */
public class LawTest {
    private int[] numbersDrew = {0, 0, 0, 0, 0, 0};
    private double[] percentuali = {0, 0, 0, 0, 0, 0,};
    private static int iterazioni = 100000;

    /**
     * Costruttore
     */
    public LawTest() {
        
    }

    /**
     * lancia
     */
    public void lancia(){
        numbersDrew[getRandomNumber(0, 6)]++;
    }

    public String toString(int inIterazioni){
        iterazioni = inIterazioni;
        // creating string to print
        String string = "Usciti:\n*1*: ";
        for(int i = 1; i < numbersDrew.length; i++){
            string += numbersDrew[i];
            if(i < numbersDrew.length - 1) string += "\n" + "*" + (i + 1) + "* ";
        }
        return string;
    }

    public double[] getPercentuali(){
        for(int i = 0; i < numbersDrew.length; i++){
            percentuali[i] = roundAvoid((double)numbersDrew[i] / (double)getIterazioni() * 100, 2);  // calcolo percentuale
        }
        return percentuali;
    }
    public double[] getPercentuali(int iterationsDone){
        for(int i = 0; i < numbersDrew.length; i++){
            percentuali[i] = roundAvoid((double)numbersDrew[i] / (double)iterationsDone * 100, 2);  // calcolo percentuale
        }
        return percentuali;
    }

    public void reset(){
        Arrays.fill(numbersDrew, 0);
    }

    public static int getIterazioni() {
        return iterazioni;
    }

    public int getRandomNumber(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    private static double roundAvoid(double value, int places) {     // https://www.baeldung.com/java-round-decimal-number
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}