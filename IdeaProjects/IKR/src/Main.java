import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

abstract class Shape {
    protected Point p1, p2;

    public Shape(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public int square() {
        return Math.abs(p2.y - p1.y) * Math.abs(p2.x - p1.x);
    }
}

class Rectangle extends Shape {

    private Point p3, p4;

    public Rectangle(Point p1, Point p2) {
        super(p1, p2);
        p3 = new Point(p2.x, p1.y);
        p4 = new Point(p1.x, p2.y);
    }

    public String toString() {
        return "(" + p1.x + ", " + p1.y + "), " + "(" + p3.x + ", " + p3.y + "),(" + p2.x + ", " + p2.y + "), (" + p4.x + ", " + p4.y + "), " + "площадь: " + square();
    }

}

class MyFrame extends JFrame {
    private MyMenuBar menubar;
    private ArrayList<Rectangle> rects = new ArrayList<>();
    private JTabbedPane tabbedPane;
    private ArrayList<JPanel> panels = new ArrayList<>();
    private Comparator<Rectangle> compSquare, compPoints;
    HashMap<Point, Integer> rectPoints = new HashMap<>();

    public MyFrame() {}

    public MyFrame (String name) {
        super(name);
        compSquare = new Comparator<Rectangle>() {

            @Override
            public int compare(Rectangle r1, Rectangle r2) {
                return Integer.compare(r1.square(), r2.square());
            }

        };

        compPoints = new Comparator<Rectangle>() {

            @Override
            public int compare(Rectangle r1, Rectangle r2) {
                if (Integer.compare(r1.p1.x, r2.p1.x) != 0) {
                    return Integer.compare(r1.p1.x, r2.p1.x);
                }
                return Integer.compare(r1.p1.y, r2.p1.y);
            }

        };

    }

    public void form() {
        menubar = new MyMenuBar();
        setJMenuBar(menubar);

        tabbedPane = new JTabbedPane();
        panels.add(new JPanel());
        tabbedPane.addTab("От угла", panels.get(panels.size() - 1));
        panels.add(new JPanel());
        tabbedPane.addTab("По площади", panels.get(panels.size() - 1));
        panels.add(new JPanel());
        tabbedPane.addTab("Точки", panels.get(panels.size() - 1));

        add(tabbedPane);
    }

    private void displaySortedRects(int index) {
        JList list = new JList();
        DefaultListModel listModel = new DefaultListModel();

        for (Rectangle rect: rects) {
            listModel.addElement(rect);
        }

        list.setModel(listModel);
        JScrollPane listPane = new JScrollPane(list);
        panels.get(index).removeAll();
        panels.get(index).add(listPane);
        MyFrame.this.revalidate();
        MyFrame.this.repaint();
    }

    private void formMap() {
        for (Rectangle rect: rects) {
            if (!rectPoints.containsKey(rect.p1)) {
                rectPoints.put(rect.p1, 1);
            }
            else {
                rectPoints.put(rect.p1, rectPoints.get(rect.p1) + 1);
            }
            if (!rectPoints.containsKey(rect.p2)) {
                rectPoints.put(rect.p2, 1);
            }
            else {
                rectPoints.put(rect.p2, rectPoints.get(rect.p2) + 1);
            }
        }
    }

    class MyMenuBar extends JMenuBar {
        private JMenu filemenu, datamenu;
        private JMenuItem openfile, angle, square, points, search, quantity;

        public MyMenuBar() {
            filemenu = new JMenu("Файл");
            openfile = new JMenuItem(new AbstractAction("Открыть") {

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

                        SAXPars saxp = new SAXPars();

                        try {
                            parser.parse(f, saxp);
                        }
                        catch (FileNotFoundException e1) {
                            JOptionPane.showMessageDialog(MyFrame.this, "ќшибка: не удаетс€ найти указанный файл.", "ќшибка", JOptionPane.OK_OPTION);
                            return;
                        }
                        catch (IllegalArgumentException e1) {
                            JOptionPane.showMessageDialog(MyFrame.this, "ќшибка: неверный формат некоторых входных данных.", "ќшибка", JOptionPane.WARNING_MESSAGE);
                        }
                        catch (SAXException | IOException e1) {
                            JOptionPane.showMessageDialog(MyFrame.this, "Ќеизвестна€ ошибка.", "ќшибка", JOptionPane.OK_OPTION);
                            return;
                        }
                        rects.clear();
                        rects = saxp.getRects();
                        formMap();
                        JOptionPane.showMessageDialog(MyFrame.this, "Количество: " + rects.size(), "Чтение файла", JOptionPane.INFORMATION_MESSAGE);
                    }

                }

            });



            filemenu.add(openfile);
            add(filemenu);
            datamenu = new JMenu("Данные");
            angle = new JMenuItem(new AbstractAction("От угла") {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    rects.sort(compPoints);
                    displaySortedRects(0);
                }

            });


            square = new JMenuItem(new AbstractAction("По площади") {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    rects.sort(compSquare);
                    displaySortedRects(1);

                }
            });


            points = new JMenuItem(new AbstractAction("Точки") {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    JList list = new JList();
                    DefaultListModel listModel = new DefaultListModel();

                    HashSet<Point> points = new HashSet<>();

                    for (Rectangle rect: rects) {
                        points.add(rect.p1);
                        points.add(rect.p2);
                    }

                    for (Point point: points) {
                        listModel.addElement("( " + point.x + ", " + point.y + ")");
                    }

                    list.setModel(listModel);
                    JScrollPane listPane = new JScrollPane(list);
                    panels.get(2).removeAll();
                    panels.get(2).add(listPane);
                    MyFrame.this.revalidate();
                    MyFrame.this.repaint();
                }
            });

            search = new JMenuItem(new AbstractAction("Поиск") {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    String res = "";
                    int minSquare = Collections.min(rects, compSquare).square();
                    for (Rectangle rect: rects) {
                        if (rect.square() == minSquare * 2) {
                            res = rect.toString();
                            break;
                        }
                    }
                    if (res.equals("")) {
                        JOptionPane.showMessageDialog(MyFrame.this, "ѕр€моугольник не найден", "ѕоиск", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(MyFrame.this, "»скомый пр€моугольник: " + res, "ѕоиск", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });

            quantity = new JMenuItem(new AbstractAction(" оличество") {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = JOptionPane.showInputDialog(MyFrame.this, "¬ведите координаты точки:", " оличество", JOptionPane.PLAIN_MESSAGE);
                    System.out.println(input);
                    int x, y;
                    Point p = null;
                    String[] items = input.split(",");
                    try {
                        x = Integer.parseInt(items[0]);
                        y = Integer.parseInt(items[0]);
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(MyFrame.this, "ќшибка: неверные входные данные.", "ќшибка", JOptionPane.OK_OPTION);
                        return;
                    }
                    p = new Point(x, y);
                    Integer quan = rectPoints.get(p);
                    if (quan == null) {
                        quan = 0;
                    }
                    JOptionPane.showMessageDialog(MyFrame.this, " оличество точек: " + quan, " оличество", JOptionPane.INFORMATION_MESSAGE);
                }

            });
            datamenu.add(angle);
            datamenu.add(square);
            datamenu.add(points);
            datamenu.add(search);
            datamenu.add(quantity);
            add(datamenu);
        }
    }

    class SAXPars extends DefaultHandler {
        private ArrayList<Rectangle> rects;
        private boolean isrect = false;
        private Point p1 = null, p2 = null;

        public SAXPars() {}

        @Override
        public void startDocument() {
            rects = new ArrayList<>();
        }

        @Override
        public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
            if (qName.equals("rect")) {
                isrect = true;
            }
            else if (isrect && qName.equals("point")) {
                try {
                    if (p1 == null) {
                        p1 = new Point(Integer.parseInt(atts.getValue("x")), Integer.parseInt(atts.getValue("y")));
                    }
                    else {
                        p2 = new Point(Integer.parseInt(atts.getValue("x")), Integer.parseInt(atts.getValue("y")));
                    }
                }
                catch (Exception ex) {
                    isrect = false;
                    JOptionPane.showMessageDialog(MyFrame.this, "ќшибка: неверный формат входных данных.", "ќшибка", JOptionPane.OK_OPTION);
                }
            }
        }
        @Override
        public void endElement(String namespaceURI, String localName, String qName) {
            if (p1 != null && p2 != null && isrect) {
                rects.add(new Rectangle(p1, p2));
                p1 = null;
                p2 = null;
                isrect = false;
            }
        }

        public ArrayList<Rectangle> getRects() {
            return rects;
        }

    }

}

public class Main {

    public static void main(String[] args) {
        MyFrame frame = new MyFrame("КР");
        frame.setSize(new Dimension(1500, 900));
        frame.form();
        frame.setVisible(true);

    }

}
