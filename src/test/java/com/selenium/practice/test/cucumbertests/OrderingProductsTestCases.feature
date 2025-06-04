#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
#THIS FILE IS FOR ORDERING PRODUCTS TESTCASES

Feature: Product Ordering Functionality

	Background:
		Given You are on the Ecommerce Page
	
	  @tag1
	  Scenario Outline: Trying to order products
	    Given I am on the login or sign-up page
	    When I login as this user <user>
	    Then I go to the products page and add a few products to cart
	    And go ahead and order them using the card details of <user> and logout
	
	    Examples: 
	      | user        |
	      | Eli Walker  |
	      | Glen Ramos  |
	      | ivanka tame |

	  @tag2
	  Scenario: Trying to order products without registering
	    Given I am on the Products page
	    Then I go to the products page and add a few products to cart
	    When I go to the cart page and try to checkout without registering, I am asked to register, so I go register
	    Then after registering I go back to the cart page and try to checkout
	    And delete account

	  @tag3
	  Scenario: Trying to order products after registering
	    Given I am on the login or sign-up page
	    When I signup
	    Then I go to the products page and add a few products to cart
	    And I go to the cart page and checkout
	    And delete account	    
	    
	  @tag3
	  Scenario Outline: Trying to order Searched products
	    Given I am on the Products page
	    When I search for a product <product_number> and add the searched product to cart
	    Then I go to the cart page and verify the products in cart
	    And go back to the login and signup page and login with this user <user>
	    And verify the products in cart again and go ahead and order them using the card details of <user> and logout
	
	    Examples: 
	      | user        | product_number |
	      | Eli Walker  | 2              |
	      | Glen Ramos  | 1              |
	      | ivanka tame | 3              |