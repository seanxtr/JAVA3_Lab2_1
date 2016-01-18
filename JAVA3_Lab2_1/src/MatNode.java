// protected enables us to safely make col/data public
public class MatNode<E> implements Cloneable {
   public int col;
   public E data;

   // we need a default constructor for lists
   MatNode() {
      col = 0;
      data = null;
   }

   MatNode(int cl, E dt) {
         col = cl;
         data = dt;
   }

   public Object clone() throws CloneNotSupportedException {
      // shallow copy
      MatNode<?> newObject = (MatNode<?>)super.clone();
      return (Object) newObject;
   }
};
