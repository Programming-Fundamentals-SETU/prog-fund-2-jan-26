<div align="center">

<h1>Programming Fundamentals 2</h1>
<h2>Topic 04 - Tutorial Sheet 4.2  </h2>

</div>

## Static Members, Javadoc


## 1. Instance vs Static (Class) Variables

### Instance Variables
- Belong to **individual objects**
- Each object has its **own copy**
- Represent object-specific state

```java
public class BouncingBall {
    private int xPosition;
    private int yPosition;
}
```

If three `BouncingBall` objects exist, there are three separate `xPosition` and `yPosition` values.

---

### Check Your Understanding
1. How many copies of a static variable exist in a program?
2. Why is `gravity` (as in the Bouncing BAll example) suitable as a static variable, but `xPosition` is not?

---

## 2. Static Methods


### Think About This
Should the following method be static or instance-based?

```java
public int moveBallDown() { ... }
```

Explain your reasoning.

---

## 3. Javadoc: Documenting Classes and Methods

---

### Class Documentation Example

```java
/**
 * Represents an employee in the company.
 * Stores salary information and provides
 * methods for salary calculations.
 *
 * @author Your Name
 * @version 1.0
 */
public class Employee {
    ...
}
```

---

### Method Documentation Example

```java
/**
 * Calculates and returns the employee's net salary.
 *
 * @return the net salary after deductions
 */
public double calculateNetSalary() {
    return salary - deductions;
}
```

---

### Common Javadoc Tags
- `@author`
- `@version`
- `@param`
- `@return`

---

### Task
Add full Javadoc comments to:
- One class
- One constructor
- Two methods

## Hint : 
 - if in doubt, check with an API e.g. of Strings or Collections to see the level of comment. 
---

