import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controller {

    private view theView;
    private model theModel;

    public controller(view theView, model theModel){
        this.theView = theView;
        this.theModel = theModel;

        this.theView.addCalculationListener(new CalculateListener());
    }

    class CalculateListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            int firstNumber, secondNumber = 0;


            try{
                firstNumber = theView.getFirstNumber();
                secondNumber = theView.getSecondNumber();

                theModel.multiplyTwoNumbers(firstNumber, secondNumber);

                theView.setCalcSolution(theModel.getCalculationValue());
            }

            catch (NumberFormatException ex){
                theView.displayErrorMessage("You need to enter two Integers.");
            }
        }
    }
}
