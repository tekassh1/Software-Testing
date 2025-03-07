package org.example.Part3;

import org.example.Part3.AbstractClasses.StoryCharacter;
import org.example.Part3.Enums.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationArea {

    Location location;
    private List<StoryCharacter> characters;

    public LocationArea(Location loc) {
        this.location = loc;
        this.characters = new ArrayList<>();
    }

    public Location getPlace() {
        return location;
    }

    public void addCharacter(StoryCharacter character) {
        characters.add(character);
    }

    public void removeCharacter(StoryCharacter character) {
        characters.remove(character);
    }

    public List<StoryCharacter> getCharacters() {
        return characters;
    }
}
