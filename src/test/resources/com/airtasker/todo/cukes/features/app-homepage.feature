@homepage @all
Feature: Todo app homepage

Scenario: I am on a homepage
  Given I launch Todo app in chrome web browser
  And The todo list is empty
  When The app homepage loads
  Then I verify the page title as "Create a Todo List"
  And With a text box that allows to enter task
  And Shows the status of an empty todo list
