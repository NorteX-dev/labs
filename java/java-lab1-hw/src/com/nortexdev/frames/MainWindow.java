package com.nortexdev.frames;

import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("Window");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("hello");
        this.add(button);
    }
}
