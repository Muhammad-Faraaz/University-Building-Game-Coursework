package students;

import building.Building;

import java.util.ArrayList;
import java.util.Random;

public class Team {
  private final int recruitNewStudent = 0;
  int knowledgePoints;
  ArrayList<Student> students = new ArrayList<Student>();
  Random randomNumber = new Random();
  private int newStudentCost = 100;

  public Team(int knowledgePoints) {
    this.knowledgePoints = knowledgePoints;
  }

  public int getKnowledgePoints() {
    return knowledgePoints;
  }

  public ArrayList<Student> getStudents() {
    return students;
  }

  public void studentsAct(Building building) {
    for (Student student : students) {
      int knowledgeGained = student.defence(building);
      knowledgePoints += knowledgeGained;
    }
  }

  public int getNewStudentCost() {
    newStudentCost += (10 * recruitNewStudent);
    return newStudentCost;
  }

  /**
   * Implements the recruitNewStudent() method This method takes a random integer so that every one
   * of the students has an equal probability to get chosen. In this case each student has a 25%
   * chance of being recruited. It also throws an arithmetic exception if there are not enough
   * knowledge points.
   */
  public void recruitNewStudent() {
    if (knowledgePoints >= newStudentCost) {
      int studentType = new Random().nextInt(4);
      if (studentType == 0) {
        Student student = new AiStudent(1);
        students.add(student);
      }
      if (studentType == 1) {
        Student student = new CsStudent(1);
        students.add(student);
      }
      if (studentType == 2) {
        Student student = new CyberStudent(1);
        students.add(student);
      }
      if (studentType == 3) {
        Student student = new SeStudent(1);
        students.add(student);
      }
      knowledgePoints -= newStudentCost;
    } else {
      throw new ArithmeticException(
          "Student does not have enough knowledge points for recruitment");
    }
  }

  /**
   * In this upgrade() method, it ugrades a student if it has enough knowledge points It also throws
   * an arithmetic exception if there are not enough knowledge points.
   */
  public void upgrade(Student student) {
    int upgradeCost = student.upgradeCost();
    if (knowledgePoints >= upgradeCost) {
      student.upgradeLevel();
      knowledgePoints -= upgradeCost;
    } else {
      throw new ArithmeticException("Student does not have enough knowledge points to upgrade");
    }
  }
}
