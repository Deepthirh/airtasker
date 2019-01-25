@delete @all
Feature: Delete tasks

Scenario: Delete a task
  Given I have "Task to be deleted" task in the list
  When I click on X beside the task
  Then The task is deleted
