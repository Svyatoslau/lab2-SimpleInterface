package bsu.rfe.java.group10.lab2.Yaroshevich.varC2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {
    //размер окна
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;

    //текстовые поля
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;

    //Внутреняя память
    private Double mem1;
    private Double mem2;
    private Double mem3;

    //группа радио кнопок для отображения уникальности выделения в группе
    private ButtonGroup radioButtonsForFunc = new ButtonGroup();

    //Контейнер для отображения радио-кнопок:
    // формул
    private Box hboxFormulaType = Box.createHorizontalBox();
    // памяти
    private Box hboxMemoryType = Box.createHorizontalBox();

    private int formuaId = 1;
    private String pathToImageFunc ="C:\\study\\Java\\lab2 - Simple Interface\\images\\func1.jpg";
    JLabel labelForFormula = new JLabel();

    //Формула №1 для расчёта
    public Double calculate1(Double x,Double y,Double z){
        return Math.pow((2*Math.log(1+x)+Math.cos(Math.PI*Math.pow(z,3))),Math.sin(y))+Math.pow(Math.pow(Math.E,Math.pow(x,2))+Math.cos(Math.pow(Math.E,z))+Math.pow(1/y,1/2),1/x);
    }

    // Считывания картинки и вставка её в label
    private void labelPicture(){
        if(formuaId==2) pathToImageFunc="C:\\study\\Java\\lab2 - Simple Interface\\images\\func2.jpg";
        else pathToImageFunc ="C:\\study\\Java\\lab2 - Simple Interface\\images\\func1.jpg";
        BufferedImage imageFunc1 = null;
        try {
            imageFunc1 = ImageIO.read(new File(pathToImageFunc));
        } catch (IOException e) {
            e.printStackTrace();
        }
        labelForFormula.setIcon(new ImageIcon(imageFunc1));
    }

    //Формлуа №2 для рассчёта
    public Double calculate2(Double x,Double y,Double z){
        return Math.pow(Math.cos(Math.PI*Math.pow(x,3))+2*Math.log(1+y),1/4)*(Math.pow(Math.E,Math.pow(z,2))+Math.pow(1/x,1/2)+Math.cos(Math.pow(Math.E,y)));
    }

    //методы помощники для добавления радио кнопок
    private void addRadioButton(String buttonName,final int formuaId){
        JRadioButton button = new JRadioButton(buttonName);
        //Определить и зарегистрировать обработчик
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formuaId = formuaId;
                labelPicture();
            }
        });
        radioButtonsForFunc.add(button);
        hboxFormulaType.add(button);
    }

    public MainFrame(){
        //Вызов контруктора JFrame (Создание окна с заголовком)
        super("Вычисления формулы");

        //установка размеров и положения окна
        setSize(WIDTH,HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2,(kit.getScreenSize().height - HEIGHT)/2);

        //добавление картинки в контейнер
        Box hboxImageType = Box.createHorizontalBox();
        hboxImageType.add(Box.createHorizontalGlue());
        labelPicture();
        hboxImageType.add(labelForFormula);
        hboxImageType.add(Box.createHorizontalGlue());

        //добавление радио-кнопок выбора формулы и добавление их в контейнер
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1",1);
        addRadioButton("Формула 2",2);
        radioButtonsForFunc.setSelected(radioButtonsForFunc.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        // Создание текстовых полей для переменных
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0",10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0",10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0",10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hboxVaribles = Box.createHorizontalBox();
        hboxVaribles.setBorder(BorderFactory.createLineBorder(Color.RED));

        // Добавление в контейнер ряд обьектов:
        hboxVaribles.add(Box.createHorizontalGlue());
        hboxVaribles.add(labelForX);
        hboxVaribles.add(Box.createHorizontalStrut(10));
        hboxVaribles.add(textFieldX);
        hboxVaribles.add(Box.createHorizontalStrut(100));
        hboxVaribles.add(labelForY);
        hboxVaribles.add(Box.createHorizontalStrut(10));
        hboxVaribles.add(textFieldY);
        hboxVaribles.add(Box.createHorizontalStrut(100));
        hboxVaribles.add(labelForZ);
        hboxVaribles.add(Box.createHorizontalStrut(10));
        hboxVaribles.add(textFieldZ);
        hboxVaribles.add(Box.createHorizontalGlue());

        // Добавление области для вывода результатов
        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0",10);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        // создание конопок
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try{
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if(formuaId==1)
                        result = calculate1(x,y,z);
                    else
                        result = calculate2(x,y,z);
                    // Установить текст записи равным результату
                    textFieldResult.setText(result.toString());
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой",
                            "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        // Создание кнопки "Очистить поля"
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });
        // Создать коробку с горизонтальной укладкой и добавить туда кнопки
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        // Создать контейнер "коробка с вертикальной укладкой"
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        //добавить горизонтальные контейнеры
        contentBox.add(hboxImageType);
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVaribles);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        //установить вертикальную коробку в область содержания главного окна
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }



}
