package grafikus;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GrafikusController {

    @FXML
    private GridPane gp1, gp2,gp3,gp4,gp5, gp6;
    @FXML
    private Label parhl1, parhl2;


    @FXML
    private TextField tfVizsgazoaz, tfVizsgatargyaz, tfSzobeli, tfIrasbeli, tfAzon, tfNev, tfSzomax, tfIrmax, tfVizsgazoaz2, tfAzon2, Nev2, tfOsztaly;
    @FXML
    private TextArea textA1;
    @FXML
    private TableView tv1, tv2, tv3;
    @FXML
    private ComboBox box1,box2,box3,box4;
    @FXML
    private TableColumn<Vizsga, Integer> vizsgazoazCol;
    @FXML
    private TableColumn<Vizsga, String> vizsgatargyazCol;
    @FXML
    private TableColumn<Vizsga, String> szobeliCol;
    @FXML
    private TableColumn<Vizsga, String> irasbeliCol;
    @FXML
    private TableColumn<Vizsgatargy, Integer> azonCol;
    @FXML
    private TableColumn<Vizsgatargy, String> nevCol;
    @FXML
    private TableColumn<Vizsgatargy, Integer> szomaxCol;
    @FXML
    private TableColumn<Vizsgatargy, Integer> irmaxCol;
    @FXML
    private TableColumn<Vizsgatargy, Integer> vizsgazoaz2Col;
    @FXML
    private TableColumn<Vizsgazo, Integer> azon2Col;
    @FXML
    private TableColumn<Vizsgazo, String> nev2Col;
    @FXML
    private TableColumn<Vizsgazo, Integer> osztalyCol;
    @FXML

    SessionFactory factory;
    @FXML
    private TextArea táblák;
    @FXML
    void initialize() {
        ElemekTörlése();
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        factory = cfg.buildSessionFactory();
        box1.getItems().add("Vizgsa");
        box1.getItems().add("Vizsgazo");
        box1.getItems().add("Vizgsatargy");
        String[] szamok = {"1","2","3","4","5","6","7","8","9","10"};
        box2.getItems().addAll(szamok);
        box4.getItems().add("Vizgsa");
        box4.getItems().add("Vizsgazo");
        box4.getItems().add("Vizgsatargy");
       // box3.getItems().addAll(szamok);






    }

    void ElemekTörlése() {

        gp1.setVisible(false);
        gp1.setManaged(false);
        gp2.setVisible(false);
        gp2.setManaged(false);
        gp3.setVisible(false);
        gp3.setManaged(false);
        gp4.setVisible(false);
        gp4.setManaged(false);
        gp5.setVisible(false);
        gp5.setManaged(false);
        gp6.setVisible(false);
        gp6.setManaged(false);
        //box2.setVisible(false);
        //box2.setManaged(false);
        box3.setVisible(false);
        box3.setManaged(false);
        tv1.setVisible(false);
        tv1.setManaged(false);
        tv2.setVisible(false);
        tv2.setManaged(false);
        tv3.setVisible(false);
        tv3.setManaged(false);


    }

    @FXML
    protected void menuReadClick() {
        ElemekTörlése();
        tv1.setVisible(true);
        tv1.setManaged(true);
        tv2.setVisible(true);
        tv2.setManaged(true);
        tv3.setVisible(true);
        tv3.setManaged(true);

        tv1.getColumns().removeAll(tv1.getColumns());
        tv2.getColumns().removeAll(tv2.getColumns());
        tv3.getColumns().removeAll(tv3.getColumns());

        vizsgazoazCol = new TableColumn("Vizsgázoaz");
        vizsgatargyazCol = new TableColumn("Vizsgatárgyaz");
        szobeliCol = new TableColumn("Szóbeli");
        irasbeliCol = new TableColumn("Írásbeli");
        azonCol = new TableColumn("Azon");
        nevCol = new TableColumn("Név");
        szomaxCol = new TableColumn("Szómax");
        irmaxCol = new TableColumn("Írmax");
        vizsgazoaz2Col = new TableColumn("Vizsgázóaz");
        azon2Col = new TableColumn("Azon");
        nev2Col = new TableColumn("Név");
        osztalyCol = new TableColumn("Osztály");
        tv1.getColumns().addAll(vizsgazoazCol, vizsgatargyazCol, irasbeliCol, szomaxCol, nev2Col, osztalyCol);//vizsgazoaz2Col
        tv2.getColumns().addAll(azonCol, nevCol, szomaxCol, irmaxCol);
        tv3.getColumns().addAll(azon2Col, nev2Col, osztalyCol);
        vizsgazoazCol.setCellValueFactory(new PropertyValueFactory<>("Vizsgazoaz"));
        vizsgatargyazCol.setCellValueFactory(new PropertyValueFactory<>("Vizsgatargyaz"));
        szobeliCol.setCellValueFactory(new PropertyValueFactory<>("Szobeli"));
        irasbeliCol.setCellValueFactory(new PropertyValueFactory<>("Irasbeli"));
        azonCol.setCellValueFactory(new PropertyValueFactory<>("Azon"));
        nevCol.setCellValueFactory(new PropertyValueFactory<>("Nev"));
        szomaxCol.setCellValueFactory(new PropertyValueFactory<>("Szomax"));
        irmaxCol.setCellValueFactory(new PropertyValueFactory<>("Irmax"));
        vizsgazoaz2Col.setCellValueFactory(new PropertyValueFactory<>("Vizsgazoaz"));
        azon2Col.setCellValueFactory(new PropertyValueFactory("Azon2"));
        nev2Col.setCellValueFactory(new PropertyValueFactory("Nev2"));
        osztalyCol.setCellValueFactory(new PropertyValueFactory("Osztaly"));
        tv1.getItems().clear();
        tv2.getItems().clear();
        tv3.getItems().clear();

        Session session = factory.openSession();
        Session session2 = factory.openSession();
        Session session3 = factory.openSession();
        Transaction t = session.beginTransaction();
        Transaction t2 = session2.beginTransaction();
        Transaction t3 = session3.beginTransaction();
        List<Vizsga> list = session.createQuery("FROM Vizsga ").list();
        List<Vizsgatargy> lista = session2.createQuery("FROM Vizsgatargy ").list();
        List<Vizsgazo> listaa = session3.createQuery("FROM Vizsgazo ").list();
        for (Vizsga vizsga : list)
            tv1.getItems().add(vizsga);
        System.out.println();
        t.commit();

        for (Vizsgatargy targy : lista)
            tv2.getItems().add(targy);
        System.out.println();
        t2.commit();

        for (Vizsgazo vizsgazo : listaa)
            tv3.getItems().add(vizsgazo);
        System.out.println();
        t3.commit();
    }

    @FXML
    protected void menuRead2Click() {
        ElemekTörlése();
        gp1.setVisible(true);
        gp1.setManaged(true);
        box1.setVisible(true);
        box1.setManaged(true);
    }
    public void bt1Click(ActionEvent actionEvent) {
        ElemekTörlése();
    }

    @FXML
    public void menuWriteClick() {
        ElemekTörlése();
        gp2.setVisible(true);
        gp2.setManaged(true);
    }

    void Create() {
        Session session = factory.openSession();
        Transaction t= session.beginTransaction();
        Vizsgazo vizsgazo=new Vizsgazo(Integer.parseInt(tfAzon2.getText()),Nev2.getText(),tfOsztaly.getText());
        session.save(vizsgazo);
        t.commit();
    }
    @FXML void bt2Click(){
        Create();
        ElemekTörlése();
    }

    //public void bt2Click(ActionEvent actionEvent) {
    //Create();
    //ElemekTörlése();
    //
    @FXML public void menuUpdateClick() {
        ElemekTörlése();
        gp4.setVisible(true);
        gp4.setManaged(true);
        box3.setVisible(true);
        box3.setManaged(true);
        Session session = factory.openSession();
        List<Vizsgazo> lista = session.createQuery("from Vizsgazo ").list();
        List<Integer> azonosítok = lista.stream().map(Vizsgazo::getAzon2).collect(Collectors.toList());
        box3.getItems().addAll(azonosítok);
    }
    void Módosít(){
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        int valasztott = Integer.parseInt(box3.getSelectionModel().getSelectedItem().toString());
        String név = Nev2.getText();
        String osztály = tfOsztaly.getText();
        // Vizsgazo targy = session.get(Vizsgazo.class,valasztott);
         //targy.setOsztaly(osztály);
         //targy.setNev2(név);
        Vizsgazo tanulo =(Vizsgazo) session.createQuery("From Vizsgazo v where v.azon2="+valasztott).list().get(0);
        session.delete(tanulo);
        session.saveOrUpdate(new Vizsgazo(valasztott,név,osztály));
        t.commit();
    }
    public void bt6Click(ActionEvent actionEvent) {
        Módosít();
        ElemekTörlése();
    }


    @FXML protected void menuDeleteClick() {
        ElemekTörlése();
        gp3.setVisible(true);
        gp3.setManaged(true);
    }
    void Delete(){
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        String valasztott = box2.getSelectionModel().getSelectedItem().toString();
        String sqlQuery = "DELETE FROM Vizsgatargy WHERE azon ='" + valasztott + "'";
        session.createQuery(sqlQuery);
        session.save(valasztott);
        t.commit();
    }
    public void bt3Click(ActionEvent actionEvent) {
        Delete();
        ElemekTörlése();
    }


    public void dontesiFa(ActionEvent actionEvent) {
        ElemekTörlése();

    }

    public void tobbAlg(ActionEvent actionEvent) {
        ElemekTörlése();
    }

    public void tobbAlg2(ActionEvent actionEvent) {
        ElemekTörlése();
    }

    public void restCreat(ActionEvent actionEvent) {
        ElemekTörlése();
    }

    public void restRead(ActionEvent actionEvent) {
        ElemekTörlése();
    }

    public void restUpdate(ActionEvent actionEvent) {
        ElemekTörlése();
    }

    public void restDelete(ActionEvent actionEvent) {
        ElemekTörlése();
    }

    public void rest2Creat(ActionEvent actionEvent) {
        ElemekTörlése();
    }

    public void rest2Read(ActionEvent actionEvent) {
        ElemekTörlése();
    }

    public void rest2Update(ActionEvent actionEvent) {
        ElemekTörlése();
    }

    public void rest2Delete(ActionEvent actionEvent) {
        ElemekTörlése();
    }

    public void letoltes(ActionEvent actionEvent) {
        ElemekTörlése();
    }

    public void letoltes2(ActionEvent actionEvent) {
        ElemekTörlése();
    }

    public void grafikon(ActionEvent actionEvent) {
        ElemekTörlése();
    }

    @FXML public void parhuzamos() {

        ElemekTörlése();
        gp5.setVisible(true);
        gp5.setManaged(true);
        parhl1.setVisible(true);
        parhl1.setManaged(true);
        parhl2.setVisible(true);
        parhl2.setManaged(true);
    }
    void cserel() {
        String[] abc = {"momentum", "brave", "aloof", "stunning", "call"};
        String[] abc1 = {"dilemma", "mainstream" ,"yard","outside", "foot"};
        Runnable task=()->{
            while(true) {
                int i = new Random().nextInt(abc.length);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(() -> {
                            parhl1.setText(abc[i]);
                        }
                );
            }
        };
        Runnable task2=()->{
            int i = new Random().nextInt(abc1.length);
            while(true) {
                try {

                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(() -> {
                            parhl2.setText(abc1[i]);
                        }
                );
            }
        };
        new Thread(task).start();
        new Thread(task2).start();
    }
    public void bt4Click(ActionEvent actionEvent) {

        ElemekTörlése();

        ElemekTörlése();
        gp5.setVisible(true);
        gp5.setManaged(true);
        parhl1.setVisible(true);
        parhl1.setManaged(true);
        parhl2.setVisible(true);
        parhl2.setManaged(true);
        String[] abc = {"momentum", "brave", "aloof", "stunning", "call"};
        String[] abc1 = {"dilemma", "mainstream" ,"yard","outside", "foot"};
        Runnable task=()->{
            while(true) {
                int i = new Random().nextInt(abc.length);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(() -> {
                            parhl1.setText(abc[i]);
                        }
                );
            }
        };
        Runnable task2=()->{
            while(true) {
                int i = new Random().nextInt(abc.length);
                try {

                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(() -> {
                            parhl2.setText(abc1[i]);
                        }
                );
            }
        };
        new Thread(task).start();
        new Thread(task2).start();
    }



    public void stream(ActionEvent actionEvent) {
        ElemekTörlése();
        gp6.setVisible(true);
        gp6.setManaged(true);

    }
    void StreamKeres(){

    }


    public void bt5Click(ActionEvent actionEvent) {
        StreamKeres();
        ElemekTörlése();
    }


}
