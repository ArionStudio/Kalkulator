/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator;

import java.awt.*;
import java.awt.event.*;

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
        super.style();
        super.setBackground(new Color(215, 215, 215));
        super.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
    }

    private double round(double x) {
        return Math.round(x * Math.pow(10, 10)) / Math.pow(10, 10);
    }

    private String changeLastChar(String text, String modifayer) {
        return text.substring(0, text.length() - 1) + modifayer;
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
            default: {
                return 0;
            }
        }
    }

    private String lastChar(String x) {
        System.out.println(x.charAt(x.length() - 1) + "");
        return x.charAt(x.length() - 1) + "";
    }

    private String standardMath(int methodIdx, String aT) {
        String method[] = new String[] { "+", "-", "*", "/", "=" };
        if (k.lastMethodNO == 4) {
            k.result = 0;
            k.howMany = 0;
        }
        if (Double.parseDouble(aT) == 0 && k.lastMethod == 3) {
            k.calcEx.setText("Nie można dzielić przez 0!");
            k.lastMethod = 0;
            k.lastMethodNO = 0;
            k.doMethod = false;
            return "0";
        }
        if (k.doMethod && !k.doNumber && k.lastMethod < 4) {
            k.doMethod = true;
            k.doNumber = false;
            if (methodIdx == 4) {
                k.result = calculate(method[k.lastMethodNO], k.result, Double.parseDouble(aT));
                k.calcEx.setText(k.result + "");
                return round(k.result) + "";
            } else if (methodIdx == k.lastMethod && k.result != Double.parseDouble(aT)) {
                k.result = calculate(method[k.lastMethodNO], k.result, Double.parseDouble(aT));
                k.calcEx.setText(k.result + "");
                return round(k.result) + "";
            }

            if (k.calcEx.getText().length() > 0)
                if ((method[methodIdx] != lastChar(k.calcEx.getText()))) {
                    k.lastMethod = k.lastMethodNO = methodIdx;
                    k.calcEx.setText(changeLastChar(k.calcEx.getText(), method[methodIdx]));
                }
            return round(k.result) + "";
        }
        k.doMethod = true;
        k.doNumber = false;

        if (k.howMany == 0) {
            k.result = Double.parseDouble(aT);

            if (methodIdx != 4) {
                k.lastMethodNO = methodIdx;
                k.calcEx.setText(k.result + method[methodIdx]);

                k.howMany++;
            }
        } else {
            if (methodIdx == 4) {
                k.calcEx.setText("");
                k.howMany = 0;
            } else {
                k.calcEx.setText(k.calcEx.getText() + aT + method[methodIdx]);
                k.howMany++;
            }
            k.result = calculate(method[k.lastMethodNO], k.result, Double.parseDouble(aT));
        }

        if (methodIdx != 4) {
            k.lastMethodNO = methodIdx;
        }

        k.lastMethod = methodIdx;
        return round(k.result) + "";
    }

    private String methodStandard(String text) {
        if (k.calcEx.getText().equals("Nie można dzielić przez 0!")
                || k.calcEx.getText().equals("Nieprawidłowe dane wejściowe")) {
            k.calcEx.setText("");
        }
        String aT = k.calField.getText();
        switch (text) {
            case "%": {
                if (k.lastMethod > 4) {
                    k.calcEx.setText(k.oldText + (Double.parseDouble(aT) / 100) + "");
                } else {
                    k.oldText = k.calcEx.getText();
                    k.calcEx.setText(k.calcEx.getText() + (Double.parseDouble(aT) / 100) + "");
                }
                k.doMethod = true;
                k.doNumber = true;
                return round(Double.parseDouble(aT)) / 100 + "";
            }
            case "1/x": {
                if (Double.parseDouble(aT) == 0) {
                    k.calcEx.setText("Nie można dzielić przez 0!");
                    return "0";
                }
                if (k.lastMethod > 4) {
                    k.calcEx.setText(k.oldText + "1/" + Double.parseDouble(aT) + "");
                } else {

                    k.oldText = k.calcEx.getText();
                    k.calcEx.setText(k.calcEx.getText() + "1/" + Double.parseDouble(aT) + "");
                }

                k.doMethod = true;
                k.doNumber = true;
                return round(1 / Double.parseDouble(aT)) + "";
            }
            case "x²": {
                if (k.lastMethod > 4) {
                    k.calcEx.setText(k.oldText + "sqr(" + Double.parseDouble(aT) + ")");
                } else {

                    k.oldText = k.calcEx.getText();
                    k.calcEx.setText(k.calcEx.getText() + "sqr(" + Double.parseDouble(aT) + ")");
                }

                k.doMethod = true;
                k.doNumber = true;
                return round(Math.pow(Double.parseDouble(aT), 2)) + "";
            }
            case "√x": {
                if (Double.parseDouble(aT) < 0) {
                    k.calcEx.setText("Nieprawidłowe dane wejściowe");
                    return "0";
                }
                if (k.lastMethod > 4) {
                    k.calcEx.setText(k.oldText + "sqrt(" + Double.parseDouble(aT) + ")");
                } else {

                    k.oldText = k.calcEx.getText();
                    k.calcEx.setText(k.calcEx.getText() + "sqrt(" + Double.parseDouble(aT) + ")");
                }

                k.doMethod = true;
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
            case "=": {
                return standardMath(4, aT);
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
        super.actions();
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (k.powerCalc) {
                    calcInputEnd(text);
                } else {
                    return;
                }
                k.repaint();
            }
        });
    }
}
