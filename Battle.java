import bugs.Bug;
import building.Building;
import students.Student;
import students.Team;

import java.util.ArrayList;
import java.util.Arrays;

public class Battle {
  Team team;
  Building building;
  int count; //keeps track of the number of students that have been upgraded.
  ArrayList<Student> students;
  Student activeStudent;

  public Battle(Team team, Building building) {
    this.team = team;
    this.building = building;
    count = 0;
  }

  /**
   * I have implemented the Battle class in line with the coursework specification.
   * The strategy implemented is to recruit 5 students and then upgrade each student one by one.
   */
  public void manageTeam() {
    try {
      if (team.getNewStudentCost() >= 150) {
        students = team.getStudents();
        activeStudent = students.get(count);
        if (team.getKnowledgePoints() >= activeStudent.upgradeCost()) {
          team.upgrade(activeStudent);
          count += 1;
        } else if (count >= students.size()) {
          count = 0;
        }
      } else if (team.getKnowledgePoints() >= team.getNewStudentCost()) {
        team.recruitNewStudent();
      }
    } catch (Exception e) {
      System.out.println("Not enough points");
    }
  }

  /**
   * I have implemented the step method in line with the coursework specification, and decided to add a bit more for the aesthetic look of the game.
   * The method prints out stats related to the bug, such as level and hp, as well as stats related to the student such as knowledge points etc.
   */

  public void step() {
    try {
      Thread.sleep(500);
      this.manageTeam();
      this.building.bugsMove();
      this.team.studentsAct(building);
      Bug[] bugs = building.getAllBugs();
      ArrayList<Student> students = team.getStudents();
      for (Bug bug : bugs) {
        System.out.println(
            bug.getName()
                + " Level: "
                + bug.getLevel()
                + " Hp: "
                + bug.getCurrentHp()
                + " Floor: "
                + bug.getCurrentFloor()
                + " Number of steps: "
                + bug.getCurrentSteps());
      }
      for (Student student : students) {
        System.out.println(student.getName() + " Level: " + student.getLevel());

        System.out.println(
            " Amount of knowledge points: "
                + team.getKnowledgePoints()
                + " \n"
                + " Recruiting cost: "
                + team.getNewStudentCost()
                + "\n"
                + " Number of students: "
                + team.getStudents().size()
                + " \n"
                + " Amount of building construction points: "
                + building.getConstructionPoints()
                + "\n"
                + " Amount of bugs: "
                + building.getAllBugs().length);
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
