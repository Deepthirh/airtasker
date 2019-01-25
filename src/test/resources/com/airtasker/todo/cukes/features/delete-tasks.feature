@delete @all
Feature: Delete tasks

Scenario: Delete a task
  Given I have "Task to be deleted" task in the list
  And I am on todo app home page
  When I click on X beside the task
  Then The task is deleted
