Feature: Getting basic Feat activity
  Rule: A Feat can have Active, Passive or both values.

  Scenario: A Feat can have Active, Passive or both values.
    Given a Feat with at least one Active or Passive values
    When you try to remove all usages
    Then The operation should fail

    Scenario: A feat must have at least 1 usage type
      Given a Feat of any type
      When you retrieve it's usage types
      Then it should have at least 1 but no more than 2 types.



