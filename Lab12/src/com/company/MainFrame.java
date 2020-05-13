package com.company;

import javax.swing.*;
import java.util.List;

public class MainFrame extends JFrame {
    private ControlPanel controlPanel;
    private DesignPanel designPanel;
    private PropertiesTable propertiesTable;

    public DesignPanel getDesignPanel() {
        return designPanel;
    }

    public MainFrame()  {
        super("Build a Swing graphical interface!");

        controlPanel = new ControlPanel(this);
        designPanel = new DesignPanel(this);
        propertiesTable = new PropertiesTable();
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 800);

        add(controlPanel);
        add(designPanel);
        add(propertiesTable);
        setVisible(true);
    }

    public void passInfoToDesignPanel(String componentName, String defaultText) {
        designPanel.setData(componentName, defaultText);
    }

    public void passInfoToControlPanel(String message) {
        controlPanel.setMessageFromDesignPanel(message);
    }

    public List<Object> getDesignPanelElements() {
        return this.designPanel.getElements();
    }

    public void setDesignPanelElements(List<Object> elements) {
        this.designPanel.setElements(elements);
    }

    public void setTableData(List<String[]> data) {
        propertiesTable.setTableConfig(data);
    }

    public void applyChanges(String x, String y, String width, String height, String text) {
        this.designPanel.applyChanges(x, y, width, height, text);
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
    }
}
