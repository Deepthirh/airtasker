@complete @all
Feature: Mark tasks as completed

Scenario: Mark a task as completed
  Given I have "Task to be completed" task in the list
  And I am on todo app home page
  When I tick the task checkbox
  Then The task is marked as completed
