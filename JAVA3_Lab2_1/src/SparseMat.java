import java.util.Iterator;
import cs_1c.*;

public class SparseMat<E> implements Cloneable{

	protected int rowSize, colSize;
	protected E defaultVal;
	protected FHarrayList <FHlinkedList<MatNode>> rows;
	   
	SparseMat( int numRows, int numCols, E defaultVal){
		// check row and column size
		if (numRows < 1 || numCols < 1)
			return;
		
		this.rowSize = numRows;
		this.colSize = numCols;
		this.defaultVal = defaultVal;
	
		allocateEmptyMatrix();
	}
	
	public E get(int r, int c){
		if (r < 0 || r > rowSize - 1 ||
			c < 0 || c > colSize - 1)
			throw new IndexOutOfBoundsException();
		
		MatNode<?> targetNode = FindNode(r,c);
		
		return targetNode == null ? defaultVal : (E)targetNode.data;
	}
	
	public boolean set(int r, int c, E x){
		if (r < 0 || r > rowSize - 1 ||
			c < 0 || c > colSize - 1)
			return false;
		
		MatNode targetNode = FindNode(r,c);
		
		if (targetNode != null){
			if (x.equals(defaultVal)){
				// remove target node
				rows.get(r).remove(targetNode);
			}
			else
				// update target node data
				targetNode.data = x;
		}
		else
		{
			if (!x.equals(defaultVal)){
				// add new node
				rows.get(r).add(new MatNode<E>(c, x));
			}
		}
		
		return true;
	}
	
	private MatNode<?> FindNode(int r, int c) {
		FHlinkedList<MatNode> row = rows.get(r);
		Iterator<MatNode> itr = row.iterator();
		MatNode<?> tempNode;
		
		while (itr.hasNext()){
			tempNode = (MatNode<?>)itr.next();
			if (tempNode.col == c)
				return tempNode;
		}
		
		return null;
	}
	
	private void allocateEmptyMatrix(){
		rows = new FHarrayList<FHlinkedList<MatNode>>();
		
		for (int i = 0; i < rowSize; i++) {
			rows.add(new FHlinkedList<MatNode>());
		}
	}
	
	public void clear(){
		allocateEmptyMatrix();
	}
	
	public void showSubSquare(int start, int size){
		MatNode tempNode;
		
		for (int i = start; i < start + size; i++){
			for (int k = start; k < start + size; k++){
				tempNode = FindNode(i,k);
				System.out.printf("%s ",tempNode == null ? 
					defaultVal.toString() : tempNode.data.toString());
			}
			System.out.print('\n');
		}
	}
	
	//public Object clone() throws CloneNotSupportedException {
    //
	//}
}
