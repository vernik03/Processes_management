package com.lab;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.*;


public class GUI extends JFrame {
    public JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
    public JSpinner spinner1 = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
    public JSpinner spinner2 = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
    public JButton button1 = new JButton("Start!");



    public GUI() {



//        JFrame frame = new JFrame("Процеси, потоки");
//        Box b1=Box.createVerticalBox();    // Создаем горизонтальный контейнер Box
//        Box b2=Box.createVerticalBox();    // Создаем горизонтальный контейнер Box
//        Box b3=Box.createVerticalBox();    // Создаем вертикальный контейнер Box
//        frame.add(b1);  // Добавляем в форму внешний горизонтальный прямоугольник
//        //b1.add(Box.createHorizontalStrut(20));    // Добавляем вертикальную рамку высотой 200
//        b1.add(slider);    // Добавить кнопку 1
//        //b1.add(Box.createHorizontalStrut(20));
//        // Добавляем в форму внешний горизонтальный прямоугольник
//        frame.add(b2);
//        b2.add(spinner1);
//        //b2.add(Box.createVerticalGlue());    // Добавляем горизонтальный клей
//        b2.add(spinner2);
//        //b2.add(Box.createHorizontalStrut(20));// Добавляем вложенный вертикальный контейнер Box
//        // Добавляем фиксированную область шириной 100 и высотой 20
//        //b2.add(Box.createRigidArea(new Dimension(100,20)));
//        // Добавляем в форму внешний горизонтальный прямоугольник
//        frame.add(b3);
//        b3.add(button1);
//        //b3.add(Box.createHorizontalStrut(20));
//        // Устанавливаем действие закрытия, заголовок, размер, положение и видимость окна
//        frame.setResizable(false);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setBounds(300, 100, 400, 300);
//        frame.setVisible(true);
//
//
//        ThreadLeft threadLeft = new ThreadLeft();
//        ThreadRight threadRight = new ThreadRight();
//
//        slider.setPaintTicks(true);
//        slider.setMajorTickSpacing(10);
//        slider.setPaintLabels(true);
//
//        button1.addActionListener((ActionEvent e) -> {
//            threadLeft.start();
//            threadRight.start();
//        });
//
//
//        spinner1.addChangeListener((ChangeEvent e) -> {
//            threadLeft.setPriority((int) spinner1.getValue());
//        });
//
//        spinner2.addChangeListener((ChangeEvent e) -> {
//            threadRight.setPriority((int) spinner2.getValue());
//        });

        JFrame frame=new JFrame("Пример программы Java");
        Box b1=Box.createVerticalBox();
        Box b0=Box.createVerticalBox();
        Box b2=Box.createHorizontalBox();
        Box b3=Box.createHorizontalBox();
        Box b4=Box.createHorizontalBox();
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
        b1.add(b3);
        b3.add(Box.createHorizontalStrut(20));
        b3.add(spinner1);
        b3.add(Box.createHorizontalStrut(20));
        b3.add(spinner2);
        b3.add(Box.createHorizontalStrut(20));
        b1.add(b4);
        b4.add(Box.createVerticalGlue());
        b4.add(button1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100,100,400,200);
        frame.setResizable(false);
        frame.setVisible(true);



        ThreadLeft threadLeft = new ThreadLeft();
        ThreadRight threadRight = new ThreadRight();
        threadLeft.setPriority((int) spinner1.getValue());
        threadRight.setPriority((int) spinner2.getValue());
        button1.addActionListener((ActionEvent e) -> {
            threadLeft.start();
            threadRight.start();
        });

        spinner1.addChangeListener((ChangeEvent e) -> {
            threadLeft.setPriority((int) spinner1.getValue());
            System.out.println(threadLeft.getPriority());
        });

        spinner2.addChangeListener((ChangeEvent e) -> {
            threadRight.setPriority((int) spinner2.getValue());
            System.out.println(threadRight.getPriority());
        });

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                threadLeft.run_flag = false;
                threadRight.run_flag = false;
//                threadLeft.interrupt();
//                threadRight.interrupt();
            }
        });

    }


    public class ThreadLeft extends Thread {
        public boolean run_flag = true;
        public void run() {
            while (run_flag) {
                try {
                if (slider.getValue() > 10) {
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
                if (slider.getValue() < 90){
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
