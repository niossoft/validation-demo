# validation-demo

As per the requirement, the validation for each rule will be different and we may need to extend the validations for
more rules in the future. Hence, the best approach will be to use the Strategy pattern.

I try to avoid with the usual 'Class' way of implementation. Because if we do it that way, we need more classes to be
defined. Then I try to use Enum for the implementation on this.

The structure is presented in the picture below.

![image](src\main\resources\images\Validation_Structure.png)

The PasswordValidationStrategy implements interface Validation Strategy, and each node of PasswordValidationStrategy will overide with 3 methods to do the customized job in the validation process. 

