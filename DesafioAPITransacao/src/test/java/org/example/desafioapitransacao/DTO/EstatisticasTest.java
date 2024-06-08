package org.example.desafioapitransacao.DTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EstatisticasTest {
    @Test
    void getCount(){
        Estatistica estatistica = new Estatistica(10, 1234.56, 126.456, 12.34, 123.56);
        int count = estatistica.getCount();
        Assertions.assertEquals(10, count);
    }
    @Test
    void setCount(){
        Estatistica estatistica = new Estatistica(0, 1234.56, 126.456, 12.34, 123.56);
        estatistica.setCount(10);
        int count = estatistica.getCount();
        Assertions.assertEquals(10, count);
    }
    @Test
    void getSum(){
        Estatistica estatistica = new Estatistica(10, 1234.56, 126.456, 12.34, 123.56);
        double sum = estatistica.getSum();
        Assertions.assertEquals(1234.56, sum);
    }
    @Test
    void setSum(){
        Estatistica estatistica = new Estatistica(10, 1234.56, 126.456, 12.34, 123.56);
        estatistica.setSum(1234.56);
        double sum = estatistica.getSum();
        Assertions.assertEquals(1234.56, sum);
    }
    @Test
    void getAvg(){
        Estatistica estatistica = new Estatistica(10, 1234.56, 126.456, 12.34, 123.56);
        double avg = estatistica.getAvg();
        Assertions.assertEquals(126.456, avg);
    }
    @Test
    void setAvg(){
        Estatistica estatistica = new Estatistica(10, 1234.56, 0.0, 12.34, 123.56);
        estatistica.setAvg(126.456);
        double avg = estatistica.getAvg();
        Assertions.assertEquals(126.456, avg);
    }
    @Test
    void getMin(){
        Estatistica estatistica = new Estatistica(10, 1234.56, 126.456, 12.34, 123.56);
        double min = estatistica.getMin();
        Assertions.assertEquals(12.34, min);
    }
    @Test
    void setMin(){
        Estatistica estatistica = new Estatistica(10, 1234.56, 126.456, 0.0, 123.56);
        estatistica.setMin(12.34);
        double min = estatistica.getMin();
        Assertions.assertEquals(12.34, min);
    }
    @Test
    void getMax(){
        Estatistica estatistica = new Estatistica(10, 1234.56, 126.456, 12.34, 123.56);
        double max = estatistica.getMax();
        Assertions.assertEquals(123.56, max);
    }
    @Test
    void setMax(){
        Estatistica estatistica = new Estatistica(10, 1234.56, 126.456, 0.0, 0.0);
        estatistica.setMax(123.56);
        double max = estatistica.getMax();
        Assertions.assertEquals(123.56, max);
    }

}
