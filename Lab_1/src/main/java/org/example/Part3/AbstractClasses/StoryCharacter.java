package org.example.Part3.AbstractClasses;

import org.example.Part3.Enums.CharacterState;
import org.example.Part3.Interfaces.CharacterAction;
import org.example.Part3.LocationArea;

public abstract class StoryCharacter implements CharacterAction {

    protected String name;
    protected LocationArea locationArea;
    protected CharacterState characterState;

    public StoryCharacter(String name, LocationArea locationArea) {
        this.name = name;
        this.locationArea = locationArea;
        locationArea.addCharacter(this);
    }

    public void setState(CharacterState state) {
        this.characterState = state;
    }

    public abstract void updateStates();
}
