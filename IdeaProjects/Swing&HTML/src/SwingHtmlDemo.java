import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;

public class SwingHtmlDemo {
    public static void main(String [] args){

        SwingUtilities.invokeLater(() -> {
                ApplicationFrame mainFrame = new ApplicationFrame();
                mainFrame.setVisible(true);
            });
        }

}

class ApplicationFrame extends JFrame {

    JFXPanel javafxPanel;
    WebView webComponent;
    JPanel mainPanel;

    JTextField urlField;
    JButton goButton;

    public ApplicationFrame() {

        javafxPanel = new JFXPanel();

        initSwingComponents();

        loadJavaFXScene();
    }

    private void initSwingComponents() {

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(javafxPanel, BorderLayout.CENTER);

        JPanel urlPanel = new JPanel(new FlowLayout());
        urlField = new JTextField();
        urlField.setColumns(50);
        urlPanel.add(urlField);
        goButton = new JButton("<html><font size=+4><font color = GREEN>"
                + "<center><i>Press <b>me</b> to go!</i>");

        goButton.addActionListener((e) -> Platform.runLater(() -> {
                        String url = urlField.getText();
                        if (url != null && url.length() > 0) {
                            if (!url.contains("https://"))
                                webComponent.getEngine().load("https://"+url);
                        }
                }));

        urlPanel.add(goButton);
        mainPanel.add(urlPanel, BorderLayout.NORTH);

        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
    }

    private void loadJavaFXScene() {
        Platform.runLater(() -> {

            BorderPane borderPane = new BorderPane();
            webComponent = new WebView();

            webComponent.getEngine().load("http://google.com/");

            borderPane.setCenter(webComponent);
            Scene scene = new Scene(borderPane, 450, 450);
            javafxPanel.setScene(scene);

        });
    }
}