/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package sdp.prac2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class AppTest {

    // Tests for Task 4 - g19f7591
    // If lists are same length
    @Test void Task4Equal(){
        //Arrange
        App classBeingTested = new App();
        List<Integer> a = Arrays.asList(2, 4, 6, 8);
        List<Integer> b = Arrays.asList(1, 3, 5, 7);

        List<Integer> expected = Arrays.asList(14, 20, 18, 8);

        //Act
        List<Integer> result = SimpleFunctions.Task4(a, b);
        
        //Assert
        assertIterableEquals(expected, result);
    }

    // If 2 lists are different length.
    @Test void Task4Diff(){
        //Arrange
        App classBeingTested = new App();
        List<Integer> a = Arrays.asList(2, 4, 6, 8);
        List<Integer> b = Arrays.asList(1, 3, 5, 7,9);

        //Act
        List<Integer> result = SimpleFunctions.Task4(a, b);
        
        //Assert
        assertIterableEquals(null, result);
    }
    // ---------------------------------------------------Task 4 Tests END ------------------------------------------------------------\\
        
}
