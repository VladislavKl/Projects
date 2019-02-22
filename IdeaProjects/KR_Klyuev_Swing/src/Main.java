import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        JFrame frame=new JFrame("KR_Klyuev");
        frame.setSize(720, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        JPanel panel1=new JPanel();
        panel1.setLayout(new FlowLayout());

        JPanel panel2=new JPanel();
        panel2.setLayout(new FlowLayout());

        JPanel panel3=new JPanel();
        panel3.setLayout(new FlowLayout());

        JButton button1=new JButton("1 (choose file to see)");
        JButton button2=new JButton("2");
        JButton button3=new JButton("3");

        JTextArea textArea = new JTextArea(8,50);
        textArea.setCaretPosition(0);
        textArea.setEditable(false);

        JTextArea textArea2 = new JTextArea(15,20);
        textArea2.setCaretPosition(0);
        textArea2.setEditable(false);

        JTextArea textArea3 = new JTextArea(15,20);
        textArea3.setCaretPosition(0);
        textArea3.setEditable(false);

        JScrollPane jscr=new JScrollPane(textArea);

        JScrollPane jscr2=new JScrollPane(textArea2);

        JScrollPane jscr3=new JScrollPane(textArea3);

        //JTextField textField=new JTextField();// May be not required
        //textField.setColumns(5);
        //PlainDocument doc = (PlainDocument) textField.getDocument();
        //doc.setDocumentFilter(new DigitFilter());

        panel1.add(button1);

        ArrayList<Rectangle> rectangles=new ArrayList<>();

        Functions.task1(button1, rectangles, textArea);
        //Functions.task2(button2,rectangles,textArea2);
        //Functions.task3(button3,rectangles,textArea3);

        //panel2.add(textField);//delete if unnecessary
        panel2.add(jscr);

        panel3.add(button2);
        panel3.add(jscr2);
        panel3.add(jscr3);
        panel3.add(button3);

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        frame.add(panel3, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}

