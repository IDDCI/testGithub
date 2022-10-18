/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testgithub;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author DDC
 */
public class Main {
    public static void main(final String[] args) {
        final JFrame frame = new JFrame("GUI Game: Virtual Pet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel panel = new Panel();
        frame.getContentPane().setBackground(Color.PINK);
        frame.getContentPane().add(panel);
        frame.setSize(1000, 700);
        frame.setVisible(true);
        frame.setResizable(false);
        
        
    }
}
