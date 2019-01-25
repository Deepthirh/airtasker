@add @all
Feature: Add tasks

Scenario: Add a task
  Given I am on todo app home page
  When I enter "Add a task to the todo list" task
  Then The task is listed under Todo Items
  And Its status is non-completed
