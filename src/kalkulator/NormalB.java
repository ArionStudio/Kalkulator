/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Adrian
 */
public abstract class NormalB extends JButton implements ActionListener{
    protected Kalkulator k;
    String text;
    private String values[] = new String[]{
        "7", "8", "9", 
        "4", "5", "6", 
        "1", "2", "3", 
        "+/-", "0", ",",
        "%", "1/x", "sqr(x)", "sqrt(x)", 
        "➗", "✖", "➖", "➕", "=",
        "C", "CE", "<[x]"
            
    };
    public NormalB(int a, Kalkulator k){
        super();
        this.k = k;
        text = values[a];
        setText(this.text);
        style();
        actions();
    }
    private void thisHoverStyleOn(){
        setBorder(BorderFactory.createLineBorder(this.getBackground().brighter(), 1));
        setBackground(this.getBackground().darker());
    }
    private void thisHoverStyleOff(){
        setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 1));
        setBackground(this.getBackground().brighter());
    }
    private void style() {
        setBorderPainted(true);
        setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 1));
        setFocusPainted(false);
        setBackground(new Color(230, 230, 230));
        setOpaque(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    
    protected void actions(){
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent me){
                thisHoverStyleOn();
                k.repaint();
            }
            @Override
            public void mouseExited(MouseEvent me){
                thisHoverStyleOff();
                k.repaint();
            }
        });
        addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent evt){
        Object source = evt.getSource();
        
        
    }
    @Override
    public String getText(){
        return super.getText();
    }
    
}
