/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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

    private void methodStyle() {
        setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 1));
        setBackground(new Color(215, 215, 215));
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

    // private String complite(String aT) {
    // int index = 0;
    // while (nextMethod() > -1) {
    // int x = nextMethod();
    // k.History.set(x - 1, calcFun(Double.parseDouble(k.History.get(x - 1)),
    // Double.parseDouble(k.History.get(x + 1)), k.History.get(x)) + "");
    // k.History.remove(x);
    // k.History.remove(x);
    // }
    // k.calcEx.setText(k.calcEx.getText() + aT);
    // String result = k.History.get(0);
    // k.History = new ArrayList<>();
    // return result;
    // }

    private double round(double x) {
        return Math.round(x * Math.pow(10, 10)) / Math.pow(10, 10);
    }

    private String sM(String m, double x, double y) {
        switch (m) {
            case "+": {
                return (x + y) + "";
            }
            case "-": {
                return (x - y) + "";
            }
            case "*": {
                return (x * y) + "";
            }
            case "/": {
                return (x / y) + "";
            }
            case "%": {
                return (x % y) + "";
            }
            default: {
                return "0";
            }
        }
    }

    private String standardMath(int metIdx, String aT) {
        String method[] = new String[] { "+", "-", "*", "/", "%", "=" };
        if (k.numberClicked && k.buffor.size() == 0) {
            k.numberClicked = false;

            k.calcEx.setText(k.calcEx.getText() + aT + method[metIdx]);
            if (k.buffor.size() == 0) {
                k.buffor.add(1.0);
            }
            k.buffor.add(Double.parseDouble(aT));
            k.afterMethod = true;
            if (metIdx == 5) {
                k.afterCompare = true;
            } else {
                k.lastMehotd = metIdx;
            }
            return aT;
        } else {
            if (k.numberClicked) {
                k.buffor.add(Double.parseDouble(aT));
                System.out.println(aT);
                k.numberClicked = false;
            }
            if ((k.lastMehotd == metIdx && k.buffor.size() > 2) || metIdx == 5) {
                if (k.buffor.get(0) == 1) {
                    k.buffor.set(0, 1.0);
                    k.afterMethod = false;
                    k.calcEx.setText(k.calcEx.getText() + Double.parseDouble(aT));
                    double x, y;
                    if (k.buffor.size() == 2) {
                        x = k.buffor.get(1);
                        y = k.buffor.get(1);
                    } else {
                        x = k.buffor.get(1);
                        y = k.buffor.get(2);
                    }
                    k.buffor = new ArrayList<Double>();
                    if (metIdx == 5) {
                        k.afterCompare = true;
                        return sM(method[k.lastMehotd], x, y);
                    } else {
                        return sM(method[metIdx], x, y);
                    }

                } else {
                    if (k.afterCompare) {
                        k.calcEx.setText(aT + "=");
                    }
                    return aT;
                }

            } else {
                System.out.println(k.afterCompare);
                if (k.afterCompare) {
                    k.lastMehotd = metIdx;
                    k.afterMethod = true;
                    k.afterCompare = false;
                    k.buffor = new ArrayList<Double>();
                    k.buffor.add(1.0);
                    k.buffor.add(Double.parseDouble(aT));
                    k.calcEx.setText(aT + method[metIdx]);
                    return aT;
                }
                k.lastMehotd = metIdx;
                String l = k.calcEx.getText();
                l = l.substring(0, l.length() - 1) + method[metIdx];
                k.calcEx.setText(l);
                return aT;
            }
        }
    }

    private String methodStandard(String text) {
        if (k.afterCompare) {
            if (text == "=") {
                return k.calField.getText();
            }
        }
        String aT = k.calField.getText();
        switch (text) {
            case "1/x": {
                if(k.afterMethod) return aT;
                if (k.afterCompare) {
                    k.calcEx.setText("1/" + Double.parseDouble(aT) + "");
                } else {
                    k.calcEx.setText(k.calcEx.getText() + "1/" + Double.parseDouble(aT) + "");
                }
                return round(1 / Double.parseDouble(aT)) + "";
            }
            case "sqr(x)": {
                if (k.afterCompare) {
                    k.calcEx.setText("sqr(" + Double.parseDouble(aT) + ")");
                } else {
                    k.calcEx.setText(k.calcEx.getText() + "sqr(" + Double.parseDouble(aT) + ")");
                }

                return round(Math.pow(Double.parseDouble(aT), 2)) + "";
            }
            case "sqrt(x)": {
                if(k.afterMethod) return aT;
                if (k.afterCompare) {
                    k.calcEx.setText("sqrt(" + Double.parseDouble(aT) + ")");
                } else {
                    k.calcEx.setText(k.calcEx.getText() + "sqrt(" + Double.parseDouble(aT) + ")");
                }

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
