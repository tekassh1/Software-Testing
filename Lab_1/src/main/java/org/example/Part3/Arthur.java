package org.example.Part3;

import org.example.Part3.AbstractClasses.StoryCharacter;
import org.example.Part3.Enums.CharacterState;
import org.example.Part3.Enums.Place;

public class Arthur extends StoryCharacter {

    private Place place;
    private int trustlessLevel;
    private int jawOpenedCoefficient;

    public Arthur(String name, LocationArea locationArea) {
        super(name, locationArea);
        place = Place.FLOOR;
        trustlessLevel = 1;
        jawOpenedCoefficient = 1;
        setState(CharacterState.NERVOUS);
    }

    @Override
    public void changeLocationArea(LocationArea newLocationArea) {
        this.locationArea.removeCharacter(this);
        this.locationArea = newLocationArea;
        locationArea.addCharacter(this);

        boolean newLocationContainsTwoHeaded = false;

        for (StoryCharacter character : this.locationArea.getCharacters()) {
            if (character instanceof TwoHeadedMan) {
                newLocationContainsTwoHeaded = true;
                see(character);
            }
        }

        if (!newLocationContainsTwoHeaded) {
            setState(CharacterState.DEFAULT);
        }

        updateStates();
    }

    @Override
    public void see(StoryCharacter storyCharacter) {
        if (storyCharacter instanceof TwoHeadedMan) {
            setState(CharacterState.SHOCKED);
        }
        updateStates();
    }

    @Override
    public void updateStates() {
        if (characterState == CharacterState.SHOCKED) {
            trustlessLevel = 100;
            jawOpenedCoefficient *= trustlessLevel;
        }
        else if (characterState == CharacterState.NERVOUS || characterState == CharacterState.DEFAULT) {
            trustlessLevel = 1;
            jawOpenedCoefficient = 1;
        }
    }

    public int getTrustlessLevel() {
        return trustlessLevel;
    }

    public int getJawOpenedCoefficient() {
        return jawOpenedCoefficient;
    }

    public Place getPlace() {
        return place;
    }

    public LocationArea getLocationArea() {
        return locationArea;
    }
}
