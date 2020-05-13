package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ControlPanel extends JPanel {
    private String message = "";
    private JLabel messageLabel;

    public ControlPanel(MainFrame mainFrame) {
        setLayout(null);
        setBackground(Color.gray);
        setBounds(5,5,470,170);
        String controlPanelTitle = "Control Panel";
        Border controlPanelBorder = BorderFactory.createTitledBorder(controlPanelTitle);
        setBorder(controlPanelBorder);

        JLabel componentLabel = new JLabel("Component: ");
        Dimension componentLabelSize = componentLabel.getPreferredSize();
        componentLabel.setBounds(10, 20, componentLabelSize.width, componentLabelSize.height);
        componentLabel.setForeground(Color.white);
        add(componentLabel);

        JTextField swingComponentName = new JTextField();
        swingComponentName.setBounds(100, 20, 200, 20);
        add(swingComponentName);


        JLabel defaultTextLabel = new JLabel("Default text: ");
        Dimension defaultTextLabelSize = defaultTextLabel.getPreferredSize();
        defaultTextLabel.setBounds(10, 70, defaultTextLabelSize.width, defaultTextLabelSize.height);
        defaultTextLabel.setForeground(Color.white);
        add(defaultTextLabel);

        JTextField defaultTextName = new JTextField();
        defaultTextName.setBounds(100, 70, 200, 20);
        add(defaultTextName);

        JButton createComponentBtn = new JButton("Create");
        Dimension btnSize = createComponentBtn.getPreferredSize();
        createComponentBtn.setBounds(160, 120, btnSize.width, btnSize.height);
        add(createComponentBtn);

        createComponentBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.passInfoToDesignPanel(swingComponentName.getText(), defaultTextName.getText());
            }
        });

        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.white);
        add(messageLabel);

        JButton saveButton = new JButton("Save");
        Dimension saveButtonSize = saveButton.getPreferredSize();
        saveButton.setBounds(350, 15, saveButtonSize.width, saveButtonSize.height);
        add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("designPanel.xml")));
                    encoder.writeObject(mainFrame.getDesignPanelElements());
                    encoder.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton loadButton = new JButton("Load");
        Dimension loadButtonSize = loadButton.getPreferredSize();
        loadButton.setBounds(350, 65, loadButtonSize.width, loadButtonSize.height);
        add(loadButton);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("designPanel.xml")));
                    mainFrame.setDesignPanelElements((ArrayList) decoder.readObject());
                    decoder.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void setMessageFromDesignPanel(String message) {
        this.messageLabel.setText(message);
        Dimension messageLabelSize = messageLabel.getPreferredSize();
        messageLabel.setBounds(140, 150, messageLabelSize.width, messageLabelSize.height);
        this.revalidate();
    }
}
