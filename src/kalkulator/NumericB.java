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

    private String lastChar(String text) {
        return text.charAt(text.length() - 1) + "";
    }

    private String numberChecker(String actualText, String checkedText) {
        k.numberClicked = true;
        if (k.afterCompare == true) {
            k.calcEx.setText("");
            actualText = "0";
            k.afterCompare = false;
        }
        if (k.afterMethod == true) {
            actualText = "0";
        }
        if (actualText.equals("Error")) {
            actualText = "0";
        }
        if (actualText.length() == 16) {
            return actualText;
        }
        String lAText = lastChar(actualText);
        if (checkedText.equals(",")) {
            if (actualText.contains(",")) {
                return actualText;
            }
            if (lAText.equals(",")) {
                return actualText;
            } else {
                return actualText.concat(",");
            }
        }

        if (checkedText.equals("+/-")) {
            if (actualText.charAt(0) != '-') {
                return "-".concat(actualText);
            } else {
                return actualText.substring(1);
            }
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
                calcInput(text);
                k.repaint();
            }
        });

    }
}
