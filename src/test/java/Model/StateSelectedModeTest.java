//Author: Federico Scatà
package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateSelectedModeTest {

    @Test
    void getSelectedMode() {
        StateSelectedMode m = new StateSelectedMode();
        m.setSelectedmode(Mode.BASE);
        assertEquals(m.getSelectedmode(), Mode.BASE);
    }

    @Test
    void setSelectedMode() {
        StateSelectedMode m = new StateSelectedMode();
        m.setSelectedmode(Mode.BASE);
        assertEquals(m.getSelectedmode(), Mode.BASE);
    }
}