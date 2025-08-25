package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.model.BeerStyle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface BeerRepository extends JpaRepository<Beer, UUID> {

    List<Beer> findAllByBeerNameIsLikeIgnoreCase(String beerName);

    List<Beer> findAllByBeerStyle(BeerStyle beerStyle);

    List<Beer> findAllByBeerNameIsLikeIgnoreCaseAndBeerStyle(String beerName, BeerStyle beerStyle);

    //These last two aren't used.  I was just fooling around with what is capable with JPA
    List<Beer> findAllByBeerNameIsLikeIgnoreCaseAndUpcIsLikeIgnoreCaseAndBeerStyleAndPriceGreaterThan(String beerName, String upc, BeerStyle beerStyle, BigDecimal price);

    List<Beer> findAllByBeerNameIsLikeIgnoreCaseAndUpcIsLikeIgnoreCaseAndBeerStyleAndPriceGreaterThanAndPriceLessThan(String beerName, String upc, BeerStyle beerStyle, BigDecimal price, BigDecimal price2);
}
