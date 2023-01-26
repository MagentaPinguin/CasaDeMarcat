package app;

import domain.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import validators.ServiceException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InterfaceController extends abstractController {

    private final List<Pair<Product,Integer>> productList=new ArrayList<>();
    public TextField cod_input;
    public Button cancelCod;
    public TextField nr_view;
    public TextArea view_bill;
    public TextField view_total;

    @FXML
    void initialize() {
        nr_view.setText("0");
    }




    public void type(ActionEvent actionEvent) {
        var x = (Button) actionEvent.getSource();
        cod_input.setText(cod_input.getText() + x.getText());
    }

    public void clearCod() {
        cod_input.clear();
    }

    public void modify_nr(ActionEvent actionEvent) {
        var x = (Button) actionEvent.getSource();
        if (x.getText().equalsIgnoreCase("^")) {
            nr_view.setText(String.valueOf(Integer.parseInt(nr_view.getText()) + 1));
        } else if (Integer.parseInt(nr_view.getText()) > 0)
            nr_view.setText(String.valueOf(Integer.parseInt(nr_view.getText()) - 1));
    }

    public void addProduct() {
        try {
            var ret = service.get(cod_input.getText());
            if(ret.isEmpty())
                throw new ServiceException("nonexistent ARTICLE");
            var nr=Integer.parseInt(nr_view.getText());
            if(nr==0)
                throw new ServiceException("SELECT A QUANTITY");
            productList.add(new Pair<>(ret.get(),nr));
        } catch (ServiceException e) {
            alertWarning("WARRNING",e.getMessage());
        }


        var afis= productList.stream().map(e->String.format("%s%20s%28s  x%d%6.2f",
                                                                            e.getKey().getCod(),
                                                                            e.getKey().getCategory(),
                                                                            e.getKey().getName(),
                                                                            e.getValue(),
                                                                            e.getValue()* (e.getKey().getPrice()))).toList();
        view_bill.setText(String.join("\n", afis));
        view_total.setText(String.format("Total: %.3f Lei",productList.stream().mapToDouble(e->e.getValue()*e.getKey().getPrice()).sum()));

    }

    public void billing() {

        File myObj = new File("bonuri/bon-"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("___dd-MM-yyyy__HH-mm-ss"))+".txt");
        if(!myObj.exists()) {
            try {
                System.out.println(myObj.createNewFile());
            } catch (IOException e) {
                alertWarning("WARRNING",e.getMessage());
            }
        }
        try {
            System.out.println(myObj.getAbsolutePath());
            FileWriter wr=new FileWriter(myObj);
            wr.write("Bonul generat la data "+LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)+"\n");
            wr.write("--------------------------------------------------------------------------------\n");
            wr.write(view_bill.getText());
            wr.write("\n--------------------------------------------------------------------------------\n");
            wr.write(view_total.getText());
            wr.close();
        } catch (IOException e) {
            alertWarning("WARRNING",e.getMessage());
        }

    }

    public void openSearch() {
        try {
            openStage("searchProduct.fxml");
        } catch (IOException e) {
            alertWarning("ERROR","Opening stage");
        }
    }

    public void addNewProduct() {
        try {
            openStage("addElement.fxml");
        } catch (IOException e) {
            alertWarning("ERROR","Opening stage");
        }
    }
}
