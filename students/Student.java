package students;

import bugs.Bug;
import building.Building;

public interface Student {

     int getLevel();
     int upgradeCost();
     int defence(Building building);
     void upgradeLevel();
     String getName();
}
