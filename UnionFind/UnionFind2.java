/**
初始化的时候：
0 1 2 3 4 5 6 7 8 9
此时每一个节点都是根节点，它都是指向自己的

parent  0  1  2  3  4  5  6  7  8  9
--------------------------------------
        0  1  2  3  4  5  6  7  8  9

在经过数次union操作之后(union(4, 3), union(3, 8), union(6, 5), union(9, 4), union(2, 1), union(5, 0), union(7, 2), union(6, 2))
此处的union(9, 4)是让9的节点指向4节点的根节点而不是指向4节点，充分利用树结构而不是链表结构
此处的union(6, 2)是让6的根节点0指向2的根节点1
         1               8
      /  |  \            |  \
     0   2   7           3   9
     |                   |
     5                   4
     |
     6

0  1  2  3  4  5  6  7  8  9
---------------------------------------
1  1  1  8  3  0  5  1  8  8

*/
public class UnionFind2 {

}