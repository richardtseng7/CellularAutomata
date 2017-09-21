Inheritance Review Exercise 
==========

Discussion Group: 

* mts40 
* zk174
* ll253
* bas65
* jpp20

## Part 1
1. Whereas we are hiding our rule-checking/state-advancement procedure from our State Processor (keeping them in the cells themselves), the group we dicussed our design with made the opposite decision, keeping rule-checking in their State Manager and hiding it from their cells. 
2. We are going to have a base abstract Cell class and have the specific cells for each game inherit from this base class. The main behavior we want to get out of this is rule-checking behavior, which will be different for each type of simulation. 
3. We do not understand this question. 
4. A cell might end up having an undefined state for some reason, or a location which is out of bounds. We will throw errors if this occurs and deal with them at that point. 
5. Our design is good because it's easily understood and flexible, in that it will be easy to create new types, even without much prior exposure to the design. Our measure of goodness is mainly based on this flexibilty.

## Part 2 
1. Our component is liked to the XML input in that it will contain initial cell positions and states. It's also related to the manager because the cells and state processor will need to pass information to the manager (as well as have information passed to it). 
2. Yes, these dependencies were planned out in the design process and are intended.
3. As it stands, the dependencies are unidirectional and well-minimized already. In the case that they become more extensive, we will seek to minimize them by keeping thing unidirectional and not straying from the original purpose intended for our classes. 
4. We haven't started our part of the project yet, but we will achieve this by keeping as much behavior as possible in the superclass. 

## Part 3
1. 
    a. 
    b. 
    c. 
    d. 
    e. 
2. 
3. 

(We ran out of time)