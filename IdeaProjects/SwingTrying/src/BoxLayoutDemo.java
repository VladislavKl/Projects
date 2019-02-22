import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.*;


public class BoxLayoutDemo {

    public static void addComponentsToPane(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        JButton button = new JButton("Choose file");
        JLabel label = new JLabel("Chosen file");
        pane.add(label);
        pane.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    label.setText(file.getAbsolutePath());
                }
            }
        });
        final String TEXT = "You can get a blog started in less time than "
                + " it takes you to read this sentence. All you need is an email "
                + " address. You’ll get your own WordPress.com address "
                + " (like you.wordpr\ness.com), \na selection of great free \n"
                + " and customizable designs for your blog (we call them themes), \n"
                + " 3 gigabytes of file sto\nrage (that’s about 2,500 pictures!) \n"
                + " and all the other great features listed here. \n"
                + " You can blog\n as much as you want for free, \n"
                + " your blog can be public to the world or private \n"
                + " for just your friends, and our premium features \n"
                + "are completely optional.";

        pane.add(new JLabel("1."));

        JTextArea textArea = new JTextArea(10,20);
        textArea.setText(TEXT);
        textArea.setCaretPosition(0);

        JScrollPane jscr1=new JScrollPane(textArea);
        jscr1.createVerticalScrollBar();

        pane.add(jscr1);

        pane.add(new JLabel("2."));
        JScrollPane jscr2=new JScrollPane();
        jscr2.createVerticalScrollBar();
        pane.add(jscr2);

        pane.add(new JLabel("3."));

        JScrollPane jscr3=new JScrollPane();
        jscr2.createVerticalScrollBar();
        pane.add(jscr3);


    }

    private static void createAndShowGUI() {
        // Создание фрейма
        JFrame frame = new JFrame("BoxLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());

        frame.setPreferredSize(new Dimension(720,480));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[ ] args) {
        // запустить приложение
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                createAndShowGUI();
            }
        });
    }
}

