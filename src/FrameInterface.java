import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameInterface implements ActionListener{
    final int frameWidth = 420;
    final int frameBiggerWidth = 600;
    final int frameHeight = 330;
    final int frameBiggerHeight = 500;
    final int frameMargin = 20;
    final int buttonWidth = 100;
    final int buttonHeight = 50;
    private int nLanci;

    JFrame frame = new JFrame(); // creating instance of JFrame
    JTextField txtLanci = new JTextField();
    TextArea textPrintArea = new TextArea();
    JButton btn10klanci = new JButton("10k lanci");
    JButton btn100klanci = new JButton("100k lanci");
    JButton btn100lanci = new JButton("100 lanci");
    JButton btnShowCredits = new JButton("Credits");

    public FrameInterface(){
        prepareStartingGUI();
    }

    private void prepareStartingGUI(){
        frame.setTitle("Legge dei grandi numeri");
        frame.getContentPane().setLayout(null);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // stop esecution when frame is closed
        frame.setVisible(false);    // nascondi finestra
    }

    public void setHomePage(){
        // title
        Label lblTitle = new Label("Legge dei grandi numeri");
        Font  Fh1  = new Font(Font.SANS_SERIF,  Font.BOLD|Font.ITALIC, 15);
        lblTitle.setFont(Fh1);
        lblTitle.setBounds(frameMargin, frameMargin, 220, 20);
        frame.add(lblTitle);

        // lanci label
        Label lblTip = new Label("Inserisci quante volte devo lanciare il dado");
        lblTip.setBounds(frameMargin, frameMargin * 3, 250, 20);
        frame.add(lblTip);

        // text field
        txtLanci.setBounds(frameMargin, frameMargin * 4, 150, 22);
        frame.add(txtLanci);

        // button start law with textfield
        JButton btnStartLaw = new JButton("Tira il dado");
        btnStartLaw.setBounds(frameMargin + 150 + frameMargin, frameMargin * 4, buttonWidth, 22);
        btnStartLaw.addActionListener(this);    // Registering ActionListener to the button
        frame.add(btnStartLaw);

        int heightPresetsBtn = frameMargin * 8;
        // presets label
        JLabel lblPresets = new JLabel("Oppure utilizza uno dei preset");
        lblPresets.setBounds(frameMargin, heightPresetsBtn - 30, 200, 20);
        frame.add(lblPresets);

        // button 100 lanci
        btn100lanci.setBounds(frameMargin, heightPresetsBtn, buttonWidth, buttonHeight);
        btn100lanci.addActionListener(this);    // Registering ActionListener to the button
        frame.add(btn100lanci);

        // button 10k lanci
        btn10klanci.setBounds(frameMargin + buttonWidth + frameMargin, heightPresetsBtn, buttonWidth, buttonHeight);
        btn10klanci.addActionListener(this);    // Registering ActionListener to the button
        frame.add(btn10klanci);

        // button 100k lanci
        btn100klanci.setBounds(frameMargin + buttonWidth + buttonWidth + frameMargin * 2, heightPresetsBtn, buttonWidth, buttonHeight);
        btn100klanci.addActionListener(this);    // Registering ActionListener to the button
        frame.add(btn100klanci);

        // button credits
        btnShowCredits.setBounds(frameWidth - frameMargin - 90, frameHeight - frameMargin - 50, 90, 30);
        btnShowCredits.addActionListener(this);
        btnShowCredits.setBackground(Color.gray);
        btnShowCredits.setOpaque(true);
        frame.add(btnShowCredits);

        frame.setVisible(true);
    }

    public String startTesting(int nLanci){
        LawTest lawTest = new LawTest();
        for(int i = 0; i < nLanci; i++){
            lawTest.lancia();
        }
        return lawTest.toString(nLanci);
    }


    // when one button is pressed:
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("--- BTN PRESSED ---");
        String textFieldValue = txtLanci.getText();

        textPrintArea.setBounds(frameMargin, 220, 500, 200);
        textPrintArea.setEditable(false);
        frame.setSize(frameBiggerWidth, frameBiggerHeight);
        btnShowCredits.setBounds(frameBiggerWidth - frameMargin - 90, frameBiggerHeight - frameMargin - 50, 90, 30);

        if (e.getSource() == btn100lanci){
            nLanci = 100;
            String toPrint = startTesting(nLanci);
            textPrintArea.setText(toPrint);
            frame.add(textPrintArea);
        } else if (e.getSource() == btn10klanci){
            nLanci = 10000;
            String toPrint = startTesting(nLanci);
            textPrintArea.setText(toPrint);
            frame.add(textPrintArea);
        } else if (e.getSource() == btn100klanci){
            nLanci = 100000;
            String toPrint = startTesting(nLanci);
            textPrintArea.setText(toPrint);
            frame.add(textPrintArea);
        }else if (e.getSource() == btnShowCredits){
            // popup con credits
            frame.remove(textPrintArea);
            frame.setSize(frameWidth, frameHeight);
            String errorMessage = "\"Crediti\"\n" + "Progetto di Andrea Chiurla con l'aiuto del prof. Luigi Ferrari";
            JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Credits", JOptionPane.INFORMATION_MESSAGE);

        } else{      // button "submit", using text field value
            if (!onlyNumbers(textFieldValue, textFieldValue.length()) || textFieldValue.isEmpty()) {    // invalid value
                System.out.println("--- INVALID VALUE ---");
                frame.remove(textPrintArea);
                frame.setSize(frameWidth, frameHeight);
                btnShowCredits.setBounds(frameWidth - frameMargin - 90, frameHeight - frameMargin - 50, 90, 30);
                // popup con errore
                String errorMessage = "\"Errore\"\n" + "Valore non valido";
                    JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Dialog", JOptionPane.ERROR_MESSAGE);
            }else{      // valid value
                System.out.println("--- VALID VALUE ---");
                nLanci = Integer.parseInt(textFieldValue);
                String toPrint = startTesting(nLanci);
                textPrintArea.setText(toPrint);
                frame.add(textPrintArea);
            }
        }
    }

    private boolean onlyNumbers(String str, int n) {

        // Return false if the string
        // has empty or null
        if (str == null || str == "") {
            return false;
        }

        // Traverse the string from
        // start to end
        for (int i = 0; i < n; i++) {
            // Check if the specified
            // character is not a letter then
            // return false,
            // else return true
            if (Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
