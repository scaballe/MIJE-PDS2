package edu.uoc.mije.carsharing.integration;

import java.util.List;

import javax.persistence.EntityManager;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class SimpleTest extends AbstractJPATest{

    protected void clearDataIntoTransaction(EntityManager em) throws Exception {
        em.createQuery("delete from CarJPA").executeUpdate();
    }

    protected void insertDataIntoTransaction(EntityManager em) throws Exception {
        CarJPA car1 = new CarJPA("1","marca","modelo","rojo");
        em.persist(car1);
        CarJPA car2 = new CarJPA("2","AlfaRomeo","cupe","pink");
        em.persist(car2);
    }
    
    @Test
    public void simpleTest() throws Exception {
        // given
        String fetchingAllInJpql = "select g from CarJPA g order by g.id";

        // when
        System.out.println("Selecting (using JPQL)...");
        List<CarJPA> list = em.createQuery(fetchingAllInJpql, CarJPA.class).getResultList();

        // then
        System.out.println("Found  games (using JPQL):");
        Assert.assertTrue( list.size() == 2);
    }
}
