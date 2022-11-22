# validation-demo

### Intro

As per the requirement, the validation for each rule will be different and we may need to extend the validations for
more rules in the future. Hence, the best approach will be to use the Strategy pattern.

I try to avoid with the usual **Class way** of implementation. Because if we do it that way, we need more classes to be
defined. Then I try to use Enum for the implementation on this.

The structure is presented in the picture below.

![image](./src/main/resources/images/Validation_Structure.png)

The PasswordValidationStrategy implements interface ValidationStrategy, 
and each node of PasswordValidationStrategy will override with 3 methods to do the customized job in the validation process. 

### Extensible & Maintainable

The class ValidationContext like a container object to include all of ValidationStrategies.
so that we could handle validation types whatever we want. To accomplish customized validation on any single fields/details.


#### src\main\java\com\innova\ds\service\ValidationService.java
```
    public Map<String, String> verifyPasswordStrategy(BaseInput baseInput) {
        Set<ValidationStrategy> strategies = new LinkedHashSet<>();
        strategies.add(PasswordValidationStrategy.LOWERCASE_NUMERIC_ONLY);
        strategies.add(PasswordValidationStrategy.LENGTH_RANGE);
        strategies.add(PasswordValidationStrategy.MIN_LOWERCASE);
        strategies.add(PasswordValidationStrategy.MIN_NUMERIC);
        strategies.add(PasswordValidationStrategy.NO_SEQUENCE);
        
        // add new validations following the strategies list...
        
        return new ValidationContext(strategies).execute(baseInput);
    }
```

If user wants to add any rules into the validation process, 
they could just create new field in Enum PasswordValidationStrategy to setting own validate content & error message.
Then, if they want to include new input column should be checking. They could just create new enum object which implements 
interface ValidationStrategy to raise entire fresh validation process. 

**e.g.** added Enum EmailValidationStrategy and also added related fields both in ValidationType & ErrorMsgType, 
so that we could extend validation functionalities based on the structure much easier.

### Example

#### Url:
```
POST http://localhost:8080/api/verify/password
```
#### Body:
```
{
    "password": "<input String>"
}
```

Hit the API via postman with **Url** above, we could get the error fields below. 
If Results are empty, **_means it passed all the validation!_**

#### Successful Results:
```
{

}
```

#### Failure Results:
```
{
    "LOWERCASE_NUMERIC_ONLY": "alphabetical lowercase & numeric character only.",
    "LENGTH_RANGE": "Failed length requirement.",
    "MIN_LOWERCASE": "must contain at least one alphabetical lowercase character.",
    "MIN_NUMERIC": "must contain at least one numeric character.",
    "NO_SEQUENCE": "must not contain any repeating substrings of two characters or more"
}
```


