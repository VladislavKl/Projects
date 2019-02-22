import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

final class Perform1Button implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            String str = TS.list.getSelectedValue().toString();
            switch(str) {
                case "Size":
                    TS.res.setText(String.valueOf(TS.ts.size()));
                    break;

                case "Clear":
                    TS.ts.clear();
                    TS.tree1.setText("");
                    break;

                case "Clear2":
                    TS.ts2.clear();
                    TS.tree2.setText("");
                    break;

                case "IsEmpty":
                    TS.res.setText(String.valueOf(TS.ts.isEmpty()));
                    break;

                case "Add":
                    try {
                        TS.ts.add(Integer.parseInt(TS.input1.getText()));
                        Iterator<Integer> it1 = TS.ts.iterator();
                        String s1 = "";
                        while(it1.hasNext())
                            s1 += it1.next() + " ";
                        TS.tree1.setText(s1);
                        TS.input1.setText("");
                    } catch(NumberFormatException e) {
                        TS.error();
                        TS.input1.setText("");
                    }
                    break;

                case "Add2":
                    try {
                        TS.ts2.add(Integer.parseInt(TS.input2.getText()));
                        Iterator<Integer> it2 = TS.ts2.iterator();
                        String s2 = "";
                        while(it2.hasNext())
                            s2 += it2.next() + " ";
                        TS.tree2.setText(s2);
                        TS.input2.setText("");
                    } catch(NumberFormatException e) {
                        TS.error();
                        TS.input2.setText("");
                    }
                    break;

                case "Remove":
                    try {
                        TS.ts.remove(Integer.parseInt(TS.input1.getText()));
                        Iterator<Integer> it3 = TS.ts.iterator();
                        String s3 = "";
                        while(it3.hasNext())
                            s3 += it3.next() + " ";
                        TS.tree1.setText(s3);
                        TS.input1.setText("");
                    } catch(NumberFormatException e) {
                        TS.error();
                        TS.input1.setText("");
                    }
                    break;

                case "Remove2":
                    try {
                        TS.ts2.remove(Integer.parseInt(TS.input2.getText()));
                        Iterator<Integer> it4 = TS.ts2.iterator();
                        String s4 = "";
                        while(it4.hasNext())
                            s4 += it4.next() + " ";
                        TS.tree2.setText(s4);
                        TS.input2.setText("");
                    } catch(NumberFormatException e) {
                        TS.error();
                        TS.input2.setText("");
                    }
                    break;

                case "Contains":
                    try {
                        TS.res.setText(String.valueOf(TS.ts.contains(Integer.parseInt(TS.input1.getText()))));
                        TS.input1.setText("");
                    } catch(NumberFormatException e) {
                        TS.error();
                        TS.input1.setText("");
                    }
                    break;

                case "Equals":
                    TS.res.setText(String.valueOf(TS.ts.equals(TS.ts2)));
                    break;

                case "AddAll":
                    TS.ts.addAll(TS.ts2);
                    Iterator<Integer> it5 = TS.ts.iterator();
                    String s5 = "";
                    while(it5.hasNext())
                        s5 += it5.next() + " ";
                    TS.tree1.setText(s5);
                    break;

                case "RemoveAll":
                    TS.ts.removeAll(TS.ts2);
                    Iterator<Integer> it6 = TS.ts.iterator();
                    String s6 = "";
                    while(it6.hasNext())
                        s6 += it6.next() + " ";
                    TS.tree1.setText(s6);
                    break;

                case "RetainAll":
                    TS.ts.retainAll(TS.ts2);
                    Iterator<Integer> it7 = TS.ts.iterator();
                    String s7 = "";
                    while(it7.hasNext())
                        s7 += it7.next() + " ";
                    TS.tree1.setText(s7);
                    break;

                case "First":
                    TS.res.setText(String.valueOf(TS.ts.first()));
                    break;

                case "Last":
                    TS.res.setText(String.valueOf(TS.ts.last()));
                    break;

                case "SubSet":
                    try {
                        int a = Integer.parseInt(TS.input1.getText());
                        int b = Integer.parseInt(TS.input2.getText());
                        SortedSet<Integer> ss = TS.ts.subSet(a, b);
                        TS.ts = new TreeSet<>(ss);
                        Iterator<Integer> it8 = TS.ts.iterator();
                        String s8 = "";
                        while(it8.hasNext())
                            s8 += it8.next() + " ";
                        TS.tree1.setText(s8);
                        TS.input1.setText("");
                        TS.input2.setText("");
                    } catch(NumberFormatException e) {
                        TS.error();
                        TS.input1.setText("");
                        TS.input2.setText("");
                    }
                    break;

                case "HeadSet":
                    try {
                        int b = Integer.parseInt(TS.input2.getText());
                        SortedSet<Integer> ss = TS.ts.headSet(b);
                        TS.ts = new TreeSet<>(ss);
                        Iterator<Integer> it9 = TS.ts.iterator();
                        String s9 = "";
                        while(it9.hasNext())
                            s9 += it9.next() + " ";
                        TS.tree1.setText(s9);
                        TS.input2.setText("");
                    } catch(NumberFormatException e) {
                        TS.error();
                        TS.input2.setText("");
                    }
                    break;

                case "TailSet":
                    try {
                        int a = Integer.parseInt(TS.input1.getText());
                        SortedSet<Integer> ss = TS.ts.tailSet(a);
                        TS.ts = new TreeSet<>(ss);
                        Iterator<Integer> it10 = TS.ts.iterator();
                        String s10 = "";
                        while(it10.hasNext())
                            s10 += it10.next() + " ";
                        TS.tree1.setText(s10);
                        TS.input2.setText("");
                    } catch(NumberFormatException e) {
                        TS.error();
                        TS.input2.setText("");
                    }
                    break;

                case "Lower":
                    try {
                        int x = Integer.parseInt(TS.input1.getText());
                        TS.res.setText(String.valueOf(TS.ts.lower(x)));
                        TS.input1.setText("");
                    } catch(NumberFormatException e) {
                        TS.error();
                        TS.input1.setText("");
                    }
                    break;

                case "Floor":
                    try {
                        int x = Integer.parseInt(TS.input1.getText());
                        TS.res.setText(String.valueOf(TS.ts.floor(x)));
                        TS.input1.setText("");
                    } catch(NumberFormatException e) {
                        TS.error();
                        TS.input1.setText("");
                    }
                    break;

                case "Higher":
                    try {
                        int x = Integer.parseInt(TS.input1.getText());
                        TS.res.setText(String.valueOf(TS.ts.higher(x)));
                        TS.input1.setText("");
                    } catch(NumberFormatException e) {
                        TS.error();
                        TS.input1.setText("");
                    }
                    break;

                case "Ceiling":
                    try {
                        int x = Integer.parseInt(TS.input1.getText());
                        TS.res.setText(String.valueOf(TS.ts.ceiling(x)));
                        TS.input1.setText("");
                    } catch(NumberFormatException e) {
                        TS.error();
                        TS.input1.setText("");
                    }
                    break;

                case "PollFirst":
                    TS.ts.pollFirst();
                    Iterator<Integer> it11 = TS.ts.iterator();
                    String s11 = "";
                    while(it11.hasNext())
                        s11 += it11.next() + " ";
                    TS.tree1.setText(s11);
                    break;

                case "PollLast":
                    TS.ts.pollLast();
                    Iterator<Integer> it12 = TS.ts.iterator();
                    String s12 = "";
                    while(it12.hasNext())
                        s12 += it12.next() + " ";
                    TS.tree1.setText(s12);
                    break;

                case "DescendingSet":
                    TS.ts = new TreeSet<>(TS.ts.descendingSet());
                    Iterator<Integer> it13 = TS.ts.iterator();
                    String s13 = "";
                    while(it13.hasNext())
                        s13 += it13.next() + " ";
                    TS.tree1.setText(s13);
                    break;

                case "SubSet2":
                    try {
                        int a = Integer.parseInt(TS.input1.getText());
                        int b = Integer.parseInt(TS.input2.getText());
                        boolean b1 = TS.rb1.isSelected();
                        boolean b2 = TS.rb2.isSelected();
                        SortedSet<Integer> ss = TS.ts.subSet(a, b1, b, b2);
                        TS.ts = new TreeSet<>(ss);
                        Iterator<Integer> it14 = TS.ts.iterator();
                        String s14 = "";
                        while(it14.hasNext())
                            s14 += it14.next() + " ";
                        TS.tree1.setText(s14);
                        TS.input1.setText("");
                        TS.input2.setText("");
                    } catch(NumberFormatException e) {
                        TS.error();
                        TS.input1.setText("");
                        TS.input2.setText("");
                    }
                    break;

                case "HeadSet2":
                    try {
                        int b = Integer.parseInt(TS.input2.getText());
                        boolean b2 = TS.rb2.isSelected();
                        SortedSet<Integer> ss = TS.ts.headSet(b, b2);
                        TS.ts = new TreeSet<>(ss);
                        Iterator<Integer> it15 = TS.ts.iterator();
                        String s15 = "";
                        while(it15.hasNext())
                            s15 += it15.next() + " ";
                        TS.tree1.setText(s15);
                        TS.input2.setText("");
                    } catch(NumberFormatException e) {
                        TS.error();
                        TS.input2.setText("");
                    }
                    break;

                case "TailSet2":
                    try {
                        int a = Integer.parseInt(TS.input1.getText());
                        boolean b1 = TS.rb1.isSelected();
                        SortedSet<Integer> ss = TS.ts.tailSet(a, b1);
                        TS.ts = new TreeSet<>(ss);
                        Iterator<Integer> it16 = TS.ts.iterator();
                        String s16 = "";
                        while(it16.hasNext())
                            s16 += it16.next() + " ";
                        TS.tree1.setText(s16);
                        TS.input2.setText("");
                    } catch(NumberFormatException e) {
                        TS.error();
                        TS.input2.setText("");
                    }
                    break;

                case "AddFromFile":
                    BufferedReader br1;
                    String ss1;
                    StringBuffer sb1;
                    try {
                        br1 = new BufferedReader(new FileReader("data.txt"));
                        sb1 = new StringBuffer();
                        while ((ss1 = br1.readLine()) != null)
                            sb1.append(ss1 + " ") ;
                        String string = new String(sb1);
                        if(string.equals(""))
                            TS.emptyFile();
                        else {
                            try {
                                StringTokenizer st = new StringTokenizer(string, " ");
                                while(st.hasMoreTokens())
                                    TS.ts.add(Integer.parseInt(st.nextToken()));
                                Iterator<Integer> it17 = TS.ts.iterator();
                                String s17 = "";
                                while(it17.hasNext())
                                    s17 += it17.next() + " ";
                                TS.tree1.setText(s17);
                            } catch(NumberFormatException e) {
                                TS.incorrectData();
                            }
                        }
                        br1.close();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    break;

                case "DeleteFromFile":
                    BufferedReader br2;
                    String ss2;
                    StringBuffer sb2;
                    try {
                        br2 = new BufferedReader(new FileReader("data.txt"));
                        sb2 = new StringBuffer();
                        while ((ss2 = br2.readLine()) != null)
                            sb2.append(ss2 + " ") ;
                        String string = new String(sb2);
                        if(string.equals(""))
                            TS.emptyFile();
                        else {
                            try {
                                StringTokenizer st = new StringTokenizer(string, " ");
                                while(st.hasMoreTokens())
                                    TS.ts.remove(Integer.parseInt(st.nextToken()));
                                Iterator<Integer> it18 = TS.ts.iterator();
                                String s18 = "";
                                while(it18.hasNext())
                                    s18 += it18.next() + " ";
                                TS.tree1.setText(s18);
                            } catch(NumberFormatException e) {
                                TS.incorrectData();
                            }
                        }
                        br2.close();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    break;

                case "SaveTS":
                    String res = TS.ts.toString();
                    FileWriter fw;
                    try {
                        fw = new FileWriter("treeset.txt", false);
                        fw.write(res);
                        fw.close();
                        JOptionPane.showMessageDialog(null, "Your TS is in file!", "Attention", JOptionPane.INFORMATION_MESSAGE);
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    break;
            }
        } catch(NullPointerException e) {
            TS.error2();
        }
    }
}

final class Perform2Button implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            String str = TS.list2.getSelectedValue().toString();
            switch(str) {
                case "Disjoint":
                    TS.res.setText(String.valueOf(Collections.disjoint(TS.ts, TS.ts2)));
                    break;

                case "Frequency":
                    Object ob = Integer.parseInt(TS.input1.getText());
                    TS.res.setText(String.valueOf(Collections.frequency(TS.ts, ob)));
                    break;

                case "Max":
                    TS.res.setText(String.valueOf(Collections.max(TS.ts)));
                    break;

                case "Min":
                    TS.res.setText(String.valueOf(Collections.min(TS.ts)));
                    break;

                case "UnmodifiableSet":
                    JOptionPane.showMessageDialog(null, "Unmodifiable Set!", "Attention", JOptionPane.INFORMATION_MESSAGE);
                    TS.res2.setText(TS.ts.toString());
                    TS.perform1.setEnabled(false);
                    TS.perform2.setEnabled(false);
                    TS.list.setEnabled(false);
                    TS.list2.setEnabled(false);
                    TS.perform3.setEnabled(true);
                    TS.unmodList.setEnabled(true);
                    break;

                default:
                    break;
            }

        } catch(NullPointerException e) {
            TS.error2();
        }
    }
}

final class Perform3Button implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            Set<Integer> set = new TreeSet<>(TS.ts);
            Set<Integer> us = Collections.unmodifiableSet(set);
            String str = TS.unmodList.getSelectedValue().toString();
            switch(str) {
                case "Size":
                    TS.res.setText(String.valueOf(us.size()));
                    break;

                case "Clear2":
                    TS.ts2.clear();
                    TS.tree2.setText("");
                    break;

                case "IsEmpty":
                    TS.res.setText(String.valueOf(us.isEmpty()));
                    break;

                case "Add2":
                    try {
                        TS.ts2.add(Integer.parseInt(TS.input2.getText()));
                        Iterator<Integer> it = TS.ts2.iterator();
                        String s = "";
                        while(it.hasNext())
                            s += it.next() + " ";
                        TS.tree2.setText(s);
                        TS.input2.setText("");
                    } catch(NumberFormatException e) {
                        TS.error();
                        TS.input2.setText("");
                    }
                    break;

                case "Remove2":
                    try {
                        TS.ts2.add(Integer.parseInt(TS.input2.getText()));
                        Iterator<Integer> it2 = TS.ts2.iterator();
                        String s2 = "";
                        while(it2.hasNext())
                            s2 += it2.next() + " ";
                        TS.tree2.setText(s2);
                        TS.input2.setText("");
                    } catch(NumberFormatException e) {
                        TS.error();
                        TS.input2.setText("");
                    }
                    break;

                case "Contains":
                    try {
                        TS.res.setText(String.valueOf(us.contains(Integer.parseInt(TS.input1.getText()))));
                        TS.input1.setText("");
                    } catch(NumberFormatException e) {
                        TS.error();
                        TS.input1.setText("");
                    }
                    break;

                case "Equals":
                    TS.res.setText(String.valueOf(us.equals(TS.ts2)));
                    break;

                case "Disjoint":
                    TS.res.setText(String.valueOf(Collections.disjoint(us, TS.ts2)));
                    break;

                case "Frequency":
                    try {
                        Object ob = Integer.parseInt(TS.input1.getText());
                        TS.res.setText(String.valueOf(Collections.frequency(us, ob)));
                    } catch(NumberFormatException e) {
                        TS.error();
                        TS.input1.setText("");
                    }
                    break;

                case "Max":
                    TS.res.setText(String.valueOf(Collections.max(us)));
                    break;

                case "Min":
                    TS.res.setText(String.valueOf(Collections.min(us)));
                    break;

                default:
                    break;
            }
        } catch(NullPointerException e) {
            TS.error2();
        }
    }
}

public class TS {

    static TreeSet<Integer> ts = new TreeSet<>();
    static TreeSet<Integer> ts2 = new TreeSet<>();

    static JFrame frame = new JFrame("TreeSet GUI");

    static String[] buttons = {"Size", "Clear", "Clear2", "IsEmpty", "Add", "Add2", "Remove", "Remove2",
            "Contains", "Equals", "AddAll", "RemoveAll", "RetainAll", "First", "Last",
            "SubSet", "HeadSet", "TailSet", "Lower", "Floor", "Higher", "Ceiling",
            "PollFirst", "PollLast", "DescendingSet", "SubSet2", "HeadSet2", "TailSet2",
            "AddFromFile", "DeleteFromFile", "SaveTS"};
    static JList list = new JList(buttons);
    static JScrollPane sp = new JScrollPane(list);
    static String[] alg = {"Disjoint", "Frequency", "Max", "Min", "UnmodifiableSet"};
    static JList list2 = new JList(alg);
    static JScrollPane sp2 = new JScrollPane(list2);
    static JButton perform1 = new JButton("Perform 1");
    static JButton perform2 = new JButton("Perform 2");
    static JRadioButton rb1 = new JRadioButton("Including 1");
    static JRadioButton rb2 = new JRadioButton("Including 2");
    static JLabel v1 = new JLabel("Value 1:");
    static JLabel v2 = new JLabel("Value 2:");
    static JTextField input1 = new JTextField(4);
    static JTextField input2 = new JTextField(4);
    static JLabel label = new JLabel("Result: ");
    static JTextField tree1 = new JTextField(15);
    static JTextField tree2 = new JTextField(15);
    static JTextField res = new JTextField(15);
    static JLabel label2 = new JLabel("Unmod. set: ");
    static JTextField res2 = new JTextField(10);
    static String[] buttons2 = {"Size", "Clear2", "IsEmpty", "Add2", "Remove2", "Contains",
            "Equals", "Disjoint", "Frequency", "Max", "Min"};
    static JList unmodList = new JList(buttons2);
    static JScrollPane sp3 = new JScrollPane(unmodList);
    static JButton perform3 = new JButton("Perform 3");

    public static void main(String[] args) {
        frame.setSize(new Dimension(1000, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JPanel m = new JPanel(new BorderLayout());
        JPanel alg = new JPanel(new BorderLayout());
        JPanel extra = new JPanel(new BorderLayout());

        sp.setPreferredSize(new Dimension(200, 200));
        list.setLayoutOrientation(JList.VERTICAL);
        m.add(sp, BorderLayout.NORTH);
        m.add(perform1, BorderLayout.SOUTH);

        sp2.setPreferredSize(new Dimension(200, 200));
        list2.setLayoutOrientation(JList.VERTICAL);
        alg.add(sp2, BorderLayout.NORTH);
        alg.add(perform2, BorderLayout.SOUTH);

        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new BorderLayout());
        JPanel p3 = new JPanel(new BorderLayout());
        JPanel p21 = new JPanel(new BorderLayout());
        JPanel p22 = new JPanel(new BorderLayout());
        JPanel p31 = new JPanel(new BorderLayout());
        JPanel p32 = new JPanel(new BorderLayout());
        JPanel p33 = new JPanel(new FlowLayout());
        tree1.setEditable(false);
        tree2.setEditable(false);
        res.setEditable(false);
        res2.setEditable(false);
        p1.add(rb1, BorderLayout.NORTH);
        p1.add(rb2, BorderLayout.SOUTH);
        p21.add(v1, BorderLayout.NORTH);
        p21.add(v2, BorderLayout.SOUTH);
        p22.add(input1, BorderLayout.NORTH);
        p22.add(input2, BorderLayout.SOUTH);
        p2.add(p21, BorderLayout.WEST);
        p2.add(p22, BorderLayout.EAST);
        p31.add(label, BorderLayout.NORTH);
        p32.add(tree1, BorderLayout.NORTH);
        p32.add(tree2, BorderLayout.CENTER);
        p32.add(res, BorderLayout.SOUTH);
        p33.add(label2);
        p33.add(res2);
        p33.add(sp3);
        p33.add(perform3);
        perform3.setEnabled(false);
        unmodList.setEnabled(false);
        p3.add(p31, BorderLayout.NORTH);
        p3.add(p32, BorderLayout.CENTER);
        p3.add(p33, BorderLayout.SOUTH);
        extra.add(p1, BorderLayout.NORTH);
        extra.add(p2, BorderLayout.CENTER);
        extra.add(p3, BorderLayout.SOUTH);

        frame.add(m, BorderLayout.NORTH);
        frame.add(alg, BorderLayout.CENTER);
        frame.add(extra, BorderLayout.SOUTH);

        perform1.addActionListener(new Perform1Button());
        perform2.addActionListener(new Perform2Button());
        perform3.addActionListener(new Perform3Button());

        frame.setResizable(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void error() {
        JOptionPane.showMessageDialog(null, "Invalid input field!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void error2() {
        JOptionPane.showMessageDialog(null, "You didn't select an operation!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void emptyFile() {
        JOptionPane.showMessageDialog(null, "File is empty!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void incorrectData() {
        JOptionPane.showMessageDialog(null, "Incorrect data in file!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}