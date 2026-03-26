# Design Notes

## Why ArrayList Instead of Array?

Arrays in Java have a fixed size — you must declare the size upfront (e.g., `Student[] students = new Student[100]`). This creates two problems:

1. **Wasted memory** if fewer items are added than the array size.
2. **ArrayIndexOutOfBoundsException** if more items are added than expected.

ArrayList solves both problems. It grows and shrinks dynamically as items are added or removed. We don't need to know how many students or courses will be created ahead of time.

ArrayList also provides built-in methods like `add()`, `remove()`, and `isEmpty()` that make code simpler and more readable compared to manually managing array indices.

## Where Static Members Are Used and Why

**IdGenerator** — uses `static` fields (`studentIdCounter`, `courseIdCounter`, `enrollmentIdCounter`) and `static` methods (`getNextStudentId()`, etc.).

Why static? Because ID counters belong to the class itself, not to any specific instance. There should be one shared counter across the entire application. If these were instance fields, creating a new `IdGenerator` object would reset the counters to zero, leading to duplicate IDs.

Static methods match static fields — they can be called directly as `IdGenerator.getNextStudentId()` without needing to create an object.

**InputValidator** — uses `static` methods (`validateNotEmpty()`, `validateEmail()`, `validatePositiveInt()`).

Why static? These are pure utility methods that don't depend on any object state. They take input, validate it, and return a result. There's no reason to create an InputValidator instance just to call a validation method.

## Where Inheritance Is Used and What It Provides

**Person** is the base class with shared fields: `id`, `firstName`, `lastName`, `email`.

**Student** extends Person and adds: `batch`, `active`.
**Trainer** extends Person and adds: `expertise`.

### What we gained:

1. **No code duplication** — `id`, `firstName`, `lastName`, `email`, and their getters/setters are written once in Person and inherited by both Student and Trainer.

2. **Polymorphism** — Person defines `getDisplayName()` which returns `"firstName lastName"`. Student overrides it to include the batch: `"firstName lastName (batch)"`. Trainer overrides it to include expertise: `"firstName lastName (Expertise: Java)"`. The method name is the same, but the behavior changes based on the object type.

3. **Constructor chaining** — Student and Trainer use `super()` to call Person's constructor, avoiding re-initialization of shared fields in every subclass.
