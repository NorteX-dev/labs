package com.nortexdev.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClickListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent ev) {
        System.out.println("Hello");
    }
}
