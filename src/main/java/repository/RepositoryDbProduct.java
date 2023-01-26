package repository;

import domain.Product;
import validators.RepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositoryDbProduct implements RepositoryDB<Product> {

    private final String  urlDb="jdbc:postgresql://localhost:5432/Magazin";
    private final String  usernameDb="postgres";
    private final String  passwdDb="postgres";

    @Override
    public void addElement(Product product) throws RepositoryException {

        String sql="insert into product(cod_product,name,category,price) values (?,?,?,?)"; // SQL string
        try(Connection connection= DriverManager.getConnection(urlDb,usernameDb,passwdDb);
            PreparedStatement preparedStatement= connection.prepareStatement(sql)){
            preparedStatement.setObject(1, product.getCod());
            preparedStatement.setObject(2, product.getName());
            preparedStatement.setObject(3, product.getCategory());
            preparedStatement.setObject(4, product.getPrice());
            preparedStatement.execute();
        }catch (SQLException e){
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Product> getAll() throws RepositoryException {
        List<Product> productList=new ArrayList<>();
        String sql ="select * from product";
        try( Connection connection = DriverManager.getConnection(urlDb,usernameDb,passwdDb);
             PreparedStatement preparedStatement= connection.prepareStatement(sql);
        ){
            ResultSet resultSet= preparedStatement.executeQuery();
            while(resultSet.next()) {
                productList.add(new Product(resultSet.getString("cod_product"),
                        resultSet.getString("name"),
                        resultSet.getString("category"),
                        resultSet.getDouble("price")));
            }
            return productList;
        }catch (SQLException e){
            throw new RepositoryException(e.getMessage());
        }

    }

    @Override
    public Product getArticle(String cod) throws RepositoryException {
        String sql ="select * from product where cod_product in (?)";
        try( Connection connection = DriverManager.getConnection(urlDb,usernameDb,passwdDb);
             PreparedStatement preparedStatement= connection.prepareStatement(sql);

        ){
            preparedStatement.setObject(1, cod);
            ResultSet resultSet= preparedStatement.executeQuery();
            resultSet.next();
            return new Product(resultSet.getString("cod_product"),
                              resultSet.getString("name"),
                            resultSet.getString("category"),
                               resultSet.getDouble("price"));

        }catch (SQLException e){
            throw new RepositoryException(e.getMessage());
        }

    }

    @Override
    public void update() {

    }

    @Override
    public void deleteElement(Product product) {

    }


}


