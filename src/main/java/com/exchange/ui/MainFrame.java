package com.exchange.ui;

import com.exchange.converter.FreeCurrencyConverter;

import javax.swing.*;

public class MainFrame extends JFrame{
    private final GlobalPanel globalFrama;

    public MainFrame(){
        globalFrama = new GlobalPanel();
        this.setContentPane(globalFrama);
        this.pack();
        this.setLocationRelativeTo(null);
    }

}
