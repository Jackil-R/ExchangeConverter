package com.exchange.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class ComboBox<E> extends JComboBox<E> {

    public ComboBox(E[] items) {
        super(items);
        addActionListener(this);
    }

    public ComboBox(Vector<E> items) {
        super(items);
        addActionListener(this);
    }

    public ComboBox(ComboBoxModel<E> aModel) {
        super(aModel);
        addActionListener(this);
    }

    private boolean layingOut = false;

    @Override
    public void doLayout(){
        try{
            layingOut = true;
            super.doLayout();
        }finally{
            layingOut = false;
        }
    }

    @Override
    public Dimension getSize(){
        Dimension dim = super.getSize();
        if ( !layingOut ) {
            dim.width = Math.max(dim.width, getPreferredSize().width);
        }
        return dim;
    }
}
