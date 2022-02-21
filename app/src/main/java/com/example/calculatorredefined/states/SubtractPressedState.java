package com.example.calculatorredefined.states;

import com.example.calculatorredefined.CalculatorModel;
import com.example.calculatorredefined.CalculatorViewModel;

import java.math.BigDecimal;

public class SubtractPressedState implements IState {
    CalculatorViewModel calculatorViewModel;

    public SubtractPressedState(CalculatorViewModel calculatorViewModel) {
        this.calculatorViewModel = calculatorViewModel;
    }

    @Override
    public void pressANumber(String numberPressed) {
        calculatorViewModel.setSecondOperandString(calculatorViewModel.getSecondOperandString() + numberPressed);
        calculatorViewModel.setSecondNumber(new BigDecimal(calculatorViewModel.getSecondOperandString()));

        BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
        BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
        BigDecimal subtractionResult = CalculatorModel.subtract(firstNumber, secondNumber);
        calculatorViewModel.setLastSavedResult(subtractionResult.toString());
    }

    @Override
    public void pressASign() {
        if (!calculatorViewModel.getSecondOperandString().isEmpty()) {
            calculatorViewModel.setSecondOperandString(CalculatorModel.negate(calculatorViewModel.getSecondNumber()).toString());
            calculatorViewModel.setSecondNumber(new BigDecimal(calculatorViewModel.getSecondOperandString()));

            BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
            BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
            BigDecimal subtractionResult = CalculatorModel.subtract(firstNumber, secondNumber);
            calculatorViewModel.setLastSavedResult(subtractionResult.toString());
        }
    }

    @Override
    public void pressADot() {
        if (!calculatorViewModel.isDotPressed()) {
            calculatorViewModel.setDotPressed();

            if (!calculatorViewModel.isSecondOperandStringEmpty()) {
                calculatorViewModel.setSecondOperandString(calculatorViewModel.getSecondOperandString() + '.');
            } else {
                calculatorViewModel.setSecondOperandString(calculatorViewModel.getSecondOperandString() + "0.");
            }

            calculatorViewModel.setSecondNumber(new BigDecimal(calculatorViewModel.getSecondOperandString()));

            BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
            BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
            BigDecimal subtractionResult = CalculatorModel.subtract(firstNumber, secondNumber);
            calculatorViewModel.setLastSavedResult(subtractionResult.toString());
        }
    }

    @Override
    public void pressAllClear() {
        calculatorViewModel.setState(calculatorViewModel.getFirstOperandInputState());
        calculatorViewModel.setDotUnpressed();
        calculatorViewModel.setCurrentOperation("");
        calculatorViewModel.setFirstOperandString("");
        calculatorViewModel.setSecondOperandString("");
        calculatorViewModel.setLastSavedResult("");
    }

    @Override
    public void pressClear() {
        if (!calculatorViewModel.isSecondOperandStringEmpty()) {
            if (calculatorViewModel.isLastCharacterOfSecondNumberADot())
                calculatorViewModel.setDotUnpressed();

            calculatorViewModel.removeLastCharacterOfSecondOperandString();

            if (!calculatorViewModel.isSecondOperandStringEmpty()) {
                calculatorViewModel.setSecondNumber(new BigDecimal(calculatorViewModel.getSecondOperandString()));

                BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
                BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
                BigDecimal subtractionResult = CalculatorModel.subtract(firstNumber, secondNumber);
                calculatorViewModel.setLastSavedResult(subtractionResult.toString());

            } else {
                calculatorViewModel.setLastSavedResult(calculatorViewModel.getFirstOperandString());
            }
        } else if (!calculatorViewModel.isOperationEmpty()) {
            calculatorViewModel.setCurrentOperation("");
            calculatorViewModel.setState(calculatorViewModel.getFirstOperandInputState());
        }
    }

    @Override
    public void evaluateExpression() {
        if (!calculatorViewModel.isSecondOperandStringEmpty()) {
            calculatorViewModel.setState(calculatorViewModel.getFirstOperandInputState());

            BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
            BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
            BigDecimal subtractionResult = CalculatorModel.subtract(firstNumber, secondNumber);
            calculatorViewModel.setSecondOperandString("");
            calculatorViewModel.setCurrentOperation("");
            calculatorViewModel.setLastSavedResult(subtractionResult.toString());
            calculatorViewModel.setFirstOperandString(subtractionResult.toString());
            calculatorViewModel.setFirstNumber(subtractionResult);
            calculatorViewModel.setLastSavedResult(subtractionResult.toString());
        }
    }

    @Override
    public void pressAdd() {
        if (!calculatorViewModel.isSecondOperandStringEmpty()) {
            BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
            BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
            BigDecimal subtractionResult = CalculatorModel.subtract(firstNumber, secondNumber);

            calculatorViewModel.setLastSavedResult(subtractionResult.toString());
            calculatorViewModel.setFirstOperandString(subtractionResult.toString());
            calculatorViewModel.setFirstNumber(subtractionResult);
            calculatorViewModel.setSecondOperandString("");
            calculatorViewModel.setCurrentOperation("+");
            calculatorViewModel.setDotUnpressed();
        } else {
            calculatorViewModel.setCurrentOperation("+");
        }
    }

    @Override
    public void pressSubtract() {
        if (!calculatorViewModel.isSecondOperandStringEmpty()) {
            BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
            BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
            BigDecimal subtractionResult = CalculatorModel.subtract(firstNumber, secondNumber);

            calculatorViewModel.setLastSavedResult(subtractionResult.toString());
            calculatorViewModel.setFirstOperandString(subtractionResult.toString());
            calculatorViewModel.setFirstNumber(subtractionResult);
            calculatorViewModel.setSecondOperandString("");
            calculatorViewModel.setCurrentOperation("-");
            calculatorViewModel.setDotUnpressed();
            calculatorViewModel.setState(calculatorViewModel.getSubtractPressedState());
        }
    }

    @Override
    public void pressDivide() {
        if (!calculatorViewModel.isSecondOperandStringEmpty()) {
            BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
            BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
            BigDecimal subtractionResult = CalculatorModel.subtract(firstNumber, secondNumber);

            calculatorViewModel.setLastSavedResult(subtractionResult.toString());
            calculatorViewModel.setFirstOperandString(subtractionResult.toString());
            calculatorViewModel.setFirstNumber(subtractionResult);
            calculatorViewModel.setSecondOperandString("");
            calculatorViewModel.setCurrentOperation("÷");
            calculatorViewModel.setDotUnpressed();
            calculatorViewModel.setState(calculatorViewModel.getDividePressedState());
        } else {
            calculatorViewModel.setCurrentOperation("÷");
        }
    }

    @Override
    public void pressRemain() {
        if (!calculatorViewModel.isSecondOperandStringEmpty()) {
            BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
            BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
            BigDecimal subtractionResult = CalculatorModel.subtract(firstNumber, secondNumber);

            calculatorViewModel.setLastSavedResult(subtractionResult.toString());
            calculatorViewModel.setFirstOperandString(subtractionResult.toString());
            calculatorViewModel.setFirstNumber(subtractionResult);
            calculatorViewModel.setSecondOperandString("");
            calculatorViewModel.setCurrentOperation("%");
            calculatorViewModel.setDotUnpressed();
            calculatorViewModel.setState(calculatorViewModel.getRemainPressedState());
        } else {
            calculatorViewModel.setCurrentOperation("%");
        }
    }

    @Override
    public void pressMultiply() {
        if (!calculatorViewModel.isSecondOperandStringEmpty()) {
            BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
            BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
            BigDecimal subtractionResult = CalculatorModel.subtract(firstNumber, secondNumber);

            calculatorViewModel.setLastSavedResult(subtractionResult.toString());
            calculatorViewModel.setFirstOperandString(subtractionResult.toString());
            calculatorViewModel.setFirstNumber(subtractionResult);
            calculatorViewModel.setSecondOperandString("");
            calculatorViewModel.setCurrentOperation("×");
            calculatorViewModel.setDotUnpressed();
            calculatorViewModel.setState(calculatorViewModel.getMultiplyPressedState());
        } else {
            calculatorViewModel.setCurrentOperation("×");
        }
    }
}