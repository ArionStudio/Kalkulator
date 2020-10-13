/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Adrian
 */
public final class ActionB extends NormalB {

    ActionB(int a, Kalkulator k) {
        super(a, k);
        if (a >= 3 && a <= 0) {
            style();
            actions();
        }
    }

    @Override
    protected void style() {
        super.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 1));
        super.setBackground(new Color(215, 215, 215));
        super.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
    }

    private String delLastChar(String text) {
        String x = k.calField.getText();
        if (x.length() > 1) {
            return x.substring(0, x.length() - 1);
        } else if (x.length() == 1) {
            return "0";
        } else {
            return x;
        }

    }

    private void doActoionAcions(String x) {
        switch (x) {
            case "C": {
                k.result = 0;
                k.howMany = 0;
                k.lastMethod = -1;
                k.lastMethodNO = 0;
                k.doMethod = false;
                k.doNumber = false;
                k.calField.setText("0");
                k.calcEx.setText("");
                break;
            }
            case "CE": {
                k.calField.setText("0");
                break;
            }
            case "⌫": {
                k.calField.setText(delLastChar(text));
                break;
            }
            default: {
                k.calField.setText("");
            }
        }
    }

    @Override
    protected void actions() {
        super.actions();
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                doActoionAcions(text);
                k.repaint();
            }
        });
    }
}
