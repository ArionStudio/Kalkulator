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

public class Kalkulator extends JFrame implements ActionListener {

    Boolean powerCalc = true;

    Container cont;

    JPanel mainPanel;

    JPanel topPanel;
    JButton endButton;
    JLabel titleLabel;

    JPanel inputPanel;

    JLabel calcEx;
    JLabel calField;
    String oldText;
    double result = 0;
    int howMany = 0;
    int lastMethod = -1;
    int lastMethodNO = 0;
    boolean doMethod = false;
    boolean doNumber = false;

    JPanel numberPanel;
    NumericB[] calcNumber = new NumericB[12];
    MethodB[] calcMethod = new MethodB[10];
    ActionB[] calcAction = new ActionB[3];

    public Kalkulator() {
        initFrame();
    }

    private void initFrame() {
        setTitle("Kalkulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(430, 605);
        setResizable(false);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        initPanels();
        setVisible(true);

    }

    private void initPanels() {
        cont = getContentPane();
        cont.setBackground(Color.WHITE);
        cont.setFont(new Font("Arial", Font.PLAIN, 25));

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        cont.add(mainPanel);

        topPanel = new JPanel();
        inputPanel = new JPanel();
        numberPanel = new JPanel();

        mainPanel.add(topPanel);
        mainPanel.add(inputPanel);
        mainPanel.add(numberPanel);

        // panel nagłówkowy

        topPanel.setBackground(Color.white);
        topPanel.setPreferredSize(new Dimension(400, 45));

        titleLabel = new JLabel("Standardowy", SwingConstants.LEFT);
        topPanel.add(titleLabel);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        endButton = new JButton("◎");
        endButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                power();
                if (powerCalc) {
                    endButton.setText("◉");
                } else {
                    endButton.setText("◎");
                }
                repaint();
            }
        });

        endButton.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
        setToFlatStyle(endButton);

        topPanel.add(endButton);

        // panel wejscowy

        inputPanel.setBackground(new Color(230, 230, 230));
        inputPanel.setPreferredSize(new Dimension(400, 100));
        inputPanel.setBorder(BorderFactory.createLineBorder(Color.white, 3));
        calcEx = new JLabel("", SwingConstants.RIGHT);

        calField = new JLabel("0", SwingConstants.RIGHT);

        calField.setFont(new Font("Arial", Font.PLAIN, 13));
        calcEx.setPreferredSize(new Dimension(370, 20));
        calField.setPreferredSize(new Dimension(370, 80));

        calcEx.setVerticalTextPosition(SwingConstants.BOTTOM);
        calField.setVerticalTextPosition(SwingConstants.BOTTOM);

        inputPanel.add(calcEx);
        inputPanel.add(calField);

        // panel numeryczny i akcji
        numberPanel.setPreferredSize(new Dimension(400, 400));
        numberPanel.setLayout(new GridLayout(6, 3));
        numberPanel.setBackground(Color.WHITE);

        addButton();

        numberPanel.add(calcMethod[0]);
        numberPanel.add(calcAction[0]);
        numberPanel.add(calcAction[1]);
        numberPanel.add(calcAction[2]);

        numberPanel.add(calcMethod[1]);
        numberPanel.add(calcMethod[2]);
        numberPanel.add(calcMethod[3]);
        numberPanel.add(calcMethod[4]);

        numberPanel.add(calcNumber[0]);
        numberPanel.add(calcNumber[1]);
        numberPanel.add(calcNumber[2]);
        numberPanel.add(calcMethod[5]);

        numberPanel.add(calcNumber[3]);
        numberPanel.add(calcNumber[4]);
        numberPanel.add(calcNumber[5]);
        numberPanel.add(calcMethod[6]);

        numberPanel.add(calcNumber[6]);
        numberPanel.add(calcNumber[7]);
        numberPanel.add(calcNumber[8]);
        numberPanel.add(calcMethod[7]);

        numberPanel.add(calcNumber[9]);
        numberPanel.add(calcNumber[10]);
        numberPanel.add(calcNumber[11]);
        numberPanel.add(calcMethod[8]);
        power();
    }

    private void setToFlatStyle(JButton button) {
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }

    private void power() {
        this.powerCalc = !this.powerCalc;
        if (!powerCalc) {
            calcEx.setText("");
            calField.setText("0");
            result = 0;
            howMany = 0;
            lastMethod = -1;
            lastMethodNO = 0;
            doMethod = false;
            doNumber = false;
        }
        calField.setEnabled(this.powerCalc);
        for (ActionB act : this.calcAction) {
            act.setEnabled(this.powerCalc);
        }
        for (NumericB num : this.calcNumber) {
            num.setEnabled(this.powerCalc);
        }
        for (MethodB mee : this.calcMethod) {
            mee.setEnabled(this.powerCalc);
        }
    }

    private void addButton() {
        int a = 0;
        for (int i = 0; i < 12; i++) {
            calcNumber[i] = new NumericB(a, this);
            a++;
        }
        for (int i = 0; i < 10; i++) {
            calcMethod[i] = new MethodB(a, this);
            a++;
        }

        a--;
        for (int i = 0; i < 3; i++) {
            calcAction[i] = new ActionB(a, this);
            a++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
    }

    public static void main(String[] args) {
        Kalkulator kalkulator = new Kalkulator();
    }
}
