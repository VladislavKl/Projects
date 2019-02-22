
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main{
    public static void show(Document doc){
        Node root = doc.getDocumentElement();
        NodeList list = root.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node elem = list.item(i);
            NodeList descr = elem.getChildNodes();
            for (int j = 0; j < descr.getLength(); j++) {
                if(descr.item(j).getNodeType() != Node.TEXT_NODE)
                    System.out.println(descr.item(j).getNodeName() + ": " + descr.item(j).getChildNodes().item(0).getTextContent());
            }
            if(list.item(i).getNodeType() != Node.TEXT_NODE) {
                System.out.println("\n/////////////////////////////////////////////////\n");
            }
        }
    }

    public static void write(Document doc) throws TransformerException, FileNotFoundException {
        Transformer tr = TransformerFactory.newInstance().newTransformer();
        tr.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        FileOutputStream fos = new FileOutputStream("output.xml");
        StreamResult result = new StreamResult(fos);
        tr.transform(source, result);
    }

    public static void addFolder(Document doc){
        Node root = doc.getDocumentElement();
        String str;
        Scanner sc = new Scanner(System.in);

        Element folder = doc.createElement("Folder");

        Element name = doc.createElement("Name");
        System.out.print("Enter name:");
        str = sc.nextLine();
        name.setTextContent(str);

        Element capacity = doc.createElement("Capacity");
        System.out.print("Enter capacity:");
        str = sc.nextLine();
        capacity.setTextContent(str);

        Element EmptySpace = doc.createElement("EmptySpace");
        System.out.print("Enter empty space amount:");
        str = sc.nextLine();
        EmptySpace.setTextContent(str);

        Element lv = doc.createElement("LastVisitor");
        System.out.print("Enter the name of the last visitor:");
        str = sc.nextLine();
        lv.setTextContent(str);

        Element creatr = doc.createElement("Creator");
        System.out.print("Enter creator's name:");
        str = sc.nextLine();
        creatr.setTextContent(str);

        Element tp = doc.createElement("Type");
        System.out.print("Enter type:");
        str = sc.nextLine();
        tp.setTextContent(str);

        Element acs = doc.createElement("Access");
        System.out.print("Enter access:");
        str = sc.nextLine();
        acs.setTextContent(str);


        Element qof = doc.createElement("QuantityOfFiles");
        System.out.print("Enter quantity of files in the folder:");
        str = sc.nextLine();
        qof.setTextContent(str);

        folder.appendChild(name);
        folder.appendChild(capacity);
        folder.appendChild(EmptySpace);
        folder.appendChild(lv);
        folder.appendChild(creatr);
        folder.appendChild(tp);
        folder.appendChild(acs);
        folder.appendChild(qof);

        root.appendChild(folder);
    }

    public static void changeData(Document doc){
        Scanner sc = new Scanner(System.in);
        String str;
        System.out.println("Enter the name of the folder to change: ");
        str = sc.nextLine();

        Node root = doc.getDocumentElement();
        NodeList list = root.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node elem = list.item(i);
            NodeList descr = elem.getChildNodes();
            for (int j = 0; j < descr.getLength(); j++)
                if (descr.item(j).getNodeName().equals("Name") && descr.item(j).getTextContent().equals(str)){
                    System.out.print("Enter the name of the field to change: ");
                    Scanner sc1 = new Scanner(System.in);
                    String temp = sc1.nextLine();
                    for(int k = 0; k < descr.getLength(); k++)
                        if(descr.item(k).getNodeName().equals(temp)){
                            System.out.print("Enter changed data: ");
                            temp = sc1.nextLine();
                            descr.item(k).setTextContent(temp);
                            break;
                        }
                    break;
                }
        }
    }

    public static void main(String[] args){
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            File file = new File("input");
            Document doc = builder.parse(file);

            Scanner sc = new Scanner(System.in);
            int i = -1;

            while (i != 0){
                System.out.println("1. Show folders");
                System.out.println("2. Add a new folder");
                System.out.println("3. Change data of a certain folder");
                System.out.println("4. Exit");

                i = sc.nextInt();
                switch (i) {
                    case 1:
                        show(doc);
                        break;
                    case 2:
                        addFolder(doc);
                        break;
                    case 3:
                        changeData(doc);
                        break;
                    case 4:
                        write(doc);
                        break;
                    default:
                        break;
                }
            }

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}