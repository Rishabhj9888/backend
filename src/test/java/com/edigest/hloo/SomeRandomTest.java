package com.edigest.hloo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
public class SomeRandomTest {

    @Test
    public void randomTest(){
        int expected=8;
        int actual =8;
        assertEquals(expected,actual,"test failed!!");
    }
    @ParameterizedTest
    @CsvSource({
           "1,1,2",
            "4,4,4"
    })

    public void otherTest(int a,int b,int expected){
        assertEquals(expected,a+b,"done");

    }

//we can also use @valueSource in place of CsvSource like
//@ValueSource(strings = {
//        "ram",
//      "  shyam"
//})

//    and we can also make our custom test by using a class call ArgumentProvider
//    in this we will make one class which extends the  calss of argumentsProvider
//    under this we will give the diiferent different things according to our need
//    and by using the annotation @ArgumentSource(class)

}
