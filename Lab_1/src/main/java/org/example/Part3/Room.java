package org.example.Part3;

public class Room {
    Chair chair = new Chair();
    TwoHeadedMan occupant = new TwoHeadedMan();

    public void describe() {
        occupant.relaxInChair(chair);
    }
}