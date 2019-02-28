import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// build a balanced binary tree from a sequential list of integers in random order
public class TreeProblem {
	
	static List<Integer> list = new ArrayList<Integer>();
	
	static Set<Integer> visited = new HashSet<Integer>();

	public static void main(String[] args) {
		
		// problem setup
		
		list.add(1);
		list.add(4);
		list.add(9);
		list.add(7);
		list.add(10);
		list.add(13);
		list.add(5);
		list.add(3);
		list.add(2);
		list.add(6);
		list.add(11);
		list.add(8);
		list.add(12);
		
		// solution starts here
		
		Collections.sort(list);
		
		Node tree = buildTree(list);
		
		// BFS, prints node value, depth, and side relative to the parent
		printTree(tree, 1);
	}
	
	public static class Node {
		
		public Node(int value) {
			this.value = value;
		}
		
		int value;
		Node left;
		Node right;
	}
	
	public static Node buildTree(List<Integer> list) {
		
		if (list.size() == 1) {
			return new Node(list.get(0));
		}
		
		int size = list.size();
		int midIndex = (int)Math.floor(size/2);
		int midValue = list.get(midIndex);
		
		Node n = new Node(midValue);
		
		List<Integer> left = list.subList(0, midIndex);
		List<Integer> right = list.subList(midIndex, size);
		
		n.left = buildTree(left);
		n.right = buildTree(right);
		
		return n;
	}
	
	// a sloppy BFS
	public static void printTree(Node n, int depth) {
		
		if (!visited.contains(n.value)) {
			System.out.println("value:" + n.value + " depth:" + depth + " orientation:ROOT");
			visited.add(n.value);
		}
		depth = depth + 1;
		if (n.left != null) {
			if (!visited.contains(n.left.value)) {
				System.out.println("value:" + n.left.value + " depth:" + depth + " orientation:L" + " parent:" + n.value);
				visited.add(n.left.value);
			}
		}
		if (n.right != null) {
			if (!visited.contains(n.right.value)) {
				System.out.println("value:" + n.right.value + " depth:" + depth + " orientation:R" + " parent:" + n.value);
				visited.add(n.right.value);
			}
		}
		if (n.left != null) {
			printTree(n.left, depth);
		}
		if (n.right != null) {
			printTree(n.right, depth);
		}
	}
	
}
