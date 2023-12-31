import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameInterface implements ActionListener{
    final int frameWidth = 620;
    final int frameHeight = 500;
    final int frameMargin = 20;
    final int buttonWidth = 100;
    final int buttonHeight = 50;
    LawTest lawTest = new LawTest();
    JFrame frame = new JFrame(); // creating instance of JFrame
    JTextField txtLanci = new JTextField();
    TextArea textPrintArea = new TextArea();
    JButton btn100lanci = new JButton("100 lanci");
    JButton btn1klanci = new JButton("1k lanci");
    JButton btn10klanci = new JButton("10k lanci");
    JButton btn100klanci = new JButton("100k lanci");
    JButton btn1000klanci = new JButton("1000k lanci");
    JButton btnShowCredits = new JButton("Credits");
    Label[] lblsPercentage = {new Label(), new Label(), new Label(), new Label(), new Label(), new Label()};
    JCheckBox cbxAnimation = new JCheckBox("Rallenta costruzione grafico", false);
    int heightPresetsBtn = frameMargin * 8 - 20 ;       // height of the preset buttons
    Label[] lblsTime = {new Label(), new Label(), new Label(), new Label(), new Label(), new Label()};

    public FrameInterface(){
        prepareStartingGUI();
    }

    /**
     * Sets the frame properties
     */
    private void prepareStartingGUI(){
        frame.setTitle("Legge dei grandi numeri");
        frame.getContentPane().setLayout(null);
        frame.setSize(frameWidth, frameHeight);
        frame.setLocationRelativeTo(null);      // open frame in center of the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // stop execution when frame is closed
        frame.setResizable(false);
        frame.setVisible(false);    // nascondi finestra
    }

    /**
     * Sets the frame objects and makes the frame visible
     */
    public void setHomePage(){
        // title
        Label lblTitle = new Label("Legge dei grandi numeri");
        Font  Fh1  = new Font(Font.SANS_SERIF,  Font.BOLD|Font.ITALIC, 15);
        lblTitle.setFont(Fh1);
        lblTitle.setBounds(frameMargin, frameMargin, 220, 20);
        frame.add(lblTitle);

        // lanci label
        Label lblTip = new Label("Inserisci quante volte devo lanciare il dado");
        lblTip.setBounds(frameMargin, frameMargin * 3, 280, 20);
        frame.add(lblTip);

        // text field
        txtLanci.setBounds(frameMargin, frameMargin * 4, 150, 22);
        frame.add(txtLanci);

        // button start law with textfield
        JButton btnStartLaw = new JButton("Tira il dado");
        btnStartLaw.setBounds(frameMargin + 150 + frameMargin, frameMargin * 4, buttonWidth, 22);
        btnStartLaw.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnStartLaw.addActionListener(this);    // Registering ActionListener to the button
        frame.add(btnStartLaw);

        // checkbox animation
        cbxAnimation.setBounds(350, frameMargin * 4, 250, 22);
        cbxAnimation.addActionListener(this);
        frame.add(cbxAnimation);

        // presets label
        JLabel lblPresets = new JLabel("Oppure utilizza uno dei preset");
        lblPresets.setBounds(frameMargin, heightPresetsBtn - 20, 200, 20);
        frame.add(lblPresets);

        // button 100 lanci
        btn100lanci.setBounds(frameMargin, heightPresetsBtn, buttonWidth, buttonHeight);
        btn100lanci.addActionListener(this);    // Registering ActionListener to the button
        btn100lanci.setCursor(new Cursor(Cursor.HAND_CURSOR));
        frame.add(btn100lanci);

        // button 1k lanci
        btn1klanci.setBounds(frameMargin * 2 + buttonWidth, heightPresetsBtn, buttonWidth, buttonHeight);
        btn1klanci.addActionListener(this);    // Registering ActionListener to the button
        btn1klanci.setCursor(new Cursor(Cursor.HAND_CURSOR));
        frame.add(btn1klanci);

        // button 10k lanci
        btn10klanci.setBounds(frameMargin * 3 + buttonWidth * 2, heightPresetsBtn, buttonWidth, buttonHeight);
        btn10klanci.addActionListener(this);    // Registering ActionListener to the button
        btn10klanci.setCursor(new Cursor(Cursor.HAND_CURSOR));
        frame.add(btn10klanci);

        // button 100k lanci
        btn100klanci.setBounds(frameMargin * 4 + buttonWidth * 3, heightPresetsBtn, buttonWidth, buttonHeight);
        btn100klanci.addActionListener(this);    // Registering ActionListener to the button
        btn100klanci.setCursor(new Cursor(Cursor.HAND_CURSOR));
        frame.add(btn100klanci);

        // button 1000k lanci
        btn1000klanci.setBounds(frameMargin * 5 + buttonWidth * 4, heightPresetsBtn, buttonWidth, buttonHeight);
        btn1000klanci.addActionListener(this);    // Registering ActionListener to the button
        btn1000klanci.setCursor(new Cursor(Cursor.HAND_CURSOR));
        frame.add(btn1000klanci);

        // button credits
        btnShowCredits.setBounds(frameWidth - frameMargin - 90, frameHeight - frameMargin - 50, 90, 30);
        btnShowCredits.addActionListener(this);
        btnShowCredits.setBackground(Color.gray);
        btnShowCredits.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnShowCredits.setOpaque(true);
        frame.add(btnShowCredits);

        frame.setVisible(true);
    }

    /**
     * Calls the method of the lawTest class that throws a dice once n times
     * @param nThrows
     * @return string to print in the frame with the n times that each number did
     */
    public String startTesting(int nThrows){
        lawTest.reset();
        for(int i = 0; i < nThrows; i++){
            lawTest.lancia();
        }
        return lawTest.toString(nThrows);
    }

    public String startTesting(int nThrows, int slow){
        lawTest.lancia();
        return lawTest.toString(nThrows);
    }

    /**
     * Removes 6 labels (graphic)
     */
    private void removePercentageLabels(){
        for (Label label : lblsPercentage) {
            frame.remove(label);
        }
    }

    /**
     * Prints 6 labels for the graphic
     * @param percentage
     */
    public void printPercentageLabels(double[] percentage){
        removePercentageLabels();

        int y = 230;
        for (int i = 0; i < percentage.length; i++){
            lblsPercentage[i].setBackground(new Color(255, 0, 0));
            double width = mapMinMax(percentage[i], 0, 100, 10, 1000);
            lblsPercentage[i].setBounds(frameMargin + 150 + frameMargin, y, (int)width, 20);
            frame.add(lblsPercentage[i]);
            y += 30;
        }
    }

    /**
     * Re-maps a number from one range to another
     * @param value value to map
     * @param oldMin
     * @param oldMax
     * @param newMin
     * @param newMax
     * @return value mapped
     */
    public double mapMinMax(double value, double oldMin, double oldMax, double newMin, double newMax) {
        return  (newMax-newMin)*(value-oldMin)/(oldMax-oldMin)+newMin;
    }


    // when one button is pressed:
    @Override
    public void actionPerformed(ActionEvent e) {
        String textFieldValue = txtLanci.getText();

        textPrintArea.setBounds(frameMargin, 220, 150, 160);
        textPrintArea.setEditable(false);
        btnShowCredits.setBounds(frameWidth - frameMargin - 90, frameHeight - frameMargin - 50, 90, 30);

        int nLanci;
        if (e.getSource() == btn100lanci) {             // BTN 100 THROWS
            nLanci = 100;
            if(cbxAnimation.isSelected()){
                buildResults(nLanci, 12);
            }else {
                String toPrint = startTesting(nLanci);
                textPrintArea.setText(toPrint);
                frame.add(textPrintArea);
                printPercentageLabels(lawTest.getPercentuali());
            }
        }else if (e.getSource() == btn1klanci){         // BTN 1K THROWS
            nLanci = 1000;
            if(cbxAnimation.isSelected()){
                buildResults(nLanci, 1);
            }else {
                String toPrint = startTesting(nLanci);
                textPrintArea.setText(toPrint);
                frame.add(textPrintArea);
                printPercentageLabels(lawTest.getPercentuali());
            }
        } else if (e.getSource() == btn10klanci){        // BTN 10K THROWS
            nLanci = 10000;
            if(cbxAnimation.isSelected()){
                buildResults(nLanci, 1);
            }else {
                String toPrint = startTesting(nLanci);
                textPrintArea.setText(toPrint);
                frame.add(textPrintArea);
                printPercentageLabels(lawTest.getPercentuali());
            }
        } else if (e.getSource() == btn100klanci){      // BTN 100K THROWS
            nLanci = 100000;
            if(cbxAnimation.isSelected()){
                buildResults(nLanci, 1);
            }else {
                String toPrint = startTesting(nLanci);
                textPrintArea.setText(toPrint);
                frame.add(textPrintArea);
                printPercentageLabels(lawTest.getPercentuali());
            }
        } else if (e.getSource() == btn1000klanci){     // BTN 1000K THROWS
            nLanci = 1000000;
            if(cbxAnimation.isSelected()){
                buildResults(nLanci, 1);
            }else {
                String toPrint = startTesting(nLanci);
                textPrintArea.setText(toPrint);
                frame.add(textPrintArea);
                printPercentageLabels(lawTest.getPercentuali());
            }
        }else if (e.getSource() == btnShowCredits) {     // BTN SHOW CREDITS
            frame.remove(textPrintArea);
            removePercentageLabels();
            // popup with credits
            String errorMessage = "\"Crediti\"\n" + "Progetto di Andrea Chiurla con l'aiuto del prof. Luigi Ferrari";
            JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Credits", JOptionPane.INFORMATION_MESSAGE);
        }else if (e.getSource() == cbxAnimation) {
            if (cbxAnimation.isSelected()) {
                // popup con informazione
                String errorMessage = "\"Attenzione\"\n" + "Per favore, non interrompere la costruzione del grafico cliccando un altro bottone";
                JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Dialog", JOptionPane.INFORMATION_MESSAGE);

                // print time indication labels
                lblsTime[0].setText("circa 2s");
                lblsTime[0].setBounds(frameMargin + 25, heightPresetsBtn + 40, 50, 30);
                frame.add(lblsTime[0]);

                lblsTime[1].setText("circa 2s");
                lblsTime[1].setBounds(frameMargin * 2 + 25  + buttonWidth, heightPresetsBtn + 40, 50, 30);
                frame.add(lblsTime[1]);

                lblsTime[2].setText("circa 20s");
                lblsTime[2].setBounds(frameMargin  * 3 + 25 + buttonWidth * 2, heightPresetsBtn + 40, 60, 30);
                frame.add(lblsTime[2]);

                lblsTime[3].setText("circa 3min30s");
                lblsTime[3].setBounds(frameMargin * 4 + 8 + buttonWidth * 3, heightPresetsBtn + 40, 100, 30);
                frame.add(lblsTime[3]);

                lblsTime[4].setText("scoprilo tu stesso");
                lblsTime[4].setBounds(frameMargin * 5 + buttonWidth * 4, heightPresetsBtn + 40, 150, 30);
                frame.add(lblsTime[4]);

                frame.repaint();
            }
        }else {      // BTN "submit" (custom value)
            if (!onlyNumbers(textFieldValue) || textFieldValue.isEmpty()) {    // invalid value
                System.out.println("--- INVALID VALUE ---");
                frame.remove(textPrintArea);
                removePercentageLabels();
                btnShowCredits.setBounds(frameWidth - frameMargin - 90, frameHeight - frameMargin - 50, 90, 30);
                // popup con errore
                String errorMessage = "\"Errore\"\n" + "Valore non valido";
                JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Dialog", JOptionPane.ERROR_MESSAGE);
            }else{      // valid value
                System.out.println("--- VALID VALUE ---");
                nLanci = Integer.parseInt(textFieldValue.trim());
                String toPrint = startTesting(nLanci);
                textPrintArea.setText(toPrint);
                frame.add(textPrintArea);
                printPercentageLabels(lawTest.getPercentuali());
            }
        }
    }

    private void buildResults(int nLanci, int millisDelay) {
        // background working: https://stackoverflow.com/a/19708722
        SwingWorker<String, Object> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() {
                lawTest.reset();
                frame.add(textPrintArea);
                for (int i = 0; i < nLanci; i++) {
                    String toPrint = startTesting(nLanci, 0);
                    printPercentageLabels(lawTest.getPercentuali(i));
                    textPrintArea.setText(toPrint);
                    try {
                        Thread.sleep(millisDelay);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                return "DONE";
            }
        };
        worker.execute();
    }

    /**
     * Checks if there are only numbers in a string, uses trim()
     * @param str
     * @return True if only digits
     */
    private boolean onlyNumbers(String str) {
        // ignores leading and trailing spaces
        str = str.trim();
        int n = str.length();

        // Return false if the string
        // has empty or null
        if (str == null || str.isEmpty()) {
            return false;
        }

        // Traverse the string from
        // start to end
        for (int i = 0; i < n; i++) {
            // Check if the specified
            // character is not a letter then
            // return false,
            // else return true
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
