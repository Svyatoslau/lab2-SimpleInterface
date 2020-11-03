package bsu.rfe.java.group10.lab2.Yaroshevich.varC2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    //размеры окна приложения в виде консоли
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;

    //текстовые поля для считывания X и Y
    private JTextField textFieldX;
    private JTextField textFieldY;

    //текстовы поля для отображения результата
    private  JTextField textFieldResult;

    //группа радио кнопок для отображения уникальности выделения в группе
    private ButtonGroup radioButtons = new ButtonGroup();

    //Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createHorizontalBox();

    //индентификатор выбранной формулы
    private int formuaId = 1;

    //методы-помощники для вычисления значений функций
    //Формула №1 для расчёта
    public Double calculate1(Double x,Double y,Double z){
        return Math.pow((2*Math.log(1+x)+Math.cos(Math.PI*Math.pow(z,3))),Math.sin(y))+Math.pow(Math.pow(Math.E,Math.pow(x,2))+Math.cos(Math.pow(Math.E,z))+Math.pow(1/y,1/2),1/x);
    }
    //Формлуа №2 для рассчёта
    public Double calculate2(Double x,Double y,Double z){
        return Math.pow(Math.cos(Math.PI*Math.pow(x,3))+2*Math.log(1+y),1/4)*(Math.pow(Math.E,Math.pow(z,2))+Math.pow(1/x,1/2)+Math.cos(Math.pow(Math.E,y)));
    }

    //методы помощники для добавления радио кнопок
    //buttonName - текст рядом с кнопкой, formulaId - идентификатор формулы
    private void addRadioButton(String buttonName,final int formuaId){
        //Создать экземпляр радио-кнопки с заданным текстом
        JRadioButton button = new JRadioButton(buttonName);
        //Определить и зарегистрировать обработчик
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                // который будет устанавливать идентификатор выбранной
                //формулы в классе MainFrame равным formulaId
                MainFrame.this.formuaId = formuaId;
            }
        });
        //Добавить радио-кнопку в группу
        radioButtons.add(button);
        // Добавить радио-кнопку в группу
        // Для этого ссылка на контейнер сделана полем данных класса
        hboxFormulaType.add(button);
    }

    public MainFrame() {
        //Вызов контруктора JFrame (Создание окна с заголовком)
        super("Вычисления формулы");

        //установка размеров и положения окна
        setSize(WIDTH,HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2,(kit.getScreenSize().height - HEIGHT)/2);

        //добавление радио-кнопок выбора формулы
        //добавить "клей" C1-H1 с левой стороны
        hboxFormulaType.add(Box.createHorizontalGlue());
        //создать радио-кнопку для формулы 1
        addRadioButton("Формула 1",1);
        //создать радио-кнопку для формулы 2
        addRadioButton("Формула 2",2);
        //устанавить выделенной 1-ую кнопку из группы
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        //добавить "клей" C1-H2 с правой стороны
        hboxFormulaType.add(Box.createHorizontalGlue());
        //задать рамку для коробки с помощью класса BorderFactory
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        // Создание текстовых полей для переменных
        //Создание подписи "X: " для переменной X
        JLabel labelForX = new JLabel("X:");
        //Создание текстового поля для для ввода переменной X,
        //(по умолчанию 0)
        textFieldX = new JTextField("0",10);
        // Установка макс размера = желаемому для предотвращения масштабирования
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        // Тоже самое для переменной Y
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0",10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        //Создаём контейнер "с горизонтальной укладкой"
        Box hboxVaribles = Box.createHorizontalBox();
        //Зададим рамку для коробки с помощью класса BorderFactory
        hboxVaribles.setBorder(BorderFactory.createLineBorder(Color.RED));

        // Добавление в контейнер ряд обьектов:
        // ДОбавление клея C2-H1 - для максимального удаление от левого края
        hboxVaribles.add(Box.createHorizontalGlue());
        // Добавление подписи для X
        hboxVaribles.add(labelForX);
        // Добавление "распорки" C2-H2 шириной 10 пикселов для отступа
        // между подписью и самим полем X для ввода значений
        hboxVaribles.add(Box.createHorizontalStrut(10));
        // Добавление самого текстового поля для X
        hboxVaribles.add(textFieldX);
        // Добавление "распорки" C2-H3 шириной 100 пикселов для отступа
        // между текстовым полем X и подиписи для Y
        hboxVaribles.add(Box.createHorizontalStrut(100));
        // Тоже самое делаем для Y
        hboxVaribles.add(labelForY);
        hboxVaribles.add(Box.createHorizontalStrut(10));
        hboxVaribles.add(textFieldY);
        //Добавление "клея" C2-H5 для максимального удаления от правого края
        hboxVaribles.add(Box.createHorizontalGlue());

        // Добавление области для вывода результатов
        // Создать подписи для вывода результатов
        JLabel labelForResult = new JLabel("Результат:");
        // создание текстового поля для вывода результатов с начальным значением - 0
        textFieldResult = new JTextField("0",10);
        //Создание контейнера  "коробка с горизонтальной укладкой" и его укоплектовка
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        // создание конопок
        // создание кнопки "Вычислить"
        JButton buttonCalc = new JButton("Вычислить");
        // Определить и зарегестрировать обработчик нажатия на кнопку
        buttonCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try{
                    // Получить значение X
                    Double x = Double.parseDouble(textFieldX.getText());
                    // Получить значение Y
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double result;
                    if(formuaId==1)
                        result = calculate1(x,y);
                    else
                        result = calculate2(x,y);
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
        // Определить и зарегестрировать обработчик нажатия на кнопку
        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
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
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVaribles);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        //установить вертикальную коробку в область содержания главного окна
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }



}
