package com.inatlas.infra;

import com.inatlas.infra.api.ApiCoffeeShopController;
import com.inatlas.infra.api.ApiCoffeeShopDelegate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoffeeShopController extends ApiCoffeeShopController {

  public CoffeeShopController(ApiCoffeeShopDelegate delegate) {
    super(delegate);
  }


}
