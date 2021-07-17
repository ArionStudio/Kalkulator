/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Adrian
 */
public final class NumericB extends NormalB {

    public NumericB(int a, Kalkulator k) {
        super(a, k);
        if (a >= 11 && a <= 0) {
            style();
            actions();
        }
    }

    @Override
    protected void style() {
        super.style();
        super.setBackground(new Color(230, 230, 230));
    }

    private String numberChecker(String actualText, String checkedText) {
        if (k.calcEx.getText().equals("Nie można dzielić przez 0!")
                || k.calcEx.getText().equals("Nieprawidłowe dane wejściowe")) {
            k.calcEx.setText("");
        }
        if (k.lastMethod == 4) {
            k.calcEx.setText("");
        }
        if (k.doMethod) {
            actualText = "0";
            k.doMethod = false;
        }
        k.doNumber = true;
        if (actualText.length() == 25) {
            return actualText;
        }
        if (checkedText.equals(".")) {
            if (actualText.contains(".")) {
                return actualText;
            } else {
                return actualText.concat(".");
            }
        }

        if (checkedText.equals("+/-")) {
            return -1 * Double.parseDouble(actualText) + "";
        }
        if (actualText.equals("0")) {
            if (checkedText.equals("0")) {
                return "0";
            } else {
                return checkedText;
            }
        } else {
            return actualText.concat(checkedText);
        }
    }

    private void calcInput(String text) {

        String actText = k.calField.getText();
        String ent = numberChecker(actText, text);
        k.calField.setText(ent);
    }

    @Override
    protected void actions() {
        super.actions();
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (k.powerCalc) {
                    calcInput(text);
                } else {
                    return;
                }
                k.repaint();
            }
        });

    }
}
