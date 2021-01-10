package com.exchange;

import com.exchange.ui.MainFrame;

import javax.swing.*;

public class main {

    public static void main(String[] args) {


        MainFrame frame = new MainFrame();
        frame.setTitle("Currency Exchange Converter");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800, 400);
        frame.setPreferredSize(frame.getSize());

    }
}
