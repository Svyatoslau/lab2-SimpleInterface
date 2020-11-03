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
    private static final int WIDTH = 420;
    private static final int HEIGHT = 320;

    //текстовые поля
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;

    //Внутреняя память
    private Double mem1 = (double) 0;
    private Double mem2 = (double) 0;
    private Double mem3 = (double) 0;

    //группа радио кнопок для отображения уникальности выделения в группе
    private ButtonGroup radioButtonsForFunc = new ButtonGroup();
    private ButtonGroup radioButtonsForMemory = new ButtonGroup();
    //Контейнер для отображения радио-кнопок:
    // формул
    private Box hboxFormulaType = Box.createHorizontalBox();
    // памяти
    private Box hboxMemoryType = Box.createHorizontalBox();

    private int formuaId = 1;
    private int memoryId = 1;
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
    private void addRadioButtonForFormula(String buttonName,final int formuaId){
        JRadioButton button = new JRadioButton(buttonName);
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
    private void addRadioButtonForMemory(String buttonName,final int memoryId){
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.memoryId = memoryId;
            }
        });
        radioButtonsForMemory.add(button);
        hboxMemoryType.add(button);
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
        hboxImageType.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //добавление памяти
        hboxMemoryType.add(Box.createHorizontalGlue());
        addRadioButtonForMemory("Переменная 1",1);
        addRadioButtonForMemory("Переменная 2",2);
        addRadioButtonForMemory("Переменная 3",3);
        radioButtonsForMemory.setSelected(radioButtonsForMemory.getElements().nextElement().getModel(),true);
        hboxMemoryType.add(Box.createHorizontalGlue());
        hboxMemoryType.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));

        //коробка для ячеек памяти
        JTextField textFieldMem1 = new JTextField("0",11);
        textFieldMem1.setMaximumSize(textFieldMem1.getPreferredSize());
        JTextField textFieldMem2 = new JTextField("0",11);
        textFieldMem2.setMaximumSize(textFieldMem2.getPreferredSize());
        JTextField textFieldMem3 = new JTextField("0",11);
        textFieldMem3.setMaximumSize(textFieldMem3.getPreferredSize());
        Box hboxMemoryR = Box.createHorizontalBox();
        hboxMemoryR.add(Box.createHorizontalGlue());
        hboxMemoryR.add(textFieldMem1);
        hboxMemoryR.add(Box.createHorizontalStrut(10));
        hboxMemoryR.add(textFieldMem2);
        hboxMemoryR.add(Box.createHorizontalStrut(10));
        hboxMemoryR.add(textFieldMem3);
        hboxMemoryR.add(Box.createHorizontalGlue());

        //добавление радио-кнопок выбора формулы и добавление их в контейнер
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButtonForFormula("Формула 1",1);
        addRadioButtonForFormula("Формула 2",2);
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
        textFieldResult = new JTextField("0",11);
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
        // "MC" - очищает значение активной переменной
        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(memoryId==1){
                    mem1 = (double) 0;
                    textFieldMem1.setText(mem1.toString());
                }
                else if(memoryId==2){
                    mem2 = (double) 0;
                    textFieldMem2.setText(mem2.toString());
                }
                else if(memoryId==3){
                    mem3 = (double) 0;
                    textFieldMem3.setText(mem3.toString());
                }
            }
        });
        //"M+" - прибавляет к текщему значению памяти полученный результат
        JButton buttonM = new JButton("M+");
        buttonM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(memoryId==1) {
                    mem1  =  (double)mem1 + Double.parseDouble(textFieldResult.getText());
                    textFieldMem1.setText(mem1.toString());
                    textFieldResult.setText(mem1.toString());
                }
                else if(memoryId==2) {
                    mem2 =(double)mem2 + Double.parseDouble(textFieldResult.getText());
                    textFieldMem2.setText(mem2.toString());
                    textFieldResult.setText(mem2.toString());
                }
                else if(memoryId==3){
                    mem3 =(double)mem3 + Double.parseDouble(textFieldResult.getText());
                    textFieldMem3.setText(mem3.toString());
                    textFieldResult.setText(mem3.toString());
                }
            }
        });


        // Создать коробку с горизонтальной укладкой и добавить туда кнопки
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonM);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonMC);
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
        contentBox.add(hboxMemoryType);
        contentBox.add(hboxMemoryR);
        contentBox.add(hboxVaribles);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        //установить вертикальную коробку в область содержания главного окна
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }



}
