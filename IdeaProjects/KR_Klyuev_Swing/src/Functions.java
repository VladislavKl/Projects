import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;


public class Functions {

    public static void task1(JButton button, ArrayList<Rectangle> rectangles, JTextArea textArea) {
        button.addActionListener((e)-> {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Open file");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    rectangles.clear(); textArea.setText(null);
                    File file = fileopen.getSelectedFile();
                    try {
                        XMLReader.readTo(file.getAbsolutePath(), rectangles);
                    } catch (ClassCastException e1){
                        e1.printStackTrace();
                    }
                    button.setText("1");
                    JOptionPane.showMessageDialog(null, "Quantity of rectangles: "+rectangles.size(), "Reactangle Quantity", JOptionPane.OK_OPTION);
                }
                /*if (rectangles.size() != 0) {
                    Collections.sort(rectangles, new CombinedComparator());
                    StringBuffer outputBuffer= new StringBuffer();
                    for (Object tempObject :rectangles) {
                        outputBuffer.insert(outputBuffer.length(),tempObject.toString());
                        outputBuffer.insert(outputBuffer.length(),"\n");
                    }
                        textArea.setText(outputBuffer.toString());
                }
                */

        });
    }
/*
    public static void task2(JButton button, ArrayList<Rectangle> rectangles, JTextArea textArea) {
    textArea.setText(null);
    rectangles.clear();
    button.addActionListener((e)-> {
            if (rectangles.size() != 0) {
                Collections.sort(rectangles, Rectangle.NameComparator);
                String s = rectangles.get(0).getName();
                StringBuffer outputBuffer=new StringBuffer(s+"\n");
                for (Rectangle tempObject : rectangles) {
                    if (!s.equals(tempObject.getName())) {
                        outputBuffer.insert(outputBuffer.length(),tempObject.getName());
                        outputBuffer.insert(outputBuffer.length(),"\n");
                        s=tempObject.getName();
                    }
                }
                textArea.setText(outputBuffer.toString());
            }
            else {
                JOptionPane.showMessageDialog(null, "You must choose XML file!", "Notification", JOptionPane.OK_OPTION);
                return;
            }
    });
}

    public static void task3(JButton button, ArrayList<? extends Rectangle> rectangles, JTextArea textArea) {
        textArea.setText(null);
        rectangles.clear();
        button.addActionListener((e)-> {
                if (rectangles.size() != 0) {
                    Collections.sort(rectangles, Rectangle.CostComparator);
                    int temporateValue = rectangles.get(0).getCost();
                    int max=rectangles.get(0).getCaffeine();
                    StringBuffer outputBuffer=new StringBuffer(temporateValue+"-");
                    for (Rectangle tempObject : rectangles) {
                        if (temporateValue==tempObject.getCost()) {
                            if (max<tempObject.getCaffeine())
                                max=tempObject.getCaffeine();
                        }
                        else {
                            outputBuffer.insert(outputBuffer.length(),max); outputBuffer.insert(outputBuffer.length(),"\n");
                            temporateValue=tempObject.getCost();
                            outputBuffer.insert(outputBuffer.length(),temporateValue); outputBuffer.insert(outputBuffer.length(),"-");
                            max=tempObject.getCaffeine();
                        }
                    }
                    outputBuffer.insert(outputBuffer.length(), max); outputBuffer.insert(outputBuffer.length(),"\n");
                    textArea.setText(outputBuffer.toString());
                }
                else {
                    JOptionPane.showMessageDialog(null, "You must choose XML file!", "Notification", JOptionPane.OK_OPTION);
                    return;
                }
        });
    }

    public static void task4(JButton button,JTextField jTextField, ArrayList<? extends Rectangle> rectangles, JTextArea textArea) {
        textArea.setText(null);
        rectangles.clear();
        button.addActionListener((e) -> {
                if (rectangles.size() != 0) {
                    Collections.sort(rectangles, Rectangle.NameComparator);
                    int cost;
                    try {
                        cost = Integer.parseInt(jTextField.getText());
                    } catch (NumberFormatException e1){
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error: Incorrect input format", "Error", JOptionPane.OK_OPTION);
                        return;
                    }
                    StringBuffer outputBuffer=new StringBuffer();
                    //Check if file has elements of only one derived class
                    boolean flag=false;
                    for (Rectangle tempObject: rectangles) {
                        if(Integer.compare(tempObject.getCost(),cost)==0) {
                            flag=true;
                            break;
                        }
                    }


                    if(flag) {
                        for (Rectangle tempObject : rectangles) {
                            if (Integer.compare(tempObject.getCost(), cost) == 0) {
                                outputBuffer.insert(outputBuffer.length(), tempObject.getName());
                                outputBuffer.insert(outputBuffer.length(), "\n");
                            }
                        }
                        outputBuffer.insert(0, cost + ":\n");
                        outputBuffer.insert(outputBuffer.length(), textArea.getText());
                        textArea.setText(outputBuffer.toString());
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Not found", "Notification", JOptionPane.OK_OPTION);
                        return;
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "You must choose XML file!", "Notification", JOptionPane.OK_OPTION);
                    return;
                }
        });
    }
*/
}