# Measuring Stability Using the Reflection API





﻿B.Sc. Software Development – Advanced OO Design Principles and Patterns Assignment (2016).
------------------------------------------------------------------------------------------

*************************************************
####Measuring Stability using Reflection API.
*************************************************

The provided program is measuring positional stability
(I) for each class from given JAR archive.
Stability or Instability is a degree of difficulty
of modification for class or module. Its value range is           
between [0...1]. The smaller instability value the
more stable is class and the more likely it has to be
abstract.
Instability is calculating using formula I = Ce / Ce + Ca;
where Ce – is Efferent coupling, and Ca – is Afferent coupling.
Efferent coupling is a number of types that current
class is depends on (pointing in).
Afferent coupling is a number of types that depends
on current class (pointing out).

JAR archive is a collection of classes that could be
represented as digraph with dependencies as
a connections. By representing the class as a node
we can calculate Ca as a number of outgoing
edges, and Ce as a number edges that pointing on
current class.


**************************************************
####PROGRAM
**************************************************
Program is packaged into ie.gmit.sw root package.
Main class Runner.class with the main method located
in the root package - ie.gmit.sw.Runner.class

Program is divaded on another three packages
* reflection - ie.gmit.sw.reflection
* graph - ie.gmit.sw.graph
* measure - ie.gmit.sw.measure

######_ie.gmit.sw.reflection_

Reflection package contains supproriting class JarClassLab.class
This class carries the load of total class inspection using
the java reflection API technique.

######_ie.gmit.sw.graph_

It is used by GraphHandler.class for defining edges (dependencies)
between nodes (classes). GraphHandler it self sits
in the graph package and used to build the graph.
Graph is represented by JarGraph.class that contains
list of nodes and deligated methods.
Also in this package are Node.class and Edge.class
which represent nodes and edges respectively.

######_ie.gmit.sw.measure_

Measure package is responcible for calculate
and get results.
The interface Measurable contains method
public void measure. All classes that measures
degrees, states, and other values should implement
this interface. InstabilityMeasure is such class.
Instability is class that represents result.
InstabilityMeasure returns List of Instability
classes.


**************************************************
####HOW TO RUN
**************************************************

To run this program you need to have Java 8 installed.
1) Open terminal and navigate to directory with stability.jar location.
2) Run programm:
java -cp ./stability.jar:./path/to/inspectable.jar ie.gmit.sw.Runner ./path/to/inspectable.jar
where ./path/to/inspectable.jar is path to JAR archive that should be inspected for positional instabilty.


**************************************************
####RESULT
**************************************************

Result is printed in terminal as:

1) table of values:

----------------------------------------
|       CLASSES        | CE | CA |  I  |
|----------------------|----|----|-----|
|                Person|  0 |  4 | 0.0 |
|                Doctor|  1 |  1 | 0.5 |
----------------------------------------

2) Nodes representation:

---NODE(Doctor)---

---IN---
      (Person) --> (Doctor)
---OUT---
      (Doctor) --> (Surgeon)
___________________________________

---NODE(Person)---

---IN---
      
---OUT---
      (Person) --> (Doctor)
      (Person) --> (Teacher)
      (Person) --> (Futballer)
      (Person) --> (Student)
____________________________________

and so on...
