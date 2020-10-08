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
        return text.charAt(text.length() - 1) + "";
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

    private String standardMath(int methodIdx, String aT) {
        String method[] = new String[] { "+", "-", "*", "/", "%", "=" };
        if (k.lastMethodNO == 5) {
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
        if (k.doMethod && !k.doNumber && k.lastMethod < 5) {
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
                if (k.lastMethod > 4) {
                    k.calcEx.setText("1/" + Double.parseDouble(aT) + "");
                } else {
                    k.calcEx.setText(k.calcEx.getText() + "1/" + Double.parseDouble(aT) + "");
                }
                k.lastMethod = 6;
                k.doNumber = true;
                return round(1 / Double.parseDouble(aT)) + "";
            }
            case "x²": {
                if (k.lastMethod > 4) {
                    k.calcEx.setText("sqr(" + Double.parseDouble(aT) + ")");
                } else {
                    k.calcEx.setText(k.calcEx.getText() + "sqr(" + Double.parseDouble(aT) + ")");
                }
                k.lastMethod = 7;
                k.doNumber = true;
                return round(Math.pow(Double.parseDouble(aT), 2)) + "";
            }
            case "√x": {
                if (k.lastMethod > 4) {
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
        super.actions();
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                calcInputEnd(text);
                k.repaint();
            }
        });
    }
}
