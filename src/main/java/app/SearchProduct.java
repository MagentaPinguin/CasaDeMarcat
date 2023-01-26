package app;

import domain.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.Service;
import validators.ServiceException;

import java.util.function.Predicate;

public class SearchProduct extends abstractController{



    public TableColumn<Product,String> columnCod;
    public TableColumn<Product,String> columnCategory;
    public TableColumn<Product,String> columnPrice;
    public TableColumn<Product,String> columnName;
    public TextField searchName;
    public TextField searchCategory;
    public TextField searchPrice;
    public Button buttonExit;
    public TableView<Product> tabProducts;
    private final ObservableList<Product> model= FXCollections.observableArrayList();

    private Service service;
    void setService(Service serv){
        this.service=serv;
        createModel();
    }
    void createModel(){
        try {
        Predicate<Product> containName=(e)-> e.getName().toLowerCase().contains(searchName.getText().toLowerCase());
        Predicate<Product> containCategory=(e)-> e.getCategory().toLowerCase().contains(searchCategory.getText().toLowerCase());
        Predicate<Product> withPrice = (e) -> String.valueOf(e.getPrice()).contains(searchPrice.getText());

            model.setAll(service.getAll().stream().filter(containCategory.and(containName.and(withPrice))).toList());
        } catch (ServiceException e) {
            alertWarning("WARRNING",e.getMessage());
        }
    }

    @FXML
    void initialize(){

        columnCod.setCellValueFactory(new PropertyValueFactory<>("cod"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        searchCategory.textProperty().addListener(e->createModel());
        searchName.textProperty().addListener(e->createModel());
        searchPrice.textProperty().addListener(e->createModel());

        tabProducts.setItems(model);

    }


}
