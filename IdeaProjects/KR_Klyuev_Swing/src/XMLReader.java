import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XMLReader {
/*
    public static void writeDocument(Document document, final String path)
            throws TransformerFactoryConfigurationError {
        Transformer trf = null;
        DOMSource        src = null;
        FileOutputStream fos = null;
        try {
            trf = TransformerFactory.newInstance().newTransformer();
            src = new DOMSource(document);
            fos = new FileOutputStream(path);
            StreamResult result = new StreamResult(fos);
            trf.transform(src, result);
        } catch (TransformerException e) {
            e.printStackTrace(System.out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
*/

    public static void readTo(String fileName,ArrayList<Rectangle> rectangles) throws ClassCastException{
        File xmlFile= new File(fileName);
        DocumentBuilderFactory _documentBuilderFactory = DocumentBuilderFactory.newInstance();
            _documentBuilderFactory.setValidating(false);
        DocumentBuilder _documentBuilder = null;
        try {
            _documentBuilder = _documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Incorrect input format",
                    "Error", JOptionPane.OK_OPTION);
            return;
        }
        Document document = null;
        try {
            document = _documentBuilder.parse(xmlFile);
            //JOptionPane.showMessageDialog(null, "Error: Incorrect input format", "Error", JOptionPane.OK_OPTION);
        } catch (SAXException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Incorrect input format",
                    "Error", JOptionPane.OK_OPTION);
            return;
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Incorrect input format",
                    "Error", JOptionPane.OK_OPTION);
            return;
        }
        catch (IllegalArgumentException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Incorrect input format",
                    "Error", JOptionPane.OK_OPTION);
            return;
        }
        document.getDocumentElement().normalize();

        Element root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();

        Node check=nodeList.item(1);
        boolean flagOfOnlyType=true;
        for (int i = 1; i < nodeList.getLength(); i++) {
            if(!(nodeList.item(i).getNodeName().equals(check.getNodeName()))
                    &&
                    Node.ELEMENT_NODE == nodeList.item(i).getNodeType())
                flagOfOnlyType=false;
        }
        if(flagOfOnlyType==true) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    if (node.getNodeName().equals("Rectangle"))
                    {
                        Point tpoint1=new Point((Integer.parseInt(element.getElementsByTagName("X1").item(0).getTextContent())),
                                Integer.parseInt(element.getElementsByTagName("Y1").item(0).getTextContent()));
                            Point tpoint2=new Point((Integer.parseInt(element.getElementsByTagName("X2").item(0).getTextContent())),
                                    Integer.parseInt(element.getElementsByTagName("Y2").item(0).getTextContent()));
                            int tsquare= Integer.parseInt(element.getElementsByTagName("Square").item(0).getTextContent());
                            rectangles.add(new Rectangle(tpoint1,tpoint2, tsquare));
                }}
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "All elements of the file must be of only one derived class!",
                    "Error", JOptionPane.OK_OPTION);
            return;
        }
    }
}