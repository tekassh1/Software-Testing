import org.example.Part3.Arthur;
import org.example.Part3.Enums.Actions;
import org.example.Part3.Enums.Location;
import org.example.Part3.Enums.Place;
import org.example.Part3.LocationArea;
import org.example.Part3.TwoHeadedMan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPart3 {

    LocationArea street;
    LocationArea room;

    Arthur arthur;
    TwoHeadedMan twoHeadedMan;

    @BeforeEach
    public void setUp() {
        street = new LocationArea(Location.STREET);
        room = new LocationArea(Location.ROOM);

        arthur = new Arthur("Arthur", street);
        twoHeadedMan = new TwoHeadedMan("Two Headed Man", room);
    }

    @Test
    public void testArthurEnteredNewArea() {
        arthur.changeLocationArea(room);
        assertEquals(arthur.getLocationArea(), room);
    }

    @Test
    public void testArthurEnteredTwoHeadedArea() {
        arthur.changeLocationArea(room);
        assertTrue(arthur.getTrustlessLevel() > 1);
    }

    @Test
    public void testArthurExitedTwoHeadedArea() {
        arthur.changeLocationArea(room);
        arthur.changeLocationArea(street);
        assertTrue(arthur.getTrustlessLevel() == 1);
    }

    @Test
    public void testArthurJarOpenedCoefficient() {
        arthur.changeLocationArea(room);
        assertTrue(arthur.getJawOpenedCoefficient() > 1);
    }

    @Test
    public void testArthurDefaultPlace() {
        assertEquals(arthur.getPlace(), Place.FLOOR);
    }

    @Test
    public void testTwoHeadedLeaningOnChair() {
        twoHeadedMan.act(Actions.LEAN_ON_CHAIR);
        assertEquals(twoHeadedMan.getPlace(), Place.CHAIR);
    }

    @Test
    public void testTwoHeadedPeakNoseWhileSitting() {
        twoHeadedMan.act(Actions.LEAN_ON_CHAIR);
        twoHeadedMan.act(Actions.PICK_NOSE);

        assertAll("All states are correct",
                () -> assertTrue(twoHeadedMan.getSmileCoefficient() > 1),
                () -> assertEquals(street.getCharacters().size(), 1),
                () -> assertEquals(room.getCharacters().size(), 1),
                () -> assertTrue(twoHeadedMan.getPlace() == Place.CHAIR)
        );
    }

    @Test
    public void testTwoHeadedLeaveRoom() {
        twoHeadedMan.changeLocationArea(street);
        assertEquals(twoHeadedMan.getLocationArea(), street);

        assertAll("All states are correct",
                () -> assertTrue(arthur.getJawOpenedCoefficient() == 1),
                () -> assertNotEquals(street.getCharacters().size(), 0),
                () -> assertTrue(street.getCharacters().contains(twoHeadedMan))
        );
    }

    @Test
    public void testTwoHeadedSmilesOff() {
        twoHeadedMan.changeLocationArea(street);
        assertTrue(twoHeadedMan.getSmileCoefficient() == 1);
    }
}
