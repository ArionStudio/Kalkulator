/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.function.Function;

import javax.swing.*;

/**
 *
 * @author Adrian
 */
public final class MethodB extends NormalB {

    MethodB(int a, Kalkulator k) {
        super(a, k);
        if (a <= 0 && a >= 9) {

            style();
            actions();
        }
    }

    @Override
    protected void style() {
        super.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 1));
        super.setBackground(new Color(215, 215, 215));
    }

    // private int nextMethod() {
    // String arr[] = new String[] { "*", "/", "%", "+", "-" };
    // for (int i = 0; i < 6; i++) {
    // for (int j = 1; j < k.History.size(); j += 2) {
    // if (arr[i].equals(k.History.get(j))) {
    // return j;
    // }
    // }
    // }
    // return -1;
    // }

    private String calcFun(double a, double b, String c) {
        switch (c) {
            case "+": {
                return round(a + b) + "";
            }
            case "-": {
                return round(a - b) + "";
            }
            case "*": {
                return round(a * b) + "";
            }
            case "/": {
                if (b == 0)
                    return "Nie wolno przez 0!";
                return round(a / b) + "";
            }
            case "%": {
                return round(a % b) + "";
            }
            default: {
                return "0";
            }
        }
    }

    private double round(double x) {
        return Math.round(x * Math.pow(10, 10)) / Math.pow(10, 10);
    }

    private double calculate(String m, double x, double y) {
        switch (m) {
            case "+": {
                return (x + y);
            }
            case "-": {
                return (x - y);
            }
            case "*": {
                return (x * y);
            }
            case "/": {
                return (x / y);
            }
            case "%": {
                return (x % y);
            }
            default: {
                return 0;
            }
        }
    }

    private String lastChar(String x) {
        return x.charAt(x.length() - 1) + "";
    }

    private String changeLastChar(String text, String modifier) {
        String x = k.calcEx.getText();
        x = x.substring(0, x.length() - 1) + modifier;
        return x;
    }

    private String standardMath(int methodIdx, String aT) {
        String method[] = new String[] { "+", "-", "*", "/", "%", "=" };
        if (k.doMethod && !k.doNumber && k.lastMethod > 5) {
            k.doMethod = true;
            k.doNumber = false;
            if (methodIdx == 5) {
                k.result = calculate(method[k.lastMethodNO], k.result, Double.parseDouble(aT));
                k.calcEx.setText(k.result + "");
                return k.result + "";
            }
            if (k.calcEx.getText().length() > 0)
                if ((method[methodIdx] != lastChar(k.calcEx.getText())))
                    k.calcEx.setText(changeLastChar(k.calcEx.getText(), method[methodIdx]));

            return k.result + "";
        }
        k.doMethod = true;
        k.doNumber = false;

        if (k.howMany == 0) {
            k.result = Double.parseDouble(aT);
            k.calcEx.setText(k.result + method[methodIdx]);
            if (methodIdx != 5) {
                k.lastMethodNO = methodIdx;
            }
            k.howMany++;
        } else {
            if (methodIdx == 5) {
                k.calcEx.setText("");
                k.howMany = 0;
            } else {
                k.calcEx.setText(k.calcEx.getText() + aT + method[methodIdx]);
                k.howMany++;
            }
            k.result = calculate(method[k.lastMethodNO], k.result, Double.parseDouble(aT));
        }
        if (methodIdx != 5) {
            k.lastMethodNO = methodIdx;
        }
        k.lastMethod = methodIdx;
        return k.result + "";
    }

    private String methodStandard(String text) {
        if (k.lastMethod == 5) {
            if (k.lastMethod == 6) {
                text = "1/x";
            } else if (k.lastMethod == 7) {
                text = "sqr(x)";
            } else if (k.lastMethod == 8) {
                text = "sqrt(x)";
            }
        }
        String aT = k.calField.getText();
        switch (text) {
            case "1/x": {
                if (k.lastMethod == 6 || k.lastMethod == 5) {
                    k.calcEx.setText("1/" + Double.parseDouble(aT) + "");
                } else {
                    k.calcEx.setText(k.calcEx.getText() + "1/" + Double.parseDouble(aT) + "");
                }
                k.lastMethod = 6;
                k.doNumber = true;
                return round(1 / Double.parseDouble(aT)) + "";
            }
            case "sqr(x)": {
                if (k.lastMethod == 7 || k.lastMethod == 5) {
                    k.calcEx.setText("sqr(" + Double.parseDouble(aT) + ")");
                } else {
                    k.calcEx.setText(k.calcEx.getText() + "sqr(" + Double.parseDouble(aT) + ")");
                }
                k.lastMethod = 7;
                k.doNumber = true;
                return round(Math.pow(Double.parseDouble(aT), 2)) + "";
            }
            case "sqrt(x)": {
                if (k.lastMethod == 8 || k.lastMethod == 5) {
                    k.calcEx.setText("sqrt(" + Double.parseDouble(aT) + ")");
                } else {
                    k.calcEx.setText(k.calcEx.getText() + "sqrt(" + Double.parseDouble(aT) + ")");
                }
                k.lastMethod = 8;
                k.doNumber = true;
                return round(Math.sqrt(Double.parseDouble(aT))) + "";
            }
            case "➕": {
                return standardMath(0, aT);
            }
            case "➖": {
                return standardMath(1, aT);
            }
            case "✖": {
                return standardMath(2, aT);
            }
            case "➗": {
                return standardMath(3, aT);
            }
            case "%": {
                return standardMath(4, aT);
            }
            case "=": {
                return standardMath(5, aT);
            }
            default: {
                return "0";
            }
        }
    }

    private void calcInputEnd(String text) {
        String ent = methodStandard(text);
        k.calField.setText(ent);
    }

    @Override
    protected void actions() {
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                calcInputEnd(text);
                k.repaint();
            }
        });
    }
}
