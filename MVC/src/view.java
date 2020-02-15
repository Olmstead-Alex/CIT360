import java.awt.event.ActionListener;
import javax.swing.*;

public class view extends JFrame {

    private JTextField firstNumber = new JTextField(10);
    private JLabel additionLabel = new JLabel("+");
    private JLabel multiplyLabel = new JLabel("x");
    private JLabel divideLabel = new JLabel("/");
    private JLabel subtractLabel = new JLabel("-");
    private JTextField secondNumber = new JTextField(10);
    private JButton calculateButton = new JButton("Calculate");
    private JTextField calcSolution = new JTextField(10);

    view(){
        JPanel multiplyPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize (600, 200);
        
        multiplyPanel.add(firstNumber);
        multiplyPanel.add(multiplyLabel);
        multiplyPanel.add(secondNumber);
        multiplyPanel.add(calculateButton);
        multiplyPanel.add(calcSolution);

        this.add(multiplyPanel);
    }

    public int getFirstNumber(){
        return Integer.parseInt(firstNumber.getText());
    }

    public int getSecondNumber(){
        return Integer.parseInt(secondNumber.getText());
    }

    public int getCalcSolution(){
        return Integer.parseInt(calcSolution.getText());
    }

    public void setCalcSolution(int solution){
        calcSolution.setText(Integer.toString((solution)));
    }

    void addCalculationListener(ActionListener listenForCalcButton){
        calculateButton.addActionListener(listenForCalcButton);
    }


    void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }

}
