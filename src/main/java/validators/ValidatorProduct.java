package validators;

import domain.Product;

public class ValidatorProduct {

      public void  validate(Product p) throws ValidatorException {

          if(p.getCod().isBlank())
              throw new ValidatorException("Invalid object, cod");
          if(p.getName().isBlank())
              throw new ValidatorException("Invalid object, name");
          if(p.getCategory().isBlank())
              throw new ValidatorException("Invalid object, category");
          if(p.getPrice()<0)
              throw new ValidatorException("Invalid object, price");

      }

}
