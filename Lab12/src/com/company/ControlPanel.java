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
        createComponentBtn.setBounds(390, 40, btnSize.width, btnSize.height);
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
        saveButton.setBounds(320, 15, saveButtonSize.width, saveButtonSize.height);
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
        loadButton.setBounds(320, 65, loadButtonSize.width, loadButtonSize.height);
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

        JLabel xLabel = new JLabel("X: ");
        xLabel.setBounds(10, 110, 20, 20);
        xLabel.setForeground(Color.white);
        add(xLabel);

        JTextField xField = new JTextField();
        xField.setBounds(30, 110, 40,15);
        add(xField);

        JLabel yLabel = new JLabel("Y: ");
        yLabel.setBounds(10, 130, 20, 20);
        yLabel.setForeground(Color.white);
        add(yLabel);

        JTextField yField = new JTextField();
        yField.setBounds(30, 130, 40,15);
        add(yField);

        JLabel textLabel = new JLabel("Text: ");
        textLabel.setBounds(80, 110, 40, 20);
        textLabel.setForeground(Color.white);
        add(textLabel);

        JTextField textField = new JTextField();
        textField.setBounds(120, 110, 200,20);
        add(textField);

        JLabel widthLabel = new JLabel("Width: ");
        widthLabel.setBounds(80, 135, 40, 20);
        widthLabel.setForeground(Color.white);
        add(widthLabel);

        JTextField widthField = new JTextField();
        widthField.setBounds(120, 135, 40,15);
        add(widthField);

        JLabel heightLabel = new JLabel("Height: ");
        heightLabel.setBounds(200, 135, 60, 20);
        heightLabel.setForeground(Color.white);
        add(heightLabel);

        JTextField heightField = new JTextField();
        heightField.setBounds(250, 135, 40,15);
        add(heightField);

        JButton applyBtn = new JButton("Apply");
        Dimension size = applyBtn.getPreferredSize();
        applyBtn.setBounds(370, 120, size.width, size.height);
        add(applyBtn);

        applyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.applyChanges(xField.getText(), yField.getText(), widthField.getText(), heightField.getText(), textField.getText());
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
