public class Main {
    public static void main(String[] args){
        FrameInterface frameInterface = new FrameInterface();
        frameInterface.setHomePage();


        LawTest lawTest = new LawTest();
        for(int i = 0; i < lawTest.getIterazioni(); i++){
            lawTest.lancia();
        }

        System.out.println(lawTest.toString());
    }
}
