package factory;

import factory.components.Accesory;
import factory.components.Body;
import factory.components.Car;
import factory.components.Motor;
import factory.store.*;
import factory.workers.TimeSettingsForWorkers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private final Store<Body> bodyStoreInfo;
    private final Store<Accesory> accesoryStoreInfo;
    private final Store<Motor> motorStoreInfo;
    private final Store<Car> carStoreInfo;

    private JPanel panelForProgressBars;
    private JPanel panelForCountOfCars;
    private JPanel panelForSpeedOfWorkers;

    private JLabel labelForCountOfCars;

    private JPanel panelForBody, panelForAccesory, panelForMotor, panelForCar;
    private JLabel labelForBody, labelForAccesory, labelForMotor, labelForCar;
    private JProgressBar bodyStoreProgress, accesoryStoreProgress, motorStoreProgress, carStoreProgress;

    private JPanel panelForTytle, panelForSuppliersAccesory, panelForCarCreator, panelForSuppliersMotor, panelForSuppliersBody;
    private JLabel labelForSupplierMotor, labelForSupplierBody, labelForSupplierAccesory, labelForCarCreator;
    private JLabel labelForTytleOfSpeeds;
    private JButton plusSpeedMotorButton, minusSpeedMotorButton, plusSpeedBodyButton, minusSpeedBodyButton,
                    plusSpeedAccesoryButton, minusSpeedAccesoryButton, plusSpeedCarButton, minusSpeedCarButton;

    public View(Store<Body> bodyStoreInfo, Store<Accesory> accesoryStoreInfo,
                Store<Motor> motorStoreInfo, Store<Car> carStoreInfo) {
        super("Factory");
        this.accesoryStoreInfo = accesoryStoreInfo;
        this.bodyStoreInfo = bodyStoreInfo;
        this.carStoreInfo = carStoreInfo;
        this.motorStoreInfo = motorStoreInfo;
        initComponents();
        this.setVisible(true);
    }

    private void initComponents() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds(dimension.width / 20, dimension.height / 20, 400, 600);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        Font font = new Font("MV Boli", Font.BOLD, 20);

        //создаем панели для количества машин, показателей заполненности складов и скорости работы работников
        panelForCountOfCars = new JPanel();
        panelForProgressBars = new JPanel();
        panelForSpeedOfWorkers = new JPanel();

        //устанавливаем размеры панелей
        panelForCountOfCars.setBounds(0, 25, 400, 75);
        panelForProgressBars.setBounds(0, 100, 400, 250);
        panelForSpeedOfWorkers.setBounds(0, 350, 400, 225);

        //Устанавливаем Layout
        panelForProgressBars.setLayout(null);
        panelForSpeedOfWorkers.setLayout(null);
        panelForCountOfCars.setLayout(null);

        //Установим задний фон для каждой панели
        panelForProgressBars.setBackground(new Color(200, 200, 200));
        panelForCountOfCars.setBackground(new Color(150, 150,150));
        panelForSpeedOfWorkers.setBackground(new Color(200, 200, 200));

        //Заполним панель количества произведенных машин
        labelForCountOfCars = new JLabel();
        labelForCountOfCars.setBounds(15, 5, 370, 65);
        labelForCountOfCars.setText("Count of created cars: " + carStoreInfo.getCountOfCreatedDetails());
        panelForCountOfCars.add(labelForCountOfCars);

        //Заполним панель прогресса каждого из работников
        //Сначала создадим 4 панели под каждый прогресс
        panelForBody = new JPanel();
        panelForAccesory = new JPanel();
        panelForCar = new JPanel();
        panelForMotor = new JPanel();

        //Зададим размеры панелей для каждого из прогрессов
        panelForMotor.setBounds(10, 5, 380, 60);
        panelForAccesory.setBounds(10, 65, 380, 60);
        panelForBody.setBounds(10, 125, 380, 60);
        panelForCar.setBounds(10, 185, 380, 60);

        carStoreProgress = new JProgressBar();
        motorStoreProgress = new JProgressBar();
        accesoryStoreProgress = new JProgressBar();
        bodyStoreProgress = new JProgressBar();

        //сделаем отображение прогресса в процентах
        carStoreProgress.setStringPainted(true);
        motorStoreProgress.setStringPainted(true);
        accesoryStoreProgress.setStringPainted(true);
        bodyStoreProgress.setStringPainted(true);

        //установим шрифт для записи процентов
        carStoreProgress.setFont(font);
        motorStoreProgress.setFont(font);
        accesoryStoreProgress.setFont(font);
        bodyStoreProgress.setFont(font);

        //установим цвета для каждого из баров
        carStoreProgress.setForeground(Color.black);
        bodyStoreProgress.setForeground(new Color(232, 166, 86));
        motorStoreProgress.setForeground(new Color(58, 181, 199));
        accesoryStoreProgress.setForeground(new Color(217, 75, 153));

        //добавим подписи к каждому бару
        labelForAccesory = new JLabel();
        labelForBody = new JLabel();
        labelForCar = new JLabel();
        labelForMotor = new JLabel();

        labelForAccesory.setBounds(0, 0, 100, 60);
        labelForBody.setBounds(0, 0, 100, 60);
        labelForCar.setBounds(0, 0, 100, 60);
        labelForMotor.setBounds(0, 0, 100, 60);

        labelForCar.setText("Car Store");
        labelForAccesory.setText("Accesory Store");
        labelForMotor.setText("Motor Store");
        labelForBody.setText("Body Store");

        //зададим размеры каждого бара
        carStoreProgress.setBounds(0, 0, 280, 60);
        motorStoreProgress.setBounds(0, 0, 280, 60);
        accesoryStoreProgress.setBounds(0, 0, 280, 60);
        bodyStoreProgress.setBounds(0, 0, 280, 60);

        //добавим в каждую панель по бару и подписи:
        panelForCar.add(labelForCar);
        panelForCar.add(carStoreProgress);

        panelForBody.add(labelForBody);
        panelForBody.add(bodyStoreProgress);

        panelForAccesory.add(labelForAccesory);
        panelForAccesory.add(accesoryStoreProgress);

        panelForMotor.add(labelForMotor);
        panelForMotor.add(motorStoreProgress);

        //Теперь каждую из панелей добавим в соответствующую панель
        panelForProgressBars.add(panelForAccesory);
        panelForProgressBars.add(panelForBody);
        panelForProgressBars.add(panelForMotor);
        panelForProgressBars.add(panelForCar);


        //Сделаем теперь еще панели для каждой из панелей скоростей:
        panelForTytle = new JPanel();
        panelForSuppliersAccesory = new JPanel();
        panelForSuppliersBody = new JPanel();
        panelForSuppliersMotor = new JPanel();
        panelForCarCreator = new JPanel();

        panelForTytle.setBounds(10, 10, 380, 40);
        panelForSuppliersMotor.setBounds(10, 50, 380, 40);
        panelForSuppliersBody.setBounds(10, 90, 380, 40);
        panelForSuppliersAccesory.setBounds(10, 130, 380, 40);
        panelForCarCreator.setBounds(10, 170, 380, 40);

        //Теперь сделаем кнопки и подписи для последней панели связанной со скоростями
        //Сначала делаем подписи
        labelForSupplierAccesory = new JLabel();
        labelForCarCreator = new JLabel();
        labelForSupplierBody = new JLabel();
        labelForSupplierMotor = new JLabel();
        labelForTytleOfSpeeds = new JLabel();

        labelForTytleOfSpeeds.setText("Count of seconds per:");
        labelForTytleOfSpeeds.setFont(font);
        labelForTytleOfSpeeds.setBounds(0,0,380,40);

        labelForSupplierBody.setText("Body:" + Factory.timeSet.getTimeForBody());
        labelForCarCreator.setText("Car:" + Factory.timeSet.getTimeForMachine());
        labelForSupplierMotor.setText("Motor:" + Factory.timeSet.getTimeForMotor());
        labelForSupplierAccesory.setText("Accesory:" + Factory.timeSet.getTimeForAccesory());

        labelForCarCreator.setFont(font);
        labelForSupplierMotor.setFont(font);
        labelForSupplierAccesory.setFont(font);
        labelForSupplierBody.setFont(font);

        labelForSupplierBody.setBounds(0,0,150,40);
        labelForSupplierAccesory.setBounds(0,0,150,40);
        labelForSupplierMotor.setBounds(0,0,150,40);
        labelForCarCreator.setBounds(0,0,150,40);

        //Теперь добавим кнопки
        plusSpeedAccesoryButton = new JButton();
        plusSpeedBodyButton = new JButton();
        plusSpeedCarButton = new JButton();
        plusSpeedMotorButton = new JButton();

        minusSpeedBodyButton = new JButton();
        minusSpeedCarButton = new JButton();
        minusSpeedMotorButton = new JButton();
        minusSpeedAccesoryButton = new JButton();

        minusSpeedMotorButton.setBounds(155,5,90,30);
        minusSpeedMotorButton.setText("-1");
        minusSpeedMotorButton.addActionListener(this);
        plusSpeedMotorButton.setBounds(255, 5, 90, 30);
        plusSpeedMotorButton.setText("+1");
        plusSpeedMotorButton.addActionListener(this);

        minusSpeedCarButton.setBounds(155,5,90,30);
        minusSpeedCarButton.setText("-1");
        minusSpeedCarButton.addActionListener(this);
        plusSpeedCarButton.setBounds(255, 5, 90, 30);
        plusSpeedCarButton.setText("+1");
        plusSpeedCarButton.addActionListener(this);

        minusSpeedBodyButton.setBounds(155,5,90,30);
        minusSpeedBodyButton.setText("-1");
        minusSpeedBodyButton.addActionListener(this);
        plusSpeedBodyButton.setBounds(255, 5, 90, 30);
        plusSpeedBodyButton.setText("+1");
        plusSpeedBodyButton.addActionListener(this);

        minusSpeedAccesoryButton.setBounds(155,5,90,30);
        minusSpeedAccesoryButton.setText("-1");
        minusSpeedAccesoryButton.addActionListener(this);
        plusSpeedAccesoryButton.setBounds(255, 5, 90, 30);
        plusSpeedAccesoryButton.setText("+1");
        plusSpeedAccesoryButton.addActionListener(this);

        //Добавляем кнопки и подписи в соответствующие панели
        panelForSuppliersAccesory.add(labelForSupplierAccesory);
        panelForSuppliersAccesory.add(plusSpeedAccesoryButton);
        panelForSuppliersAccesory.add(minusSpeedAccesoryButton);

        panelForTytle.add(labelForTytleOfSpeeds);

        panelForCarCreator.add(labelForCarCreator);
        panelForCarCreator.add(plusSpeedCarButton);
        panelForCarCreator.add(minusSpeedCarButton);

        panelForSuppliersBody.add(labelForSupplierBody);
        panelForSuppliersBody.add(plusSpeedBodyButton);
        panelForSuppliersBody.add(minusSpeedBodyButton);

        panelForSuppliersMotor.add(labelForSupplierMotor);
        panelForSuppliersMotor.add(plusSpeedMotorButton);
        panelForSuppliersMotor.add(minusSpeedMotorButton);

        //Добавляем эти панели в панель скоростей
        panelForSpeedOfWorkers.add(panelForCarCreator);
        panelForSpeedOfWorkers.add(panelForSuppliersAccesory);
        panelForSpeedOfWorkers.add(panelForSuppliersBody);
        panelForSpeedOfWorkers.add(panelForSuppliersMotor);
        panelForSpeedOfWorkers.add(panelForTytle);

        //И наконец добавляем все панели в фрэйм
        this.add(panelForCountOfCars);
        this.add(panelForProgressBars);
        this.add(panelForSpeedOfWorkers);

    }

    void fill() {
        labelForSupplierBody.setText("Body:" + Factory.timeSet.getTimeForBody());
        labelForCarCreator.setText("Car:" + Factory.timeSet.getTimeForMachine());
        labelForSupplierMotor.setText("Motor:" + Factory.timeSet.getTimeForMotor());
        labelForSupplierAccesory.setText("Accesory:" + Factory.timeSet.getTimeForAccesory());
        labelForCountOfCars.setText("Count of created cars: " + carStoreInfo.getCountOfCreatedDetails());
        carStoreProgress.setValue((int)( (double)carStoreInfo.getSize() / carStoreInfo.getMaxSize() * 100));
        bodyStoreProgress.setValue((int)( (double)bodyStoreInfo.getSize() / bodyStoreInfo.getMaxSize() * 100));
        accesoryStoreProgress.setValue((int)( (double)accesoryStoreInfo.getSize() / accesoryStoreInfo.getMaxSize() * 100));
        motorStoreProgress.setValue((int)( (double)motorStoreInfo.getSize() / motorStoreInfo.getMaxSize() * 100));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == plusSpeedAccesoryButton) {
            Factory.timeSet.setTimeForAccesory(Factory.timeSet.getTimeForAccesory() + 1);
        }
        if(e.getSource() == plusSpeedBodyButton) {
            Factory.timeSet.setTimeForBody(Factory.timeSet.getTimeForBody() + 1);
        }
        if(e.getSource() == plusSpeedCarButton) {
            Factory.timeSet.setTimeForMachine(Factory.timeSet.getTimeForMachine() + 1);
        }
        if(e.getSource() == plusSpeedMotorButton) {
            Factory.timeSet.setTimeForMotor(Factory.timeSet.getTimeForMotor() + 1);
        }
        if(e.getSource() == minusSpeedAccesoryButton && Factory.timeSet.getTimeForAccesory() > 1) {
            Factory.timeSet.setTimeForAccesory(Factory.timeSet.getTimeForAccesory() - 1);
        }
        if(e.getSource() == minusSpeedBodyButton && Factory.timeSet.getTimeForBody() > 1) {
            Factory.timeSet.setTimeForBody(Factory.timeSet.getTimeForBody() - 1);
        }
        if(e.getSource() == minusSpeedCarButton && Factory.timeSet.getTimeForMachine() > 1) {
            Factory.timeSet.setTimeForMachine(Factory.timeSet.getTimeForMachine() - 1);
        }
        if(e.getSource() == minusSpeedMotorButton && Factory.timeSet.getTimeForMotor() > 1) {
            Factory.timeSet.setTimeForMotor(Factory.timeSet.getTimeForMotor() - 1);
        }
    }
}
