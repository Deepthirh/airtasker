@edit @all
Feature: Edit tasks

Scenario: Edit a task
  Given I have "Task to be edited" task in the list
  And I am on todo app home page
  When I edit the task to "Edited task"
  Then The task values is changed to "Edited task"
