import org.example.Part3.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPart3 {

    @Test
    public void testLeftHeadAction() {
        TwoHeadedMan man = new TwoHeadedMan();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        man.leftHeadAction();

        String expected = "но зато " + man.getLeftHead().getName() + " улыбалась широко и непринужденно. \r\n";
        assertEquals(expected, outputStream.toString());
    }

    @Test
    public void testRightHeadAction() {
        TwoHeadedMan man = new TwoHeadedMan();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        man.rightHeadAction();

        String expected = man.getRightHead().getName() + ", казалось, была всецело занята этим.\r\n";
        assertEquals(expected, outputStream.toString());
    }

    @Test
    public void testLeftHandAction() {
        TwoHeadedMan man = new TwoHeadedMan();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        man.leftHandAction();

        String expected = "ковырял " + man.getLeftHand().getSide() + " рукой в зубах " + man.getRightHead().getName() + "\r\n";
        assertEquals(expected, outputStream.toString());
    }

    @Test
    public void testFeetAction() {
        TwoHeadedMan man = new TwoHeadedMan();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        man.feetAction();

        String expected = "положил " + man.getFeet().all() + " на пуль управления\r\n";
        assertEquals(expected, outputStream.toString());
    }

    @Test
    public void testRelaxInChair() {
        TwoHeadedMan man = new TwoHeadedMan();
        Chair chair = new Chair();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        man.relaxInChair(chair);

        String expectedOutput = man.getName() + " развалился в кресле.\r\n" +
                "положил " + man.getFeet().all() + " на пуль управления\r\n" +
                "ковырял " + man.leftHand.getSide() + " рукой в зубах " + man.getRightHead().getName() + "\r\n" +
                man.getRightHead().getName() + ", казалось, была всецело занята этим.\r\n" +
                "но зато " + man.getLeftHead().getName() + " улыбалась широко и непринужденно. \r\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testEnterRoom() {
        Arthur arthur = new Arthur();
        Room room = new Room();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        arthur.enterRoom(room);

        String expectedOutput = arthur.getName() + " нервничая, вошел следом и был ошеломлен, увидев развалившегося в кресле человека.\n" +
                "Двухголовый человек развалился в кресле.\n" +
                "положил Обе ноги на пуль управления\n" +
                "ковырял левая рукой в зубах правая голова\n" +
                "правая голова, казалось, была всецело занята этим.\n" +
                "но зато левая голова улыбалась широко и непринужденно. \n" +
                "Количество вещей, видя которые, Артур не верил своим глазам, все росло.\n" +
                "Его челюсть отвисла.\n";

        assertEquals(expectedOutput, outputStream.toString().replace("\r", ""));
    }

    @Test
    public void testBeShocked() {
        Arthur arthur = new Arthur();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        arthur.beShocked();

        String expectedOutput = "Количество вещей, видя которые, " + arthur.getName() + " не верил своим глазам, все росло.\n" +
                "Его челюсть отвисла.\n";
        assertEquals(expectedOutput, outputStream.toString().replace("\r", ""));
    }

    @Test
    public void testScene() {
        Scene scene = new Scene();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        scene.run();

        String expectedOutput = "Артур нервничая, вошел следом и был ошеломлен, увидев развалившегося в кресле человека.\n" +
                "Двухголовый человек развалился в кресле.\n" +
                "положил Обе ноги на пуль управления\n" +
                "ковырял левая рукой в зубах правая голова\n" +
                "правая голова, казалось, была всецело занята этим.\n" +
                "но зато левая голова улыбалась широко и непринужденно. \n" +
                "Количество вещей, видя которые, Артур не верил своим глазам, все росло.\n" +
                "Его челюсть отвисла.\n";

        assertEquals(expectedOutput, outputStream.toString().replace("\r", ""));
    }
}
