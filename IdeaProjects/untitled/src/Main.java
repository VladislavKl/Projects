import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

class MainFrame extends JFrame {
    private JPanel panel1, panel2;
    public MainFrame (String s) {
        super(s);
        FontUIResource f = new FontUIResource(new Font("Courier New", Font.PLAIN, 22));
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                FontUIResource orig = (FontUIResource) value;
                Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
                UIManager.put(key, new FontUIResource(font));
            }
        }
        calculator = new Calculator();
        setSize(new Dimension(800, 1400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1));
        slMenu = new SaveLoadMenu();
        setJMenuBar(slMenu);
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 1));
        numberPanel = new NumberPanel();
        panel1.add(numberPanel);
        frPanel = new FRPanel();
        panel1.add(frPanel);

        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3, 1));
        tablePanel = new TablePanel();
        listPanel = new ListPanel();
        panel2.add(tablePanel);
        panel2.add(listPanel);
        add(panel1);
        add(panel2);
    }
    private Calculator calculator;
    private NumberPanel numberPanel;
    class NumberPanel extends JPanel {
        private ArrayList<JTextField> operands = new ArrayList<>();
        private ArrayList<JComboBox<Character>> operators = new ArrayList<>();
        private JPanel inputPane = new JPanel();
        private JPanel labelPanel = new JPanel();
        private JPanel buttonPanel = new JPanel();
        private JLabel resultLabel = new JLabel();
        private JPanel outerPanel = new JPanel();
        private JButton addButton;
        private JButton removeButton;
        private JButton resultButton;

        public NumberPanel() {
            setLayout(new GridLayout(1, 1));
            JTextField jtf = new JTextField(4);
            operands.add(jtf);
            outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.Y_AXIS));

            JLabel label = new JLabel("Current expression:");
            label.setFont(new Font("Courier New", Font.BOLD,22));
            labelPanel.add(label);

            resultLabel.setText("Result:");
            resultLabel.setFont(new Font("Courier New", Font.BOLD,22));

            addButton = new JButton("Add operation");

            removeButton = new JButton("Delete operation");

            resultButton = new JButton("Evaluate");

            addButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    addOperand();
                }
            });
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeOperand();
                }
            });
            resultButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resultLabel.setText("Result: ");
                    ArrayList<Numb> numbers = new ArrayList<>();
                    ArrayList<Character> operations = new ArrayList<>();
                    String operand;
                    boolean rationalModeOn = false;
                    if (frPanel.jrbFractionMode.isSelected()) {
                        rationalModeOn = true;
                    }
                    boolean badCase = false;
                    boolean generalBadCase = false;
                    for (int i = 0 ; i < operands.size(); i++) {
                        badCase = false;
                        operand = operands.get(i).getText();
                        try {
                            Numb numb = Numb.parseNumb(operand, rationalModeOn);
                            numbers.add(numb);
                        }
                        catch (RuntimeException genExc) {
                            badCase = true;
                            for (int j = 0; j < tablePanel.table.getRowCount(); j++) {
                                if (operand.equals(tablePanel.table.getValueAt(j, 0))) {
                                    try {
                                        Numb numb = Numb.parseNumb((String) tablePanel.table.getValueAt(j, 1), rationalModeOn);
                                        badCase = false;
                                        numbers.add(numb);
                                        break;
                                    }
                                    catch (RuntimeException exc1) {
                                        if (rationalModeOn) {
                                            try {
                                                Double.parseDouble((String) tablePanel.table.getValueAt(j, 1));
                                                JOptionPane.showMessageDialog(MainFrame.this, "Error: Incorrect input format", "Error", JOptionPane.OK_OPTION);
                                            } catch (RuntimeException exc2) {
                                            }
                                        }
                                    }
                                }
                            }
                            generalBadCase = true;
                        }
                        if (badCase) {
                            operands.get(i).setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                        }
                        else {
                            operands.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

                        }
                    }
                    if (badCase) {
                        return;
                    }
                    for (int i = 0; i < operators.size(); i++) {
                        operations.add((Character) operators.get(i).getSelectedItem());
                    }
                    try {
                        if (numbers.size() > operations.size()) {
                            Numb res = calculator.getResult(numbers, operations);
                            parseHistoryItem(rationalModeOn);
                            listPanel.updateListPanel();
                            if (generalBadCase == false)
                                resultLabel.setText("Result: " + res);
                            resultLabel.setHorizontalAlignment(JLabel.CENTER);
                        }
                    }
                    catch (RuntimeException ex) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Error: Division by zero");
                        operands.get(Integer.parseInt(ex.getMessage())).setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    }
                }
            });

            buttonPanel.add(addButton);
            buttonPanel.add(removeButton);
            buttonPanel.add(resultButton);
            add(outerPanel);
            updateNumberPanel();
        }

        private void parseHistoryItem(boolean rationalModeOn) {
            ListPanel.Expressions item = listPanel.new Expressions();
            for (int i = 0; i < operators.size(); i++) {
                item.exprOperands.add(operands.get(i).getText());
                item.exprOperators.add((Character) operators.get(i).getSelectedItem());
            }
            item.exprOperands.add(operands.get(operands.size() - 1).getText());
            item.rationalModeOn = rationalModeOn;
            listPanel.expressions.add(item);
        }

        public void updateNumberPanel() {
            inputPane.removeAll();
            outerPanel.removeAll();
            if (operators.size() > 0) {
                removeButton.setEnabled(true);
            }
            else {
                removeButton.setEnabled(false);
            }
            for (int i = 0; i < operators.size(); i++) {
                inputPane.add(operands.get(i));
                inputPane.add(operators.get(i));
            }
            inputPane.add(operands.get(operands.size() - 1));
            JScrollPane inpScrPane = new JScrollPane(inputPane);
            outerPanel.add(labelPanel);
            outerPanel.add(inpScrPane);
            outerPanel.add(buttonPanel);
            outerPanel.add(resultLabel);
            revalidate();
            repaint();
        }

        public void addOperand() {
            JComboBox<Character> comboBox = new JComboBox<>();
            comboBox.addItem('+');
            comboBox.addItem('-');
            comboBox.addItem('*');
            comboBox.addItem('/');
            comboBox.setFont(new Font("Courier New", Font.BOLD, 20));
            operators.add(comboBox);

            JTextField textField = new JTextField(4);
            textField.setFont(new Font("Courier New", Font.PLAIN, 22));
            operands.add(textField);
            updateNumberPanel();
        }

        public void removeOperand() {
            if (operators.size() == 0) {
                return;
            }
            operators.remove(operators.size() - 1);
            operands.remove(operands.size() - 1);
            updateNumberPanel();
        }
    }
    private FRPanel frPanel;
    class FRPanel extends JPanel {
        private JRadioButton jrbFloatMode, jrbFractionMode;
        private ButtonGroup buttonGroup;

        public FRPanel() {
            setLayout(new GridLayout(6, 1));
            jrbFloatMode = new JRadioButton("float");
            jrbFloatMode.setSelected(true);
            jrbFractionMode = new JRadioButton("rational");
            buttonGroup = new ButtonGroup();
            buttonGroup.add(jrbFloatMode);
            buttonGroup.add(jrbFractionMode);
            jrbFloatMode.setHorizontalAlignment(SwingConstants.CENTER);
            jrbFractionMode.setHorizontalAlignment(SwingConstants.CENTER);
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Evaluation mode:");
            label.setFont(new Font("Courier New", Font.BOLD,22));
            panel.add(label);
            add(panel);
            add(jrbFloatMode);
            add(jrbFractionMode);
        }
    }
    private TablePanel tablePanel;
    class TablePanel extends JPanel {
        private JTable table;
        private DefaultTableModel tableModel;
        public TablePanel() {
            JLabel label = new JLabel("Variable values");
            label.setFont(new Font("Courier New", Font.BOLD,22));
            label.setHorizontalAlignment(JLabel.CENTER);
            table = new JTable(10, 2);
            String columns[] = { "Variables", "Values" };
            tableModel = new DefaultTableModel(columns, 10);
            table.setModel(tableModel);
            table.setRowHeight(MainFrame.this.getHeight()/30);
            JScrollPane scrollPane = new JScrollPane(table);
            setLayout(new GridLayout(2, 1));
            add(label);
            add(scrollPane);
        }
    }

    private ListPanel listPanel;
    class ListPanel extends JPanel {
        private JList<String> list;
        private DefaultListModel<String> listModel;
        private ArrayList<Expressions> expressions = new ArrayList<>();

        public ListPanel() {
            JLabel label = new JLabel("History");
            label.setFont(new Font("Courier New", Font.BOLD,22));
            label.setHorizontalAlignment(JLabel.LEFT);
            listModel = new DefaultListModel<>();
            list = new JList<>(listModel);
            list.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent arg0) {
                    int index = list.getSelectedIndex();
                    if (index == -1) {
                        return;
                    }
                    if (numberPanel.operands.size() < expressions.get(index).exprOperands.size()) {
                        for (int i = numberPanel.operands.size() + 1; i <= expressions.get(index).exprOperands.size(); i++) {
                            numberPanel.addOperand();
                        }
                    }
                    else if (numberPanel.operands.size() > expressions.get(index).exprOperands.size()) {
                        for (int i = numberPanel.operands.size(); i > expressions.get(index).exprOperands.size(); i--) {
                            numberPanel.removeOperand();
                        }
                    }

                    for (int i = 0; i < expressions.get(index).exprOperators.size(); i++) {
                        numberPanel.operands.get(i).setText(expressions.get(index).exprOperands.get(i));
                        numberPanel.operators.get(i).setSelectedItem(expressions.get(index).exprOperators.get(i));
                    }
                    numberPanel.operands.get(numberPanel.operands.size() - 1).setText(expressions.get(index).exprOperands.get(expressions.get(index).exprOperands.size() - 1));
                    if (expressions.get(index).rationalModeOn) {
                        frPanel.jrbFractionMode.setSelected(true);
                    }
                    else {
                        frPanel.jrbFloatMode.setSelected(true);
                    }
                }
            });
            JScrollPane listPane = new JScrollPane(list);
            setLayout(new GridLayout(2, 1));
            add(label);
            add(listPane);
        }

        public void updateListPanel() {
            listModel.removeAllElements();
            for (int i = 0; i < expressions.size(); i++) {
                listModel.addElement(expressions.get(i).convertToString());
            }
        }

        class Expressions {
            public ArrayList<String> exprOperands = new ArrayList<>();
            public ArrayList<Character> exprOperators = new ArrayList<>();
            public boolean rationalModeOn = false;

            public String convertToString() {
                StringBuffer stringBuffer = new StringBuffer("");
                for (int i = 0; i < exprOperators.size(); i++) {
                    stringBuffer.append(exprOperands.get(i));
                    char operator = exprOperators.get(i);
                    stringBuffer.append(operator);
                }
                stringBuffer.append(exprOperands.get(exprOperands.size() - 1));
                return stringBuffer.toString();
            }
        }
    }

    private SaveLoadMenu slMenu;
    class SaveLoadMenu extends JMenuBar {
        private JMenu generalMenu, subMenu;

        public SaveLoadMenu() {
            generalMenu = new JMenu("Menu");
            subMenu = new JMenu("Load");
            subMenu.add(new JMenuItem(new AbstractAction("Variable values") {
                public void actionPerformed(ActionEvent e) {
                    ArrayList<NamedNodeMap> info;
                    try {
                        info = open();
                    }
                    catch (FileNotFoundException e1) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Error: cannot access this file", "Error", JOptionPane.OK_OPTION);
                        return;
                    }
                    catch (Exception e2) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Error: impossible action", "Error", JOptionPane.OK_OPTION);
                        return;
                    }

                    for (int i = 0; i < tablePanel.tableModel.getRowCount(); i++) {
                        for (int j = 0; j < tablePanel.tableModel.getColumnCount(); j++) {
                            tablePanel.tableModel.setValueAt("", i, j);
                        }
                    }

                    int index = 0;
                    for (int i = 0; i < info.size(); i++) {
                        if (index >= tablePanel.tableModel.getRowCount()) {
                            JOptionPane.showMessageDialog(MainFrame.this, "Error: too much variables", "Error", JOptionPane.OK_OPTION);
                            break;
                        }

                        String var = "", val = "";
                        var = info.get(i).getNamedItem("variable").getNodeValue().toString();
                        val = info.get(i).getNamedItem("value").getNodeValue().toString();

                        tablePanel.tableModel.setValueAt(var, index, 0);
                        tablePanel.tableModel.setValueAt(val, index, 1);
                        index++;
                    }
                }
            }));
            subMenu.add(new JMenuItem(new AbstractAction("History") {
                public void actionPerformed(ActionEvent e) {
                    ArrayList<NamedNodeMap> info;
                    try {
                        info = open();
                    }
                    catch (FileNotFoundException e1) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Error: cannot access this file", "Error", JOptionPane.OK_OPTION);
                        return;
                    }
                    catch (Exception e2) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Error: impossible action", "Error", JOptionPane.OK_OPTION);
                        return;
                    }
                    listPanel.expressions.clear();
                    for (int i = 0; i < info.size(); i++) {
                        ListPanel.Expressions expr = listPanel.new Expressions();
                        String expression = "";
                        expression = info.get(i).getNamedItem("expression").getNodeValue().toString();

                        String[] operands = expression.split("\\+|\\-|\\*|\\:");
                        expr.exprOperands = new ArrayList<>(Arrays.asList(operands));
                        String delimiters = "+-*:";
                        for (int j = 0; j < expression.length(); j++) {
                            char c = expression.charAt(j);
                            if (delimiters.contains(c + "")) {
                                if (c == ':') {
                                    c = '/';
                                }
                                expr.exprOperators.add(c);
                            }
                        }
                        listPanel.expressions.add(expr);
                    }
                    listPanel.updateListPanel();
                }
            }));
            generalMenu.add(subMenu);
            subMenu = new JMenu("Save");
            subMenu.add(new JMenuItem(new AbstractAction("Variable values") {
                public void actionPerformed(ActionEvent e) {
                    ArrayList<ArrayList<String>> data = new ArrayList<>();
                    for (int i = 0; i < tablePanel.tableModel.getRowCount(); i++) {
                        try {
                            String variable = tablePanel.table.getValueAt(i, 0).toString();
                            String value = tablePanel.table.getValueAt(i, 1).toString();
                            ArrayList<String> dataItems = new ArrayList<>();
                            dataItems.add("variable");
                            dataItems.add(variable);
                            dataItems.add("value");
                            dataItems.add(value);
                            data.add(dataItems);
                        }
                        catch (Exception ex) {
                            continue;
                        }
                    }
                    save("variables.xml", data);
                }
            }));
            subMenu.add(new JMenuItem(new AbstractAction("History") {
                public void actionPerformed(ActionEvent e) {
                    ArrayList<ArrayList<String>> data = new ArrayList<>();
                    for (int i = 0; i < listPanel.expressions.size(); i++) {
                        try {
                            String expr = listPanel.expressions.get(i).convertToString();
                            ArrayList<String> dataItems = new ArrayList<>();
                            dataItems.add("expression");
                            dataItems.add(expr);
                            data.add(dataItems);
                        }
                        catch (Exception ex) {
                            continue;
                        }
                    }
                    save("list.xml", data);
                }
            }));
            generalMenu.add(subMenu);
            add(generalMenu);
        }

        private ArrayList<NamedNodeMap> open() throws Exception {
            ArrayList<NamedNodeMap> info = new ArrayList<>();
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                Document document = null;
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = factory.newDocumentBuilder();
                document = documentBuilder.parse(file);
                document.getDocumentElement().normalize();
                Node dataItem = document.getElementsByTagName("data").item(0);
                for (int i = 0; i < dataItem.getChildNodes().getLength(); ++i) {
                    if (dataItem.getChildNodes().item(i).getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    info.add(dataItem.getChildNodes().item(i).getAttributes());
                }
            }
            return info;
        }

        private void save(String fileName, ArrayList<ArrayList<String>> data) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File(fileName));
            if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = null;
                try {
                    documentBuilder = factory.newDocumentBuilder();
                }
                catch (ParserConfigurationException e1) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Cannot create file", "Error", JOptionPane.OK_OPTION);
                    return;
                }
                Document document = documentBuilder.newDocument();

                Element rootElement = document.createElement("data");
                for (int i = 0; i < data.size(); i++) {
                    Element item = document.createElement("item");
                    for (int j = 0; j < data.get(i).size(); j += 2) {
                        item.setAttribute(data.get(i).get(j), data.get(i).get(j + 1));
                    }
                    rootElement.appendChild(item);
                }
                document.appendChild(rootElement);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = null;
                try {
                    transformer = transformerFactory.newTransformer();
                }
                catch (TransformerConfigurationException e1) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Error: Problems with saving", "Error", JOptionPane.OK_OPTION);
                    return;
                }
                DOMSource source = new DOMSource(document);
                StreamResult result =  new StreamResult(file);
                try {
                    transformer.transform(source, result);
                }
                catch (TransformerException e1) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Problems with saving", "Error", JOptionPane.OK_OPTION);
                }
            }
        }
    }
}

class Calculator {
    public Numb getResult (ArrayList<Numb> operands, ArrayList<Character> operators) throws ArithmeticException {
        Numb result;
        ArrayList<Numb> expressions = new ArrayList<>();
        ArrayList<Character> pluses_minuses = new ArrayList<>();
        Numb curNumb = operands.get(0);
        int i = 0, j = 0;
        for (j = 0; j < operators.size(); j++) {
            char action = operators.get(j);
            if (action == '*') {
                i++;
                curNumb = Numb.multiply(curNumb, operands.get(i));
            }
            else if (action == '/') {
                i++;
                if (operands.get(i).isZero()) {
                    throw new ArithmeticException("Division by zero");
                }
                curNumb = Numb.divide(curNumb, operands.get(i));
            }
            else {
                expressions.add(curNumb);
                pluses_minuses.add(action);
                if (j == operators.size() - 1) {
                    curNumb = operands.get(operands.size() - 1);
                }
                else {
                    i++;
                    curNumb = operands.get(i);
                }
            }
        }
        expressions.add(curNumb);

        result = expressions.get(0);
        for (j = 0; j < pluses_minuses.size(); j++) {
            char action = pluses_minuses.get(j);
            if (action == '+') {
                result = Numb.add(result, expressions.get(j + 1));
            }
            else {
                result = Numb.subtract(result, expressions.get(j + 1));
            }
        }
        return result;
    }

}

public class Main {

    public static void main(String[] args) {
        MainFrame evaluatorFrame = new MainFrame("My evaluator");
        evaluatorFrame.setVisible(true);
    }
}

class Fraction {
    private int numerator;
    private int denominator;

    public Fraction (int numer, int denom) throws ArithmeticException {
        if (denom == 0) {
            throw new ArithmeticException("Division by zero");
        }
        numerator = numer;
        denominator = denom;
    }

    public static int gcd(int a, int b) { //greatest common divider
        int gcd = 1;
        for (int i = Math.min(a, b); i > 0; i--) {
            if (a % i == 0 && b % i == 0) {
                gcd = i;
                break;
            }
        }
        return gcd;
    }

    public boolean isZero() {
        return numerator == 0;
    }

    public static Fraction parseFraction(String s) throws RuntimeException {
        int ind = s.indexOf("/");
        int numer = 0;
        int denom = 1;
        if (ind == -1) {
            try {
                numer = Integer.parseInt(s);
            }
            catch (Exception e) {
                throw new RuntimeException("Incorrect format of input");
            }
            return new Fraction(numer, denom);
        }
        else {
            try {
                numer = Integer.parseInt(s.substring(0, ind));
                denom = Integer.parseInt(s.substring(ind + 1));
                if (denom == 0) {
                    throw new ArithmeticException("Division by zero");
                }
            }
            catch (Exception e) {
                throw new RuntimeException("Incorrect format of input");
            }
            return new Fraction(numer, denom);
        }
    }

    public static Fraction reduce(Fraction a){
        return new Fraction (a.numerator/gcd(a.numerator, a.denominator),a.denominator/gcd(a.numerator, a.denominator));
    }

    public static Fraction add(Fraction a, Fraction b) {
        return reduce(new Fraction(a.numerator * b.denominator + a.denominator * b.numerator, a.denominator * b.denominator));
    }

    public static Fraction subtract(Fraction a, Fraction b) {
        return reduce(new Fraction(a.numerator * b.denominator - a.denominator * b.numerator, a.denominator * b.denominator));
    }

    public static Fraction multiply(Fraction a, Fraction b) {
        return reduce(new Fraction(a.numerator * b.numerator, a.denominator * b.denominator));
    }

    public static Fraction divide(Fraction a, Fraction b) throws ArithmeticException {
        if (b.isZero()) {
            throw new ArithmeticException("Division by zero");
        }
        return reduce(new Fraction(a.numerator * b.denominator, a.denominator * b.numerator));
    }

    public String toString() {
        return numerator + "/" + denominator;
    }
}

class Numb {
    private Fraction rationalNumb;
    private double doubleNumb;
    private boolean rationalModeOn;

    public Numb(double d) {
        doubleNumb = d;
    }

    public Numb(Fraction f) {
        rationalNumb = f;
        rationalModeOn = true;
    }

    public static Numb parseNumb(String s, boolean ratModOn) throws RuntimeException {
        if (ratModOn) {
            try {
                return new Numb(Fraction.parseFraction(s));
            }
            catch (RuntimeException e) {
                throw e;
            }
        }
        else {
            try {
                return new Numb(Double.parseDouble(s));
            }
            catch (NumberFormatException e) {
                throw e;
            }
        }
    }

    public String toString() {
        if (rationalModeOn) {
            return rationalNumb.toString();
        }
        else {
            return (new Double(doubleNumb)).toString();
        }
    }

    public boolean isZero() {
        if (rationalModeOn) {
            return rationalNumb.isZero();
        }
        else return doubleNumb == 0;
    }

    public static Numb add(Numb a, Numb b) {
        if (a.rationalModeOn) {
            return new Numb(Fraction.add(a.rationalNumb, b.rationalNumb));
        }
        else {
            return new Numb(a.doubleNumb + b.doubleNumb);
        }
    }

    public static Numb subtract(Numb a, Numb b) {
        if (a.rationalModeOn) {
            return new Numb(Fraction.subtract(a.rationalNumb, b.rationalNumb));
        }
        else {
            return new Numb(a.doubleNumb - b.doubleNumb);
        }
    }

    public static Numb multiply(Numb a, Numb b) {
        if (a.rationalModeOn) {
            return new Numb(Fraction.multiply(a.rationalNumb, b.rationalNumb));
        }
        else {
            return new Numb(a.doubleNumb * b.doubleNumb);
        }
    }

    public static Numb divide(Numb a, Numb b) throws ArithmeticException {
        if (b.isZero()) {
            throw new ArithmeticException("Division by zero");
        }
        if (a.rationalModeOn) {
            return new Numb(Fraction.divide(a.rationalNumb, b.rationalNumb));
        }
        else {
            return new Numb(a.doubleNumb / b.doubleNumb);
        }
    }
}