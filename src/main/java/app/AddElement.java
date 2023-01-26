package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import service.Service;

public class AddElement extends abstractController{

    public TextField codElem;
    public TextField categoryElem;
    public TextField nameElem;
    public TextField priceElem;
    private Service service;

    void setService(Service s) {
        this.service = s;
    }

    @FXML
    void initialize(){


    }

    public void addElem(ActionEvent actionEvent) {
        try{
            String cod=codElem.getText();
            String category=categoryElem.getText();
            String name=nameElem.getText();
            double price=Double.parseDouble(priceElem.getText());
            if(price<=0)
                throw new IllegalArgumentException("Price cant be <=0");
            if(cod.isBlank() || category.isBlank() || name.isBlank() )
                throw new IllegalArgumentException("Product detail empty");
            service.addElement(cod,category,name,price);
            alertWarning("SUCCES",""+name+" added");
            codElem.clear();
            categoryElem.clear();
            nameElem.clear();
            priceElem.clear();
        }catch (Exception e){
            alertWarning("WARRNING",e.getMessage());

        }
    }

}
