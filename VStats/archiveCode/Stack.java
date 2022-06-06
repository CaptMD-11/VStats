import java.util.ArrayList;

	public class Stack<T>{

	  	// data
		private ArrayList<T> data; // int[] data, String[] myStr
		// T is generic data type
		private int size; // # of elements in the stack - not the storage space size 
		
		// constructor - should initialize array to have 10 spaces 
		
		public Stack() {
			data = new ArrayList<T>();
			size = 0;
		}
		
		// getter for size 
		public int size() {
			return size;
		}
		
		
		// push - add element to list 
		public void push(T el) {
			data.add(el);
			size++; // update size 
		}  
		 
		
		// pop - remove and RETURN element from the list 
		// should be from the "top" of the list 
		public T pop() {
			// remove & return the element from the top of the list
			size--;
			return data.get(data.size()-1);
			// update other relevant instance variables 
		}
		
		public String toString() { 
			return data.toString();
		} 
		
		public static void main(String[] arg) {
			
			// add lines of code to test if our implementation of a stack works 
			Stack<Integer> test = new Stack<Integer>();
			
			test.push(1);
			test.push(2);
			test.push(3);
			test.push(4);
			
			test.pop();
			
			////////System.out.println(test.toString());
			
		}


	
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}