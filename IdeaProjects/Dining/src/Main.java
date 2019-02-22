import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


class Cleaner {
    private String firm = "";
    private enum CleanerColor {BLACK, WHITE, GREY, RED, BLUE};
    private CleanerColor color = CleanerColor.BLACK;
    private int power = 0;
    private String image = "";

    public static ArrayList<String> getStringColors() {
        ArrayList<String> res = new ArrayList<>();
        for (CleanerColor cc: CleanerColor.values()) {
            res.add(cc.toString());
        }
        return res;
    }

    public void setFirm(String f) {
        firm = f;
    }

    public String getFirm() {
        return firm;
    }

    public void setColor(String c) throws IllegalArgumentException {
        color = CleanerColor.valueOf(c);
    }

    public String getColor() {
        return color.toString();
    }

    public void setPower(String p) throws NumberFormatException {
        power = Integer.parseInt(p);
    }

    public int getPower() {
        return power;
    }

    public void setImage(String im) {
        image = im;
    }

    public ArrayList<String> getInfo() {
        ArrayList<String> res = new ArrayList<>();
        res.add(firm);
        res.add(color.toString());
        res.add(power + "");
        res.add(image);
        return res;
    }
}

class WashCleaner extends Cleaner {
    private double volume = 0;

    public void setVolume(String v) throws NumberFormatException {
        volume = Double.parseDouble(v);
    }

    public ArrayList<String> getInfo() {
        ArrayList<String> res = super.getInfo();
        res.add(volume + "");
        return res;
    }
}

class RobotCleaner extends Cleaner {
    private boolean hasScreen = false;

    public void setHasScreen(String hs) throws IllegalArgumentException {
        if (hs.toLowerCase().equals("true") || hs.toLowerCase().equals("false")) {
            hasScreen = Boolean.parseBoolean(hs);
        }
        else throw new IllegalArgumentException();
    }

    public ArrayList<String> getInfo() {
        ArrayList<String> res = super.getInfo();
        if (hasScreen) {
            res.add("да");
        }
        else {
            res.add("нет");
        }
        return res;
    }
}

class Model {

    private ArrayList<Cleaner> cleaners = new ArrayList<>();
    private TreeSet<String> firms = new TreeSet<>();
    private Cleaner curcleaner = null;
    private int curType = -1; //0 - wash, 1 - robot

    //TODO
    public ArrayList<String> getColors() {
        return Cleaner.getStringColors();
		/*String[] res = {"Черный", "Белый", "Серый", "Красный", "Синий"};
		return res;*/
    }

    public ArrayList<Cleaner> getCleaners() {
        return cleaners;
    }

    public void setFirms() {
        if (firms.size() > 0) {
            return;
        }
        for (Cleaner cleaner: cleaners) {
            firms.add(cleaner.getFirm());
        }
    }

    public TreeSet<String> getFirms() {
        return firms;
    }

    public TreeSet<String> getFirmsByColor(String c) {
        TreeSet<String> res = new TreeSet<>();
        for (Cleaner cleaner: cleaners) {
            if (cleaner.getColor().equals(c)) {
                res.add(cleaner.getFirm());
            }
        }
        return res;
    }

    public void sort() {
        Comparator<Cleaner> comp = new Comparator<Cleaner>() {

            @Override
            public int compare(Cleaner cl1, Cleaner cl2) {
                if (cl1.getFirm().compareTo(cl2.getFirm()) != 0) {
                    return cl1.getFirm().compareTo(cl2.getFirm());
                }
                if (cl1.getPower() != cl2.getPower()) {
                    return cl2.getPower() - cl1.getPower();
                }
                if (cl1.getColor().compareTo(cl2.getColor()) != 0) {
                    return cl1.getColor().compareTo(cl2.getColor());
                }
                return 0;
            }

        };
        cleaners.sort(comp);
    }

    public String[] getHeader() {
        if (curType == 0) {
            String[] res = {"Фирма", "Цвет", "Мощность", "Картинка", "Объем контейнера"};
            return res;
        }
        else if (curType == 1) {
            String[] res = {"Фирма", "Цвет", "Мощность", "Картинка", "Экран"};
            return res;
        }
        return null;
    }

    public void clear() {
        cleaners.clear();
        firms.clear();
    }

    public void process(String qName, Attributes atts) throws IllegalArgumentException, NumberFormatException {
        String attname;
        if (qName.equals("type")) {
            if (atts.getValue("value").equals("wash")) {
                curType = 0;
            }
            else if (atts.getValue("value").equals("robot")) {
                curType = 1;
            }
            return;
        }
        if (qName.equals("cleaner")) {
            switch (curType) {
                case 0:
                    curcleaner = new WashCleaner();
                    break;
                case 1:
                    curcleaner = new RobotCleaner();
                    break;
                default:
                    break;
            }
            for (int i = 0; i < atts.getLength(); i++) {
                attname = atts.getQName(i);
                if (attname.equals("firm")) {
                    curcleaner.setFirm(atts.getValue(i));
                }
                else if (attname.equals("color")) {
                    curcleaner.setColor(atts.getValue(i));
                }
                else if (attname.equals("power")) {
                    curcleaner.setPower(atts.getValue(i));
                }
                else if (attname.equals("image")) {
                    curcleaner.setImage(atts.getValue(i));
                }
                else if (attname.equals("volume")) {
                    if (curType != 0) {
                        continue;
                    }
                    ((WashCleaner) curcleaner).setVolume(atts.getValue(i));
                }
                else if (attname.equals("hasscreen")) {
                    if (curType != 1) {
                        continue;
                    }
                    ((RobotCleaner) curcleaner).setHasScreen(atts.getValue(i));
                }

            }
        }
        if (curcleaner != null) {
            cleaners.add(curcleaner);
        }
        curcleaner = null;
    }
}

class SAXPars extends DefaultHandler {
    private Model model;

    public SAXPars() {}

    public SAXPars(Model m) {
        model = m;
    }

    @Override
    public void startDocument() {
        model.clear();
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException, IllegalArgumentException, NumberFormatException {
        model.process(qName, atts);
    }


}

class MyFrame extends JFrame {

    private CommonPanel panel1, panel2, panel3;
    private Model model = new Model();

    public MyFrame() {}

    public MyFrame (String name) {
        super(name);
    }

    public void form() {
        setLayout(new GridLayout(1, 3));
        panel1 = new InfoPanel();
        panel2 = new FirmsPanel();
        panel3 = new ColorPanel();
        add(panel1);
        add(panel2);
        add(panel3);
    }

    class CommonPanel extends JPanel {
        protected JButton btnAction;
        protected JPanel btnWrapper;
        protected Font font = new Font("Serif", Font.PLAIN, 20);
        public CommonPanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            btnWrapper = new JPanel();
            btnAction = new JButton();
            btnAction.setFont(font);
            btnWrapper.add(btnAction);
            btnWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        }
    }

    class InfoPanel extends CommonPanel {
        private JTable table;
        private JPanel tablePanel = new JPanel();
        private DefaultTableModel tableModel;
        public InfoPanel() {
            super();
            btnAction.setText("Открыть");

            btnAction.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    JFileChooser fc = new JFileChooser();
                    if (fc.showOpenDialog(MyFrame.this) == JFileChooser.APPROVE_OPTION) {
                        File f = fc.getSelectedFile();

                        SAXParserFactory factory = SAXParserFactory.newInstance();
                        SAXParser parser = null;
                        try {
                            parser = factory.newSAXParser();
                        } catch (ParserConfigurationException e1) {
                            JOptionPane.showMessageDialog(MyFrame.this, "Неизвестная ошибка.", "Ошибка", JOptionPane.OK_OPTION);
                            return;
                        } catch (SAXException e1) {
                            JOptionPane.showMessageDialog(MyFrame.this, "Неизвестная ошибка.", "Ошибка", JOptionPane.OK_OPTION);
                            return;
                        }

                        SAXPars saxp = new SAXPars(model);

                        try {
                            parser.parse(f, saxp);
                        }
                        catch (FileNotFoundException e1) {
                            JOptionPane.showMessageDialog(MyFrame.this, "Ошибка: не удается найти указанный файл.", "Ошибка", JOptionPane.OK_OPTION);
                            return;
                        }
                        catch (IllegalArgumentException e1) {
                            JOptionPane.showMessageDialog(MyFrame.this, "Ошибка: неверный формат некоторых входных данных.", "Ошибка", JOptionPane.WARNING_MESSAGE);
                        }
                        catch (SAXException | IOException e1) {
                            JOptionPane.showMessageDialog(MyFrame.this, "Неизвестная ошибка.", "Ошибка", JOptionPane.OK_OPTION);
                            return;
                        }

                        model.sort();
                        displayCleaners();
                    }
                }

            });
            add(btnWrapper);
        }

        public void displayCleaners() {

            ArrayList<Cleaner> cleaners = model.getCleaners();
            String header[] = model.getHeader();
            if (header == null) return;
            table = new JTable(0, header.length);
            tableModel = new DefaultTableModel(header, 0);

            for (Cleaner cleaner: cleaners) {
                tableModel.addRow(cleaner.getInfo().toArray());
            }

            table.setModel(tableModel);
            //table.setFont(new Font(fontFamily, fontStyle, height / 45));
            //table.setRowHeight(height / 35);
            remove(tablePanel);
            JScrollPane tablePane = new JScrollPane(table);
            tablePanel.removeAll();
            tablePanel.add(tablePane);
            add(tablePanel);


            revalidate();
            repaint();
        }
    }

    class FirmsPanel extends CommonPanel {
        private JList list;
        private JPanel listPanel = new JPanel();
        private DefaultListModel listModel;

        public FirmsPanel() {
            btnAction.setText("Показать фирмы");
            listPanel.setLayout(new BorderLayout());

            btnAction.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    model.setFirms();
                    TreeSet<String> firms = model.getFirms();
                    list = new JList();
                    listModel = new DefaultListModel();

                    for (String firm: firms) {
                        listModel.addElement(firm);
                    }

                    list.setModel(listModel);
                    remove(listPanel);
                    JScrollPane listPane = new JScrollPane(list);
                    listPanel.removeAll();
                    JLabel listLabel = new JLabel("Производители:");
                    listLabel.setFont(font);
                    listPanel.add(listLabel, BorderLayout.NORTH);
                    listPanel.add(listPane, BorderLayout.CENTER);

                    add(listPanel);

                    revalidate();
                    repaint();
                }

            });

            add(btnWrapper);
        }
    }

    class ColorPanel extends CommonPanel {
        private JList list;
        private JPanel listPanel = new JPanel();
        private DefaultListModel listModel;
        private ButtonGroup btnGroup = new ButtonGroup();
        private ArrayList<JRadioButton> buttons = new ArrayList<>();
        private TreeSet<String> firms = new TreeSet<>();
        public ColorPanel() {
            btnAction.setText("Найти фирмы");
            listPanel.setLayout(new BorderLayout());

            btnAction.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    for (JRadioButton rb: buttons) {
                        if (rb.isSelected()) {
                            int index = buttons.indexOf(rb);
                            switch (index) {
                                case 0:
                                    firms = model.getFirmsByColor("BLACK");
                                    break;
                                case 1:
                                    firms = model.getFirmsByColor("WHITE");
                                    break;
                                case 2:
                                    firms = model.getFirmsByColor("GREY");
                                    break;
                                case 3:
                                    firms = model.getFirmsByColor("RED");
                                    break;
                                case 4:
                                    firms = model.getFirmsByColor("BLUE");
                                    break;
                                default:
                                    break;
                            }
                            displayFirms();
                            break;
                        }
                    }
                }

            });

            JLabel colorLabel = new JLabel("Выберите цвет: ");
            colorLabel.setFont(new Font(font.getFontName(), font.getStyle(), 25));
            colorLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));


            JPanel radioButtonsPanel = new JPanel();
            radioButtonsPanel.setLayout(new BoxLayout(radioButtonsPanel, BoxLayout.Y_AXIS));
            for (String color: model.getColors()) {
                buttons.add(new JRadioButton(color));
            }

            radioButtonsPanel.add(colorLabel);
            for (JRadioButton button: buttons) {
                button.setFont(font);
                btnGroup.add(button);
                radioButtonsPanel.add(button);
            }

            radioButtonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            add(radioButtonsPanel);
            add(btnWrapper);
        }

        public void displayFirms() {
            list = new JList();
            listModel = new DefaultListModel();

            for (String firm: firms) {
                listModel.addElement(firm);
            }

            list.setModel(listModel);
            remove(listPanel);
            JScrollPane listPane = new JScrollPane(list);
            listPanel.removeAll();
            JLabel listLabel = new JLabel("Производители:");
            listLabel.setFont(font);
            listPanel.add(listLabel, BorderLayout.NORTH);
            listPanel.add(listPane, BorderLayout.CENTER);

            add(listPanel);

            revalidate();
            repaint();
        }
    }

}

public class Main {

    public static void main(String[] args) {
        MyFrame frame = new MyFrame("Итоговая контрольная работа");
        frame.setSize(new Dimension(1500, 900));
        frame.form();
        frame.setVisible(true);

    }

}