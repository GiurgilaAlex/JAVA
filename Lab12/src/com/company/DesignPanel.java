package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class DesignPanel extends JPanel {
    private MainFrame mainFrame;
    private JComponent selectedComponent;

    public void setElements(List<Object> elements) {
        this.removeAll();
        this.repaint();
        this.elements = elements;
        for(Object obj: this.elements) {
            this.add((Component) obj);
            ((Component) obj).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        selectedComponent = ((JComponent) obj);
                        BeanInfo bi = Introspector.getBeanInfo(obj.getClass());
                        List<String[]> data = new ArrayList<>();
                        for(PropertyDescriptor prop: bi.getPropertyDescriptors()) {
                            String[] a = {prop.getName(), prop.getShortDescription()};
                            data.add(a);
                        }
                        mainFrame.setTableData(data);
                    } catch (IntrospectionException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        this.revalidate();
    }

    public List<Object> getElements() {
        return elements;
    }

    private List<Object> elements;

    public DesignPanel(MainFrame mainFrame) {
        this.elements = new ArrayList<>();
        this.mainFrame = mainFrame;
        setLayout(null);

        setBackground(Color.white);
        setBounds(5,180,470,270);

        String controlPanelTitle = "Design Panel";
        Border controlPanelBorder = BorderFactory.createTitledBorder(controlPanelTitle);
        setBorder(controlPanelBorder);
    }

    public void setData(String componentName, String defaultText) {
        try {
            Class<?> cls = Class.forName(componentName);
            Constructor<?> ctor = cls.getConstructor(String.class);
            Object obj = ctor.newInstance(defaultText);
            JComponent cmp = ((JComponent) obj);
            cmp.setBounds(100, 100, 50,50);
            this.add(cmp);
            this.repaint();
            (cmp).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        selectedComponent = cmp;
                        BeanInfo bi = Introspector.getBeanInfo(cmp.getClass());
                        List<String[]> data = new ArrayList<>();
                        for(PropertyDescriptor prop: bi.getPropertyDescriptors()) {
                            String[] a = {prop.getName(), prop.getShortDescription()};
                            data.add(a);
                        }
                        mainFrame.setTableData(data);
                    } catch (IntrospectionException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            this.elements.add(cmp);
            this.mainFrame.passInfoToControlPanel("");
        } catch (InstantiationException e) {
            System.out.println("Something went wrong!");
        } catch (InvocationTargetException e) {
            System.out.println("Something went wrong!");
        } catch (NoSuchMethodException e) {
            System.out.println("Something went wrong!");
        } catch (IllegalAccessException e) {
            System.out.println("Something went wrong!");
        } catch (ClassNotFoundException e) {
            this.mainFrame.passInfoToControlPanel("Class name is incorrect");
        }
    }

    public void applyChanges(String x, String y, String width, String height, String text) {
        int tempX = this.selectedComponent.getX();
        int tempY = this.selectedComponent.getY();
        int tempWidth = this.selectedComponent.getWidth();
        int tempHeight = this.selectedComponent.getHeight();

        if (!text.equals("")) {
            if (this.selectedComponent instanceof JButton) {
                JButton jbtn = ((JButton) this.selectedComponent);
                jbtn.setText(text);
            } else if (this.selectedComponent instanceof JLabel) {
                JLabel jlbl = ((JLabel) this.selectedComponent);
                jlbl.setText(text);
            } else if (this.selectedComponent instanceof JTextField) {
                JTextField jtxt = ((JTextField) this.selectedComponent);
                jtxt.setText(text);
            } else if (this.selectedComponent instanceof JCheckBox) {
                JCheckBox jck = ((JCheckBox) this.selectedComponent);
                jck.setText(text);
            }
        }

        if (!x.equals("")) {
            tempX = Integer.parseInt(x);
        }
        if (!y.equals("")) {
            tempY = Integer.parseInt(y);
        }
        if (!width.equals("")) {
            tempWidth = Integer.parseInt(width);
        }
        if (!height.equals("")) {
            tempHeight = Integer.parseInt(height);
        }

        this.selectedComponent.setBounds(tempX, tempY, tempWidth, tempHeight);
        this.repaint();
    }
}
