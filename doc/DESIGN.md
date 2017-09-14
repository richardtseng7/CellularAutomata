# Cell Society

Second project for Computer Science 308. 

Authors:
* Santo Grillo (sdg12@duke.edu)
* Ben Schwennesen (bas65@duke.edu)
* Michael Scruggs (mts40@duke.edu)
* Richard Tseng (rt120@duke.edu)

## Introduction

Our team wants this project to be very flexible to the addition of new cellular automata simulations; that is, we want our code to be easily extended to support new types of cell states and rules. 

## Overview

There are a number of components we know our program will include: 
 1. A main class (preliminary name: Launcer) which launches a main menu/splash screen. The menu will allow for selection of the simulation to start, among other options.
 2. A manager class (preliminary name: SimulationManager) that will handle the progression of the simulation; its methods will start or stop the simulation and update the states of the cells at regular time intervals.
 3. A state processer (preliminary name: StateProcessor) that will process the current state of the cells, check the states against the rules for each cell, and either update the states of the cells itself or delegate this step to another class (perhaps a StateUpdater)
 4. A cell class (preliminary name: Cell) storing the state of the cell, rules this cell follows, a data structure (some kind of collection) containing the cell's neighors (other cells). Methods will include: getState, updateState, getNeighbors, getRule. 
 5. A display class (preliminary name choices: Display, Frontend, etc.) that will create a scene for the simulation and update it per the information passed to it by the manager. 
 6. A file IO class (preliminary name: InitialStateReader) that will readin the XML data and initialize the grid of cells.

## User Interface

## Design Details

## Design Considerations

## Team Responsibilities

