import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;


public class TestingHashSet {
    private static HashSet<Integer> hSet;

    public static void main(String[] args) {

        JFrame frame=new JFrame("HashSet");
        frame.setLayout(new GridLayout());
        frame.setSize(900, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel jPanel1=new JPanel();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        JButton btnIsEmpty=new JButton("Is Empty");
        JButton btnSize=new JButton("Size");
        JButton btnAdd=new JButton("Add");
        JButton btnAddAll=new JButton("Add All");
        JButton btnRemove=new JButton("Remove");
        JButton btnRemoveAll=new JButton("Remove All");
        JButton btnContains=new JButton("Contains");
        JButton btnClear=new JButton("Clear");
        JButton btnClone=new JButton("Clone");
        JButton btnIterator=new JButton("Iterator");
        JButton btnDisjoint=new JButton("Disjpoint");
        JButton btnMin=new JButton("Min");
        JButton btnMax=new JButton("Max");
        JButton btnRetainAll=new JButton("Retain All");
        JButton btnToArray=new JButton("To array");
        JButton btnAddFromFile=new JButton("Add from the file");
        JButton btnSaveToFile=new JButton("Save to the file");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        jPanel1.setLayout(new FlowLayout());
        jPanel1.add(btnAdd);
        jPanel1.add(btnAddAll);
        jPanel1.add(btnIsEmpty);
        jPanel1.add(btnSize);
        jPanel1.add(btnRemove);
        jPanel1.add(btnRemoveAll);
        jPanel1.add(btnRetainAll);
        jPanel1.add(btnContains);
        jPanel1.add(btnClear);
        jPanel1.add(btnClone);
        jPanel1.add(btnIterator);
        jPanel1.add(btnMax);
        jPanel1.add(btnMin);
        jPanel1.add(btnDisjoint);
        jPanel1.add(btnToArray);
        jPanel1.add(btnAddFromFile);
        jPanel1.add(btnSaveToFile);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        JTextField textField=new JTextField();
        JTextArea textArea=new JTextArea();
        JTextArea textArea2=new JTextArea();
        JScrollPane jscr=new JScrollPane(textArea);
        JScrollPane jscr2=new JScrollPane(textArea2);

        JPanel jPanel2=new JPanel();

        JLabel jLabel=new JLabel("Input field");
        jPanel2.setLayout(new GridLayout(4,1));
        jPanel2.add(jLabel);
        jPanel2.add(textField);
        jPanel2.add(jscr);
        jPanel2.add(jscr2);

        frame.add(jPanel1);
        frame.add(jPanel2);

        frame.setVisible(true);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Создание пустого набора с начальной емкостью (16) и со значением коэффициента загрузки (0.75) по умолчанию:
        hSet = new HashSet<>();
        // Создание множества из элементов коллекции:
        // HashSet(Collection c)
        // Создание множества с указанной начальной емкостью и со значением коэффициента загрузки по умолчанию (0.75):
        // HashSet(int initialCapacity)
        // Создание множества с указанными начальной емкостью и коэффициентом загрузки:
        // HashSet(int initialCapacity, float loadFactor)
        JOptionPane.showMessageDialog(null, "HashSet was created","Creating empty HashSet..."
                , JOptionPane.OK_OPTION);

        btnIsEmpty.addActionListener((e)->{
            if(hSet.isEmpty()) {
                textArea.setText("HashSet is empty");
            }
            else{
                textArea.setText("HashSet is not empty");
            }
        });

        btnAdd.addActionListener((e)->{
            if(textField.getText().compareTo("")!=0) {
                hSet.add(Integer.parseInt(textField.getText()));
                textArea.setText("Element "+Integer.parseInt(textField.getText())+" was added");
                textField.setText("");
                textArea2.setText("");
                printSet(hSet,textArea2);
            }
            else
                JOptionPane.showMessageDialog(null, "TextField is empty","TextField is empty"
                        , JOptionPane.OK_OPTION);
                });

        btnSize.addActionListener((e)->{
            textArea.setText("The size of hSet is "+hSet.size());
        });

        btnRemove.addActionListener((e)->{
            if(!hSet.isEmpty()) {
                if (textField.getText()!=null&&hSet.contains(Integer.parseInt(textField.getText()))){
                    hSet.remove(Integer.parseInt(textField.getText()));
                    textField.setText("");
                    textArea2.setText("");
                    printSet(hSet,textArea2);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Logical error", "There is no such element!"
                            , JOptionPane.OK_OPTION);
                }

            }
            else {
                JOptionPane.showMessageDialog(null, "HashSet is empty", "HashSet is empty"
                        , JOptionPane.OK_OPTION);
            }
        });

        btnContains.addActionListener((e)->{
            if (hSet.contains(Integer.parseInt(textField.getText()))){
                textArea.setText("hSet contains "+Integer.parseInt(textField.getText()));
                textField.setText("");
            }
            else {
                textArea.setText("There is no such element in hSet");
            }
        });

        btnClear.addActionListener((e)->{
            hSet.clear();
            textArea.setText("hSet was cleared");
            textArea2.setText("");
        });

        btnIterator.addActionListener((e)->{
            textArea.setText("");
            StringBuffer outputBuffer= new StringBuffer();
            for(Object i : hSet) {
                outputBuffer.insert(outputBuffer.length(), i.toString());
                outputBuffer.insert(outputBuffer.length(), " ");
            }
            textArea.setText(outputBuffer.toString());
        });

        btnAddAll.addActionListener((e)->{
            HashSet<Integer> collectSet = new HashSet<>();
            collectSet.add(10);
            collectSet.add(45);
            hSet.addAll(collectSet);
            textArea.setText("Collection with  elements 10, 45 was added to hSet");
            printSet(hSet,textArea2);
        });

        btnClone.addActionListener((e)->{
            HashSet<Integer> cloneSet = (HashSet)hSet.clone();
            cloneSet.add(0);
            textArea2.setText("");
            textArea.setText("Clone of hSet was created. Element 0 was added there.\n" +
                    "Now this clone is showed below");
            StringBuffer outputBuffer= new StringBuffer();
            for(Object i : cloneSet) {
                outputBuffer.insert(outputBuffer.length(), i.toString());
                outputBuffer.insert(outputBuffer.length(), " ");
            }
            textArea2.setText(outputBuffer.toString());
        });

        btnRemoveAll.addActionListener((e) -> {
            HashSet<Integer> remSet = new HashSet<>();
            remSet.add(10);
            remSet.add(45);
            hSet.removeAll(remSet);
            textArea.setText("Collection with  elements 10, 45 was removed from hSet");
            printSet(hSet,textArea2);
        });

        btnRetainAll.addActionListener((e)->{
            HashSet<Integer> interSet = new HashSet<>();
            interSet.add(10);
            interSet.add(45);
            hSet.retainAll(interSet);
            textArea.setText("Collection with  elements 10, 45 was intersected with hSet");
            textArea2.setText("");
            printSet(hSet,textArea2);
        });

        btnDisjoint.addActionListener((e)->{
            HashSet<Integer> disjSet = new HashSet<>();
            disjSet.add(10);
            disjSet.add(45);
            if(Collections.disjoint(hSet,disjSet)){
                textArea.setText("hSet and disjSet of elements 10,45 has nothing in common");
            }
            else
                textArea.setText("hSet and disjSet of elements 10,45 has something in common");
        });

        btnMax.addActionListener((e)->{
            textArea.setText("The max element of hSet is "+Collections.max(hSet));
        });

        btnMin.addActionListener((e)->{
            textArea.setText("The min element of hSet is "+Collections.min(hSet));
        });

        btnToArray.addActionListener((e)->{
            Object[] array=hSet.toArray();
            textArea.setText("");
            StringBuffer outputBuffer= new StringBuffer();
            for(int i=0;i<array.length;++i) {
                outputBuffer.insert(outputBuffer.length(), array[i].toString());
                outputBuffer.insert(outputBuffer.length(), " ");
            }
            textArea.setText(outputBuffer.toString());
        });

        btnAddFromFile.addActionListener((e)->{
            BufferedReader br1;
            String ss1;
            StringBuffer sb1;
            try {
                br1 = new BufferedReader(new FileReader("data.txt"));
                sb1 = new StringBuffer();
                while ((ss1 = br1.readLine()) != null) {
                    sb1.append(ss1);
                    sb1.append(" ");
                }
                String string = new String(sb1);
                if(string.equals("")) {
                    JOptionPane.showMessageDialog(null, "HashSet is empty","HashSet is empty"
                            , JOptionPane.OK_OPTION);
                }
                else {
                    try {
                        StringTokenizer st = new StringTokenizer(string, " ");
                        while(st.hasMoreTokens())
                            hSet.add(Integer.parseInt(st.nextToken()));
                        textArea.setText("Elements from file hashset.txt were added to hSet");
                        printSet(hSet,textArea2);
                    } catch(NumberFormatException e1) {
                        e1.printStackTrace();
                    }
                }
                    br1.close();
                } catch (IOException e2){
                    e2.printStackTrace();
                }
        });

        btnSaveToFile.addActionListener((e)->{
            String res = hSet.toString();
            FileWriter fw;
            try {
                fw = new FileWriter("hashset.txt", false);
                fw.write(res);
                fw.close();
                textArea.setText("Elements from hSet were written to data.txt");
            } catch(IOException e2) {
                e2.printStackTrace();
            }
        });

    }

    public static void printSet(HashSet hSet, JTextArea textArea2){
        StringBuffer outputBuffer= new StringBuffer();
        for(Object i : hSet) {
            outputBuffer.insert(outputBuffer.length(), i.toString());
            outputBuffer.insert(outputBuffer.length(), " ");
        }
        textArea2.setText(outputBuffer.toString());
    }

}