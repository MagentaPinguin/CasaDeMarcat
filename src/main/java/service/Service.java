package service;

import domain.Product;
import repository.RepositoryDbProduct;
import validators.RepositoryException;
import validators.ServiceException;

import java.util.List;
import java.util.Optional;

public class Service {
    private RepositoryDbProduct repo=new RepositoryDbProduct();


    public List<Product> getAll() throws ServiceException {
        try {
            return repo.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Product> get(String text) throws ServiceException {
        Product found=null;
        try {
            found= repo.getArticle(text);
        } catch (RepositoryException e) {
            throw new ServiceException("INVALID PRODUCT");
        }
        return Optional.of(found);
    }

    public void addElement(String cod, String category, String name, double price) throws ServiceException {
        try {
            repo.addElement(new Product(cod,category,name,price));
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
