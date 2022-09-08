package com.lab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GUI extends JFrame {
         public JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
    public JSpinner spinner1 = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
    public JSpinner spinner2 = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));


    public GUI() {
//            super("Процеси, потоки");
//            super.setBounds(300, 100, 400, 300);
//            super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            Container container = super.getContentPane();
//            container.setLayout(new GridLayout(3, 1, 2, 10));

            JFrame frame = new JFrame("Процеси, потоки");
            frame.setBounds(300, 100, 400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JButton button1 = new JButton("Start!");
            JPanel panel = new JPanel();
            panel.add(button1);



            slider.setPaintTicks(true);
            slider.setMajorTickSpacing(10);
            slider.setPaintLabels(true);

            button1.addActionListener((ActionEvent e) -> {
                synchronized (slider){
                    for (int i = 0; i < 10; i++) {
                        slider.setValue(slider.getValue()+1);
                    }
                }
            });


            panel.add(slider);
            panel.add(spinner1);
            panel.add(spinner2);


            frame.setContentPane(panel);


            frame.setVisible(true);

        }

    public class ThreadLeft extends Thread {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Left");
            }
        }
    }
}
