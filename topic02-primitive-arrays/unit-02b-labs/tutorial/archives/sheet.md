---
icon:
  type: fluent-mdl2:handwriting
  color: lightseagreen
---

<div align="center">

<h1>Tutorial Sheet - Topic 05 </h1>
<h2>Programming Fundamentals 1</h2>

Loops, while loops, for loops, nested loops.

</div>

## Part A: Understanding While Loops

### 1. Writing simple while loops
Write Java code for each of the following:
1. Print the message **"Hello World"** five times using a `while` loop.  
2. Print the numbers from **1 to 10** (inclusive) on separate lines.  
3. Print the numbers from **10 down to 1** (inclusive) on the same line, separated by spaces.
4. Print the numbers from **5 to 12** (inclusive) on separate lines. 
5. Print the numbers from **20 down to 10** (inclusive) on the same line, separated by spaces.
6. Print the numbers from **2 to 17** (inclusive) in steps of 3 on separate lines

---

### 2. Predicting output
Without running the code, write down exactly what is printed by the following program:
```java
int i = 2;
while (i <= 8) {
    System.out.println("Value of i: " + i);
    i = i + 2;
}
```
a. How many times does the loop run?  
b. What is the last value of `i` printed?

---

### 3. Correcting logic
The following loop is supposed to print the numbers 1–5, but it doesn’t work.  
Explain what’s wrong and correct the code:
```java
int i = 1;
while (i <= 5) {
    System.out.println(i);
}
```

---
<div style="page-break-before: always;"></div>


### 4. Designing your own
Write a `while` loop that prints the **square of each number** from 1 to 5:
```
1
4
9
16
25
```

---

## Part B: For Loops

### 5. Rewriting while as for
Rewrite each of the following `while` loops using a `for` loop.

(a)
```java
int i = 1;
while (i <= 4) {
    System.out.println("Java");
    i++;
}
```

(b)
```java
int n = 10;
while (n >= 1) {
    System.out.println(n);
    n--;
}
```

---
### 6. Writing simple for loops (rewriting the while loops from Part A)
Write Java code for each of the following:
1. Print the message **"Hello World"** five times using a `for` loop.  
2. Print the numbers from **1 to 10** (inclusive) on separate lines.  
3. Print the numbers from **10 down to 1** (inclusive) on the same line, separated by spaces.
4. Print the numbers from **5 to 12** (inclusive) on separate lines. 
5. Print the numbers from **20 down to 10** (inclusive) on the same line, separated by spaces.
6. Print the numbers from **2 to 17** (inclusive) in steps of 3 on separate lines

<div style="page-break-before: always;"></div>

### 7. Tracing nested loops
What will this code print?
```java
for (int i = 0; i < 2; i++) {
    for (int j = 0; j < 3; j++) {
        System.out.println("i = " + i + ", j = " + j);
    }
}
```
a. How many total lines are printed?  
b. What is printed on the 5th line?

---

### 8. Writing a counting pattern
Using for loop/s, write code that prints:
```
Counting up: 1 2 3 4 5
Counting down: 5 4 3 2 1
```

---


### 9. Thinking challenge
Why might a **while loop** be useful for **input validation** (for example, asking a user for a positive number)?  
Write an example snippet to demonstrate your reasoning. Use the Loop Control Variable concept. 
- What would your Loop Control Variable be? 
- How would you initialise it? 
- How would you update it? 
- How would you test it?

