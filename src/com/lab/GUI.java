package com.lab;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.MIN_PRIORITY;


public class GUI extends JFrame {
    public JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
    public JButton button_left_start = new JButton("Start!");
    public JButton button_right_start = new JButton("Start!");
    public JButton button_left_stop = new JButton("Stop!");
    public JButton button_right_stop = new JButton("Stop!");

    public int semafore = 0;

    public GUI() {

        JFrame frame=new JFrame("Процеси, потоки з семафором");
        Box b1=Box.createVerticalBox();
        Box b0=Box.createVerticalBox();
        Box b2=Box.createHorizontalBox();
        Box b4=Box.createHorizontalBox();
        Box b5=Box.createHorizontalBox();

        frame.add(b1);
        b0.add(Box.createVerticalStrut(10));
        b1.add(b2);
        b2.add(Box.createHorizontalStrut(20));
        b2.add(slider);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(10);
        slider.setPaintLabels(true);
        b2.add(Box.createHorizontalStrut(20));
        b2.add(Box.createVerticalGlue());
        b1.add(b0);

        b1.add(b4);
        b4.add(Box.createVerticalGlue());
        b4.add(Box.createHorizontalStrut(20));
        b4.add(button_left_start);
        b4.add(Box.createHorizontalStrut(20));
        b4.add(button_right_start);
        b4.add(Box.createHorizontalStrut(20));
        b4.add(Box.createVerticalGlue());

        b1.add(b5);
        b5.add(Box.createVerticalGlue());
        b5.add(Box.createHorizontalStrut(20));
        b5.add(button_left_stop);
        b5.add(Box.createHorizontalStrut(20));
        b5.add(button_right_stop);
        b5.add(Box.createHorizontalStrut(20));


        ThreadLeft threadLeft = new ThreadLeft();
        ThreadRight threadRight = new ThreadRight();

        threadLeft.start();
        threadRight.start();

        button_left_start.addActionListener((ActionEvent e) -> {
            if (semafore == 0){
                threadLeft.setPriority(MIN_PRIORITY);
                semafore = 1;
            }
        });

        button_right_start.addActionListener((ActionEvent e) -> {
            if (semafore == 0){
                threadRight.setPriority(MAX_PRIORITY);
                semafore = 2;
            }
        });

        button_left_stop.addActionListener((ActionEvent e) -> {
            if (semafore == 1){
                threadLeft.setPriority(MIN_PRIORITY);
                semafore = 0;
            }
        });

        button_right_stop.addActionListener((ActionEvent e) -> {
            if (semafore == 2){
                threadRight.setPriority(MIN_PRIORITY);
                semafore = 0;
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                threadLeft.run_flag = false;
                threadRight.run_flag = false;
//                threadLeft.interrupt();
//                threadRight.interrupt();
            }
        });


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100,100,400,200);
        frame.setResizable(false);
        frame.setVisible(true);


    }


    public class ThreadLeft extends Thread {
        public boolean run_flag = true;
        public void run() {
            while (run_flag) {
                try {
                if (slider.getValue() > 10 && semafore == 1) {
                    synchronized (slider) {
                        slider.setValue(slider.getValue() - 1);
                            sleep(10);
                    }
                }
                else {
                        sleep(10);
                }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class ThreadRight extends Thread {
        public boolean run_flag = true;
        public void run() {
            while (run_flag) {
                try {
                if (slider.getValue() < 90 && semafore == 2){
                    synchronized (slider) {
                        slider.setValue(slider.getValue() + 1);
                            sleep(10);
                    }
                }
                else {
                        sleep(10);
                }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
