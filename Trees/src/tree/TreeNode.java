package tree;
import java.util.ArrayList;

public class TreeNode<T> {
	public TreeNode(T data) {
		this.data = data;
		this.children = new ArrayList<>();
	}
	T data;
	ArrayList<TreeNode<T>> children;
}
