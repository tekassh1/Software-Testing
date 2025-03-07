package org.example.Part3.Interfaces;

import org.example.Part3.AbstractClasses.StoryCharacter;
import org.example.Part3.Enums.Actions;
import org.example.Part3.LocationArea;

public interface CharacterAction {
    default void see(StoryCharacter character) {};
    default void act(StoryCharacter... characters) {};
    default void act(Actions action) {};
    void changeLocationArea(LocationArea newLocationArea);
}
