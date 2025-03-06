package org.example.Part3;

import org.example.Part3.AbastractClasses.Person;

public class Arthur extends Person {
    public Arthur() {
        super("Артур");
    }
    public void enterRoom(Room room) {
        System.out.println(name + " нервничая, вошел следом и был ошеломлен, увидев развалившегося в кресле человека.");
        room.describe();
        beShocked();
    }
    public void beShocked() {
        System.out.println("Количество вещей, видя которые, " + name + " не верил своим глазам, все росло.");
        System.out.println("Его челюсть отвисла.");
    }
}
