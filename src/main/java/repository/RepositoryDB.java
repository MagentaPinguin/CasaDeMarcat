package repository;

import domain.Product;
import validators.RepositoryException;

import java.util.List;

public interface RepositoryDB<E extends Product>{

     //"C.R.U.D.
     //#C-REATE
     void addElement(E e) throws RepositoryException;

     //#R-EAD
     List<E> getAll() throws RepositoryException;

     E getArticle( String cod) throws RepositoryException;

     //#R-EAD
     void update();

    //#Delete
     void deleteElement(E e);


}
