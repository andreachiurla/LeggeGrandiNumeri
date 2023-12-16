
/**
 * Classe che verifica la legge dei grandi numeri
 * 
 * @author Chiurla Andrea 4AII
 * @version 07/11/23
 */
public class LawTest
{
    private int[] numbersDrew = {0, 0, 0, 0, 0, 0};
    private double[] percentuali = {0, 0, 0, 0, 0, 0,};
    private final static int iterazioni = 100000;

    /**
     * Costruttore
     */
    public LawTest()
    {
        
    }

    /**
     * lancia
     */
    public void lancia(){
        numbersDrew[getRandomNumber(0, 6)]++;
    }

    public String toString(int iterazioni){
        // creating string to print
        String string = "Usciti:\n*1*: ";
        for(int i = 0; i < numbersDrew.length; i++){
            string += numbersDrew[i];
            if(i < numbersDrew.length - 1) string += "\n" + "*" + (i + 1) + "* ";
        }
        return string;
    }

    public double[] getPercentuali(){
        for(int i = 0; i < numbersDrew.length; i++){
            percentuali[i] = roundAvoid((double)numbersDrew[i] / (double)iterazioni * 100, 2);  // calcolo percentuale
        }
        return percentuali;
    }

    public static int getIterazioni() {
        return iterazioni;
    }

    /*
    public String graphs(){
        String graph = "";
        String tempRiga;
        int length = numbersDrew.length;
        for(int riga = 0; riga < length; riga++){
            tempRiga = "";
            for(int numbersInRiga = 0; numbersInRiga < length; numbersInRiga++){
                if(percentuali[riga] > 90) tempRiga+= "--"; // togliere 90 e mettere variabile
            }
            tempRiga += " ";
            // stringa grafico + tempRiga
        }

        return graph;
    }
    */

    public int getRandomNumber(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    private static double roundAvoid(double value, int places) {     // https://www.baeldung.com/java-round-decimal-number
    double scale = Math.pow(10, places);
    return Math.round(value * scale) / scale;
    }
}
