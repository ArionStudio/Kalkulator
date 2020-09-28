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
public final class ActionB extends NormalB{

    ActionB(int a, Kalkulator k){
        super(a, k);
        if(a >= 3 && a <=0) {
            actionStyle();
            actions();
        }
    }

    private void actionStyle(){
        setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 1));
        setBackground(new Color(215, 215, 215));
        
    }
    
    private void doActoionAcions(String x){
        
    }
    @Override
    protected void actions(){
        super.actions();
        super.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                doActoionAcions(text);
                k.repaint();
            }
        });
    }
}
