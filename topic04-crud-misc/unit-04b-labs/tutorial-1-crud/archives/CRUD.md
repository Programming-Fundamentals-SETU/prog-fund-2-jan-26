
<div align="center">

<h1>Programming Fundamentals 2</h1>
<h2>Topic 04 - Tutorial Sheet 4.1  </h2>

</div>

## CRUD (Create, Read, Update, Delete)

---

### Section 1: Understanding CRUD

**Question 1.1: Short Answers**

a) What does CRUD stand for?

b) Which CRUD operations were already implemented in Shop V3.0?

c) Which CRUD operations were missing in Shop V3.0?

d) Which CRUD operation(s) modify existing data?

---

### Section 2: Recap – Shop V3.0 Structure

**Question 2.1: Classes and Responsibilities**

a) Write the instance field declaration used in `Store` to store products.

b) Write the constructor for `Store` that initialises this collection.

```java
public class Store {
    // Your code here
}
```

---

### Section 3: CRUD and the Menu (Driver.java)

**Question 3.1: Switch Statement**

Write a `switch` statement fragment that handles the following menu options:

- 1 → Create product  
- 2 → Read products  
- 3 → Update product  
- 4 → Delete product  

```java
switch(option) {
    // Your code here
}
```

---

### Section 4: Delete (D) – Validation Methods

**Question 4.1: productsExist Method**

Write a method in `Store` that returns `true` if there is at least one product stored.

```java
public boolean productsExist() {
    // Your code here
}
```

---

**Question 4.2: Index Validation**

Complete the method so that it checks whether an index is valid.

```java
public boolean isValidIndex(int index) {
    // index must be >= 0 and < products.size()
    // Your code here
}
```

---

### Section 5: Delete (D) – Store Logic

**Question 5.1: deleteProduct Method**

Write the full method that deletes and returns a product if the index is valid.
Return `null` otherwise.

```java
public Product deleteProduct(int indexToDelete) {
    // Your code here
}
```

---

### Section 6: Delete (D) – Driver Logic

**Question 6.1: Driver Delete Case**

Write the code for **case 4** in the `switch` statement that:

1. Checks products exist  
2. Asks the user for an index  
3. Attempts to delete the product  
4. Prints a success or failure message  

```java
case 4 -> 
    // Your code here
 
```

---

### Section 7: Update (U) – Store Logic

**Question 7.1: updateProduct Method**

Write a method in `Store` that updates a product at a given index.
Return `true` if the update succeeds, `false` otherwise.

```java
public boolean updateProduct(int index, Product updatedProduct) {
    // Your code here
}
```

---

### Section 8: Update (U) – Driver Logic

**Question 8.1: Driver Update Case**

Write the code for **case 3** in the `switch` statement that:

1. Checks products exist  
2. Asks the user for an index  
3. Reads in updated product details  
4. Calls `updateProduct`  
5. Prints a suitable message  

```java
case 3 -> 
    // Your code here
 
```

---

### Section 9: Read (R) – Code Practice

**Question 9.1: listProducts Method**

Write a method in `Store` that returns a formatted `String` containing all products
with their index numbers.

```java
public String listProducts() {
    // Your code here
}
```

---

### Section 10: Create (C) – Code Practice

**Question 10.1: addProduct Method**

Write a method in `Store` that adds a `Product` to the `ArrayList`.

```java
public void addProduct(Product product) {
    // Your code here
}
```

---

### Section 11: Debugging

**Question 11.1: Runtime Error**

What is wrong with the following code?

```java
products.remove(products.size());
```

Explain **why** it fails and how to fix it.

---

**Question 11.2: Logic Error**

Why is the following update logic unsafe?

```java
products.set(index, updatedProduct);
```

---

### Section 12: Extension (Optional)

**Question 12.1: Safer Update**

Rewrite the update logic so that it is safe and cannot cause a runtime error.

---


