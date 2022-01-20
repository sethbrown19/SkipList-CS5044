import student.TestableRandom;

/**
 * 
 * This class is used to set up the integers used to test
 * random levels of insertion.
 * 
 * @author Seth Brown
 * @author 906388237
 * 
 * @version 26 Sep 2021
 */
public class Test {

    /**
     * This test method sets up the exact random ints to produce.
     * 
     */
    Test() {
        TestableRandom rnd = new TestableRandom();
        for (int i = 0; i < 3; i++) {
            System.out.println(rnd.nextInt());
        }
    }

}
