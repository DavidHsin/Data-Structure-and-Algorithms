//基于rank的优化，rank[i]表示根节点为i的树的高度
/**
 * 进行union(4, 2)操作
 *                7    8
 *        / / / / |    |
 *       0  1 2 5 6    3
 *                     |
 *                     4
 * 第一种情况：
 *                7
 *        / / / / | \
 *       0  1 2 5 6  8
 *                   |
 *                   3
 *                   |
 *                   4
 * 此时树的高度为4
 * 
 * 第二种情况：
 *                    8
 *                  / |
 *                7   3
 *        / / / / |   |
 *       0  1 2 5 6   4
 * 此时树的高度依旧是3
 * */

public class UnionFind4 implements UF {
	
	private int[] parent;
	private int[] rank;		//rank[i]表示以i为根的集合所表示的树的层数
	
	public UnionFind4(int size) {
		parent = new int[size];
		rank = new int[size];
		
		for(int i = 0; i < size; i++) {
			parent[i] = i;	//初始化每个节点都指向它自己，都是一棵独立的树
			rank[i] = 1;	//对于一个节点来说，它的层数也是1
		}
	}
	
	@Override
	public int getSize() {
		return parent.length;
	}
	
	//查找过程，比如此时需要查找节点6，那最终返回的是根节点1的集合编号1
	//时间复杂度为O(h)，h为树的高度
	private int find(int p) {
		if(p < 0 && p > parent.length) {
			throw new IllegalArgumentException("p is out of bound.");
		}
		
		while(p != parent[p]) {
			//比如查找节点6，此时节点6的parent是5，那就一直往上查找，知道根节点1的parent是1，查找停止
			p = parent[p];
		}
		return p;
	}
	
	//查看元素p和元素q是否所属一个集合
	//O(h)时间复杂度，h为树的高度
	@Override
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}
	
	//合并元素p和元素q所属的集合
	//O(h)时间复杂度，h为树的高度
	@Override
	public void unionElements(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		
		if(pRoot == qRoot) {
			return;
		}
		
		//根据两个元素所在树的rank不同判断合并方向
		//将rank低的集合合并到rank高的集合上
		if(rank[pRoot] < rank[qRoot]) {
			parent[pRoot] = qRoot;	//此时不用维护rank，因为将小的指向大的，那大的肯定不用维护size
		}else if(rank[qRoot] < rank[pRoot]) {
			parent[qRoot] = pRoot;	//同理
		}else {
			parent[qRoot] = pRoot;	//当这两棵树高度相等的时候，比如union(0,1)，此时0节点和1节点都是根节点，这时候rank就要变成2
			rank[pRoot] += 1;		//注意此处是加1，而不是加rank[qRoot]，因为不是链表结构而是树结构
		}
		/**
		 * 第一种：union(0, 1), union(2, 3)
		 * 0   2
		 * |   |
		 * 1   3
		 * 
		 * 第二种，union(0, 2)
		 * 0
		 * | \
		 * 1  2
		 *    |
		 *    3
		 * 由此可见，都是rank += 1
		 * 
		 * */
	}
}
