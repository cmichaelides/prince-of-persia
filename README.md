# prince-of-persia
3D Discrete Labyrinth Problem


The Prince of Persia has been thrown onto the top level of Jaffar's underground labyrinth. The labyrinth consists of h levels strictly on top of each other. Each level is split into m by n areas. Some areas have columns that support ceiling, some areas are free. The Prince can move only to free areas. To move to the level below, the Prince can break the floor underneath him and jump down if there is no column underneath. Every move takes the Prince 5 seconds. A Princess is waiting for the Prince at the lowest level. 

Write a program that will help the Prince to save the Princess as fast as possible by finding the shortest path between them and outputting the time it took the Prince to find the Princess. 

The Prince's location is marked with 1, the Princess's location is marked with 2. A . marks a free spot and o marks a column. 

The labyrinth can be read in from STDIN where the first three lines will give you m, n, and h, followed by each level of the labyrinth as shown above. Please output to STDOUT the total time taken by the Prince to find the Princess. 

P.S. Because good always trumps evil, you can assume that there is always a way for the Prince to find his Princess. 
