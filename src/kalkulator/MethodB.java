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
<<<<<<< HEAD
            methodStyle();
=======
            style();
>>>>>>> 0719de4e67007fb77c4cb430f7a5b73a7246ee18
            actions();
        }
    }

<<<<<<< HEAD
    private void methodStyle() {
        setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 1));
        setBackground(new Color(215, 215, 215));
=======
    @Override
    protected void style() {
        super.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 1));
        super.setBackground(new Color(215, 215, 215));
>>>>>>> 0719de4e67007fb77c4cb430f7a5b73a7246ee18
    }

    private int nextMethod() {
        String arr[] = new String[] { "*", "/", "%", "+", "-" };
        for (int i = 0; i < 6; i++) {
            for (int j = 1; j < k.History.size(); j += 2) {
                if (arr[i].equals(k.History.get(j))) {
                    return j;
                }
            }
        }
        return -1;
    }

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

    private double standardMath(String method, double x, double y) {
        switch (method) {
            case "+": {
                return x + y;
            }
            case "-": {
                return x - y;
            }
            case "*": {
                return x * y;
            }
            case "/": {
                return x / y;
            }
            default: {
                return 0;
            }
        }
    }

    private String methodStandard(String text) {
        if (k.afterCompare) {
            if (text == "=") {
                return k.calField.getText();
            } else {
                k.calcEx.setText("");
                k.afterCompare = false;
            }
        }
        String aT = k.calField.getText();
        switch (text) {
            case "1/x": {
                k.calcEx.setText(k.calcEx.getText() + "1/" + Double.parseDouble(aT) + "");
                k.History.add(aT);
                return round(1 / Double.parseDouble(aT)) + "";
            }
            case "sqr(x)": {
                k.calcEx.setText(k.calcEx.getText() + "sqr(" + Double.parseDouble(aT) + ")");
                k.History.add(aT);
                return round(Math.pow(Double.parseDouble(aT), 2)) + "";
            }
            case "sqrt(x)": {
                k.calcEx.setText(k.calcEx.getText() + "sqrt(" + Double.parseDouble(aT) + ")");
                k.History.add(aT);
                return k.History.get(k.History.size()) + Double.parseDouble(aT);
            }
            case "➕": {
                if (k.afterMethod) {
                    k.calcEx.setText(k.calcEx.getText() + "sqrt(" + Double.parseDouble(aT) + ")");
                    k.History.add(aT);
                    return k.History.get(k.History.size() - 1)
                            + Double.parseDouble(k.History.get(k.History.size() - 2));
                } else {
                    k.calcEx.setText(k.calcEx.getText() + "sqrt(" + Double.parseDouble(aT) + ")");
                    k.History.add(aT);
                    return k.History.get(k.History.size() - 1)
                            + Double.parseDouble(k.History.get(k.History.size() - 2));
                }
            }
            case "➖": {
                k.calcEx.setText(k.calcEx.getText() + "sqrt(" + Double.parseDouble(aT) + ")");
                k.History.add(aT);
                return k.History.get(k.History.size()) + Double.parseDouble(aT);
            }
            case "✖": {
                k.calcEx.setText(k.calcEx.getText() + "sqrt(" + Double.parseDouble(aT) + ")");
                k.History.add(aT);
                return k.History.get(k.History.size()) + Double.parseDouble(aT);
            }
            case "➗": {
                k.calcEx.setText(k.calcEx.getText() + "sqrt(" + Double.parseDouble(aT) + ")");
                k.History.add(aT);
                return k.History.get(k.History.size()) + Double.parseDouble(aT);
            }
            case "%": {
                k.calcEx.setText(k.calcEx.getText() + "sqrt(" + Double.parseDouble(aT) + ")");
                k.History.add(aT);
                return k.History.get(k.History.size()) + Double.parseDouble(aT);
            }
            case "=": {
                k.afterCompare = true;
                k.History.add(aT);
                //return sta(aT);
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
